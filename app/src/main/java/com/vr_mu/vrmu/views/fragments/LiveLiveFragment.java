package com.vr_mu.vrmu.views.fragments;


import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Toast;

import com.vr_mu.vrmu.R;
import com.vr_mu.vrmu.adapters.RoomAdapter;
import com.vr_mu.vrmu.gson.Live;
import com.vr_mu.vrmu.gson.LiveHome;
import com.vr_mu.vrmu.presenters.UserServerHelper;
import com.vr_mu.vrmu.utils.HttpUtil;
import com.vr_mu.vrmu.utils.Utility;
import com.vr_mu.vrmu.views.customize.LoadingProgressDialog;
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
public class LiveLiveFragment extends BaseFragment implements PullToRefreshView.OnHeaderRefreshListener{

    private LoadingProgressDialog mLoadingDialog;
    private PullToRefreshView mPullToRefreshView;

    private View bannerview;
    private ListView listView;
    private List<Live> liveList = new ArrayList<>();

    private ScrollView sv;

    @Override
    protected int setLayoutResouceId() {
        return R.layout.fragment_live_live;
    }

    @Override
    protected void initView() {
        bannerview = LayoutInflater.from(mActivity).inflate(R.layout.lay_banner, null);
        listView = findViewById(R.id.list_view);
        listView.addHeaderView(bannerview);

        //初始化刷新控件
        mLoadingDialog = LoadingProgressDialog.createDialog(getActivity());
        mPullToRefreshView = (PullToRefreshView) mRootView.findViewById(R.id.swipe_refresh);
        mPullToRefreshView.setEnablePullLoadMoreDataStatus(false);

        sv = findViewById(R.id.over_scroll);
        sv.smoothScrollTo(0, 0);

        //读取本地是否有缓存文件
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String liveInfoString = preferences.getString("liveInfo", null);

        if (liveInfoString != null) {
            LiveHome liveHome = Utility.handleLiveResponse(liveInfoString);
            mPullToRefreshView.setOnHeaderRefreshListener(LiveLiveFragment.this);
            showLiveInfo(liveHome);
        } else {
            requestLiveData();
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
                            editor.putString("liveInfo", responseText);
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
        for (Live live : liveHome.liveRoomList) {
            liveList.add(live);
            Log.d(TAG, "showLiveInfo>>>>>>>>>>>>>>>>: " + live.name);
        }
        RoomAdapter liveAdapter = new RoomAdapter(mActivity, R.layout.live_item, liveHome.liveRoomList);
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
}