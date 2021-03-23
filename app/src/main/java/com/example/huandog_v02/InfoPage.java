package com.example.huandog_v02;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class InfoPage extends AppCompatActivity {

    ImageButton back;
    Button human, dog;
    RelativeLayout hulayout, doglayout;

    DatabaseOpenHelper dbhelper01, dbhelper02;

    SQLiteDatabase sqlDB;

    String sql;
    Cursor cur;

    EditText inName, inPhone, inAddr, inEmail, inDname, inSort, inAge, inNum;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_human);

        dbhelper01 = new DatabaseOpenHelper(InfoPage.this, DatabaseOpenHelper.table01,null,1);
        dbhelper02 = new DatabaseOpenHelper(InfoPage.this, DatabaseOpenHelper.table02,null,1);

        inName = (EditText)findViewById(R.id.inName);
        inPhone = (EditText)findViewById(R.id.inPhone);
        inAddr = (EditText)findViewById(R.id.inAddr);
        inDname = (EditText)findViewById(R.id.inDname);
        inSort = (EditText)findViewById(R.id.inSort);
        inAge = (EditText)findViewById(R.id.inAge);


        back = (ImageButton)findViewById(R.id.inBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainFragMypage02.class);
                startActivity(intent);

                finish();


            }
        });


        human = (Button)findViewById(R.id.human);
        dog = (Button)findViewById(R.id.dog);
        hulayout = (RelativeLayout)findViewById(R.id.HuLayout);
        doglayout = (RelativeLayout)findViewById(R.id.DogLayout);

        doglayout.setVisibility(View.INVISIBLE);


    }

   public void mOnclick (View view){
    switch (view.getId()){

        case R.id.human:
            doglayout.setVisibility(View.INVISIBLE);
            hulayout.setVisibility(View.VISIBLE);

           /* sql = "SELECT email FROM "+ dbhelper01.table01 + " WHERE email = '"+email+"'";
            cur = sqlDB.rawQuery(sql,null);
            cur.moveToNext();*/

            break;



        case R.id.dog:
            hulayout.setVisibility(View.INVISIBLE);
            doglayout.setVisibility(View.VISIBLE);
            break;

        }

    }

}
