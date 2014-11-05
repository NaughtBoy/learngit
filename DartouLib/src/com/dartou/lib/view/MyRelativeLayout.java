package com.dartou.lib.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.RelativeLayout;
/**
 * 带点击效果的相对布局
 * @author cyn
 */
public class MyRelativeLayout extends RelativeLayout{

	AlphaAnimation ap;
	AlphaAnimation apOut;
	private boolean isCliceEffect = true;
	public MyRelativeLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		ap = new AlphaAnimation(1.0f, 0.32f);
		ap.setDuration(0);
		ap.setFillAfter(true);
		
		apOut = new AlphaAnimation(0.32f, 1f);
		apOut.setDuration(0);
		apOut.setFillAfter(true);
		
		setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					if(isCliceEffect){
						startAnimation(ap);
					}
					break;
				case MotionEvent.ACTION_CANCEL:
				case MotionEvent.ACTION_OUTSIDE:
				case MotionEvent.ACTION_UP:
					if(isCliceEffect){
						clearAnimation();
						if(getVisibility()==View.VISIBLE){
							startAnimation(apOut);
						}
					}
					return false;
				default:
					break;
				}
				return false;
			}
		});
	}
	
	@Override
	public void setClickable(boolean clickable) {
		// TODO Auto-generated method stub
		super.setClickable(clickable);
		setIsClickEffect(clickable);
	}
	
	private void setIsClickEffect(boolean isClickEffect){
		this.isCliceEffect = isClickEffect;
	}
	
}
