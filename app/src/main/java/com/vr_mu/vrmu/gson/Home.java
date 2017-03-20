package com.vr_mu.vrmu.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by zhangjialiang on 17/3/20.
 */

public class Home {

    /**
     * code : 0
     * msg : success
     * data : {"Banner":[{"id":4,"img":"http://api.vr-mu.com/Uploads/banner/home/banner1.jpg?1111"},{"id":5,"img":"http://api.vr-mu.com/Uploads/banner/home/banner2.jpg?2222"},{"id":6,"img":"http://api.vr-mu.com/Uploads/banner/home/banner3.jpg?3333"}],"LiveRoom":[{"id":31,"name":"VR直播测试","headimg":"","img":"http://api.vr-mu.com/Uploads/room/img/58afddaab82ab.jpg","pathtype":1,"path":"http://live.gzcnad.com/AppName/StreamName.m3u8","desc":"这是\u201cVR直播测试\u201d的描述","viewers":"71","subscribers":0,"createtime":"0"},{"id":30,"name":"海豚","headimg":"","img":"http://api.vr-mu.com/Uploads/room/img/58ad12970015f.png","pathtype":1,"path":"http://oss-cdn.gzcnad.com/1470742003_37_3840HD.mp4","desc":"海豚","viewers":"194","subscribers":0,"createtime":"1487046260"}],"Video":[{"id":17,"name":"默剧标清","desc":"默剧标清","img":"http://api.vr-mu.com/Uploads/room/img/58aab2b9cf15f.jpg","count":"125","pathtype":0,"path":"http://cdn.gzcnad.com/room/ZY/MJ.mp4"},{"id":16,"name":"超级豆你玩","desc":"超级豆你玩","img":"http://api.vr-mu.com/Uploads/room/img/58aab2cee9bfe.jpg","count":"126","pathtype":0,"path":"http://cdn.gzcnad.com/room/ZY/CJDNW.mp4"}],"SongMenu":[{"id":73,"title":"哇妙佛乐测试歌单","img":"http://api.vr-mu.com/Uploads/songsmenu/img/58a27bf8170ea.jpg","desc":"哇秒测试歌单By VR-Mu","views":"143","bgcolor_rgb":["86","37","18"],"bgcolor_hex":"#562512","bgcolor_arr":{"red":"86","green":"37","blue":"18"}},{"id":64,"title":"哇秒测试歌单 第一季","img":"http://api.vr-mu.com/Uploads/songsmenu/img/58aad072cb5db.jpg","desc":"哇秒测试歌单By VR-Mu","views":"129","bgcolor_rgb":["18","52","86"],"bgcolor_hex":"#123456","bgcolor_arr":{"red":"18","green":"52","blue":"86"}},{"id":36,"title":"哇妙测试歌单 第二季","img":"http://api.vr-mu.com/Uploads/songsmenu/img/58aad11de45f6.jpg","desc":"治愈小短篇，50首精选曲目。","views":"73","bgcolor_rgb":["18","52","86"],"bgcolor_hex":"#123456","bgcolor_arr":{"red":"18","green":"52","blue":"86"}},{"id":35,"title":"哇妙测试歌单 第三季","img":"http://api.vr-mu.com/Uploads/songsmenu/img/58aad17b6651b.jpg","desc":"终于有时间把摇滚乐历史粗糙梳理了一遍、","views":"135","bgcolor_rgb":["40","62","50"],"bgcolor_hex":"#283e32","bgcolor_arr":{"red":"40","green":"62","blue":"50"}}],"Mv":[{"id":43,"singer":"其他","title":"泰雷加 《阿尔汉布拉宫的回忆》","img":"http://api.vr-mu.com/Uploads/mv/img/5844c4930a32b.jpg","desc":"泰雷加 《阿尔汉布拉宫的回忆》 吉他_标清简介","views":"56","bgcolor_rgb":["0","0","0"],"bgcolor_hex":"#000000","bgcolor_arr":{"red":"0","green":"0","blue":"0"}},{"id":42,"singer":"其他","title":"任天堂游戏配乐小串烧","img":"http://api.vr-mu.com/Uploads/mv/img/5844c475cf454.jpg","desc":"任天堂游戏配乐小串烧_标清简介","views":"47","bgcolor_rgb":["0","0","0"],"bgcolor_hex":"#000000","bgcolor_arr":{"red":"0","green":"0","blue":"0"}}]}
     * location :
     */

    @SerializedName("code")
    public int code;

    @SerializedName("msg")
    public String msg;

    @SerializedName("data")
    public DataBean homeData;

    @SerializedName("location")
    public String location;


}
