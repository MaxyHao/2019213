package com.example.yaohao.testproject.utils;

import android.text.TextUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5加盐加密
 * @ClassName:CreateToken

 * @PackageName:eiyudensetsu.ginga.youxin.com.yinheyingxiong.utils2

 * @Create On 2018/3/15   11:28

 * @author:gongchenghao

 * @Copyrights 2018/3/15 宫成浩 All rights reserved.
 */


public class CreateToken {
	public static String getMd5Token(String sign) {
		if (TextUtils.isEmpty(sign)) {
			return "";
		}
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
			byte[] bytes = md5.digest(("889ead93ad8b3585a3f2346975178650dc41f0fe7"+sign).getBytes());
			String result = "";
			for (byte b : bytes) {
				String temp = Integer.toHexString(b & 0xff);
				if (temp.length() == 1) {
					temp = "0" + temp;
				}
				result += temp;
			}

			//对md5加盐加密后的结果再进行md5加密
			byte[] bytes2 = md5.digest(result.getBytes());
			String result2 = "";
			for (byte b : bytes2) {
				String temp2 = Integer.toHexString(b & 0xff);
				if (temp2.length() == 1) {
					temp2 = "0" + temp2;
				}
				result2 += temp2;
			}

			return result2;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return "";
	}
}
