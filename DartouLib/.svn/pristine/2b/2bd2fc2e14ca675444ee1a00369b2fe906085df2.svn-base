package com.dartou.lib.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * assets 读取工具类
 * 
 * @author cyn
 * 
 */
public class AssertsUtil {

	/**
	 * 从asserts文件夹读取资源文件为字符串，适用于html以及txt等文档
	 * 
	 * @param context
	 * @param name
	 * @return
	 */
	public static String getString(Context context, String name) {
		AssetManager assets = context.getAssets();
		String result = null;
		try {
			InputStream is = assets.open(name);
			int size = is.available();
			byte[] buffer = new byte[size];
			is.read(buffer);
			result = new String(buffer, "utf-8");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return result;
	}

	/**
	 * 从文件里读入字符串
	 * 
	 * @param file
	 * @return
	 */
	public static String getString(File file) {
		String result = null;
		try {
			result = new String(getBytes(file), "utf-8");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return result;
	}

	/**
	 * 获取图片
	 * 
	 * @param context
	 * @param name
	 * @param sample
	 *            缩小倍数
	 * @return
	 */
	public static Bitmap getBitmap(Context context, String path, int sample) {
		Bitmap result = null;
		AssetManager assetManager = context.getAssets();
		InputStream is;
		try {
			is = assetManager.open(path);
			BitmapFactory.Options op = new BitmapFactory.Options();
			op.inSampleSize = sample;
			op.inInputShareable = true;
			op.inPurgeable = true;
			result = BitmapFactory.decodeStream(is, null, op);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 从文件里读入数组
	 * 
	 * @param file
	 * @return
	 */
	public static byte[] getBytes(File file) {
		try {
			InputStream is = new FileInputStream(file);
			int size = is.available();
			byte[] buffer = new byte[size];
			is.read(buffer);
			return buffer;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
