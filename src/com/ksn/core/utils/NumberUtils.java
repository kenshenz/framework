package com.ksn.core.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class NumberUtils {
	
	/**
	 * 百万
	 */
	public static final long MILLION = 1000000;
	/**
	 * 一亿
	 */
	public static final long HUNDRED_MILLION = 100000000;
	/**
	 * 十亿
	 */
	public static final long BILLION = 1000000000;
	
	/**
	 * 精确到两位小数
	 * @param num
	 * @return
	 */
	public static double scale(double num) {
		return scale(num, 2);
	}
	
	/**
	 * 精确到指定小数位
	 * @param num
	 * @param scale
	 * @return
	 */
	public static double scale(double num, int scale) {
		BigDecimal dec = new BigDecimal(num);
		dec = dec.setScale(scale, RoundingMode.HALF_UP);
		return dec.doubleValue();
	}
	
	/**
	 * 相加
	 * @param nums
	 * @return
	 */
	public static double sum(double... nums) {
		BigDecimal result = new BigDecimal("0");
		for (double n : nums) {
			result = result.add(new BigDecimal(n));
		}
		return result.doubleValue();
	}
	
	/**
	 * 幂运算
	 * @param num
	 * @param p
	 * @return
	 */
	public static double power(double num, double p) {
		double pow = Math.pow(num, p);
		return pow;
	}

}
