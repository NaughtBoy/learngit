package com.dartou.lib.crash;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.Thread.UncaughtExceptionHandler;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Environment;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.dartou.lib.utils.LogUtil;

/**
 * 拦截崩溃异常处理类,当程序发生崩溃异常的时候,有该类来接管程序,并记录发送错误报告.
 * 
 * @author cyn
 * 
 */
public class CrashHandler implements UncaughtExceptionHandler {

	public static final String TAG = "CrashHandler";

	// 系统默认的UncaughtException处理类
	private Thread.UncaughtExceptionHandler mDefaultHandler;
	// CrashHandler实例
	private static CrashHandler INSTANCE = new CrashHandler();
	// 程序的Context对象
	private Context mContext;
	// 用来存储设备信息和异常信息
	private Map<String, String> infos = new HashMap<String, String>();

	// 用于格式化日期,作为日志文件名的一部分
	private DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");

	/** 保证只有一个CrashHandler实例 */
	private CrashHandler() {
	}

	/** 获取CrashHandler实例 ,单例模式 */
	public static CrashHandler getInstance() {
		return INSTANCE;
	}

	/**
	 * 初始化
	 * 
	 * @param context
	 * @param packname 日志文件夹名字
	 */
	public void init(Context context,String packname) {
		setFilesName(packname);
		mContext = context;
		// 获取系统默认的UncaughtException处理器
		mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
		// 设置该CrashHandler为程序的默认处理器
		Thread.setDefaultUncaughtExceptionHandler(this);

		try {
			PackageManager pm = context.getPackageManager();
			PackageInfo pi = pm.getPackageInfo(context.getPackageName(),
					PackageManager.GET_ACTIVITIES);
			if (pi != null) {

				versionName = pi.versionName == null ? "null" : pi.versionName;
				versionCode = pi.versionCode + "";
				infos.put("versionName", versionName);
				infos.put("versionCode", versionCode);
			}
		} catch (NameNotFoundException e) {
			Log.e(TAG, "an error occured when collect package info", e);
		}

		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			filepath = Environment.getExternalStorageDirectory().getPath()
					+ "/" + mPackname + "/crash/";
			File dir = new File(filepath);
			if (!dir.exists()) {
				dir.mkdirs();
			}
		}
	}

	/**
	 * 设置日志文件夹名称
	 * @param packname
	 */
	private void setFilesName(String packname){
		this.mPackname = packname;
	}
	
	/**
	 * 当UncaughtException发生时会转入该函数来处理
	 */
	@Override
	public void uncaughtException(Thread thread, Throwable ex) {
		if (!handleException(ex) && mDefaultHandler != null) {
			// 如果用户没有处理则让系统默认的异常处理器来处理
			mDefaultHandler.uncaughtException(thread, ex);
		} else {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				Log.e(TAG, "error : ", e);
			}
			// 退出程序
			android.os.Process.killProcess(android.os.Process.myPid());
			System.exit(0);
			
//			Intent intent = mContext.getPackageManager().getLaunchIntentForPackage(mContext.getPackageName());  
//	        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);  
//	        mContext.startActivity(intent);
		}
	}

	/**
	 * 自定义错误处理,收集错误信息 发送错误报告等操作均在此完成.
	 * 
	 * @param ex
	 * @return true:如果处理了该异常信息;否则返回false.
	 */
	private boolean handleException(Throwable ex) {
		if (ex == null) {
			return false;
		}
		// 使用Toast来显示异常信息
		new Thread() {
			@Override
			public void run() {
				Looper.prepare();
				Toast.makeText(mContext, mCrashPromot, Toast.LENGTH_LONG)
						.show();
				Looper.loop();
			}
		}.start();
		
		if(mICrashBeforeHandler!=null){
			mICrashBeforeHandler.before();
		}
		
		// 收集设备参数信息
		collectDeviceInfo(mContext);
		// 保存日志文件
		saveCrashInfo2File(ex);
		return true;
	}

	private String mPackname;

	private String versionName;

	private String versionCode;
	
	private String mCrashPromot ;
	/**
	 * 设置崩溃提示
	 * @param crashPromot
	 */
	public void setCrashPromot(String crashPromot){
		mCrashPromot = crashPromot;
	}

	/**
	 * 收集设备参数信息
	 * 
	 * @param ctx
	 */
	public void collectDeviceInfo(Context ctx) {

		Field[] fields = Build.class.getDeclaredFields();
		for (Field field : fields) {
			try {
				field.setAccessible(true);
				infos.put(field.getName(), field.get(null).toString());
				Log.d(TAG, field.getName() + " : " + field.get(null));
			} catch (Exception e) {
				Log.e(TAG, "an error occured when collect crash info", e);
			}
		}
	}

	/**
	 * 保存错误信息到文件中
	 * 
	 * @param ex
	 * @return 返回文件名称,便于将文件传送到服务器
	 */
	private String saveCrashInfo2File(Throwable ex) {

		StringBuffer sb = new StringBuffer();
		for (Map.Entry<String, String> entry : infos.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue();
			sb.append(key + "=" + value + "\n");
		}

		Writer writer = new StringWriter();
		PrintWriter printWriter = new PrintWriter(writer);
		ex.printStackTrace(printWriter);
		Throwable cause = ex.getCause();
		while (cause != null) {
			cause.printStackTrace(printWriter);
			cause = cause.getCause();
		}
		printWriter.close();
		String result = writer.toString();
		sb.append(result);
		result = sb.toString();
		LogUtil.e("crash", result);
		mICrashLog.log(result);
		return null;
	}

	public String getLogFileName(String result) {
		String fileName = null;
		Pattern p = Pattern
				.compile("Caused by(\\D*\\d*)\\(\\w+\\.java:\\d+\\)\\n");
		Matcher m = p.matcher(result);
		if (m.find()) {
			String s1 = m.group();
			Pattern p1 = Pattern.compile("\\(\\w+\\.java:\\d+\\)");
			Matcher m1 = p1.matcher(s1);
			if (m1.find()) {
				String classInfo = m1.group();
				int index = classInfo.indexOf(".");
				int index1 = classInfo.indexOf(":");
				//类名+行数+版本+时间
				fileName = classInfo.substring(1, index)+ "_"
						+ classInfo.substring(index1 + 1,classInfo.length() - 1) + "_" 
						+ versionCode+"_"
						+System.currentTimeMillis()
						+ ".txt";
			}
		}

		if (TextUtils.isEmpty(fileName)) {
			Pattern p1 = Pattern.compile("\\(\\w+\\.java:\\d+\\)");
			Matcher m1 = p1.matcher(result);
			if (m1.find()) {
				String classInfo = m1.group();
				int index = classInfo.indexOf(".");
				int index1 = classInfo.indexOf(":");
				//类名+行数+版本+时间
				fileName = classInfo.substring(1, index)+ "_"
						+ classInfo.substring(index1 + 1,classInfo.length() - 1) + "_" 
					    + versionCode+"_"
						+System.currentTimeMillis()
						+ ".txt";
			}
		}
		
		if(TextUtils.isEmpty(fileName)){
			fileName = "other_" + versionCode +"_"+System.currentTimeMillis()+ ".txt";
		}
		return fileName;
	}

	/**
	 * 保存数据
	 * @param result 返回绝对路径
	 * @return
	 */
	public String saveNative(String result) {
		if(TextUtils.isEmpty(filepath)){
			return "";
		}
		String fileName = getLogFileName(result);
		try {
			FileOutputStream fos = new FileOutputStream(filepath + fileName);
			fos.write(result.getBytes());
			fos.close();
			return filepath + fileName;
		} catch (Exception e) {
			Log.e(TAG, "an error occured while writing file...", e);
		}
		return "";
	}
	
	public String filepath;
	private ICrashLog mICrashLog;
	public void setICrashLog(ICrashLog mICrashLog) {
		this.mICrashLog = mICrashLog;
	}
	
	private ICrashBeforeHandler mICrashBeforeHandler;
	public void setICrashBeforeHandler(ICrashBeforeHandler mICrashBeforeHandler) {
		this.mICrashBeforeHandler = mICrashBeforeHandler;
	}
	
	
}