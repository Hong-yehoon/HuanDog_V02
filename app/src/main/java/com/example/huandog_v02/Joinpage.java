package com.example.huandog_v02;


import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Joinpage extends AppCompatActivity {

    Button next;
    ImageButton back;
    EditText jemail, jname, jpass, jpassChk, jphone, jaddr;
    DatabaseOpenHelper dbhelper ;
    SQLiteDatabase sqlDB;
    Cursor cur;

    /*String [] projection = {"id","jEamil","jpass","jPassChk","jName","jPhone","jAddr"};*/

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_join);

        dbhelper = new DatabaseOpenHelper(Joinpage.this, DatabaseOpenHelper.table01,null,1);
        sqlDB = dbhelper.getWritableDatabase();


        next = (Button)findViewById(R.id.jNext);
        jemail = (EditText)findViewById(R.id.jEmail);
        jname = (EditText)findViewById(R.id.jName);
        jpass = (EditText)findViewById(R.id.jPass);
        jpassChk = (EditText)findViewById(R.id.jPassChk);
        jphone = (EditText)findViewById(R.id.jPhone);
        jaddr = (EditText)findViewById(R.id.jAddr);

        back = (ImageButton)findViewById(R.id.joinBack);




        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                // *************DB*****************

                String email = jemail.getText().toString();
                String pass = jpass.getText().toString();
                String passChk = jpassChk.getText().toString();
                String name = jpass.getText().toString();
                String phone = jphone.getText().toString();
                String addr = jaddr.getText().toString();


                if(email.length() > 0 && pass.length() > 0
                        && passChk.length() > 0 && name.length() >0) {

                    if(pass.equals(passChk)){


                        dbhelper.insertUser(sqlDB,email,pass,passChk,name,phone,addr);
                        Toast.makeText(getApplicationContext(),"정보가 입력되었습니다.",Toast.LENGTH_LONG).show();

                        Intent intent04 = new Intent(getApplicationContext(),JoinpageDog.class);
                        startActivity(intent04);

                    }else{

                        Toast.makeText(getApplicationContext(),"비밀번호가 서로 다릅니다",Toast.LENGTH_LONG).show();
                    }
                }else {
                    Toast.makeText(getApplicationContext(),"* 는 필수 입력입니다. ",Toast.LENGTH_LONG).show();
                }

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
    }

