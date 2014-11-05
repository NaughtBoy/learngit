package com.schoolinfo.view;

import java.io.File;
import java.io.IOException;

import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UploadFileListener;

import com.schoolinfo.MainActivity;
import com.schoolinfo.R;
import com.schoolinfo.control.UIControl;
import com.schoolinfo.data.User;
import com.schoolinfo.layout.LoginLayout;
import com.schoolinfo.layout.MainLayout;
import com.schoolinfo.utils.ImageUtils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.InputFilter;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.Toast;

public class RegistView extends LinearLayout {

	private Bitmap mSelectHeadBitmap;// 选择的图片
	private EditText mNameEdit;// 账号
	private EditText mPasswordEdit;// 密码
	private EditText mConfirmationPasswordEdit;// 确认密码
	private EditText mStudentIdEdit;// 学号
	private EditText mPhoneEdit;// 手机
	private EditText mEmailEdit;// 邮箱

	public RegistView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub

		mNameEdit = new EditText(context);
		mPasswordEdit = new EditText(context);
		mConfirmationPasswordEdit = new EditText(context);
		mStudentIdEdit = new EditText(context);
		mPhoneEdit = new EditText(context);
		mEmailEdit = new EditText(context);

		setView();
	}

	private void setView() {
		// TODO Auto-generated method stub
		this.setOrientation(LinearLayout.VERTICAL);
		this.setBackgroundColor(Color.BLUE);
		// this.getBackground().setAlpha((int) (0.50 * 255));

		// 账号
		// 文本
		LinearLayout nameLayout = new LinearLayout(MainActivity.getInstance());
		LinearLayout.LayoutParams nameParams = new LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		nameParams.topMargin = UIControl.p.y / 50;
		nameParams.rightMargin = UIControl.p.y / 50;
		nameLayout.setLayoutParams(nameParams);
		nameLayout.setGravity(Gravity.CENTER_VERTICAL);
		TextView nameTextView = new TextView(MainActivity.getInstance());
		nameTextView.setGravity(Gravity.RIGHT);
		nameTextView.setEms(5);
		nameTextView.setText("账号：");
		nameTextView.setTextColor(Color.BLACK);
		// nameTextView.setTextScaleX(1.2f);
		nameLayout.addView(nameTextView);
		// 编辑框
		mNameEdit
				.setFilters(new InputFilter[] { new InputFilter.LengthFilter(10) });// 限制长度为10
		mNameEdit.setHint("请不要超过八个字");
		// mNameEdit.setEms(10);
		Bitmap startBitmap = BitmapFactory.decodeResource(MainActivity
				.getInstance().getResources(), R.drawable.star);
		Drawable leftDrawable = new BitmapDrawable(Bitmap.createScaledBitmap(
				startBitmap, (int) (mNameEdit.getTextSize() * 1.0f),
				(int) (mNameEdit.getTextSize() * 1.0f), true));
		mNameEdit.setCompoundDrawablesWithIntrinsicBounds(leftDrawable, null,
				null, null);
		mNameEdit.setSingleLine();
		mNameEdit.setBackgroundColor(Color.WHITE);
		mNameEdit.getBackground().setAlpha((int) (0.27 * 255));
		nameLayout.addView(mNameEdit, new LayoutParams(UIControl.p.x / 3 * 2,
				android.view.ViewGroup.LayoutParams.WRAP_CONTENT));
		this.addView(nameLayout);

		// 密码
		// 文本
		LinearLayout passwordLayout = new LinearLayout(
				MainActivity.getInstance());
		LinearLayout.LayoutParams passwordParams = new LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		passwordParams.topMargin = UIControl.p.y / 50;
		passwordLayout.setLayoutParams(passwordParams);
		passwordLayout.setGravity(Gravity.CENTER_VERTICAL);
		TextView passwordTextView = new TextView(MainActivity.getInstance());
		passwordTextView.setGravity(Gravity.RIGHT);
		passwordTextView.setEms(5);
		passwordTextView.setText(" 密 码 ： ");
		passwordTextView.setTextColor(Color.BLACK);
		// passwordTextView.setTextScaleX(1.2f);
		passwordLayout.addView(passwordTextView);
		// 编辑框
		mPasswordEdit
				.setFilters(new InputFilter[] { new InputFilter.LengthFilter(10) });// 限制长度为10
		mPasswordEdit.setHint("请不要超过八个字");
		mPasswordEdit.setEms(8);
		mPasswordEdit.setBackgroundColor(Color.WHITE);
		mPasswordEdit.getBackground().setAlpha((int) (0.27 * 255));
		mPasswordEdit.setInputType(InputType.TYPE_CLASS_TEXT
				| InputType.TYPE_TEXT_VARIATION_PASSWORD);
		mPasswordEdit.setSingleLine();
		mPasswordEdit.setCompoundDrawablesWithIntrinsicBounds(leftDrawable,
				null, null, null);
		passwordLayout.addView(mPasswordEdit, new LayoutParams(
				UIControl.p.x / 3 * 2,
				android.view.ViewGroup.LayoutParams.WRAP_CONTENT));
		this.addView(passwordLayout);

		// 确定密码
		// 文本
		LinearLayout confirLayout = new LinearLayout(MainActivity.getInstance());
		LinearLayout.LayoutParams confirParams = new LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		confirParams.topMargin = UIControl.p.y / 50;
		confirLayout.setLayoutParams(confirParams);
		confirLayout.setGravity(Gravity.CENTER_VERTICAL);
		TextView confirTextView = new TextView(MainActivity.getInstance());
		confirTextView.setGravity(Gravity.RIGHT);
		confirTextView.setEms(5);
		confirTextView.setText("确定密码：");
		confirTextView.setTextColor(Color.BLACK);
		// confirTextView.setTextScaleX(1.2f);
		confirLayout.addView(confirTextView);
		// 编辑框
		mConfirmationPasswordEdit
				.setFilters(new InputFilter[] { new InputFilter.LengthFilter(10) });// 限制长度为10
		mConfirmationPasswordEdit.setHint("请不要超过八个字");
		mConfirmationPasswordEdit.setEms(8);
		mConfirmationPasswordEdit.setSingleLine();
		mConfirmationPasswordEdit.setBackgroundColor(Color.WHITE);
		mConfirmationPasswordEdit.getBackground().setAlpha((int) (0.27 * 255));
		mConfirmationPasswordEdit.setInputType(InputType.TYPE_CLASS_TEXT
				| InputType.TYPE_TEXT_VARIATION_PASSWORD);
		mConfirmationPasswordEdit.setCompoundDrawablesWithIntrinsicBounds(
				leftDrawable, null, null, null);
		confirLayout.addView(mConfirmationPasswordEdit, new LayoutParams(
				UIControl.p.x / 3 * 2,
				android.view.ViewGroup.LayoutParams.WRAP_CONTENT));
		this.addView(confirLayout);

		// 学号
		// 文本
		LinearLayout studentLayout = new LinearLayout(
				MainActivity.getInstance());
		LinearLayout.LayoutParams studentParams = new LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		studentParams.topMargin = UIControl.p.y / 50;
		studentLayout.setLayoutParams(studentParams);
		studentLayout.setGravity(Gravity.CENTER_VERTICAL);
		TextView studentTextView = new TextView(MainActivity.getInstance());
		studentTextView.setGravity(Gravity.RIGHT);
		studentTextView.setEms(5);
		studentTextView.setText("  学号  ：");
		studentTextView.setTextColor(Color.BLACK);
		// studentTextView.setTextScaleX(1.2f);
		studentLayout.addView(studentTextView);
		// 编辑框
		mStudentIdEdit
				.setFilters(new InputFilter[] { new InputFilter.LengthFilter(13) });// 限制长度为10
		mStudentIdEdit.setHint("请不要超过八个字");
		mStudentIdEdit.setEms(8);
		mStudentIdEdit.setSingleLine();
		mStudentIdEdit.setBackgroundColor(Color.WHITE);
		mStudentIdEdit.setCompoundDrawablesWithIntrinsicBounds(leftDrawable,
				null, null, null);
		mStudentIdEdit.getBackground().setAlpha((int) (0.27 * 255));
		studentLayout.addView(mStudentIdEdit, new LayoutParams(
				UIControl.p.x / 3 * 2,
				android.view.ViewGroup.LayoutParams.WRAP_CONTENT));
		this.addView(studentLayout);

		// 手机
		// 文本
		LinearLayout phoneLayout = new LinearLayout(MainActivity.getInstance());
		LinearLayout.LayoutParams phoneParams = new LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		phoneParams.topMargin = UIControl.p.y / 50;
		phoneLayout.setLayoutParams(phoneParams);
		phoneLayout.setGravity(Gravity.CENTER_VERTICAL);
		TextView phoneTextView = new TextView(MainActivity.getInstance());
		phoneTextView.setGravity(Gravity.RIGHT);
		phoneTextView.setEms(5);
		phoneTextView.setText("手机号码：");
		phoneTextView.setTextColor(Color.BLACK);
		// phoneTextView.setTextScaleX(1.2f);
		phoneLayout.addView(phoneTextView);
		// 编辑框
		mPhoneEdit.setFilters(new InputFilter[] { new InputFilter.LengthFilter(
				11) });// 限制长度为10
		mPhoneEdit.setHint("请不要超过八个字");
		mPhoneEdit.setEms(8);
		mPhoneEdit.setSingleLine();
		mPhoneEdit.setBackgroundColor(Color.WHITE);
		mPhoneEdit.setCompoundDrawablesWithIntrinsicBounds(leftDrawable,
				null, null, null);
		mPhoneEdit.getBackground().setAlpha((int) (0.27 * 255));
		phoneLayout.addView(mPhoneEdit, new LayoutParams(UIControl.p.x / 3 * 2,
				android.view.ViewGroup.LayoutParams.WRAP_CONTENT));
		this.addView(phoneLayout);

//		// 邮箱
//		// 文本
//		LinearLayout emailLayout = new LinearLayout(MainActivity.getInstance());
//		LinearLayout.LayoutParams emailParams = new LayoutParams(
//				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
//		emailParams.topMargin = UIControl.p.y / 50;
//		emailLayout.setLayoutParams(emailParams);
//		emailLayout.setGravity(Gravity.CENTER_VERTICAL);
//		TextView emailTextView = new TextView(MainActivity.getInstance());
//		emailTextView.setGravity(Gravity.RIGHT);
//		emailTextView.setEms(5);
//		emailTextView.setText(" 邮 箱 ：");
//		emailTextView.setTextColor(Color.BLACK);
//		// emailTextView.setTextScaleX(1.2f);
//		emailLayout.addView(emailTextView);
//		// 编辑框
//		mEmailEdit.setFilters(new InputFilter[] { new InputFilter.LengthFilter(
//				20) });// 限制长度为10
//		mEmailEdit.setHint("请不要超过八个字");
//		mEmailEdit.setEms(8);
//		mEmailEdit.setSingleLine();
//		mEmailEdit.setBackgroundColor(Color.WHITE);
//		mEmailEdit.getBackground().setAlpha((int) (0.27 * 255));
//		emailLayout.addView(mEmailEdit, new LayoutParams(UIControl.p.x / 3 * 2,
//				android.view.ViewGroup.LayoutParams.WRAP_CONTENT));
//		this.addView(emailLayout);

		// 图片
		LinearLayout bitmapLayout = new LinearLayout(MainActivity.getInstance());
		LinearLayout.LayoutParams bitmapParams = new LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		bitmapParams.topMargin = UIControl.p.y / 50;
		bitmapLayout.setLayoutParams(bitmapParams);
		bitmapLayout.setGravity(Gravity.CENTER);
		// 选择图片按钮
		Button selectBitmapButton = new Button(MainActivity.getInstance());
		// selectBitmapButton.setTextScaleX(1.5f);
		// selectBitmapButton.setText("选择图片");
		selectBitmapButton.setBackgroundResource(R.drawable.select_bitmap_btn);
		selectBitmapButton.setOnClickListener(selectHeadClickListener);
		bitmapLayout.addView(selectBitmapButton, new LayoutParams(
				(int) (mEmailEdit.getTextSize() * 1.5f * 250 / 80),
				(int) (mEmailEdit.getTextSize() * 1.5f)));
		// 查看图片按钮
		Button lookBitmapButton = new Button(MainActivity.getInstance());
		// lookBitmapButton.setTextScaleX(1.5f);
		// lookBitmapButton.setText("查看图片");
		lookBitmapButton.setBackgroundResource(R.drawable.look_bitmap_btn);
		lookBitmapButton.setOnClickListener(lookHeadClickListener);
		LayoutParams selectBtnParams = new LayoutParams(
				(int) (mEmailEdit.getTextSize() * 1.5f * 250 / 80),
				(int) (mEmailEdit.getTextSize() * 1.5f));
		selectBtnParams.leftMargin = UIControl.p.y / 50;
		bitmapLayout.addView(lookBitmapButton, selectBtnParams);
		this.addView(bitmapLayout);

		// 按钮
		LinearLayout btnLayout = new LinearLayout(MainActivity.getInstance());
		LinearLayout.LayoutParams btnParams = new LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		btnParams.topMargin = UIControl.p.y / 50;
		btnLayout.setLayoutParams(btnParams);
		btnLayout.setGravity(Gravity.CENTER);
		// 注册按钮
		Button registButton = new Button(MainActivity.getInstance());
		registButton.setTextScaleX(1.5f);
		registButton.setText(" 注  册 ");
		registButton.setOnClickListener(registClickListener);
		btnLayout.addView(registButton);
		// 返回登陆按钮
		Button backButton = new Button(MainActivity.getInstance());
		backButton.setTextScaleX(1.5f);
		backButton.setText("返回登陆");
		backButton.setOnClickListener(backLoginClickListener);
		btnLayout.addView(backButton);
		this.addView(btnLayout);
	}

	// 选择图片按钮监听器
	private OnClickListener selectHeadClickListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			ImageUtils.selectAndCropImage();
		}
	};

	// 查看按钮监听器
	private OnClickListener lookHeadClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if (mSelectHeadBitmap != null) {

				ShowPictureView showPictureView = new ShowPictureView(
						MainActivity.getInstance(), mSelectHeadBitmap);
				MainLayout.getInstance().addView(showPictureView);
				showPictureView.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						MainLayout.getInstance().removeView(v);
					}
				});
			}
		}
	};

	// 返回登陆监听器
	private OnClickListener backLoginClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			ScaleAnimation animation = new ScaleAnimation(1.0f, 0, 1.0f, 0);
			animation.setDuration(500);
			RegistView.this.startAnimation(animation);
			animation.setAnimationListener(new AnimationListener() {

				@Override
				public void onAnimationStart(Animation animation) {
					// TODO Auto-generated method stub

				}

				@Override
				public void onAnimationRepeat(Animation animation) {
					// TODO Auto-generated method stub

				}

				@Override
				public void onAnimationEnd(Animation animation) {
					// TODO Auto-generated method stub
					closeRegistView();
				}
			});

			// MainLayout.getInstance().removeView(RegistView.this);//失效了，为什么？
		}
	};

	// 注册监听器
	private OnClickListener registClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			final User data = new User();
			Log.i("TAG",mNameEdit.getText().toString() );
			System.out.println(mPasswordEdit.getText().toString());
			if (mNameEdit.getText().length()==0 || mPasswordEdit.getText().length()==0 
					|| mConfirmationPasswordEdit.getText().length()==0
					|| mStudentIdEdit.getText().length()==0||mPhoneEdit.getText().length()==0) {
				MyToast.showToast(MyToast.INFORMATION_IS_EMPTY);
				return;
			}
			if (!mPasswordEdit.getText().toString()
					.equals(mConfirmationPasswordEdit.getText().toString())) {
				Toast.makeText(MainActivity.getInstance(), "请确保两次输入的密码一致", 2000);
			}
			data.setUsername(mNameEdit.getText().toString());
			data.setPassword(mPasswordEdit.getText().toString());
			data.setStudentId(mStudentIdEdit.getText().toString());
			data.setPhone(mPhoneEdit.getText().toString());
