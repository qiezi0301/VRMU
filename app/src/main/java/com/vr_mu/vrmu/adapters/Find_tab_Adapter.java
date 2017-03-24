package com.vr_mu.vrmu.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by zjl on 17/3/24.
 */

public class Find_tab_Adapter extends FragmentPagerAdapter {
    private List<Fragment> list_fragment;  //fragment列表
    private List<String> list_title;       //tab名的列表

    public Find_tab_Adapter(FragmentManager fm, List<Fragment> list_fragment, List<String> list_title) {
        super(fm);
        this.list_fragment = list_fragment;
        this.list_title = list_title;
    }

    @Override
    public Fragment getItem(int position) {
        return list_fragment.get(position);
    }

    @Override
    public int getCount() {
        return list_title.size();
    }

    //此方法用来显示tab上的名字
    @Override
    public CharSequence getPageTitle(int position) {
        return list_title.get(position % list_title.size());
    }
}
