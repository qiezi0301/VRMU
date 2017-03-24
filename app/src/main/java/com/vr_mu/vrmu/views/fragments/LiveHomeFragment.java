package com.vr_mu.vrmu.views.fragments;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vr_mu.vrmu.R;
import com.vr_mu.vrmu.adapters.Find_tab_Adapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class LiveHomeFragment extends Fragment {

    private static final String TAG = "LiveHomeFragment";
    private TabLayout mTabLayout;  //定义TabLayout
    private ViewPager mViewPager;  //定义viewPager
    private FragmentPagerAdapter fPagerAdapter;  //定义adapter

    private List<Fragment> list_fragment;                                //定义要装fragment的列表
    private List<String> list_title;                                     //tab名称列表

    private LiveLiveFragment liveLiveFragment;         //live fragment
    private LiveVarietyFragment liveVarietyFragment;   //综艺 fragment
    private LiveVideoFragment liveVideoFragment;       //影视 fragment
    private LiveReviewFragment liveReviewFragment;     //回顾 fragment
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_live_home, container, false);
        initContents(view);
        Log.d(TAG, "onCreateView: ");
        return view;
    }

    private void initContents(View view) {
        mTabLayout = (TabLayout)view.findViewById(R.id.tabs);
        mViewPager = (ViewPager)view.findViewById(R.id.vp_FindFtagment_pager);

        //初始化各fragment
        liveLiveFragment = new LiveLiveFragment();
        liveVarietyFragment = new LiveVarietyFragment();
        liveVideoFragment = new LiveVideoFragment();
        liveReviewFragment = new LiveReviewFragment();

        //将fragment装进列表中
        list_fragment = new ArrayList<>();
        list_fragment.add(liveLiveFragment);
        list_fragment.add(liveVarietyFragment);
        list_fragment.add(liveVideoFragment);
        list_fragment.add(liveReviewFragment);

        //将名称加载tab名字列表，正常情况下，我们应该在values/arrays.xml中进行定义然后调用
        list_title = new ArrayList<>();
        list_title.add("LIVE");
        list_title.add("综艺");
        list_title.add("影视");
        list_title.add("回顾");

        //设置TabLayout的模式
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);

        //为TabLayout添加tab名称
        for (int i = 0; i < list_fragment.size(); i++) {
            mTabLayout.addTab(mTabLayout.newTab().setText(list_title.get(i)));
        }

        fPagerAdapter = new Find_tab_Adapter(getActivity().getSupportFragmentManager(), list_fragment, list_title);

        //viewpager加载adapter
        mViewPager.setAdapter(fPagerAdapter);
        //TabLayout加载viewpager
        mTabLayout.setupWithViewPager(mViewPager);
        Log.d(TAG, "initContents: ");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }

    @Override
    public void onPause() {
        super.onPause();
        liveLiveFragment.onStop();
        Log.d(TAG, "onPause: ");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        Log.d(TAG, "onDestroy: ");
    }
}
