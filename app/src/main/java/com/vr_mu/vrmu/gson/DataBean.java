package com.vr_mu.vrmu.gson;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by zhangjialiang on 17/3/21.
 */
public class DataBean {

    @SerializedName("Banner")
    public List<BannerBean> Banner;

    @SerializedName("LiveRoom")
    public List<LiveRoomBean> liveRoomList;

    @SerializedName("Video")
    public List<VideoBean> Video;

    @SerializedName("SongMenu")
    public List<SongMenuBean> SongMenu;

    @SerializedName("Mv")
    public List<MvBean> Mv;

    public class BannerBean {
        /**
         * id : 4
         * img : http://api.vr-mu.com/Uploads/banner/home/banner1.jpg?1111
         */

        @SerializedName("id")
        public int bannerId;

        @SerializedName("img")
        public String img;
    }


    public class VideoBean {
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
        public int videoId;
        @SerializedName("name")
        public String videoName;
        @SerializedName("desc")
        public String desc;
        @SerializedName("img")
        public String img;
        @SerializedName("count")
        public String count;
        @SerializedName("pathtype")
        public int pathtype;
        @SerializedName("path")
        public String path;
    }

    public class SongMenuBean {
        /**
         * id : 73
         * title : 哇妙佛乐测试歌单
         * img : http://api.vr-mu.com/Uploads/songsmenu/img/58a27bf8170ea.jpg
         * desc : 哇秒测试歌单By VR-Mu
         * views : 143
         * bgcolor_rgb : ["86","37","18"]
         * bgcolor_hex : #562512
         * bgcolor_arr : {"red":"86","green":"37","blue":"18"}
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
        @SerializedName("bgcolor_arr")
        public BgcolorArrBean bgcolorArr;
        @SerializedName("bgcolor_rgb")
        public List<String> bgcolorRgb;

        public class BgcolorArrBean {
            /**
             * red : 86
             * green : 37
             * blue : 18
             */

            @SerializedName("red")
            public String red;
            @SerializedName("green")
            public String green;
            @SerializedName("blue")
            public String blue;
        }
    }

    public class MvBean {
        /**
         * id : 43
         * singer : 其他
         * title : 泰雷加 《阿尔汉布拉宫的回忆》
         * img : http://api.vr-mu.com/Uploads/mv/img/5844c4930a32b.jpg
         * desc : 泰雷加 《阿尔汉布拉宫的回忆》 吉他_标清简介
         * views : 56
         * bgcolor_rgb : ["0","0","0"]
         * bgcolor_hex : #000000
         * bgcolor_arr : {"red":"0","green":"0","blue":"0"}
         */

        @SerializedName("id")
        public int id;
        @SerializedName("singer")
        public String singer;
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
        @SerializedName("bgcolor_arr")
        public BgcolorArrBeanX bgcolorArr;
        @SerializedName("bgcolor_rgb")
        public List<String> bgcolorRgb;

        public class BgcolorArrBeanX {
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

