package com.dartou.lib.view.mydraw;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
/**
 * 抽屉activity
 * @author cyn
 */
public abstract class BaseDrawerActivity extends Activity {
		

	private SlidingMenu slidingMenu;
	private RelativeLayout root;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		root = new RelativeLayout(this, null);
		slidingMenu = new SlidingMenu(this, null);
		root.addView(slidingMenu,new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.FILL_PARENT,RelativeLayout.LayoutParams.FILL_PARENT));
		setContentView(root);
		
		View center = getCenterView();
		if(center!=null){
			slidingMenu.setCenter(center);
		}
		MenuLayout left = getLeftView();
		if(left!=null){
			slidingMenu.setLeftMenu(left, getLeftWidth());
		}
		MenuLayout left2 = getLeftView2();
		if(left2!=null){
			slidingMenu.setLeftMenu2(left2, getLeftWidth2());
		}
		
		MenuLayout right = getRightView();
		if(right!=null){
			slidingMenu.setRightMenu(right, getRightWidth());
		}
		MenuLayout right2 = getRightView2();
		if(right2!=null){
			slidingMenu.setRightMenu2(right2, getRightWidth2());
		}
	}
	
	public RelativeLayout getRoot(){
		return root;
	}
	
	
	/**
	 *中间主界面
	 * @return
	 */
	public abstract View getCenterView();
	
	/**
	 * 左一级菜单，可以为空
	 * @return
	 */
	public abstract MenuLayout getLeftView();
	
	/**
	 * 右一级菜单，可以为空
	 * @return
	 */
	public abstract MenuLayout getRightView();
	
	/**
	 * 左一级菜单宽度
	 * @return
	 */
	public abstract int getLeftWidth();
	/**
	 * 右一级菜单宽度
	 * @return
	 */
	public abstract int getRightWidth();
	
	/**
	 * 左二级菜单，可以为空，若不为空则左一级菜单不能为空
	 * @return
	 */
	public abstract MenuLayout getLeftView2();
	/**
	 * 右二级菜单宽度，可以为空，若不为空则右一级菜单不能为空
	 * @return
	 */
	public abstract MenuLayout getRightView2();
	
	/**
	 * 左二级菜单宽度
	 * @return
	 */
	public abstract int getLeftWidth2();
	
	/**
	 * 右二级菜单宽度
	 * @return
	 */
	public abstract int getRightWidth2();
	
	/**
	 * 显示左一级菜单
	 */
	public void showLeft(){
		slidingMenu.showLeft();
	}
	
	/**
	 * 显示左二级菜单
	 */
	public void showLeft2(){
		slidingMenu.showLeft2();
	}
	
	/**
	 * 显示右一级菜单
	 */
	public void showRight(){
		slidingMenu.showRight();
	}
	
	/**
	 * 显示右二级菜单
	 */
	public void showRight2(){
		slidingMenu.showRight2();
	}
	
	public void showContent(){
		slidingMenu.showCenter();
	}
	
	/**
	 * 返回一级左菜单
	 */
	public void backLeft(){
		slidingMenu.backLeft();
		slidingMenu.closeInputMethodManager();
	}
	
	/**
	 * 返回一级右菜单
	 */
	public void backRight(){
		slidingMenu.backRight();
		slidingMenu.closeInputMethodManager();
	}
	
	public void setLeftListener(MyDrawerListener listner){
		slidingMenu.setLeftListener(listner);
	}
	
	public void setRightListener(MyDrawerListener listner){
		slidingMenu.setRightListener(listner);
	}
	
	public void setCanScroll(boolean isScroll){
		slidingMenu.setCanScroll(isScroll);
	}
	
	public interface MyDrawerListener{
		public void derectOpen(int time);
		public void derectClose(int time);
		public void open(int time);
		public void close(int time);
		public void detal(float x);
	}
	
}
