package com.sao.easyui.test.controller.anno;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 字典返回注解
 * @author 29416
 *
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TranslateNeed {
	//public static final String DEFAULT_CARRY_FIELD = "dicts";
	
	//public enum TYPE{WEAVE, CARRY}
	
	/**
	 * 字典填充方式
	 * CARRY 夹带,会为返回结果中增加一个字段,用于携带所有TranslatePart所涉及的字典的全部值
	 * WEAVE 织入,会为返回结果中TranslatePart字段创建翻译字段
	 * @author 29416
	 *
	 */
	//public TYPE type() default TYPE.CARRY;
	
	/**
	 * 默认夹带字段名称
	 * @return
	 */
	//public String carryField() default DEFAULT_CARRY_FIELD;
}
