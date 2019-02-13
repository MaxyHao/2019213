/**
 * ClassName: Constants.java
 * created on 2013-1-24
 * Copyrights 2013-1-24 hjgang All rights reserved.
 * site: http://t.qq.com/hjgang2012
 * email: hjgang@yahoo.cn
 */
package com.example.yaohao.testproject;

import android.content.Context;

import java.io.File;

/**
 * @author yaohao
 * @日期 2017-12-25
 */

public final class Constants {
    /**
     * 系统初始化配置文件名
     */
    public static final String SYSTEM_INIT_FILE_NAME = "yh.android.sysini";
    public static final String FLAG = "com.yh.android";


    /**
     * 图片类型
     */
    public static final String IMAGE_UNSPECIFIED = "image/*";
    /**
     * 本地缓存目录
     */
    public static String CACHE_DIR;
    /**
     * 图片缓存目录
     */
    public static String CACHE_DIR_IMAGE;
    /**
     * 待上传图片缓存目录
     */
    public static String CACHE_DIR_UPLOADING_IMG;
    /**
     * 图片目录
     */
    public static String CACHE_IMAGE;
    /**
     * 图片名称
     */
    public static final String PHOTO_PATH = "handongkeji_android_photo";
    /**
     * 语音缓存目录
     */
    public static String CACHE_VOICE;

    public static void init(Context context) {
        CACHE_DIR = context.getCacheDir().getAbsolutePath();
        File file = new File(CACHE_DIR, "image");
        file.mkdirs();
        CACHE_IMAGE = file.getAbsolutePath();
        CACHE_DIR_IMAGE = CACHE_IMAGE;
        file = new File(CACHE_DIR, "temp");
        file.mkdirs();
        CACHE_DIR_UPLOADING_IMG = file.getAbsolutePath();
        file = new File(CACHE_DIR, "voice");
        file.mkdirs();
        CACHE_VOICE = file.getAbsolutePath();

        file = new File(CACHE_DIR, "html");
        file.mkdirs();
        ENVIROMENT_DIR_CACHE = file.getAbsolutePath();
    }

    public static String ENVIROMENT_DIR_CACHE;

    private Constants() {

    }

    /**
     * 数据库版本号
     */
    public static final int DB_VERSION = 1;
    /**
     * 数据库名
     */
    public static final String DB_NAME = "android.db";

    //图片服务器地址
//    public static final String URL_IMAGE = "http://www.hhhxin.com/";
    public static final String URL_IMAGE = "http://neibu.hhhxin.com/";

    //接口服务器地址
//    public static final String URL_CONTEXTPATH = "http://www.hhhxin.com/index.php/";
    public static final String URL_CONTEXTPATH = "http://neibu.hhhxin.com/index.php/";

    //测试h5域名
//    public static final String URL_H5 = "http://www.hhhxin.com/";
    public static final String URL_H5 = "http://neibu.hhhxin.com/";

    public static String dataToken = "5ED1A79225747AC29F7BD9DE7AB6A1EE"; //每个接口都要加

//    ==============================================================================================================

    //登录 ---- 调完
    public static String LOGIN = URL_CONTEXTPATH +
            "xk/Login/applogin";

    //判断用户账号是否存在 ---- 调完
    public static String USER_IS_EXIST = URL_CONTEXTPATH +
            "xk/user/isExistence";

    //验证码登录、 ---- 正在调
    public static String CODE_LOGIN = URL_CONTEXTPATH +
            "xk/Login/codeLogin";

    //验证码登录接口获取验证码  --- 调完
    public static String GET_CODE = URL_CONTEXTPATH +
            "xk/Login/getUserPhone";

    //忘记密码 --- 调完
    public static String FORGET_PASSWORD = URL_CONTEXTPATH +
            "xk/Login/updatePwd";

    //验证原密码是否正确 --- 调完
    public static String VERYFY_OLDPASSWORD = URL_CONTEXTPATH +
            "xk/user/pwdIsRight";

    //修改旧密码 --- 调完
    public static String MODIFY_OLDPASSWORD = URL_CONTEXTPATH +
            "xk/user/editPwd";

