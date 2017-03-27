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
import com.vr_mu.vrmu.adapters.VarietyAdapter;
import com.vr_mu.vrmu.gson.LiveVarietyGson;
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

/**直播综艺
 * A simple {@link Fragment} subclass.
 */
public class LiveVarietyFragment extends BaseFragment implements PullToRefreshView.OnHeaderRefreshListener{



    private PullToRefreshView mPullToRefreshView;

    private View bannerview;
    private ListView listView;
    private VarietyAdapter varietyAdapter;

    private ScrollView sv;
    private TextView tipTV;

    @Override
    protected int setLayoutResouceId() {
        return R.layout.fragment_live_live;
    }

    @Override
    protected void initView() {
        bannerview = LayoutInflater.from(mActivity).inflate(R.layout.lay_banner, null);
        listView = findViewById(R.id.list_view);
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
        String liveInfoString = preferences.getString("varietyInfo", null);

        if (liveInfoString != null) {
            LiveVarietyGson liveVariety = Utility.handleVarietResponse(liveInfoString);
            showLiveInfo(liveVariety);
        } else {
            requestLiveData();
        }

        //设置下拉监听器
        mPullToRefreshView.setOnHeaderRefreshListener(LiveVarietyFragment.this);
    }

    private void requestLiveData() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("orderby", "");
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
                final LiveVarietyGson liveVariety = Utility.handleVarietResponse(responseText);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (liveVariety != null && "success".equals(liveVariety.msg)) {
                            SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(getActivity()).edit();
                            editor.putString("varietyInfo", responseText);
                            editor.apply();
                            showLiveInfo(liveVariety);
                        } else {
                            Toast.makeText(getActivity(), "获取首页数据失败", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }
        });
    }

    private void showLiveInfo(LiveVarietyGson liveVariety) {
        if (liveVariety.data.size() != 0) {
            tipTV.setVisibility(View.GONE);
            varietyAdapter = new VarietyAdapter(mActivity, R.layout.video_item, liveVariety.data);
            listView.setAdapter(varietyAdapter);
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
