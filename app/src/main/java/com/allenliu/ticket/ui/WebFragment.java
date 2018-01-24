package com.allenliu.ticket.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.allenliu.ticket.R;
import com.blankj.utilcode.util.ToastUtils;
import com.tencent.smtt.export.external.interfaces.ConsoleMessage;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;


/**
 * Created by allenliu on 2018/1/23.
 */

public class WebFragment extends Fragment {
    WebView webview;
    private long time;
    AlertDialog alertDialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_web, null);
        webview = view.findViewById(R.id.webview);
        webview.setWebViewClient(new WebViewClient());
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

    public void onBackPressed() {
        if (webview.canGoBack()) {
            webview.goBack();
        } else {
            if (System.currentTimeMillis() - time < 1000) {
                if (getActivity() != null) {
                    getActivity().finish();
                }
            } else {
                time = System.currentTimeMillis();
                ToastUtils.showShort("再按一次退出应用");

            }
        }
    }
}
