package com.vr_mu.vrmu.views;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.vr_mu.vrmu.R;
import com.vr_mu.vrmu.gson.Home;
import com.vr_mu.vrmu.gson.LiveRoomBean;
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

public class HomeActivity extends AppCompatActivity {

    private static final String TAG = "HomeActivity";
    private DrawerLayout mDrawerLayout;

    private ListView homeLiveList;
    private CircleImageView avatarImg;
    private TextView nameTv;
    private TextView causeTv;
    private TextView watchTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        homeLiveList = (ListView) findViewById(R.id.home_live_list);
        avatarImg = (CircleImageView) findViewById(R.id.avatar_img);
        nameTv = (TextView) findViewById(R.id.name_tv);
        causeTv = (TextView) findViewById(R.id.cause_tv);
        watchTv = (TextView) findViewById(R.id.watch_tv);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navView = (NavigationView) findViewById(R.id.nav_view);
        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }

        navView.setCheckedItem(R.id.nav_money);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                mDrawerLayout.closeDrawers();
                return true;
            }
        });
        setMainIcons();

        requestHomeData();
    }

    public void requestHomeData() {
        String homeUrl = "http://api.vr-mu.com/Api/Home/data?msg=%7b%22encrypt%22%3a%22yes%22%7d";
        HttpUtil.sendOkHttpRequest(homeUrl, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(HomeActivity.this, "获取首页数据失败", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseText = response.body().string();
                final Home home = Utility.handleHomeResponse(responseText);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (home != null && "success".equals(home.msg)){
                            showHomeInfo(home);
                        }else {
                            Toast.makeText(HomeActivity.this, "获取首页数据失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    private void showHomeInfo(Home home) {
        String msg = home.msg;
        for (LiveRoomBean liveRoomBean : home.homeData.liveRoomList){
            Log.d(TAG, "showHomeInfo msg: " + liveRoomBean.liveName);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            default:
        }
        return true;
    }

    //设置主入口图标数据
    private void setMainIcons() {
        /*使用Gview控件*/
        GridView gridView = (GridView) findViewById(R.id.fourbtn_grid);
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
        SimpleAdapter sim_adapter = new SimpleAdapter(this, data_list, R.layout.nav_item, new String[]{"image"}, new int[]{R.id.nav_iv});
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
