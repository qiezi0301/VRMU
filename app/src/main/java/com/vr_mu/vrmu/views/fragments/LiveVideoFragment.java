package com.vr_mu.vrmu.views.fragments;


import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Toast;

import com.vr_mu.vrmu.R;
import com.vr_mu.vrmu.adapters.VideoAdapter;
import com.vr_mu.vrmu.gson.Live;
import com.vr_mu.vrmu.gson.LiveHome;
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
 * A simple {@link Fragment} subclass.
 */
public class LiveVideoFragment extends BaseFragment implements PullToRefreshView.OnHeaderRefreshListener{
    private PullToRefreshView mPullToRefreshView;


    private ListView listView;
    private List<Live> liveList = new ArrayList<>();
    private VideoAdapter videoAdapter;

    private ScrollView sv;

    //用于筛选的参数
    private String type = "0";
    private String area = "0";

    @Override
    protected int setLayoutResouceId() {
        return R.layout.fragment_live_live;
    }

    @Override
    protected void initView() {

        listView = findViewById(R.id.list_view);


        //初始化刷新控件
        mPullToRefreshView = (PullToRefreshView) mRootView.findViewById(R.id.swipe_refresh);
        mPullToRefreshView.setEnablePullLoadMoreDataStatus(false);

        //设置ScrollView默认定位顶部
        sv = findViewById(R.id.over_scroll);
        sv.smoothScrollTo(0, 0);

        //读取本地是否有缓存文件
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String liveInfoString = preferences.getString("videoInfo", null);

        if (liveInfoString != null) {
            LiveHome liveHome = Utility.handleLiveResponse(liveInfoString);
            showLiveInfo(liveHome);
        } else {
            requestLiveData(type, area);
        }

        //设置下拉监听器
        mPullToRefreshView.setOnHeaderRefreshListener(LiveVideoFragment.this);
    }

    private void requestLiveData(String type, String area) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("type", type);
            jsonObject.put("area", area);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String homeUrl = UserServerHelper.VIDEO + jsonObject.toString();

        HttpUtil.sendOkHttpRequest(homeUrl, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(), "获取首页数据失败", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseText = response.body().string();
                final LiveHome liveHome = Utility.handleLiveResponse(responseText);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (liveHome != null && "success".equals(liveHome.msg)) {
                            SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(getActivity()).edit();
                            editor.putString("videoInfo", responseText);
                            editor.apply();
                            showLiveInfo(liveHome);
                        } else {
                            Toast.makeText(getActivity(), "获取首页数据失败", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }
        });
    }

    private void showLiveInfo(LiveHome liveHome) {
        videoAdapter = new VideoAdapter(mActivity, R.layout.video_item, liveHome.liveRoomList);
        listView.setAdapter(videoAdapter);
    }

    @Override
    public void onHeaderRefresh(PullToRefreshView view) {
        mPullToRefreshView.postDelayed(new Runnable() {
            @Override
            public void run() {
                mPullToRefreshView.onHeaderRefreshComplete();
                requestLiveData(LiveVideoFragment.this.type, LiveVideoFragment.this.area);
            }
        }, 1000);
    }

}
