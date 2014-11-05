package com.schoolinfo.layout;

import java.io.File;
import java.io.IOException;

import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UploadFileListener;

import com.schoolinfo.MainActivity;
import com.schoolinfo.R;
import com.schoolinfo.control.GlobalControl;
import com.schoolinfo.control.UIControl;
import com.schoolinfo.data.LostData;
import com.schoolinfo.data.OldData;
import com.schoolinfo.utils.ImageUtils;
import com.schoolinfo.view.MyButton;
import com.schoolinfo.view.MyProgressDialog;
import com.schoolinfo.view.MyToast;
import com.schoolinfo.view.ShowPictureView;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.opengl.Visibility;
import android.os.Handler;
import android.os.Message;
import android.text.InputFilter;
import android.text.Layout;
import android.text.TextPaint;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

/**
 * 发布界面
 * 
 * @author huangwubin 2014-9-22
 */
public class ReleaseLayout extends BaseLayout implements OnClickListener {

	private int[] mTypeImageId = new int[] { R.drawable.gallery_lost,
			R.drawable.gallery_old };
	private String[] mContactType = new String[] { "手机长号", "手机短号", "QQ", "邮箱" };
	private int mSelectType = 0;// 当前选择的类型
	private Uri mSelectBitmapUri;
	private boolean mIsLost = true;// 是否寻物启事

	private LinearLayout mBodyLayout;
	private LinearLayout mTypeLayout;// 发布类型（第一行）
	private Button[] mTypeButton = new Button[mTypeImageId.length];// 类型
	private EditText mNameEditText;// 物品名字
	private EditText mDecEditText;// 物品详情
	private EditText mContaceEditText;
	private Spinner mContactTypeSpinner;// 联系方式类型
	private EditText mContactInfoEditText;// 联系方式
	// 以下分功能显示
	private RadioGroup mIsLostGroup;// 失物招领或寻物启事
	private LinearLayout mPriceLayout;// 二手物品价格布局
	private EditText mPriceView;
	private CheckBox mBindingBox;//是否与当前用户绑定
	
	private Handler mHandler;
	public ReleaseLayout(Context context) {

		super.mLayout = new RelativeLayout(context);
		super.mLayout.setTag(RunLayout.RELEASELAYOUT);
		mBodyLayout = new LinearLayout(context);
		mTypeLayout = new LinearLayout(context);
		for (int i = 0; i < mTypeButton.length; i++) {
			mTypeButton[i] = new Button(context);
		}
		mNameEditText = new EditText(context);
		mContaceEditText = new EditText(context);
		mContactInfoEditText = new EditText(context);
		mContactTypeSpinner = new Spinner(context);
		mDecEditText = new EditText(context);
		mIsLostGroup = new RadioGroup(context);
		mPriceLayout = new LinearLayout(context);
		mPriceView = new EditText(context);
		mBindingBox=new CheckBox(context);
		
		setView();
		initHandler();
	}

	private void initHandler() {
		// TODO Auto-generated method stub
		mHandler=new Handler(){

			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				switch (msg.what) {
				case 0://显示登录提示对话框
					Builder builder=new Builder(MainActivity.getInstance());
					builder.setTitle("提示");
					builder.setMessage("当前未登录，是否转到登录界面？");
					builder.setPositiveButton("确定", new android.content.DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							MainLayout.getInstance().changeLayout(new LoginLayout(MainActivity.getInstance()));
						}
					});
					builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
						}
					});
					builder.create().show();
					mHandler.sendEmptyMessage(1);
					break;
				case 1://取消进度框
//					MyProgressDialog.getInstance().cancel();
					MyProgressDialog.getInstance().dismiss();
					break;
				case 2://显示进度框
