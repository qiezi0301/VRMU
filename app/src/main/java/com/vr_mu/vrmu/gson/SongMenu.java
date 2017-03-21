package com.vr_mu.vrmu.gson;

import com.google.gson.annotations.SerializedName;

/**首页SongMenu音乐部分
 * Created by zjl on 17/3/21.
 */
public class SongMenu {
    /**
     * id : 73
     * title : 哇妙佛乐测试歌单
     * img : http://api.vr-mu.com/Uploads/songsmenu/img/58a27bf8170ea.jpg
     * desc : 哇秒测试歌单By VR-Mu
     * views : 143
     */

    @SerializedName("id")
    public int id;

    @SerializedName("title")
    public String title;

    @SerializedName("img")
    public String img;

    @SerializedName("desc")
    public String desc;

    @SerializedName("views")
    public String views;

    @SerializedName("bgcolor_hex")
    public String bgcolorHex;

}