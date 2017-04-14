package com.vr_mu.vrmu.views.customize;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.vr_mu.vrmu.R;
import com.vr_mu.vrmu.adapters.BannerAdapter;
import com.vr_mu.vrmu.gson.BannerGson;
import com.vr_mu.vrmu.presenters.UserServerHelper;
import com.vr_mu.vrmu.utils.HttpUtil;
import com.vr_mu.vrmu.utils.Utility;
import com.vr_mu.vrmu.views.MyApplication;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by zjl on 17/4/13.
 */

public class BannerView extends View{

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

    public BannerView(Context context) {
        super(context);
        initData();
    }

    private Activity mActivity;

    public void setActivity(Activity activity) {
        mActivity = activity;
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
                        Toast.makeText(MyApplication.getContext(), "获取数据失败", Toast.LENGTH_SHORT).show();
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
                            SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(mActivity).edit();
                            editor.putString("bannerInfo", responseText);
                            editor.apply();
                            showBannerInfo(banner);
                        } else {
                            Toast.makeText(mActivity, "获取数据失败", Toast.LENGTH_SHORT).show();
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
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (!isStop) {
                    SystemClock.sleep(2000);
                    mActivity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1);
                        }
                    });
                }
            }
        }).start();
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
