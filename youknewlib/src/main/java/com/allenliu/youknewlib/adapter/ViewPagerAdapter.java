package com.allenliu.youknewlib.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Allen Liu on 2017/1/9.
 */

public class ViewPagerAdapter extends PagerAdapter {
    private List<View> data;

    public ViewPagerAdapter(List<View> data) {
        this.data = data;
    }
    public List<View> getData(){
        return data;
    }
    public void setData(List<View>data){
        this.data=data;
    }
    @Override
    public int getCount() {
        // TODO 自动生成的方法存根
        return data != null && data.size() > 0 ? data.size() : 0;
    }

    /**
     * 判断出去的view是否等于进来的view 如果是直接复用
     */
    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        // TODO 自动生成的方法存根
        return arg0 == arg1;
    }

    /**
     * 销毁预加载以外的view对象, 会把需要销毁的对象的索引位置传进来，就是position，
     */
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        container.removeView((View) data.get(position % data.size()));
    }

    /*
     * （非 Javadoc）
     *
     * @see
     * android.support.v4.view.PagerAdapter#instantiateItem(android.view.View,
     * int)
     */
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        // TODO 自动生成的方法存根
        if (data != null && data.size() != 0) {
            View view = (View) data.get(position % data.size());
            if (view.getParent() == null) {
                container.addView(view);
            } else {
                ViewGroup group = (ViewGroup) view.getParent();
                group.removeView(view);
                container.addView(view);
            }
            return data.get(position % data.size());
        } else {
            return null;
        }

    }
}
