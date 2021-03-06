package com.example.yaohao.testproject.mvp.codelogin;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.yaohao.testproject.MainActivity;
import com.example.yaohao.testproject.R;
import com.example.yaohao.testproject.Word;
import com.example.yaohao.testproject.mvp.base.MvpActivity;
import com.example.yaohao.testproject.retrofit.DefaultParser;
import com.example.yaohao.testproject.utils.CheckUtils;
import com.example.yaohao.testproject.utils.LogUtils;
import com.example.yaohao.testproject.utils.MoveUtils;
import com.example.yaohao.testproject.utils.ToastUtils;

import java.util.HashMap;

import butterknife.ButterKnife;
import butterknife.InjectView;


/**
 * 验证码登录
 *
 * @ClassName:YanZhengMaLoginActivity
 * @PackageName:eiyudensetsu.ginga.youxin.com.yinheyingxiong.activity
 * @Create On 2018/3/15   14:19
 * @author:gongchenghao
 * @Copyrights 2018/3/15 宫成浩 All rights reserved.
 */

public class CodeLoginActivity extends MvpActivity<CodeLoginPresenter> implements CodeLoginView, View.OnClickListener {

    @InjectView(R.id.et_username)
    EditText mEtUsername;
    @InjectView(R.id.ll_input)
    LinearLayout mLlInput;
    @InjectView(R.id.et_phone)
    TextView mEtPhone;
    @InjectView(R.id.et_code)
    EditText mEtCode;
    @InjectView(R.id.tv_get_code)
    TextView mTvGetCode;
    @InjectView(R.id.tv_login)
    TextView mTvLogin;
    @InjectView(R.id.tv_forget)
    TextView mTvForget;
    @InjectView(R.id.ll_login_item)
    LinearLayout mLlLoginItem;
    @InjectView(R.id.tv_get_phone)
    TextView mTvGetPhone;

    private String phone;
    private TimeCount timeCount = new TimeCount(60000, 1000); //倒计时一分钟
    private Context mContext;
    private String[] promissions = {
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
    };
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yan_zheng_ma_login);
        ButterKnife.inject(this);
        initClick();
        isAndroidO();
        mContext = this;
    }

    private void initClick() {
        mTvLogin.setOnClickListener(this);
        mTvForget.setOnClickListener(this);
        mTvGetCode.setOnClickListener(this);
        mTvGetPhone.setOnClickListener(this);

        phone = getIntent().getStringExtra("phone");
        LogUtils.i("获取到的错误账号的手机号：" + phone);
        mEtPhone.setText(phone);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_login: //验证码登录
                codeLogin();
                break;
            case R.id.tv_forget:
//                MoveUtils.go(CodeLoginActivity.this, ForgetPasswordActivity.class);
                break;
            case R.id.tv_get_code: //获取验证码
                getCode();
                break;
            case R.id.tv_get_phone:
//                getPhone();
                break;
        }
    }
