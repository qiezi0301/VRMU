package com.vr_mu.vrmu.views.fragments;


import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.vr_mu.vrmu.R;
import com.vr_mu.vrmu.adapters.BannerAdapter;
import com.vr_mu.vrmu.adapters.LiveAdapter;
import com.vr_mu.vrmu.gson.BannerGson;
import com.vr_mu.vrmu.gson.LiveLiveGson;
import com.vr_mu.vrmu.presenters.UserServerHelper;
import com.vr_mu.vrmu.utils.HttpUtil;
import com.vr_mu.vrmu.utils.Utility;
import com.vr_mu.vrmu.views.customize.PullToRefreshView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * 直播列表页
 * A simple {@link Fragment} subclass.
 */
public class LiveLiveFragment extends BaseFragment implements PullToRefreshView.OnHeaderRefreshListener {


    private PullToRefreshView mPullToRefreshView;

    private View bannerview;
    private ListView listView;
    private LiveAdapter liveAdapter;

    private ScrollView sv;

    // 声明控件
    private ViewPager mViewPager;
    private List<ImageView> mlist;
    private TextView mTextView;
    private LinearLayout mLinearLayout;

    // 广告图素材
    private int[] bannerImages;

    // 广告语
    private String[] bannerTexts = {"因为专业 所以卓越", "坚持创新 行业领跑", "诚信 专业 双赢", "精细 和谐 大气 开放"};;

    // 圆圈标志位
    private int pointIndex = 0;

    // 线程标志
    private boolean isStop = false;

    @Override
    protected int setLayoutResouceId() {
        return R.layout.fragment_list;
    }

    @Override
    protected void initView() {
        bannerview = LayoutInflater.from(mActivity).inflate(R.layout.lay_banner, null);
        listView = findViewById(R.id.list_view);
        listView.setDividerHeight(0);
        listView.addHeaderView(bannerview);

        //初始化轮播控件
        mViewPager = findViewById(R.id.viewpager);
        mTextView = findViewById(R.id.tv_bannertext);
        mLinearLayout = findViewById(R.id.points);

        //初始化刷新控件
        mPullToRefreshView = (PullToRefreshView) mRootView.findViewById(R.id.swipe_refresh);
        mPullToRefreshView.setEnablePullLoadMoreDataStatus(false);

        //设置ScrollView默认定位顶部
        sv = findViewById(R.id.over_scroll);
        sv.smoothScrollTo(0, 0);

        //读取本地是否有缓存文件
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String liveInfoString = preferences.getString("liveInfo", null);
        String bannerInfoString = preferences.getString("bannerInfo", null);

        if (liveInfoString != null) {
            LiveLiveGson liveLive = Utility.handleLiveResponse(liveInfoString);
            showLiveInfo(liveLive);
        } else {
            requestLiveData();
        }

        //设置下拉监听器
        mPullToRefreshView.setOnHeaderRefreshListener(LiveLiveFragment.this);


        if (bannerInfoString != null) {
            BannerGson banner = Utility.handleBannerResponse(bannerInfoString);
            showBannerInfo(banner);
        } else {
            initData();
        }
    }

    private void requestLiveData() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("orderby", "hot");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String homeUrl = UserServerHelper.LIVE + jsonObject.toString();

        HttpUtil.sendOkHttpRequest(homeUrl, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(), "获取数据失败", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseText = response.body().string();
                final LiveLiveGson liveLive = Utility.handleLiveResponse(responseText);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (liveLive != null && "success".equals(liveLive.msg)) {
                            SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(getActivity()).edit();
                            editor.putString("liveInfo", responseText);
                            editor.apply();
                            showLiveInfo(liveLive);
                        } else {
                            Toast.makeText(getActivity(), "获取数据失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    /**
     * 初始化数据
     */
    private void initData() {
        String homeUrl = UserServerHelper.BANNER;
        HttpUtil.sendOkHttpRequest(homeUrl, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                mActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(), "获取数据失败", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseText = response.body().string();
                final BannerGson banner = Utility.handleBannerResponse(responseText);
                mActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (banner != null && "success".equals(banner.msg)) {
                            SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(getActivity()).edit();
                            editor.putString("bannerInfo", responseText);
                            editor.apply();
                            showBannerInfo(banner);
                        } else {
                            Toast.makeText(getActivity(), "获取数据失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    private void showBannerInfo(BannerGson banner) {
        mlist = new ArrayList<>();
        View view;
        LinearLayout.LayoutParams params;
        for (BannerGson.DataBean bannerImage : banner.data) {
            // 设置广告图
            ImageView imageView = new ImageView(mActivity);
            imageView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            Glide.with(mActivity).load(bannerImage.img).into(imageView);
            mlist.add(imageView);

            // 设置圆圈点
            view = new View(mActivity);
            params = new LinearLayout.LayoutParams(15, 15);
            params.leftMargin = 10;
            view.setBackgroundResource(R.drawable.point_background);
            view.setLayoutParams(params);
            view.setEnabled(false);
            mLinearLayout.addView(view);
        }
        BannerAdapter adapter = new BannerAdapter(mlist);
        mViewPager.setAdapter(adapter);
        initAction();

        // 开启新线程，2秒一次更新Banner
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while (!isStop) {
//                    SystemClock.sleep(2000);
//                    mActivity.runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                                mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1);
//                        }
//                    });
//                }
//            }
//        }).start();
    }

    private void showLiveInfo(LiveLiveGson liveLive) {
        TextView emptyView = findViewById(R.id.tip_tv);
        listView.setEmptyView(emptyView); //没有数据时候显示
        liveAdapter = new LiveAdapter(mActivity, R.layout.item_live, liveLive.data);
        listView.setAdapter(liveAdapter);
    }

    @Override
    public void onHeaderRefresh(PullToRefreshView view) {
        mPullToRefreshView.postDelayed(new Runnable() {
            @Override
            public void run() {
                mPullToRefreshView.onHeaderRefreshComplete();
                requestLiveData();
            }
        }, 1000);
    }

    /**
     * 初始化事件
     */
    private void initAction() {
        BannerListener bannerListener = new BannerListener();
        mViewPager.addOnPageChangeListener(bannerListener);

        //取中间数来作为起始位置
        int index = (Integer.MAX_VALUE / 2) - (Integer.MAX_VALUE / 2 % mlist.size());

        //用来出发监听器
        mViewPager.setCurrentItem(index);
        mLinearLayout.getChildAt(pointIndex).setEnabled(true);

    }

    @Override
    public void onDestroy() {
        // 关闭定时器
        isStop = true;
        super.onDestroy();
    }

    //实现VierPager监听器接口
    class BannerListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        @Override
        public void onPageSelected(int position) {
            int newPosition = position % mlist.size();
            mTextView.setText(bannerTexts[newPosition]);
            mLinearLayout.getChildAt(newPosition).setEnabled(true);
            mLinearLayout.getChildAt(pointIndex).setEnabled(false);

            // 更新标志位
            pointIndex = newPosition;
        }

        @Override
        public void onPageScrollStateChanged(int arg0) {
        }

    }

}