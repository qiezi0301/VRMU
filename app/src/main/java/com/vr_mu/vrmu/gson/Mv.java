package com.vr_mu.vrmu.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by zjl on 17/3/21.
 */
public class Mv{
    /**
     * id : 43
     * singer : 其他
     * title : 泰雷加 《阿尔汉布拉宫的回忆》
     * img : http://api.vr-mu.com/Uploads/mv/img/5844c4930a32b.jpg
     * desc : 泰雷加 《阿尔汉布拉宫的回忆》 吉他_标清简介
     * views : 56
     */

    @SerializedName("id")
    public int id;

    @SerializedName("singer")
    public String singer;

    @SerializedName("title")
    public String name;

    @SerializedName("img")
    public String img;

    @SerializedName("desc")
    public String desc;

    @SerializedName("views")
    public String viewers;

}
