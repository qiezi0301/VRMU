package com.vr_mu.vrmu.views.fragments;


import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.vr_mu.vrmu.R;
import com.vr_mu.vrmu.adapters.LiveAdapter;
import com.vr_mu.vrmu.gson.LiveLiveGson;
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

/**直播列表页
 * A simple {@link Fragment} subclass.
 */
public class LiveLiveFragment extends BaseFragment implements PullToRefreshView.OnHeaderRefreshListener{


    private PullToRefreshView mPullToRefreshView;

    private View bannerview;
    private ListView listView;
    private LiveAdapter liveAdapter;

    private ScrollView sv;
    private TextView tipTV;

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
        tipTV = findViewById(R.id.tip_tv);
        //初始化刷新控件
        mPullToRefreshView = (PullToRefreshView) mRootView.findViewById(R.id.swipe_refresh);
        mPullToRefreshView.setEnablePullLoadMoreDataStatus(false);

        //设置ScrollView默认定位顶部
        sv = findViewById(R.id.over_scroll);
        sv.smoothScrollTo(0, 0);

        //读取本地是否有缓存文件
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String liveInfoString = preferences.getString("liveInfo", null);

        if (liveInfoString != null) {
            LiveLiveGson liveLive = Utility.handleLiveResponse(liveInfoString);
            showLiveInfo(liveLive);
        } else {
            requestLiveData();
        }

        //设置下拉监听器
        mPullToRefreshView.setOnHeaderRefreshListener(LiveLiveFragment.this);
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

    private void showLiveInfo(LiveLiveGson liveLive) {
        if (liveLive.data.size() != 0) {
            tipTV.setVisibility(View.GONE);
            liveAdapter = new LiveAdapter(mActivity, R.layout.live_item, liveLive.data);
            listView.setAdapter(liveAdapter);
        }else {
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