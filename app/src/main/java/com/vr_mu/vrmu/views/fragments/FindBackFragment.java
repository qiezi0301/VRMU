package com.vr_mu.vrmu.views.fragments;


import android.animation.ValueAnimator;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.vr_mu.vrmu.R;
import com.vr_mu.vrmu.adapters.BackAdapter;
import com.vr_mu.vrmu.gson.FindBackGson;
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
public class FindBackFragment extends BaseFragment implements PullToRefreshView.OnHeaderRefreshListener {
    private PullToRefreshView mPullToRefreshView;

    private static final String PAGEDATA = "BackInfo";
    private ListView listView;
    private String classId = "0";
    private FrameLayout menuLy;
    private TextView titleTv;
    private TextView cateTv;

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
        String preInfoString = preferences.getString(PAGEDATA, null);

        if (preInfoString != null) {
            FindBackGson liveVideo = Utility.handBackResponse(preInfoString);
            showInfo(liveVideo);
        } else {
            requestData(classId);
        }

        //下拉刷新设置下拉监听器
        mPullToRefreshView.setOnHeaderRefreshListener(FindBackFragment.this);
    }


    /**
     * 通过接口请求
     * @param classId
     */
    private void requestData(String classId) {
        String homeUrl = UserServerHelper.BACK + classId;
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
                final FindBackGson dataInfo = Utility.handBackResponse(responseText);
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

    private void showInfo(FindBackGson dataList) {
        TextView emptyView = findViewById(R.id.tip_tv);
        listView.setEmptyView(emptyView); //没有数据时候显示
        BackAdapter backAdapter = new BackAdapter(mActivity, R.layout.item_find_back, dataList.data.feedbackList);
        listView.setAdapter(backAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                cateTv = (TextView) view.findViewById(R.id.cate_tv);
                titleTv = (TextView) view.findViewById(R.id.title_tv);

                menuLy = (FrameLayout) view.findViewById(R.id.menu_layout);
                menuLy.measure(0, 0);
                final int height = menuLy.getMeasuredHeight();

                if (menuLy.getVisibility() == View.GONE) {
                    show(menuLy, height);
                    cateTv.setTextColor(getResources().getColor(R.color.colorPrimary));
                    titleTv.setTextColor(getResources().getColor(R.color.colorPrimary));
                } else {
                    dismiss(menuLy, height);
                    cateTv.setTextColor(getResources().getColor(R.color.colorGrey));
                    titleTv.setTextColor(getResources().getColor(R.color.colorGrey));
                }
            }
        });

    }

    //显示动画
    public void show(final View v, int height) {
        v.setVisibility(View.VISIBLE);
        ValueAnimator animator = ValueAnimator.ofInt(0, height);
        animator.setDuration(500);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (Integer) animation.getAnimatedValue();
                v.getLayoutParams().height = value;
                v.setLayoutParams(v.getLayoutParams());
            }
        });
        animator.start();
    }

    //隐藏的动画
    public void dismiss(final View v, int height) {

        ValueAnimator animator = ValueAnimator.ofInt(height, 0);
        animator.setDuration(500);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (Integer) animation.getAnimatedValue();
                if (value == 0) {
                    v.setVisibility(View.GONE);
                }
                v.getLayoutParams().height = value;
                v.setLayoutParams(v.getLayoutParams());
            }
        });
        animator.start();
    }

    @Override
    public void onHeaderRefresh(PullToRefreshView view) {
        mPullToRefreshView.postDelayed(new Runnable() {
            @Override
            public void run() {
                mPullToRefreshView.onHeaderRefreshComplete();
                requestData(FindBackFragment.this.classId);
            }
        }, 1000);
    }

}
