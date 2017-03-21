package com.vr_mu.vrmu.gson;

import com.google.gson.annotations.SerializedName;

/**首页Video录播部分
 * Created by zjl on 17/3/21.
 */
public class Video {
    /**
     * id : 17
     * name : 默剧标清
     * desc : 默剧标清
     * img : http://api.vr-mu.com/Uploads/room/img/58aab2b9cf15f.jpg
     * count : 125
     * pathtype : 0
     * path : http://cdn.gzcnad.com/room/ZY/MJ.mp4
     */

    @SerializedName("id")
    public int id;

    @SerializedName("name")
    public String name;

    @SerializedName("desc")
    public String desc;

    @SerializedName("img")
    public String img;

    @SerializedName("count")
    public String viewers;

    @SerializedName("pathtype")
    public int pathtype;

    @SerializedName("path")
    public String path;
}
