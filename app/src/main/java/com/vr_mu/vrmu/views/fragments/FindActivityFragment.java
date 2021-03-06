package com.vr_mu.vrmu.views.fragments;


import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.vr_mu.vrmu.R;
import com.vr_mu.vrmu.adapters.ActivityAdapter;
import com.vr_mu.vrmu.gson.FindActivityGson;
import com.vr_mu.vrmu.presenters.UserServerHelper;
import com.vr_mu.vrmu.utils.HttpUtil;
import com.vr_mu.vrmu.utils.Utility;
import com.vr_mu.vrmu.views.customize.PullToRefreshView;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class FindActivityFragment extends BaseFragment implements PullToRefreshView.OnHeaderRefreshListener {
    private PullToRefreshView mPullToRefreshView;

    private static final String PAGEDATA = "ActivityInfo";
    private ListView listView;

    @Override
    protected int setLayoutResouceId() {
        return R.layout.fragment_list;
    }

    @Override
    protected void initView() {
        listView = findViewById(R.id.list_view);
        listView.setDividerHeight(0);

        //初始化刷新控件
        mPullToRefreshView = (PullToRefreshView) mRootView.findViewById(R.id.swipe_refresh);
        mPullToRefreshView.setEnablePullLoadMoreDataStatus(false);

        //设置ScrollView默认定位顶部
        ScrollView sv = findViewById(R.id.over_scroll);
        sv.smoothScrollTo(0, 0);

        //读取本地是否有缓存文件
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String liveInfoString = preferences.getString(PAGEDATA, null);

        if (liveInfoString != null) {
            FindActivityGson liveVideo = Utility.handActivityResponse(liveInfoString);
            showInfo(liveVideo);
        } else {
            requestData();
        }

        //下拉刷新设置下拉监听器
        mPullToRefreshView.setOnHeaderRefreshListener(FindActivityFragment.this);
    }


    /**
     * 通过接口请求
     */
    private void requestData() {
        String homeUrl = UserServerHelper.ACTIVITY;
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
                final FindActivityGson dataInfo = Utility.handActivityResponse(responseText);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (dataInfo != null && "success".equals(dataInfo.msg)) {
                            SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(getActivity()).edit();
                            editor.putString(PAGEDATA, responseText);
                            editor.apply();
                            showInfo(dataInfo);
                        } else {
                            Toast.makeText(getActivity(), "获取数据失败", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }
        });
    }

    private void showInfo(FindActivityGson dataList) {
        TextView emptyView = findViewById(R.id.tip_tv);
        listView.setEmptyView(emptyView); //没有数据时候显示
        ActivityAdapter activityAdapter = new ActivityAdapter(mActivity, R.layout.item_video, dataList.data);
        listView.setAdapter(activityAdapter);

    }

    @Override
    public void onHeaderRefresh(PullToRefreshView view) {
        mPullToRefreshView.postDelayed(new Runnable() {
            @Override
            public void run() {
                mPullToRefreshView.onHeaderRefreshComplete();
                requestData();
            }
        }, 1000);
    }

}
