package com.vr_mu.vrmu.gson;

import com.google.gson.annotations.SerializedName;

/**首页汇总
 * Created by zhangjialiang on 17/3/20.
 */

public class Home {

    /**
     * code : 0
     * msg : success
     * data : {}
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
