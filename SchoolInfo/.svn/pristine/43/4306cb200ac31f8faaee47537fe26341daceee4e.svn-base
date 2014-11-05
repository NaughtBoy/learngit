package com.schoolinfo.adapter;

import com.schoolinfo.MainActivity;
import com.schoolinfo.control.UIControl;
import com.schoolinfo.layout.RunLayout;
import com.schoolinfo.utils.ImageUtils;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

/**
 * 自定义画廊控件
 * @author huangwubin 2014-9-21
 */
public class GalleryAdapter extends BaseAdapter {

	private int[] mImageResId;//图片id
	private RunLayout[] mImageResTag;
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mImageResId.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ImageView iv = null;
		if(convertView == null) {
			iv = new ImageView(MainActivity.getInstance());
		} else {
			iv = (ImageView) convertView;
		}
		
		Bitmap bitmap = ImageUtils.getImageBitmap(
				MainActivity.getInstance().getResources(), mImageResId[position]);
		BitmapDrawable bd = new BitmapDrawable(bitmap);
		bd.setAntiAlias(true);		// 消除锯齿
		
		iv.setTag(mImageResTag[position]);//设置标签
		iv.setImageDrawable(bd);
		LayoutParams params=new Gallery.LayoutParams(UIControl.p.y/5,UIControl.p.y/5*271/200);
		iv.setLayoutParams(params);
		return iv;
	}

	public int[] getImageResId() {
		return mImageResId;
	}

	public void setImageResId(int[] imageResId) {
		this.mImageResId = imageResId;
	}

	public RunLayout[] getmImageResTag() {
		return mImageResTag;
	}

	public void setmImageResTag(RunLayout[] mImageResTag2) {
		this.mImageResTag = mImageResTag2;
	}

}
