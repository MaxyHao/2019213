package com.example.yaohao.testproject.mvp.codelogin;


import com.example.yaohao.testproject.mvp.base.BaseView;

/**
 *
 * @ClassName:MainView

 * @PackageName:com.wuxiaolong.androidmvpsample.mvp.main

 * @Create On 2018/5/18   16:18

 * @author:gongchenghao

 * @Copyrights 2018/5/18 宫成浩 All rights reserved.
 */

public interface CodeLoginView extends BaseView {

    void codeLoginSuccess(String json);

    void getCodeSuccess(String json);

    void getPhone(GetPhoneBean getPhoneBean);

    void onFail(String msg);

    void onFinish();

}
