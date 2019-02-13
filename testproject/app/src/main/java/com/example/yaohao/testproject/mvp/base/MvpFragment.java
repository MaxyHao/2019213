package com.example.yaohao.testproject.mvp.base;


import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.yaohao.testproject.base.BaseFragment;


/**
 *
 * @ClassName:MvpFragment

 * @PackageName:com.wuxiaolong.androidmvpsample.mvp.other

 * @Create On 2018/5/18   16:18

 * @author:gongchenghao

 * @Copyrights 2018/5/18 宫成浩 All rights reserved.
 */

public abstract class MvpFragment<P extends BasePresenter> extends BaseFragment {
    protected P mvpPresenter;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mvpPresenter = createPresenter();
	}

    protected abstract P createPresenter();


    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        if (mvpPresenter != null) {
//            mvpPresenter.detachView();
//        }
    }
	public void showLoading() {
		dialogShow();
	}

	public void hideLoading() {
		dialogDismiss();
	}
}
