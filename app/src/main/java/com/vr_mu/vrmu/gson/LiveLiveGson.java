package com.vr_mu.vrmu.gson;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**直播GSON
 * Created by zhangjialiang on 17/3/27.
 */

public class LiveLiveGson {

    /**
     * code : 0
     * msg : success
     * data : [{"roomlive_id":"31","hostname":"VR直播测试","headimg":"","cate_id":"0","img":"http://api.vr-mu.com/Uploads/room/img/58afddaab82ab.jpg","desc":"这是\u201cVR直播测试\u201d的描述","viewers":83,"pathtype":1,"path":"http://live.gzcnad.com/AppName/StreamName.m3u8","subscribers":0,"createtime":"0","isCollect":0},{"roomlive_id":"30","hostname":"海豚","headimg":"","cate_id":"0","img":"http://api.vr-mu.com/Uploads/room/img/58ad12970015f.png","desc":"海豚","viewers":203,"pathtype":1,"path":"http://oss-cdn.gzcnad.com/1470742003_37_3840HD.mp4","subscribers":0,"createtime":"1487046260","isCollect":0},{"roomlive_id":"29","hostname":"意大利风景","headimg":"","cate_id":"0","img":"http://api.vr-mu.com/Uploads/room/img/58ad13995e3a0.png","desc":"意大利风景","viewers":54,"pathtype":1,"path":"http://oss-cdn.gzcnad.com/room/VR/fccedfadee31d9a5881dd92f2fa7f5c6.mp4","subscribers":0,"createtime":"1487046260","isCollect":0},{"roomlive_id":"10","hostname":"彭于晏正在直播健身","headimg":"http://api.vr-mu.com/Uploads/room/img/pengyuyantouxiang.jpg","cate_id":"1","img":"http://api.vr-mu.com/Uploads/room/img/pengyuyan.jpg","desc":"肌肉彭于晏教你如何科学健身","viewers":294,"pathtype":1,"path":"http://cdn.gzcnad.com/room/source/10.mp4","subscribers":201021,"createtime":"1482589314","isCollect":0},{"roomlive_id":"9","hostname":"属于你的\"sunshine\"正在直播","headimg":"http://api.vr-mu.com/Uploads/room/img/girlfriend.jpg","cate_id":"1","img":"http://api.vr-mu.com/Uploads/room/img/sunshine.jpg","desc":"大家好 我们是sunshine","viewers":127,"pathtype":1,"path":"http://cdn.gzcnad.com/room/source/9.mp4","subscribers":20,"createtime":"1482589314","isCollect":0}]
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
