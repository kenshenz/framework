package com.ksn.core.worker.annonation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Worker {
	
	/**
	 * Worker包名
	 * @return
	 */
	String packageName() default "";
	
	/**
	 * Worker名称
	 * @return
	 */
	String name() default "";

	/**
	 * Worker版本
	 * @return
	 */
	String version() default "1.0.0";
	
}
