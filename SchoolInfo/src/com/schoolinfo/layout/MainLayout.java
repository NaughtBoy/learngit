package com.schoolinfo.layout;

import com.dartou.lib.utils.AssertsUtil;
import com.schoolinfo.R;
import com.schoolinfo.data.User;
import com.schoolinfo.view.CustomGallery;
import com.schoolinfo.view.MyDragButton;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

/**
 * 单例类，主布局，更换功能使用此类更换
 * 
 * @author huangwubin 2014-9-4
 */
public class MainLayout extends RelativeLayout {

	public static MainLayout mMainLayout;
	public User mCurrentUser;
	public BaseLayout mCurrentLayout;// 当前界面
	public RunLayout mCurrentLayoutId;
	public MyDragButton mDragButton;// 悬浮球

	public MainGalleryLayout mMainGalleryLayout;// 画廊

	public MainLayout(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		mMainLayout = this;
		this.setBackgroundResource(R.drawable.background);
		mMainGalleryLayout = new MainGalleryLayout(context);
		mDragButton = new MyDragButton(context);
	}

	public static MainLayout getInstance() {
		return mMainLayout;
	}

	/**
	 * 获取实例
	 * 
	 * @param context
	 */
	public static MainLayout getInstance(Context context) {
		// TODO Auto-generated method stub
		mMainLayout = new MainLayout(context);
		return getInstance();
	}

	/**
	 * 更换布局，在这儿更换功能
	 * 
	 * @param baseLayout
	 */
	public void changeLayout(BaseLayout baseLayout) {
		if (mCurrentLayout != null) {
			if (mCurrentLayout.mLayout.getTag()==baseLayout.mLayout.getTag()) {
				return;
			}
			mCurrentLayout.mIsRun = false;
			this.removeView(mCurrentLayout.mLayout);
		}
		mCurrentLayout = baseLayout;
		mCurrentLayout.mIsRun = true;
		LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT);
		this.addView(mCurrentLayout.mLayout, lp);

		// 添加悬浮球
		this.removeView(mDragButton);
		this.addView(mDragButton, mDragButton.getLayoutParams());
	}

	/**
	 * 显示画廊
	 */
	public void showGalery(){
		try {
			this.removeView(mMainGalleryLayout);//先清除
		} catch (Exception e) {
			// TODO: handle exception
		}
		this.addView(mMainGalleryLayout,new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
		this.mMainGalleryLayout.setVisibility(View.VISIBLE);
	}
}
