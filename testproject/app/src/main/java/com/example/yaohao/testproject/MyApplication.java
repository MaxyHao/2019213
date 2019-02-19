package com.example.yaohao.testproject;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.StrictMode;

import com.example.yaohao.testproject.utils.GlideImageLoader;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.view.CropImageView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;


/**
 * Created by Administrator on 2017/3/1 0001.
 */

public class MyApplication extends Application {

    public static MyApplication instance;

//    private RefWatcher refWatcher;


    static {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                layout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white);//全局设置主题颜色
                return new ClassicsHeader(context);//.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
            }
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                //指定为经典Footer，默认是 BallPulseFooter
                return new ClassicsFooter(context).setDrawableSize(20);
            }
        });
    }

    @Override
    public void onCreate() {
        super.onCreate();

        //适配Android7.0 URI 拍照，应用安装问题
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy(builder.build());
            builder.detectFileUriExposure();
        }

        instance = this;
        init();
		initImagePicker();
    }

	private void init() {
        Constants.init(this);//初始化缓存目录
//        refWatcher = setLeakCanary();
    }

//    监听内存泄漏的代码
//    private RefWatcher setLeakCanary()
//    {
//        //初始化leakCanary，用来检测内存泄漏
//        if (LeakCanary.isInAnalyzerProcess(this)) {
//            return RefWatcher.DISABLED;
//        }
//        return LeakCanary.install(this);
//    }
//    public RefWatcher getRefWatcher() {
//        if (refWatcher != null)
//        {
//            return refWatcher;
//        }else {
//            return null;
//        }
//    }

	private void initImagePicker() {
		ImagePicker imagePicker = ImagePicker.getInstance();
		imagePicker.setImageLoader(new GlideImageLoader());   //设置图片加载器
		imagePicker.setShowCamera(true);  //显示拍照按钮
		imagePicker.setCrop(false);        //不允许裁剪（单选才有效）
		imagePicker.setSaveRectangle(true); //是否按矩形区域保存
		imagePicker.setSelectLimit(9);    //选中数量限制
		imagePicker.setStyle(CropImageView.Style.RECTANGLE);  //裁剪框的形状
		imagePicker.setFocusWidth(800);   //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
		imagePicker.setFocusHeight(800);  //裁剪框的高度。单位像素（圆形自动取宽高最小值）
		imagePicker.setOutPutX(1000);//保存文件的宽度。单位像素
		imagePicker.setOutPutY(1000);//保存文件的高度。单位像素
	}


    //单例模式中获取唯一的MyApp实例
    public static MyApplication getInstance() {
        if (null == instance) {
            instance = new MyApplication();
        }
        return instance;
    }
}
