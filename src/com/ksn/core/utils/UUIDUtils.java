package com.ksn.core.utils;

import java.util.UUID;

/**
 * UUID生成工具
 * @author ccf
 * @date 2016年3月8日
 *
 */
public class UUIDUtils {
	
	/**
	 * 生成UUID
	 * @return
	 */
	public static String generate() {
		return UUID.randomUUID().toString().replaceAll("\\-", "");
	}

}
