package com.example.yaohao.testproject.base;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import eiyudensetsu.ginga.youxin.com.yinheyingxiong.retrofit.ApiClient;
import eiyudensetsu.ginga.youxin.com.yinheyingxiong.retrofit.ApiStores;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;

/**
 * discription:
 * Created by Guo WenHui on 2018/6/2.
 * phone:13552163255
 */

public class BaseUtils {
	private CompositeDisposable mCompositeDisposable;
	private List<Call> calls;

	public ApiStores apiStores() {
		return ApiClient.retrofit().create(ApiStores.class);
	}

	public void addCalls(Call call) {
		if (calls == null) {
			calls = new ArrayList<>();
		}
		calls.add(call);
	}

	private void callCancel() {
		if (calls != null && calls.size() > 0) {
			for (Call call : calls) {
				if (!call.isCanceled())
					call.cancel();
			}
			calls.clear();
		}
	}
	//   参数一:Observable 即被观察者，它决定什么时候触发事件以及触发怎样的事件
//   参数二:Observer 即观察者，它决定事件触发的时候将有怎样的行为
	public <T> void addSubscription(Observable<T> observable, DisposableObserver<T> observer) {
		if (mCompositeDisposable == null) {
			mCompositeDisposable = new CompositeDisposable();
		}
		mCompositeDisposable.add(observer);

		observable.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(observer);
	}

	public void addSubscription(Disposable disposable) {
		if (mCompositeDisposable == null) {
			mCompositeDisposable = new CompositeDisposable();
		}
		mCompositeDisposable.add(disposable);
	}

	public void onUnsubscribe() {
		Log.i("test111","onUnSubscribe");
		//取消注册，以避免内存泄露
		if (mCompositeDisposable != null)
			mCompositeDisposable.dispose();
	}
}