//							MyProgressDialog.getInstance().show();
					MyProgressDialog.getInstance().setisShow(true, 2);
					break;
				default:
					break;
				}
				super.handleMessage(msg);
			}
			
		};
	}

	private void setView() {
		// TODO Auto-generated method stub

		// 顶部菜单
		TopLayout topLayout = new TopLayout(MainActivity.getInstance());
		topLayout.setLayoutText(RunLayout.RELEASELAYOUT);
		super.mLayout.addView(topLayout,
				UIControl.getLayoutParams(UIControl.topData, 1));

		mBodyLayout.setOrientation(LinearLayout.VERTICAL);
		// mBodyLayout.setGravity(Gravity.CENTER);

		// 临时套多一层，实现布局居中
		RelativeLayout tempReleaseLayout = new RelativeLayout(
				MainActivity.getInstance());
		tempReleaseLayout.setGravity(Gravity.CENTER);
		tempReleaseLayout.addView(mBodyLayout);

		ScrollView bodyScrollView = new ScrollView(MainActivity.getInstance());
		bodyScrollView.addView(tempReleaseLayout);
		super.mLayout.addView(bodyScrollView,
				UIControl.getLayoutParams(UIControl.bodyData, 1));

		// 发布类型
		mTypeLayout.setGravity(Gravity.CENTER_VERTICAL);
		LinearLayout.LayoutParams typeParams = new LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		typeParams.topMargin = UIControl.p.y / 50;
		;
		mTypeLayout.setLayoutParams(typeParams);
		TextView typeTextView = new TextView(MainActivity.getInstance());
		typeTextView.setGravity(Gravity.RIGHT);
		typeTextView.setText("发布类型：");
		typeTextView.setTextColor(Color.BLACK);
		typeTextView.setTextScaleX(1.2f);
		mTypeLayout.addView(typeTextView);
		final Animation animation = new AlphaAnimation(1.0f, 0.5f);// 透明度动画
		animation.setFillAfter(true);
		animation.setDuration(0);
		for (int i = 0; i < mTypeButton.length; i++) {
			final int index = i;
			mTypeButton[i].setBackgroundResource(mTypeImageId[i]);
			LinearLayout.LayoutParams typeButtonParams = new LinearLayout.LayoutParams(
					UIControl.p.x / 10, UIControl.p.x / 10 * 271 / 200);
			typeButtonParams.leftMargin = typeButtonParams.width / 2;
			mTypeLayout.addView(mTypeButton[i], typeButtonParams);

			if (i != 0) {// 默认选中0
				mTypeButton[i].startAnimation(animation);
				mTypeButton[i].setTag(false);
			}

			mTypeButton[i].setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					if ((Boolean) (v.getTag()) == false) {// 没被选中
						if (mSelectType > -1) {
							mTypeButton[mSelectType].setTag(false);
							if (mTypeButton[mSelectType].getAnimation() == null) {// 取消选中透明度变小
								mTypeButton[mSelectType]
										.startAnimation(animation);
							}
						}
						v.setTag(true);

						v.clearAnimation();
						mSelectType = index;
						switch (index) {
						case 0:// 寻物启事
							mPriceLayout.setVisibility(View.GONE);
							mIsLostGroup.setVisibility(View.VISIBLE);
							break;
						case 1:// 二手市场
							mPriceLayout.setVisibility(View.VISIBLE);
							mIsLostGroup.setVisibility(View.GONE);
							break;
						default:
							break;
						}
					}
				}
			});
		}
		mBodyLayout.addView(mTypeLayout);

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
		selectBitmapButton.setOnClickListener(mSelectBitmapListener);
		bitmapLayout.addView(selectBitmapButton, new LayoutParams(
				mTypeButton[0].getLayoutParams().width * 250 / 80,
				mTypeButton[0].getLayoutParams().width));
		// 查看图片按钮
		Button lookBitmapButton = new Button(MainActivity.getInstance());
		// lookBitmapButton.setTextScaleX(1.5f);
		// lookBitmapButton.setText("查看图片");
		lookBitmapButton.setBackgroundResource(R.drawable.look_bitmap_btn);
		lookBitmapButton.setOnClickListener(mLookBitmapListener);
		LayoutParams selectBtnParams = new LayoutParams(
				mTypeButton[0].getLayoutParams().width * 250 / 80,
				mTypeButton[0].getLayoutParams().width);
		selectBtnParams.leftMargin = UIControl.p.y / 50;
		bitmapLayout.addView(lookBitmapButton, selectBtnParams);
		mBodyLayout.addView(bitmapLayout);

		// 是否寻物启事
		// 寻物启事
		final RadioButton lostRadioButton = new RadioButton(
				MainActivity.getInstance());
		lostRadioButton.setText("寻物启事");
		lostRadioButton.setTextColor(Color.BLACK);
		// lostRadioButton.setTextScaleX(1.5f);
		mIsLostGroup.addView(lostRadioButton);
		// 失物招领
		RadioButton foundRadioButton = new RadioButton(
				MainActivity.getInstance());
		foundRadioButton.setText("失物招领");
		foundRadioButton.setTextColor(Color.BLACK);
		// foundRadioButton.setTextScaleX(1.5f);
		mIsLostGroup.addView(foundRadioButton);
		mIsLostGroup.check(lostRadioButton.getId());
		mIsLostGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				if (checkedId == lostRadioButton.getId()) {
					mIsLost = true;
				} else {
					mIsLost = false;
				}
			}
		});
		mIsLostGroup.setOrientation(RadioGroup.HORIZONTAL);
		// mIsLostGroup.setGravity(Gravity.CENTER);
		mBodyLayout.addView(mIsLostGroup);

		// 联系人
		// 文本
		LinearLayout contactLayout = new LinearLayout(
				MainActivity.getInstance());
		LinearLayout.LayoutParams contactParams = new LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		contactParams.topMargin = UIControl.p.y / 50;
		contactLayout.setLayoutParams(contactParams);
		contactLayout.setGravity(Gravity.CENTER_VERTICAL);
		TextView contactTextView = new TextView(MainActivity.getInstance());
		contactTextView.setGravity(Gravity.CENTER);
		// contactTextView.setEms(5);
		contactTextView.setText("联系人：");
		contactTextView.setTextColor(Color.BLACK);
		contactTextView.setTextScaleX(1.2f);
		contactLayout.addView(contactTextView);
		// 编辑框
		mContaceEditText
				.setFilters(new InputFilter[] { new InputFilter.LengthFilter(6) });// 限制长度为10
		mContaceEditText.setHint("请不要超过八个字");
		mContaceEditText.setEms(10);
		mContaceEditText.setSingleLine();
		mContaceEditText.setBackgroundColor(Color.WHITE);
		mContaceEditText.getBackground().setAlpha((int) (0.27 * 255));
		contactLayout.addView(mContaceEditText, new LayoutParams(
				UIControl.p.x / 3 * 2,
				android.view.ViewGroup.LayoutParams.WRAP_CONTENT));
		mBodyLayout.addView(contactLayout);

		// 联系方式
		LinearLayout contactInfoLayout = new LinearLayout(
				MainActivity.getInstance());
		LinearLayout.LayoutParams contactInfoParams = new LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		contactInfoParams.topMargin = UIControl.p.y / 50;
		contactInfoLayout.setGravity(Gravity.CENTER);
		contactInfoLayout.setLayoutParams(contactParams);
		//文本
		TextView contactInfoTextView = new TextView(MainActivity.getInstance());
		contactInfoTextView.setGravity(Gravity.RIGHT);
		contactInfoTextView.setText("联系方式：");
		contactInfoTextView.setTextColor(Color.BLACK);
		contactInfoTextView.setTextScaleX(1.2f);
		contactInfoLayout.addView(contactInfoTextView);
		
		// 联系方式类型
		ArrayAdapter<String> contactInfoAdapter=new ArrayAdapter<String>(MainActivity.getInstance(),android.R.layout.simple_spinner_item,mContactType);
		contactInfoAdapter.setDropDownViewResource(R.layout.sprinner_style);
		mContactTypeSpinner.setAdapter(contactInfoAdapter);
		contactInfoLayout.addView(mContactTypeSpinner,
				new LayoutParams(android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
						android.view.ViewGroup.LayoutParams.WRAP_CONTENT));
		mBodyLayout.addView(contactInfoLayout);
		
		// 联系方式内容
		LinearLayout contactInfoContentLayout = new LinearLayout(
				MainActivity.getInstance());
		LinearLayout.LayoutParams contactInfoContentParams = new LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		contactInfoContentLayout.setGravity(Gravity.CENTER);
		contactInfoContentLayout.setLayoutParams(contactInfoContentParams);
		// 联系方式内容文本
		TextView contactInfoContentTextView = new TextView(MainActivity.getInstance());
		contactInfoContentTextView.setGravity(Gravity.RIGHT);
		contactInfoContentTextView.setText("联系方式：");
		contactInfoContentTextView.setVisibility(4);//不可见，但占空间，相当于View.UNVISIBLE
		contactInfoContentTextView.setTextColor(Color.BLACK);
		contactInfoContentTextView.setTextScaleX(1.2f);
		contactInfoContentLayout.addView(contactInfoContentTextView);
		mContactInfoEditText
				.setFilters(new InputFilter[] { new InputFilter.LengthFilter(15) });// 限制长度为140
		mContactInfoEditText.setEms(10);
		mContactInfoEditText.setSingleLine();
		mContactInfoEditText.setBackgroundColor(Color.WHITE);
		mContactInfoEditText.getBackground().setAlpha((int) (0.27 * 255));
		contactInfoContentLayout.addView(mContactInfoEditText);
