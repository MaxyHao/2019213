package com.example.yaohao.testproject.mvp.codelogin;

import android.util.Log;

import com.example.yaohao.testproject.mvp.base.BasePresenter;
import com.example.yaohao.testproject.retrofit.ApiCallback;
import com.example.yaohao.testproject.retrofit.DefaultParser;
import com.example.yaohao.testproject.utils.LogUtils;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.ResponseBody;

/**
 *
 * @ClassName:MainPresenter

 * @PackageName:com.wuxiaolong.androidmvpsample.mvp.main

 * @Create On 2018/5/18   16:17

 * @author:gongchenghao

 * @Copyrights 2018/5/18 宫成浩 All rights reserved.
 */

public class CodeLoginPresenter extends BasePresenter<CodeLoginView> {

    public CodeLoginPresenter(CodeLoginView view) {
        attachView(view);
    }

    public void postCodeLogin(final String url, final HashMap map) {

        addSubscription(apiStores.postCodeLogin(map),
                new ApiCallback<ResponseBody>() {
                    @Override
                    public void onSuccess(ResponseBody responseBody) {
                        try {
                            String netJson = new String(responseBody.string());
                            mvpView.codeLoginSuccess(netJson);
                        } catch (IOException e) {
                            e.printStackTrace();
                            Log.i("test111","ResponseBody转化成Json字符串异常");
                        }
                    }
                    @Override
                    public void onFailure(String msg) {
                        mvpView.onFail(msg);
//                        ErrorLogUtils.sendErrorLog(url,msg);
                    }

                    @Override
                    public void onFinish() {
                        mvpView.onFinish();
                    }
                });
    }

	public void postGetCode(final String url, final HashMap map) {

		addSubscription(apiStores.postGetCode(map),
				new ApiCallback<ResponseBody>() {
					@Override
					public void onSuccess(ResponseBody responseBody) {
						try {
							String netJson = new String(responseBody.string());
							mvpView.getCodeSuccess(netJson);
						} catch (IOException e) {
							e.printStackTrace();
							Log.i("test111","ResponseBody转化成Json字符串异常");
						}
					}

					@Override
					public void onFailure(String msg) {
						mvpView.onFail(msg);
//                        ErrorLogUtils.sendErrorLog(url,msg);
					}

					@Override
					public void onFinish() {
						mvpView.onFinish();
					}
				});
	}

    //根据用户名获取手机号
    public void postGetPhone(final HashMap map) {

        addSubscription(apiStores.postGetPhone(map),
                new ApiCallback<ResponseBody>() {
                    @Override
                    public void onSuccess(ResponseBody responseBody) {
                        try {
                            String netJson = new String(responseBody.string());
                            LogUtils.i("根据用户名获取手机号的Json："+netJson);
                            GetPhoneBean getPhoneBean = new DefaultParser<GetPhoneBean>().parser(netJson, GetPhoneBean.class);
                            if (getPhoneBean == null) {
                                return;
                            }
                            mvpView.getPhone(getPhoneBean);
                        } catch (IOException e) {
                            e.printStackTrace();
                            Log.i("test111","ResponseBody转化成Json字符串异常");
                        }
                    }

                    @Override
                    public void onFailure(String msg) {
                        mvpView.onFail(msg);
//						ErrorLogUtils.sendErrorLog(url,msg);
                    }

                    @Override
                    public void onFinish() {
                        mvpView.onFinish();
                    }
                });
    }

}
