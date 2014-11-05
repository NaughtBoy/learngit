package com.dartou.lib.utils;

import android.text.Html;
import android.text.Spanned;
/**
 * 动态生成带颜色的文本
 * @author cyn
 */
public class TextColorUtil {

	public StringBuffer mText;
	public TextColorUtil(){
		mText = new StringBuffer();
	}
	
	public TextColorUtil add(String color,String text){
		mText.append("<font color = \""+color+"\">"+text+"</font>");
		return this;
	}
	
	public Spanned getString(){
		return Html.fromHtml(mText.toString());
	}
}
