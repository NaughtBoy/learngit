package com.dartou.lib.utils;

import java.lang.reflect.Field;

import android.app.Activity;
import android.view.WindowManager;

/**
 *开启硬件加速
 * @author cyn
 *
 */
public class HardwareUtil {
	public static void hardware_accelerated(Activity activity) {
		if (getAndroidSDKVersion() > 10) {
			Class c = WindowManager.LayoutParams.class;

			Field fs[] = c.getDeclaredFields();
			boolean isTrue = false;
			int t = -10000;
			for (int i = 0; i < fs.length; i++) {
				if (fs[i].getName().equals("FLAG_HARDWARE_ACCELERATED")) {
					isTrue = true;
					try {
						t = fs[i].getInt(new WindowManager.LayoutParams());
					} catch (IllegalArgumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				}
			}
			if (isTrue) {
				activity.getWindow().setFlags(t, t);
			}
		}
	}

	public static int getAndroidSDKVersion() {
		int version = 0;
		try {
			version = Integer.valueOf(android.os.Build.VERSION.SDK);
		} catch (NumberFormatException e) {

		}
		return version;
	}
}
