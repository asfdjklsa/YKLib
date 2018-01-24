package com.allenliu.youknewlib;

import android.content.Context;

import com.allenliu.library.BaseApplication;
import com.allenliu.youknewlib.listener.VerifyListener;
import com.allenliu.youknewlib.net.APIService;
import com.blankj.utilcode.util.Utils;
import com.tencent.smtt.sdk.QbSdk;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by allenliu on 2018/1/23.
 */

public class YouKnewUtil {
    private static APIService apiService;
    private static String appId;
    private static VerifyListener verifyListener;
    public static void init(Context context,String appid, VerifyListener listener) {
        OkHttpClient.Builder builder;
        builder = new OkHttpClient.Builder()
                .retryOnConnectionFailure(true)
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://201888888888.com:8080/")
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(APIService.class);
        appId=appid;
        verifyListener=listener;
        BaseApplication.globalContext=context;
        Utils.init(context);
        QbSdk.initX5Environment(context, new QbSdk.PreInitCallback() {
            @Override
            public void onCoreInitFinished() {

            }

            @Override
            public void onViewInitFinished(boolean b) {

            }
        });

    }

    public static APIService getApiService() {
        return apiService;
    }

    public static String getAppId() {
        return appId;
    }

    public static VerifyListener getVerifyListener() {
        return verifyListener;
    }
}
