package com.vr_mu.vrmu.utils;

import com.google.gson.Gson;
import com.vr_mu.vrmu.gson.Home;

/**
 * Created by zhangjialiang on 17/3/20.
 */

public class Utility {
    public static Home handleHomeResponse(String response){
        try {
            return new Gson().fromJson(response, Home.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
