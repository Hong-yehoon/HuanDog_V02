package com.example.huandog_v02;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.huandog_v02.R;

public class JoinpageDog extends AppCompatActivity {


    ImageButton back;
    EditText Dname, Dnum, Dage, Dsort, Dplace,Dgender;
    /*RadioButton Df, Dm;*/
    /*RadioGroup Dgender;*/


    Button join;
    DatabaseOpenHelper dbhelper;
    SQLiteDatabase sqlDB;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_join_dog);

        dbhelper = new DatabaseOpenHelper(JoinpageDog.this, DatabaseOpenHelper.table02,null,1);


        back = (ImageButton)findViewById(R.id.jdBack);
        Dname = (EditText)findViewById(R.id.dName);
        Dnum = (EditText)findViewById(R.id.dRegNum);
        Dage = (EditText)findViewById(R.id.dAge);
        Dsort = (EditText)findViewById(R.id.dSort);
        Dgender = (EditText)findViewById(R.id.dGender);
        Dplace = (EditText)findViewById(R.id.dPlace);

        join = (Button)findViewById(R.id.join);




        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // *************DB*****************

                       //하나의 레코드를 정의하는 객체, 레코드 삽입과 갱신에 사용 - write
                     //여러개의 레코드를 가져와서 객페화 시켜서 하나의 필드값을 꺼낼수 있게 한다. read
                    String dname = Dname.getText().toString();
                    String dnum = Dnum.getText().toString();
                    String dage = Dage.getText().toString();
                    String dsort = Dsort.getText().toString();
                    String dgender = Dgender.getText().toString();
                    String dplace = Dplace.getText().toString();



                if (Dnum.getText().length() > 0 && Dname.getText().length() > 0
                        && Dplace.getText().toString().length() > 0) {

                    sqlDB = dbhelper.getWritableDatabase();
                    dbhelper.insertUserd(sqlDB,dnum,dname,dage,dsort,dgender,dplace);


                    Toast.makeText(getApplicationContext(), "회원가입되었습니다.", Toast.LENGTH_LONG).show();

                    Intent intent05 = new Intent(getApplicationContext(), Loginpage.class);
                    startActivity(intent05);
                    finish();
                }
                else {
                    Toast.makeText(getApplicationContext(), "* 는 필수 입력입니다. ", Toast.LENGTH_LONG).show();

                }
            }
        });

    }
}
/*
class JoinDBhelperDog extends SQLiteOpenHelper {

    JoinDBhelperDog(Context context){
        super(context,"hdDBv04.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //테이블 만들기

        //강아지

        sqLiteDatabase.execSQL("create table if not exists dog " +
                "(id integer primary key autoincrement," +
                "dRegNum integer (45) not null, " +
                "dName text not null," +
                "dAge integer (45)," +
                "dSort text, " +
                "dGender text ," +
                "dPlace text not null )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("drop table if exists dog");
    }
}
*/
