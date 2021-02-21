package com.example.huandog_v02;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.zip.Inflater;

public class Mypage extends AppCompatActivity {

    Button myNotice, myOut;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainfrag02_mypage);

        myNotice = (Button)findViewById(R.id.myNotice);
        myOut = (Button)findViewById(R.id.myOut);

        myNotice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent01 = new Intent(Mypage.this,Notice.class);
                startActivity(intent01);
            }
        });

    }
}

