package com.vr_mu.vrmu.views.fragments;


import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.vr_mu.vrmu.R;

import java.util.Arrays;
import java.util.List;

/**
 * 音乐首页碎片
 * A simple {@link Fragment} subclass.
 */
public class SongHomeFragment extends BaseFragment {

    @Override
    protected void initView() {
        TabLayout mTabLayout = findViewById(R.id.tabs);                  //定义TabLayout
        ViewPager mViewPager = findViewById(R.id.child_fragment_pager);  //定义viewPager

        final List<? extends Fragment> list_fragment = Arrays.asList(new SongSongFragment(), new SongMvFragment(), new SongSingerFragment(), new SongAlbumFragment(), new SongHotFragment());

        final List<String> list_title = Arrays.asList("歌单", "MV", "歌手", "专辑", "热榜");  //tab名称列表

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