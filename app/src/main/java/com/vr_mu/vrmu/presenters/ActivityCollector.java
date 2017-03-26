package com.vr_mu.vrmu.presenters;

import android.app.Activity;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**活动管理器
 * Created by zhangjialiang on 17/3/25.
 */

public class ActivityCollector {
    private static final String TAG = "ActivityCollector";

    public static List<Activity> activities = new ArrayList<>();

    public static void addActivity(Activity activity){
        activities.add(activity);
        Log.d(TAG, "加入管理器的活动是:============= " + activity.getClass().getSimpleName());
    }

    public static void removeActivity(Activity activity){
        activities.remove(activity);
        Log.d(TAG, "移出管理器的活动是:============= " + activity.getClass().getSimpleName());
    }

    public static void finishAll() {
        for (Activity activity : activities) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
        activities.clear();
    }
}
