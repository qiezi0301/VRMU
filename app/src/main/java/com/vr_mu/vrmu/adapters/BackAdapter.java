package com.vr_mu.vrmu.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.vr_mu.vrmu.R;
import com.vr_mu.vrmu.gson.FindBackGson;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * 反馈适配器
 * Created by zjl on 17/4/10.
 */

public class BackAdapter extends ArrayAdapter<FindBackGson.DataBean.FeedbackListBean> {
    List<FindBackGson.DataBean.FeedbackListBean> dataList = new ArrayList<>();
    private int resourceId;
    private Context mContext;

    public BackAdapter(Context context, int songViewResourceId, List<FindBackGson.DataBean.FeedbackListBean> lists) {
        super(context, songViewResourceId, lists);
        resourceId = songViewResourceId;
        dataList = lists;
        mContext = context;
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public FindBackGson.DataBean.FeedbackListBean getItem(int position) {
        return dataList.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        FindBackGson.DataBean.FeedbackListBean item = getItem(position);
        View view;
        ViewHolder viewHolder = null;
        if (convertView == null) {
            view = LayoutInflater.from(mContext).inflate(resourceId, parent, false);
            viewHolder = new ViewHolder();

            viewHolder.cateTv = (TextView) view.findViewById(R.id.cate_tv);
            viewHolder.avatarImg = (CircleImageView) view.findViewById(R.id.avatar_img);
            viewHolder.titleTv = (TextView) view.findViewById(R.id.title_tv);
            viewHolder.contentTv = (TextView) view.findViewById(R.id.content_tv);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        if (item != null) {
            viewHolder.cateTv.setText(item.className);
            viewHolder.titleTv.setText("问："+item.question);
            viewHolder.contentTv.setText("答："+item.content);
            Glide.with(mContext).load(item.userIcon).into(viewHolder.avatarImg);
        }
        return view;
    }

    public class ViewHolder {
        TextView cateTv;
        CircleImageView avatarImg;
        TextView titleTv;
        TextView contentTv;
    }
}
