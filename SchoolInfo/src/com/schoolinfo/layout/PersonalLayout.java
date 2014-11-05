package com.schoolinfo.layout;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

import com.schoolinfo.MainActivity;
import com.schoolinfo.R;
import com.schoolinfo.control.UIControl;
import com.schoolinfo.data.User;
import com.schoolinfo.view.ImagePopupWindow;
import com.schoolinfo.view.MyToast;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.XmlResourceParser;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

/**
 * 个人中心
 * 
 * @author huangwubin 2014-10-12
 */
public class PersonalLayout extends BaseLayout {

	private User mUser;
	private Context mContext;
	private ImagePopupWindow mImagePopupWindow;//上滑菜单

	private RelativeLayout mChangePasswordLayout;//重置密码布局
	public PersonalLayout(Context context) {
		// TODO Auto-generated constructor stub
		mRunLayout=RunLayout.PERSONALLAYOUT;
		mContext = context;
		mUser = MainLayout.getInstance().mCurrentUser;
		if (mUser == null) {//未登录，跳到登陆界面
//			MainLayout.getInstance().removeView(mLayout);
			return;
		}
		setView();
	}

	private void setView() {
		// TODO Auto-generated method stub
		mLayout=new RelativeLayout(mContext);
		mLayout.setBackgroundResource(R.drawable.personal_bg);

		// 头像
		ImageView photoView = new ImageView(mContext);
		if (mUser.getHeadPicture() == null) {
			photoView.setImageResource(R.drawable.def_head);
		} else {
			mUser.getHeadPicture().loadImageThumbnail(mContext, photoView,
					UIControl.personalPhotoData.w,
					UIControl.personalPhotoData.h);
		}
		mLayout.addView(photoView,
				UIControl.getLayoutParams(UIControl.personalPhotoData, 1));

		// 文本
		LinearLayout textLayout = new LinearLayout(mContext);
		textLayout.setOrientation(LinearLayout.VERTICAL);
		// textLayout.setBackgroundColor(Color.BLUE);
		LinearLayout.LayoutParams textParams = new LinearLayout.LayoutParams(
				UIControl.personalTextData.w, UIControl.personalTextData.h / 3);
		// 欢迎语
		TextView welcomeView = new TextView(mContext);
		welcomeView.setTextSize(UIControl.personalTextData.h / 3 * 0.4f);
		welcomeView.setTextColor(Color.BLACK);
		welcomeView.setText("欢迎您！");
		welcomeView.setIncludeFontPadding(false);
		// welcomeView.setBackgroundColor(Color.RED);
		textLayout.addView(welcomeView, textParams);
		// 账号
		TextView nameView = new TextView(mContext);
		nameView.setText(mUser.getUsername());
		nameView.setTextSize(UIControl.personalTextData.h / 3 * 0.4f);
		nameView.setTextColor(Color.BLACK);
		// nameView.setGravity(Gravity.LEFT);
		// nameView.setBackgroundColor(Color.BLUE);
		nameView.setIncludeFontPadding(false);
		textLayout.addView(nameView, textParams);
		// 学号
		TextView studentIdView = new TextView(mContext);
		studentIdView.setText(mUser.getStudentId());
		studentIdView.setTextSize(UIControl.personalTextData.h / 3 * 0.4f);
		studentIdView.setTextColor(Color.BLACK);
		studentIdView.setIncludeFontPadding(false);
		// studentIdView.setBackgroundColor(Color.RED);
		textLayout.addView(studentIdView, textParams);
		mLayout.addView(textLayout,
				UIControl.getLayoutParams(UIControl.personalTextData, 1));

		// 我的密码
		Button passwordBtn = new Button(mContext);
		passwordBtn.setBackgroundResource(R.drawable.personal_password);
		passwordBtn.setOnClickListener(passwordBtnClickListener);
		mLayout.addView(passwordBtn,
				UIControl.getLayoutParams(UIControl.personalPasswordData, 1));

		// 我的头像
		Button photoBtn = new Button(mContext);
		photoBtn.setBackgroundResource(R.drawable.personal_photo);
		photoBtn.setOnClickListener(photoBtnClickListener);
		mLayout.addView(photoBtn,
				UIControl.getLayoutParams(UIControl.personalBtnPhotoData, 1));

		// 我的发布
		Button releaseBtn = new Button(mContext);
		releaseBtn.setBackgroundResource(R.drawable.personal_release);
		releaseBtn.setOnClickListener(releaseBtnClickListener);
		mLayout.addView(releaseBtn,
				UIControl.getLayoutParams(UIControl.personalReleaseData, 1));

		// 我的学号
		Button studentIdBtn = new Button(mContext);
		studentIdBtn.setBackgroundResource(R.drawable.personal_student_id);
		mLayout.addView(studentIdBtn,
				UIControl.getLayoutParams(UIControl.personalStudentIdData, 1));

		// 个人中心
		Button personalBtn = new Button(mContext);
		personalBtn.setBackgroundResource(R.drawable.personal);
		mLayout.addView(personalBtn,
				UIControl.getLayoutParams(UIControl.personalBtnData, 1));
		
		//上滑菜单
		mImagePopupWindow=new ImagePopupWindow(mContext, null, mUser.getHeadPicture());
		
	}
	
