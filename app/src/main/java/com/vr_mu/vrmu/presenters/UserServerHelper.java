package com.vr_mu.vrmu.presenters;

/**
 * Created by zhangjialiang on 17/3/26.
 */

public class UserServerHelper {

    public static final String ftpUrl = "http://api.vr-mu.com/Api/";


    public static final String HOME = ftpUrl + "Home/data?msg=";              //首页
    public static final String LIVE = ftpUrl + "RoomLive/getList?msg=";       //直播／综艺
    public static final String VIDEO = ftpUrl + "Video/getList?msg=";         //影视
    public static final String REVIEW = ftpUrl + "Reviewer/getList?msg=";     //回顾

    public static final String SONG = ftpUrl + "Songsmenu/getcateList?msg=";  //歌单
    public static final String MV = ftpUrl + "Mv/getList?msg=";               //MV
    public static final String SINGER = ftpUrl + "Singer/getList?msg=";       //歌手
    public static final String ALBUM = ftpUrl + "Albums/getcateList?msg=";    //专辑
    public static final String HOT = ftpUrl + "Rank/getChartscate?msg=";      //热榜



}
