package com.schoolinfo.view;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import cn.bmob.v3.datatype.BmobFile;

import com.schoolinfo.MainActivity;
import com.schoolinfo.R;
import com.schoolinfo.control.UIControl;
import com.schoolinfo.layout.MainLayout;
import com.schoolinfo.utils.ImageUtils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.RelativeLayout.LayoutParams;

/**
 * 从底部滑出的菜单，选择图片
 * 
 * @author huangwubin 2014-10-15
 */
public class ImagePopupWindow extends PopupWindow {

	private Button mLookBtn;
	private Button mPhotographBtn;
	private Button mSelectBtn;
	private Button mCanelBtn;
	private View mPopupView;// 弹出的View

	private Uri mImageUri;//当前选择的图片的Uri
	private BmobFile mImageFile;//当前用户的头像文件
	/**
	 * 
	 * @param context
	 * @param uri 当前的图片的Uri，可为null
	 * @param file 当前用户的头像 ，可为null
	 */
	public ImagePopupWindow(Context context,Uri uri,BmobFile file) {
		super(context);
		mImageUri=uri;
		mImageFile=file;
		
		setView();
		setListener();
	}

	private void setView() {
		// TODO Auto-generated method stub
		mPopupView = MainActivity.getInstance().getLayoutInflater()
				.inflate(R.layout.image_popup, null);
		mLookBtn = (Button) mPopupView.findViewById(R.id.popup_look_image);
		mPhotographBtn = (Button) mPopupView
				.findViewById(R.id.popup_photograph);
		mSelectBtn = (Button) mPopupView.findViewById(R.id.popup_select_image);
		mCanelBtn = (Button) mPopupView.findViewById(R.id.popup_canel);

		this.setContentView(mPopupView);
		this.setBackgroundDrawable(new ColorDrawable(0x00000000));//半透明
		this.setFocusable(true);
		//必须设置高宽，否则无法显示
		this.setHeight(LayoutParams.WRAP_CONTENT);
		this.setWidth(UIControl.p.x/3*2);
		this.setAnimationStyle(R.style.popupwindow_anim);
	}

	private void setListener() {
		//点击范围外撤销菜单
		mPopupView.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub

				int height = mPopupView.findViewById(R.id.popup_layout)
						.getTop();
				int y = (int) event.getY();
				if (event.getAction() == MotionEvent.ACTION_UP) {
					if (y < height) {
						dismiss();
					}
				}
				return true;
			}
		});
		
		//取消
		mCanelBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dismiss();
			}
		});
		
		//查看图片
		mLookBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				if (mImageUri!=null) {//优先选择本地图片
					ShowPictureView showPictureView = new ShowPictureView(MainActivity.getInstance(), mImageUri);
		MainLayout
				.getInstance()
				.addView(
						showPictureView);
				}
				else if (mImageFile!=null){//显示当前用户的头像
					ShowPictureView showPictureView = new ShowPictureView(MainActivity.getInstance(), mImageFile,null);
					MainLayout
							.getInstance()
							.addView(
									showPictureView);
				}
				else {//提示没有当前用户没有上传图片
					MyToast.showToast(MyToast.NOT_UPLOAD_IMAGE);
				}
				dismiss();
			}
		});
		
		//拍照
		mPhotographBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//启动拍照
				MainActivity.getInstance().startActivityForResult(new Intent("android.media.action.IMAGE_CAPTURE"), 4);
				dismiss();
			}
		});
		
		//从系统中选择图片
		mSelectBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ImageUtils.selectAndCropImage();//选择并裁剪
				dismiss();
			}
		});
	}
	
	/**
	 * 拍照后设置Uri
	 */
	public void setAfterPhotograp(Uri uri){

		mImageUri=uri;
////		ShowPictureView showPictureView = new ShowPictureView(MainActivity.getInstance(), bitmap);
////		MainLayout
////				.getInstance()
////				.addView(
////						showPictureView);
	}
}
