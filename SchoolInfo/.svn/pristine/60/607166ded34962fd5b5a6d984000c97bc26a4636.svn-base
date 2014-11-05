package com.schoolinfo.view;

import com.schoolinfo.MainActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;
import android.os.Message;

/**
 * 进度框，单例类
 * 
 * @author huangwubin 2014-10-2
 */
public class MyProgressDialog extends ProgressDialog {

	final static String LOGINING_STRING="正在登陆...";
	final static String RELEASING_STRING="正在发布...";
	
	
	private static MyProgressDialog myProgressDialog;
	private Handler mHandler;//消息
	public MyProgressDialog(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		
		this.setTitle("提示");
//		this.setMessage("内容");
		mHandler=new Handler(){
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				switch (msg.what) {
				case 1:
					myProgressDialog.show();
					break;
				case 2:
					myProgressDialog.cancel();
					break;
				default:
					break;
				}
				super.handleMessage(msg);
			}
		};
	}

	public static MyProgressDialog getInstance(){
		if (myProgressDialog==null) {
			myProgressDialog=new MyProgressDialog(MainActivity.getInstance());
		}
		return myProgressDialog;
	}
	
	/**
	 * 显示或取消进度框
	 * @param isShow 是否显示
	 * @param type 1为登陆，2为发布
	 */
	public void setisShow(boolean isShow,int type){
//		if (!isShow) {
//			
//		}
//		MainActivity.getInstance().runOnUiThread(new Runnable() {
//			
//			@Override
//			public void run() {
//				// TODO Auto-generated method stub
//				myProgressDialog.show();
//			}
//		});
		
		if (isShow) {
			switch (type) {
			case 1:
				myProgressDialog.setMessage(LOGINING_STRING);
				break;
			case 2:
				myProgressDialog.setMessage(RELEASING_STRING);
				break;
			default:
				break;
			}
			mHandler.sendEmptyMessage(1);
		}
		else {
			mHandler.sendEmptyMessage(2);
		}
	}
	
	/**
	 * 显示或取消进度框
	 * @param isShow
	 */
	public void setisShow(boolean isShow){
		
		if (isShow) {
			mHandler.sendEmptyMessage(1);
		}
		else {
			mHandler.sendEmptyMessage(2);
		}
	}
}