    //银币明细列表--已调
    public static String YB_DETAIL_LIST = URL_CONTEXTPATH +
            "xk/Integralmall/getMyYbObjectList";

    //公告列表 --- 已调
    public static String GET_GONG_GAO_LIST = URL_CONTEXTPATH +
            "xk/Notice/getNoticeList";

    //公告详情 --- 调完
    public static String GET_GONG_GAO_DETAIL = URL_CONTEXTPATH +
            "xk/Notice/getNoticeMsg";

    //公告详情中的发送回执 --- 调完
    public static String GONG_GAO_HUI_ZHI = URL_CONTEXTPATH +
            "xk/Notice/sendReply";

    //确认收到任务 --- 调完
    public static String GET_REN_WU = URL_CONTEXTPATH +
            "xk/Notice/taskButton";


    //获取个人榜数据--已调
    public static String SELF_RANKING_LIST = URL_CONTEXTPATH +
            "xk/Integralmall/getSingleGameList";

    //获取区域榜数据--已调
    public static String WD_RANKING_LIST = URL_CONTEXTPATH +
            "xk/Integralmall/getAreaGameList";

    //商品详情
    public static String GOODS_DETAIL = URL_CONTEXTPATH +
            "xk/Integralmall/getGoodsMsg";
    //获取兑换人信息
    public static String GET_EXCHANGE_PEOPLE_INFO = URL_CONTEXTPATH +
            "xk/Integralmall/getPricerMsg";

    //提交兑换--已调
    public static String COMMIT_EXCHANGE = URL_CONTEXTPATH +
            "xk/Integralmall/goodsExchange";


    //日报记录列表
    public static String DAILY_REEORD_LIST = URL_CONTEXTPATH +
            "xk/daily/getRblList";


    //获取日报详情数据
    public static String GET_DAILY_DETAIL = URL_CONTEXTPATH +
            "xk/daily/getSubmitContent";

    //我的订单列表 --- 已调
    public static String MY_ORDER_LIST = URL_CONTEXTPATH +
            "xk/Integralmall/getMyOrderList";

    //我的订单--确认收货 --已调
    public static String CONFIREM_ORDER = URL_CONTEXTPATH +
            "xk/Integralmall/confirmOrder";

    //我的订单--删除订单 --已调
    public static String DETLETE_ORDER = URL_CONTEXTPATH +
            "xk/Integralmall/delMyOrder";

    //打点
    public static String DA_DIAN = URL_CONTEXTPATH +
            "xk/user/dadian";

    //获取个人积分信息--调完
    public static String GET_USER_JIFEN = URL_CONTEXTPATH +
            "cd/Integral/getInteregalRecord";

    //弹幕接口 --- 调完
    public static String DAN_MU = URL_CONTEXTPATH +
            "xk/Operation/getBarrageData";

    //获取头像 --- 调完
    public static String GET_TOU_XIANG = URL_CONTEXTPATH +
            "xk/user/getAvatarData";

    //修改头像 --- 调完
    public static String MODIFY_TOU_XIANG = URL_CONTEXTPATH +
            "xk/user/editAvatar";


    //富豪榜，费沙头数据 --已调
    public static String HEADER_DATA = URL_CONTEXTPATH +
            "xk/Integralmall/getAllMsg";

    //费沙-商品分类 --已调
    public static String GOODS_SORT = URL_CONTEXTPATH +
            "xk/Integralmall/getShopType";

    //用途选择 --已调
    public static String YONGTU_CHOOSE = URL_CONTEXTPATH +
            "xk/Integralmall/getGoodsPurpose";

    //获取个人信息 --- 调完
    public static String PERSONAL_INFO = URL_CONTEXTPATH +
            "xk/user/getUserMsg";

    //管理--休假审批 --- 调完
    public static String XIUJIA_APPROVAL = URL_CONTEXTPATH +
            "pz/Vacationrecords/vacations_examination_list";

    //管理--休假审批--通过 --- 调完
    public static String AGREE_XIUJIA = URL_CONTEXTPATH +
            "pz/Vacationrecords/agreeLeaveMsg";

