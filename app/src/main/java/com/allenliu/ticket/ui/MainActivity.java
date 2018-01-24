package com.allenliu.ticket.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.allenliu.ticket.Api;
import com.allenliu.ticket.R;
import com.allenliu.ticket.databinding.ActivityMainBinding;
import com.allenliu.youknewlib.ui.YouKnewBaseActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends YouKnewBaseActivity {
    ActivityMainBinding binding;
    private FooyoViewPagerFragmentAdapter adapter;
    private List<Fragment> list;
    private List<String> stringList;

    @Override
    public void init() {
        list = new ArrayList<>();
        stringList = new ArrayList<>();

        for (int i = 0; i < Api.urls.size(); i++) {
            WebFragment fragment = new WebFragment();
            Bundle bundle = new Bundle();
            bundle.putString("url", Api.urls.get(i));
            fragment.setArguments(bundle);
            list.add(fragment);
            stringList.add(getResources().getStringArray(R.array.tab_titles)[i]);
        }
        adapter = new FooyoViewPagerFragmentAdapter(getSupportFragmentManager(), list, stringList);
        binding.viewpager.setAdapter(adapter);
        binding.viewpager.setOffscreenPageLimit(3);
        binding.tablayout.setupWithViewPager(binding.viewpager);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        init();

    }

    @Override
    public void onBackPressed() {
        int currentItem = binding.viewpager.getCurrentItem();
        if (list.get(currentItem) instanceof WebFragment) {
            ((WebFragment) list.get(currentItem)).onBackPressed();
        }

    }
}
