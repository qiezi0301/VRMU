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
import com.vr_mu.vrmu.adapters.HotAdapter;
import com.vr_mu.vrmu.gson.SongHotGson;
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
 * A simple {@link Fragment} subclass.
 */
public class SongHotFragment extends BaseFragment implements PullToRefreshView.OnHeaderRefreshListener{
    private PullToRefreshView mPullToRefreshView;

    private ListView listView;
    private HotAdapter hotAdapter;

    private ScrollView sv;
    private TextView tipTV;


    @Override
    protected int setLayoutResouceId() {
        //TODO 忘记修改会导致空指针错误
        return R.layout.fragment_list;
    }

    @Override
    protected void initView() {

        listView = findViewById(R.id.list_view);
        listView.setDividerHeight(1);
        tipTV = findViewById(R.id.tip_tv);
        //初始化刷新控件
        mPullToRefreshView = (PullToRefreshView) mRootView.findViewById(R.id.swipe_refresh);
        mPullToRefreshView.setEnablePullLoadMoreDataStatus(false);

        //设置ScrollView默认定位顶部
        sv = findViewById(R.id.over_scroll);
        sv.smoothScrollTo(0, 0);
        //设置控件两边距离
        int spacing10 = getResources().getDimensionPixelSize(R.dimen.spacing10);
        sv.setPadding(spacing10, 0, 0, 0);

        //读取本地是否有缓存文件
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String liveInfoString = preferences.getString("hotInfo", null);

        if (liveInfoString != null) {
            SongHotGson dataInfo = Utility.handleHotResponse(liveInfoString);
            showLiveInfo(dataInfo);
        } else {
            requestLiveData();
        }

        //设置下拉监听器
        mPullToRefreshView.setOnHeaderRefreshListener(SongHotFragment.this);
    }

    private void requestLiveData() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("encrypt", "yes");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String homeUrl = UserServerHelper.HOT + jsonObject.toString();

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
                final SongHotGson dataInfo = Utility.handleHotResponse(responseText);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (dataInfo != null && "success".equals(dataInfo.msg)) {
                            SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(getActivity()).edit();
                            editor.putString("hotInfo", responseText);
                            editor.apply();
                            showLiveInfo(dataInfo);
                        } else {
                            Toast.makeText(getActivity(), "获取数据失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    private void showLiveInfo(SongHotGson dataList) {
        if (dataList.data.size() != 0) {
            tipTV.setVisibility(View.GONE);
            hotAdapter = new HotAdapter(mActivity, R.layout.item_song_hot, dataList.data);
            listView.setAdapter(hotAdapter);
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
                requestLiveData();
            }
        }, 1000);
    }

}
