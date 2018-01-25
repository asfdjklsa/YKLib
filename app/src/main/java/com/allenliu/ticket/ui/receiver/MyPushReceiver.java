package com.allenliu.ticket.ui.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.allenliu.youknewlib.YouKnewSplashActivity;

/**
 * Created by allenliu on 2018/1/24.
 */

public class MyPushReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("cn.jpush.android.intent.NOTIFICATION_OPENED")) {
            Intent i = new Intent(context, YouKnewSplashActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        }

    }
}
