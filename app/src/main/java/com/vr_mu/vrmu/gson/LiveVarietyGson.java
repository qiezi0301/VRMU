package com.vr_mu.vrmu.gson;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**综艺Gson
 * Created by zhangjialiang on 17/3/27.
 */

public class LiveVarietyGson {


    /**
     * code : 0
     * msg : success
     * data : [{"roomlive_id":"17","hostname":"默剧标清","headimg":"http://api.vr-mu.com/Uploads/room/img/runningman.jpg","cate_id":"3","img":"http://api.vr-mu.com/Uploads/room/img/58aab2b9cf15f.jpg","desc":"默剧标清","viewers":130,"pathtype":0,"path":"http://cdn.gzcnad.com/room/ZY/MJ.mp4","subscribers":0,"createtime":"1487046260","isCollect":0},{"roomlive_id":"16","hostname":"超级豆你玩","headimg":"http://api.vr-mu.com/Uploads/room/img/runningman.jpg","cate_id":"3","img":"http://api.vr-mu.com/Uploads/room/img/58aab2cee9bfe.jpg","desc":"超级豆你玩","viewers":130,"pathtype":0,"path":"http://cdn.gzcnad.com/room/ZY/CJDNW.mp4","subscribers":0,"createtime":"1487046213","isCollect":0}]
     * location :
     */

    @SerializedName("code")
    public int code;
    @SerializedName("msg")
    public String msg;
    @SerializedName("location")
    public String location;
    @SerializedName("data")
    public List<DataBean> data;

    public static class DataBean {
        /**
         * roomlive_id : 17
         * hostname : 默剧标清
         * headimg : http://api.vr-mu.com/Uploads/room/img/runningman.jpg
         * cate_id : 3
         * img : http://api.vr-mu.com/Uploads/room/img/58aab2b9cf15f.jpg
         * desc : 默剧标清
         * viewers : 130
         * pathtype : 0
         * path : http://cdn.gzcnad.com/room/ZY/MJ.mp4
         * subscribers : 0
         * createtime : 1487046260
         * isCollect : 0
         */

        @SerializedName("roomlive_id")
        public String roomliveId;
        @SerializedName("hostname")
        public String hostname;
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
    }
}