//		contactInfoLayout.addView(contactInfoContentlLayout);
		mBodyLayout.addView(contactInfoContentLayout);

		// 物品名字
		// 文本
		LinearLayout nameLayout = new LinearLayout(MainActivity.getInstance());
		LinearLayout.LayoutParams nameParams = new LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		nameParams.topMargin = UIControl.p.y / 50;
		nameLayout.setLayoutParams(nameParams);
		nameLayout.setGravity(Gravity.CENTER_VERTICAL);
		TextView nameTextView = new TextView(MainActivity.getInstance());
		nameTextView.setGravity(Gravity.RIGHT);
		nameTextView.setText("物品名称：");
		nameTextView.setTextColor(Color.BLACK);
		nameTextView.setTextScaleX(1.2f);
		nameLayout.addView(nameTextView);
		// 编辑框
		mNameEditText
				.setFilters(new InputFilter[] { new InputFilter.LengthFilter(10) });// 限制长度为10
		mNameEditText.setHint("请不要超过十个字...");
		mNameEditText.setEms(10);
		mNameEditText.setBackgroundColor(Color.WHITE);
		mNameEditText.getBackground().setAlpha((int) (0.27 * 255));
		nameLayout.addView(mNameEditText);
		mBodyLayout.addView(nameLayout);

		// 价格
		// 文本
		LinearLayout.LayoutParams priceParams = new LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		priceParams.topMargin = UIControl.p.y / 50;
		mPriceLayout.setLayoutParams(priceParams);
		mPriceLayout.setGravity(Gravity.CENTER_VERTICAL);
		TextView priceTextView = new TextView(MainActivity.getInstance());
		priceTextView.setGravity(Gravity.RIGHT);
		priceTextView.setText("物品价格：");
		priceTextView.setTextColor(Color.BLACK);
		priceTextView.setTextScaleX(1.2f);
		mPriceLayout.addView(priceTextView);
		// 编辑框
		mPriceView.setFilters(new InputFilter[] { new InputFilter.LengthFilter(
				10) });// 限制长度为10
		Bitmap yuanBitmap = BitmapFactory.decodeResource(MainActivity
				.getInstance().getResources(), R.drawable.old_yuan);
		Drawable leftDrawable = new BitmapDrawable(Bitmap.createScaledBitmap(
				yuanBitmap, (int) (mPriceView.getTextSize() * 2.0),
				(int) (mPriceView.getTextSize() * 2.0), true));
		mPriceView.setCompoundDrawablesWithIntrinsicBounds(leftDrawable, null,
				null, null);
		mPriceView.setGravity(Gravity.RIGHT);
		mPriceView.setSingleLine();
		mPriceView.setInputType(EditorInfo.TYPE_CLASS_NUMBER);
		mPriceView.setEms(5);
		mPriceView.setMaxEms(5);
		mPriceView.setBackgroundColor(Color.WHITE);
		mPriceView.getBackground().setAlpha((int) (0.27 * 255));
		mPriceLayout.addView(mPriceView);
		mBodyLayout.addView(mPriceLayout);
		// 默认寻物启事，此布局隐藏
		mPriceLayout.setVisibility(View.GONE);

		// 物品详情
		LinearLayout decLayout = new LinearLayout(MainActivity.getInstance());
		LinearLayout.LayoutParams decParams = new LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		decParams.topMargin = UIControl.p.y / 50;
		decLayout.setLayoutParams(decParams);
		decLayout.setGravity(Gravity.CENTER_VERTICAL);
		TextView decTextView = new TextView(MainActivity.getInstance());
		decTextView.setGravity(Gravity.RIGHT);
		decTextView.setText("物品详情：");
		decTextView.setTextColor(Color.BLACK);
		decTextView.setTextScaleX(1.2f);
		decLayout.addView(decTextView);
		// 编辑框
		mDecEditText
				.setFilters(new InputFilter[] { new InputFilter.LengthFilter(
						100) });// 限制长度为10
		mDecEditText.setHint("请不要超过100个字");
		mDecEditText.setEms(10);
		mDecEditText.setMinLines(3);
		mDecEditText.setBackgroundColor(Color.WHITE);
		mDecEditText.getBackground().setAlpha((int) (0.27 * 255));
		decLayout.addView(mDecEditText);
		mBodyLayout.addView(decLayout);
		
		//是否与当前用户绑定
		mBindingBox.setText("是否与当前用户绑定");
		mBindingBox.setChecked(false);
		mBodyLayout.addView(mBindingBox);
		
		// 发布按钮
		LinearLayout btnLayout=new LinearLayout(MainActivity.getInstance());
		btnLayout.setGravity(Gravity.CENTER);
		MyButton button = new MyButton(MainActivity.getInstance());
		// button.setTextScaleX(1.5f);
		// button.setText("发布");
		button.setBackgroundResource(R.drawable.release_btn);
		button.setOnClickListener(mSelectBitmapListener);
		LinearLayout.LayoutParams btnParams = new LayoutParams(
				mTypeButton[0].getLayoutParams().width * 250 / 80,
				mTypeButton[0].getLayoutParams().width);
		btnParams.topMargin = UIControl.p.y / 50;
		btnParams.bottomMargin = UIControl.p.y / 50;
		button.setOnClickListener(this);
		btnLayout.addView(button,btnParams);
		mBodyLayout.addView(btnLayout, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
	}

	// 计算出该TextView中文字的长度(像素)
	public float getTextViewLength(TextView textView, String text) {
		TextPaint paint = textView.getPaint();
		// 得到使用该paint写上text的时候,像素为多少
		float textLength = paint.measureText(text);
		return textLength;
	}

	// 发布
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		//判断当前是否有网络连接
		if (!GlobalControl.isNetworkAvailable()) {//没有网络连接
			MyToast.showToast(MyToast.NOT_NETWORK);
			return ;
		}
		if (!checkInfo()) {
			return ;
		}
		mHandler.sendEmptyMessage(2);
