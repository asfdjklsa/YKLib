package com.allenliu.youknewlib.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.allenliu.youknewlib.R;


public class WebViewActivity extends YouKnewBaseActivity {

    private String url;
    boolean isDestroy = false;
    private com.tencent.smtt.sdk.WebView webview;

    public static void goToWebView(Context context, String url) {
        Intent intent = new Intent(context, WebViewActivity.class);
        intent.putExtra("url", url);
        context.startActivity(intent);
    }

    @Override
    public void init() {
        webview = findViewById(R.id.webview);
        url=getIntent().getStringExtra("url");
        initWebView();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.younewlib_activity_web_view);
        init();

    }

    private void initWebView() {
//        webview.setWebViewClient(new WebViewClient() {
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                view.loadUrl(url);
//                return true;
//            }
//        });
        webview.setWebViewClient(new com.tencent.smtt.sdk.WebViewClient());
        webview.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.loadUrl(url);

    }

    @Override
    protected void onPause() {
        isDestroy = true;
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        isDestroy = false;
    }

    @Override
    public void onBackPressed() {
        if(webview.canGoBack()){
            webview.goBack();
        }else{
            finish();
        }
    }
}
