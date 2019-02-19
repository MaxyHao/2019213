package com.example.yaohao.testproject.mvp.login;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.yaohao.testproject.Constants;
import com.example.yaohao.testproject.MyApplication;
import com.example.yaohao.testproject.R;
import com.example.yaohao.testproject.Word;
import com.example.yaohao.testproject.mvp.base.MvpActivity;
//import com.example.yaohao.testproject.mvp.codelogin.CodeLoginActivity;
import com.example.yaohao.testproject.utils.AppUtils;
import com.example.yaohao.testproject.utils.CacheManager;
import com.example.yaohao.testproject.utils.CanXuanFuUtils;
import com.example.yaohao.testproject.utils.CreateToken;
import com.example.yaohao.testproject.utils.GetSignString;
import com.example.yaohao.testproject.utils.LogUtils;
import com.example.yaohao.testproject.utils.MoveUtils;
import com.example.yaohao.testproject.utils.NetUtils;
import com.example.yaohao.testproject.utils.PhoneUtils;
import com.example.yaohao.testproject.utils.SPUtils;
import com.example.yaohao.testproject.utils.ToastUtils;

import java.io.File;
import java.util.HashMap;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class LoginActivity extends MvpActivity<LoginPresenter> implements LoginView, View.OnClickListener {

    @InjectView(R.id.et_name)
    EditText mEtName;
    @InjectView(R.id.et_password)
    EditText mEtPassword;
    @InjectView(R.id.ll_input)
    LinearLayout mLlInput;
    @InjectView(R.id.tv_login)
    TextView mTvLogin;
    @InjectView(R.id.tv_forget_login)
    TextView mTvForget;
    @InjectView(R.id.iv_remeber)
    ImageView mIvRemeber;
    @InjectView(R.id.ll_remeber)
    LinearLayout mLlRemeber;

    private String[] promissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.CAMERA};
    private String token;
    private long p = 0;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.inject(this);

        initClick();
        initAccountIsExist();

    }

    private void initClick() {
        mTvLogin.setOnClickListener(this);
        mTvForget.setOnClickListener(this);
        mLlRemeber.setOnClickListener(this);

        if (TextUtils.isEmpty(SPUtils.getInstance().getString("etName")) == false) {
            mEtName.setText(SPUtils.getInstance().getString("etName")); //将用户输入的用户名回显在输入框上
        }
        if (TextUtils.isEmpty(SPUtils.getInstance().getString("password")) == false) {
            mEtPassword.setText(SPUtils.getInstance().getString("password")); //将用户输入的用户名回显在输入框上
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_login:
                //清空缓存（防止卸载掉重新安装后还有缓存）
                CacheManager.deleteChildDir(new File(Word.cacheDir + "ACache"));
                isAndroidO();
                break;
            case R.id.tv_forget_login:
//                MoveUtils.go(LoginActivity.this, ForgetPasswordActivity.class);
                break;
            case R.id.ll_remeber:
                mIvRemeber.setSelected(!mIvRemeber.isSelected());
                SPUtils.getInstance().put("isRemeber",mIvRemeber.isSelected());
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        boolean isRemeber = SPUtils.getInstance().getBoolean("isRemeber");
        if (isRemeber)
        {
            mIvRemeber.setSelected(true);
        }else {
            mIvRemeber.setSelected(false);
        }
    }

    private void initAccountIsExist() {
        //用户输入账号完成后，判断用户是否存在
        mEtName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b == false) //表示输入用户名的edittext失去了焦点
                {
                    isUserExist();
                }
            }
        });
    }

    //判断用户名是否存在
    private void isUserExist() {
        if (TextUtils.isEmpty(mEtName.getText().toString().trim())) {
            return;
        }
        HashMap map = new HashMap();
        map.put("username", mEtName.getText().toString().trim());
        map.put("dataToken", Constants.dataToken);
        LogUtils.i("判断账号是否存在的参数：" + map.toString());
        mvpPresenter.postIsAccountExist(Constants.USER_IS_EXIST, map);
    }

    //判断是否是Android8.0
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void isAndroidO() {
        LogUtils.i("isAndroidO");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) //如果是8.0系统，则判断是否缺少悬浮框权限和自动更新权限
        {
            //如果是8.0系统，首先判断是否具有悬浮框权限
            boolean isAppOps = CanXuanFuUtils.getAppOps(this);
            LogUtils.i("是否可以悬浮：" + isAppOps);
            if (isAppOps == false) //如果不可以悬浮,申请悬浮权限
            {
                startXuanFuPermissionSettingActivity();
                return;
            }
            //如果直接就可以悬浮，则判断是否可以自动更新
            boolean hasInstallPermission = getPackageManager().canRequestPackageInstalls();
            LogUtils.i("是否可以自动更新：" + hasInstallPermission);
            if (hasInstallPermission == false) //如果不能自动更新，则跳转到允许安装未知来源应用的界面
            {
                startInstallPermissionSettingActivity();
                return;
            }
            //如果既能悬浮，又能自动更新，则直接判断是否具有其他权限
            hasPromission();
        } else { //如果不是8.0系统，直接判断是否具有所需权限（针对6.0的权限）
            hasPromission();
        }
    }
    private void startXuanFuPermissionSettingActivity() {
        //注意这个是8.0新API
        Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
        intent.setData(Uri.parse("package:" + getPackageName()));
        startActivityForResult(intent, Word.XUAN_FU_KUANG_PERMISS_CODE);
    }
    private void startInstallPermissionSettingActivity() {
        //注意这个是8.0新API
        Uri packageURI = Uri.parse("package:" + getPackageName());
        Intent intent = new Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES, packageURI);
        startActivityForResult(intent, Word.INSTALL_PERMISS_CODE);
    }

    //判断是否具有权限
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void hasPromission() {
        boolean hasPromission = verifyPermissions(promissions);
        LogUtils.i("hasPromission");
        LogUtils.i("hasPromission：" + hasPromission);
        if (hasPromission) {
            afterRequestPermissoion();
        } else {
            LogUtils.i("hasPromission——申请权限");
            requestPermissions(promissions, Word.REQUESTCODE_1); //申请权限
        }
    }

    private void afterRequestPermissoion() {
        LogUtils.i("afterRequestPermissoion");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            boolean isAppOps = CanXuanFuUtils.getAppOps(this);
            LogUtils.i("isAppOps:" + isAppOps);
            if (isAppOps == false) {
                ToastUtils.showShort(this, "缺少悬浮权限，提醒打点功能将受影响");
            }
            boolean hasInstallPermission = getPackageManager().canRequestPackageInstalls();
            LogUtils.i("hasInstallPermission:" + hasInstallPermission);
            if (hasInstallPermission == false) {
                ToastUtils.showShort(this, "缺少自动更新权限，版本升级功能将受影响");
            } else {
                login();
            }
        } else {
            login();
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
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Word.XUAN_FU_KUANG_PERMISS_CODE) { //表示是申请悬浮框权限的，用于闹钟提醒的弹框
            if (resultCode == RESULT_OK) {
                LogUtils.i("允许悬浮");
            } else {
                LogUtils.i("不允许悬浮");
            }

            //申请完悬浮权限后，无论是否可以悬浮，都判断是否可以自动更新
            boolean hasInstallPermission = getPackageManager().canRequestPackageInstalls();
            LogUtils.i("是否可以自动更新：" + hasInstallPermission);
            if (hasInstallPermission == false) //如果不能自动更新，则跳转到允许安装未知来源应用的界面
            {
                startInstallPermissionSettingActivity();
                return;
            }
        }
        if (requestCode == Word.INSTALL_PERMISS_CODE) { //如果是请求更新的//允许安装未知来源
            if (resultCode == RESULT_OK) {
                LogUtils.i("安装应用");
            } else {
                LogUtils.i("不安装应用");
            }
            //申请完自动更新后，无论是否可以自动更新，都进行6.0的权限判断
            hasPromission();
        }
    }

    //登录接口
    private void login() {
        LogUtils.i("login");
        boolean availableByPing = NetUtils.isAvailableByPing();
        LogUtils.i("availableByPing:" + availableByPing);
        if (availableByPing == false) {
            ToastUtils.showShort(this, "请检查网络...");
            return;
        }
        if (TextUtils.isEmpty(mEtName.getText().toString().trim())) {
            ToastUtils.showShort(this, "请输入用户名");
            return;
        }
        if (TextUtils.isEmpty(mEtPassword.getText().toString().trim())) {
            ToastUtils.showShort(this, "请输入密码");
            return;
        }

        String sign = GetSignString.getSignString();
        token = CreateToken.getMd5Token(sign);

        HashMap map = new HashMap();
        map.put("username", mEtName.getText().toString().trim());
        map.put("Landmarks", PhoneUtils.getIMEI());
        map.put("password", mEtPassword.getText().toString().trim());
        map.put("phoneModel", Build.MODEL);
        map.put("version", AppUtils.getVersionName(MyApplication.getInstance().getApplicationContext()));
        map.put("sign", sign);
        map.put("loginToken", token);
        map.put("dataToken", Constants.dataToken);
        LogUtils.i("登录map:" + map.toString());
        dialogShow("正在登陆");
        mvpPresenter.postLogin(Constants.LOGIN, map);
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onLoginSuccess(LoginBean loginBean) {
        setLogin(loginBean);
    }

    @Override
    public void isAccountExist(IsUserExistBean isUserExistBean) {
        int status = isUserExistBean.getStatus();
        if (status == 10003) {
            ToastUtils.showShort(this, "用户名不存在");
        }
    }

    @Override
    public void getDataFail(String msg) {
        ToastUtils.showShort(msg);
    }

    @Override
    public void onFinish() {
        dialogDismiss();
    }

    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void setLogin(LoginBean loginBean) {
        int status = loginBean.getStatus();
        if (status == 1) {
            LogUtils.i("登录成功");
//            Word.isOnPause = false; //登录成功后，设置可以发送弹幕

            SPUtils.getInstance().put("token", token); //保存token
            SPUtils.getInstance().put("uid", loginBean.getData().getUid()); //保存userID
            SPUtils.getInstance().put("name", loginBean.getData().getName()); //保存用户名
            SPUtils.getInstance().put("etName", mEtName.getText().toString().trim()); //用户名输入的用户名
            if (mIvRemeber.isSelected()) //如果用户选择了保存密码，则保存用户的密码
            {
                SPUtils.getInstance().put("password", mEtPassword.getText().toString().trim()); //用户名输入的密码
            }else {
                SPUtils.getInstance().put("password", ""); //用户名输入的密码
            }
            LogUtils.i("登录成功，保存的用户密码："+ SPUtils.getInstance().getString("password"));

            //用户级别：1销售,2地区业务经理,3分区业务总监,4城市经理,5地区经理，6分区总监，7星系图管理员，8角色审核人员，10超级管理员
            SPUtils.getInstance().put("groupid", loginBean.getData().getGroupid());
            SPUtils.getInstance().put("groupname", loginBean.getData().getGroupName());
            SPUtils.getInstance().put("sqID", loginBean.getData().getSqid()); //保存商圈ID
            SPUtils.getInstance().put("isXinJiang", loginBean.getData().getXj()); //0:不是   1：是
            SPUtils.getInstance().put("updateID", loginBean.getData().getUpdateID()); //用来版本升级
            SPUtils.getInstance().put("d_g", loginBean.getData().getD_g()); //用来判断是否是代管账号

            LogUtils.i("登录成功，准备开启闹钟提醒");
//            TimeTickUtils.setTimeTick(); //登录成功后，开启打点提醒

            //保存用户头像
            String iconAddress = Constants.URL_IMAGE + loginBean.getData().getHeadaddress();
            String iconAddressSL = Constants.URL_IMAGE + loginBean.getData().getHeadaddress_sl();
            SPUtils.getInstance().put("icon", iconAddress); //保存用户大头像
            SPUtils.getInstance().put("iconSL", iconAddressSL); //保存用户缩略头像

            LogUtils.i("登录成功，开始跳转");
            //登录成功，关闭界面
//            KongQuanUtils.kongQuan(LoginActivity.this);
            LogUtils.i("执行finish操作了");
            LoginActivity.this.finish();
        } else if (status == 10005) { //连续10次输错密码，唤起短信登录
            ToastUtils.showShort(this, "密码输入次数过多，请用短信验证码登录");

            LogUtils.i("错误账号对应的手机号：" + loginBean.getErrMsg());
            //后台将用户手机号放在了errMsg里面
//            Intent intent = new Intent(this, CodeLoginActivity.class);
//            intent.putExtra("phone", loginBean.getErrMsg());
//            startActivity(intent);
        } else if (status == 10001) {
            ToastUtils.showShort(this, "验证用户信息失败，请重新登录");
        } else {
            LogUtils.i("loginBean.getErrMsg():" + loginBean.getErrMsg());
            ToastUtils.showShort(this, loginBean.getErrMsg());
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            long timeMillis = SystemClock.uptimeMillis();
            LogUtils.i("onKeyDown");
            if (timeMillis - p < 1500) {
                LogUtils.i("小于1500");
                this.finish();
                return true;
            }
            p = SystemClock.uptimeMillis();
            ToastUtils.showShort(this, "再按一次退出");
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onPause() {
        super.onPause();
        onFinish();
    }
}
