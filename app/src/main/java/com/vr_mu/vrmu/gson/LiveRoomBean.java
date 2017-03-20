package com.vr_mu.vrmu.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by zhangjialiang on 17/3/21.
 */
public class LiveRoomBean {
    /**
     * id : 31
     * name : VR直播测试
     * headimg :
     * img : http://api.vr-mu.com/Uploads/room/img/58afddaab82ab.jpg
     * pathtype : 1
     * path : http://live.gzcnad.com/AppName/StreamName.m3u8
     * desc : 这是“VR直播测试”的描述
     * viewers : 71
     * subscribers : 0
     * createtime : 0
     */

    @SerializedName("id")
    public int liveId;
    @SerializedName("name")
    public String liveName;
    @SerializedName("headimg")
    public String headimg;
    @SerializedName("img")
    public String img;
    @SerializedName("pathtype")
    public int pathtype;
    @SerializedName("path")
    public String path;
    @SerializedName("desc")
    public String desc;
    @SerializedName("viewers")
    public String viewers;
    @SerializedName("subscribers")
    public int subscribers;
    @SerializedName("createtime")
    public String createtime;
}