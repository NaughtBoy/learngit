package com.dartou.lib.view.mydraw;

import java.lang.reflect.Method;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.RelativeLayout;
import android.widget.Scroller;

import com.dartou.lib.view.mydraw.BaseDrawerActivity.MyDrawerListener;
/**
 * 整个抽屉类
 * @author cyn
 */
public class SlidingMenu extends RelativeLayout {

	protected Scroller mScroller;// 控制主界面滑动的组件
	protected MenuLayout mLeftView;// 左边一级菜单
	protected int mLeftWidth;// 左边一级菜单宽度
	protected MenuLayout mLeftView2;// 左边二级级菜单
	protected int mLeftWidth2;// 左边二级菜单宽度
	protected MenuLayout mRightView;// 右边一级菜单
	protected int mRightWidth;// 右边一级菜单宽度
	protected MenuLayout mRightView2;// 右边二级级菜单
	protected int mRightWidth2;// 右边二级菜单宽度
	protected RelativeLayout menuView;// 菜单
	protected CentLayout mainView;// 主界面

	protected boolean isLeft = false;// 左菜单一级是否显示
	protected boolean isLeft2 = false;// 左菜单二级一级是否显示
	protected boolean leftState = true;// 当前左菜单是一级还是二级,true为一级

	protected boolean isRight = false;// 右菜单一级一级是否显示
	protected boolean isRight2 = false;// 右菜单二级一级是否显示
	protected boolean rightState = true;// 当前右菜单是一级还是二级,true为一级
	protected int screen_width;
	
	protected  float scrollV;
	
	protected int menu_state = 0;//状态，-1表示左菜单打开 0表示无菜单 1表示右菜单打开

	public SlidingMenu(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		// mScroller = new Scroller(context);
//		mScroller = new Scroller(context, new LinearInterpolator());
		
		mScroller = new Scroller(context, new AccelerateDecelerateInterpolator());

		menuView = new RelativeLayout(context, attrs);

		mainView = new CentLayout(context, attrs, this) {
			@Override
			public void computeScroll() {
				if (mScroller.computeScrollOffset()) {
					scrollTo(mScroller.getCurrX(), 0);
					postInvalidate();
				}
			}
		};
		addView(menuView, new LayoutParams(LayoutParams.FILL_PARENT,
				LayoutParams.FILL_PARENT));
		addView(mainView, new LayoutParams(LayoutParams.FILL_PARENT,
				LayoutParams.FILL_PARENT));

		Display display = ((WindowManager) getContext().getSystemService(
				Context.WINDOW_SERVICE)).getDefaultDisplay();
		try {
			Class<?> cls = Display.class;
			Class<?>[] parameterTypes = { Point.class };
			Point parameter = new Point();
			Method method = cls.getMethod("getSize", parameterTypes);
			method.invoke(display, parameter);
			screen_width = parameter.x;
		} catch (Exception e) {
			screen_width = display.getWidth();
		}
		
		scrollV = screen_width/200f;
	}

	/**
	 * 设置一级左菜单
	 * 
	 * @param left
	 *            控件
	 * @param leftWidth
	 *            宽度
	 */
	public void setLeftMenu(MenuLayout left, int leftWidth) {
		mLeftView = left;
		mLeftWidth = leftWidth;
		mLeftView.setVisibility(View.GONE);
		menuView.addView(mLeftView, new LayoutParams(mLeftWidth,
				LayoutParams.FILL_PARENT));
	}

	/**
	 * 设置二级左菜单
	 * 
	 * @param left
	 *            控件
	 * @param leftWidth
	 *            宽度
	 */
	public void setLeftMenu2(MenuLayout left2, int leftWidth2) {
		mLeftView2 = left2;
		mLeftWidth2 = leftWidth2;
		menuView.addView(mLeftView2, new LayoutParams(mLeftWidth2,
				LayoutParams.FILL_PARENT));
		mLeftView2.setVisibility(View.GONE);
	}

