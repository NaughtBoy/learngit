package com.dartou.lib.native_storage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.dartou.lib.utils.LogUtil;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;

public class FileHandler {

	public static void download(Context context, String url, String name,
			String dir) {
		if (isExists(context, name, dir) == null) {
			String parent;
			if (Environment.getExternalStorageState().equals(
					Environment.MEDIA_MOUNTED)) {
				parent = Environment.getExternalStorageDirectory().getPath();
			} else {
				parent = context.getCacheDir().getAbsolutePath();
			}
			write2SDFromInput(parent + "/" + dir, name, url);
		}
	}

	/**
	 * 将一个InputStream里面的数据写入到SD卡中
	 * 
	 * @param path
	 * @param fileName
	 * @param input
	 * @return
	 */
	public static void write2SDFromInput(final String path, final String name,
			final String urlS) {
		new AsyncTask<Void, Void, Void>() {
			@Override
			protected Void doInBackground(Void... params) {
				OutputStream output = null;
				try {
					File file = new File(path + "/" + name);
					if(file!=null){
						file.getParentFile().mkdir();
						file.createNewFile();
					}
//					LogUtil.i("", "create-->"+file.createNewFile()+" -->"+file.getAbsolutePath());
					output = new FileOutputStream(file);
					byte[] buffer = new byte[1024];

					URL url = new URL(urlS);
					HttpURLConnection httpURLconnection = (HttpURLConnection) url
							.openConnection();
					httpURLconnection.setReadTimeout(10 * 1000);

					InputStream in = null;
					if (httpURLconnection.getResponseCode() == 200) {
						in = httpURLconnection.getInputStream();
					}

					/* 网友提供 begin */
					int length;
					while ((length = (in.read(buffer))) > 0) {
						output.write(buffer, 0, length);
					}
					/* 网友提供 end */

					output.flush();
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					try {
						if(output!=null){
							output.close();
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				return null;
			}
		}.execute();
	}

	public static InputStream isExists(Context context, String name, String dir) {
		String parent;
		File file;
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			parent = Environment.getExternalStorageDirectory().getPath();
			file = new File(parent + "/" + dir + "/" + name);
			if (file.exists()) {
				try {
					return new FileInputStream(file);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				parent = context.getCacheDir().getAbsolutePath();
				file = new File(parent + "/" + dir + "/" + name);
				if (file.exists()) {
					try {
						return new FileInputStream(file);
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		return null;
	}
}
