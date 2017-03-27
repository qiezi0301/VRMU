package com.vr_mu.vrmu.views.fragments;


import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.vr_mu.vrmu.R;

import java.util.Arrays;
import java.util.List;

/**
 * Live首页碎片
 * A simple {@link Fragment} subclass.
 */
public class LiveHomeFragment extends BaseFragment {

    @Override
    protected void initView() {

        TabLayout mTabLayout = findViewById(R.id.tabs);                  //定义TabLayout
        ViewPager mViewPager = findViewById(R.id.child_fragment_pager);  //定义viewPager

        final List<? extends Fragment> list_fragment =  Arrays.asList(new LiveLiveFragment(), new LiveVarietyFragment(), new LiveVideoFragment(), new LiveReviewFragment());

        final List<String> list_title = Arrays.asList("Live", "综艺", "影视", "回顾");  //定义要装tabname的列表

        FragmentPagerAdapter mAdapter = new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return list_fragment.get(position);
            }

            @Override
            public int getCount() {
                return list_fragment.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return list_title.get(position);
            }
        };
        mViewPager.setAdapter(mAdapter);

        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    protected int setLayoutResouceId() {
        return R.layout.fragment_common_home;
    }
}
