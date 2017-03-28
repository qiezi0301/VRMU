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
import com.vr_mu.vrmu.adapters.MvAdapter;
import com.vr_mu.vrmu.gson.SongMvGson;
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
public class SongMvFragment extends BaseFragment implements PullToRefreshView.OnHeaderRefreshListener{
    private PullToRefreshView mPullToRefreshView;


    private ListView listView;
    private MvAdapter mvAdapter;

    private ScrollView sv;
    private TextView tipTV;
    //用于筛选的参数
    private String language = "0";
    private String cate = "0";

    @Override
    protected int setLayoutResouceId() {
        return R.layout.fragment_list;
    }

    @Override
    protected void initView() {

        listView = findViewById(R.id.list_view);
        tipTV = findViewById(R.id.tip_tv);

        //初始化刷新控件
        mPullToRefreshView = (PullToRefreshView) mRootView.findViewById(R.id.swipe_refresh);
        mPullToRefreshView.setEnablePullLoadMoreDataStatus(false);

        sv = findViewById(R.id.over_scroll);
        //设置控件两边距离
        int spacing10 = getResources().getDimensionPixelSize(R.dimen.spacing10);
        sv.setPadding(spacing10, 0, spacing10, spacing10);

        //设置ScrollView默认定位顶部
        sv.smoothScrollTo(0, 0);

        //读取本地是否有缓存文件
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String liveInfoString = preferences.getString("mvInfo", null);

        if (liveInfoString != null) {
            SongMvGson songMv = Utility.handleMvResponse(liveInfoString);
            showLiveInfo(songMv);
        } else {
            requestLiveData(language, cate);
        }

        //设置下拉监听器
        mPullToRefreshView.setOnHeaderRefreshListener(SongMvFragment.this);
    }

    private void requestLiveData(String language, String cate) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("encrypt", "yes");
            jsonObject.put("language_id", language);
            jsonObject.put("cate_id", cate);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String homeUrl = UserServerHelper.MV + jsonObject.toString();

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
                final SongMvGson songMv = Utility.handleMvResponse(responseText);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (songMv != null && "success".equals(songMv.msg)) {
                            SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(getActivity()).edit();
                            editor.putString("mvInfo", responseText);
                            editor.apply();
                            showLiveInfo(songMv);
                        } else {
                            Toast.makeText(getActivity(), "获取首页数据失败", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }
        });
    }

    private void showLiveInfo(SongMvGson songMv) {
        if (songMv.data.size() != 0) {
            tipTV.setVisibility(View.GONE);
            mvAdapter = new MvAdapter(mActivity, R.layout.mv_item, songMv.data);
            listView.setAdapter(mvAdapter);
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
                requestLiveData(SongMvFragment.this.language, SongMvFragment.this.cate);
            }
        }, 1000);
    }

}
