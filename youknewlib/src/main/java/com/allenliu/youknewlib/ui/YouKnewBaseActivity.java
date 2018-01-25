package com.allenliu.youknewlib.ui;

import com.allenliu.library.BaseActivity;
import com.blankj.utilcode.util.ToastUtils;
import com.tencent.smtt.sdk.WebView;

/**
 * Created by allenliu on 2018/1/23.
 */

public abstract class YouKnewBaseActivity extends BaseActivity {
    private long time;

    protected void verifyAndExit() {
        if (System.currentTimeMillis() - time < 1000) {
            finish();

        } else {
            time = System.currentTimeMillis();
            ToastUtils.showShort("再按一次退出应用");

        }
    }
}
