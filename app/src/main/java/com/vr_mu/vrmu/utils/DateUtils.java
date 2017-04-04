package com.vr_mu.vrmu.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zhangjialiang on 17/4/4.
 */

public class DateUtils {
    private static SimpleDateFormat sf = null;

    /* 获取系统时间 格式为："yyyy/MM/dd " */
    public static String getCurrentDate() {
        Date d = new Date();

        sf = new SimpleDateFormat("yyyy年MM月dd日");
        return sf.format(d);

    }

    // 将时间戳转为字符串
    public static String getStrTime(String time) {
        String re_StrTime = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        long long_time = Long.valueOf(time);
        re_StrTime = sdf.format(new Date(long_time * 1000L));
        return re_StrTime;
    }

    /* 将字符串转为时间戳 */
    public static long getStringToDate(String time) {
        sf = new SimpleDateFormat("yyyy年MM月dd日");
        Date date = new Date();
        try {
            date = sf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date.getTime();
    }


    /* 时间戳转换成字符窜 */
    public static String getDateToStringdatime(long time) {
        Date d = new Date(time);
        sf = new SimpleDateFormat("yyyy-MM-dd");
        return sf.format(d);
    }

    /* 时间戳转换成字符窜 */
    public static String getDateToStringhengtime(long time) {
        Date d = new Date(time);
        sf = new SimpleDateFormat("yyyyMMdd");
        return sf.format(d);
    }

    /* 时间戳转换成字符窜 */
    public static String getDateToStringheartime(long time) {
        Date d = new Date(time);
        sf = new SimpleDateFormat("HH:mm");
        return sf.format(d);
    }


    public static String timeStamp2Date(String seconds, String format) {
        if (seconds == null || seconds.isEmpty() || seconds.equals("null")) {
            return "";
        }
        if (format == null || format.isEmpty())
            format = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(Long.valueOf(seconds + "000")));
    }


    //	DateUtils.getCurrentDate(); //获取系统当前时间
    //	　　
    //	DateUtils.getDateToString(时间戳); //时间戳转为时间格式
    //	　　
    //	DateUtils.getStringToDate("时间格式");//时间格式转为时间戳
}
