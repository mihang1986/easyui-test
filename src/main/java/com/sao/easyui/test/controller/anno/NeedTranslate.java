package com.sao.easyui.test.controller.anno;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NeedTranslate {
	/**
	 * 字典类型
	 * @return
	 */
	public String type() default "";
	
	/**
	 * 自动生成方法名称
	 * @return
	 */
	public String field() default "";
}
