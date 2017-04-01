package com.vr_mu.vrmu.gson;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**歌手
 * Created by zhangjialiang on 17/3/29.
 */

public class SongSingerGson {

    /**
     * code : 0
     * msg : success
     * data : [{"singer_id":"25","name":"阿兰达瓦卓玛","alias":"A","img":"http://api.vr-mu.com/Uploads/singer/img/58aacd76dd22c.jpg"},{"singer_id":"21","name":"齐豫","alias":"Q","img":"http://api.vr-mu.com/Uploads/singer/img/58aac789a55c5.jpg"},{"singer_id":"26","name":"测试其他","alias":"Q","img":"http://api.vr-mu.com/Uploads/singer/img/58ac303353286.jpg"},{"singer_id":"23","name":"吴尧尧YoYo","alias":"W","img":"http://api.vr-mu.com/Uploads/singer/img/58aacb70564e0.jpg"},{"singer_id":"22","name":"紫君","alias":"Z","img":"http://api.vr-mu.com/Uploads/singer/img/58aac9cb877ca.jpg"},{"singer_id":"24","name":"张寒","alias":"Z","img":"http://api.vr-mu.com/Uploads/singer/img/58aacca0e8523.jpg"}]
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
         * singer_id : 25
         * name : 阿兰达瓦卓玛
         * alias : A
         * img : http://api.vr-mu.com/Uploads/singer/img/58aacd76dd22c.jpg
         */

        @SerializedName("singer_id")
        public String singerId;
        @SerializedName("name")
        public String name;
        @SerializedName("alias")
        public String alias;
        @SerializedName("img")
        public String img;
    }
}
