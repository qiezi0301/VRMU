package com.vr_mu.vrmu.gson;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**回顾
 * Created by zhangjialiang on 17/3/27.
 */

public class LiveReviewGson {

    /**
     * code : 0
     * msg : success
     * data : [{"vid":"5","name":"古今潮汕情 片花","desc":"这是古今潮汕情 片花的简介","img":"http://api.vr-mu.com/Uploads/video/img/58ac8aef0800f.jpg","path":"http://cdn.gzcnad.com/video/YS/GJCSQ2250.mp4","count":"126"},{"vid":"6","name":"滅火雙雄 1分钟片花","desc":"滅火雙雄 1分钟片花","img":"http://api.vr-mu.com/Uploads/video/img/58aab515753c0.jpg","path":"http://cdn.gzcnad.com/video/YS/MHSX1.mp4","count":"40"},{"vid":"7","name":"滅火雙雄 四分钟片花","desc":"滅火雙雄 四分钟片花","img":"http://api.vr-mu.com/Uploads/video/img/58aab50860e7e.jpg","path":"http://cdn.gzcnad.com/video/YS/MHSX4.mp4","count":"23"},{"vid":"8","name":"目击 片花","desc":"目击 片花","img":"http://api.vr-mu.com/Uploads/video/img/58aab436d43e7.jpg","path":"http://cdn.gzcnad.com/video/YS/MJ.mp4","count":"21"}]
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
         * vid : 5
         * name : 古今潮汕情 片花
         * desc : 这是古今潮汕情 片花的简介
         * img : http://api.vr-mu.com/Uploads/video/img/58ac8aef0800f.jpg
         * path : http://cdn.gzcnad.com/video/YS/GJCSQ2250.mp4
         * count : 126
         */

        @SerializedName("vid")
        public String vid;
        @SerializedName("name")
        public String name;
        @SerializedName("desc")
        public String desc;
        @SerializedName("img")
        public String img;
        @SerializedName("path")
        public String path;
        @SerializedName("count")
        public String count;
    }
}
