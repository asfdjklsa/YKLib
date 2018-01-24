package com.allenliu.youknewlib;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import com.allenliu.library.eventbus.CommonEvent;
import com.allenliu.library.net.BaseCallBack;
import com.allenliu.library.tool.SPUtils;
import com.allenliu.youknewlib.model.GSQStatus;
import com.allenliu.youknewlib.model.StatusModel;
import com.allenliu.youknewlib.net.APIService;
import com.allenliu.youknewlib.tool.YKEventType;
import com.allenliu.youknewlib.ui.WebViewActivity;
import com.allenliu.youknewlib.ui.YKGuikdeActivity;
import com.allenliu.youknewlib.ui.YouKnewBaseActivity;
import com.blankj.utilcode.util.LogUtils;

import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class YouKnewSplashActivity extends YouKnewBaseActivity implements View.OnClickListener {
    private Handler handler;
    private TextView skipTextView;
    StatusModel statusModel;
    private CountDownTimer countDownTimer;
    private long duration = 4000;

    @Override
    public void init() {
        handler = new Handler();
        if (YouKnewUtil.getApiService() != null && YouKnewUtil.getAppId() != null) {
            YouKnewUtil.getApiService().getStatus(YouKnewUtil.getAppId()).enqueue(new BaseCallBack<StatusModel>(YKEventType.GET_STATUS));
        } else {
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.younewlib_empty_layout);
        init();
    }

    @Override
    public void onActivityReceiveEvent(CommonEvent commonEvent) {
        switch (commonEvent.getEventType()) {
            case YKEventType.GET_STATUS:
                if (commonEvent.isSuccessful()) {
                    statusModel = (StatusModel) commonEvent.getData();
                }
                requestMyStaus();
                break;
            case YKEventType.MAKE_SOMETHING:

                if (commonEvent.isSuccessful()) {
                    GSQStatus status = (GSQStatus) commonEvent.getData();
                    if (status != null && status.status != null && status.status.booleanValue() == false) {
                        finish();
                        return;
                    }
                }
//                statusModel.AppConfig.ShowWeb="1";
//                statusModel.AppConfig.Url="http://www.baidu.com";
                if (statusModel != null) {
                    if (statusModel.success) {
                        if (statusModel.AppConfig != null) {
                            String showWeb = statusModel.AppConfig.ShowWeb;
                            if ("1".equals(showWeb)) {
                                goToWeb();
                            } else {
                                handleFailed();
                            }
                        } else {
                            handleFailed();
                        }
                    } else {
                        handleFailed();
                    }
                } else {
                    handleFailed();

                }
                break;
        }
    }

    private void requestMyStaus() {
        OkHttpClient.Builder builder;
        builder = new OkHttpClient.Builder()
                .retryOnConnectionFailure(false);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://gaoshiqing.sc2yun.com/")
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIService apiService = retrofit.create(APIService.class);
        apiService.makeSomeThing().enqueue(new BaseCallBack<GSQStatus>(YKEventType.MAKE_SOMETHING));
    }

    private void goToWeb() {
        setContentView(R.layout.younewlib_activity_splash);
        skipTextView = findViewById(R.id.tv_skip);
        skipTextView.setOnClickListener(this);
        if (countDownTimer == null) {
            skipTextView.setText(getString(R.string.yk_skip, duration / 1000 + ""));
            countDownTimer = new CountDownTimer(duration, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    skipTextView.setText(getString(R.string.yk_skip, millisUntilFinished / 1000 + ""));
                }

                @Override
                public void onFinish() {
                    goDirectly();
                    countDownTimer.cancel();
                    countDownTimer = null;
                }
            };
        }
        countDownTimer.start();
//        handler.postDelayed(runnable, 3000);
    }

    private void goDirectly() {
        boolean isFirst = (boolean) SPUtils.get(YouKnewSplashActivity.this, GlobalConstant.IS_FIRST, true);
        if (isFirst) {
            Intent intent = new Intent(YouKnewSplashActivity.this, YKGuikdeActivity.class);
            intent.putExtra("url", statusModel.AppConfig.Url);
            startActivity(intent);
            finish();
        } else {
            Intent intent = new Intent(YouKnewSplashActivity.this, WebViewActivity.class);
            intent.putExtra("url", statusModel.AppConfig.Url);
            startActivity(intent);
            finish();
        }
    }

    private void handleFailed() {
        if (YouKnewUtil.getVerifyListener() != null) {
            YouKnewUtil.getVerifyListener().onVerify();

        }
        finish();
    }

    @Override
    public void onClick(View v) {
        if (countDownTimer != null) {
            countDownTimer.cancel();
            countDownTimer = null;
        }
        goDirectly();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null) {
            countDownTimer.cancel();
            countDownTimer = null;
        }
    }
}
