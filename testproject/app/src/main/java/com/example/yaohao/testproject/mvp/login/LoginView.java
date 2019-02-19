package com.example.yaohao.testproject.mvp.login;


import com.example.yaohao.testproject.mvp.base.BaseView;

/**
 *
 * @ClassName:MainView

 * @PackageName:com.wuxiaolong.androidmvpsample.mvp.main

 * @Create On 2018/5/18   16:18

 * @author:gongchenghao

 * @Copyrights 2018/5/18 宫成浩 All rights reserved.
 */

public interface LoginView extends BaseView {

    void onLoginSuccess(LoginBean loginBean);

    void isAccountExist(IsUserExistBean isUserExistBean);

    void getDataFail(String msg);

    void onFinish();

}
