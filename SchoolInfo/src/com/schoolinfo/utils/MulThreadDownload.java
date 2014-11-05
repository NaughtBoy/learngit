package com.schoolinfo.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import android.graphics.Path;
import android.os.Handler;
import android.os.Message;

/**
 * 多线程下载类,下载后的图片存放位置为"/mnt/sdcard/schoolInfo/" 出现问题：当线程数>1时，最后的线程无法下载，报空
 * 
 * @author huangwubin 2014-9-28
 */
public class MulThreadDownload {
	final private int THREADNUM = 20;// 下载线程数
	private Handler mHandler;// 若完成下载，handler发送1,Object内容为路径
	private URL mUrl;// 下载地址
	private File mTempFile;// 下载到本地的临时文件
	private File mFile;//下载完的文件
	private int mLength;// 下载文件的长度
	private int mBlock;// 每一个线程负责的文件长度
	private int mDownLength = 0;// 当前的下载量
	private int mFinishThreadNum = 0;// 完成下载的线程数
	private String mName;//文件名字
	
	/**
	 * 多线程下载，start启动
	 * 
	 * @param handler
	 *            完成下载，发消息1
	 * @param url
	 *            下载的URL
	 * @param name
	 *            文件名字
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	public MulThreadDownload(Handler handler, String url, String name)
			throws MalformedURLException {
		this.mHandler = handler;
		this.mUrl = new URL(url);
		this.mTempFile = new File("/mnt/sdcard/SchoolInfo/" + name+".temp");
		this.mFile=new File("/mnt/sdcard/SchoolInfo/" + name);
		this.mName=name;

	}

	/**
	 * 启动线程，下载后的路径为"/mnt/sdcard/schoolInfo/"
	 * 
	 * @throws IOException
	 */
	public void start() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				HttpURLConnection connection = null;
				try {
					connection = (HttpURLConnection) mUrl.openConnection();

					connection.setRequestMethod("GET");

					connection.setConnectTimeout(5000);
					mLength = connection.getContentLength();
					mBlock = mLength % THREADNUM == 0 ? mLength / THREADNUM
							: mLength / THREADNUM + 1;

					if (mFile.exists()&&mFile.length()==mLength) {
						Message message = new Message();
						message.what = 1;
						message.obj = mFile.getPath();
						mHandler.sendMessage(message);
						return;
					} else if (mFile.length() != mLength) {
						mFile.delete();
					}

					mTempFile.createNewFile();

					// 文件操作类，可定位到文件的某一位置进行读写
					RandomAccessFile rFile = new RandomAccessFile(mTempFile, "rwd");
					rFile.setLength(mLength);
					rFile.close();
				} catch (ProtocolException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// 启动下载线程
				for (int i = 0; i < THREADNUM; i++) {
					DownloadThread thread = new DownloadThread(i);
					thread.start();
				}
			}
		}).start();
	}

	/**
	 * 获取当前下载进度
	 * 
	 * @return
	 */
	public float getProgress() {
		return 1.0f * mDownLength / mLength;
	}

	class DownloadThread extends Thread {

		private int mThreadId;

		public DownloadThread(int index) {
			// TODO Auto-generated constructor stub
			mThreadId = index;

		}

		@Override
		public void run() {
			try {
				int startpos = mThreadId * mBlock;// 计算该线程从文件的什么位置开始下载
				int endpos = (mThreadId + 1) * mBlock - 1;// 计算该线程下载到文件的什么位置结束
				if (mThreadId == THREADNUM - 1) {
					endpos = mLength - 1;
				}
				HttpURLConnection conn = (HttpURLConnection) mUrl
						.openConnection();
				conn.setRequestMethod("GET");
				conn.setConnectTimeout(5 * 1000);
				conn.setRequestProperty("Range", "bytes=" + startpos + "-"
						+ endpos);
				InputStream inputStream = conn.getInputStream();
				RandomAccessFile rfile = new RandomAccessFile(mTempFile, "rwd");
				rfile.seek(startpos);
				byte[] buffer = new byte[1024];
				int len = 0;
				while ((len = inputStream.read(buffer)) != -1) {
					rfile.write(buffer, 0, len);
					mDownLength += len;// 累加当前线程下载的下载量
				}
				rfile.close();
				inputStream.close();
				mFinishThreadNum++;// 累加完成下载的线程数
				if (mFinishThreadNum == THREADNUM) {// 完成所有下载
					//修改名字
					mTempFile.renameTo(mFile);
					
					Message message = new Message();
					message.what = 1;
					message.obj = mFile.getPath();
					mHandler.sendMessage(message);
				}
				System.out.println("线程" + (mThreadId + 1) + "下载完成:" + startpos
						+ " -->" + endpos);
			} catch (Exception e) {
				Message message = new Message();
				message.what = 2;
				// message.obj=mFile.getPath();
				mHandler.sendMessage(message);
				e.printStackTrace();
			}
		}
	}
}
