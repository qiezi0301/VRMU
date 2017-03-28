package com.vr_mu.vrmu.utils;

import com.google.gson.Gson;
import com.vr_mu.vrmu.gson.HomeGson;
import com.vr_mu.vrmu.gson.LiveLiveGson;
import com.vr_mu.vrmu.gson.LiveReviewGson;
import com.vr_mu.vrmu.gson.LiveVarietyGson;
import com.vr_mu.vrmu.gson.LiveVideoGson;
import com.vr_mu.vrmu.gson.SongAlbumGson;
import com.vr_mu.vrmu.gson.SongMvGson;
import com.vr_mu.vrmu.gson.SongSongGson;

/**
 * Created by zhangjialiang on 17/3/20.
 */

public class Utility {
    /**
     * 将返回的JSON数据解析成Home实体类
     * @param response
     * @return
     */
    public static HomeGson handleHomeResponse(String response){
        try {
            return new Gson().fromJson(response, HomeGson.class);
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
    public static LiveLiveGson handleLiveResponse(String response) {
        try {
            return new Gson().fromJson(response, LiveLiveGson.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将返回的JSON数据解析成Variety实体类
     * @param response
     * @return
     */
    public static LiveVarietyGson handleVarietResponse(String response) {
        try {
            return new Gson().fromJson(response, LiveVarietyGson.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将返回的JSON数据解析成Video实体类
     * @param response
     * @return
     */
    public static LiveVideoGson handleVideoResponse(String response) {
        try {
            return new Gson().fromJson(response, LiveVideoGson.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将返回的JSON数据解析成Video实体类
     * @param response
     * @return
     */
    public static LiveReviewGson handleReviewResponse(String response) {
        try {
            return new Gson().fromJson(response, LiveReviewGson.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将返回的JSON数据解析成Song实体类
     * @param response
     * @return
     */
    public static SongSongGson handleSongResponse(String response) {
        try {
            return new Gson().fromJson(response, SongSongGson.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将返回的JSON数据解析成Song实体类
     * @param response
     * @return
     */
    public static SongMvGson handleMvResponse(String response) {
        try {
            return new Gson().fromJson(response, SongMvGson.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将返回的JSON数据解析成songAlbum实体类
     * @param response
     * @return
     */
    public static SongAlbumGson handleAlbumResponse(String response) {
        try {
            return new Gson().fromJson(response, SongAlbumGson.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
