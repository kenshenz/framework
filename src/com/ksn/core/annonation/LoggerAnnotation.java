package com.ksn.core.annonation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})   
@Retention(RetentionPolicy.RUNTIME)   
@Documented
public @interface LoggerAnnotation {
	String desc() default "无描述信息";
}
