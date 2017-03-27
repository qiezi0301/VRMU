package com.vr_mu.vrmu.gson;

import com.google.gson.annotations.SerializedName;

/**直播列表的bean 文件
 * Created by zhangjialiang on 17/3/27.
 */

public class Live {

    /**
     * roomlive_id : 31
     * hostname : VR直播测试
     * headimg :
     * cate_id : 0
     * img : http://api.vr-mu.com/Uploads/room/img/58afddaab82ab.jpg
     * desc : 这是“VR直播测试”的描述
     * viewers : 83
     * pathtype : 1
     * path : http://live.gzcnad.com/AppName/StreamName.m3u8
     * subscribers : 0
     * createtime : 0
     * isCollect : 0
     */

    @SerializedName("roomlive_id")
    public String id;
    @SerializedName("hostname")
    public String name;
    @SerializedName("headimg")
    public String headimg;
    @SerializedName("cate_id")
    public String cateId;
    @SerializedName("img")
    public String img;
    @SerializedName("desc")
    public String desc;
    @SerializedName("viewers")
    public int viewers;
    @SerializedName("pathtype")
    public int pathtype;
    @SerializedName("path")
    public String path;
    @SerializedName("subscribers")
    public int subscribers;
    @SerializedName("createtime")
    public String createtime;
    @SerializedName("isCollect")
    public int isCollect;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getHeadimg() {
        return headimg;
    }

    public String getCateId() {
        return cateId;
    }

    public String getImg() {
        return img;
    }

    public String getDesc() {
        return desc;
    }

    public int getViewers() {
        return viewers;
    }

    public int getPathtype() {
        return pathtype;
    }

    public String getPath() {
        return path;
    }

    public int getSubscribers() {
        return subscribers;
    }

    public String getCreatetime() {
        return createtime;
    }

    public int getIsCollect() {
        return isCollect;
    }
}
