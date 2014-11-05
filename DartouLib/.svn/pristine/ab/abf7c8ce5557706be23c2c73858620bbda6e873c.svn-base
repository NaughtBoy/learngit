package com.dartou.lib.utils;

import java.util.Calendar;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.text.format.DateFormat;
/**
 * 获取手机卡运营商/手机网络类型 
 * @author cyn
 *
 */
public class InfoUtil {
	public static final int SERVER_UNICOM = 1;
	public static final int SERVER_MOBILE = 2;
	public static final int SERVER_TELECOM = 3;
	public static final int SERVER_SIKAI = 4;
	public static final int SERVER_ALIPAY = 5;
	public static final int SERVER_UNIONPAY = 6;
	
	public static final int NETWORK_WIFI = 1;
	public static final int NETWORK_2G = 2;
	public static final int NETWORK_3G = 3;
	public static final int NETWORK_4G = 4;

	public static int getPhoneType(Context context) {
		try {
			String IMSI = ((TelephonyManager) (context.getSystemService("phone")))
					.getSimOperator();
			LogUtil.i("INfoUtil", "IMSI-->"+IMSI);
			if(TextUtils.isEmpty(IMSI)){
				return 0;
			}
			if (IMSI.startsWith("46000")||IMSI.startsWith("46002")||IMSI.startsWith("46007")) {
				return SERVER_MOBILE;
			}else if (IMSI.startsWith("46001")||IMSI.startsWith("46006")) {
				return SERVER_UNICOM;
			} else if (IMSI.startsWith("46003")||IMSI.startsWith("46005")) {
				return SERVER_TELECOM;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return 0;
	}
	
	/** 
	 * 得到当前的手机网络类型 
	 *  
	 * @param context 
	 * @return 
	 */  
	public static int getCurrentNetType(Context context) {  
		int type = 0;
	    ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);  
	    NetworkInfo info = cm.getActiveNetworkInfo();  
	    if (info == null) {  
	        type = 0;  
	    } else if (info.getType() == ConnectivityManager.TYPE_WIFI) {  
	        type = NETWORK_WIFI;  
	    } else if (info.getType() == ConnectivityManager.TYPE_MOBILE) {  
	        int subType = info.getSubtype();  
	        if (subType == TelephonyManager.NETWORK_TYPE_CDMA || subType == TelephonyManager.NETWORK_TYPE_GPRS  
	                || subType == TelephonyManager.NETWORK_TYPE_EDGE) {  
	            type = NETWORK_2G;  
	        } else if (subType == TelephonyManager.NETWORK_TYPE_UMTS || subType == TelephonyManager.NETWORK_TYPE_HSDPA  
	                || subType == TelephonyManager.NETWORK_TYPE_EVDO_A || subType == TelephonyManager.NETWORK_TYPE_EVDO_0  
	                || subType == TelephonyManager.NETWORK_TYPE_EVDO_B) {  
	            type = NETWORK_3G;  
	        }
	        else if (subType == 13) {// LTE是3g到4g的过渡，是3.9G的全球标准  TelephonyManager.NETWORK_TYPE_LTE
	            type = NETWORK_4G;  
	        }  
	    }  
	    return type;  
	}
	
	public  static String getNetwork(Context context){
		String typeS[] = {"WIFI","2G","3G","4G"};
		int type = getCurrentNetType(context);
		if(type<=0||type>=5){
			return "OTHER";
		}else{
			return typeS[type-1];
		}
	}
	
	/**
	 * 获取系统时间
	 * @return
	 */
	public static String getDate() {
		Calendar c = Calendar.getInstance();

		String year = String.valueOf(c.get(Calendar.YEAR));
		String month = String.valueOf(c.get(Calendar.MONTH) + 1);
		String day = String.valueOf(c.get(Calendar.DAY_OF_MONTH));
		String hour = String.valueOf(c.get(Calendar.HOUR_OF_DAY));
		 String mins = String.valueOf(c.get(Calendar.MINUTE));

		StringBuffer sbBuffer = new StringBuffer();
		sbBuffer.append(year + "-" + month + "-" + day + " " + hour + ":"
				+ mins);

		return sbBuffer.toString();
	}
}