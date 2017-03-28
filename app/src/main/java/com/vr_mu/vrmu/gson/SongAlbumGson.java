package com.vr_mu.vrmu.gson;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by zhangjialiang on 17/3/28.
 */

public class SongAlbumGson {

    /**
     * code : 0
     * msg : success
     * data : [{"albums_id":"36","title":"哇妙测试专辑","img":"http://api.vr-mu.com/Uploads/albums/img/58ac2d8077169.jpg","desc":"这是\u201c测试专辑\u201d的专辑简介","singer":"齐豫","views":0,"songs_views":0,"bgcolor_rgb":["0","0","0"],"bgcolor_hex":"#000000","bgcolor_arr":{"red":"0","green":"0","blue":"0"}}]
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
         * albums_id : 36
         * title : 哇妙测试专辑
         * img : http://api.vr-mu.com/Uploads/albums/img/58ac2d8077169.jpg
         * desc : 这是“测试专辑”的专辑简介
         * singer : 齐豫
         * views : 0
         * songs_views : 0
         * bgcolor_rgb : ["0","0","0"]
         * bgcolor_hex : #000000
         * bgcolor_arr : {"red":"0","green":"0","blue":"0"}
         */

        @SerializedName("albums_id")
        public String albumsId;
        @SerializedName("title")
        public String title;
        @SerializedName("img")
        public String img;
        @SerializedName("desc")
        public String desc;
        @SerializedName("singer")
        public String singer;
        @SerializedName("views")
        public int views;
        @SerializedName("songs_views")
        public int songsViews;
        @SerializedName("bgcolor_hex")
        public String bgcolorHex;
        @SerializedName("bgcolor_arr")
        public BgcolorArrBean bgcolorArr;
        @SerializedName("bgcolor_rgb")
        public List<String> bgcolorRgb;

        public static class BgcolorArrBean {
            /**
             * red : 0
             * green : 0
             * blue : 0
             */

            @SerializedName("red")
            public String red;
            @SerializedName("green")
            public String green;
            @SerializedName("blue")
            public String blue;
        }
    }
}
