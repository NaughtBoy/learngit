package com.schoolinfo.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.ref.SoftReference;
import java.util.Hashtable;
import java.util.logging.Logger;

import com.schoolinfo.MainActivity;
import com.schoolinfo.control.UIControl;

import android.R.integer;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Bitmap.Config;
import android.graphics.PorterDuffXfermode;
import android.graphics.PorterDuff.Mode;
import android.graphics.Shader.TileMode;
import android.graphics.Paint;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

/**
 * 图片工具类
 * 
 * @author huangwubin 2014-9-25
 */
public class ImageUtils {

	private static final String TAG = "ImageUtils";
	// 缓存集合
	private static Hashtable<Integer, SoftReference<Bitmap>> mCacheHashTable = new Hashtable<Integer, SoftReference<Bitmap>>();

	/**
	 * 根据id返回一个处理后的图片
	 * 
	 * @param res
	 * @param imageID
	 * @return
	 */
	public static Bitmap getImageBitmap(Resources res, int imageID) {

		// 先去集合中取当前imageID 是否已经拿过图片, 如果集合中有, 说明已经拿过, 直接使用集合中的图片返回
		SoftReference<Bitmap> softReference = mCacheHashTable.get(imageID);
		if (softReference != null) {
			Bitmap bitmap = softReference.get();

			if (bitmap != null) {
				// 从内存中取
				Log.i(TAG, "从内存中取");
				return bitmap;
			}
		}

		// 如果集合中没有, 就调用getInvertImage得到一个图片, 需要向集合中保留一张, 最后返回当前图片
		Log.i(TAG, "重新加载");
		Bitmap invertImage = getInvertImage(res, imageID);
		// 在集合中存一份, 便于下次再取的时候直接去集合中取.
		mCacheHashTable.put(imageID, new SoftReference<Bitmap>(invertImage));

		return invertImage;
	}

	/**
	 * 根据id返回一个处理后的图片
	 * 
	 * @param imageID
	 * @return
	 */
	public static Bitmap getInvertImage(Resources res, int imageID) {
		// 获取原图
		Bitmap sourceBitmap = BitmapFactory.decodeResource(res, imageID);

		// 生成倒影图片
		Matrix m = new Matrix(); // 图形矩阵
		m.setScale(1f, -1f); // 让图形按照矩阵进行垂直反转

		// float[] values = {
		// 1.0f, 0f, 0f,
		// 0f, -1.0f, 0f,
		// 0f, 0f, 1.0f
		// };
		// m.setValues(values);

		Bitmap invertBitmap = Bitmap.createBitmap(sourceBitmap, 0,
				sourceBitmap.getHeight() / 2, sourceBitmap.getWidth(),
				sourceBitmap.getHeight() / 2, m, false);

		// 把两张图片合成一张
		Bitmap resultBitmap = Bitmap.createBitmap(sourceBitmap.getWidth(),
				(int) (sourceBitmap.getHeight() * 1.5 + 5), Config.ARGB_8888);

		Canvas canvas = new Canvas(resultBitmap); // 指定画板画在合成图片上

		canvas.drawBitmap(sourceBitmap, 0, 0, null); // 把原图画在合成图片的上面

		canvas.drawBitmap(invertBitmap, 0, sourceBitmap.getHeight() + 5, null); // 把倒影图片画在合成图片上

		// 添加遮罩效果
		Paint paint = new Paint();

		// 设置颜色
		LinearGradient shader = new LinearGradient(0,
				sourceBitmap.getHeight() + 5, 0, resultBitmap.getHeight(),
				0x70ffffff, 0x00ffffff, TileMode.CLAMP);
		paint.setShader(shader);

		// 设置模式为: 遮罩, 是取交集
		paint.setXfermode(new PorterDuffXfermode(Mode.DST_IN));

		canvas.drawRect(0, sourceBitmap.getHeight() + 5,
				sourceBitmap.getWidth(), resultBitmap.getHeight(), paint);

		return resultBitmap;
	}

	/**
	 * 根据Uri获取图片路径
	 * 
	 * @param uri
	 * @return
	 */
	public static String getImagePath(Uri uri) {
		String pathString;

		String[] proj = { MediaStore.Images.Media.DATA };

		Cursor actualimagecursor = MainActivity.getInstance().managedQuery(uri,
				proj, null, null, null);

		int actual_image_column_index = actualimagecursor
				.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

		actualimagecursor.moveToFirst();

		pathString = actualimagecursor.getString(actual_image_column_index);

		return pathString;
	}

