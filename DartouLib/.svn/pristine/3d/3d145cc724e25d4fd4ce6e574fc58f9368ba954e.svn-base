package com.dartou.lib.view.mydraw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;
/**
 * 抽屉菜单类
 * @author cyn
 */
public class MenuLayout extends RelativeLayout{

	public MenuLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		p = new Paint();
	}

	private static final float SCALE = 0.8f;
	private static final float ALPHA = 0.2f;
	private float x = 1.0f, y = 1.0f, a=1.0f;
	public void setScale(float x){
		this.x = (1-SCALE)*x+SCALE;
		this.y = (1-SCALE)*x+SCALE;
		
		AlphaAnimation alphaAnimation = new AlphaAnimation(a, (1-ALPHA)*x+ALPHA);
		alphaAnimation.setDuration(0);
		alphaAnimation.setFillAfter(true);
		startAnimation(alphaAnimation);
		this.a = (1-ALPHA)*x+ALPHA;
		
		
		
		invalidate();
	}
	
	/**
	 * up时打开动画
	 * @param time
	 */
	public void open(int time){
		AnimationSet anim = new AnimationSet(getContext(), null);
		ScaleAnimation scaleAnimation = new ScaleAnimation(x, 1.0f, y, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		AlphaAnimation alphaAnimation = new AlphaAnimation(a, 1.0f);
		anim.addAnimation(scaleAnimation);
		anim.addAnimation(alphaAnimation);
		scaleAnimation.setDuration(time);
		alphaAnimation.setDuration(time);
		anim.setDuration(time);
		anim.setInterpolator(new LinearInterpolator());
		startAnimation(anim);
		refreshData();
		x = 1.0f;
		y = 1.0f;
		a=1.0f;
	}
	
	/**
	 * up时关闭动画
	 * @param time
	 */
	public void close(int time){
		AnimationSet anim = new AnimationSet(getContext(), null);
		ScaleAnimation scaleAnimation = new ScaleAnimation(x, SCALE, y, SCALE, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		AlphaAnimation alphaAnimation = new AlphaAnimation(a, ALPHA);
		x = 1.0f;
		y = 1.0f;
		a = 1.0f;
		anim.addAnimation(scaleAnimation);
		anim.addAnimation(alphaAnimation);
		anim.setDuration(time);
		anim.setInterpolator(new LinearInterpolator());
		startAnimation(anim);
		anim.setAnimationListener(new AnimationListener() {
			
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
				setVisibility(View.GONE);
			}
		});
	}
	
	/**
	 * 直接打开
	 * @param time
	 */
	public void derectOpen(int time){
		AnimationSet anim = new AnimationSet(getContext(), null);
		ScaleAnimation scaleAnimation = new ScaleAnimation(SCALE, 1.0f, SCALE, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		AlphaAnimation alphaAnimation = new AlphaAnimation(ALPHA, 1.0f);
		anim.addAnimation(scaleAnimation);
		anim.addAnimation(alphaAnimation);
		anim.setDuration(time);
		anim.setInterpolator(new LinearInterpolator());
		startAnimation(anim);
		refreshData();
		x = 1.0f;
		y = 1.0f;
		a=1.0f;
	}
	
	public void derectClose(int time){
		AnimationSet anim = new AnimationSet(getContext(), null);
		ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, SCALE, 1.0f, SCALE, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, ALPHA);
		anim.addAnimation(scaleAnimation);
		anim.addAnimation(alphaAnimation);
		anim.setDuration(time);
		anim.setInterpolator(new LinearInterpolator());
		anim.setAnimationListener(new AnimationListener() {
			
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
				setVisibility(View.GONE);
			}
		});
		startAnimation(anim);
		
	}
	
	public void refreshData(){
		
	}
	
	Paint p;
	
	@Override
	protected void dispatchDraw(Canvas canvas) {
		canvas.save();
		canvas.scale(x, y,getWidth()/2,getHeight()/2);
//		canvas.translate(-(1-x)*getWidth(), 0);
		onSetAlpha((int) (a*255));
//		p.setARGB(255, 255, 0,0);
//		canvas.drawPaint(p);
		super.dispatchDraw(canvas);
		canvas.restore();
		
	}
}
