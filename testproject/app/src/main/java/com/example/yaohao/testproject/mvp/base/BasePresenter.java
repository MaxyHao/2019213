package com.example.yaohao.testproject.mvp.base;

import com.example.yaohao.testproject.retrofit.ApiClient;
import com.example.yaohao.testproject.retrofit.ApiStores;
import com.example.yaohao.testproject.utils.LogUtils;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;


/**
 *
 * @ClassName:BasePresenter

 * @PackageName:com.wuxiaolong.androidmvpsample.mvp.other

 * @Create On 2018/5/18   16:18

 * @author:gongchenghao

 * @Copyrights 2018/5/18 宫成浩 All rights reserved.
 */

//CompositeDisposable:一种一次性容器，可容纳多个其他可弃置物品，并提供O(1)添加和删除复杂性。
public class BasePresenter<V> {
    public V mvpView;
    protected ApiStores apiStores;
    private CompositeDisposable mCompositeDisposable;

    public void attachView(V mvpView) {
        this.mvpView = mvpView;
        apiStores = ApiClient.retrofit().create(ApiStores.class);
    }


    public void detachView() {
        LogUtils.i("detachView");
        //当网络请求还没有结束时，如果在此处置空的话，网络请求返回数据，这时需要在网络请求中判断
//        mvpView是否为空，否则如果在网络请求未结束时就返回了上一个界面，在detachView()方法中就会
//        将mvpView置空，当网络请求结束后，还会用到mvpView，这时的mvpView是空的
//        this.mvpView = null;
        onUnSubscribe();
    }


    //RxJava取消注册，以避免内存泄露
    public void onUnSubscribe() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.dispose();
        }
    }


    public void addSubscription(final Observable observable, final DisposableObserver observer) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }

        mCompositeDisposable.add(observer);

        observable.subscribeOn(Schedulers.io()) //在IO线程进行网络请求
                .observeOn(AndroidSchedulers.mainThread()) //回到主线程，处理请求结果
                .subscribeWith(observer);

//        LogUtils.i("isAccountCanUse");
//        HashMap map = new HashMap();
//        String uid = SPUtils.getInstance().getString("uid");
//        //如果用户名为空或者不需要调用异地登录的接口，则走下面的方法
//        if (TextUtils.isEmpty(uid) || Word.isReLogin == false)
//        {
//            LogUtils.i("uid为空，不用判断账号是否需要重新登录");
//            if (mCompositeDisposable == null) {
//                mCompositeDisposable = new CompositeDisposable();
//            }
//
//            mCompositeDisposable.add(observer);
////          subscribeOn(Schedulers.io())
////          I/O 操作（读写文件、读写数据库、网络信息交互等）所使用的 Scheduler。
////          行为模式和 newThread() 差不多，区别在于 io() 的内部实现是是用一个无数量上限的线程池，
////          可以重用空闲的线程，因此多数情况下 io() 比 newThread() 更有效率。不要把计算工作放在 io() 中，
////          可以避免创建不必要的线程。
//
////          subscribeOn():指定 subscribe() 所发生的线程，即 Observable.OnSubscribe 被激活时所处的线程。或者叫做事件产生的线程。
////          observeOn(): 指定 Subscriber 所运行在的线程。或者叫做事件消费的线程。
////          subscribeOn(Schedulers.io())和observeOn(AndroidSchedulers.mainThread())实现的是在后台线程取数据，在主线程显示
//            observable.subscribeOn(Schedulers.io())  // 指定 subscribe() 发生在 IO 线程
//                    .observeOn(AndroidSchedulers.mainThread()) // 指定 Subscriber 的回调发生在主线程
//                    .subscribeWith(observer);
//            return;
//        }
//        map.put("uid", SPUtils.getInstance().getString("uid"));
//        map.put("Landmarks", PhoneUtils.getIMEI());
//
//        LogUtils.i("判断账号是否需要重新登录的map:"+ map.toString());
//        Call<ResponseBody> call = ApiClient.retrofit().create(ApiStores.class).postIsAccountCanUse(map);
//        call.enqueue(new RetrofitCallback<ResponseBody>() {
//            @Override
//            public void onSuccess(ResponseBody model) {
//                try {
//                    String netJson = new String(model.string());
//                    LogUtils.i("判断账号是否需要重新登录的Json："+netJson);
//                    IsNeedReLoginBean isNeedReLoginBean = new DefaultParser<IsNeedReLoginBean>().parser(netJson, IsNeedReLoginBean.class);
//                    if (isNeedReLoginBean != null && (isNeedReLoginBean.getStatus()==0 || isNeedReLoginBean.getStatus() == 2)) {
//                        LogUtils.i("重新登录");
//                        ToastUtils.showShort(isNeedReLoginBean.getMsg());
//                        //跳转到登录接口
//                        Intent intent = new Intent(MyApp.getInstance().getBaseContext(), LoginActivity.class);
//                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                        MyApp.getInstance().getBaseContext().startActivity(intent);
//
//                        //清空数据
//                        Word.PASS_UID = SPUtils.getInstance().getString(Word.SP_UID);
//                        ExitUtils.reSet(); //退出登录后需要重置的内容
//                    }else if (isNeedReLoginBean != null && isNeedReLoginBean.getStatus()==1){
//                        LogUtils.i("不需要重新登录");
//                        if (mCompositeDisposable == null) {
//                            mCompositeDisposable = new CompositeDisposable();
//                        }
//
//                        mCompositeDisposable.add(observer);
//
//                        observable.subscribeOn(Schedulers.io())
//                                .observeOn(AndroidSchedulers.mainThread())
//                                .subscribeWith(observer);
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void onFailure(int code, String msg) {
//
//            }
//
//            @Override
//            public void onThrowable(Throwable t) {
//
//            }
//
//            @Override
//            public void onFinish() {
//            }
//        });
    }
}
