package com.dartou.lib.pay;

import android.content.Context;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import com.dartou.lib.network.http.BasicHttpClient;
import com.dartou.lib.utils.LogUtil;
/**
 * 微米支付类，需要相关短信权限
 *  <uses-permission android:name="android.permission.SEND_SMS" />
    <uses-permission android:name="android.permission.READ_SMS" />
    <uses-permission android:name="android.permission.WRITE_SMS" />
    <uses-permission android:name="android.permission.RECEIVE_SMS" />
 * @author cyn
 */
public class WimiPay {
	Context context ;
	public WimiPay(Context context) {
		this.context = context;
	}

	/**
	 * @param wimicode 微米计费点
	 * @param extData 自定义数据 不超过8位
	**/
	public void startPay(String wimicode, String extData,WimiListener wimiListener) {
		TelephonyManager tp = ((TelephonyManager) (context
				.getSystemService("phone")));
		String imsi = tp.getSimOperator();
		String imei = tp.getDeviceId();
		if(TextUtils.isEmpty(imsi)||TextUtils.isEmpty(imei)){
			wimiListener.faild("没有手机SIM卡，无法进行短信支付");
		}else{
			new GetContentTask(context, imsi, imei, wimicode, extData,wimiListener).execute();
		}
	}

	public class GetContentTask extends BasicHttpClient {

		WimiListener mWimiListener;
		/**
		 * 
		 * @param context
		 * @param wimicode
		 *            微米计费点
		 * @param extData
		 *            自定义数据 不超过8位
		 */
		public GetContentTask(Context context, String imsi,String imei,String wimicode, String extData,WimiListener wimiListener) {
			mWimiListener = wimiListener;
			String url = "http://115.29.187.79/mmpm/getWimiPayMore?channel=0001"
					+ "&imsi="+ imsi
					+ "&imei="+ imei
					+ "&wimicode="+ wimicode 
					+ "&extData=" + extData;
			setUrl(url);
			LogUtil.i("WimiPay", "WimiPay-->url-->"+url);
		}

		@Override
		public void success(String result) {
			LogUtil.i("WimiPay", "WimiPay-->result-->"+result);
			if(TextUtils.isEmpty(result)){
				mWimiListener.faild("获取数据失败，无法支付！");
			}else{
				try {
					int smsportStartIndex = result.indexOf("<smsport>")+9;
					int smsportEndIndex = result.indexOf("</smsport>");
					int smscontentStartIndex = result.indexOf("<smscontent>")+12;
					int smscontentEndIndex = result.indexOf("</smscontent>");
					String smsport = result.substring(smsportStartIndex, smsportEndIndex).trim();
					String smscontent = result.substring(smscontentStartIndex, smscontentEndIndex).trim();

					if(smscontent.equals("error")){
						mWimiListener.faild("短信支付失败！");
					}else{
						SmsManager smsManager =SmsManager.getDefault();
					    smsManager.sendTextMessage(smsport,null, smscontent, null, null);
					    mWimiListener.success();
					}
				    
				} catch (Exception e) {
					mWimiListener.faild("短信支付失败！");
				}
			}

		}

		@Override
		public void faild() {
			mWimiListener.faild("网络超时");
		}

	}

	 public interface WimiListener{
		 public void success();
		 public void faild(String msg);
	 }

}
