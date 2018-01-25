package com.allenliu.ticket.ui.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.allenliu.ticket.R;
import com.allenliu.ticket.databinding.ActivitySingleWebViewBinding;
import com.allenliu.youknewlib.ui.YouKnewBaseActivity;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

public class SingleWebViewActivity extends BaseWebAcitivty {
    ActivitySingleWebViewBinding binding;

    @Override
    public void init() {
        showLoadingProgressBar();
        binding.titlebar.setLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        binding.webview.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView webView, String s) {
                super.onPageFinished(webView, s);
                dimissProgressBar();
            }
        });
        binding.webview.setWebChromeClient(new WebChromeClient() {
        });

        binding.webview.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        binding.webview.getSettings().setJavaScriptEnabled(true);
        binding.webview.loadUrl(getIntent().getStringExtra("url"));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_single_web_view);
        webview = binding.webview;
        init();
    }

}
