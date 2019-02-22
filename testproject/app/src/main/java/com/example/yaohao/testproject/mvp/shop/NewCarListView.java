package com.example.yaohao.testproject.mvp.shop;

import com.example.yaohao.testproject.mvp.base.BaseView;

/**
 * Created by yaohao on 2019/2/18.
 */

public interface NewCarListView extends BaseView {

    void onFindNewCarListSuccess();
    void onFail(String msg);
    void onFinish();
}
