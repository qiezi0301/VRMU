package com.vr_mu.vrmu.adapters;

import android.content.Context;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.vr_mu.vrmu.R;
import com.vr_mu.vrmu.gson.SongHotGson;

import java.util.ArrayList;
import java.util.List;

import static com.vr_mu.vrmu.R.id.songs_lay;

public class HotAdapter extends ArrayAdapter<SongHotGson.DataBean> {

    List<SongHotGson.DataBean> dataList = new ArrayList<>();
    private int lines =1;
    private int resourceId;
    private Context mContext;
    public HotAdapter(Context context, int songViewResourceId , List<SongHotGson.DataBean> items) {
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
    public SongHotGson.DataBean getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SongHotGson.DataBean item = getItem(position);
        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(mContext).inflate(resourceId, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.img = (ImageView) view.findViewById(R.id.img);
            viewHolder.watch_tv = (TextView) view.findViewById(R.id.watch_tv);
            viewHolder.songs_lay = (LinearLayout) view.findViewById(songs_lay);
            view.setTag(viewHolder);
        }else{
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.watch_tv.setText(item.views + "");
        Glide.with(mContext).load(item.img).into(viewHolder.img);
        viewHolder.songs_lay.removeAllViews();
        int i = 0;
        int spacing2 = mContext.getResources().getDimensionPixelSize(R.dimen.spacing2);



        for (SongHotGson.DataBean.SongsBean data : item.songs) {
            i++;
            TextView titleTv = new TextView(mContext);
            titleTv.setText(i + ".《" + data.title + "》-" + data.singerName);

            titleTv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
            titleTv.setLines(lines);
            titleTv.setPadding(0,spacing2,0,spacing2);
            viewHolder.songs_lay.addView(titleTv);

        }


        return view;
    }

    public class ViewHolder {
        ImageView img;
        TextView watch_tv;
        LinearLayout songs_lay;


    }
}