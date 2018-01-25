package com.allenliu.ticket.ui.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.view.MenuItem;

import com.allenliu.ticket.Api;
import com.allenliu.ticket.R;
import com.allenliu.ticket.databinding.ActivityMain2Binding;
import com.allenliu.ticket.ui.fragment.BaseWebFragment;
import com.allenliu.ticket.ui.fragment.MessageFragment;
import com.allenliu.ticket.ui.fragment.WebFragment;
import com.allenliu.youknewlib.ui.YouKnewBaseActivity;

public class Main2Activity extends YouKnewBaseActivity {
    ActivityMain2Binding binding;
    private Fragment[] fragments;

    @Override
    public void init() {
        fragments = new Fragment[2];

        for (int i = 0; i < 2; i++) {
            if (i == 0) {
                fragments[i] = new WebFragment();
                Bundle bundle = new Bundle();
                bundle.putString("url", Api.trendUrl);
                fragments[i].setArguments(bundle);
            } else {
                fragments[i] = new MessageFragment();
            }

            getSupportFragmentManager().beginTransaction().add(R.id.web_container, fragments[i]).commit();

        }
        binding.navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                showFragment(item.getItemId());
                return true;
            }
        });
        binding.navigationView.setSelectedItemId(R.id.trend);
    }

    private void showFragment(final int itemId) {
        for (Fragment fragment : fragments) {
            getSupportFragmentManager().beginTransaction().hide(fragment).commit();
        }
        switch (itemId) {
            case R.id.trend:
                getSupportActionBar().setTitle(R.string.trend);
                getSupportFragmentManager().beginTransaction().show(fragments[0]).commit();
                break;
            case R.id.message:
                getSupportActionBar().setTitle(R.string.message);

                getSupportFragmentManager().beginTransaction().show(fragments[1]).commit();
                break;


        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main2);
        init();

    }

    @Override
    public void onBackPressed() {
        switch (binding.navigationView.getSelectedItemId()) {
            case R.id.trend:

                ((BaseWebFragment) fragments[0]).onBackPressed();
                break;
            case R.id.message:
                ((BaseWebFragment) fragments[1]).onBackPressed();

                break;
        }
    }
}
