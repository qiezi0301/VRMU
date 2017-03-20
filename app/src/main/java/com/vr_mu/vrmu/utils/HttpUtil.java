package com.vr_mu.vrmu.utils;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**发起一条http请求只需要调用sendOkHttpRequest()方法，传入请求地址，并注册一个回调来处理服务器响应
 * Created by zhangjialiang on 17/3/20.
 */

public class HttpUtil {
    public static void sendOkHttpRequest(String address, okhttp3.Callback callback) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(address).build();
        client.newCall(request).enqueue(callback);
    }
}
