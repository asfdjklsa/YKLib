package com.allenliu.youknewlib.ui;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.allenliu.library.tool.SPUtils;
import com.allenliu.youknewlib.GlobalConstant;
import com.allenliu.youknewlib.R;
import com.allenliu.youknewlib.adapter.ViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class YKGuikdeActivity extends YouKnewBaseActivity {
private ViewPager viewPager;
private PagerAdapter pagerAdapter;
private List<View> data;
private float downX;
    @Override
    public void init() {
        SPUtils.put(this, GlobalConstant.IS_FIRST,false);
        data=new ArrayList<>();
        viewPager=findViewById(R.id.viewpager);
        viewPager.setOffscreenPageLimit(3);
        addView();
    }

    private void addView() {
        for (int i = 0; i < 3; i++) {
            ImageView imageView=new ImageView(this);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            switch (i) {
                case 0:
                    imageView.setImageResource(R.drawable.guide1);

                    break;
                case 1:
                    imageView.setImageResource(R.drawable.guide2);

                    break;
                case 2:
                    imageView.setImageResource(R.drawable.guide3);

                    break;
            }
            data.add(imageView);
        }
        pagerAdapter=new ViewPagerAdapter(data);
        viewPager.setAdapter(pagerAdapter);

        viewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case  MotionEvent.ACTION_DOWN:

                        downX=event.getX();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        if(viewPager.getCurrentItem()==2) {
                            float nowEvent = event.getX();
                            if ((downX - nowEvent) > 20) {
                                goToWeb();
                            }
                        }
                        break;
                }
                return false;
            }
        });
    }

    private void goToWeb() {
        getIntent().setClass(this,WebViewActivity.class);
        startActivity(getIntent());
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ykguikde);
        init();
    }
}
