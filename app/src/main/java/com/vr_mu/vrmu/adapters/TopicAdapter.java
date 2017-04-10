package com.vr_mu.vrmu.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.vr_mu.vrmu.R;
import com.vr_mu.vrmu.gson.FindTopicGson;
import com.vr_mu.vrmu.utils.DateUtils;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class TopicAdapter extends ArrayAdapter<FindTopicGson.DataBean.TopicBean> {

    List<FindTopicGson.DataBean.TopicBean> dataList = new ArrayList<>();
    private int resourceId;
    private Context mContext;

    public TopicAdapter(Context context, int songViewResourceId, List<FindTopicGson.DataBean.TopicBean> items) {
        super(context, songViewResourceId, items);
        resourceId = songViewResourceId;
        dataList = items;
        mContext = context;
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public FindTopicGson.DataBean.TopicBean getItem(int position) {
        return dataList.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        FindTopicGson.DataBean.TopicBean item = getItem(position);
        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(mContext).inflate(resourceId, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.avatarImg = (CircleImageView) view.findViewById(R.id.avatar_img);
            viewHolder.titleTv = (TextView) view.findViewById(R.id.title_tv);
            viewHolder.nameDateTv = (TextView) view.findViewById(R.id.name_date_tv);
            viewHolder.descTv = (TextView) view.findViewById(R.id.desc_tv);
            viewHolder.imageLay = (LinearLayout) view.findViewById(R.id.image_lay);
            viewHolder.replyTv = (TextView) view.findViewById(R.id.reply_tv);
            viewHolder.watchTv = (TextView) view.findViewById(R.id.watch_tv);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }

        if (item != null) {
            Glide.with(mContext).load(item.userImg).into(viewHolder.avatarImg);
            viewHolder.titleTv.setText(item.title);
            Log.d("getView", "getView: " + Long.valueOf(item.pubTime));
            viewHolder.nameDateTv.setText(item.petName + " / 发布于：" + DateUtils.getStrTime(item.pubTime));
            viewHolder.descTv.setText(item.content);
            viewHolder.replyTv.setText(item.comment);
            viewHolder.watchTv.setText(item.view);

            if (item.topicImg.size() != 0) {
                viewHolder.imageLay.setVisibility(View.VISIBLE);
                viewHolder.imageLay.removeAllViews();
                for (int i = 0; i < item.topicImg.size(); i++) {
                    ImageView image = new ImageView(mContext);
                    image.setLayoutParams(new ViewGroup.LayoutParams(250,250));
                    image.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    Glide.with(mContext).load(item.topicImg.get(i)).into(image);
                    viewHolder.imageLay.addView(image);
                }
            } else {
                viewHolder.imageLay.setVisibility(View.GONE);
            }
        }
        return view;
    }

    public class ViewHolder {
        CircleImageView avatarImg;
        TextView titleTv;
        TextView nameDateTv;
        TextView descTv;
        LinearLayout imageLay;
        TextView replyTv;
        TextView watchTv;
    }
}