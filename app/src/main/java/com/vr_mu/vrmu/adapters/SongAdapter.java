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
import com.vr_mu.vrmu.gson.HomeGson;

import java.util.ArrayList;
import java.util.List;

public class SongAdapter extends ArrayAdapter<HomeGson.DataBean.SongMenuBean> {
    List<HomeGson.DataBean.SongMenuBean> dataList = new ArrayList<HomeGson.DataBean.SongMenuBean>();
    private int resourceId;
    private Context mContext;
    public SongAdapter(Context context, int songViewResourceId ,List<HomeGson.DataBean.SongMenuBean> items) {
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
    public HomeGson.DataBean.SongMenuBean getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HomeGson.DataBean.SongMenuBean item = getItem(position);
        View view;
        ViewHolder viewHolder = null;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
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
        viewHolder.nameTv.setText(item.title);
        viewHolder.descTv.setText(item.desc);
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
