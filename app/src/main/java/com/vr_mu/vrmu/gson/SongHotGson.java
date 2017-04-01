package com.vr_mu.vrmu.gson;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**热榜
 * Created by zhangjialiang on 17/3/31.
 */

public class SongHotGson {

    /**
     * code : 0
     * msg : success
     * data : [{"cate_id":"1","views":"971","name":"飙升榜","img":"http://api.vr-mu.com/Uploads/charts/img/583d00d1ceb24.jpg","songs":[{"title":"別人的幸福 吴尧尧YoYo 粤语","singer_name":"吴尧尧YoYo"},{"title":"醉酒当歌 张寒","singer_name":"张寒"},{"title":"天地任我行张寒","singer_name":"张寒"}],"color":{"bgcolor_rgb":["77","25","25"],"bgcolor_hex":"#4d1919","bgcolor_arr":{"red":"77","green":"25","blue":"25"}}},{"cate_id":"2","views":"169","name":"热歌榜","img":"http://api.vr-mu.com/Uploads/charts/img/583d00c1d400a.jpg","songs":[{"title":"水岸","singer_name":"测试其他"},{"title":"问佛紫君","singer_name":"紫君"},{"title":"遇见阿弥陀佛紫君","singer_name":"紫君"}],"color":{"bgcolor_rgb":["53","70","66"],"bgcolor_hex":"#354642","bgcolor_arr":{"red":"53","green":"70","blue":"66"}}},{"cate_id":"3","views":"84","name":"新歌榜","img":"http://api.vr-mu.com/Uploads/charts/img/583d00dca61b6.jpg","songs":[{"title":"人身难得紫君","singer_name":"紫君"},{"title":"弥陀的呼唤紫君","singer_name":"紫君"},{"title":"平常心是道紫君","singer_name":"紫君"}],"color":{"bgcolor_rgb":["94","75","54"],"bgcolor_hex":"#5e4b36","bgcolor_arr":{"red":"94","green":"75","blue":"54"}}},{"cate_id":"4","views":"138","name":"原创榜","img":"http://api.vr-mu.com/Uploads/charts/img/583d00e4ec89b.jpg","songs":[{"title":"答案","singer_name":"测试其他"},{"title":"无上无等等大神咒","singer_name":"测试其他"},{"title":"摩诃","singer_name":"测试其他"}],"color":{"bgcolor_rgb":["35","61","92"],"bgcolor_hex":"#233d5c","bgcolor_arr":{"red":"35","green":"61","blue":"92"}}}]
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
         * cate_id : 1
         * views : 971
         * name : 飙升榜
         * img : http://api.vr-mu.com/Uploads/charts/img/583d00d1ceb24.jpg
         * songs : [{"title":"別人的幸福 吴尧尧YoYo 粤语","singer_name":"吴尧尧YoYo"},{"title":"醉酒当歌 张寒","singer_name":"张寒"},{"title":"天地任我行张寒","singer_name":"张寒"}]
         * color : {"bgcolor_rgb":["77","25","25"],"bgcolor_hex":"#4d1919","bgcolor_arr":{"red":"77","green":"25","blue":"25"}}
         */

        @SerializedName("cate_id")
        public String cateId;
        @SerializedName("views")
        public String views;
        @SerializedName("name")
        public String name;
        @SerializedName("img")
        public String img;
        @SerializedName("color")
        public ColorBean color;
        @SerializedName("songs")
        public List<SongsBean> songs;

        public static class ColorBean {
            /**
             * bgcolor_rgb : ["77","25","25"]
             * bgcolor_hex : #4d1919
             * bgcolor_arr : {"red":"77","green":"25","blue":"25"}
             */

            @SerializedName("bgcolor_hex")
            public String bgcolorHex;
            @SerializedName("bgcolor_arr")
            public BgcolorArrBean bgcolorArr;
            @SerializedName("bgcolor_rgb")
            public List<String> bgcolorRgb;

            public static class BgcolorArrBean {
                /**
                 * red : 77
                 * green : 25
                 * blue : 25
                 */

                @SerializedName("red")
                public String red;
                @SerializedName("green")
                public String green;
                @SerializedName("blue")
                public String blue;
            }
        }

        public static class SongsBean {
            /**
             * title : 別人的幸福 吴尧尧YoYo 粤语
             * singer_name : 吴尧尧YoYo
             */

            @SerializedName("title")
            public String title;
            @SerializedName("singer_name")
            public String singerName;
        }
    }
}
