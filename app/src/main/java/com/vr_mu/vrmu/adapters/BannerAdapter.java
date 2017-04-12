package com.vr_mu.vrmu.adapters;

import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * ViewPager适配器
 * Created by zjl on 17/4/11.
 */

public class BannerAdapter extends PagerAdapter {

    //数据源
    private List<ImageView> mList;

    public BannerAdapter(List<ImageView> list) {
        this.mList = list;
    }

    @Override
    public int getCount() {
        //取超大的数，实现无线循环效果
        return Integer.MAX_VALUE;
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        position = position % mList.size();
        Log.d("position", "position-------------: " + position);
        container.addView(mList.get(position));
        return mList.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mList.get(position % mList.size()));
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

}
