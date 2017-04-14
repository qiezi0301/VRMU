package com.vr_mu.vrmu.views;

import android.app.Application;
import android.content.Context;

import org.litepal.LitePal;

/**
 * Created by zjl on 17/4/14.
 */

public class MyApplication extends Application {
    private  static Context sContext;

    @Override
    public void onCreate() {
        sContext = getApplicationContext();
        LitePal.initialize(sContext);
    }

    public static Context getContext() {
        return sContext;
    }
}
