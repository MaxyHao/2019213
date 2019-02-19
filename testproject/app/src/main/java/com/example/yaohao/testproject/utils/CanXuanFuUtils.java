package com.example.yaohao.testproject.utils;

import android.app.AppOpsManager;
import android.content.Context;
import android.os.Binder;

import java.lang.reflect.Method;

/**
 * 是否可以悬浮
 * @ClassName:CanXuanFuUtils

 * @PackageName:eiyudensetsu.ginga.youxin.com.yinheyingxiong.utils2

 * @Create On 2018/6/6   10:58

 * @author:gongchenghao

 * @Copyrights 2018/6/6 宫成浩 All rights reserved.
 */


public class CanXuanFuUtils {
	//判断是否可以悬浮
	public static boolean getAppOps(Context context) {
		try {
			Object object = context.getSystemService(Context.APP_OPS_SERVICE);
			if (object == null) {
				return false;
			}
			Class localClass = object.getClass();
			Class[] arrayOfClass = new Class[3];
			arrayOfClass[0] = Integer.TYPE;
			arrayOfClass[1] = Integer.TYPE;
			arrayOfClass[2] = String.class;
			Method method = localClass.getMethod("checkOp", arrayOfClass);
			if (method == null) {
				return false;
			}
			Object[] arrayOfObject1 = new Object[3];
			arrayOfObject1[0] = Integer.valueOf(24);
			arrayOfObject1[1] = Integer.valueOf(Binder.getCallingUid());
			arrayOfObject1[2] = context.getPackageName();
			int m = ((Integer) method.invoke(object, arrayOfObject1)).intValue();
			return m == AppOpsManager.MODE_ALLOWED;
		} catch (Exception ex) {
//			LogUtils.i("判断是否可以悬浮出现异常："+ex.getMessage().toString());
		}
		return false;
	}
}
