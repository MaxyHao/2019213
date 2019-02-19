package com.example.yaohao.testproject.retrofit;


import android.util.Log;

import com.example.yaohao.testproject.utils.LogUtils;

import io.reactivex.observers.DisposableObserver;
import retrofit2.HttpException;


/**
 *
 * @ClassName:ApiCallback

 * @PackageName:com.wuxiaolong.androidmvpsample.retrofit

 * @Create On 2018/5/18   16:19

 * @author:gongchenghao

 * @Copyrights 2018/5/18 宫成浩 All rights reserved.
 */
//ApiCallback:继承的RxJava的DisposableObserver（观察者），用来观察ApiStores类中的比如postPersonalInfo()这种
//方法中的ResponseBody的变化，一旦ResponseBody有变，就调用onNext()方法或onError()方法。
public abstract class ApiCallback<M> extends DisposableObserver<M> {

    public abstract void onSuccess(M model);

    public abstract void onFailure(String msg);

    public abstract void onFinish();


    @Override
    public void onError(Throwable e) {

        e.printStackTrace();
        if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            //httpException.response().errorBody().string()
            int code = httpException.code();
            String msg = httpException.getMessage();
            Log.i("test111","code=" + code);
            if (code == 504) {
                msg = "网络不给力";
            }
            if (code == 502 || code == 404) {
                msg = "服务器异常，请稍后再试";
            }
            onFailure(code+msg);
        } else {
//            onFailure(e.getMessage());
            LogUtils.i("ApicallBack:onFailure:"+e.getMessage().toString());
            onFailure("服务器异常，请稍后再试");
        }
        onFinish();
    }

    @Override
    public void onNext(M model) {
        onSuccess(model);
    }

    @Override
    public void onComplete() {
        onFinish();
    }
}
