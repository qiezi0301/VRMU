package com.vr_mu.vrmu.gson;

import com.google.gson.annotations.SerializedName;

/**首页LiveRoom直播部分
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
    public int id;

    @SerializedName("name")
    public String name;

    @SerializedName("img")
    public String img;

    @SerializedName("path")
    public String path;

    @SerializedName("desc")
    public String desc;

    @SerializedName("viewers")
    public String viewers;
}
