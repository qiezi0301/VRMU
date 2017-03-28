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
import com.vr_mu.vrmu.gson.SongMvGson;

import java.util.ArrayList;
import java.util.List;

public class MvAdapter extends ArrayAdapter<SongMvGson.DataBean> {
    List<SongMvGson.DataBean> dataList = new ArrayList<SongMvGson.DataBean>();
    private int resourceId;
    private Context mContext;
    public MvAdapter(Context context, int songViewResourceId , List<SongMvGson.DataBean> lists) {
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
    public SongMvGson.DataBean getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SongMvGson.DataBean item = getItem(position);
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
        viewHolder.nameTv.setText(item.singer + "-" + item.title);
        viewHolder.descTv.setText(item.desc);
        //TODO忘记转换类型会导致空指针错误
        viewHolder.watchTv.setText(item.views + "");
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