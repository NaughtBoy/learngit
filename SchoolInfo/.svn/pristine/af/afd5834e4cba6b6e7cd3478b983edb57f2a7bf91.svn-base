package com.schoolinfo.view;

import android.content.Context;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;

/**
 * 实现了点击效果的按钮
 * @author huangwubin 2014-9-24
 */
public class MyButton extends Button {

	private boolean isClick=false;
	public MyButton(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		clickAnimation=new AlphaAnimation(1.0f, 0.8f);
		clickAnimation.setFillAfter(true);
		clickAnimation.setDuration(0);
		
		this.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				isClick=!isClick;
				if (isClick) {
					MyButton.this.setAnimation(clickAnimation);
				}
				else {
					MyButton.this.clearAnimation();
				}
			}
		});
	}
	private Animation clickAnimation;
	
}
