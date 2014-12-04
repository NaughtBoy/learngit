package com.schoolinfo.layout;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapView;
import com.schoolinfo.MainActivity;
import com.schoolinfo.control.UIControl;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * 
 * @author huangwubin 2014-12-4
 */
public class MapLayout extends BaseLayout {

	private MapView mapView;
	public MapLayout(Context context) {
		// TODO Auto-generated constructor stub
		super.mLayout = new RelativeLayout(context);
		super.mRunLayout = RunLayout.MAPLAYOUT;

		setView();

	}

	private void setView() {
		// TODO Auto-generated method stub
		// 顶部菜单
		TopLayout topLayout = new TopLayout(MainActivity.getInstance());
		topLayout.setLayoutText(RunLayout.MAPLAYOUT);
		super.mLayout.addView(topLayout,
				UIControl.getLayoutParams(UIControl.topData, 1));
		
		mapView=new MapView(mLayout.getContext());
		mLayout.addView(mapView, UIControl.getLayoutParams(UIControl.bodyData, 1));
		//设置点击事件，防止dragbutton的滑动冲突,参考：http://blog.csdn.net/catoop/article/details/14233419
		mapView.setOnTouchListener(new View.OnTouchListener() {  
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				if(event.getAction() == MotionEvent.ACTION_UP){  
                    MainLayout.getInstance().requestDisallowInterceptTouchEvent(false);  
                }else{  
                    MainLayout.getInstance().requestDisallowInterceptTouchEvent(true);  
                }  return false;
			}  
        });  
		
	}
}