	/**
	 * 设置一级右菜单
	 * 
	 * @param left
	 *            控件
	 * @param leftWidth
	 *            宽度
	 */
	public void setRightMenu(MenuLayout right, int rightWidth) {
		mRightView = right;
		mRightWidth = rightWidth;
		LayoutParams lp = new LayoutParams(mRightWidth,
				LayoutParams.FILL_PARENT);
		lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		menuView.addView(mRightView, lp);
		mRightView.setVisibility(View.GONE);
	}

	/**
	 * 设置二级右菜单
	 * 
	 * @param left
	 *            控件
	 * @param leftWidth
	 *            宽度
	 */
	public void setRightMenu2(MenuLayout right2, int rightWidth2) {
		mRightView2 = right2;
		mRightWidth2 = rightWidth2;
		LayoutParams lp = new LayoutParams(mRightWidth2,
				LayoutParams.FILL_PARENT);
		lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		menuView.addView(mRightView2, lp);
		mRightView2.setVisibility(View.GONE);
		
	}

	/**
	 * 显示左菜单
	 */
	public void showLeft(){
		showLeft(true);
		
	}
	/**
	 * 显示左菜单
	 */
	protected void showLeft(boolean isAnim) {
		if (isAnim) {
			int duration ;
			if (leftState) {
				if(mLeftView==null){
					return;
				}
//				mLeftView.bringToFront();
				mLeftView.setVisibility(View.VISIBLE);
				
				duration = (int) (mLeftWidth/scrollV);
//				duration = 2000;
				mScroller.startScroll(0, 0, -mLeftWidth, 0, duration);
				mainView.postInvalidate();
				isLeft = true;
				
				mLeftView.derectOpen(duration);
			} else {
				if(mLeftView2==null){
					return;
				}
//				mLeftView2.bringToFront();
				mLeftView2.setVisibility(View.VISIBLE);
				duration = (int) (mLeftWidth2/scrollV);
				mLeftView2.derectOpen(duration);
				mScroller.startScroll(0, 0, -mLeftWidth2, 0, duration);
				mainView.postInvalidate();
				isLeft2 = true;
			}
			
			if(mLeftMyDrawerListener!=null){
				mLeftMyDrawerListener.derectOpen(duration);
			}
			mainView.setClickable(false);
			menu_state = -1;
		} else {
			if (leftState) {
				if(mLeftView==null){
					return;
				}
//				mLeftView.bringToFront();
				mLeftView.setVisibility(View.VISIBLE);
				mainView.postInvalidate();
				isLeft = true;
			} else {
				if(mLeftView2==null){
					return;
				}
//				mLeftView2.bringToFront();
				mLeftView2.setVisibility(View.VISIBLE);
				mainView.postInvalidate();
				isLeft2 = true;
			}
		}

	}
	
	public void setLeftVislble(boolean flag){
		
		if(flag){
			if (leftState) {
				if(mLeftView==null){
					return;
				}
				mLeftView.setVisibility(View.VISIBLE);
				mainView.postInvalidate();
			} else {
				if(mLeftView2==null){
					return;
				}
				mLeftView2.setVisibility(View.VISIBLE);
				mainView.postInvalidate();
			}
		}else{
			if (leftState) {
				if(mLeftView==null){
					return;
				}
				mLeftView.clearAnimation();
				mLeftView.setVisibility(View.GONE);
				mainView.postInvalidate();
			} else {
				if(mLeftView2==null){
					return;
				}
				mLeftView2.clearAnimation();
				mLeftView2.setVisibility(View.GONE);
				mainView.postInvalidate();
			}
		}
	}
	
	public void setRightVislble(boolean flag){
		
		if(flag){
			if (rightState) {
				if(mRightView==null){
					return;
				}
				mRightView.setVisibility(View.VISIBLE);
				mainView.postInvalidate();
			} else {
				if(mRightView2==null){
					return;
				}
				mRightView2.setVisibility(View.VISIBLE);
				mainView.postInvalidate();
			}
		}else{
			if (rightState) {
				if(mRightView==null){
					return;
				}
				mRightView.clearAnimation();
				mRightView.setVisibility(View.GONE);
				mainView.postInvalidate();
			} else {
				if(mRightView2==null){
					return;
				}
				mRightView2.clearAnimation();
				mRightView2.setVisibility(View.GONE);
				mainView.postInvalidate();
			}
		}
	}

