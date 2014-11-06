package com.schoolinfo.view;

import com.schoolinfo.MainActivity;
import com.schoolinfo.R;
import com.schoolinfo.control.UIControl;
import com.schoolinfo.data.LostData;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.text.method.ScrollingMovementMethod;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Scroller;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;
import android.view.View.OnClickListener;

public class LostCard extends LinearLayout implements OnClickListener {
	public boolean isOpen = false;// 卡片是否打开
	private boolean isLeft = true;// 图片的位置
	private boolean isFrist = true;// 是否第一次打开

	public LostData mData = new LostData();

	private Context mContext;
	public ImageView mImageView;
	private RelativeLayout mTextLayout;// 简易描述文本布局
	private TextView mNameView;
	private TextView mContactView;// 联系人
	private LinearLayout mResumeLayout;// 简易描述布局
	private LinearLayout mDecLayout;// 详细描述布局
	private LinearLayout.LayoutParams cardParams;// 卡片布局参数
	private TextView mDecNameView;// 详细描述文本
	private TextView mDecView;// 详细描述内容
	private int mDecTextHeight;// 详细描述内容文本大小
	private TextView mContactInfoNameView;// 联系方式文本
	private TextView mContactInfoView;// 联系方式

	public LostCard(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		this.mContext = context;

		mImageView = new ImageView(mContext);
		mTextLayout = new RelativeLayout(context);
		mNameView = new TextView(context);
		mContactView = new TextView(context);
		mResumeLayout = new LinearLayout(context);
		mDecLayout = new TableLayout(context);
		mDecNameView = new TextView(context);
		mDecView = new TextView(context);
		mContactInfoNameView = new TextView(context);
		mContactInfoView = new TextView(context);
		setView();
	}

	private void setView() {
		// TODO Auto-generated method stub
		cardParams = new LayoutParams((int) (UIControl.p.x * 0.995),
				UIControl.p.y / 6);
		cardParams.topMargin = UIControl.p.y / 96;
		this.setOrientation(LinearLayout.VERTICAL);
		this.setBackgroundResource(R.drawable.card_bg3);
//		this.getBackground().setAlpha((int) (0.57 * 255));
		this.setGravity(Gravity.CENTER);
//		this.setLayoutParams(cardParams);
		this.setLayoutParams(new LayoutParams((int) (UIControl.p.x * 0.995), LayoutParams.WRAP_CONTENT));
		this.setOnClickListener(this);
//		this.getLayoutParams().height=LayoutParams.WRAP_CONTENT;
		// 简易布局
		mResumeLayout.setLayoutParams(new LayoutParams(
				(int) (UIControl.p.x * 0.995), UIControl.p.y / 6));
		mResumeLayout.setGravity(Gravity.CENTER);

		LinearLayout.LayoutParams imageParams = new LayoutParams(
				(int) (UIControl.p.y / 6 * 0.8),
				(int) (UIControl.p.y / 6 * 0.8));
		// imageParams.topMargin=(int) (UIControl.p.y/6*0.1);
		mResumeLayout.addView(mImageView, imageParams);

		LinearLayout.LayoutParams textParams = new LayoutParams(
				(int) ((UIControl.p.x * 0.95 - UIControl.p.y / 6)),
				(int) (UIControl.p.y / 6));
		mTextLayout.setLayoutParams(textParams);
		RelativeLayout.LayoutParams nameParams = new RelativeLayout.LayoutParams(
				textParams.width, (int) (textParams.height / 2 * 0.95));
		RelativeLayout.LayoutParams resmueParams = new RelativeLayout.LayoutParams(
				textParams.width, (int) (textParams.height / 2 * 0.95));
		resmueParams.topMargin = nameParams.height;
		mTextLayout.addView(mNameView, nameParams);
		mTextLayout.addView(mContactView, resmueParams);
		mResumeLayout.addView(mTextLayout);
		this.addView(mResumeLayout);

		// 详细布局
		mDecLayout.setLayoutParams(new LayoutParams(
				(int) (UIControl.p.x * 0.995), LayoutParams.WRAP_CONTENT));
		mDecLayout.setGravity(Gravity.CENTER);
		mDecLayout.setOrientation(LinearLayout.VERTICAL);
		// 详细描述
		LinearLayout decRow = new LinearLayout(MainActivity.getInstance());
		decRow.setLayoutParams(new LayoutParams(cardParams.width,
				LayoutParams.WRAP_CONTENT));
		mDecNameView.setGravity(Gravity.RIGHT);// ���ֿ���
		mDecNameView.setTextScaleX(0.6f);
		mDecNameView.setTextColor(Color.BLACK);
		mDecNameView.setText("详细描述：");
		mDecNameView.setLayoutParams(new LayoutParams(cardParams.width / 5,
				cardParams.height / 3));
		decRow.addView(mDecNameView);
		// 详细描述内容
		mDecView.setGravity(Gravity.LEFT);
		mDecView.setTextScaleX(0.6f);
		mDecView.setTextColor(Color.BLACK);
		mDecView.setLayoutParams(new LayoutParams(
				(int) (cardParams.width / 5 * 4 * 0.95),
				LayoutParams.WRAP_CONTENT));
		decRow.addView(mDecView);
		mDecLayout.addView(decRow);
		// 联系方式
		LinearLayout contactInfoRow = new LinearLayout(
				MainActivity.getInstance());
		contactInfoRow.setLayoutParams(new LayoutParams(cardParams.width,
				cardParams.height / 3));
		mContactInfoNameView.setGravity(Gravity.RIGHT);
		mContactInfoNameView.setTextScaleX(0.6f);
		mContactInfoNameView.setTextColor(Color.BLACK);
		mContactInfoNameView.setText("联系方式：");
		mContactInfoNameView.setLayoutParams(new LayoutParams(
				cardParams.width / 5, cardParams.height / 3));
		contactInfoRow.addView(mContactInfoNameView);
		mContactInfoView.setGravity(Gravity.LEFT);
		mContactInfoView.setTextScaleX(0.6f);
		mContactInfoView.setTextColor(Color.BLACK);
		mContactInfoView.setLayoutParams(new LayoutParams(
				cardParams.width / 5 * 4, cardParams.height / 3));
		contactInfoRow.addView(mContactInfoView);
		mDecLayout.addView(contactInfoRow);

		mNameView.setTextColor(Color.BLACK);
		mNameView.setGravity(Gravity.CENTER);
		mContactView.setTextColor(Color.BLACK);
		mContactView.setGravity(Gravity.LEFT);
	}

