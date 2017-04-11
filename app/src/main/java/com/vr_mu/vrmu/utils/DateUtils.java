package com.vr_mu.vrmu.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**日期格式化工具类
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
    public static String getStringToDate(String time) {
        return new SimpleDateFormat("yyyy年MM月dd日").format(new Date(Long.valueOf(time) * 1000L));
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

}