	/**
	 * 显示右菜单
	 */
	public void showRight(){
		showRight(true);
		
	}
	
	/**
	 * 显示右菜单
	 */
	protected void showRight(boolean isAnim) {
		if (isAnim) {
			int duration;
			if (rightState) {
				if(mRightView==null){
					return;
				}
//				mRightView.bringToFront();
				mRightView.setVisibility(View.VISIBLE);
				duration = (int) (mRightWidth/scrollV);
				mRightView.derectOpen(duration);
				mScroller.startScroll(0, 0, mRightWidth, 0, duration);
				mainView.postInvalidate();
				isRight = true;
			} else {
				if(mRightView2==null){
					return;
				}
//				mRightView2.bringToFront();
				mRightView2.setVisibility(View.VISIBLE);
				duration =(int) (mRightWidth2/scrollV);
				mRightView2.derectOpen(duration);
				mScroller.startScroll(0, 0, mRightWidth2, 0, duration);
				mainView.postInvalidate();
				isRight2 = true;
			}
			if(mRightMyDrawerListener!=null){
				mRightMyDrawerListener.derectOpen(duration);
			}
			mainView.setClickable(false);
			menu_state = 1;
		} else {
			if (rightState) {
				if(mRightView==null){
					return;
				}
//				mRightView.bringToFront();
				mRightView.setVisibility(View.VISIBLE);
				mainView.postInvalidate();
				isRight = true;
			} else {
				if(mRightView2==null){
					return;
				}
//				mRightView2.bringToFront();
				mRightView2.setVisibility(View.VISIBLE);
				mainView.postInvalidate();
				isRight2 = true;
			}
		}

	}
	
	Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 1://打开左二级菜单
				mScroller.startScroll(-mLeftWidth, 0,
						-(mLeftWidth2 - mLeftWidth), 0, msg.arg1);
				mainView.postInvalidate();
				mLeftView.setVisibility(View.GONE);
				break;
			case 2://打开右二级菜单
				mScroller.startScroll(mRightWidth, 0,
						(mRightWidth2 - mRightWidth), 0, msg.arg1);
				mainView.postInvalidate();
				mRightView.setVisibility(View.GONE);
				break;
			case 3://关闭左二级菜单
				break;
			case 4://关闭右二级菜单
				break;

