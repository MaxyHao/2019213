package com.example.yaohao.testproject.mvp.oldcar;

import com.example.yaohao.testproject.mvp.base.BaseView;

/**
 * Created by yaohao on 2019/2/18.
 */

public interface OldCarListView extends BaseView {

    void onFindCarListSuccess();
    void onFail(String msg);
    void onFinish();
}
