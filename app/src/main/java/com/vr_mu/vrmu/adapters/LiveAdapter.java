package com.vr_mu.vrmu.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.vr_mu.vrmu.R;
import com.vr_mu.vrmu.model.Live;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class LiveAdapter extends BaseAdapter {

    private List<Live> objects = new ArrayList<Live>();

    private Context context;
    private LayoutInflater layoutInflater;

    public LiveAdapter(Context context) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public Live getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.live_item, null);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews((Live) getItem(position), (ViewHolder) convertView.getTag());
        return convertView;
    }

    private void initializeViews(Live object, ViewHolder holder) {
        //TODO implement
    }

    protected class ViewHolder {
        private ImageView img;
        private CircleImageView avatarImg;
        private TextView nameTv;
        private TextView descTv;
        private TextView watchTv;

        public ViewHolder(View view) {
            img = (ImageView) view.findViewById(R.id.img);
            avatarImg = (CircleImageView) view.findViewById(R.id.avatar_img);
            nameTv = (TextView) view.findViewById(R.id.name_tv);
            descTv = (TextView) view.findViewById(R.id.desc_tv);
            watchTv = (TextView) view.findViewById(R.id.watch_tv);
        }
    }
}
