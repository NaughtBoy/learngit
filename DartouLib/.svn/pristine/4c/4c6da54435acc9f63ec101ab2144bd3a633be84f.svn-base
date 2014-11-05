package com.dartou.lib.network.http;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.Activity;
import android.app.DownloadManager;
import android.app.DownloadManager.Request;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
/**
 * 文件下载
 * @author cyn
 */
public class FileDownLoad{

	/**
	 * 从网络获取头像
	 * 
	 * @param path
	 * @return
	 * @throws Exception
	 */
	public static Bitmap getImage(String path){
		URL url;
		try {
			url = new URL(path);
			HttpURLConnection httpURLconnection = (HttpURLConnection) url
					.openConnection();
			httpURLconnection.setReadTimeout(10 * 1000);
			httpURLconnection.connect();
			InputStream in = null;
			if (httpURLconnection.getResponseCode() == 200) {
				in = httpURLconnection.getInputStream();
				Bitmap bit = BitmapFactory.decodeStream(in);
				in.close();
				httpURLconnection.disconnect();
				return bit;
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * 调用系统下载
	 * @param activity
	 * @param url
	 */
	public static void systemDownload(Activity activity,String url){
		DownloadManager downloadManager = (DownloadManager) activity.getSystemService(Activity.DOWNLOAD_SERVICE);  
        
	    Uri uri = Uri.parse(url);  
	    Request request = new Request(uri);  
	  
	    //设置允许使用的网络类型，这里是移动网络和wifi都可以    
	    request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE|DownloadManager.Request.NETWORK_WIFI);    
	  
	    //禁止发出通知，既后台下载，如果要使用这一句必须声明一个权限：android.permission.DOWNLOAD_WITHOUT_NOTIFICATION    
//	    request.setShowRunningNotification(false);    
	  
	    //不显示下载界面    
	    request.setVisibleInDownloadsUi(false);  
	        /*设置下载后文件存放的位置,如果sdcard不可用，那么设置这个将报错，因此最好不设置如果sdcard可用，下载后的文件        在/mnt/sdcard/Android/data/packageName/files目录下面，如果sdcard不可用,设置了下面这个将报错，不设置，下载后的文件在/cache这个  目录下面*/  
	    //request.setDestinationInExternalFilesDir(this, null, "tar.apk");  
	    
	    downloadManager.enqueue(request); 
	}
}
