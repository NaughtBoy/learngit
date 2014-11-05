package com.schoolinfo.view;

import com.schoolinfo.MainActivity;

import a.breaks.is;
import android.content.Context;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * 全局提示框
 * 
 * @author huangwubin 2014-10-2
 */
public class MyToast {
	public static final String DOWNLOAD_IMAGE_LOSE="下载图片失败";
	public static final String DOWNLOAD_IMAGE_SUCCESS="下载成功";
	public static final String LOGIN_LOSE="账号或密码错误，请重新输入";
	public static final String LOGIN_LOSE_NULL="账号或密码不能为空";
	public static final String RELEASE_SUCCESS="发布信息成功";
	public static final String UPLOAD_IMAGE_LOSE="上传图片失败";
	public static final String INFORMATION_IS_EMPTY="信息不能为空";
	public static final String PASSWORD_MUSE_AGREEMENT="请确保两次输入的密码一致";
	public static final String REGIST_SUCCESS="注册成功";
	public static final String NOT_UPLOAD_IMAGE="该用户没有上传图片";
	public static final String SDCARD_NOT_EXIST="SD卡不存在，无法下载图片";
	public static final String CLICK_AGAIN_TO_EXIT="请再次点击返回键退出程序";
	public static final String OLD_PASSWORD_WRONG="旧密码输入错误，请重新输入";
	public static final String CHANGE_PASSWORD_SUCCESS="修改密码成功！";
	public static final String CHANGE_PASSWORD_FAIL="修改密码失败";
	public static final String PASSWORD_NULL="密码不能为空";
	public static final String CURRENT_USER_NO_PHOTO="您没有上传头像，请选择图片";
	public static final String CURRENT_NOT_LOGIN="当前尚未登录，请先登录！";
	public static final String NOT_NETWORK="当前已断开网络，请重新连接网络";
	
	private static final int TIME=3000;
	
	private static Toast myToast;
	
	/**
	 * 显示提示框
	 * @param string
	 */
 	public static void showToast(String string){
 		if (myToast!=null) {
			myToast.setText(string);
		}
 		else {
			myToast=Toast.makeText(MainActivity.getInstance(), string, TIME);
		}
 		myToast.show();
 	}
	
}

