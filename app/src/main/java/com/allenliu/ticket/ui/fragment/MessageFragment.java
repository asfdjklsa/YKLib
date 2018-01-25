package com.allenliu.ticket.ui.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.allenliu.ticket.Api;
import com.allenliu.ticket.R;
import com.allenliu.ticket.databinding.ActivityMainBinding;
import com.allenliu.ticket.ui.FooyoViewPagerFragmentAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by allenliu on 2018/1/25.
 */

public class MessageFragment extends BaseWebFragment {
    ActivityMainBinding binding;
    private FooyoViewPagerFragmentAdapter adapter;
    private List<Fragment> list;
    private List<String> stringList;


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
        adapter = new FooyoViewPagerFragmentAdapter(getFragmentManager(), list, stringList);
        binding.viewpager.setAdapter(adapter);
        binding.viewpager.setOffscreenPageLimit(3);
        binding.tablayout.setupWithViewPager(binding.viewpager);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.activity_main, null, false);
        init();
        return binding.getRoot();
    }

    public void onBackPressed() {
        int currentItem = binding.viewpager.getCurrentItem();
        if (list.get(currentItem) instanceof WebFragment) {
            ((WebFragment) list.get(currentItem)).onBackPressed();
        }

    }
}
