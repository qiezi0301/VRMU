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
import com.vr_mu.vrmu.gson.SongMenu;

import java.util.ArrayList;
import java.util.List;

import static com.vr_mu.vrmu.R.id.img;

public class SongAdapter extends ArrayAdapter<SongMenu> {
    List<SongMenu> list = new ArrayList<SongMenu>();
    private int resourceId;
    private Context mContext;
    public SongAdapter(Context context, int songViewResourceId ,List<SongMenu> songMenu) {
        super(context, songViewResourceId, songMenu);
        resourceId = songViewResourceId;
        list = songMenu;
        mContext = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public SongMenu getItem(int position) {
        return list.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SongMenu songMenu = getItem(position);
        View view;
        ViewHolder viewHolder = null;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
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
        viewHolder.nameTv.setText(songMenu.getName());
        viewHolder.descTv.setText(songMenu.getDesc());
        viewHolder.watchTv.setText(songMenu.getViewers());
        Glide.with(mContext).load(songMenu.img).into(viewHolder.img);
        return view;
    }

    public class ViewHolder {
        ImageView img;
        TextView nameTv;
        TextView descTv;
        TextView watchTv;
    }
}
