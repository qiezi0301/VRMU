package com.vr_mu.vrmu.views;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.vr_mu.vrmu.R;
import com.vr_mu.vrmu.views.fragments.FindHomeFragment;
import com.vr_mu.vrmu.views.fragments.HomeFragment;
import com.vr_mu.vrmu.views.fragments.LiveHomeFragment;
import com.vr_mu.vrmu.views.fragments.ServerHomeFragment;
import com.vr_mu.vrmu.views.fragments.SongHomeFragment;

public class HomeActivity extends BaseActivity {
    private DrawerLayout mDrawerLayout;

    /**
     * Fragment数组界面
     */
    private final Class mFragmentArray[] = {LiveHomeFragment.class, SongHomeFragment.class, HomeFragment.class, ServerHomeFragment.class, FindHomeFragment.class};
    private int mImageArray[] = {R.drawable.ic_tab_live, R.drawable.ic_tab_music, 0, R.drawable.ic_tab_service, R.drawable.ic_tab_find};
    private String mTextArray[] = {"哇-Live", "音乐", "", "服务", "发现"};

    private LayoutInflater mLayoutInflater;
    private FragmentTabHost mTabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ImageView menuView = (ImageView) findViewById(R.id.menu_iv);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navView = (NavigationView) findViewById(R.id.nav_view);

        menuView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.openDrawer(GravityCompat.START);
            }
        });
        navView.setCheckedItem(R.id.nav_money);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                mDrawerLayout.closeDrawers();
                return true;
            }
        });

        initView();
    }

    private void initView() {
        if (mLayoutInflater == null) {
            mLayoutInflater = LayoutInflater.from(this);
        }
        if (mTabHost == null) {
            // 找到TabHost
            mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
            mTabHost.setup(this, getSupportFragmentManager(), R.id.content_lay);
        }

        // 得到fragment的个数
        for (int i = 0; i < mFragmentArray.length; i++) {

            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(mTextArray[i]).setIndicator(getTabItemView(i)); // 给每个Tab按钮设置图标、文字和内容
            mTabHost.addTab(tabSpec, mFragmentArray[i], null); // 将Tab按钮添加进Tab选项卡中
        }

        mTabHost.setCurrentTab(2); //显示第三Fragment内容为当前Fragment
    }

    /**
     * 给每个Tab按钮设置图标和文字
     */
    private View getTabItemView(int index) {
        View tabItemView = mLayoutInflater.inflate(R.layout.item_tab_view, null);

        ImageView imageView = (ImageView) tabItemView.findViewById(R.id.tab_icon);
        TextView textView = (TextView) tabItemView.findViewById(R.id.tab_tv);

        imageView.setImageResource(mImageArray[index]);
        textView.setText(mTextArray[index]);
        return tabItemView;
    }
}
