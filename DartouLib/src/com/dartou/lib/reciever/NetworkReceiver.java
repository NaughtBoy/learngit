package com.dartou.lib.reciever;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.text.TextUtils;
import android.util.Log;

/**
 * 自定义网络广播接收器
 * 
 * 如果不定义这个 Receiver，则： 1) 默认用户会打开主界面 2) 接收不到自定义消息
 * @author cyn
 */
public abstract class NetworkReceiver extends BroadcastReceiver {
	private static final String TAG = "MyReceiver";

	private static boolean isFirst = true;
	@Override
	public void onReceive(Context context, Intent intent) {
		
		Change(context);
		
		Log.e(TAG, "网络状态改变");

		if(isFirst){
			isFirst = false;
			return;
		}
		String action = intent.getAction();
        Log.i(TAG, "PfDataTransReceiver receive action " + action);
        if(TextUtils.equals(action, "android.net.conn.CONNECTIVITY_CHANGE")){//网络变化的时候会发送通知
            Log.i(TAG, "网络变化了");
            boolean success = false;

    		// 获得网络连接服务
    		ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(Activity.CONNECTIVITY_SERVICE);
    		// State state = connManager.getActiveNetworkInfo().getState();
    		State state = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
    				.getState(); // 获取网络连接状态
    		if (State.CONNECTED == state) { // 判断是否正在使用WIFI网络
    			Log.i(TAG, "网络->使用wifi");
    			success = true;
    		}

    		state = connManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
    				.getState(); // 获取网络连接状态
    		if (State.CONNECTED == state) { // 判断是否正在使用GPRS网络
    			Log.i(TAG, "网络->使用GPRS");
    			success = true;
    		}

    		if (success) {
    			change(context);
    		}else{
    			broken(context);
    		}
            return;
        }
		
	}
	
	/**
	 * 判断是否wifi
	 * @param context
	 * @return
	 */
	public static boolean isWifi(Context context){
		// 获得网络连接服务
		ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(Activity.CONNECTIVITY_SERVICE);
		// State state = connManager.getActiveNetworkInfo().getState();
		State state = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
				.getState(); // 获取网络连接状态
		if (State.CONNECTED == state) { // 判断是否正在使用WIFI网络
			return true;
		}
		return false;
	}
	
	public static boolean isNetworkAvailable(Context context) {  
        ConnectivityManager connectivity = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);  
        if (connectivity == null) {  
            Log.i("NetWorkState", "Unavailabel");  
            return false;  
        } else {  
            NetworkInfo[] info = connectivity.getAllNetworkInfo();  
            if (info != null) {  
                for (int i = 0; i < info.length; i++) {  
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {  
                        Log.i("NetWorkState", "Availabel");  
                        return true;  
                    }  
                }  
            }  
        }  
        return false;  
    }  
	
	/**
	 * 网络切换
	 */
	public abstract void change(Context context);
	
	/**
	 * 断网
	 */
	public abstract void broken(Context context);
	
	public abstract void Change(Context context);

}
