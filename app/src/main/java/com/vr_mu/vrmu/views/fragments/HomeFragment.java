package com.vr_mu.vrmu.views.fragments;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.vr_mu.vrmu.R;
import com.vr_mu.vrmu.adapters.SongAdapter;
import com.vr_mu.vrmu.gson.HomeGson;
import com.vr_mu.vrmu.presenters.UserServerHelper;
import com.vr_mu.vrmu.utils.HttpUtil;
import com.vr_mu.vrmu.utils.Utility;
import com.vr_mu.vrmu.views.customize.LoadingProgressDialog;
import com.vr_mu.vrmu.views.customize.PullToRefreshView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements PullToRefreshView.OnHeaderRefreshListener{

    private LoadingProgressDialog mLoadingDialog;
    private PullToRefreshView mPullToRefreshView;
    private LinearLayout homeLiveLayout;
    private LinearLayout homevideolayout;
    private GridView homeSongGrid;
    private LinearLayout homeMvLayout;
    private View view;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_home, container, false);
        }
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        homeLiveLayout = (LinearLayout) view.findViewById(R.id.home_live_layout);
        homeSongGrid = (GridView) view.findViewById(R.id.home_song_grid);
        homeMvLayout = (LinearLayout) view.findViewById(R.id.home_mv_layout);
        homevideolayout = (LinearLayout) view.findViewById(R.id.home_video_layout);

        //初始化刷新控件
        mLoadingDialog = LoadingProgressDialog.createDialog(getActivity());
        mPullToRefreshView = (PullToRefreshView) view.findViewById(R.id.swipe_refresh);
        mPullToRefreshView.setEnablePullLoadMoreDataStatus(false);

        //读取本地是否有缓存文件
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String homeInfoString = preferences.getString("homeInfo", null);

        if (homeInfoString != null) {
            HomeGson homeData = Utility.handleHomeResponse(homeInfoString);
            mPullToRefreshView.setOnHeaderRefreshListener(HomeFragment.this);
            showHomeInfo(homeData);
        } else {
            requestHomeData();
        }

        setMainIcons();
    }

    public void requestHomeData() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("orderby", "");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mLoadingDialog.show();
        String homeUrl = UserServerHelper.HOME + jsonObject.toString();
        HttpUtil.sendOkHttpRequest(homeUrl, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(), "获取数据失败", Toast.LENGTH_SHORT).show();
                        mLoadingDialog.dismiss();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseText = response.body().string();
                final HomeGson homeData = Utility.handleHomeResponse(responseText);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (homeData != null && "success".equals(homeData.msg)) {
                            SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(getActivity()).edit();
                            editor.putString("homeInfo", responseText);
                            editor.apply();
                            mPullToRefreshView.setOnHeaderRefreshListener(HomeFragment.this);
                            showHomeInfo(homeData);
                        } else {
                            Toast.makeText(getActivity(), "获取数据失败", Toast.LENGTH_SHORT).show();
                        }
                        mLoadingDialog.dismiss();
                    }
                });
            }
        });
    }

    private void showHomeInfo(HomeGson homeData) {

        homeLiveLayout.removeAllViews();
        for (HomeGson.DataBean.LiveRoomBean liveRoom : homeData.data.LiveRoom) {
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.item_live, homeLiveLayout, false);
            TextView watchtv = (TextView) view.findViewById(R.id.watch_tv);
            TextView causetv = (TextView) view.findViewById(R.id.desc_tv);
            TextView nametv = (TextView) view.findViewById(R.id.name_tv);
            ImageView img123 = (ImageView) view.findViewById(R.id.img);
            CircleImageView avatarimg = (CircleImageView) view.findViewById(R.id.avatar_img);

            watchtv.setText(liveRoom.viewers);
            causetv.setText(liveRoom.desc);
            nametv.setText(liveRoom.name);
            Glide.with(this).load(liveRoom.img).into(img123);
            homeLiveLayout.addView(view);
        }

        homevideolayout.removeAllViews();
        for (HomeGson.DataBean.VideoBean video : homeData.data.Video) {

            View view = LayoutInflater.from(getActivity()).inflate(R.layout.item_video, homevideolayout, false);
            ImageView img123 = (ImageView) view.findViewById(R.id.img);
            TextView nametv = (TextView) view.findViewById(R.id.name_tv);
            TextView causetv = (TextView) view.findViewById(R.id.desc_tv);
            TextView watchtv = (TextView) view.findViewById(R.id.watch_tv);

            watchtv.setText(video.count);
            causetv.setText(video.desc);
            nametv.setText(video.name);
            Glide.with(this).load(video.img).into(img123);
            homevideolayout.addView(view);
        }

        homeSongGrid = (GridView) view.findViewById(R.id.home_song_grid);
        SongAdapter songAdapter = new SongAdapter(getActivity(), R.layout.item_song, homeData.data.SongMenu);
        homeSongGrid.setAdapter(songAdapter);


        homeMvLayout.removeAllViews();
        for (HomeGson.DataBean.MvBean mv : homeData.data.Mv) {
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.item_mv, homeMvLayout, false);
            ImageView img123 = (ImageView) view.findViewById(R.id.img);
            TextView nametv = (TextView) view.findViewById(R.id.name_tv);
            TextView causetv = (TextView) view.findViewById(R.id.desc_tv);
            TextView watchtv = (TextView) view.findViewById(R.id.watch_tv);

            watchtv.setText(mv.views + "");
            causetv.setText(mv.desc);
            nametv.setText(mv.title);
            Glide.with(this).load(mv.img).into(img123);
            homeMvLayout.addView(view);
        }

    }

    //设置主入口图标数据
    private void setMainIcons() {

        GridView gridView = (GridView) view.findViewById(R.id.fourbtn_grid);/*使用Gview控件*/
        List<Map<String, Object>> data_list = new ArrayList<>();//新建List

        int[] icon = {R.drawable.home_live_btn, R.drawable.home_music_btn, R.drawable.home_service_btn, R.drawable.home_find_btn,};//获取数据

        /*cion和iconName的长度是相同的，这里任选其一都可以*/
        for (int anIcon : icon) {
            Map<String, Object> map = new HashMap<>();
            map.put("image", anIcon);
            data_list.add(map);
        }

        //新建适配器
        SimpleAdapter sim_adapter = new SimpleAdapter(getActivity(), data_list, R.layout.item_nav, new String[]{"image"}, new int[]{R.id.nav_iv});
        gridView.setAdapter(sim_adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                }
            }
        });
    }


    @Override
    public void onHeaderRefresh(PullToRefreshView view) {
        mPullToRefreshView.postDelayed(new Runnable() {
            @Override
            public void run() {
                mPullToRefreshView.onHeaderRefreshComplete();
                requestHomeData();
            }
        }, 1000);
    }
}
