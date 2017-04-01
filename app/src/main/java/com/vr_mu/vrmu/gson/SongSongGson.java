package com.vr_mu.vrmu.gson;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**歌单
 * Created by zhangjialiang on 17/3/28.
 */

public class SongSongGson {

    /**
     * code : 0
     * msg : success
     * data : [{"songsmenu_id":"35","title":"哇妙测试歌单 第三季","img":"http://api.vr-mu.com/Uploads/songsmenu/img/58aad17b6651b.jpg","intro":"终于有时间把摇滚乐历史粗糙梳理了一遍、","views":"141","bgcolor_rgb":["40","62","50"],"bgcolor_hex":"#283e32","bgcolor_arr":{"red":"40","green":"62","blue":"50"}},{"songsmenu_id":"36","title":"哇妙测试歌单 第二季","img":"http://api.vr-mu.com/Uploads/songsmenu/img/58aad11de45f6.jpg","intro":"治愈小短篇，50首精选曲目。","views":"74","bgcolor_rgb":["18","52","86"],"bgcolor_hex":"#123456","bgcolor_arr":{"red":"18","green":"52","blue":"86"}},{"songsmenu_id":"64","title":"哇秒测试歌单 第一季","img":"http://api.vr-mu.com/Uploads/songsmenu/img/58aad072cb5db.jpg","intro":"哇秒测试歌单By VR-Mu","views":"131","bgcolor_rgb":["18","52","86"],"bgcolor_hex":"#123456","bgcolor_arr":{"red":"18","green":"52","blue":"86"}},{"songsmenu_id":"73","title":"哇妙佛乐测试歌单","img":"http://api.vr-mu.com/Uploads/songsmenu/img/58a27bf8170ea.jpg","intro":"哇秒测试歌单By VR-Mu","views":"150","bgcolor_rgb":["86","37","18"],"bgcolor_hex":"#562512","bgcolor_arr":{"red":"86","green":"37","blue":"18"}}]
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
         * songsmenu_id : 35
         * title : 哇妙测试歌单 第三季
         * img : http://api.vr-mu.com/Uploads/songsmenu/img/58aad17b6651b.jpg
         * intro : 终于有时间把摇滚乐历史粗糙梳理了一遍、
         * views : 141
         * bgcolor_rgb : ["40","62","50"]
         * bgcolor_hex : #283e32
         * bgcolor_arr : {"red":"40","green":"62","blue":"50"}
         */

        @SerializedName("songsmenu_id")
        public String songsmenuId;
        @SerializedName("title")
        public String title;
        @SerializedName("img")
        public String img;
        @SerializedName("intro")
        public String intro;
        @SerializedName("views")
        public String views;
        @SerializedName("bgcolor_hex")
        public String bgcolorHex;
        @SerializedName("bgcolor_arr")
        public BgcolorArrBean bgcolorArr;
        @SerializedName("bgcolor_rgb")
        public List<String> bgcolorRgb;

        public static class BgcolorArrBean {
            /**
             * red : 40
             * green : 62
             * blue : 50
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
