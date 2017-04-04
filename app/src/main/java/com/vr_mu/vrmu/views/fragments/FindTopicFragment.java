package com.vr_mu.vrmu.views.fragments;


import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.vr_mu.vrmu.R;
import com.vr_mu.vrmu.adapters.TopicAdapter;
import com.vr_mu.vrmu.gson.FindTopicGson;
import com.vr_mu.vrmu.presenters.UserServerHelper;
import com.vr_mu.vrmu.utils.HttpUtil;
import com.vr_mu.vrmu.utils.Utility;
import com.vr_mu.vrmu.views.customize.PullToRefreshView;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**话题
 * A simple {@link Fragment} subclass.
 */
public class FindTopicFragment extends BaseFragment implements PullToRefreshView.OnHeaderRefreshListener {
    private PullToRefreshView mPullToRefreshView;

    private ListView listView;
    private TopicAdapter topicAdapter;

    private ScrollView sv;

    //缓存数据保存常量
    private static final String PAGEDATA = "TopicInfo";

    @Override
    protected int setLayoutResouceId() {
        //TODO 忘记修改会导致空指针错误
        return R.layout.fragment_list;
    }

    @Override
    protected void initView() {

        listView = findViewById(R.id.list_view);
        listView.setDividerHeight(1);

        //初始化刷新控件
        mPullToRefreshView = findViewById(R.id.swipe_refresh);
        mPullToRefreshView.setEnablePullLoadMoreDataStatus(false);

        //设置ScrollView默认定位顶部
        sv = findViewById(R.id.over_scroll);
        sv.smoothScrollTo(0, 0);
        //设置控件两边距离
        int spacing10 = getResources().getDimensionPixelSize(R.dimen.spacing10);
        sv.setPadding(spacing10, 0, 0, 0);

        //读取本地是否有缓存文件
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String liveInfoString = preferences.getString(PAGEDATA, null);

        if (liveInfoString != null) {
            FindTopicGson dataInfo = Utility.handTopicResponse(liveInfoString);
            showLiveInfo(dataInfo);
        } else {
            requestData();
        }

        //设置下拉监听器
        mPullToRefreshView.setOnHeaderRefreshListener(FindTopicFragment.this);
    }

    private void requestData() {

        String homeUrl = UserServerHelper.TOPIC;
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
                final FindTopicGson dataInfo = Utility.handTopicResponse(responseText);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (dataInfo != null && "success".equals(dataInfo.msg)) {
                            SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(getActivity()).edit();
                            editor.putString(PAGEDATA, responseText);
                            editor.apply();
                            showLiveInfo(dataInfo);
                        } else {
                            Toast.makeText(getActivity(), "onResponse 获取数据失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    private void showLiveInfo(FindTopicGson dataList) {
        TextView emptyView = findViewById(R.id.tip_tv);
        listView.setEmptyView(emptyView); //没有数据时候显示
        topicAdapter = new TopicAdapter(mActivity, R.layout.item_topic, dataList.data.topic);
        listView.setAdapter(topicAdapter);
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
