package com.vr_mu.vrmu.views.fragments;


import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.GridView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.vr_mu.vrmu.R;
import com.vr_mu.vrmu.adapters.AlbumAdapter;
import com.vr_mu.vrmu.gson.SongAlbumGson;
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
public class SongAlbumFragment extends BaseFragment implements PullToRefreshView.OnHeaderRefreshListener{
    private PullToRefreshView mPullToRefreshView;

    private GridView gridView;
    private AlbumAdapter albumAdapter;

    private ScrollView sv;
    private TextView tipTV;

    private String language = "0";
    private String category = "0";

    @Override
    protected int setLayoutResouceId() {
        //TODO 忘记修改会导致空指针错误
        return R.layout.fragment_grid;
    }

    @Override
    protected void initView() {

        gridView = findViewById(R.id.grid_view);

        tipTV = findViewById(R.id.tip_tv);
        //初始化刷新控件
        mPullToRefreshView = (PullToRefreshView) mRootView.findViewById(R.id.swipe_refresh);
        mPullToRefreshView.setEnablePullLoadMoreDataStatus(false);

        //设置ScrollView默认定位顶部
        sv = findViewById(R.id.over_scroll);
        sv.smoothScrollTo(0, 0);

        //读取本地是否有缓存文件
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String liveInfoString = preferences.getString("albumInfo", null);

        if (liveInfoString != null) {
            SongAlbumGson songAlbum = Utility.handleAlbumResponse(liveInfoString);
            showLiveInfo(songAlbum);
        } else {
            requestLiveData(language, category);
        }

        //设置下拉监听器
        mPullToRefreshView.setOnHeaderRefreshListener(SongAlbumFragment.this);
    }

    private void requestLiveData(String language, String category) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("language_id", language);
            jsonObject.put("cate_id", category);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String homeUrl = UserServerHelper.ALBUM + jsonObject.toString();

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
                final SongAlbumGson dataInfo = Utility.handleAlbumResponse(responseText);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (dataInfo != null && "success".equals(dataInfo.msg)) {
                            SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(getActivity()).edit();
                            editor.putString("albumInfo", responseText);
                            editor.apply();
                            showLiveInfo(dataInfo);
                        } else {
                            Toast.makeText(getActivity(), "获取首页数据失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    private void showLiveInfo(SongAlbumGson dataList) {
        if (dataList.data.size() != 0) {
            tipTV.setVisibility(View.GONE);
            albumAdapter = new AlbumAdapter(mActivity, R.layout.song_item, dataList.data);
            gridView.setAdapter(albumAdapter);
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
                requestLiveData(SongAlbumFragment.this.language, SongAlbumFragment.this.category);
            }
        }, 1000);
    }

}