//		MyProgressDialog.getInstance().setisShow(true);
		switch (mSelectType) {
		case 0:

					releaseLost();// 发布寻物启事

			break;
		case 1:// 发布二手物品
					releaseOld();

			break;
		default:
			break;
		}

	}

	private OnClickListener mSelectBitmapListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(
					Intent.ACTION_PICK,
					android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
			MainActivity.getInstance().startActivityForResult(intent, 1);
		}
	};

	private OnClickListener mLookBitmapListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if (mSelectBitmapUri != null) {

				ShowPictureView showPictureView = new ShowPictureView(
						MainActivity.getInstance(), mSelectBitmapUri);
				MainLayout.getInstance().addView(showPictureView);
			}
		}
	};

	public void setSelectBitmapUri(Uri selectBitmapUri) {
		this.mSelectBitmapUri = selectBitmapUri;
	}

	//检查输入限制
	private boolean checkInfo() {
		if (mContaceEditText.getText().length()==0||mContactInfoEditText.getText().length()==0||mNameEditText.getText().length()==0||mDecEditText.getText().length()==0||(mSelectType==1&&mPriceView.getText().length()==0)) {
			MyToast.showToast(MyToast.INFORMATION_IS_EMPTY);
			return false;
		}
		return true;
	}
	// 发布失物招领
	private void releaseLost() {
		final LostData data = new LostData();
		data.mContact = mContaceEditText.getText().toString();
		data.mContactType = mContactType[mContactTypeSpinner
				.getSelectedItemPosition()];
		data.mContactInfo = mContactInfoEditText.getText().toString();
		data.mName = mNameEditText.getText().toString();
		data.mDecString = mDecEditText.getText().toString();
		data.isLost = mIsLost;
		if (mBindingBox.isChecked()) {//与当前账号绑定
			if (MainLayout.getInstance().mCurrentUser==null) {
				mHandler.sendEmptyMessage(0);
//				MyProgressDialog.getInstance().cancel();
				return;
			}
			else {
				data.mUser=MainLayout.getInstance().mCurrentUser;
			}
		}
		if (mSelectBitmapUri != null) {// 有选择图片
			try {
				
				final File tempFile = File.createTempFile("SchoolInfo", ".jpg");

				ImageUtils.qualityCompress(
						ImageUtils.getMaxBitmap(
								ImageUtils.getImagePath(mSelectBitmapUri), 1),
						tempFile);
				final BmobFile picture = new BmobFile(tempFile);
				
				picture.uploadblock(MainActivity.getInstance(),
						new UploadFileListener() {

							@Override
							public void onSuccess() {
								// TODO Auto-generated method stub
								data.mPicture = picture;
								tempFile.delete();
								data.save(MainActivity.getInstance(),
										new SaveListener() {

											@Override
											public void onSuccess() {
												// TODO Auto-generated
												// method
												// stub
												MyToast.showToast(MyToast.RELEASE_SUCCESS);
												mHandler.sendEmptyMessage(1);
											}

											@Override
											public void onFailure(int arg0,
													String arg1) {
												// TODO Auto-generated
												// method
												// stub
												MyToast.showToast(arg0 + ":"
														+ arg1);
												mHandler.sendEmptyMessage(1);
											}
										});
							}

							@Override
							public void onFailure(int arg0, String arg1) {
								// TODO Auto-generated method stub
								MyToast.showToast(MyToast.UPLOAD_IMAGE_LOSE);
								mHandler.sendEmptyMessage(1);
							}
						});
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {// 没有图片,直接保存信息
			data.save(MainActivity.getInstance(), new SaveListener() {

				@Override
				public void onSuccess() {
					// TODO Auto-generated method
					// stub
					MyToast.showToast(MyToast.RELEASE_SUCCESS);
					mHandler.sendEmptyMessage(1);
				}

				@Override
				public void onFailure(int arg0, String arg1) {
					// TODO Auto-generated method
					// stub
					MyToast.showToast(arg0 + ":" + arg1);
					mHandler.sendEmptyMessage(1);
				}
			});
		}

	}
	
	// 发布二手物品
	private void releaseOld() {
		final OldData data = new OldData();
		data.mContact = mContaceEditText.getText().toString();
		data.mContactType = mContactType[mContactTypeSpinner
				.getSelectedItemPosition()];
		data.mContactInfo = mContactInfoEditText.getText().toString();
		data.mName = mNameEditText.getText().toString();
		data.mDecString = mDecEditText.getText().toString();
		data.mPrice = Float.valueOf(mPriceView.getText().toString());
		if (mBindingBox.isChecked()) {//与当前账号绑定
			if (MainLayout.getInstance().mCurrentUser==null) {
				mHandler.sendEmptyMessage(0);
//				MyProgressDialog.getInstance().cancel();
				return;
			}
			else {
				data.mUser=MainLayout.getInstance().mCurrentUser;
			}
		}
		if (mSelectBitmapUri != null) {// 有选择图片
			try {
				final File tempFile = File.createTempFile("SchoolInfo", ".jpg");

				ImageUtils.qualityCompress(
						ImageUtils.getMaxBitmap(
								ImageUtils.getImagePath(mSelectBitmapUri), 1),
						tempFile);
				final BmobFile picture = new BmobFile(tempFile);

				picture.uploadblock(MainActivity.getInstance(),
						new UploadFileListener() {

							@Override
							public void onSuccess() {
								// TODO Auto-generated method stub
								data.mPicture = picture;
								tempFile.delete();
								data.save(MainActivity.getInstance(),
										new SaveListener() {

											@Override
											public void onSuccess() {
												// TODO Auto-generated
												// method
												// stub
												MyToast.showToast(MyToast.RELEASE_SUCCESS);
//												MyProgressDialog.getInstance()
//														.setisShow(false);
												mHandler.sendEmptyMessage(1);
											}

											@Override
											public void onFailure(int arg0,
													String arg1) {
												// TODO Auto-generated
												// method
												// stub
												MyToast.showToast(arg0 + ":"
														+ arg1);
												mHandler.sendEmptyMessage(1);
											}
										});
							}

							@Override
							public void onFailure(int arg0, String arg1) {
								// TODO Auto-generated method stub
								MyToast.showToast(MyToast.UPLOAD_IMAGE_LOSE);
								mHandler.sendEmptyMessage(1);
							}
						});
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {// 没有图片,直接保存信息
			data.save(MainActivity.getInstance(), new SaveListener() {

				@Override
				public void onSuccess() {
					// TODO Auto-generated method
					// stub
					MyToast.showToast(MyToast.RELEASE_SUCCESS);
					mHandler.sendEmptyMessage(1);
				}

				@Override
				public void onFailure(int arg0, String arg1) {
					// TODO Auto-generated method
					// stub
					MyToast.showToast(arg0 + ":" + arg1);
					mHandler.sendEmptyMessage(1);
				}
			});
		}

	}
}
