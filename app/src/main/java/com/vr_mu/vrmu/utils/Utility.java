package com.vr_mu.vrmu.utils;

import com.google.gson.Gson;
import com.vr_mu.vrmu.gson.Home;
import com.vr_mu.vrmu.gson.LiveHome;

/**
 * Created by zhangjialiang on 17/3/20.
 */

public class Utility {
    /**
     * 将返回的JSON数据解析成Home实体类
     * @param response
     * @return
     */
    public static Home handleHomeResponse(String response){
        try {
            return new Gson().fromJson(response, Home.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将返回的JSON数据解析成Live实体类
     * @param response
     * @return
     */
    public static LiveHome handleLiveResponse(String response) {
        try {
            return new Gson().fromJson(response, LiveHome.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
