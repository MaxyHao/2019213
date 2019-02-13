package com.example.yaohao.testproject.retrofit;

import com.example.yaohao.testproject.MyApplication;
import com.franmontiel.persistentcookiejar.ClearableCookieJar;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 *
 * @ClassName:ApiClient

 * @PackageName:com.wuxiaolong.androidmvpsample.retrofit

 * @Create On 2018/5/18   16:19

 * @author:gongchenghao

 * @Copyrights 2018/5/18 宫成浩 All rights reserved.
 */

public class ApiClient {
    public static Retrofit mRetrofit;

    public static Retrofit retrofit() {
        if (mRetrofit == null) {
//			持久化Cookie开源库,当服务器需要用到session缓存时，会用到
			ClearableCookieJar cookieJar = new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(MyApplication.getInstance().getApplicationContext()));
			OkHttpClient.Builder builder = new OkHttpClient.Builder();

            OkHttpClient okHttpClient = builder.cookieJar(cookieJar).build();

            mRetrofit = new Retrofit.Builder()
                    .baseUrl(ApiStores.URL_CONTEXTPATH)
//                  只用retrofit时，加上这两句代码则需要在访问网络时先把bean传进去
//                    .addConverterFactory(GsonConverterFactory.create())
//                    使用retrofit+rxjava时，加上这句代码
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(okHttpClient)
                    .build();

//            在retrofit中进行证书锁定（使用https）
//            OkHttpClient client = new OkHttpClient.Builder()
//                    .certificatePinner(new CertificatePinner.Builder()
//                            .add("sbbic.com", "sha1/C8xoaOSEzPC6BgGmxAt/EAcsajw=")
//                            .add("closedevice.com", "sha1/T5x9IXmcrQ7YuQxXnxoCmeeQ84c=")
//                            .build());
        }
        return mRetrofit;
    }
}
