package com.vr_mu.vrmu.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.vr_mu.vrmu.R;
import com.vr_mu.vrmu.gson.LiveVideoGson;

import java.util.ArrayList;
import java.util.List;

public class VideoAdapter extends ArrayAdapter<LiveVideoGson.DataBean> {
    List<LiveVideoGson.DataBean> dataList = new ArrayList<LiveVideoGson.DataBean>();
    private int resourceId;
    private Context mContext;
    public VideoAdapter(Context context, int songViewResourceId , List<LiveVideoGson.DataBean> lists) {
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
    public LiveVideoGson.DataBean getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LiveVideoGson.DataBean item = getItem(position);
        View view;
        ViewHolder viewHolder = null;
        if (convertView == null) {
            view = LayoutInflater.from(mContext).inflate(resourceId, parent, false);
            viewHolder = new ViewHolder();

            viewHolder.img = (ImageView) view.findViewById(R.id.img);
            viewHolder.nameTv = (TextView) view.findViewById(R.id.name_tv);
            viewHolder.descTv = (TextView) view.findViewById(R.id.desc_tv);
            viewHolder.watchTv = (TextView) view.findViewById(R.id.watch_tv);
            view.setTag(viewHolder);
        }else{
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.nameTv.setText(item.name);
        viewHolder.descTv.setText(item.desc);
        //TODO忘记转换类型会导致空指针错误
        viewHolder.watchTv.setText(item.count + "");
        Glide.with(mContext).load(item.img).into(viewHolder.img);
        return view;
    }

    public class ViewHolder {
        ImageView img;
        TextView nameTv;
        TextView descTv;
        TextView watchTv;
    }
}