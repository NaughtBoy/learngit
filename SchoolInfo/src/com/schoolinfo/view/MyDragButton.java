package com.schoolinfo.view;

import com.schoolinfo.MainActivity;
import com.schoolinfo.R;
import com.schoolinfo.control.GlobalControl;
import com.schoolinfo.control.UIControl;
import com.schoolinfo.layout.MainGalleryLayout;
import com.schoolinfo.layout.MainLayout;
import com.schoolinfo.layout.RunLayout;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;

/**
 * 可以拖动到按钮
 * 
 * @author Administrator 在继承时需设置背景图片（bgBitmap），初始的坐标（lastX，lastY）
 */
public class MyDragButton extends Button {
	private int lastX;
	private int lastY;
	private int fristX;// 按下时的坐标
	private int fristY;
	// 用于判断是否为点击事件
	int radius;// 悬浮球的半径

	public MyDragButton(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init();
	}

	/**
	 * 初始化按钮
	 */
	public void init() {
		this.setBackgroundResource(R.drawable.drag_bg);
		radius = UIControl.p.y / 15;
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
				radius, radius);
		params.topMargin = UIControl.p.y / 2;
		params.leftMargin = 0;
		this.setLayoutParams(params);

		this.setClickable(true);
	}

	@Override
	public boolean onTouchEvent(final MotionEvent event) {
		// TODO Auto-generated method stub
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:// 点击时变淡
			Animation alpAnimation = new AlphaAnimation(1.0f, 0.5f);
			alpAnimation.setDuration(0);
			alpAnimation.setFillAfter(true);
			this.startAnimation(alpAnimation);
			lastX = (int) event.getRawX();
			lastY = (int) event.getRawY();
			fristX = lastX;
			fristY = lastY;
			break;
		case MotionEvent.ACTION_UP:// 放开后透明度复原并自动位移至边界
			// 判断是否为点击事件
			if (Math.abs(fristX - event.getRawX()) < radius / 4
					&& Math.abs(fristY - event.getRawY()) < radius / 4 	) {
				// 处理点击事件
				onClickEvent();
				break;
			}

			final boolean isRight;
			int tranlationX;// X方向的位移量
			if ((event.getRawX() + radius / 2) > UIControl.p.x / 2) {// 按钮依靠到右边
				tranlationX = (int) (UIControl.p.x
						- (event.getRawX() - event.getX()) - radius);
				isRight = true;
			} else {
				tranlationX = (int) (0 - event.getRawX() + event.getX());
				isRight = false;
			}
			// 动画
			AnimationSet set = new AnimationSet(true);
			Animation alpAnimation1 = new AlphaAnimation(0.5f, 1.0f);
			alpAnimation1.setDuration(400);
			set.addAnimation(alpAnimation1);
			Animation traAnimation = new TranslateAnimation(0, tranlationX, 0,
					0);
			traAnimation.setDuration(400);
			set.addAnimation(traAnimation);
			set.setFillAfter(true);
			set.setAnimationListener(new AnimationListener() {

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
					MyDragButton.this.clearAnimation();
					RelativeLayout.LayoutParams rParams = new RelativeLayout.LayoutParams(
							radius, radius);
					rParams.topMargin = getTop();
					if (isRight) {
						rParams.leftMargin = UIControl.p.x - radius;

					} else {
						rParams.leftMargin = 0;
					}
					MyDragButton.this.setLayoutParams(rParams);
				}
			});
			this.startAnimation(set);

			break;
		case MotionEvent.ACTION_MOVE:
			int dx = (int) event.getRawX() - lastX;
			int dy = (int) event.getRawY() - lastY;
			int top = getTop() + dy;

			int left = getLeft() + dx;
			// 边界
			if (top <= 0) {
				top = 0;
			}
			if (top >= UIControl.p.y - radius) {
				top = (int) (UIControl.p.y - radius);
			}
			if (left >= UIControl.p.x - radius) {
				left = UIControl.p.x - radius;
			}

			if (left <= 0) {
				left = 0;
			}

			layout(left, top, left + radius, top + radius);
			lastX = (int) event.getRawX();
			lastY = (int) event.getRawY();
			// System.out.println("(RawX,Y)=("+lastX+","+lastY+")");
			break;
		default:
			break;
		}
		return true;
	}

	// 点击事件
	private void onClickEvent() {
		// TODO Auto-generated method stub
		this.clearAnimation();
		MainLayout.getInstance().showGalery();
	}

}
