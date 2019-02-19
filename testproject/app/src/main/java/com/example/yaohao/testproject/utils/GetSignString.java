package com.example.yaohao.testproject.utils;

/**
 * 随机生成六位包含大小写字母的字符串
 * @ClassName:GetSignString

 * @PackageName:eiyudensetsu.ginga.youxin.com.yinheyingxiong.utils2

 * @Create On 2018/3/15   11:14

 * @author:gongchenghao

 * @Copyrights 2018/3/15 宫成浩 All rights reserved.
 */


public class GetSignString {
	public static String getSignString(){
		String randomcode2 = "";
		String model = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		char[] m = model.toCharArray();

		for (int j=0;j<6 ;j++ )
		{
			char c = m[(int)(Math.random()*52)];
			randomcode2 = randomcode2 + c;
		}
		return System.currentTimeMillis()+randomcode2;
	}
}
