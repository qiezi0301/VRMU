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
import com.vr_mu.vrmu.gson.LiveVarietyGson;

import java.util.ArrayList;
import java.util.List;

import static com.vr_mu.vrmu.R.id.img;

public class VarietyAdapter extends ArrayAdapter<LiveVarietyGson.DataBean> {
    List<LiveVarietyGson.DataBean> liveList = new ArrayList<LiveVarietyGson.DataBean>();
    private int resourceId;
    private Context mContext;
    public VarietyAdapter(Context context, int songViewResourceId , List<LiveVarietyGson.DataBean> lives) {
        super(context, songViewResourceId, lives);
        resourceId = songViewResourceId;
        liveList = lives;
        mContext = context;
    }

    @Override
    public int getCount() {
        return liveList.size();
    }

    @Override
    public LiveVarietyGson.DataBean getItem(int position) {
        return liveList.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LiveVarietyGson.DataBean live = getItem(position);
        View view;
        ViewHolder viewHolder = null;
        if (convertView == null) {
            view = LayoutInflater.from(mContext).inflate(resourceId, parent, false);
            viewHolder = new ViewHolder();

            viewHolder.img = (ImageView) view.findViewById(img);
            viewHolder.nameTv = (TextView) view.findViewById(R.id.name_tv);
            viewHolder.descTv = (TextView) view.findViewById(R.id.desc_tv);
            viewHolder.watchTv = (TextView) view.findViewById(R.id.watch_tv);
            view.setTag(viewHolder);
        }else{
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.nameTv.setText(live.hostname);
        viewHolder.descTv.setText(live.desc);
        viewHolder.watchTv.setText(live.viewers + "");
        Glide.with(mContext).load(live.img).into(viewHolder.img);
        return view;
    }

    public class ViewHolder {
        ImageView img;
        TextView nameTv;
        TextView descTv;
        TextView watchTv;
    }
}