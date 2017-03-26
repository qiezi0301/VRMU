package com.vr_mu.vrmu.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.vr_mu.vrmu.presenters.ActivityCollector;

/**基本活动
 * Created by zhangjialiang on 17/3/25.
 */

public class BaseActivity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //知晓当前是哪一个活动
        Log.d("BaseActivity", "当前活动是：------------------->" + getClass().getSimpleName());
        ActivityCollector.addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }
}
