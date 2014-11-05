package com.dartou.lib.native_storage;

import java.io.File;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Environment;

/**
 * SharePrefrence数据操作
 * 
 * @author cyn
 * 
 */
public class AppSharePrefrence {

	/**
	 * 得到以包名为名字的xml文件
	 * 
	 * @param context
	 * @return
	 */
	private static SharedPreferences get(Context context) {
		SharedPreferences sh = context.getSharedPreferences(
				context.getPackageName(), 0);
		return sh;
	}

	/**
	 * 得到以name为名字的xml文件
	 * 
	 * @param context
	 * @return
	 */
	private static SharedPreferences get(Context context, String name) {
		SharedPreferences sh = context.getSharedPreferences(name, 0);
		return sh;
	}

	/**
	 * 从以包名为名字的xml文件中取出int数据
	 * 
	 * @param context
	 * @param key键
	 * @return 默认为0
	 */
	public static int getInt(Context context, String key) {
		return get(context).getInt(key, 0);
	}

	/**
	 * 从以name为名字的xml文件中取出int数据
	 * 
	 * @param context
	 * @param name
	 * @param key键
	 * @return 默认为0
	 */
	public static int getInt(Context context, String name, String key) {
		return get(context, name).getInt(key, 0);
	}

	/**
	 * 从以name为名字的xml文件中取出int数据
	 * 
	 * @param context
	 * @param name
	 * @param key键
	 * @param defValue默认值
	 * @return
	 */
	public static int getInt(Context context, String name, String key,
			int defValue) {
		return get(context, name).getInt(key, defValue);
	}

	/**
	 * 从以包名为名字的xml文件中取出boolean数据
	 * 
	 * @param context
	 * @param key键
	 * @return 默认为false
	 */
	public static boolean getBoolean(Context context, String key) {
		return get(context).getBoolean(key, false);
	}

	/**
	 * 从以name为名字xml文件中数据取出boolean数据
	 * 
	 * @param context
	 * @param name
	 * @param key键
	 * @return 默认为false
	 */
	public static boolean getBoolean(Context context, String name, String key) {
		return getBoolean(context, name, key, false);
	}
	
	/**
	 * 从以name为名字xml文件中数据取出boolean数据
	 * 
	 * @param context
	 * @param name
	 * @param key键
	 * @return 默认为def
	 */
	public static boolean getBoolean(Context context, String name, String key,boolean def) {
		return get(context, name).getBoolean(key, def);
	}

	/**
	 * 从以包名为名字的xml文件中取出long数据
	 * 
	 * @param context
	 * @param key键
	 * @return 默认为0L
	 */
	public static long getLong(Context context, String key) {
		return get(context).getLong(key, 0L);
	}

	/**
	 * 从以name为名字的xml文件中取出long数据
	 * 
	 * @param context
	 * @param name
	 * @param key键
	 * @return 默认为def
	 */
	public static long getLong(Context context, String name, String key,long def) {
		return get(context, name).getLong(key, def);
	}
	
	/**
	 * 从以name为名字的xml文件中取出long数据
	 * 
	 * @param context
	 * @param name
	 * @param key键
	 * @return 默认为0L
	 */
	public static long getLong(Context context, String name, String key) {
		return getLong(context, name, key, 0L);
	}

	/**
	 * 从以包名为名字的xml文件中取出String数据
	 * 
	 * @param context
	 * @param key键
	 * @return 默认为空字符串
	 */
	public static String getString(Context context, String key) {
		return get(context).getString(key, "");
	}

	/**
	 * 从以name为名字的xml文件中取出String数据
	 * 
	 * @param context
	 * @param name
	 * @param key键
	 * @return 默认为空字符串
	 */
	public static String getString(Context context, String name, String key) {
		return get(context, name).getString(key, "");
	}

	/**
	 * 从以name为名字的xml文件中取出String数据
	 * 
	 * @param context
	 * @param name
	 * @param key键
	 * @return 默认为def
	 */
	public static String getString(Context context, String name, String key,
			String defValue) {
		return get(context, name).getString(key, defValue);
	}

	/**
	 * 从以包名为名字的xml文件中取出float数据
	 * 
	 * @param context
	 * @param key键
	 * @return 默认为0.0f
	 */
	public static float getFloat(Context context, String key) {
		return get(context).getFloat(key, 0.0f);
	}

	/**
	 * 从以name为名字的xml文件中取出float数据
	 * 
	 * @param context
	 * @param name
	 * @param key键
	 * @return 默认为0.0f
	 */
	public static float getFloat(Context context, String name, String key) {
		return getFloat(context, name, key, 0.0f);
	}
	
