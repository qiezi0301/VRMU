package com.vr_mu.vrmu.model;

import android.widget.ImageView;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;

/**直播实体类
 * Created by zhangjialiang on 17/3/26.
 */

public class Live {
    private ImageView img;
    private CircleImageView avatarImg;
    private TextView nameTv;
    private TextView descTv;
    private TextView watchTv;

    public Live(ImageView img, CircleImageView avatarImg, TextView nameTv, TextView descTv, TextView watchTv) {
        this.img = img;
        this.avatarImg = avatarImg;
        this.nameTv = nameTv;
        this.descTv = descTv;
        this.watchTv = watchTv;
    }

    public ImageView getImg() {
        return img;
    }

    public CircleImageView getAvatarImg() {
        return avatarImg;
    }

    public TextView getNameTv() {
        return nameTv;
    }

    public TextView getDescTv() {
        return descTv;
    }

    public TextView getWatchTv() {
        return watchTv;
    }
}
