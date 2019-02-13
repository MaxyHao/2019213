package com.example.yaohao.testproject.retrofit;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * @ClassName:ApiStores
 * @PackageName:com.wuxiaolong.androidmvpsample.retrofit
 * @Create On 2018/5/18   16:19
 * @author:gongchenghao
 * @Copyrights 2018/5/18 宫成浩 All rights reserved.
 */


//retrofit请求网络的方法: https://www.jianshu.com/p/7687365aa946
public interface ApiStores {
//    path是对应于url里直接写value的，如：http://www.test/news/1;
//    query是对应于url里面有key-value对的，如：http://www.test/news/id=1;

//    Retrofit 除了提供了传统的 Callback 形式的 API,还有 RxJava 版本的 Observable 形式 API。
//    当 RxJava 形式的时候，Retrofit 把请求封装进 Observable ，在请求结束后调用 onNext() 或在请求失败后调用 onError()。

//    使用传统callback形式时，retrofit的json在RetrofitCallback类中
//

    //baseUrl:初始化的时候，就已经将baseUrl写入Retrofit中了 线上服务器地址
//    public static final String URL_CONTEXTPATH = "http://www.hhhxin.com/index.php/";
    public static final String URL_CONTEXTPATH = "http://neibu.hhhxin.com/index.php/";


// =======================   Get请求直接返回Json的写法 =======================

    //加载天气
//    @GET("adat/sk/{cityId}.html")
//    Call<ResponseBody> loadDataByRetrofit(@Path("cityId") String cityId);


    //加载天气
//    @GET("adat/sk/{cityId}.html")
//    Observable<ResponseBody> loadDataByRetrofitRxJava(@Path("cityId") String cityId);

// =======================   Get请求需要传入Bean的写法 =======================
    //加载天气
//    @GET("adat/sk/{cityId}.html")
//    Call<MainModel> loadDataByRetrofit1(@Path("cityId") String cityId);


    //加载天气
//    @GET("adat/sk/{cityId}.html")
//    Observable<MainModel> loadDataByRetrofitRxJava1(@Path("cityId") String cityId);


    //只使用retrofit访问网络的示例
//	@FormUrlEncoded
//	@POST("user/getUserMsg")
//	Call <ResponseBody> postDemo(@FieldMap Map<String, String> map);

//  ============================ Post请求，直接返回Json的方法 ==================

    //获取个人信息 --- 调完
    @FormUrlEncoded
    @POST("Apps/xk/user/getUserMsg")
    Observable<ResponseBody> postPersonalInfo(@FieldMap Map<String, String> map);

    //向后台发送错误接口
    @FormUrlEncoded
    @POST("Apps/xk/Operation/getAppErrorLog")
    Call<ResponseBody> postSendError(@FieldMap Map<String, String> map);

    //判断账号是否存在 --- 调完
    @FormUrlEncoded
    @POST("Apps/xk/user/isExistence")
    Observable<ResponseBody> postIsAccountExist(@FieldMap Map<String, String> map);

    //判断账号是否停用 -- 调完
    @FormUrlEncoded
    @POST("AppHome/Moneycapturerule/get_login_url")
    Call<ResponseBody> postIsAccountCanUse(@FieldMap Map<String, String> map);

    //登录 --- 调完
    @FormUrlEncoded
    @POST("Apps/xk/Login/applogin")
    Observable<ResponseBody> postLogin(@FieldMap Map<String, String> map);

    //获取验证码  --- 调完
    @FormUrlEncoded
    @POST("Apps/xk/Login/getUserPhone")
    Observable<ResponseBody> postGetCode(@FieldMap Map<String, String> map);

    //忘记密码 --- 调完
    @FormUrlEncoded
    @POST("Apps/xk/Login/updatePwd")
    Observable<ResponseBody> postForgetPassword(@FieldMap Map<String, String> map);

    //验证码登录  ---- 调完
    @FormUrlEncoded
    @POST("Apps/xk/Login/codeLogin")
    Observable<ResponseBody> postCodeLogin(@FieldMap Map<String, String> map);

    //验证旧密码 ---- 调完
    @FormUrlEncoded
    @POST("Apps/xk/user/pwdIsRight")
    Observable<ResponseBody> postVeryfiOldPassword(@FieldMap Map<String, String> map);

    //重置密码 ---- 调完
    @FormUrlEncoded
    @POST("Apps/xk/user/editPwd")
    Observable<ResponseBody> postReSetPassword(@FieldMap Map<String, String> map);

