package com.schoolinfo.data;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;

/**
 * 用户类
 * @author huangwubin 2014-9-5
 */
public class User extends BmobUser {
	//BmobUser�����Ѿ���username,password,email ���ԣ�����Ҫ���¶���
	private BmobFile headPicture;//头像
	private String studentId;//学号
	private String phone;//手机号码
	public BmobFile getHeadPicture() {
		return headPicture;
	}
	public void setHeadPicture(BmobFile mHeadPicture) {
		this.headPicture = mHeadPicture;
	}
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String mStudentId) {
		this.studentId = mStudentId;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String mPhone) {
		this.phone = mPhone;
	}
	
}
