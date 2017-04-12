package com.vr_mu.vrmu.gson;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by zjl on 17/4/12.
 */

public class BannerGson {

    /**
     * code : 0
     * msg : success
     * data : [{"id":1,"img":"http://api.vr-mu.com/Uploads/banner/img/banner1.jpg"},{"id":2,"img":"http://api.vr-mu.com/Uploads/banner/img/banner2.jpg"},{"id":3,"img":"http://api.vr-mu.com/Uploads/banner/img/banner3.jpg"}]
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
         * id : 1
         * img : http://api.vr-mu.com/Uploads/banner/img/banner1.jpg
         */

        @SerializedName("id")
        public int id;
        @SerializedName("img")
        public String img;
    }
}
