package com.schoolinfo.layout;

/**
 * 所有界面的枚举
 * @author huangwubin 2014-9-21
 */
public enum RunLayout {
	MAINGALLERY,LOSTLAYOUT,OLDLAYOUT,RELEASELAYOUT,LOGINLAYOUT,PERSONALLAYOUT;
	
	public static RunLayout getRunLayout(BaseLayout layout){
		if (layout instanceof LostLayout) {
			return LOSTLAYOUT;
		}
		else if (layout instanceof ReleaseLayout) {
			return RELEASELAYOUT;
		}
		else if (layout instanceof OldLayout) {
			return OLDLAYOUT;
		}
		else if (layout instanceof LoginLayout) {
			return LOGINLAYOUT;
		}
		else if (layout instanceof PersonalLayout) {
			return PERSONALLAYOUT;
		}
		return null;
	}
	public static RunLayout getRunLayout(MainGalleryLayout layout){
		return MAINGALLERY;
	}
}
