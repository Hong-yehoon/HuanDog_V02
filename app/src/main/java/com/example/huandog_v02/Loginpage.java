package com.example.huandog_v02;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Iterator;

public class Loginpage extends AppCompatActivity {

    EditText Lemail, Lpass;

    private DatabaseReference database;

    String userEmail, userPass;

    Button login;

    boolean loginSuccess;
    Intent intent;

    //자동로그인에 저장할 값
    String loginEmail, loginPass;
    SharedPreferences autoLogin;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mypage_login);

        login = (Button)findViewById(R.id.mylogin);
        Lemail = (EditText)findViewById(R.id.logEmail);
        Lpass =  (EditText)findViewById(R.id.logPass);


        database = FirebaseDatabase.getInstance().getReference();

        //자동 로그인
        //autoLogin = getSharedPreferences("autoLogin", Activity.MODE_PRIVATE);
        //loginEmail = autoLogin.getString("inputEmail",null);
        //loginPass = autoLogin.getString("inputPass",null);

        /*if(loginEmail != null && loginPass != null){

            //Toast.makeText(getApplicationContext(),userEmail+"로 자동로그인",Toast.LENGTH_SHORT).show();

            //Intent intentAuto = new Intent(getApplicationContext(),InfoPage.class);
            //startActivity(intentAuto);


        }else{
            login.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {

                    userEmail = Lemail.getText().toString();
                    userPass = Lpass.getText().toString();

                    if(userEmail.length() > 0 && userPass.length() >0) {
                        readUser(userEmail,userPass);


                    } else {
                        Toast.makeText(getApplicationContext(),"아이디와 비밀번호는 필수사항입니다.",Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }*/

        login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                userEmail = Lemail.getText().toString();
                userPass = Lpass.getText().toString();

                if(userEmail.length() > 0 && userPass.length() >0) {
                    readUser(userEmail,userPass);

                } else {
                    Toast.makeText(getApplicationContext(),"아이디와 비밀번호는 필수사항입니다.",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
    private void readUser(String Email, String Pass) {

        database.child("users").child(Email).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.getValue(User.class) != null){
                    User post = snapshot.getValue(User.class);
                    Log.w("User" , post.toString());
                    if(post.getUserPass().equals(Pass)){
                        Toast.makeText(getApplicationContext(),"로그인 되었습니다",Toast.LENGTH_SHORT).show();

                       //SharedPreferences.Editor auto = autoLogin.edit();
                        //auto.putString("inputEmail",Email);
                        //auto.putString("inputPass",Pass);
                        //auto.commit();

                        intent = new Intent(getApplicationContext(),InfoPage.class);
                        loginSuccess = true;

                        intent.putExtra("userEmail",Lemail.getText().toString());
                        intent.putExtra("loginS",loginSuccess);
                        startActivity(intent);


                    }else{
                        Toast.makeText(getApplicationContext(),"비밀번호 오류",Toast.LENGTH_SHORT).show();
                        loginSuccess = false;
                        intent.putExtra("loginF",loginSuccess);
                    }


                }else{
                    Toast.makeText(getApplicationContext(),"가입되지 않은 이메일입니다.",Toast.LENGTH_SHORT).show();

                    loginSuccess = false;
                    intent.putExtra("loginF",loginSuccess);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

}
