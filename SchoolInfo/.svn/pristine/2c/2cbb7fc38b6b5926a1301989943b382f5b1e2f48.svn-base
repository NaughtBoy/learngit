package com.schoolinfo.layout;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.SaveListener;

import com.schoolinfo.MainActivity;
import com.schoolinfo.R;
import com.schoolinfo.control.GlobalControl;
import com.schoolinfo.control.UIControl;
import com.schoolinfo.data.User;
import com.schoolinfo.view.MyProgressDialog;
import com.schoolinfo.view.MyToast;
import com.schoolinfo.view.RegistView;
import com.schoolinfo.view.ShowPictureView;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

/**
 * 登陆界面
 * 
 * @author huangwubin 2014-9-30
 */
public class LoginLayout extends BaseLayout {

	private EditText mNameEdit;// 账号
	private EditText mPasswordEdit;// 密码
	private RegistView mRegistView;//注册界面
	RelativeLayout mRrgistlayout;//注册界面的布局
	public LoginLayout(Context context) {
		// TODO Auto-generated constructor stub
		super.mLayout = new RelativeLayout(context);
		super.mRunLayout = RunLayout.LOGINLAYOUT;

		mNameEdit = new EditText(context);
		mPasswordEdit = new EditText(context);

		setView();
	}

	private void setView() {
		// TODO Auto-generated method stub

//		LinearLayout bodyLayout = new LinearLayout(MainActivity.getInstance());
//		bodyLayout.setGravity(Gravity.CENTER);
//		bodyLayout.setOrientation(LinearLayout.VERTICAL);
//
//		TopLayout topLayout=new TopLayout(MainActivity.getInstance());
//		topLayout.setLayoutText(mRunLayout);
//		super.mLayout.addView(topLayout,
//				UIControl.getLayoutParams(UIControl.topData, 1));
//		super.mLayout.addView(bodyLayout,
//				UIControl.getLayoutParams(UIControl.bodyData, 1));
//
//		LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT,
//				LayoutParams.WRAP_CONTENT);
//		layoutParams.topMargin = UIControl.p.y / 50;
//
//		// 账号
//		LinearLayout nameLayout = new LinearLayout(MainActivity.getInstance());
//		// 文本
//		TextView nameTextView = new TextView(MainActivity.getInstance());
//		nameTextView.setTextScaleX(1.5f);
//		nameTextView.setTextColor(Color.BLACK);
//		nameTextView.setText("账号：");
//		nameLayout.addView(nameTextView);
//		// 编辑框
//		mNameEdit.setTextScaleX(1.2f);
//		mNameEdit.setEms(8);
//		mNameEdit.setSingleLine();
//		nameLayout.addView(mNameEdit);
//		bodyLayout.addView(nameLayout, layoutParams);
//
//		// 密码
//		LinearLayout passwordLayout = new LinearLayout(
//				MainActivity.getInstance());
//		// 文本
//		TextView passwordTextView = new TextView(MainActivity.getInstance());
//		passwordTextView.setTextScaleX(1.5f);
//		passwordTextView.setTextColor(Color.BLACK);
//		passwordTextView.setText("密码：");
//		passwordLayout.addView(passwordTextView);
//		// 编辑框
//		mPasswordEdit.setTextScaleX(1.2f);
//		mPasswordEdit.setEms(8);
//		mPasswordEdit.setSingleLine();
//		// 密码隐藏
//		mPasswordEdit.setInputType(InputType.TYPE_CLASS_TEXT
//				| InputType.TYPE_TEXT_VARIATION_PASSWORD);
//		passwordLayout.addView(mPasswordEdit);
//		// 密码可见选项
//		CheckBox passwordBox = new CheckBox(MainActivity.getInstance());
//		passwordBox.setText("密码可见");
//		passwordBox.setTextScaleX(1.0f);
//		passwordBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
//
//			@Override
//			public void onCheckedChanged(CompoundButton buttonView,
//					boolean isChecked) {
//				// TODO Auto-generated method stub
//				if (isChecked) {
//					mPasswordEdit
//							.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
//				} else {
//					mPasswordEdit.setInputType(InputType.TYPE_CLASS_TEXT
//							| InputType.TYPE_TEXT_VARIATION_PASSWORD);
//				}
//			}
//		});
//
//		bodyLayout.addView(passwordLayout, layoutParams);
//		bodyLayout.addView(passwordBox, layoutParams);
//		// 按钮
//		LinearLayout buttonLayout = new LinearLayout(MainActivity.getInstance());
//		// 注册
//		Button registButton = new Button(MainActivity.getInstance());
//		registButton.setText("  注  册  ");
//		registButton.setTextScaleX(1.5f);
//		registButton.setTextColor(Color.BLACK);
//		registButton.setOnClickListener(registClickListener);
//		buttonLayout.addView(registButton);
//		// 登陆
//		Button loginButton = new Button(MainActivity.getInstance());
//		loginButton.setText("  登  陆  ");
//		loginButton.setTextScaleX(1.5f);
//		loginButton.setTextColor(Color.BLACK);
//		loginButton.setOnClickListener(loginClickListener);
//		bodyLayout.addView(loginButton, layoutParams);
//		// 跳过登陆
//		Button ignoreButton = new Button(MainActivity.getInstance());
//		ignoreButton.setText("跳过登陆");
//		ignoreButton.setTextScaleX(1.5f);
//		ignoreButton.setTextColor(Color.BLACK);
//		ignoreButton.setOnClickListener(ingoreClickListener);
//		buttonLayout.addView(ignoreButton);
//		bodyLayout.addView(buttonLayout, layoutParams);
		
		//login。xml
		LinearLayout loginLayout=(LinearLayout) MainActivity.getInstance().getLayoutInflater().inflate(R.layout.login, null);
		mLayout.addView(loginLayout);
		//logo
		ImageView logoView=(ImageView) loginLayout.findViewById(R.id.login_img_logo);
//		UIControl.getLayoutParams(logoView, UIControl.loginLogo, 2);
		mNameEdit=(EditText)loginLayout.findViewById(R.id.login_edit_name);
//		UIControl.getLayoutParams(mNameEdit, UIControl.loginName, 2);
		mPasswordEdit=(EditText)loginLayout.findViewById(R.id.login_edit_password);
//		UIControl.getLayoutParams(mPasswordEdit, UIControl.loginPassword, 2);
		Button loginBtn=(Button)loginLayout.findViewById(R.id.login_btn_login);
		loginBtn.setOnClickListener(loginClickListener);
//		UIControl.getLayoutParams(loginBtn, UIControl.loginBtnLogin, 2);
		Button registBtn=(Button)loginLayout.findViewById(R.id.login_btn_rigst);
		registBtn.setOnClickListener(registClickListener);
//		UIControl.getLayoutParams(registBtn, UIControl.loginBtnRegist, 2);
		Button skipBtn=(Button)loginLayout.findViewById(R.id.login_btn_skip);
		skipBtn.setOnClickListener(ingoreClickListener);
//		UIControl.getLayoutParams(skipBtn, UIControl.loginBtnSkip, 2);
		
	}

