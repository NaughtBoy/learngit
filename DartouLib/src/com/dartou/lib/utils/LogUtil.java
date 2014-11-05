package com.dartou.lib.utils;

import android.util.Log;

/**
 * 日志工具类
 * @author cyn
 *
 */
public class LogUtil {
	
	public static boolean logenable = false;
	
	//debug
	public static void d(String tag, String msg) {
		if(logenable){
			Log.d(tag, msg);	
		}
	}

	//error
	public static void e(String tag, String msg) {
		if(logenable){
			Log.e(tag, msg);	
		}
	}
	
	//info
	public static void i(String tag, String msg) {
		if(logenable){
			Log.i(tag, msg);	
		}
	}
	
	//设置是否打印日志
	public static void setDebug(boolean isDebug){
		logenable = isDebug;
	}
	
}
