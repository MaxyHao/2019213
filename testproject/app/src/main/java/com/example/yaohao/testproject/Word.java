package com.example.yaohao.testproject;


import android.os.Environment;

import java.io.File;
import java.util.HashMap;

/**
 * Created by Administrator on 2017/3/1 0001.
 */

public class Word {

    public static String cacheDir = Environment.getExternalStorageDirectory().toString() + File.separator + "Test" + File.separator;

    public static int REQUESTCODE_LOGIN = 128; //6.0申请权限
    public static String SP_UID = "uid";
    public static String SP_GROUPID = "groupid";//角色
    public static String SP_GROUPNAME = "groupname";//职位名称
    public static String SP_NAME = "name";//用户名
    public static String SP_HEADIMG = "icon";//头像

    public static String PASS_UID = ""; //管理模块的更新标记

    //角色
    public static String ROLE_XIAOSHOU = "1";
    public static String ROLE_ZHIDAOYUAN = "2";
    public static String ROLE_DUDAO = "3";
    public static String ROLE_QUYUJINGLI = "4";
    public static String ROLE_DIQUTONGGUAN = "5";
    public static String ROLE_FENQUTONGGUAN = "6";
    public static String ROLE_ZONGBUCANMOU = "9";
    public static String ROLE_GUANLIYUAN = "10";

    //    ==== 闹钟提醒 ====
    public static String CLOCK_ALARM = "com.yinheyingxiong.android_data_notification.CLOLK"; //闹钟提醒的广播
    public static int CLOCK_REQUEST_ID = 126; //闹钟提醒的广播

    public static int INSTALL_PERMISS_CODE = 124; //8.0自动更新权限

    public static int XUAN_FU_KUANG_PERMISS_CODE = 125; //8.0悬浮框权限

    public static int REQUESTCODE_1 = 123; //6.0申请权限

    public static String updateUrl; //app更新路径

    //    ==== 更换头像
    public static String headaddress; //大头像
    public static String headaddress_sl; //缩略图
    public static String addressGroupID; //头像对应的职位



 }
