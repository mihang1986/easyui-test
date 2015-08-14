package com.sao.easyui.test.controller.anno;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 字典翻译注解
 * @author 29416
 *
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TranslatePart {
	/**
	 * 字典类型
	 * 对于 夹带 方式返回,返回此类型下的所有字典值
	 * 对于 织入 方式返回,根据此值确定字典类型
	 * @return
	 */
	public String type() default "";
	
	/**
	 * 自动生成方法名称
	 * 只对于 织入 方式生效,设置动态代理获取字段值得字段名
	 * @return
	 */
	public String field() default "";
}
