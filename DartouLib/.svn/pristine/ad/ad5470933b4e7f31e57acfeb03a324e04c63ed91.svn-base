package com.dartou.lib.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
/**
 * 带点击效果的按钮
 * @author cyn
 */
public class MyButton extends Button{

	private AlphaAnimation ap;
	private AlphaAnimation apOut;
	private boolean isCliceEffect = true;
	public MyButton(Context context, AttributeSet attrs) {
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
					clearAnimation();
					if(getVisibility()==View.VISIBLE){
						startAnimation(apOut);
					}
					break;
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
	
//	@Override
//	public void setBackgroundResource(int resid) {
//		
//		Bitmap[] bit = BitmapControl.getInstance().getBitmaps(getContext(), resid);
//		
//		StateListDrawable sd = new StateListDrawable();
//		BitmapDrawable press = new BitmapDrawable(bit[1]);
//		BitmapDrawable normal = new BitmapDrawable(bit[0]);
//		 
//		sd.addState(new int[]{android.R.attr.state_enabled, android.R.attr.state_focused}, normal);  
//        sd.addState(new int[]{android.R.attr.state_pressed, android.R.attr.state_enabled}, press);  
//        sd.addState(new int[]{android.R.attr.state_focused}, normal);  
//        sd.addState(new int[]{android.R.attr.state_pressed}, press);  
//        sd.addState(new int[]{android.R.attr.state_enabled}, normal);  
//        sd.addState(new int[]{}, normal); 
//        super.setBackgroundDrawable(sd);
//	}
//	
//	public void setBackgroundResourceById(int resid){
//		super.setBackgroundResource(resid);
//	}
	
}