    //银币明细列表 --- 已调
    @FormUrlEncoded
    @POST("Apps/xk/Integralmall/getMyYbObjectList")
    Observable<ResponseBody> postYBMingXiList(@FieldMap Map<String, String> map);

    //获取公告列表 --- 已调
    @FormUrlEncoded
    @POST("Apps/xk/Notice/getNoticeList")
    Observable<ResponseBody> postGongGaoList(@FieldMap Map<String, String> map);

    //删除公告 --- 调完
    @FormUrlEncoded
    @POST("Apps/xk/Notice/deleteNotice")
    Call<ResponseBody> postDeleteGongGao(@FieldMap Map<String, String> map);

    //获取公告详情 --- 调完
    @FormUrlEncoded
    @POST("Apps/xk/Notice/getNoticeMsg")
    Observable<ResponseBody> getGongGaoDetail(@FieldMap Map<String, String> map);

    //获取公告回执 --- 调完
    @FormUrlEncoded
    @POST("Apps/xk/Notice/sendReply")
    Observable<ResponseBody> postGetGongGaoHuiZhi(@FieldMap Map<String, String> map);

    //确认任务  --- 调完
    @FormUrlEncoded
    @POST("Apps/xk/Notice/taskButton")
    Observable<ResponseBody> postRenWuQueRen(@FieldMap Map<String, String> map);


    //个人榜--已调
    @FormUrlEncoded
    @POST("Apps/xk/Integralmall/getSingleGameList")
    Observable<ResponseBody> postSelfRankingUrl(@FieldMap Map<String, String> map);


    //区域榜--已调
    @FormUrlEncoded
    @POST("Apps/xk/Integralmall/getAreaGameList")
    Observable<ResponseBody> postWDRankingUrl(@FieldMap Map<String, String> map);

    //订单详情
    @FormUrlEncoded
    @POST("Apps/xk/Integralmall/getMyOrderMsg")
    Observable<ResponseBody> postOrderDetail(@FieldMap Map<String, String> map);

    //获取兑换人信息
    @FormUrlEncoded
    @POST("Apps/xk/Integralmall/getPricerMsg")
    Observable<ResponseBody> postGetExchangePeopleInfo(@FieldMap Map<String, String> map);

    //提交兑换
    @FormUrlEncoded
    @POST("Apps/xk/Integralmall/goodsExchange")
    Observable<ResponseBody> postCommitExchange(@FieldMap Map<String, String> map);


    //商品详情
    @FormUrlEncoded
    @POST("Apps/xk/Integralmall/getGoodsMsg")
    Observable<ResponseBody> postGoodsDetail(@FieldMap Map<String, String> map);


    //日报记录列表--销售--跳完
    @FormUrlEncoded
    @POST("Apps/pz/sale_daily_daily/get_sale_recordlist")
    Observable<ResponseBody> postSalesDailyRecordList(@FieldMap Map<String, String> map);

    //日报记录--销售详情--跳完
    @FormUrlEncoded
    @POST("Apps/pz/sale_daily_daily/sale_record_info")
    Observable<ResponseBody> postSalesDailyRecordDetail(@FieldMap Map<String, String> map);


    //日报记录列表--管理者
    @FormUrlEncoded
    @POST("Apps/pz/manager_daily_record/get_mamager_record")
    Observable<ResponseBody> postLeaderDailyRecordList(@FieldMap Map<String, String> map);


    //日报详情
    @FormUrlEncoded
    @POST("Apps/xk/daily/getSubmitContent")
    Observable<ResponseBody> postDailyDetail(@FieldMap Map<String, String> map);


    //我的订单列表 --- 已调
    @FormUrlEncoded
    @POST("Apps/xk/Integralmall/getMyOrderList")
    Observable<ResponseBody> postGetMyOrderList(@FieldMap Map<String, String> map);

    //确认收货 --- 已调
    @FormUrlEncoded
    @POST("Apps/xk/Integralmall/confirmOrder")
    Observable<ResponseBody> postConfirmOrder(@FieldMap Map<String, String> map);

    //删除订单 --- 已调
    @FormUrlEncoded
    @POST("Apps/xk/Integralmall/delMyOrder")
    Observable<ResponseBody> postDeleteOrder(@FieldMap Map<String, String> map);