//			data.setEmail(mEmailEdit.getText().toString());
			
			MyProgressDialog.getInstance().setisShow(true);
			if (mSelectHeadBitmap != null) {
				try {

					final File file = File.createTempFile("schoolInfo", "head");

					ImageUtils.saveImageToLocal(mSelectHeadBitmap,
							file.getPath());
					final BmobFile bmobFile = new BmobFile(file);
					bmobFile.uploadblock(MainActivity.getInstance(),
							new UploadFileListener() {

								@Override
								public void onSuccess() {
									// TODO Auto-generated method stub
//									System.out.println("头像上传成功"+bmobFile.getFileUrl());
									data.setHeadPicture(bmobFile);
									file.delete();// 删除临时文件
									data.signUp(MainActivity.getInstance(),
											new SaveListener() {// 保存信息

												@Override
												public void onSuccess() {
													// TODO Auto-generated
													// method stub
													Toast.makeText(
															MainActivity
																	.getInstance(),
															"注册成功！", 2000)
															.show();
													MyProgressDialog.getInstance().setisShow(false);
													closeRegistView();
													
												}

												@Override
												public void onFailure(int arg0,
														String arg1) {
													// TODO Auto-generated
													// method stub
													Toast.makeText(
															MainActivity
																	.getInstance(),
															arg0 + arg1, 2000)
															.show();
													MyProgressDialog.getInstance().setisShow(false);
												}
											});
								}

								@Override
								public void onFailure(int arg0, String arg1) {
									// TODO Auto-generated method stub
									MyProgressDialog.getInstance().setisShow(false);
								}
							});
				} catch (Exception e) {
					// TODO: handle exception
				}
			} else {
				data.signUp(MainActivity.getInstance(), new SaveListener() {// 保存信息

							@Override
							public void onSuccess() {
								// TODO Auto-generated method stub
								Toast.makeText(MainActivity.getInstance(),
										"注册成功！", 2000).show();
								closeRegistView();
								MyProgressDialog.getInstance().setisShow(false);
							}

							@Override
							public void onFailure(int arg0, String arg1) {
								// TODO Auto-generated method stub
								Toast.makeText(MainActivity.getInstance(),
										arg0 +":"+ arg1, 2000).show();
								MyProgressDialog.getInstance().setisShow(false);
							}
						});
			}

		}
	};

	public Bitmap getmSelectHeadBitmap() {
		return mSelectHeadBitmap;
	}

	public void setmSelectHeadBitmap(Bitmap mSelectHeadBitmap) {
		this.mSelectHeadBitmap = mSelectHeadBitmap;
	}

	//关闭注册界面
	private void closeRegistView(){
		ViewGroup parent = (ViewGroup) RegistView.this.getParent();
		parent.removeView(RegistView.this);
	}
}
