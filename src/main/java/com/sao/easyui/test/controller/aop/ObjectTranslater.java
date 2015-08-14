package com.sao.easyui.test.controller.aop;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import net.sf.cglib.asm.Type;
import net.sf.cglib.core.Signature;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.InterfaceMaker;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.sao.easyui.test.controller.anno.TranslatePart;
import com.sao.easyui.test.pub.SaoUtils;
import com.sao.easyui.test.service.DictionaryService;

@Component
public class ObjectTranslater {
	
	@Resource
	private DictionaryService dictionaryService;

	public <T> T translate(T object) throws InstantiationException, IllegalAccessException{
		if(null == object){
			return null;
		}
		
		Class<?> clazz = object.getClass();
		if(object instanceof Collection){
			// 如果是一个集合,重构这个集合,将其中的每个字段都代理
			Collection result = (Collection) clazz.newInstance();
			for(Object o : (Collection)object){
				result.add(translate(o));
			}
			return (T)result;
		}else if(clazz.isArray()){
			Object[] arr = (Object[])object;
			for(Integer i=0; i<arr.length; i++){
				arr[i] = translate(arr[i]);
			}
			
			return (T)arr;
		}else{
			Field[] fields  = clazz.getDeclaredFields();
			List<Field> needWarpFields = new ArrayList<Field>();
			
			for(Field f : fields){
				Boolean src = f.isAccessible();
				f.setAccessible(true);
				Object val = f.get(object);
				if(null != val){
					Class sclazz = val.getClass();
					if(!f.isAnnotationPresent(TranslatePart.class)){
						if(!SaoUtils.isNativeClass(sclazz)){
							f.set(object, translate(f.get(object)));
						}
					}else{
						needWarpFields.add(f);
					}
				}
				
				f.setAccessible(src);
			}
			
			return (T)warpObject(object, needWarpFields);
		}
	}
	
	private Object warpObject(Object object, Collection<Field> fields) throws IllegalArgumentException, IllegalAccessException{
		if(fields.size() == 0){
			return object;
		}
		
		Map<String, String> methodMap = new HashMap<String, String>();
		
		for(Field f : fields){
			TranslatePart tp = f.getAnnotation(TranslatePart.class);
			Boolean accessable = f.isAccessible();
			
			String methodName = "get" + (StringUtils.isNotBlank(tp.field()) ? tp.field() : StringUtils.capitalize(f.getName()) + "Tran");
			f.setAccessible(true);
			String methodValue = dictionaryService.translate(tp.type(), f.get(object).toString());
			methodMap.put(methodName, methodValue);
			
			f.setAccessible(accessable);
		}
		
		InterfaceMaker im = new InterfaceMaker();
		for(Entry<String, String> entry : methodMap.entrySet()){
			im.add(new Signature(entry.getKey(), Type.getType(String.class), new Type[] {}), null);
		}
		Class myInterface = im.create();
		
		// 创建动态代理对象
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(object.getClass());
		enhancer.setInterfaces(new Class[] { myInterface });
		enhancer.setCallback(new TranMapMethodInterceptor(methodMap, object));
		
		return enhancer.create();
	}
	
	/**
	 * 内部类包装类
	 * @author 29416
	 *
	 */
	private class TranMapMethodInterceptor implements MethodInterceptor{
		private Map<String, String> methodMap;
		private Object target;
		
		public TranMapMethodInterceptor(Map<String, String> methodMap, Object target){
			this.methodMap = methodMap;
			this.target = target;
		}

		@Override
		public Object intercept(Object object, Method method, Object[] args,
				MethodProxy proxy) throws Throwable {
			if(methodMap.containsKey(method.getName())){
				return methodMap.get(method.getName());
			}else{
				return proxy.invoke(target, args);
			}
		}  
	}
}
