package com.example.yaohao.testproject.mvp.base;


import com.example.yaohao.testproject.base.BaseUtils;

/**
 *
 * @ClassName:MvpActivity

 * @PackageName:com.wuxiaolong.androidmvpsample.mvp.other

 * @Create On 2018/5/18   16:18

 * @author:gongchenghao

 * @Copyrights 2018/5/18 宫成浩 All rights reserved.
 */

public abstract class MvpUtils<P extends BasePresenter> extends BaseUtils {
    protected P mvpPresenter;

    public void instence()
	{
		mvpPresenter = createPresenter();
	}

    protected abstract P createPresenter();


    public void showLoading() {
    }

    public void hideLoading() {
    }
}
