package com.schoolinfo.layout;

import com.schoolinfo.MainActivity;
import com.schoolinfo.R;
import com.schoolinfo.adapter.GalleryAdapter;
import com.schoolinfo.control.UIControl;
import com.schoolinfo.view.CustomGallery;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.RelativeLayout;

/**
 * 主画廊菜单，再次切换功能
 * 
 * @author huangwubin 2014-9-21
 */
public class MainGalleryLayout extends RelativeLayout {

	private int[] mImageResIds = new int[] { R.drawable.gallery_lost,
			R.drawable.gallery_old, R.drawable.gallery_release };
	private RunLayout[] mImageResTag = new RunLayout[] { RunLayout.LOSTLAYOUT,
			RunLayout.OLDLAYOUT, RunLayout.RELEASELAYOUT };
	private String[] mTextString=new String[]{"寻物启事/失物招领","二手市场","物品发布"};

	private CustomGallery mCustomGallery;// 画廊控件
	private GalleryAdapter mAdapter;
	private TextView mTextView;//描述
	public MainGalleryLayout(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		this.setVisibility(View.GONE);
		setView();
	}

	private void setView() {
		// TODO Auto-generated method stub
		this.setBackgroundColor(Color.BLACK);
		this.getBackground().setAlpha((int) (0.7 * 225));
		this.setGravity(Gravity.CENTER);

		mAdapter = new GalleryAdapter();
		mAdapter.setImageResId(mImageResIds);
		mAdapter.setmImageResTag(mImageResTag);

		mCustomGallery = new CustomGallery(MainActivity.getInstance(), null);
		mCustomGallery.setAdapter(mAdapter);
		mCustomGallery.setSpacing(UIControl.p.x / 40);
		mCustomGallery.setOnItemClickListener(onItemClickListener);
		mCustomGallery.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				//描述
				mTextView.setText(mTextString[position]);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
		});
		this.addView(mCustomGallery, new LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		
		mTextView=new TextView(MainActivity.getInstance());
		LayoutParams params=new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		params.topMargin=UIControl.p.y/4*1;
		params.addRule(RelativeLayout.CENTER_HORIZONTAL);
		this.addView(mTextView, params);
	}

	private OnItemClickListener onItemClickListener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// TODO Auto-generated method stub
			
			switch ((RunLayout) view.getTag()) {
			case LOSTLAYOUT:// 失物招领
				LostLayout.setGoodsQuery(LostLayout.QUERYALL);
				MainLayout.getInstance().changeLayout(
						new LostLayout(MainActivity.getInstance()));

				break;
			case OLDLAYOUT:// 二手市场
				OldLayout.setGoodsQuery(OldLayout.QUERYALL);
				MainLayout.getInstance().changeLayout(
						new OldLayout(MainActivity.getInstance()));
				break;
			case RELEASELAYOUT:// 发布
				MainLayout.getInstance().changeLayout(
						new ReleaseLayout(MainActivity.getInstance()));
				break;
			default:
				break;
			}
			MainLayout.getInstance().removeView(
					MainLayout.getInstance().mMainGalleryLayout);
		}
	};
}
