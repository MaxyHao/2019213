package com.example.yaohao.testproject.base;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.blankj.utilcode.util.Utils;
import com.example.yaohao.testproject.MyApplication;
import com.example.yaohao.testproject.dialog.ProgressDialog;
import com.example.yaohao.testproject.retrofit.ApiClient;
import com.example.yaohao.testproject.retrofit.ApiStores;
import com.example.yaohao.testproject.utils.LogUtils;
import com.example.yaohao.testproject.utils.NetUtils;
import com.example.yaohao.testproject.utils.SPUtils;
import com.example.yaohao.testproject.utils.SystemDialogUtils;
import com.example.yaohao.testproject.utils.SystemUtils;
import com.example.yaohao.testproject.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;


import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;


/**
 * Created by Administrator on 2017/3/1 0001.
 */

public class BaseActivity extends AppCompatActivity {

    public MyApplication myApp;
    public ProgressDialog progressDialog; //初始化加载框
    public SPUtils spUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String currentActivity = getClass().getSimpleName();
        if (currentActivity.contains("SplashActivity"))
        {
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            initenimate();
        }
        setQinRuShi();//设置侵入式状态栏

        myApp = (MyApplication) getApplicationContext();
        initSp(); //初始化Sp
    }

    //判断手机是佛具有虚拟按键
    public void setNavigationBar(View v_bottom) {
        boolean b = new SystemUtils().isHuaWei(this);
        LogUtils.i("判断手机是否有虚拟按键："+b);
        if (b == false) {
            v_bottom.setVisibility(View.GONE);
        } else {
            v_bottom.setVisibility(View.VISIBLE);
        }
    }

    //设置侵入式状态栏
    private void setQinRuShi() {
        //设置本页面为侵入式状态栏的核心代码
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

            //给状态栏设置颜色。我设置透明。
            window.setStatusBarColor(Color.TRANSPARENT);
            window.setNavigationBarColor(Color.TRANSPARENT);
        }
    }
    //获取侵入式状态栏中状态栏的高度
    private int getStatusHeight()
    {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    private void initSp() {
        Utils.init(this);
        spUtils = SPUtils.getInstance("TestProject");
    }

    public void dialogShow(String message) {
		//先检查网络，如果未联网，则不显示加载框
		boolean connected = NetUtils.isAvailableByPing();
		LogUtils.i("connected:" + connected);
		if (connected == false) { //如果没有网络
			ToastUtils.showShort(this, "请检查网络...");
			return;
		}
		if (progressDialog == null)
        {
            LogUtils.i("新建progressDialog");
            progressDialog = new ProgressDialog(this);
        }
        progressDialog.setMsg(message);
        if (progressDialog.isShowing() == false)
        {
            LogUtils.i("==== 出现加载框 ====");
            progressDialog.show();
        }
    }

    public void dialogShow() {
        dialogShow("加载中...");
    }

    public void dialogDismiss() {
		if (progressDialog != null)
		{
            LogUtils.i("dialogDismiss");
			progressDialog.dismiss();
		}
    }
    public void dialogComplete(ProgressDialog.OnCompleteListener listener, String complMessage) {
        progressDialog.complete(listener, complMessage);
    }



    //    Lollipop 中Activity和 Fragment的过渡动画是基于 Android一个叫作 Transition 的新特性实现的。
//    初次引入这个特性是在 KitKat 中，Transition 框架提供了一个方便的 API 来构建应用中不同 UI 状态切换时的动画。
//    这个框架始终围绕两个关键概念:场景和过渡。
    private void initenimate() {
        Object o = setenim(getWindow());
        if (o == null) {
            Transition slide_left = null;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                slide_left = TransitionInflater.from(this).inflateTransition(android.R.transition.explode);
                getWindow().setEnterTransition(slide_left);
                Transition slide_right = TransitionInflater.from(this).inflateTransition(android.R.transition.fade);
                getWindow().setExitTransition(slide_right);
            }
        }
    }

    public Object setenim(Window window) {
        return null;
    }

    @Override
    protected void onResume() {
        //设置界面不能横屏
        if(getRequestedOrientation()!= ActivityInfo.SCREEN_ORIENTATION_PORTRAIT){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        super.onResume();

        String currentActivity = getClass().getSimpleName();
        if (!currentActivity.contains("SplashActivity")
                && !currentActivity.contains("LoginActivity")
                && !currentActivity.contains("ForgetPasswordActivity")
                && !currentActivity.contains("ModifyPasswordActivity"))
        {
            //除了splash界面和登录界面，其他界面未登录时都finish掉
            String token = SPUtils.getInstance().getString("token");
            if (TextUtils.isEmpty(token))
            {
                LogUtils.i("未登录，finish界面");
                finish();
            }
        }
    }


    /**
     * 检测是否所有的权限都已经授权
     * @param grantResults
     * @return
     * @since 2.5.0
     *
     */
    public boolean verifyPermissions(String[] grantResults) {
        for (String result : grantResults) {
            if (ContextCompat.checkSelfPermission(this, result) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    /**
     * 显示提示信息
     *
     * @since 2.5.0
     */
    public void showMissingPermissionDialog() {
		LogUtils.i("showMissingPermissionDialog_activity");
        new SystemDialogUtils().showMissingPermissionDialog(
                this,
                "提示",
                "当前应用缺少必要权限。请点击\"设置\"-\"权限\"-打开所需权限。",
                "设置",
                "取消", new SystemDialogUtils.AfterClick() {
                    @Override
                    public void confirm() {
                        startAppSettings();
                    }

                    @Override
                    public void cancle() {
                    }
                });
    }
    /**
     *  启动应用的设置
     *
     * @since 2.5.0
     *
     */
    private void startAppSettings() {
        Log.i("test111","无权限，且用户点击了设置");

        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + "eiyudensetsu.ginga.youxin.com.yinheyingxiong"));
        startActivity(intent);
    }


//    ----------------------------------- mvp中用到的 -----------------------------------

	private CompositeDisposable mCompositeDisposable;
	private List<Call> calls;

	@Override
	protected void onDestroy() {
		callCancel();
		onUnsubscribe();
		super.onDestroy();
	}

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
