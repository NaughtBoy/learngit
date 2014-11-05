package com.schoolinfo.control;

import android.graphics.Point;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * ui控制类
 * 
 * @author huangwubin 2014-9-5
 */
public class UIControl {

	public static Point p;// 屏幕分辨率

	public static void init(Point point) {
		p = point;

		// 初始化数据
		initBaseData();
		initTopData();
		initPersonalData();
		initLoginData();
	}

	// 整体布局数据
	public static UIData topData = new UIData();// 顶部菜单
	public static UIData bodyData = new UIData();// 物品列表菜单

	private static void initBaseData() {

		topData.w = p.x;
		topData.h = p.y / 12;

		bodyData.w = p.x;
		bodyData.h = p.y / 12 * 11;
		bodyData.y = p.y / 12;

	}
	
	// 顶部布局数据
	public static UIData topImageData = new UIData();// 头像
	public static UIData topTextData = new UIData();// 当前布局文本
	private static void initTopData() {
		// TODO Auto-generated method stub
		topImageData.x=(int) (topData.h*0.1);
		topImageData.y=topImageData.x;
		topImageData.h=(int) (topData.h*0.8);
		topImageData.w=topImageData.h;
		
		topTextData.h=topData.h;
		topTextData.w=p.x-topImageData.w-topImageData.x;
		topTextData.x=topImageData.w+topImageData.x;
//		topTextData.y=topImageData.y;
	}
	
	
	//初始化个人中心数据
	public static UIData personalPhotoData=new UIData();//头像
	public static UIData personalTextData=new UIData();//文本信息
	public static UIData personalBtnPhotoData=new UIData();//头像按钮
	public static UIData personalPasswordData=new UIData();//密码按钮
	public static UIData personalReleaseData=new UIData();//发布按钮
	public static UIData personalStudentIdData=new UIData();//学号按钮
	public static UIData personalBtnData=new UIData();//个人中心按钮
	
	private static void initPersonalData(){
		
		personalPhotoData.x=(int) (p.x*0.09f);
		personalPhotoData.y=(int) (p.y*0.06f);
		personalPhotoData.w=(int) (p.x*0.18f);
		personalPhotoData.h=personalPhotoData.w;
		
		personalTextData.x=(int) (p.x*0.33f);
		personalTextData.y=personalPhotoData.y;
		personalTextData.w=p.x/2;
		personalTextData.h=personalPhotoData.h;
		
		personalBtnPhotoData.x=(int) (p.x*0.2f);
		personalBtnPhotoData.y=(int) (p.y*0.3f);
		personalBtnPhotoData.w=(int) (p.x*0.15f);
		personalBtnPhotoData.h=personalBtnPhotoData.w*110/100;
		
		personalPasswordData.x=(int) (p.x*0.03f);
		personalPasswordData.y=(int) (p.y*0.5f);
		personalPasswordData.w=(int) (p.x*0.2f);
		personalPasswordData.h=personalPasswordData.w*115/130;
		
		personalReleaseData.x=(int) (p.x*0.48f);
		personalReleaseData.y=(int) (p.y*0.41f);
		personalReleaseData.w=(int) (p.x*0.18f);
		personalReleaseData.h=personalReleaseData.w*110/115;
		
		personalStudentIdData.x=(int) (p.x*0.73f);
		personalStudentIdData.y=(int) (p.y*0.5f);
		personalStudentIdData.w=(int) (p.x*0.19f);
		personalStudentIdData.h=personalStudentIdData.w*130/125;
		
		personalBtnData.x=(int) (p.x*0.36f);
		personalBtnData.y=(int) (p.y*0.80f);
		personalBtnData.w=(int) (p.x*0.22f);
		personalBtnData.h=personalBtnData.w*140/140;
		
	}
	//初始化登陆界面的布局数据 ,采用线性布局
	public static UIData loginLogo=new UIData();
	public static UIData loginName=new UIData();
	public static UIData loginPassword=new UIData();
	public static UIData loginBtnLogin=new UIData();
	public static UIData loginBtnRegist=new UIData();
	public static UIData loginBtnSkip=new UIData();
	private static void initLoginData(){
		loginLogo.w=(int) (p.x*0.4f);
		loginLogo.h=loginLogo.w;
		
		loginName.w=(int) (p.x*0.8f);
		
		loginPassword.w=loginName.w;
		
		loginBtnLogin.w=loginName.w;
		
		loginBtnRegist.w=(int) (loginName.w/2*0.9f);
		
		loginBtnSkip.w=loginBtnRegist.w;
		loginBtnSkip.x=loginName.w-loginBtnSkip.w*2;
	}
	
	public static class UIData {
		public int x = 0;
		public int y = 0;
		public int w = 0;
		public int h = 0;
	}



	/**
	 * 根据UIData获取LayoutParams
	 * 
	 * @param data
	 *            UIData
	 * @param type
	 *            布局类型，1为RelativeLayout。2为线性布局
	 * @return
	 */
	public static LayoutParams getLayoutParams(UIData data, int type) {
		switch (type) {
		case 1:
			RelativeLayout.LayoutParams rParams = new RelativeLayout.LayoutParams(
					data.w, data.h);
			rParams.leftMargin = data.x;
			rParams.topMargin = data.y;
			return rParams;
		case 2:
			
		default:
			break;
		}
		return null;
	}
	
	/**
	 * 根据UIData获取LayoutParams
	 * 
	 * @param data
	 *            UIData
	 * @param type
	 *            布局类型，1为RelativeLayout。2为线性布局
	 * @return
	 */
	public static LayoutParams getLayoutParams(View view,UIData data, int type) {
		switch (type) {
		case 1:
			RelativeLayout.LayoutParams rParams = (android.widget.RelativeLayout.LayoutParams) view.getLayoutParams();
			rParams.width=data.w;
			rParams.height=data.h;
			rParams.leftMargin = data.x;
			rParams.topMargin = data.y;
			return rParams;
		case 2:
			LinearLayout.LayoutParams params=(android.widget.LinearLayout.LayoutParams) view.getLayoutParams();
			params.weight=data.w;
			params.height=data.h;
			params.leftMargin=data.x;
			params.topMargin=data.y;
			view.setLayoutParams(params);
			return params;
		default:
			break;
		}
		return null;
	}
}
