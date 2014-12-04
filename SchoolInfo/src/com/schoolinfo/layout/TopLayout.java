package com.schoolinfo.layout;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;

import com.dartou.lib.utils.DensityUtil;
import com.schoolinfo.MainActivity;
import com.schoolinfo.R;
import com.schoolinfo.control.UIControl;
import com.schoolinfo.data.User;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.media.FaceDetector;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 每个布局上面的顶部菜单，有登陆、当前布局等信息
 * 
 * @author huangwubin 2014-9-29
 */
public class TopLayout extends RelativeLayout {

	private ImageView mLoginView;// 头像
	private TextView mCurrentTextView;// 当前界面的文本

	private User mUser;// 当前登陆的用户

	public TopLayout(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		mUser = MainLayout.getInstance().mCurrentUser;
		// mUser=(User) BmobUser.getCurrentUser(context);
		mLoginView = new ImageView(context);
		mCurrentTextView = new TextView(context);
		setView();
	}

	private void setView() {
		// TODO Auto-generated method stub
		if (mUser != null) {// 当前有用户登陆
			setHeadPicture(mUser.getHeadPicture());
		} else {
			mLoginView.setImageResource(R.drawable.def_head);
		}
		mLoginView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (mUser != null) {// 有用户登陆
					PersonalLayout personalLayout = new PersonalLayout(
							MainActivity.getInstance());
					MainLayout.getInstance().changeLayout(personalLayout);
				} else {
					Builder builder = new Builder(MainActivity.getInstance());
					builder.setTitle("提示");
					builder.setMessage("当前未登录，是否转到登录界面？");
					builder.setPositiveButton(
							"确定",
							new android.content.DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									// TODO Auto-generated method stub
									MainLayout.getInstance().changeLayout(
											new LoginLayout(MainActivity
													.getInstance()));
								}
							});
					builder.setNegativeButton("取消",
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									// TODO Auto-generated method stub
								}
							});
					builder.create().show();
				}
			}
		});
		this.addView(mLoginView,
				UIControl.getLayoutParams(UIControl.topImageData, 1));

		mCurrentTextView.setTextSize(DensityUtil.px2sp(
				MainActivity.getInstance(), UIControl.topTextData.h * 0.9f));
		mCurrentTextView.setTextColor(Color.BLACK);
		mCurrentTextView.setGravity(Gravity.CENTER);
		this.addView(mCurrentTextView,
				UIControl.getLayoutParams(UIControl.topTextData, 1));
	}

	/**
	 * 设置头像
	 * 
	 * @param bitmap
	 */
	public void setHeadPicture(BmobFile file) {
		if (file != null) {
			file.loadImageThumbnail(MainActivity.getInstance(), mLoginView,
					UIControl.topImageData.w, UIControl.topImageData.h);
		} else {
			mLoginView.setImageResource(R.drawable.def_head);
		}
	}

	public void setLayoutText(RunLayout runLayout) {
		String string = null;
		switch (runLayout) {
		case LOSTLAYOUT:
			string = "失物招领";
			break;
		case OLDLAYOUT:
			string = "二手市场";
			break;
		case RELEASELAYOUT:
			string = "发布";
			break;
		case LOGINLAYOUT:
			string = "登陆";
			break;
		case MAPLAYOUT:
			string = "校内地图";
		default:
			break;
		}
		mCurrentTextView.setText(string);
	}

	public User getUser() {
		return mUser;
	}

}
