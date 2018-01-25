package com.allenliu.ticket.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.allenliu.ticket.R;
import com.allenliu.ticket.ui.activity.SingleWebViewActivity;
import com.allenliu.ticket.ui.fragment.BaseWebFragment;
import com.allenliu.youknewlib.ui.WebViewActivity;
import com.blankj.utilcode.util.ToastUtils;
import com.tencent.smtt.export.external.interfaces.ConsoleMessage;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;


/**
 * Created by allenliu on 2018/1/23.
 */

public class WebFragment extends BaseWebFragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_web, null);
        webview = view.findViewById(R.id.webview);
        showLoadingProgressBar();
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView webView, String s) {
                Intent intent = new Intent(getActivity(), SingleWebViewActivity.class);
                intent.putExtra("url", s);
                startActivity(intent);
                return true;
            }

            @Override
            public void onPageFinished(WebView webView, String s) {
                super.onPageFinished(webView, s);
                dimissProgressBar();
            }
        });
        webview.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
//                if (consoleMessage.message().contains("Uncaught ReferenceError: webCallLocal is not defined")) {
//                    if (alertDialog == null) {
//                        alertDialog = new AlertDialog.Builder(getActivity())
//                                .setTitle("提示")
//                                .setMessage("功能开发中，敬请期待！")
//                                .setPositiveButton("好的", null)
//                                .create();
//                    }
//                    alertDialog.show();
//                    return true;
//                } else
                return super.onConsoleMessage(consoleMessage);

            }


        });
        webview.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.loadUrl(getArguments().getString("url"));
        return view;
    }


}