    //打点 --- 正在调
    @Multipart
    @POST("Apps/xk/User/dadian")
    Observable<ResponseBody> postDaDian2(@Part("uid") RequestBody uid,
                                         @Part("r_type") RequestBody r_type,
                                         @Part("city") RequestBody city,
                                         @Part("cl_type") RequestBody cl_type,
                                         @Part("cl_content") RequestBody cl_content,
                                         @Part("shid") RequestBody shid,
                                         @Part("sqid") RequestBody sqid,
                                         @Part("remark") RequestBody remark,
                                         @Part("dataToken") RequestBody dataToken,
                                         @Part MultipartBody.Part part);

    //判断用户是否在休假 -- 完成
    @FormUrlEncoded
    @POST("Apps/xk/user/userIsLeave")
    Call<ResponseBody> postIsXiuJia(@FieldMap Map<String, String> map);

    //版本更新 -- 调完
    @FormUrlEncoded
    @POST("AppHome/Operation/verion")
    Call<ResponseBody> postUpDate(@FieldMap Map<String, String> map);

    //向后台发送当前版本号 -- 调完
    @FormUrlEncoded
    @POST("AppHome/Moneycapturerule/update_version")
    Call<ResponseBody> postSendVersion(@FieldMap Map<String, String> map);

    //商城商品列表 ---已调
    @FormUrlEncoded
    @POST("Apps/xk/Integralmall/getMallMsg")
    Observable<ResponseBody> postShangCheng(@FieldMap Map<String, String> map);


    //获取积分列表--- 调完
    @FormUrlEncoded
    @POST("Apps/cd/Integral/getInteregalRecord")
    Observable<ResponseBody> postGetJiFenMXList(@FieldMap Map<String, String> map);

    //弹幕 --- 调完
    @FormUrlEncoded
    @POST("Apps/xk/Operation/getBarrageData")
    Observable<ResponseBody> postDanMu(@FieldMap Map<String, String> map);


    //获取头像 ---- 调完
    @FormUrlEncoded
    @POST("Apps/xk/user/getAvatarData")
    Observable<ResponseBody> postGetTouXiang(@FieldMap Map<String, String> map);

    //修改头像 ---- 调完
    @FormUrlEncoded
    @POST("Apps/xk/user/editAvatar")
    Observable<ResponseBody> postModifyTouXiang(@FieldMap Map<String, String> map);

    //富豪榜,费沙补给站 头部数据 --- 已调
    @FormUrlEncoded
    @POST("Apps/xk/Integralmall/getAllMsg")
    Observable<ResponseBody> postheaderData(@FieldMap Map<String, String> map);

    //费沙补给站商品分类 --- 已调
    @FormUrlEncoded
    @POST("Apps/xk/Integralmall/getShopType")
    Observable<ResponseBody> postGoodsSortData(@FieldMap Map<String, String> map);

    //商品兑换时的用途选择 ---已调
    @FormUrlEncoded
    @POST("Apps/xk/Integralmall/getGoodsPurpose")
    Observable<ResponseBody> postYongTuChoose(@FieldMap Map<String, String> map);

    //我要休假 --- 调完
    @FormUrlEncoded
    @POST("Apps/pz/Vacationrecords/get_leave")
    Observable<ResponseBody> postWoYaoXiuJia(@FieldMap Map<String, String> map);

    //休假记录 --- 调完
    @FormUrlEncoded
    @POST("Apps/pz/Vacationrecords/examined_record")
    Observable<ResponseBody> postXiuJiaJiLu(@FieldMap Map<String, String> map);

    //删除休假记录 --- 调完
    @FormUrlEncoded
    @POST("Apps/pz/Vacationrecords/delete_record")
    Call<ResponseBody> postDeleteXiuJiaJiLu(@FieldMap Map<String, String> map);

    //管理--休假审批 --- 已调
    @FormUrlEncoded
    @POST("Apps/pz/Vacationrecords/vacations_examination_list")
    Observable<ResponseBody> postXiuJiaApprovalList(@FieldMap Map<String, String> map);

    //休假审批记录 --- 调完
    @FormUrlEncoded
    @POST("Apps/pz/Vacationrecords/get_val_app_record")
    Observable<ResponseBody> postXiuJiaShenPiJiLuList(@FieldMap Map<String, String> map);

    //删除休假审批记录 --- 调完
    @FormUrlEncoded
    @POST("Apps/pz/Vacationrecords/delete_app_record")
    Call<ResponseBody> postDeleteXiuJiaShenPiJiLu(@FieldMap Map<String, String> map);

    //管理--休假审批 --- 通过---已调
    @FormUrlEncoded
    @POST("Apps/pz/Vacationrecords/agreeLeaveMsg")
    Observable<ResponseBody> postAgreeXiuJia(@FieldMap Map<String, String> map);

