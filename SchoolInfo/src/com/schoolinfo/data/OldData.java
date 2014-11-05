package com.schoolinfo.data;

import java.io.File;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

/**
 * 二手物品数据类
 * 
 * @author huangwubin 2014-9-29
 */
public class OldData extends BmobObject {
	public String mName;//
	public String mDecString;// 物品的详细描述
	public BmobFile mPicture;// 物品图片
	public String mContact;// 联系人
	public String mContactInfo;// 联系方式
	public String mContactType;// 联系方式的类型
	public User mUser;//绑定的用户
	public float mPrice;// 价格
}
