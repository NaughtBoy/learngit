package com.dartou.lib.utils;

public class RandomUtil {
	/**
	 * 生成center-w到center+w之间的随机整数
	 * @param center
	 * @param w
	 * @return
	 */
	public static int Random(int center,int w){
		int result = (int) (center+(Math.random()-0.5)*2*w);
		return result;
	}
}