	//我的密码按钮
	private OnClickListener passwordBtnClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			// 取得布局文件
			if (mChangePasswordLayout!=null&&(Boolean) mChangePasswordLayout.getTag()) {
				return;
			}
			final LinearLayout layout = (LinearLayout) MainActivity
					.getInstance().getLayoutInflater()
					.inflate(R.layout.change_password, null);
			mChangePasswordLayout = new RelativeLayout(mContext);
			mChangePasswordLayout.setGravity(Gravity.CENTER);
			LayoutParams params = new LayoutParams(
					(int) (UIControl.p.x * 0.8f), (int) (UIControl.p.y * 0.5));
			mChangePasswordLayout.addView(layout, params);
			mLayout.addView(mChangePasswordLayout, new LayoutParams(
					LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
			mChangePasswordLayout.setTag(true);//标志重置密码布局是否显示
			//动画
			ScaleAnimation animation=new ScaleAnimation(0, 1.0f, 0, 1.0f);
			animation.setDuration(500);
			layout.startAnimation(animation);
			// 修改密码逻辑
			// 返回按钮
			Button canelBtn = (Button) layout
					.findViewById(R.id.change_password_canel_btn);
			canelBtn.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					//动画
					ScaleAnimation animation=new ScaleAnimation(1.0f, 0, 1.0f,0);
					animation.setDuration(500);
					layout.startAnimation(animation);
					mLayout.removeView(mChangePasswordLayout);
					mChangePasswordLayout.setTag(false);
				}
			});
			// 确认按钮
			Button trueBtn = (Button) layout
					.findViewById(R.id.change_password_true_btn);
			trueBtn.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					EditText oldText = (EditText) layout
							.findViewById(R.id.old_password_edit);
					final EditText newText = (EditText) layout
							.findViewById(R.id.new_password_edit);
					final EditText confireText = (EditText) layout
							.findViewById(R.id.confir_password_edit);
					if (!newText.getText().toString()//两次密码不一致
									.equals(confireText.getText().toString())) {
								MyToast.showToast(MyToast.PASSWORD_MUSE_AGREEMENT);
								return;
					}
					if (oldText.getText().length()==0||newText.getText().length()==0||confireText.getText().length()==0) {
						MyToast.showToast(MyToast.PASSWORD_NULL);
						return;
					}
					mUser.setPassword(oldText.getText().toString());
					mUser.login(mContext, new SaveListener() {
						
						@Override
						public void onSuccess() {
							// TODO Auto-generated method stub
							mUser.setPassword(newText.getText().toString());

							mUser.update(mContext, new UpdateListener() {

								@Override
								public void onSuccess() {
									// TODO Auto-generated method stub
									MyToast.showToast(MyToast.CHANGE_PASSWORD_SUCCESS);
									mLayout
											.removeView(mChangePasswordLayout);
									mChangePasswordLayout.setTag(false);
									ScaleAnimation animation=new ScaleAnimation(1.0f, 0, 1.0f,0);
									animation.setDuration(500);
									layout.startAnimation(animation);

								}

								@Override
								public void onFailure(int arg0, String arg1) {
									// TODO Auto-generated method stub
									MyToast.showToast(MyToast.CHANGE_PASSWORD_FAIL);
								}
							});
						}
						
						@Override
						public void onFailure(int arg0, String arg1) {
							// TODO Auto-generated method stub
							MyToast.showToast(MyToast.OLD_PASSWORD_WRONG);
						}
					});
				}
			});
		}
	};

	//我的头像按钮
	private OnClickListener photoBtnClickListener=new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			//弹出上滑菜单
			mImagePopupWindow.showAtLocation(mLayout, Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0); 
			
		}
	};
	
	//我的发布按钮
	private OnClickListener releaseBtnClickListener=new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Builder builder=new Builder(mContext);
			builder.setTitle("请选择类型：");
			//失物招领的监听事件
			builder.setItems(new String[]{"失物招领","二手市场","取消"}, new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					switch (which) {
					case 0://失物招领
						LostLayout.setGoodsQuery(LostLayout.QUERYMY);
						MainLayout.getInstance().changeLayout(new LostLayout(mContext));
						break;
					case 1://二手市场
						OldLayout.setGoodsQuery(OldLayout.QUERYMY);
						MainLayout.getInstance().changeLayout(new OldLayout(mContext));
						break;
					case 2://取消
						break;
					default:
						break;
					}
					
				}
			});
			builder.create().show();
		}
	};
	
	/**
	 * 获取上滑菜单
	 * @return
	 */
	public ImagePopupWindow getImagePopupWindow() {
		return mImagePopupWindow;
	}
}
