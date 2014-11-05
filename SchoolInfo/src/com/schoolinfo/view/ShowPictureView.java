package com.schoolinfo.view;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobQuery.CachePolicy;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.listener.GetListener;

import com.schoolinfo.MainActivity;
import com.schoolinfo.control.UIControl;
import com.schoolinfo.layout.MainLayout;
import com.schoolinfo.utils.ImageUtils;
import com.schoolinfo.utils.MulThreadDownload;

import android.app.ProgressDialog;
import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.view.View.OnClickListener;

/**
 * 用于加载显示图片,使用缓存查询图片
 * @author huangwubin 2014-9-20
 */
public class ShowPictureView extends LinearLayout implements OnClickListener{

	public BmobFile mBmobFile;
	public Bitmap mThumbnail;//缩略图
	private Bitmap mBitmap;
	private ImageView mImageView;
	/**
	 * 从网络上获取图片
	 * @param context
	 * @param file
	 * @param thumbnailBitmap 缩量图，没有可为null
	 */
	public ShowPictureView(Context context,BmobFile file,Bitmap thumbnailBitmap) {
		super(context);
		// TODO Auto-generated constructor stub
		this.mBmobFile=file;
		this.mThumbnail=thumbnailBitmap;
		setView();
		if (file!=null) {
			getBitmap(mBmobFile);
		}
		else {
			MyToast.showToast(MyToast.NOT_UPLOAD_IMAGE);
		}
		
		this.setOnClickListener(this);
	}
	/**
	 * 从本地获取图片
	 * @param context
	 * @param uri
	 */
	public ShowPictureView(Context context,Uri uri){
		super(context);
		setView();
		getBitmap(uri);
		this.setOnClickListener(this);
	}
	
	/**
	 * 传进图片
	 * @param context
	 * @param bitmap
	 */
	public ShowPictureView(Context context, Bitmap bitmap) {
		// TODO Auto-generated constructor stub
		super(context);
		setView();
		mBitmap=bitmap;
		mImageView.setImageBitmap(bitmap);
	}
	//g根据Uri获取图片并设置mImageView
	private void getBitmap(Uri uri) {
		// TODO Auto-generated method stub
		ContentResolver contentResolver=MainActivity.getInstance().getContentResolver();
			 BitmapFactory.Options opts = new BitmapFactory.Options();

			 // 设置inJustDecodeBounds为true
			mBitmap=ImageUtils.getMaxBitmap(ImageUtils.getImagePath(uri), 1);
			mImageView.setBackgroundDrawable(new BitmapDrawable(ImageUtils.getMaxShowBitmap(mBitmap)));
//			mImageView.setImageBitmap(mBitmap);
//			mImageView.setLayoutParams(getImageViewParams(mBitmap));
	}

	private void setView() {
		// TODO Auto-generated method stub
		this.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		this.setGravity(Gravity.CENTER);
		this.setBackgroundColor(Color.BLACK);
		this.getBackground().setAlpha((int) (225*0.75));
		
		mImageView=new ImageView(MainActivity.getInstance());
		if (mThumbnail!=null) {//缩略图
			mImageView.setLayoutParams(getImageViewParams(mThumbnail));
			mImageView.setBackgroundDrawable(new BitmapDrawable(mThumbnail));
		}
		this.addView(mImageView);
		
	}
	
	//根据图片大小获得布局参数
	private LayoutParams getImageViewParams(
			Bitmap bitmap) {
		// TODO Auto-generated method stub
		int w,h;
		int bitmapW=bitmap.getWidth();
		int bitmapH=bitmap.getHeight();
		if (bitmapH>bitmapH) {
			w=UIControl.p.x;
			h=w*bitmapH/bitmapW;
		}
		else {
			h=UIControl.p.y;
			w=h*bitmapW/bitmapH;
		}
		return new LayoutParams(w,h);
	}

	//根据fileUrl从Bmob获取图片
	private void getBitmap(BmobFile bmobFile) {
		// TODO Auto-generated method stub
		if ( Environment.getExternalStorageState()   
                           .equals(Environment.MEDIA_MOUNTED)) {//SD卡存在
			Handler handler=new Handler(){

				@Override
				public void handleMessage(Message msg) {
					// TODO Auto-generated method stub
					switch (msg.what) {
					case 1://更新ui
						String path=(String) msg.obj;
						Bitmap bitmap=ImageUtils.getMaxBitmap(path, 1);
						mBitmap=ImageUtils.getMaxShowBitmap(bitmap);
						mImageView.setImageBitmap(mBitmap);
						//清除背景
						mImageView.setBackgroundResource(0);
						break;
					case 2://下载失败
						MyToast.showToast(MyToast.DOWNLOAD_IMAGE_LOSE);
						break;
					default:
						break;
					}
					super.handleMessage(msg);
				}
				
			};
			MulThreadDownload mDownload;
			try {
				mDownload = new MulThreadDownload(handler, bmobFile.getFileUrl(), bmobFile.getFilename());
				mDownload.start();//开始下载
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		else {
			MyToast.showToast(MyToast.SDCARD_NOT_EXIST);
		}
	}

	//点击消失
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		MainLayout.getInstance().removeView(this);
	}

	
}