    //管理--休假审批 --拒绝--- 调完
    public static String REFUSE_XIUJIA = URL_CONTEXTPATH +
            "pz/Vacationrecords/refuseLeaveMsg";

    //日报记录列表--销售--- 调完
    public static String DAILYRECORD_SALES_LIST = URL_CONTEXTPATH +
            "pz/sale_daily_daily/get_sale_recordlist";

    //日报记录--销售详情--- 调完
    public static String DAILYRECORD_SALES_DETAIL = URL_CONTEXTPATH +
            "pz/sale_daily_daily/sale_record_info";

    //管理--销售--首页- 调完
    public static String MANAGER_SALES_HOME = URL_CONTEXTPATH +
            "pz/sale_daily_daily/getsale_todaydaily";

    //管理--销售日报详情--- 调完
    public static String MANAGER_SALES_DAILY_DETAIL = URL_CONTEXTPATH +
            "pz/sale_daily_daily/sale_daily_info";

    //管理--销售--提交日报- 调完
    public static String MANAGER_SALES_COMMIT_DAILY = URL_CONTEXTPATH +
            "pz/sale_daily_daily/sub_sale_today";

    //管理--销售--保存明日任务- 调完
    public static String SAVE_TOM_TASK = URL_CONTEXTPATH +
            "pz/sale_daily_daily/sale_tom_plan";

    //管理--管理者--日报管理首页- 调完
    public static String DAILY_MANAGE_HOME_DATA = URL_CONTEXTPATH +
            "pz/manager_sale_daily/get_manager_daily_list";

    //管理--管理者--个人日报详情- 调完
    public static String LEADER_PERSONAL_DAILY_DETAIL = URL_CONTEXTPATH +
            "pz/manager_sale_daily/get_manager_daily_info";


    //管理--管理者--提交个人日报- 调完
    public static String SUBMIT_LEADER_PERSONAL_DAILY = URL_CONTEXTPATH +
            "pz/manager_sale_daily/submit_manager_daily";

    //管理--团队日报--下级列表详情- 调完
    public static String NEXT_LIST = URL_CONTEXTPATH +
            "pz/manager_sale_daily/get_sp_daily_info";

    //管理--团队日报--个人日报详情- 调完
    public static String GUANLI_DAILY_DETAIL = URL_CONTEXTPATH +
            "pz/manager_sale_daily/get_daily_info";

    //管理--团队日报--保存个人日报详情审批- 调完
    public static String SAVE_APPROVAL = URL_CONTEXTPATH +
            "pz/manager_sale_daily/approval_daily";

    //日报记录列表--管理者--- 调完
    public static String DAILYRECORD_MANAGER_LIST = URL_CONTEXTPATH +
            "pz/manager_daily_record/get_mamager_record";

    //日报记录--管理者个人日报--- 调完
    public static String DAILYRECORD_MANAGER_PERSONAL = URL_CONTEXTPATH +
            "pz/manager_daily_record/get_mamager_record_info";

    //日报记录--管理者团队日报列表--- 调完
    public static String DAILYRECORD_MANAGER_TEAMDAILY_LIST = URL_CONTEXTPATH +
            "pz/manager_daily_record/get_mamager_record_teaminfo";

    //日报记录--管理者团队日报下级列表--- 调完
    public static String DAILYRECORD_MANAGER_TEAMDAILY_XIACENG_LIST = URL_CONTEXTPATH +
            "pz/manager_daily_record/get_manger_team_next_info";

    //日报记录--管理者团队日报--销售记录详情--- 调完
    public static String DAILYRECORD_MANAGER_TEAMDAILY_XSDETAIL = URL_CONTEXTPATH +
            "pz/manager_daily_record/get_manger_team_daily_info";

    //数据概览页面链接--- 调完
    public static String SHUJU_GAILAN_URL = URL_CONTEXTPATH +
            "xk/Operation/getH5Url";

    //订单详情
    public static String ORDER_DETAIL = URL_CONTEXTPATH +
            "xk/Integralmall/getMyOrderMsg";
}