    //管理--休假审批 --- 拒绝 --已调
    @FormUrlEncoded
    @POST("Apps/pz/Vacationrecords/refuseLeaveMsg")
    Observable<ResponseBody> postRefuseXiuJia(@FieldMap Map<String, String> map);

    //员工紧急休假 --完调
    @FormUrlEncoded
    @POST("Apps/pz/Vacationrecords/urgent_vacation")
    Observable<ResponseBody> postJinJiXiuJia(@FieldMap Map<String, String> map);

    //员工紧急休假界面的员工列表 --调完
    @FormUrlEncoded
    @POST("Apps/pz/Vacationrecords/get_user_regional_manager")
    Observable<ResponseBody> postJinJiXiuJiaYuanGongList(@FieldMap Map<String, String> map);


    //管理--销售 --调完
    @FormUrlEncoded
    @POST("Apps/pz/sale_daily_daily/getsale_todaydaily")
    Observable<ResponseBody> postManagerSalesData(@FieldMap Map<String, String> map);

    //管理--销售 --日报详情--调完
    @FormUrlEncoded
    @POST("Apps/pz/sale_daily_daily/sale_daily_info")
    Observable<ResponseBody> postSalesDailyDetail(@FieldMap Map<String, String> map);

    //管理--销售 --提交日报--调完
    @FormUrlEncoded
    @POST("Apps/pz/sale_daily_daily/sub_sale_today")
    Observable<ResponseBody> postManagerSalesCommitDaily(@FieldMap Map<String, String> map);

    //管理--销售 --保存明日任务--调完
    @FormUrlEncoded
    @POST("Apps/pz/sale_daily_daily/sale_tom_plan")
    Observable<ResponseBody> postSaveTomTask(@FieldMap Map<String, String> map);

    //管理--管理者 --日报管理--调完
    @FormUrlEncoded
    @POST("Apps/pz/manager_sale_daily/get_manager_daily_list")
    Observable<ResponseBody> postDailyManageData(@FieldMap Map<String, String> map);

    //管理--管理者 --个人日报详情--调完
    @FormUrlEncoded
    @POST("Apps/pz/manager_sale_daily/get_manager_daily_info")
    Observable<ResponseBody> postLeaderPersonalDailyDetail(@FieldMap Map<String, String> map);

    //管理--管理者 --提交个人日报--调完
    @FormUrlEncoded
    @POST("Apps/pz/manager_sale_daily/submit_manager_daily")
    Observable<ResponseBody> postSubmitLeaderPersonalDaily(@FieldMap Map<String, String> map);

    //管理--日报管理--团队日报--下级列表详情--调完
    @FormUrlEncoded
    @POST("Apps/pz/manager_sale_daily/get_sp_daily_info")
    Observable<ResponseBody> postNextList(@FieldMap Map<String, String> map);

    //管理--日报管理--日报详情--调完
    @FormUrlEncoded
    @POST("Apps/pz/manager_sale_daily/get_daily_info")
    Observable<ResponseBody> postGuanLi_DailyDetail(@FieldMap Map<String, String> map);

    //管理--日报管理--保存审批--调完
    @FormUrlEncoded
    @POST("Apps/pz/manager_sale_daily/approval_daily")
    Observable<ResponseBody> postSaveApproval(@FieldMap Map<String, String> map);

    //管理--日报管理 --提交团队日报--调完
    @FormUrlEncoded
    @POST("Apps/pz/manager_sale_daily/sub_team_daily")
    Observable<ResponseBody> postSubmitTeamDaily(@FieldMap Map<String, String> map);

    //任务 --- 正在调
    @FormUrlEncoded
    @POST("Apps/xk/Task/getNewTaskList")
    Observable<ResponseBody> postRenWu(@FieldMap Map<String, String> map);

    //分区 --- 调完
    @FormUrlEncoded
    @POST("Apps/xk/Galaxy/getGalaxyMsg")
    Observable<ResponseBody> postFenQu(@FieldMap Map<String, String> map);

    //地区 --- 调完
    @FormUrlEncoded
    @POST("Apps/xk/Galaxy/getGalaxyMsg")
    Observable<ResponseBody> postDiQu(@FieldMap Map<String, String> map);

    //网点 --- 调完
    @FormUrlEncoded
    @POST("Apps/xk/Galaxy/getGalaxyMsg")
    Observable<ResponseBody> postWangDian(@FieldMap Map<String, String> map);