	/**
	 * 从以name为名字的xml文件中取出float数据
	 * 
	 * @param context
	 * @param name
	 * @param key键
	 * @return 默认为def
	 */
	public static float getFloat(Context context, String name, String key,float def) {
		return get(context, name).getFloat(key, def);
	}

	/**
	 * 添加到以包名为名字xml文件中
	 * 
	 * @param context
	 * @param key
	 *            键
	 * @param value
	 *            值
	 */
	public static void put(Context context, String key, Object value) {
		put(context, context.getPackageName(), key, value);
	}

	/**
	 * 添加到以name为名字的xml文件中
	 * 
	 * @param context
	 * @param key
	 *            键
	 * @param value
	 *            值
	 */
	public static void put(Context context, String name, String key,
			Object value) {
		SharedPreferences sh = get(context, name);
		Editor editor = sh.edit();
		if (value instanceof Integer) {
			editor.putInt(key, (Integer) value);
		} else if (value instanceof Boolean) {
			editor.putBoolean(key, (Boolean) value);
		} else if (value instanceof Long) {
			editor.putLong(key, (Long) value);
		} else if (value instanceof Float) {
			editor.putFloat(key, (Float) value);
		} else if (value instanceof String) {
			editor.putString(key, (String) value);
		}
		editor.commit();
	}

	/**
	 * 
	 * 删除位于包名为名字的xml文件中的某个值
	 * 
	 * @param context
	 * @param key
	 *            键
	 */
	public static void remove(Context context, String key) {
		remove(context, context.getPackageName(), key);
	}

	/**
	 * 
	 * 删除位于name为名字的xml文件中的某个值
	 * 
	 * @param context
	 * @param key
	 *            键
	 */
	public static void remove(Context context, String name, String key) {
		SharedPreferences sh = get(context, name);
		Editor editor = sh.edit();
		editor.remove(key);
		editor.commit();
	}

	/**
	 * 清除以包名为名字的xml文件
	 * 
	 * @param context
	 */
	public static void clear(Context context) {
		SharedPreferences sh = context.getSharedPreferences(
				context.getPackageName(), 0);
		Editor editor = sh.edit();
		editor.clear();
		editor.commit();
	}

	/**
	 * 清除以name为名字的xml文件
	 * 
	 * @param context
	 */
	public static void clear(Context context, String name) {
		SharedPreferences sh = get(context, name);
		Editor editor = sh.edit();
		editor.clear();
		editor.commit();
	}
	
	/** * 清除本应用内部缓存(/data/data/com.xxx.xxx/cache) * * @param context */
    public static void cleanInternalCache(Context context) {
        deleteFilesByDirectory(context.getCacheDir());
    }

    /** * 清除本应用所有数据库(/data/data/com.xxx.xxx/databases) * * @param context */
    public static void cleanDatabases(Context context) {
        deleteFilesByDirectory(new File("/data/data/"
                + context.getPackageName() + "/databases"));
    }

    /**
     * * 清除本应用SharedPreference(/data/data/com.xxx.xxx/shared_prefs) * * @param
     * context
     */
    public static void cleanSharedPreference(Context context) {
        deleteFilesByDirectory(new File("/data/data/"
                + context.getPackageName() + "/shared_prefs"));
    }

    /** * 按名字清除本应用数据库 * * @param context * @param dbName */
    public static void cleanDatabaseByName(Context context, String dbName) {
        context.deleteDatabase(dbName);
    }

    /** * 清除/data/data/com.xxx.xxx/files下的内容 * * @param context */
    public static void cleanFiles(Context context) {
        deleteFilesByDirectory(context.getFilesDir());
    }

    /**
     * * 清除外部cache下的内容(/mnt/sdcard/android/data/com.xxx.xxx/cache) * * @param
     * context
     */
    public static void cleanExternalCache(Context context) {
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            deleteFilesByDirectory(context.getExternalCacheDir());
        }
    }

    /** * 清除自定义路径下的文件，使用需小心，请不要误删。而且只支持目录下的文件删除 * * @param filePath */
    public static void cleanCustomCache(String filePath) {
        deleteFilesByDirectory(new File(filePath));
    }


    /** * 删除方法 这里只会删除某个文件夹下的文件，如果传入的directory是个文件，将不做处理 * * @param directory */
    private static void deleteFilesByDirectory(File directory) {
        if (directory != null && directory.exists() && directory.isDirectory()) {
            for (File item : directory.listFiles()) {
                item.delete();
            }
        }
    }
    
    /** * 清除本应用所有的数据 * * @param context * @param filepath */
    public static void cleanApplicationData(Context context, String... filepath) {
        cleanInternalCache(context);
        cleanExternalCache(context);
        cleanDatabases(context);
        cleanSharedPreference(context);
        cleanFiles(context);
        for (String filePath : filepath) {
            cleanCustomCache(filePath);
        }
    }
}
