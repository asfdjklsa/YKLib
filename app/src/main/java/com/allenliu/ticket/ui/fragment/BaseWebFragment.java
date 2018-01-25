package com.allenliu.ticket.ui.fragment;

import com.allenliu.library.BaseFragment;
import com.blankj.utilcode.util.ToastUtils;
import com.tencent.smtt.sdk.WebView;

/**
 * Created by allenliu on 2018/1/25.
 */

public class BaseWebFragment extends BaseFragment {
    protected WebView webview;
    private long time;

    public void onBackPressed() {
        if (webview != null) {
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
}