			default:
				break;
			}
		};
	};

	/**
	 * 显示二级左菜单
	 */
	public void showLeft2() {

		
		final int duration = (int) ((mLeftWidth2 - mLeftWidth) /scrollV);
		
		
		Message msg = handler.obtainMessage();
		msg.what = 1;
		msg.arg1 = duration;
		handler.sendMessageDelayed(msg, (int) (mLeftWidth2/scrollV) - duration + 30);
//		new Handler().postDelayed(new Runnable() {
//
//			@Override
//			public void run() {
//				mScroller.startScroll(-mLeftWidth, 0,
//						-(mLeftWidth2 - mLeftWidth), 0, duration);
//				mainView.postInvalidate();
//				mLeftView.setVisibility(View.GONE);
//			}
//		}, (int) (mLeftWidth2/scrollV) - duration + 30);

		isLeft2 = true;
		
		mLeftView2.setVisibility(View.VISIBLE);
		TranslateAnimation translateAnimation = new TranslateAnimation(
				Animation.RELATIVE_TO_SELF, -1.0f, Animation.RELATIVE_TO_SELF,
				0, Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0);
		translateAnimation.setDuration((int) (mLeftWidth2/scrollV));
		translateAnimation.setInterpolator(new LinearInterpolator());
		mLeftView2.startAnimation(translateAnimation);

		leftState = false;
		mainView.setClickable(false);
	}

	/**
	 * 显示二级右菜单
	 */
	public void showRight2() {

		final int duration = (int) ((mRightWidth2 - mRightWidth) / scrollV);
		
		
		Message msg = handler.obtainMessage();
		msg.what = 2;
		msg.arg1 = duration;
		handler.sendMessageDelayed(msg, (int) (mRightWidth2/scrollV) - duration + 30);
//		new Handler().postDelayed(new Runnable() {
//
//			@Override
//			public void run() {
//				mScroller.startScroll(mRightWidth, 0,
//						(mRightWidth2 - mRightWidth), 0, duration);
//				mainView.postInvalidate();
//				mRightView.setVisibility(View.GONE);
//			}
//		}, (int) (mRightWidth2/scrollV) - duration + 30);

		isRight2 = true;

		mRightView2.setVisibility(View.VISIBLE);
		TranslateAnimation translateAnimation = new TranslateAnimation(
				Animation.RELATIVE_TO_SELF, 1.0f, Animation.RELATIVE_TO_SELF,
				0, Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0);
		translateAnimation.setDuration((int) (mRightWidth2/scrollV));
		translateAnimation.setInterpolator(new LinearInterpolator());
		mRightView2.startAnimation(translateAnimation);

		rightState = false;
		mainView.setClickable(false);
	}

	/**
	 * 关闭二级左菜单
	 * @param run 
	 */
	public void closeLeft2(){
		closeLeft2(null);
	}
	
	/**
	 * 关闭二级左菜单
	 * @param run 
	 */
	protected void closeLeft2(final Runnable run) {
		int duration = (int) (mLeftWidth2/scrollV);
		mScroller.startScroll(-mLeftWidth2, 0, mLeftWidth2, 0, duration);
		mainView.postInvalidate();
		isLeft2 = false;
		leftState = false;
		mainView.setClickable(true);

		isLeft = false;

		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {
				if(run!=null){
					run.run();
				}
				mLeftView2.setVisibility(View.GONE);
				mLeftView.setVisibility(View.GONE);
			}
		}, duration);
		
		menu_state = 0;
		
		if(mLeftMyDrawerListener!=null){
			mLeftMyDrawerListener.derectClose(duration);
		}
	}

	/**
	 * 关闭二级右菜单
	 * @param run 
	 */
	public void closeRight2(){
		closeRight2(null);
	}
	
	/**
	 * 关闭二级右菜单
	 * @param run 
	 */
	protected void closeRight2(final Runnable run) {
		int duration = (int) (mRightWidth/scrollV);
		mScroller.startScroll(mRightWidth2, 0, -mRightWidth2, 0, duration);
		mainView.postInvalidate();
		isRight2 = false;
		rightState = false;
		mainView.setClickable(true);

		isRight = false;

		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {
				if(run!=null){
					run.run();
				}
				mRightView2.setVisibility(View.GONE);
				mRightView.setVisibility(View.GONE);
			}
		}, duration);
		menu_state = 0;
		
		if(mRightMyDrawerListener!=null){
			mRightMyDrawerListener.derectClose(duration);
		}
	}

	/**
	 * 返回一级左菜单
	 */
	public void backLeft() {
		handler.removeMessages(1);
		mLeftView.setVisibility(View.VISIBLE);
		final int duration = (int) ((mLeftWidth2 - mLeftWidth) / scrollV);
		
		mScroller.startScroll(-mLeftWidth2, 0, (mLeftWidth2 - mLeftWidth), 0,
				duration);
		mainView.postInvalidate();
		isLeft2 = false;

		TranslateAnimation translateAnimation = new TranslateAnimation(
				Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF,
				-1.0f, Animation.RELATIVE_TO_SELF, 0,
				Animation.RELATIVE_TO_SELF, 0);
		translateAnimation.setDuration((int) (mLeftWidth2/scrollV));
		translateAnimation.setInterpolator(new LinearInterpolator());
		mLeftView2.startAnimation(translateAnimation);
		translateAnimation.setAnimationListener(new AnimationListener() {

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
				mLeftView2.setVisibility(View.GONE);
			}
		});
		leftState = true;

		isLeft = true;
	}

	/**
	 * 返回一级右菜单
	 */
	public void backRight() {
		handler.removeMessages(2);
		mRightView.setVisibility(View.VISIBLE);
		final int duration = (int) ((mRightWidth2 - mRightWidth) / scrollV);

		mScroller.startScroll(mRightWidth2, 0, -(mRightWidth2 - mRightWidth),
				0, duration);
		mainView.postInvalidate();
		isRight2 = false;

		TranslateAnimation translateAnimation = new TranslateAnimation(
				Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF,
				1.0f, Animation.RELATIVE_TO_SELF, 0.0f,
				Animation.RELATIVE_TO_SELF, 0);
		translateAnimation.setDuration((int) (mRightWidth2/scrollV));
		translateAnimation.setInterpolator(new LinearInterpolator());
		mRightView2.startAnimation(translateAnimation);
		translateAnimation.setAnimationListener(new AnimationListener() {

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
				mRightView2.setVisibility(View.GONE);
			}
		});
		rightState = true;

		isRight = true;
	}
	
	/**
	 * 关闭菜单
	 */
	public void showCenter(){
		if(menu_state==-1){
			showCenter(true,null);
			closeInputMethodManager();
		}else if(menu_state==0){
			
		}else if(menu_state==1){
			showCenter(false,null);
			closeInputMethodManager();
		}
	}
	
	
	/**
	 * 关闭菜单
	 * @param isLeft
	 * @param run 
	 */
	public void showCenter(boolean isLeft){
		showCenter(isLeft,null);
	}

	/**
	 * 关闭菜单
	 * 
	 * @param isLeft
	 * @param run 
	 */
	protected void showCenter(boolean isLeft, final Runnable run) {
		menu_state = 0;
		final MenuLayout v;
		int w ;
		if (isLeft) {
			if(leftState){
				v = mLeftView;
				w = mLeftWidth;
			}else{
				v = mLeftView2;
				w = mLeftWidth2;
			}
			
			int duration = (int) (w/scrollV);
			mScroller.startScroll(-w, 0, w, 0, duration);
			if(mLeftMyDrawerListener!=null){
				mLeftMyDrawerListener.derectClose(duration);
			}
			v.derectClose(duration);
		} else {
			if(rightState){
				v = mRightView;
				w = mRightWidth;
			}else{
				v = mRightView2;
				w = mRightWidth2;
			}
//			int duration = 2000;
			int duration = (int) (w/scrollV);
			mScroller.startScroll(w, 0, -w, 0, duration);
			if(mRightMyDrawerListener!=null){
				mRightMyDrawerListener.derectClose(duration);
			}
			v.derectClose(duration);
		}

		mainView.postInvalidate();

		mainView.setClickable(true);

		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {
				if(v!=null){
					v.setVisibility(View.GONE);
				}
				if(run!=null){
					run.run();
				}
			}
		}, (int) (w/scrollV));
	}

	/**
	 * 设置中间主界面
	 * 
	 * @param center
	 */
	public void setCenter(View center) {
		mainView.addView(center, new LayoutParams(LayoutParams.FILL_PARENT,
				LayoutParams.FILL_PARENT));
		// mainView.setClickable(true);
	}

	public void handler(MotionEvent ev,Runnable run) {
		if (isLeft2 && ev.getX() >= mLeftWidth2) {
			closeLeft2(run);
			// return true;
		} else if (isLeft && ev.getX() >= mLeftWidth) {
			showCenter(true,run);
			isLeft = false;
			// return true;
		} else if (isRight2 && ev.getX() <= screen_width - mRightWidth2) {
			closeRight2(run);
			// return true;
		} else if (isRight && ev.getX() <= screen_width - mRightWidth) {
			showCenter(false,run);
			isRight = false;
			// return true;
		}
		closeInputMethodManager();
	}

	InputMethodManager im;
	public void closeInputMethodManager(){
		
		Activity activity = (Activity)getContext();
		if(im==null){
			im = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
		}
		if(activity.getCurrentFocus()!=null&&im!=null){
			im.hideSoftInputFromWindow(activity.getCurrentFocus()
					.getWindowToken(), InputMethodManager.RESULT_UNCHANGED_SHOWN);
		}
		
	}
	
	MyDrawerListener mLeftMyDrawerListener,mRightMyDrawerListener;
	public void setLeftListener(MyDrawerListener listner){
		mLeftMyDrawerListener = listner;
	}
	
	
	public void setRightListener(MyDrawerListener listner){
		mRightMyDrawerListener = listner;
	}
	
	public void setCanScroll(boolean isScroll){
		mainView.setCanScroll(isScroll);
	}


}
