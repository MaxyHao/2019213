package com.example.yaohao.testproject.mvp.shop;

import android.util.Log;

import com.example.yaohao.testproject.mvp.base.BasePresenter;
import com.example.yaohao.testproject.mvp.login.IsUserExistBean;
import com.example.yaohao.testproject.mvp.login.LoginBean;
import com.example.yaohao.testproject.mvp.login.LoginView;
import com.example.yaohao.testproject.retrofit.ApiCallback;
import com.example.yaohao.testproject.retrofit.DefaultParser;
import com.example.yaohao.testproject.utils.LogUtils;
import com.example.yaohao.testproject.utils.SPUtils;
import com.example.yaohao.testproject.utils.ToastUtils;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.ResponseBody;

/**
 * @ClassName:MainPresenter
 * @PackageName:com.wuxiaolong.androidmvpsample.mvp.main
 * @Create On 2018/5/18   16:17
 * @author:gongchenghao
 * @Copyrights 2018/5/18 宫成浩 All rights reserved.
 */

public class ShopPresenter extends BasePresenter<LoginView> {

    public ShopPresenter(LoginView view) {
        attachView(view);
    }

    public void postLogin(final String url, final HashMap map) {

        addSubscription(apiStores.postLogin(map),
                new ApiCallback<ResponseBody>() {
                    @Override
                    public void onSuccess(ResponseBody responseBody) {
                        try {
                            String netJson = new String(responseBody.string());
                            LogUtils.i("登录返回的Json:" + netJson);
                            LoginBean loginBean = new DefaultParser<LoginBean>().parser(netJson, LoginBean.class);
                            if (loginBean == null) {
                                ToastUtils.showShort("登录失败，请重新登录");
                                return;
                            }
                            SPUtils.getInstance().put("loginJson", netJson); //保存登录的json

                            mvpView.onLoginSuccess(loginBean);
                        } catch (IOException e) {
                            e.printStackTrace();
                            Log.i("test111", "ResponseBody转化成Json字符串异常");
                        }
                    }

                    @Override
                    public void onFailure(String msg) {
                        mvpView.getDataFail(msg);
                    }

                    @Override
                    public void onFinish() {
                        mvpView.onFinish();
                    }
                });
    }


}
