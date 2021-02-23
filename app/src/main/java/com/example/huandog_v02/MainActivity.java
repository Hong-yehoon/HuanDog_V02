package com.example.huandog_v02;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    private FragmentManager manager;
    private  MainFragHome01 frag01;
    private MainFragMypage02 frag02;

    private FragmentTransaction transaction;

    Switch walk;
    Switch gps;
    Button go;
    Button start;

    Intent intent;
    boolean walkFlag  =false;
    boolean gpsFlag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        manager = getSupportFragmentManager();

        frag01 = new MainFragHome01();
        frag02 = new MainFragMypage02();

        transaction = manager.beginTransaction();
        transaction.replace(R.id.frame_container, frag01).commitAllowingStateLoss();

        start = (Button)findViewById(R.id.mStart);

        intent= new Intent(MainActivity.this,StartCount.class);

        start.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Dialog startDialog = new Dialog(MainActivity.this);
                startDialog.setContentView(R.layout.start_dialog);
                startDialog.setTitle("산책조와");

                walk = (Switch)startDialog.findViewById(R.id.sdWalk);
                gps = (Switch)startDialog.findViewById(R.id.sdGPS);
                go = (Button)startDialog.findViewById(R.id.sdGo);

                walk.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                        if(isChecked){
                            walkFlag=true;
                        }else {
                            walkFlag=false;
                        }
                        intent.putExtra("walk",walkFlag);
                    }
                });

                gps.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                        if(isChecked){
                            gpsFlag = true;
                        }else {
                            gpsFlag = false;
                        }
                        intent.putExtra("gps",gpsFlag);
                    }
                });

                go.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        startActivity(intent);
                        startDialog.dismiss();
                    }
                });

                startDialog.show();
            }
        });

    }

    public void setFrag01(View v) {

       frag01 = new MainFragHome01();

       transaction = manager.beginTransaction();
       start.setVisibility(View.VISIBLE);
       transaction.replace(R.id.frame_container, frag01).commitAllowingStateLoss();


        //frag02 = new MainFragMypage02();
        /*FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();

        ft.replace(R.id.frame_container, new MainFragHome01(),"one");
        ft.commitAllowingStateLoss();*/
    }

    public void setFrag02(View v) {

        transaction = manager.beginTransaction();

        transaction.replace(R.id.frame_container, frag02).commitAllowingStateLoss();

        start.setVisibility(View.INVISIBLE);
    }
}