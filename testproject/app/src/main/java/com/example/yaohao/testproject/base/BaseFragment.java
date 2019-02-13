package com.example.yaohao.testproject.base;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import eiyudensetsu.ginga.youxin.com.yinheyingxiong.common.MyApp;
import eiyudensetsu.ginga.youxin.com.yinheyingxiong.dialog.ProgressDialog;
import eiyudensetsu.ginga.youxin.com.yinheyingxiong.utils.LogUtils;
import eiyudensetsu.ginga.youxin.com.yinheyingxiong.utils.NetUtils;
import eiyudensetsu.ginga.youxin.com.yinheyingxiong.utils.SystemDialogUtils;
import eiyudensetsu.ginga.youxin.com.yinheyingxiong.utils.ToastUtils;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;


/**
 * Created by Administrator on 2017/3/2 0002.
 */

public class BaseFragment extends Fragment {

    public Context mContext;
    public MyApp myApp;
    public ProgressDialog progressDialog;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
        myApp = MyApp.getInstance();
		progressDialog = new ProgressDialog(getContext());
	}

    public void dialogShow(String message) {
		//先检查网络，如果未联网，则不显示加载框
		boolean connected = NetUtils.isAvailableByPing();
		LogUtils.i("connected:" + connected);
		if (connected == false) { //如果没有网络
			ToastUtils.showShort(getContext(), "请检查网络...");
			return;
		}
		if (progressDialog == null)
		{
			progressDialog = new ProgressDialog(getContext());
		}
        progressDialog.setMsg(message);
//        progressDialog.setCancelable(false);
		if (progressDialog.isShowing() == false)
		{
			progressDialog.show();
		}
    }

    public void dialogShow() {
        dialogShow("加载中...");
    }

    public void dialogDismiss() {
		if (progressDialog != null)
		{
			progressDialog.dismiss();
		}
    }
    public void dialogComplete(ProgressDialog.OnCompleteListener listener, String complMessage) {
        progressDialog.complete(listener, complMessage);
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
			if (ContextCompat.checkSelfPermission(getActivity(), result) != PackageManager.PERMISSION_GRANTED) {
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
		LogUtils.i("showMissingPermissionDialog_fragment");
		new SystemDialogUtils().showMissingPermissionDialog(
				getActivity(),
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

//	---------------------------- mvp中用到的 -------------------------------------------
	@Override
	public void onDestroy() {
		super.onDestroy();
		onUnsubscribe();
		//用来检测Fragment中的内存泄漏
//		RefWatcher refWatcher = MyApp.getInstance().getRefWatcher();
//		if (refWatcher != null)
//		{
//			refWatcher.watch(this);
//		}
	}

	private CompositeDisposable mCompositeDisposable;

	public void onUnsubscribe() {
		//取消注册，以避免内存泄露
		if (mCompositeDisposable != null) {
			mCompositeDisposable.dispose();
		}
	}

	public void addSubscription(DisposableObserver observer) {
		mCompositeDisposable = new CompositeDisposable();
		mCompositeDisposable.add(observer);
	}
}
