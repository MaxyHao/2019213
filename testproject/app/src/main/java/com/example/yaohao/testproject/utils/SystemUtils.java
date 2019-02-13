package com.example.yaohao.testproject.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.Build;
import android.view.Display;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.ViewConfiguration;

import java.lang.reflect.Method;

/**
 * discription:
 * Created by Guo WenHui on 2018/4/19.
 * phone:13552163255
 */

public class SystemUtils {
	public boolean isHuaWei(Activity activity)
	{
		//获取厂商
		String changShang = Build.BRAND;
		String changShang1 = changShang.toLowerCase();
		if (changShang1.contains("huawei") || changShang1.contains("honor")){ //是华为手机
			boolean navigationBarShow = isNavigationBarShow(activity);
			return navigationBarShow;
		}else {
			boolean b = checkDeviceHasNavigationBar(activity);
			return b;
		}
	}

	//获取是否存在NavigationBar
	private   boolean checkDeviceHasNavigationBar(Context context) {
		boolean hasNavigationBar = false;
		Resources rs = context.getResources();
		int id = rs.getIdentifier("config_showNavigationBar", "bool", "android");
		if (id > 0) {
			hasNavigationBar = rs.getBoolean(id);
		}
		try {
			Class systemPropertiesClass = Class.forName("android.os.SystemProperties");
			Method m = systemPropertiesClass.getMethod("get", String.class);
			String navBarOverride = (String) m.invoke(systemPropertiesClass, "qemu.hw.mainkeys");
			if ("1".equals(navBarOverride)) {
				hasNavigationBar = false;
			} else if ("0".equals(navBarOverride)) {
				hasNavigationBar = true;
			}
		} catch (Exception e) {

		}
		return hasNavigationBar;
	}

	//用来判断华为手机是否有虚拟按键
	private boolean isNavigationBarShow(Activity activity){
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
			Display display = activity.getWindowManager().getDefaultDisplay();
			Point size = new Point();
			Point realSize = new Point();
			display.getSize(size);
			display.getRealSize(realSize);
			return realSize.y!=size.y;
		}else {
			boolean menu = ViewConfiguration.get(activity).hasPermanentMenuKey();
			boolean back = KeyCharacterMap.deviceHasKey(KeyEvent.KEYCODE_BACK);
			if(menu || back) {
				return false;
			}else {
				return true;
			}
		}
	}
}
