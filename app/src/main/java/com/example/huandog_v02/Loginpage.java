package com.example.huandog_v02;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Loginpage extends AppCompatActivity {

    EditText Lemail, Lpass;

    SQLiteDatabase sqlDB= null;
    DatabaseOpenHelper helper;

    String sql;
    Cursor cur;



    Button login;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mypage_login);

        helper = new DatabaseOpenHelper(Loginpage.this,DatabaseOpenHelper.table01,null,1);
        login = (Button)findViewById(R.id.mylogin);
        Lemail = (EditText)findViewById(R.id.logEmail);
        Lpass =  (EditText)findViewById(R.id.logPass);

        String email = Lemail.getText().toString();
        String pass = Lpass.getText().toString();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Lemail.getText().toString().length() > 0 && Lpass.getText().toString().length() >0) {

                }
                else {
                    Toast.makeText(getApplicationContext(),"아이디와 비밀번호는 필수사항입니다.",Toast.LENGTH_SHORT).show();
                }
                //DB 아이디, 비밀번호 확인
                //아이디
                sql = "SELECT email FROM "+ helper.table01 + " WHERE email = '"+email+"'";
                cur = sqlDB.rawQuery(sql,null);
                cur.moveToNext();
                if(cur.getCount() != 1){
                    Toast.makeText(getApplicationContext(), "가입된 메일이 아닙니다.", Toast.LENGTH_SHORT).show();
                    return;
                }
                // 비밀번호 확인
                sql = "SELECT pass FROM "+ helper.table02 + " WHERE pass = '"+pass+"'";
                cur=sqlDB.rawQuery(sql,null);
                cur.moveToNext();
                if(!pass.equals(cur.getString(0))){
                    Toast.makeText(getApplicationContext(),"비밀번호가 틀렸습니다.",Toast.LENGTH_SHORT).show();
                    return;
                }
                else{
                    Toast.makeText(getApplicationContext(), "로그인 성공", Toast.LENGTH_SHORT).show();
                    //인텐트 main
                    Intent intent = new Intent(getApplicationContext(),InfoPage.class);
                    startActivity(intent);
                    finish();
                }
                cur.close();

            }
        });
    }
}
