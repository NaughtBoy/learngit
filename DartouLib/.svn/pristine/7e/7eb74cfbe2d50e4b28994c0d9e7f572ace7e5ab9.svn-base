package com.dartou.lib.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.TypedValue;

/**
 * Bitmap工具类
 * 
 * @author cyn
 * 
 */
public class BitmapUtil {
	/**
	 * 图片去色,返回灰度图片
	 * 
	 * @param bmpOriginal
	 *            传入的图片
	 * @return 去色后的图片
	 */
	public static Bitmap toGrayscale(Bitmap bmpOriginal) {
		int width, height;
		height = bmpOriginal.getHeight();
		width = bmpOriginal.getWidth();

		Bitmap bmpGrayscale = Bitmap.createBitmap(width, height,
				Bitmap.Config.RGB_565);
		Canvas c = new Canvas(bmpGrayscale);
		Paint paint = new Paint();
		ColorMatrix cm = new ColorMatrix();
		cm.setSaturation(0);
		ColorMatrixColorFilter f = new ColorMatrixColorFilter(cm);
		paint.setColorFilter(f);
		c.drawBitmap(bmpOriginal, 0, 0, paint);
		return bmpGrayscale;
	}

	/**
	 * 以真实大小读取drawble文件夹的图片图片
	 * 
	 * @param resources
	 * @param id
	 * @return
	 */
	public static Bitmap decodeResource(Resources resources, int id) {
		TypedValue value = new TypedValue();
		resources.openRawResource(id, value);
		BitmapFactory.Options opts = new BitmapFactory.Options();
		opts.inTargetDensity = value.density;
		return BitmapFactory.decodeResource(resources, id, opts);
	}

	/** scale with width and height */
	public static Bitmap resizeBitmap(Bitmap bitmap, int w, int h) {
		if (bitmap != null) {
			int width = bitmap.getWidth();
			int height = bitmap.getHeight();
			int newWidth = w;
			int newHeight = h;
			float scaleWidth = ((float) newWidth) / width;
			float scaleHeight = ((float) newHeight) / height;
			Matrix matrix = new Matrix();
			matrix.postScale(scaleWidth, scaleHeight);
			Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0, width,
					height, matrix, true);
			return resizedBitmap;
		} else {
			return null;
		}
	}

	/**
	 * 以宽等比缩放图片
	 * 
	 * @param bitmap
	 * @param w
	 * @return
	 */
	public static Bitmap resizeBitmap(Bitmap bitmap, int w) {
		if (bitmap != null) {
			int width = bitmap.getWidth();
			int height = bitmap.getHeight();
			int newWidth = w;
			float scaleWidth = ((float) newWidth) / width;
			Matrix matrix = new Matrix();
			matrix.postScale(scaleWidth, scaleWidth);
			Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0, width,
					height, matrix, true);
			return resizedBitmap;
		} else {
			return null;
		}
	}

	/**
	 * 以高等比缩放图片
	 * 
	 * @param bitmap
	 * @param w
	 * @return
	 */
	public static Bitmap resizeBitmapByHeight(Bitmap bitmap, int h) {
		if (bitmap != null) {
			int width = bitmap.getWidth();
			int height = bitmap.getHeight();
			int newHeight = h;
			float scaleWidth = ((float) newHeight) / height;
			Matrix matrix = new Matrix();
			matrix.postScale(scaleWidth, scaleWidth);
			Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0, width,
					height, matrix, true);
			return resizedBitmap;
		} else {
			return null;
		}
	}

	/**
	 * 保存图片为png格式
	 * 
	 * @param bitmap
	 * @param name
	 *            文件绝对路径
	 */
	public static Bitmap savePNG_After(Bitmap bitmap, String name) {
		File file = new File(name);
		Bitmap resizebBitmap = resizeBitmap(bitmap, 240);
		try {
			FileOutputStream fos = new FileOutputStream(file);
			if (resizebBitmap.compress(Bitmap.CompressFormat.PNG, 100, fos)) {
				fos.flush();
				fos.close();
			}
			return resizebBitmap;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 截取中间最大正方形
	 * 
	 * @param bitmap
	 * @return
	 */
	public static Bitmap cutBitmapAndResize(Bitmap bitmap) {
		if (bitmap != null) {
			int width = bitmap.getWidth();
			int height = bitmap.getHeight();
			if (width == height) {
				return bitmap;
			}
			int lastW = width < height ? width : height;
			Bitmap resizedBitmap = Bitmap.createBitmap(bitmap,
					(width - lastW) / 2, (height - lastW) / 2, lastW, lastW);
			return resizedBitmap;
		} else {
			return null;
		}
	}

	/**
	 * 截取中间最大正方形
	 * 
	 * @param bitmap
	 * @return
	 */
	public static Bitmap cutBitmapAndResize(byte[] data) {

		int length = data.length;

		if (data != null && data.length != 0) {

			BitmapFactory.Options options = new BitmapFactory.Options();
			options.inJustDecodeBounds = true;

			Bitmap bit = BitmapFactory
					.decodeByteArray(data, 0, length, options);

			int size = options.outWidth > options.outHeight ? options.outWidth
					: options.outHeight;
			options.inJustDecodeBounds = false;
			if (size > 100) {
				options.inSampleSize = size / 100;
			}
			bit = BitmapFactory.decodeByteArray(data, 0, length, options);

			int width = bit.getWidth();
			int height = bit.getHeight();
			if (width == height) {
				return bit;
			}
			int lastW = width < height ? width : height;
			Bitmap result = Bitmap.createBitmap(bit, (width - lastW) / 2,
					(height - lastW) / 2, lastW, lastW);
			bit.recycle();
			return result;
		} else {
			return null;
		}
	}

	/**
	 * 将图片颜色转化为颜色矩阵对应的颜色
	 * 
	 * @param bitmap
	 * @param colorMatrix
	 * @return
	 */
	public static Bitmap changeColor(Bitmap bitmap, ColorMatrix colorMatrix) {
		Bitmap result = Bitmap.createBitmap(bitmap.getWidth(),
				bitmap.getHeight(), bitmap.getConfig());
		;
		Paint paint = new Paint();
		paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
		Canvas canvas = new Canvas(result);
		canvas.drawBitmap(bitmap, new Matrix(), paint);
		return result;

	}

	/**
	 * 
	 * @param bitmap
	 * @param flipX
	 *            是否绕x轴翻转
	 * @param flipY
	 *            是否绕y轴翻转
	 * @return
	 */
	public static Bitmap flipPicture(Bitmap bitmap, boolean flipX, boolean flipY) {

		Matrix matrix = new Matrix();
		int sy = flipX ? -1 : 1;
		int sx = flipY ? -1 : 1;
		matrix.postScale(sx, sy);

		Bitmap tempBitmap = Bitmap.createBitmap(bitmap, 0, 0,
				bitmap.getWidth(), bitmap.getHeight(), matrix, true);
		return tempBitmap;

	}
}
