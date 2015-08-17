package com.sao.easyui.test.controller.aop;

import java.lang.reflect.Method;

import javax.annotation.Resource;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import com.sao.easyui.test.controller.anno.TranslateNeed;

@Aspect
public class TranslateInterceptor {
	
	@Resource
	private ObjectTranslater objectTranslater;
	
	@Pointcut("execution(* com.sao.easyui.test.controller.*Controller.*(..))")
	private void jsonMethod(){};
	
	@Around("jsonMethod()")
	public Object doBasicProfiling(ProceedingJoinPoint pjp) throws Throwable{
		Object object = pjp.proceed();
		
		MethodSignature signature = (MethodSignature) pjp.getSignature();
		Method method = signature.getMethod();
		if(method.isAnnotationPresent(TranslateNeed.class)){
			return translateObject(object);
		}else{
			return object;
		}
	}
	
	private Object translateObject(Object object) throws InstantiationException, IllegalAccessException{
		return objectTranslater.translate(object);
	}
}
