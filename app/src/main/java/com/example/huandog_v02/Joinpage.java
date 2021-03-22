package com.example.huandog_v02;


import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Joinpage extends AppCompatActivity {

    Button next;
    ImageButton back;
    EditText jemail, jname, jpass, jpassChk, jaddr;

    User user;

    String getEmail, getPass, getPassChk, getName, getAddr;
    private DatabaseReference database;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_join);
        database = FirebaseDatabase.getInstance().getReference();

        next = (Button)findViewById(R.id.jNext);
        jemail = (EditText)findViewById(R.id.jEmail);
        jname = (EditText)findViewById(R.id.jName);
        jpass = (EditText)findViewById(R.id.jPass);
        jpassChk = (EditText)findViewById(R.id.jPassChk);
        jaddr = (EditText)findViewById(R.id.jAddr);

        back = (ImageButton)findViewById(R.id.joinBack);


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // *************DB*****************
                getEmail = jemail.getText().toString();
                getPass = jpass.getText().toString();
                getPassChk = jpassChk.getText().toString();
                getName = jpass.getText().toString();
                getAddr = jaddr.getText().toString();

                if(getEmail.length() > 0 && getPass.length() > 0
                        && getPassChk.length() > 0 && getName.length() >0) {

                    if(getPass.equals(getPassChk)){


                        writeUser(getEmail,getEmail,getPass,getName, getAddr);

                        Intent intent04 = new Intent(getApplicationContext(),Loginpage.class);
                        startActivity(intent04);
                        finish();

                    }else{

                        Toast.makeText(getApplicationContext(),"비밀번호가 서로 다릅니다",Toast.LENGTH_LONG).show();
                    }
                }else {
                    Toast.makeText(getApplicationContext(),"* 는 필수 입력입니다. ",Toast.LENGTH_LONG).show();
                }

            }
        });


    }
    private void writeUser (String userId, String userEmail, String userPass, String userName, String userAddr){

        user = new User(userEmail, userPass, userName, userAddr);


            database.child("users").child(userId).setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {

                    Toast.makeText(getApplicationContext(),"정보가 입력되었습니다.",Toast.LENGTH_LONG).show();
                }
            })
            .addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                    Toast.makeText(getApplicationContext(),"입력 실패",Toast.LENGTH_LONG).show();
                }
            });

    }
}

