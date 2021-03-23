package com.example.huandog_v02;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class Loginpage extends AppCompatActivity {

    EditText Lemail, Lpass;

    private DatabaseReference database;

    String userEmail, userPass;

    Button login;

    Intent intent;

    //자동로그인에 저장할 값
    String loginEmail, loginPass;
    SharedPreferences auto;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mypage_login);

        login = (Button)findViewById(R.id.mylogin);
        Lemail = (EditText)findViewById(R.id.logEmail);
        Lpass =  (EditText)findViewById(R.id.logPass);

        database = FirebaseDatabase.getInstance().getReference();

        //*****************자동 로그인**************8
        auto = getSharedPreferences("autoLogin", Activity.MODE_PRIVATE);
        loginEmail = auto.getString("inputEmail",null);
        loginPass = auto.getString("inputPass",null);


        if(loginEmail == null && loginPass == null){

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

        }else{
            Toast.makeText(getApplicationContext(),loginEmail+"로 자동로그인",Toast.LENGTH_SHORT).show();
            intent = new Intent(Loginpage.this,InfoPage.class);
            startActivity(intent);
            finish();

        }

    }

    //로그인
    private void readUser(String Email, String Pass) {
        database.child("users").child(Email).addValueEventListener(new ValueEventListener() {
            User post;

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                post = snapshot.getValue(User.class);
                if (snapshot.getValue(User.class) != null) {
                    Log.w("User", post.toString());

                    if (post.getUserPass().equals(Pass)) {
                        Toast.makeText(getApplicationContext(), Email + "로 로그인 되었습니다", Toast.LENGTH_SHORT).show();

                        SharedPreferences.Editor autoLogin = auto.edit();
                        autoLogin.putString("inputEmail", Email);
                        autoLogin.putString("inputPass", Pass);
                        autoLogin.commit();

                        intent = new Intent(Loginpage.this, InfoPage.class);
                        intent.putExtra("userEmail", Lemail.getText().toString());

                        startActivity(intent);
                        finish();

                    } else {
                        Toast.makeText(getApplicationContext(), "비밀번호 오류", Toast.LENGTH_SHORT).show();

                    }
                }else{
                    Toast.makeText(getApplicationContext(), "아이디 오류", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

}
