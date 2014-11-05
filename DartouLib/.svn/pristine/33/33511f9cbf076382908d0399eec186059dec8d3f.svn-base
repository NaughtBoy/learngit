package com.dartou.lib.utils;

import java.io.UnsupportedEncodingException;
import java.util.regex.Pattern;

import android.text.TextUtils;
/**
 * 字符串工具类
 * 1.转带千位符的数字
 * 2.截取带货币的数字
 * 3.转换以BMK为单位的数字
 * 4.判断是否是数字
 * 5.判断版本号
 * @author cyn
 */
public class StringUtil {
	/**
	 * 根据int数据返回一个字符串，从右至左每隔三个字符加一个逗号
	 */
	public static String intToString(int n) {
		StringBuffer result = new StringBuffer();
		String temp = "" + Math.abs(n);
		result.append(temp);
		int sum = (temp.length() + 2) / 3 - 1;

		for (int i = 1; i <= sum; i++) {
			result.insert(temp.length() - i * 3, ",");
		}
		if(n>=0){
			return result.toString();
		}else{
			return "-"+result.toString();
		}
		
	}

	/**
	 * 根据String数据返回一个字符串，从右至左每隔三个字符加一个逗号
	 */
	public static String intToString(String n) {
		StringBuffer result = new StringBuffer();
		result.append("" + n);
		String temp = "" + n;
		int sum = (temp.length() + 2) / 3 - 1;

		for (int i = 1; i <= sum; i++) {
			result.insert(temp.length() - i * 3, ",");
		}
		return result.toString();
	}

	/**
	 * 根据double数据返回一个字符串，从右至左每隔三个字符加一个逗号
	 */
	public static String DoubleToString(double n) {

		String n1 = intToString((int) n);
		int index = ("" + n).indexOf(".");

		String strn = "" + n;

		if (index == -1)
			return "" + n1;

		int end;
		if (strn.length() <= index + 2) {
			end = index + 2;
		} else {
			end = index + 3;
		}

		String str = strn.substring(index, end);

		if (".00".equals(str) || ".0".equals(str))
			return n1;
		else
			return n1 + str;

	}

	/**
	 * 根据字符串截取一定字节长度的字符串
	 * 
	 * @param str
	 *            要截取的字符串
	 * @param length
	 *            要截取的字节长度
	 * @return
	 */
	public static String GetString(String str, int length) {
		if (TextUtils.isEmpty(str)) {
			return "";
		}
		String temp = str;
		int j = 0;
		int k = 0;
		byte b[] = null;
		try {
			b = temp.getBytes("GB2312");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (b.length <= length) {
			return str;
		}
		for (int i = 0; i < str.length(); i++) {
			String s = temp.substring(i, i + 1);
			if (s.getBytes().length == 2) {
				j += 2;
			} else {
				j += 1;
			}

			if (j >= length - 1) {
				break;
			}
			k++;
		}
		if (j > length - 1) {

			return str.substring(0, k - 1) + "…";
		} else {
			return str.substring(0, k) + "…";
		}
	}

	/**
	 * 获取“$100”后面的“100”
	 * 
	 * @param str
	 * @return
	 */
	public static String[] GetPrice(String str) {
		str = str.replaceAll(",", "").replaceAll(" ", "").trim();
		if (isNumeric(str.charAt(0) + "")) {
			int index = -1;

			for (int i = 0; i < str.length(); i++) {
				String st = str.charAt(i) + "";
				if (!isNumeric(st) && !st.equals(".")) {
					index = i;
					break;
				}
			}
			return new String[] { str.substring(index), str.substring(0, index) };
		} else {
			for (int i = 1; i < str.length(); i++) {
				String st = str.charAt(i) + "";
				boolean b = isNumeric(st);
				if (b) {

					return new String[] { str.substring(0, i), str.substring(i) };
				}
			}
		}

		return new String[] {};
	}

	/**
	 * 判断字符是否是数字
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]");
		return pattern.matcher(str).matches();
	}
	
	/**
	 * 返回以B、M、K为单位的字符串
	 * @param n
	 * @return
	 */
	public static String GetShowNum(long n){
		long m = (long) Math.abs(n);
		if(m >= 1000000000){
			return (n/100000000/10.0f)+"B";
		}else if(m>=1000000){
			return (n/100000/10.0)+"M";
		}else if(m>=1000){
			return (n/100/10.0)+"K";
		}else if(m>0){
			return n+"";
		}else{
			return "0";
		}
	}
	
	/**
	 * 五位数以上的的返回以B、M、K为单位的字符串
	 * @param n
	 * @return
	 */
	public static String GetShowNum1(long n){
		long m = (long) Math.abs(n);
		if(m >= 1000000000){
			return (n/100000000/10)+"B";
		}else if(m>=1000000){
			return (n/100000/10)+"M";
		}else if(m>=9999){
			return (n/100/10)+"K";
		}else{
			return n+"";
		}
	}
	
	
	/**
	 * 返回根据语言来显示数字文本
	 * @param n
	 * @return lan 语言 0简体 1繁体 2英文
	 */
	public static String GetShowNumByLan(long n,int lan){
		if(lan==0||lan==1){
			String s[][] = {{"亿","万"},{"億","萬"}};
			long m = (long) Math.abs(n);
			if(m >= 100000000){
				if(n%100000000==0){
					return (n/100000000)+s[lan][0];
				}
				return (n/10000000/10.0f)+s[lan][0];
			}else if(m>=10000000){
				return (n/10000)+s[lan][1];
			}else if(m>=10000){
				if(n%10000==0){
					return (n/10000)+s[lan][1];
				}
				return (n/1000/10.0f)+s[lan][1];
			}else if(m>0){
				return n+"";
			}else{
				return "0";
			}
		}else{
			return GetShowNum(n);
		}
	}
	
	/**
	 * 比对版本
	 * @param serverVesion // 网络获取版本
	 * @param localVersion // 当前版本
	 * @return
	 */
	public static boolean isUpdate(String serverVesion,String oldVesion){
		String[] vers = serverVesion.split("\\.");
		String[] ver = oldVesion.split("\\.");
		return compareV1AndV2(vers, ver);
	}
	
	/**
	 * 比较版本v1是否大于版本v2
	 * @param v1
	 * @param v2
	 * @return
	 */
	private static boolean compareV1AndV2(String[] v1, String[] v2) {
		try {
			if (Integer.valueOf(v1[0]) > Integer.valueOf(v2[0])) {
				return true;
			} else if (Integer.valueOf(v1[0]) < Integer
					.valueOf(v2[0])) {
				return false;
			} else {
				if (Integer.valueOf(v1[1]) > Integer
						.valueOf(v2[1])) {
					return true;
				} else if (Integer.valueOf(v1[1]) < Integer
						.valueOf(v2[1])) {
					return false;
				} else {
					if (Integer.valueOf(v1[2]) > Integer
							.valueOf(v2[2])) {
						return true;
					} else {
						return false;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