    //获取打点信息 --- 调完
    @FormUrlEncoded
    @POST("Apps/xk/Galaxy/getSqidBySh")
    Observable<ResponseBody> postDaDianMessage(@FieldMap Map<String, String> map);


    //日报记录--管理者个人日报--调完
    @FormUrlEncoded
    @POST("Apps/pz/manager_daily_record/get_mamager_record_info")
    Observable<ResponseBody> postLeaderPersonalDaily(@FieldMap Map<String, String> map);

    //日报记录--管理者--团队日报列表--调完
    @FormUrlEncoded
    @POST("Apps/pz/manager_daily_record/get_mamager_record_teaminfo")
    Observable<ResponseBody> postLeaderDailySQList(@FieldMap Map<String, String> map);

    //日报记录--管理者--团队日报下层列表--调完
    @FormUrlEncoded
    @POST("Apps/pz/manager_daily_record/get_manger_team_next_info")
    Observable<ResponseBody> postLeaderDailyXCList(@FieldMap Map<String, String> map);

    //日报记录--管理者--团队日报--销售记录详情--调完
    @FormUrlEncoded
    @POST("Apps/pz/manager_daily_record/get_manger_team_daily_info")
    Observable<ResponseBody> postXSRecordDetail(@FieldMap Map<String, String> map);

    //差旅和外勤--调完
    @FormUrlEncoded
    @POST("Apps/xk/Galaxy/getOutMsg")
    Observable<ResponseBody> postChaiLvAndWaiQinMessage(@FieldMap Map<String, String> map);

    //获取大陆界面的商户列表--调完
    @FormUrlEncoded
    @POST("Apps/xk/Galaxy/getShList")
    Call<ResponseBody> postShangHuList(@FieldMap Map<String, String> map);

    //获取大陆界面的商户详情--调完
    @FormUrlEncoded
    @POST("Apps/xk/Galaxy/getShMsg")
    Call<ResponseBody> postShangHuDetail(@FieldMap Map<String, String> map);

    //获取差旅类型--调完
    @FormUrlEncoded
    @POST("Apps/xk/User/getClAndLeaveType")
    Call<ResponseBody> postGetChaiLvType(@FieldMap Map<String, String> map);

    //获取数据概览页面链接--调完
    @FormUrlEncoded
    @POST("Apps/xk/Operation/getH5Url")
    Observable<ResponseBody> postShuJuGaiLanUrl(@FieldMap Map<String, String> map);

    //判断差旅状态--调完
    @FormUrlEncoded
    @POST("Apps/xk/User/getTravelStatus")
    Call<ResponseBody> postChaiLvZhuangTai(@FieldMap Map<String, String> map);

    //撤销休假 -- 调完
    @FormUrlEncoded
    @POST("Apps/pz/Vacationrecords/revoke_vacation")
    Call<ResponseBody> postCheXiaoXiuJia(@FieldMap Map<String, String> map);

    //撤销休假 -- 调完
    @FormUrlEncoded
    @POST("Apps/pz/Vacationrecords/vacation_unapproved")
    Call<ResponseBody> postXiuJiaTiXing(@FieldMap Map<String, String> map);

    //请休假制度
    @FormUrlEncoded
    @POST("Apps/xk/Operation/getH5Url")
    Observable<ResponseBody> postXiuJiaZhiDu(@FieldMap Map<String, String> map);

    //领取奖励 --- 调完
    @FormUrlEncoded
    @POST("Apps/xk/Task/getSelfYb")
    Call<ResponseBody> postLingQuJiangLi(@FieldMap Map<String, String> map);

    //获取手机号 --- 调完
    @FormUrlEncoded
    @POST("Apps/pz/Vacationrecords/get_phone")
    Observable<ResponseBody> postGetPhone(@FieldMap Map<String, String> map);

    //任务界面获取当前银币和上月银币 --- 调完
    @FormUrlEncoded
    @POST("Apps/xk/Task/getMonthYb")
    Call<ResponseBody> postYinBi(@FieldMap Map<String, String> map);

    //积分申诉审批 --- 正在调
    @FormUrlEncoded
    @POST("fdsfs")
    Observable<ResponseBody> postJiFenShenSuShenPi(@FieldMap Map<String, String> map);

//    //订单详情
//    @FormUrlEncoded
//    @POST("xk/Integralmall/getMyOrderMsg")
//    Call<ResponseBody> postOrderDetail(@FieldMap Map<String, String> map);
}