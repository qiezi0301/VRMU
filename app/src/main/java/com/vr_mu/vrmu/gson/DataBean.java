package com.vr_mu.vrmu.gson;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**首页data数据部分
 * Created by zhangjialiang on 17/3/21.
 */
public class DataBean {

    @SerializedName("Banner")
    public List<Banner> banner;

    @SerializedName("LiveRoom")
    public List<LiveRoom> liveRoomList;

    @SerializedName("Video")
    public List<Video> videoList;

    @SerializedName("SongMenu")
    public List<SongMenu> songMenu;

    @SerializedName("Mv")
    public List<Mv> mvList;

}

