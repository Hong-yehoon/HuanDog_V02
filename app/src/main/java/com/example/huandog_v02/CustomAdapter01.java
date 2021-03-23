package com.example.huandog_v02;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("rawtypes")
public class CustomAdapter01 extends ArrayAdapter {

    private final List<Dogs> list;

    static class ViewHolder{
        public TextView tv_name;
        public TextView tv_time;
        public ImageView im_image;
    }

    public CustomAdapter01(Context context, ArrayList<Dogs> list){
        super(context, 0, list);
        this.list = list;
    }

    @Override
    public View getView(int position,View convertView,ViewGroup parent) {

        final ViewHolder viewHolder;

        if (convertView == null){
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            convertView = layoutInflater.inflate(R.layout.home_timeline_listview,parent,false);
        }

        viewHolder = new ViewHolder();

        viewHolder.tv_name = (TextView)convertView.findViewById(R.id.htName);
        viewHolder.tv_time = (TextView)convertView.findViewById(R.id.htTime);
        viewHolder.im_image = (ImageView)convertView.findViewById(R.id.htImage);

        final Dogs dogs = list.get(position);
        viewHolder.tv_name.setText(dogs.getName());
        viewHolder.tv_time.setText(dogs.getTime());
        viewHolder.im_image.setImageDrawable(dogs.getImage());

        return convertView;

    }
}
