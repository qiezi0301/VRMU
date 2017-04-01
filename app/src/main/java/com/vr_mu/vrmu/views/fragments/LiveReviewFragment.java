package com.vr_mu.vrmu.views.fragments;


import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.vr_mu.vrmu.R;
import com.vr_mu.vrmu.adapters.ReviewAdapter;
import com.vr_mu.vrmu.gson.LiveReviewGson;
import com.vr_mu.vrmu.presenters.UserServerHelper;
import com.vr_mu.vrmu.utils.HttpUtil;
import com.vr_mu.vrmu.utils.Utility;
import com.vr_mu.vrmu.views.customize.PullToRefreshView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * 回顾碎片视图
 * A simple {@link Fragment} subclass.
 */
public class LiveReviewFragment extends BaseFragment implements PullToRefreshView.OnHeaderRefreshListener {
    private PullToRefreshView mPullToRefreshView;


    private ListView listView;
    private ReviewAdapter reviewAdapter;

    private ScrollView sv;
    private TextView tipTV;

    //用于筛选的参数
    private String type = "0";
    private String area = "0";

    @Override
    protected int setLayoutResouceId() {
        return R.layout.fragment_list;
    }

    @Override
    protected void initView() {

        listView = findViewById(R.id.list_view);
        listView.setDividerHeight(0);
        tipTV = findViewById(R.id.tip_tv);

        //初始化刷新控件
        mPullToRefreshView = (PullToRefreshView) mRootView.findViewById(R.id.swipe_refresh);
        mPullToRefreshView.setEnablePullLoadMoreDataStatus(false);

        //设置ScrollView默认定位顶部
        sv = findViewById(R.id.over_scroll);
        sv.smoothScrollTo(0, 0);

        //读取本地是否有缓存文件
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String preInfoString = preferences.getString("reviewInfo", null);

        if (preInfoString != null) {
            LiveReviewGson liveReview = Utility.handleReviewResponse(preInfoString);
            showLiveInfo(liveReview);
        } else {
            requestLiveData(type, area);
        }

        //设置下拉监听器
        mPullToRefreshView.setOnHeaderRefreshListener(LiveReviewFragment.this);
    }

    private void requestLiveData(String type, String area) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("type", type);
            jsonObject.put("area", area);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String homeUrl = UserServerHelper.REVIEW + jsonObject.toString();

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
                final LiveReviewGson liveReview = Utility.handleReviewResponse(responseText);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (liveReview != null && "success".equals(liveReview.msg)) {
                            SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(getActivity()).edit();
                            editor.putString("reviewInfo", responseText);
                            editor.apply();
                            showLiveInfo(liveReview);
                        } else {
                            Toast.makeText(getActivity(), "获取数据失败", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }
        });
    }

    private void showLiveInfo(LiveReviewGson liveReview) {
        if (liveReview.data.size() != 0) {
            tipTV.setVisibility(View.GONE);
            reviewAdapter = new ReviewAdapter(mActivity, R.layout.video_item, liveReview.data);
            listView.setAdapter(reviewAdapter);
        } else {
            tipTV.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onHeaderRefresh(PullToRefreshView view) {
        mPullToRefreshView.postDelayed(new Runnable() {
            @Override
            public void run() {
                mPullToRefreshView.onHeaderRefreshComplete();
                requestLiveData(LiveReviewFragment.this.type, LiveReviewFragment.this.area);
            }
        }, 1000);
    }
}
