package com.schoolinfo;

import java.io.File;
import java.util.Date;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;

import com.baidu.mapapi.SDKInitializer;
import com.schoolinfo.control.UIControl;
import com.schoolinfo.layout.LoginLayout;
import com.schoolinfo.layout.LostLayout;
import com.schoolinfo.layout.MainGalleryLayout;
import com.schoolinfo.layout.MainLayout;
import com.schoolinfo.layout.MapLayout;
import com.schoolinfo.layout.PersonalLayout;
import com.schoolinfo.layout.ReleaseLayout;
import com.schoolinfo.layout.RunLayout;
import com.schoolinfo.utils.ImageUtils;
import com.schoolinfo.view.MyToast;
import com.schoolinfo.view.ShowPictureView;
import com.testin.agent.TestinAgent;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.net.Uri;
import android.opengl.Visibility;
import android.os.Bundle;
import android.os.Environment;
import android.view.Display;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

/**
 * 单例类，主activity
 * 
 * @author huangwubin 2014-9-4
 */
public class MainActivity extends Activity {

	final public static String BMOBAPPID_STRING = "35422b2fd2e8669360a9d3a2cdba3114";
	final public static String TESTINAGENT_STRING = "a44a6116b63da36fe95a0d8c0bd1ad85";

	public static MainActivity mMainActivity;
	final public static String SDPATH_STRING="/mnt/sdcard/SchoolInfo/";
	public static boolean isSD;//是否有SD卡
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mMainActivity = this;
		//初始化百度地图SDK
		SDKInitializer.initialize(getApplicationContext());
		// 加载bmob SDK
		Bmob.initialize(this, BMOBAPPID_STRING);
		// 加载崩溃分析sdk
		TestinAgent.init(this, TESTINAGENT_STRING);
		// 获取屏幕分辨率并初始化UI数据
		Display mDisplay = getWindowManager().getDefaultDisplay();
		UIControl.init(new Point(mDisplay.getWidth(), mDisplay.getHeight()));
		// 创建SD卡目录
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {// SD卡存在
			isSD=true;
			// 创建目录
			File dir = new File(SDPATH_STRING);
			if (!dir.exists() || !dir.isDirectory()) {
				dir.mkdirs();
			}
		}
		else {
			isSD=false;
		}
		// 设置布局
		this.setContentView(MainLayout.getInstance(this));
		MainLayout.getInstance().changeLayout(new MapLayout(this));
	}

	public static MainActivity getInstance() {
		return mMainActivity;
	}

	private Date mLastBackDownDate;

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK
				&& event.getAction() == KeyEvent.ACTION_DOWN) {

			if (MainLayout.getInstance().mMainGalleryLayout.getVisibility() == View.VISIBLE) {// 画廊运行中
				MainLayout.getInstance().removeView(
						MainLayout.getInstance().mMainGalleryLayout);
				MainLayout.getInstance().mMainGalleryLayout
						.setVisibility(View.GONE);
				return false;
			} else {
				if (mLastBackDownDate == null) {
					mLastBackDownDate = new Date(System.currentTimeMillis());
					MyToast.showToast(MyToast.CLICK_AGAIN_TO_EXIT);
					return false;
				}
				long current = (new Date(System.currentTimeMillis()).getTime());
				long last = mLastBackDownDate.getTime();
				if (new Date(current - last).getSeconds() <= 2) {
					System.exit(0);
				} else {
					mLastBackDownDate = new Date(current);
					MyToast.showToast(MyToast.CLICK_AGAIN_TO_EXIT);
				}
				return false;
			}
		}
		return super.onKeyDown(keyCode, event);
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
		case 1: // 选择图片并显示
			if (resultCode == Activity.RESULT_OK) {

				Uri uri = data.getData();
				if (MainLayout.getInstance().mCurrentLayout instanceof ReleaseLayout) {// 若当前为发布界面
					// 设置图片Uri
					((ReleaseLayout) (MainLayout.getInstance().mCurrentLayout))
							.setSelectBitmapUri(uri);
				}
				ShowPictureView showPictureView = new ShowPictureView(this, uri);
				MainLayout.getInstance().addView(showPictureView);

			}
			break;
		case 2:// 选择系统图片并调用剪裁
			if (resultCode == Activity.RESULT_OK) {
				Uri uri = data.getData();
				ImageUtils.cropImage(uri);
			}
		case 3:// 剪裁图片 的回调
			if (data != null && data.getExtras() != null) {
				Bundle extras = data.getExtras();
				Bitmap bitmap = extras.getParcelable("data");
				if (bitmap != null) {
					if (MainLayout.getInstance().mCurrentLayout instanceof LoginLayout) {
						((LoginLayout) (MainLayout.getInstance().mCurrentLayout))
							.setRegistHeadImage(bitmap);
					}
					else if (MainLayout.getInstance().mCurrentLayout instanceof PersonalLayout) {
						Uri uri=Uri.parse(android.provider.MediaStore.Images.Media.insertImage(getContentResolver(),  bitmap, null, null));
						((PersonalLayout)(MainLayout.getInstance().mCurrentLayout)).getImagePopupWindow().setAfterPhotograp(uri);
				}
				}
			}
			break;
		case 4://拍照，并调用裁剪
			if (resultCode==RESULT_OK) {
				Uri uri=Uri.parse(android.provider.MediaStore.Images.Media.insertImage(getContentResolver(),  (Bitmap) data.getExtras().get("data"), null, null));
				ImageUtils.cropImage(uri);
				
			}
			break;
		default:
			break;
		}

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		//以下调用testin的sdk
		try {
			TestinAgent
					.setUserInfo(BmobUser.getCurrentUser(this).getObjectId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		TestinAgent.onResume(this);
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		try {
			TestinAgent
					.setUserInfo(BmobUser.getCurrentUser(this).getObjectId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		TestinAgent.onStop(this);
	}

}
