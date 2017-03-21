package com.vr_mu.vrmu.gson;

import com.google.gson.annotations.SerializedName;

/**banner
 * Created by zjl on 17/3/21.
 */
public class Banner {
    /**
     * id : 4
     * img : http://api.vr-mu.com/Uploads/banner/home/banner1.jpg?1111
     */

    @SerializedName("id")
    public int id;

    @SerializedName("img")
    public String img;

}
