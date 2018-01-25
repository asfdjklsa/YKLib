package com.allenliu.ticket.ui.activity;

import com.allenliu.youknewlib.ui.YouKnewBaseActivity;
import com.tencent.smtt.sdk.WebView;

/**
 * Created by allenliu on 2018/1/25.
 */

public abstract class BaseWebAcitivty extends YouKnewBaseActivity {
    protected WebView webview;

    @Override
    public void onBackPressed() {
        if (webview != null) {
            if (webview.canGoBack()) {
                webview.goBack();
            } else {
                finish();
            }
        }
    }


}