	/**
	 * 设置数据
	 * 
	 * @param data
	 *            lostData
	 */
	public void setData(LostData data) {
		this.mData = data;
		mNameView.setText((data.isLost ? "【寻物启事】" : "【失物招领】" )+ data.mName.toString());
		mContactView.setText("联系人：" + data.mContact);
		if (data.mPicture != null) {
			data.mPicture.loadImageThumbnail(mContext, mImageView,
					(int) (UIControl.p.y / 6 * 0.8),
					(int) (UIControl.p.y / 6 * 0.8));
			// mImageView
			// .setBackgroundDrawable(new BitmapDrawable(data.mThumbnail));

		} else {// 使用默认图片
			mImageView.setBackgroundDrawable(new BitmapDrawable(BitmapFactory
					.decodeResource(MainActivity.getInstance().getResources(),
							R.drawable.def_picture)));
		}
		mDecView.setText(data.mDecString);
		mDecTextHeight = mDecView.getLineCount() * mDecView.getLineHeight();
		mContactInfoView.setText(data.mContactInfo + "(" + data.mContactType
				+ ")");
	}

	public void isLeft(boolean isLeft) {
		if (!isLeft) {// 右边
			mResumeLayout.removeAllViews();
			mResumeLayout.addView(mTextLayout);
			mResumeLayout.addView(mImageView);
		}
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		// 根据文本框高度调节整个卡片的高度
		int decHeight = (mDecView.getLineCount() + (isFrist ? 2 : -3))
				* mDecView.getLineHeight();
		
		isFrist = false;
		if (!isOpen) {
			isOpen = !isOpen;
			cardParams.height = cardParams.height * 2 + decHeight;
			this.addView(mDecLayout);
//			this.setLayoutParams(cardParams);
//			this.setLayoutParams(new LayoutParams((int) (UIControl.p.x * 0.995), LayoutParams.WRAP_CONTENT));
//			this.setBackgroundResource(R.drawable.card_bg2);
//			this.getBackground().setAlpha((int) (0.57 * 255));
		} else {
			isOpen = !isOpen;
			cardParams.height = mResumeLayout.getLayoutParams().height;
			this.removeView(mDecLayout);
//			this.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
//			this.setLayoutParams(cardParams);
//			this.setBackgroundResource(R.drawable.card_bg);
//			this.getBackground().setAlpha((int) (0.57 * 255));
		}
	}
}
