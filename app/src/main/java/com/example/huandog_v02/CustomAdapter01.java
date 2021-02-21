package com.example.huandog_v02;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.huandog_v02.R;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter01 extends ArrayAdapter {

    private Context context;
    private List list;

    class ViewHolder{
        public TextView tv_name;
        public TextView tv_time;
        public ImageView im_image;
    }

    public CustomAdapter01(Context context, ArrayList list){
        super(context, 0, list);
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        final ViewHolder viewHolder;

        if (convertView == null){
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            convertView = layoutInflater.inflate(R.layout.home_timeline_listview,parent,false);
        }

        viewHolder = new ViewHolder();

        viewHolder.tv_name = (TextView)convertView.findViewById(R.id.htName);
        viewHolder.tv_time = (TextView)convertView.findViewById(R.id.htTime);
        viewHolder.im_image = (ImageView)convertView.findViewById(R.id.htImage);

        final Dogs dogs =(Dogs) list.get(position);
        viewHolder.tv_name.setText(dogs.getName());
        viewHolder.tv_time.setText(dogs.getTime());
        viewHolder.im_image.setImageDrawable(dogs.getImage());


        return convertView;

    }
}
