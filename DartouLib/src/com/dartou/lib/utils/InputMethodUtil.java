package com.dartou.lib.utils;

import android.app.Activity;
import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * 输入法键盘消失和显示
 * @author cyn
 */
public class InputMethodUtil {

	/**
	 * 弹出软键盘
	 * @param editText
	 */
	public static void showMethod(EditText editText){
		InputMethodManager imm = (InputMethodManager) editText.getContext()
				.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.showSoftInput(editText, InputMethodManager.SHOW_FORCED);
	}
	
	/**
	 * 隐藏软键盘
	 * @param editText
	 */
	public static void dismissMethod(EditText editText){
		
		InputMethodManager imm = (InputMethodManager) editText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(editText.getWindowToken(),InputMethodManager.RESULT_UNCHANGED_SHOWN);
	}
	/**
	 * 隐藏软键盘
	 * @param editText
	 */
	public static void closeInputMethodManager(Activity activity){
		InputMethodManager im = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
		if(activity.getCurrentFocus()!=null&&im!=null){
			im.hideSoftInputFromWindow(activity.getCurrentFocus()
					.getWindowToken(), InputMethodManager.RESULT_UNCHANGED_SHOWN);
		}
		
	}
}
