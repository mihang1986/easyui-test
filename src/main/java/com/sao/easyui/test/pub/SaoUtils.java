package com.sao.easyui.test.pub;

/**
 * 
 * @author 29416
 *
 */
public class SaoUtils {
	public static Boolean isNativeObject(Object obj){
		return isNativeClass(obj.getClass());
	}
	
	public static Boolean isNativeClass(Class<?> clz) {
		if(clz == String.class){
			return true;
		}
        try { 
           return ((Class<?>)clz.getField("TYPE").get(null)).isPrimitive();
        } catch (Exception e) { 
            return false; 
        } 
    }
}
