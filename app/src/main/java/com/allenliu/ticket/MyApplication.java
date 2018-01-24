package com.allenliu.ticket;

import android.app.Application;
import android.content.Intent;

import com.allenliu.ticket.ui.MainActivity;
import com.allenliu.youknewlib.YouKnewUtil;
import com.allenliu.youknewlib.listener.VerifyListener;
import com.blankj.utilcode.util.LogUtils;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by allenliu on 2018/1/23.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        JPushInterface.setDebugMode(false);
        JPushInterface.init(this);
        YouKnewUtil.init(this,"wuheng20180123", new VerifyListener() {
            @Override
            public void onVerify() {
                LogUtils.e("verify mode");
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }
}