	/**
	 * 图片压缩:质量压缩
	 * 
	 * @param bitmap
	 * @param file
	 */
	public static void qualityCompress(Bitmap bmp, File file) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int options = 80;// 从80开始
		bmp.compress(Bitmap.CompressFormat.JPEG, options, baos);
		while (baos.toByteArray().length / 1024 > 2048) { // 图片大小压缩至2M以下
			baos.reset();
			options -= 10;
			bmp.compress(Bitmap.CompressFormat.JPEG, options, baos);
		}
		try {
			FileOutputStream fos = new FileOutputStream(file);
			fos.write(baos.toByteArray());
			fos.flush();
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 大图压缩。。。没用了
	 * 
	 * @param srcPath
	 *            原图片玩文件路径
	 * @param resultPath
	 *            压缩完的图片路径
	 */
	public static void bigImageCompress(String srcPath, String resultPath) {
		BitmapFactory.Options newOpts = new BitmapFactory.Options();
		// 开始读入图片，此时把options.inJustDecodeBounds 设回true了
		newOpts.inJustDecodeBounds = true;
		Bitmap bitmap = BitmapFactory.decodeFile(srcPath, newOpts);// 此时返回bm为空

		newOpts.inJustDecodeBounds = false;
		int w = newOpts.outWidth;
		int h = newOpts.outHeight;
		// 现在主流手机比较多是800*480分辨率，所以高和宽我们设置为
		float hh = 800f;// 这里设置高度为800f
		float ww = 480f;// 这里设置宽度为480f
		// 缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
		int be = 1;// be=1表示不缩放
		if (w > h && w > ww) {// 如果宽度大的话根据宽度固定大小缩放
			be = (int) (newOpts.outWidth / ww);
		} else if (w < h && h > hh) {// 如果高度高的话根据宽度固定大小缩放
			be = (int) (newOpts.outHeight / hh);
		}
		if (be <= 0)
			be = 1;
		newOpts.inSampleSize = be;// 设置缩放比例
		BitmapFactory.Options opt = new BitmapFactory.Options();
		opt.inSampleSize = be;
		// 重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
		bitmap = BitmapFactory.decodeFile(srcPath, opt);
		qualityCompress(bitmap, new File(resultPath));// 压缩好比例大小后再进行质量压缩

	}

	/**
	 * 从原图的大小开始进行测试，取到最大分辨率的图
	 * 
	 * @param path
	 *            原图路径
	 * @param inSampleSize
	 *            1
	 * @return
	 */
	public static Bitmap getMaxBitmap(String path, int inSampleSize) {
		BitmapFactory.Options options = new Options();
		options.inSampleSize = inSampleSize;
		Bitmap bitmap = null;
		try {
			bitmap = BitmapFactory.decodeFile(path, options);

		} catch (OutOfMemoryError e) {

			bitmap = getMaxBitmap(path, inSampleSize + 1);

		}
		return bitmap;

	}

	/**
	 * 根据分辨率获取最大的能显示的图片
	 * 
	 * @param bitmap
	 * @return
	 */
	public static Bitmap getMaxShowBitmap(Bitmap bitmap) {
		int w = bitmap.getWidth();
		int h = bitmap.getHeight();

		if (w > h && w > UIControl.p.x) {//
			w = UIControl.p.x;
			h = w * bitmap.getHeight() / bitmap.getWidth();
		} else if (h > w && h > UIControl.p.y) {
			h = UIControl.p.y;
			w = h * bitmap.getWidth() / bitmap.getHeight();
		} else if (h == w) {
			if (UIControl.p.x > UIControl.p.y) {
				h = UIControl.p.y;
				w = h;
			} else {
				w = UIControl.p.x;
				h = w;
			}
		}
		return Bitmap.createScaledBitmap(bitmap, w, h, false);
	}

	/**
	 * 选择并裁剪图片，需要在Mainactivity定义回调
	 */
	public static void selectAndCropImage() {
		Intent intent = new Intent(Intent.ACTION_PICK,
				android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
		MainActivity.getInstance().startActivityForResult(intent, 2);
	}

	/**
	 * 调用系统对图片进行裁剪
	 * 
	 * @param uri
	 */
	public static void cropImage(Uri uri) {
		Intent intent = new Intent("com.android.camera.action.CROP");
		intent.setDataAndType(uri, "image/*");
		intent.putExtra("crop", "true");
		// aspectX aspectY 是宽高的比例`
		intent.putExtra("aspectX", 1);
		intent.putExtra("aspectY", 1);
		// outputX outputY 是裁剪图片宽高
		intent.putExtra("outputX", 300);
		intent.putExtra("outputY", 300);
		intent.putExtra("return-data", true);
		MainActivity.getInstance().startActivityForResult(intent, 3);
	}

	public static boolean saveImageToLocal(Bitmap bitmap, String path) {
		File file = new File(path);
		if (file.exists()) {
			file.delete();
		}
		FileOutputStream stream;
		try {
			stream = new FileOutputStream(file);
			bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
			stream.flush();
			stream.close();
			Log.i("TAG", "图片已保存");
			return true;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}
}