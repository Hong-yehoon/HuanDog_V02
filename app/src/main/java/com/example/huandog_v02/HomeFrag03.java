package com.example.huandog_v02;

import android.app.NotificationManager;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

public class HomeFrag03 extends Fragment {

    ImageView time ;
    TextView timeView01,day01, timeView02, day02  ;

    SQLiteDatabase sqlDB;
    DatabaseOpenHelper helper;

    String sql;
    Cursor cur;

    //시간 예약 푸시 알림
    int mhour;
    int minute;

    Date nowTime;
    Date selTime;

    NotificationManager manager;

    private static String CHANNEL_ID = "channel1";
    private static String CHANNEL_NAME = "Channel1";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }



    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_state,null);

        time = (ImageView)view.findViewById(R.id.imv01);

        timeView01 = (TextView)view.findViewById(R.id.tv03);
        day01 = (TextView)view.findViewById(R.id.tv02);

        timeView02 = (TextView)view.findViewById(R.id.tv05);
        day02 = (TextView)view.findViewById(R.id.tv06);


        helper = new DatabaseOpenHelper( getContext(), DatabaseOpenHelper.table03,null,1);
        sqlDB = helper.getReadableDatabase();
        sql = "SELECT walkTime , walkDay FROM "+ helper.table03;
        cur = sqlDB.rawQuery(sql, null);
        cur.moveToLast();

        if(cur !=null && cur.moveToLast()){
            String tt = cur.getString(0);
            String dd = cur.getString(1);
            timeView02.setText(dd);
            day02.setText(tt);
        }

        cur.close();
        helper.close();

        long now = System.currentTimeMillis();
        Date mDate = new Date(now);
        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy"+"년"+"MM"+"월"+"dd"+"일");
        String getDay = simpleDate.format(mDate);


        //푸시 알림쓰기 위한 시간 포맷 변경
        Date pushDate = new Date(now);
        SimpleDateFormat pushDate_Time = new SimpleDateFormat("k:mm"); // k는 our in day 24시간

        String getpushTime = pushDate_Time.format(pushDate);

        try {

            nowTime = pushDate_Time.parse(getpushTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }



        //시간 예약
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c= Calendar.getInstance();

                mhour = c.get(Calendar.HOUR_OF_DAY);
                minute = c.get(Calendar.MINUTE);



            TimePickerDialog timePickerDialog = new TimePickerDialog(
                    getContext(), new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int h, int m) {

                   day01.setText(getDay);
                   timeView01.setText(h+"시"+m+"분");
                   String e = h+":"+m;

                    try {
                       selTime = pushDate_Time.parse(e);
                    } catch (ParseException parseException) {
                        parseException.printStackTrace();
                    }

                }
            }
                    , mhour, minute,true);
            timePickerDialog.show();
            }
        });

        /*long diff = nowTime.getTime() - selTime.getTime();
        long min = diff/60000;

        if (min <= 10){
            Log.d("tag","10분전");
        }*/

        return view;


    }


}

