package com.vr_mu.vrmu.views.fragments;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
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
import com.vr_mu.vrmu.gson.Home;
import com.vr_mu.vrmu.gson.LiveRoom;
import com.vr_mu.vrmu.gson.Mv;
import com.vr_mu.vrmu.gson.SongMenu;
import com.vr_mu.vrmu.gson.Video;
import com.vr_mu.vrmu.utils.HttpUtil;
import com.vr_mu.vrmu.utils.Utility;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import static com.vr_mu.vrmu.R.id.img;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private SwipeRefreshLayout swipeRefreshLayout;
    private LinearLayout homeLiveLayout;
    private LinearLayout homevideolayout;
    private GridView homeSongGrid;
    private LinearLayout homeMvLayout;
    private View view;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        homeLiveLayout = (LinearLayout) view.findViewById(R.id.home_live_layout);
        homeSongGrid = (GridView) view.findViewById(R.id.home_song_grid);
        homeMvLayout = (LinearLayout) view.findViewById(R.id.home_mv_layout);
        homevideolayout = (LinearLayout) view.findViewById(R.id.home_video_layout);

        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                requestHomeData();
            }
        });

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String homeInfoString = preferences.getString("homeInfo", null);

        if (homeInfoString != null) {
            Home home = Utility.handleHomeResponse(homeInfoString);
            showHomeInfo(home);
        } else {
            requestHomeData();
        }

        setMainIcons();
    }

    public void requestHomeData() {
        String homeUrl = "http://api.vr-mu.com/Api/Home/data?msg=%7b%22encrypt%22%3a%22yes%22%7d";
        HttpUtil.sendOkHttpRequest(homeUrl, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(), "获取首页数据失败", Toast.LENGTH_SHORT).show();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseText = response.body().string();
                final Home home = Utility.handleHomeResponse(responseText);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (home != null && "success".equals(home.msg)) {
                            SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(getActivity()).edit();
                            editor.putString("homeInfo", responseText);
                            editor.apply();
                            showHomeInfo(home);
                        } else {
                            Toast.makeText(getActivity(), "获取首页数据失败", Toast.LENGTH_SHORT).show();
                        }
                        swipeRefreshLayout.setRefreshing(false);
                    }
                });
            }
        });
    }

    private void showHomeInfo(Home home) {
        homeLiveLayout.removeAllViews();
        for (LiveRoom liveRoom : home.homeData.liveRoomList) {
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.live_item, homeLiveLayout, false);
            TextView watchtv = (TextView) view.findViewById(R.id.watch_tv);
            TextView causetv = (TextView) view.findViewById(R.id.desc_tv);
            TextView nametv = (TextView) view.findViewById(R.id.name_tv);
            ImageView img123 = (ImageView) view.findViewById(img);
            CircleImageView avatarimg = (CircleImageView) view.findViewById(R.id.avatar_img);

            watchtv.setText(liveRoom.viewers);
            causetv.setText(liveRoom.desc);
            nametv.setText(liveRoom.name);
            Glide.with(this).load(liveRoom.img).into(img123);
            homeLiveLayout.addView(view);
        }

        homevideolayout.removeAllViews();
        for (Video video : home.homeData.videoList) {

            View view = LayoutInflater.from(getActivity()).inflate(R.layout.video_item, homevideolayout, false);
            ImageView img123 = (ImageView) view.findViewById(img);
            TextView nametv = (TextView) view.findViewById(R.id.name_tv);
            TextView causetv = (TextView) view.findViewById(R.id.desc_tv);
            TextView watchtv = (TextView) view.findViewById(R.id.watch_tv);

            watchtv.setText(video.viewers);
            causetv.setText(video.desc);
            nametv.setText(video.name);
            Glide.with(this).load(video.img).into(img123);
            homevideolayout.addView(view);
        }

        List<SongMenu> songList = new ArrayList<>();
        homeSongGrid = (GridView) view.findViewById(R.id.home_song_grid);

        for (SongMenu songMenu : home.homeData.songMenu) {
            songList.add(songMenu);
        }
        SongAdapter songAdapter = new SongAdapter(getActivity(), R.layout.song_item, songList);
        homeSongGrid.setAdapter(songAdapter);

        List<Mv> mvList = home.homeData.mvList;
        homeMvLayout.removeAllViews();
        for (Mv mv : mvList) {
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.mv_item, homeMvLayout, false);
            ImageView img123 = (ImageView) view.findViewById(img);
            TextView nametv = (TextView) view.findViewById(R.id.name_tv);
            TextView causetv = (TextView) view.findViewById(R.id.desc_tv);
            TextView watchtv = (TextView) view.findViewById(R.id.watch_tv);

            watchtv.setText(mv.viewers);
            causetv.setText(mv.desc);
            nametv.setText(mv.name);
            Glide.with(this).load(mv.img).into(img123);
            homeMvLayout.addView(view);
        }
    }

    //设置主入口图标数据
    private void setMainIcons() {
        /*使用Gview控件*/
        GridView gridView = (GridView) view.findViewById(R.id.fourbtn_grid);
        //新建List
        List<Map<String, Object>> data_list = new ArrayList<Map<String, Object>>();

        //获取数据
        int[] icon = {R.drawable.home_live_btn, R.drawable.home_music_btn, R.drawable.home_service_btn, R.drawable.home_find_btn,};

        /*cion和iconName的长度是相同的，这里任选其一都可以*/
        for (int i = 0; i < icon.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("image", icon[i]);
            data_list.add(map);
        }

        //新建适配器
        SimpleAdapter sim_adapter = new SimpleAdapter(getActivity(), data_list, R.layout.nav_item, new String[]{"image"}, new int[]{R.id.nav_iv});
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

}
