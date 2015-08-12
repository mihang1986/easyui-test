package com.sao.easyui.test.controller.aop;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import net.sf.cglib.asm.Type;
import net.sf.cglib.core.Signature;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.InterfaceMaker;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import com.sao.easyui.test.controller.anno.NeedTranslate;
import com.sao.easyui.test.service.DictionaryService;

@Aspect
public class TranslateInterceptor {
	@Resource
	private DictionaryService dictionaryService;
	
	@Pointcut("execution(* com.sao.easyui.test.controller.*.*(..))")
	private void jsonMethod(){};
	
	@Around("jsonMethod()")
	public Object doBasicProfiling(ProceedingJoinPoint pjp) throws Throwable{
		Object object = pjp.proceed();
		
		MethodSignature signature = (MethodSignature) pjp.getSignature();
		Method method = signature.getMethod();
		if(method.isAnnotationPresent(NeedTranslate.class)){
			return translateObject(object);
		}else{
			return object;
		}
	}

	/**
	 * 翻译对象
	 * @param t
	 * @return
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	private <T> T translateObject(final T t) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		Map<String, String> tranMap = new HashMap<String, String>();
		
		
		// 整理需要处理字段的方法
		Class clazz = t.getClass();
		Field[] fields = clazz.getDeclaredFields();
		
		// 如果对象不需要翻译,那么直接返回
		if(clazz.isAnnotationPresent(NeedTranslate.class)){
			return t;
		}
		
		// 开始翻译需要翻译的字段
		for(Field field : fields){
			if(field.isAnnotationPresent(NeedTranslate.class)){
				NeedTranslate nt = field.getAnnotation(NeedTranslate.class);
				if(StringUtils.isNotBlank(nt.field() + nt.type())){
					String fileName = !"".equals(nt.field()) ? nt.field() : field.getName() + "Tran";
					tranMap.put("get" + StringUtils.capitalize(fileName), dictionaryService.translate(nt.type(), BeanUtils.getProperty(t, field.getName())));
				}
			}
		}
		
		// 创建动态代理接口
		InterfaceMaker im = new InterfaceMaker();
		for(Entry<String, String> entry : tranMap.entrySet()){
			im.add(new Signature(entry.getKey(), Type.getType(String.class), new Type[]{}), null);
		}
		Class inter = im.create();
		
		// 创建动态代理对象
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(t.getClass());
		enhancer.setInterfaces(new Class[] { inter });
		enhancer.setCallback(new TranMapMethodInterceptor(tranMap, t));
		
		return (T)enhancer.create();
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