//    //获取手机号
//    private void getPhone() {
//        if (TextUtils.isEmpty(mEtUsername.getText().toString().trim())) {
//            ToastUtils.showShort("请先输入用户名");
//            return;
//        }
//        HashMap map = new HashMap();
//        map.put("username", mEtUsername.getText().toString().trim());
//        map.put("dataToken", Constants.dataToken);
//        LogUtils.i("判断账号是否存在的参数：" + map.toString());
//        mvpPresenter.postGetPhone(map);
//    }

    //验证码登录
    private void codeLogin() {
//        if (TextUtils.isEmpty(mEtUsername.getText().toString().trim())) {
//            ToastUtils.showShort(CodeLoginActivity.this, "请输入用户名");
//            return;
//        }
//        if (TextUtils.isEmpty(mEtPhone.getText().toString().trim())) {
//            ToastUtils.showShort(CodeLoginActivity.this, "请输入手机号");
//            return;
//        }
//        if (CheckUtils.isMobileNO(mEtPhone.getText().toString()) == false) {
//            ToastUtils.showShort(CodeLoginActivity.this, "请输入正确的手机号");
//            return;
//        }
//        if (TextUtils.isEmpty(mEtCode.getText().toString().trim())) {
//            ToastUtils.showShort(CodeLoginActivity.this, "请输入验证码");
//            return;
//        }

//        String sign = GetSignString.getSignString();
//        final String token = CreateToken.getMd5Token(sign);
//
//        HashMap map = new HashMap();
//        map.put("phone", mEtPhone.getText().toString().trim()); //手机号
//        map.put("username", mEtUsername.getText().toString().trim()); //用户名
//        map.put("code", mEtCode.getText().toString().trim()); //验证码
//        map.put("Landmarks", PhoneUtils.getIMEI()); //IMEI号
//        map.put("phoneModel", Build.MODEL); //手机型号
//        map.put("version", AppUtils.getVersionName(MyApp.getInstance().getApplicationContext())); //版本号
//        map.put("sign", sign);
//        map.put("loginToken", token);
//        map.put("dataToken", Constants.dataToken);
//
//        LogUtils.i("验证码登录的map：" + map.toString());
//        dialogShow("正在登陆");
//        mvpPresenter.postCodeLogin(Constants.CODE_LOGIN, map);
        MoveUtils.go(mContext, MainActivity.class);
        finish();
    }

    //获取验证码
    private void getCode() {
        LogUtils.i("getCode");
        if (TextUtils.isEmpty(mEtUsername.getText().toString().trim())) {
            ToastUtils.showShort("请输入用户名");
            return;
        }
        if (TextUtils.isEmpty(mEtPhone.getText().toString().trim())) {
            ToastUtils.showShort("请输入手机号");
            return;
        }
        if (CheckUtils.isMobileNO(mEtPhone.getText().toString()) == false) {
            ToastUtils.showShort(CodeLoginActivity.this, "请输入正确的手机号");
            return;
        }
//        HashMap map = new HashMap();
//        map.put("phone", mEtPhone.getText().toString().trim());
//        map.put("username", mEtUsername.getText().toString().trim());
//        map.put("mobile", Build.MODEL);
//        map.put("dataToken", Constants.dataToken);
//        dialogShow("正在获取验证码");
//        mvpPresenter.postGetCode(Constants.GET_CODE, map);
    }

    @Override
    protected CodeLoginPresenter createPresenter() {
        return new CodeLoginPresenter(this);
    }

    @Override
    public void codeLoginSuccess(String json) {
//        LogUtils.i("验证码登录的Json：" + json);
//        if (json != null) {
//            LoginBean loginBean = new DefaultParser<LoginBean>().parser(json, LoginBean.class);
//            if (loginBean == null) {
//                return;
//            }
//            int status = loginBean.getStatus();
//            if (status == 1) {
//                LogUtils.i("登录成功");
//                //保存验证码登录成功后的UserID,因为修改密码界面会用到userID
//                SPUtils.getInstance().put("uid", loginBean.getData().getUid());
//
//                //验证码登录成功后跳转到修改密码界面，从此处跳转到的修改密码界面不能点返回键
//                Intent intent = new Intent(CodeLoginActivity.this, ModifyPasswordActivity.class);
//                intent.putExtra("isBack", false);
//                startActivity(intent);
//                finish();
//            } else if (status == 10001) {
//                ToastUtils.showShort(CodeLoginActivity.this, "验证用户信息失败，请重新登录");
//            } else {
//                ToastUtils.showShort(CodeLoginActivity.this, loginBean.getErrMsg());
//            }
//        } else {
//            ToastUtils.showShort(CodeLoginActivity.this, "服务器错误");
//        }
    }

    @Override
    public void getCodeSuccess(String json) {
//        LogUtils.i("获取验证码的Json：" + json);
//        if (json != null) {
//            CodeBean codeBean = new DefaultParser<CodeBean>().parser(json, CodeBean.class);
//            if (codeBean == null) {
//                return;
//            }
//            int status = codeBean.getStatus();
//            if (status == 1) {
//                ToastUtils.showShort(CodeLoginActivity.this, "获取验证码成功");
//                timeCount.start(); //开始倒计时
//                mTvGetCode.setClickable(false);
//            } else {
//                ToastUtils.showShort(CodeLoginActivity.this, codeBean.getErrMsg());
//            }
//        } else {
//            ToastUtils.showShort(CodeLoginActivity.this, "服务器错误");
//        }
    }

    @Override
    public void getPhone(GetPhoneBean getPhoneBean) {
        if (getPhoneBean.getStatus() == 1) {
            mEtPhone.setText(getPhoneBean.getData());
        } else {
            ToastUtils.showShort(getPhoneBean.getErrMsg());
        }
    }

    @Override
    public void onFail(String msg) {
        ToastUtils.showShort(msg);
    }

    @Override
    public void onFinish() {
        dialogDismiss();
    }

    class TimeCount extends CountDownTimer {

        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            String s = millisUntilFinished / 1000 + "";
            mTvGetCode.setText(s);
        }

        @Override
        public void onFinish() {
            mTvGetCode.setText("获取验证码");
            mTvGetCode.setClickable(true);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        timeCount.cancel();
    }

    @Override
    protected void onPause() {
        super.onPause();
        onFinish();
    }


    //判断是否是Android8.0
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void isAndroidO() {
        LogUtils.i("isAndroidO");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) //如果是8.0系统，则判断是否缺少悬浮框权限和自动更新权限
        {
            //如果是8.0系统，首先判断是否具有悬浮框权限
//            boolean isAppOps = CanXuanFuUtils.getAppOps(this);
//            LogUtils.i("是否可以悬浮：" + isAppOps);
//            if (isAppOps == false) //如果不可以悬浮,申请悬浮权限
//            {
//                startXuanFuPermissionSettingActivity();
//                return;
//            }
            //如果直接就可以悬浮，则判断是否可以自动更新
            boolean hasInstallPermission = getPackageManager().canRequestPackageInstalls();
            LogUtils.i("是否可以自动更新：" + hasInstallPermission);
            if (hasInstallPermission == false) //如果不能自动更新，则跳转到允许安装未知来源应用的界面
            {
//                startInstallPermissionSettingActivity();
                return;
            }
            //如果既能悬浮，又能自动更新，则直接判断是否具有其他权限
            hasPromission();
        } else { //如果不是8.0系统，直接判断是否具有所需权限（针对6.0的权限）
            hasPromission();
        }
    }

    //判断是否具有权限
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void hasPromission() {
        boolean hasPromission = verifyPermissions(promissions);
        LogUtils.i("hasPromission");
        LogUtils.i("hasPromission：" + hasPromission);
        if (hasPromission) {
//            afterRequestPermissoion();
        } else {
            LogUtils.i("hasPromission——申请权限");
            requestPermissions(promissions, Word.REQUESTCODE_1); //申请权限
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        LogUtils.i("onRequestPermissionsResult_activity");
        LogUtils.i("requestCode:" + requestCode);
        if (requestCode == Word.REQUESTCODE_1) {
            boolean hasPromission = verifyPermissions(promissions);
            LogUtils.i("onRequestPermissionsResult_hasPromission:" + hasPromission);
            if (hasPromission) {
                afterRequestPermissoion();
            } else {
                LogUtils.i("onRequestPermissionsResult_hasPromission ===");
                showMissingPermissionDialog();
            }
        }
    }

    private void afterRequestPermissoion() {
        LogUtils.i("afterRequestPermissoion");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            boolean isAppOps = CanXuanFuUtils.getAppOps(this);
//            LogUtils.i("isAppOps:" + isAppOps);
//            if (isAppOps == false) {
//                ToastUtils.showShort(this, "缺少悬浮权限，提醒打点功能将受影响");
//            }
            boolean hasInstallPermission = getPackageManager().canRequestPackageInstalls();
            LogUtils.i("hasInstallPermission:" + hasInstallPermission);
            if (hasInstallPermission == false) {
                ToastUtils.showShort(this, "缺少自动更新权限，版本升级功能将受影响");
            } else {
//                login();
                ToastUtils.showShort(this, "1");
            }
        } else {
//            login();
            ToastUtils.showShort(this, "2");
        }
    }
}
