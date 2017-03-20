package com.vr_mu.vrmu.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by zhangjialiang on 17/3/20.
 */

public class LiveRoom {


    /**
     * id : 31
     * name : VR直播测试
     * img : http://api.vr-mu.com/Uploads/room/img/58afddaab82ab.jpg
     * path : http://live.gzcnad.com/AppName/StreamName.m3u8
     * desc : 这是“VR直播测试”的描述
     * viewers : 71
     */

    @SerializedName("id")
    public int roomId;

    @SerializedName("name")
    public String liveTitle;

    @SerializedName("img")
    public String liveImg;

    @SerializedName("path")
    public String path;

    @SerializedName("desc")
    public String desc;

    @SerializedName("viewers")
    public String viewers;
}