	//登陆按钮监听
	private OnClickListener loginClickListener=new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			//判断当前是否有网络连接
			if (!GlobalControl.isNetworkAvailable()) {//没有网络连接
				MyToast.showToast(MyToast.NOT_NETWORK);
				return ;
			}
			final User data=new User();
			data.setUsername(mNameEdit.getText().toString());
			data.setPassword(mPasswordEdit.getText().toString());
			if (mNameEdit.getText().length()==0||mPasswordEdit.getText().length()==0) {
				MyToast.showToast(MyToast.LOGIN_LOSE_NULL);
				return;
			}
			//显示进度框
			MyProgressDialog.getInstance().setisShow(true,1);
			//登陆
			data.login(MainActivity.getInstance(), new SaveListener() {
				
				@Override
				public void onSuccess() {
					// TODO Auto-generated method stub
					MainLayout.getInstance().mCurrentUser=BmobUser.getCurrentUser(MainActivity.getInstance(), User.class);
					MainLayout.getInstance().showGalery();
					MyProgressDialog.getInstance().setisShow(false);
				}
				
				@Override
				public void onFailure(int arg0, String arg1) {
					// TODO Auto-generated method stub
					if (arg0==101) {
						MyToast.showToast(MyToast.LOGIN_LOSE);
					}
					MyProgressDialog.getInstance().setisShow(false);
				}
			});
		}
	};

	//跳过登陆按钮监听
	private OnClickListener ingoreClickListener=new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			MainLayout.getInstance().showGalery();
		}
	};
	
	//注册按钮监听
	private OnClickListener registClickListener=new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			try {
				MainLayout.getInstance().removeView(mRrgistlayout);
				mRrgistlayout.removeView(mRegistView);
			} catch (Exception e) {
				// TODO: handle exception
			}
			mRegistView=new RegistView(MainActivity.getInstance());
			mRrgistlayout=new RelativeLayout(MainActivity.getInstance());
			mRrgistlayout.setGravity(Gravity.CENTER);
			mRrgistlayout.addView(mRegistView);
			LayoutParams params=new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
			MainLayout.getInstance().addView(mRrgistlayout, params);
			//动画
			ScaleAnimation animation=new ScaleAnimation(0, 1.0f, 0, 1.0f);
			animation.setDuration(500);
			mRegistView.startAnimation(animation);
		}
	};
	/**
	 * 设置注册界面的头像
	 * @param bitmap
	 */
	public void setRegistHeadImage(Bitmap bitmap){
		mRegistView.setmSelectHeadBitmap(bitmap);
	}
}
