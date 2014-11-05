package com.dartou.lib.anim;

import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

/**
 * 抛物线动画
 * @author cyn
 */
public class ParabolaAnimtion {

	
	public long time;
	public Animation animStart,animEnd;
	
	public View view;
	
	/**
	 * 
	 * @param v
	 * @param fromX 起点x
	 * @param fromY 起点y
	 * @param topX 终点x
	 * @param topY 终点y
	 * @param toY 顶点y
	 */
	public ParabolaAnimtion(final View v, final int fromX, final int fromY, final int topX,
			int topY,final int toY) {
		
		
		
		RelativeLayout.LayoutParams lp = (LayoutParams) v.getLayoutParams();
		lp.leftMargin = fromX;
		lp.topMargin = fromY;
		lp.bottomMargin = -fromY;
		v.setLayoutParams(lp);
		AlphaAnimation alphaAnimationOut = new AlphaAnimation(0, 1.0f);
		alphaAnimationOut.setDuration(100);
		alphaAnimationOut.setStartOffset((long) (Math.random()*500+100));
		alphaAnimationOut.setFillAfter(true);

		final AnimationSet set = new AnimationSet(false);
		TranslateAnimation translateAnimationX = new TranslateAnimation(0,
				topX-fromX, 0, 0);
//		TranslateAnimation translateAnimationX = new TranslateAnimation(Animation.ABSOLUTE, 0, Animation.ABSOLUTE , topX-fromX, Animation.ABSOLUTE, 0,Animation.ABSOLUTE, 0);
		translateAnimationX.setInterpolator(new LinearInterpolator());
//		TranslateAnimation translateAnimationY = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0,Animation.RELATIVE_TO_SELF, topY-fromY);
		TranslateAnimation translateAnimationY = new TranslateAnimation(0, 0,
				0, topY-fromY);
		translateAnimationY.setInterpolator(new DecelerateInterpolator());
		set.addAnimation(translateAnimationY);
		set.addAnimation(translateAnimationX);
		set.setDuration(500);
		set.setFillAfter(true);
		
		AnimationSet set1 = new AnimationSet(false);
		TranslateAnimation translateAnimationX1 = new TranslateAnimation(topX-fromX,
					(topX - fromX) * 2, 0, 0);
		
//		TranslateAnimation translateAnimationX1 = new TranslateAnimation(Animation.ABSOLUTE, topX, Animation.ABSOLUTE, fromX+(topX - fromX) * 2, Animation.ABSOLUTE, 0,Animation.ABSOLUTE, 0);
		
		translateAnimationX1.setInterpolator(new LinearInterpolator());
		
		TranslateAnimation translateAnimationY1 = new TranslateAnimation(0, 0,
				topY-fromY, toY-topY);
		
//		TranslateAnimation translateAnimationY1 = new TranslateAnimation(Animation.ABSOLUTE, 0, Animation.ABSOLUTE, 0, Animation.ABSOLUTE, topY,Animation.ABSOLUTE, toY);
		translateAnimationY1.setInterpolator(new AccelerateInterpolator());
		set1.addAnimation(translateAnimationY1);
		set1.addAnimation(translateAnimationX1);
		set1.setDuration(500);
		set1.setFillAfter(true);

//		final AlphaAnimation alphaAnimationIn = new AlphaAnimation(1.0f, 0);
//		alphaAnimationIn.setDuration(300);
//		alphaAnimationIn.setFillAfter(true);
//		
		alphaAnimationOut.setAnimationListener(new AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation animation) {
				v.setVisibility(View.VISIBLE);
			}
			
			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationEnd(Animation animation) {
				v.startAnimation(set);
				
			}
		});
//		alphaAnimationOut.setAnimationListener(new ParaAnim(v, set));
		set.setAnimationListener(new ParaAnim(v, set1));
//		set1.setAnimationListener(new ParaAnim(v, alphaAnimationIn));
		
		set1.setAnimationListener(new AnimationListener() {
			
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
//				RelativeLayout.LayoutParams lp = (LayoutParams) v.getLayoutParams();
//				lp.leftMargin = (topX - fromX) * 2+fromX;
//				lp.topMargin = toY-fromY;
//				v.setLayoutParams(lp);
//				v.startAnimation(alphaAnimationIn);
				
				v.setVisibility(View.GONE);
//				v.clearAnimation();
//				
//				ViewGroup parent = (ViewGroup)(view.getParent());
//				if(parent!=null){
//					parent.removeView(v);
//				}
			}
		});
		
//		alphaAnimationIn.setAnimationListener(new AnimationListener() {
//			
//			@Override
//			public void onAnimationStart(Animation animation) {
//				// TODO Auto-generated method stub
//				
//			}
//			
//			@Override
//			public void onAnimationRepeat(Animation animation) {
//				
//				
//			}
//			
//			@Override
//			public void onAnimationEnd(Animation animation) {
//				v.setVisibility(View.GONE);
//			}
//		});
		
		
		view = v;
		time = getTime(alphaAnimationOut)+getTime(set)+getTime(set1);
		animStart = alphaAnimationOut;
		animEnd = set1;
	}
	
	/**
	 * 结束
	 * @param run
	 */
	public void setAnimListner(final Runnable run){
		if(run!=null){
			animEnd.setAnimationListener(new AnimationListener() {
				
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
					run.run();
//					ViewGroup parent = (ViewGroup)(view.getParent());
//					if(parent!=null){
//						parent.removeAllViews();
//					}
					
					view.setVisibility(View.GONE);
//					view.clearAnimation();
//					
//					ViewGroup parent = (ViewGroup)(view.getParent());
//					if(parent!=null){
//						parent.removeView(view);
//					}
				}
			});
		}
	}
	
	/**
	 * 启动
	 */
	public void start(){
		view.startAnimation(animStart);
	}
	
	/**
	 * 获取总时间
	 * @return
	 */
	public long getAnimTime(){
		return time;
	}
	
	private long getTime(Animation anim){
		return anim.getStartOffset()+anim.getDuration();
	}

	class ParaAnim implements AnimationListener {
		View view;
		Animation animation;

		public ParaAnim(View view, Animation animation) {
			this.animation = animation;
			this.view = view;
		}

		@Override
		public void onAnimationEnd(Animation animation) {
			view.startAnimation(this.animation);
		}

		@Override
		public void onAnimationRepeat(Animation animation) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onAnimationStart(Animation animation) {

		}

	}

}
