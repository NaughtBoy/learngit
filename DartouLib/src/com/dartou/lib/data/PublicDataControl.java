package com.dartou.lib.data;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Point;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;

import com.dartou.lib.utils.InfoUtil;
import com.dartou.lib.utils.MD5Util;
/**
 * 
 * @author cyn
 * 公共数据：屏幕尺寸，设备信息，网络信息，音量信息等
 */
public class PublicDataControl {

	public static PublicDataControl mCCommondData;
	private PublicDataControl(){
		
	}
	
	public static PublicDataControl getInstance(){
		if(mCCommondData==null){
			mCCommondData = new PublicDataControl();
		}
		return mCCommondData;
	}
	
	public void init(Context context){
		initDivice(context);
		initScreenSize(context);
	}
	
	/**
	 * 保存一个抽象对象
	 */
	public Object m_objData = null;
	
	/**
	 * 初始化设备信息
	 */
	private void initDivice(Context context) {
		DeviceData data = new DeviceData();
		try {
			data.vesion = context.getPackageManager().getPackageInfo(context.getPackageName(),
					0).versionName;
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		data.model = android.os.Build.MODEL;
		data.sdk = android.os.Build.VERSION.SDK;
		data.os = "android-"+android.os.Build.VERSION.RELEASE;
		String deviceId = ((TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
//		String deviceId = ((TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId()+"hdjkashdjsa";
		data.deviceId = MD5Util.MD5(deviceId).toLowerCase();
		
		NetworkInfo networkInfo = getNetworkType(context);
		data.networkInfo = (networkInfo != null ? networkInfo.getTypeName(): "");
		
		data.serviceType = InfoUtil.getPhoneType(context);
		data.networkType = InfoUtil.getCurrentNetType(context);
		data.release = android.os.Build.VERSION.RELEASE;
		SetDeviceData(data);
	}

	/**
	 * 初始化分辨率
	 */
	private void initScreenSize(Context context) {
//		DisplayMetrics dm = new DisplayMetrics();
//		((Application)context).getWindowManager().getDefaultDisplay().getMetrics(dm);
		DisplayMetrics dm = context.getResources().getDisplayMetrics();
		int w = dm.widthPixels;
		int h = dm.heightPixels;
		if (w > h) {
			int temp = h;
			h = w;
			w = temp;
		}
		SetResolution(new Point(w, h));
	}
	
	private  NetworkInfo getNetworkType(Context context){
		ConnectivityManager cm = (ConnectivityManager)context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo network = cm.getActiveNetworkInfo();
		return network;
	}
	
	//分辨率
	Point screenPoint;
	public void SetResolution(Point screenPoint){
		this.screenPoint = screenPoint;
	}
	
	public Point GetResolution(){
		return screenPoint;
	}
	
	DeviceData deviceData;
	public void SetDeviceData(DeviceData deviceData){
		this.deviceData = deviceData;
	}
	
	public DeviceData GetDeviceData(){
		return deviceData;
	}
	
	//保存音量大小
	float soundSize;
	public void fSoundSize(float fSoundSize){
		this.soundSize = fSoundSize;
	}
	
	public float fSoundSize() {
		return soundSize;
	}
	
	//第三方登录方式
	private String loginType = SNSType.NONE;
	public void SetLoginType(String type){
		this.loginType = type;
	}
	
	public String GetLoginType() {
		return loginType;
	}
}
