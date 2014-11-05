package com.schoolinfo.control;

import android.R.integer;
import android.content.Context;
import android.graphics.Rect;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.schoolinfo.MainActivity;

/**
 * 全局控制类
 * 
 * @author huangwubin 2014-10-26
 */
public class GlobalControl {

	private static int statusBarHeight;//状态栏高度
	private static int systemVersion;//系统版本号
	public static void init(){
		
	}
	
	public static int getStatusBarHeight(){
		if (statusBarHeight==0) {
			Rect frame = new Rect();
			MainActivity.getInstance().getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);

			statusBarHeight = frame.top;
		}
		return statusBarHeight;
	}
	
	public static int getSystemVersion(){
		if (systemVersion==0) {
			systemVersion = Integer.valueOf(android.os.Build.VERSION.SDK); 
		}
		return systemVersion;
	}
	
	/**
	 * 判断当前是否有网络连接
	 * @return
	 */
	public static boolean isNetworkAvailable() {   
        ConnectivityManager cm = (ConnectivityManager) MainActivity.getInstance()   
                .getSystemService(Context.CONNECTIVITY_SERVICE);   
        if (cm == null) {   
        } else {
            NetworkInfo[] info = cm.getAllNetworkInfo();   
            if (info != null) {   
                for (int i = 0; i < info.length; i++) {   
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {   
                        return true;
                    }
                }
            }
        }
		return false;
	}
}
