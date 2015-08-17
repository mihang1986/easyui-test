package com.sao.easyui.test.controller.aop;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sao.easyui.test.controller.bean.ResultBean;
import com.sao.easyui.test.pub.Constants;

@Aspect
public class ThrowingInterceptor {
	private final Logger logger = LoggerFactory.getLogger(ThrowingInterceptor.class);
	
	@Pointcut("execution(* com.sao.easyui.test.controller.*Controller.*(..))")
	private void pointcut(){};
	
	@Around("pointcut()")
	public Object throwing(ProceedingJoinPoint pjp) throws Throwable{
		Object object = null;
		try{
			object = pjp.proceed();
		}catch(Exception err){
			MethodSignature signature = (MethodSignature) pjp.getSignature();
			Method method = signature.getMethod();
			if(method.isAnnotationPresent(ResponseBody.class)){
				logger.info(String.format("执行方法[%s]出现错误:%s", pjp.getTarget().getClass().getName() + "." + method.getName(), err));
				return new ResultBean(Constants.RESULT_FAILED, "", null);
			}else{
				return "error";
			}
		}
		
		return object;
	}
}
