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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.HashSet;

public class Joinpage extends AppCompatActivity {

    Button next;
    ImageButton back;
    EditText jemail, jname, jpass, jpassChk, jphone, jaddr;
    DatabaseOpenHelper dbhelper ;
    SQLiteDatabase sqlDB;
    Cursor cur;

    //Firebase DatabaseReference instance
    /*FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();*/

    DatabaseReference database = FirebaseDatabase.getInstance().getReference();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_join);

       /* dbhelper = new DatabaseOpenHelper(Joinpage.this, DatabaseOpenHelper.table01,null,1);
        sqlDB = dbhelper.getWritableDatabase();*/


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

                String getEmail = jemail.getText().toString();
                String getPass = jpass.getText().toString();
                String getPassChk = jpassChk.getText().toString();
                String getName = jpass.getText().toString();
                String getPhone = jphone.getText().toString();
                String getAddr = jaddr.getText().toString();

                //HashMap 만들기





                if(getEmail.length() > 0 && getPass.length() > 0
                        && getPassChk.length() > 0 && getName.length() >0) {

                    if(getPass.equals(getPassChk)){


                        HashMap table = new HashMap<>();
                        table.put("userEmail",getEmail);
                        table.put("userPass",getPass);
                        table.put("userName",getName);
                        table.put("userPhone",getPhone);
                        table.put("userAddr",getAddr);

                        writeUser("1",getEmail,getPass,getName,getPhone, getAddr);

                        //dbhelper.insertUser(sqlDB,email,pass,passChk,name,phone,addr);
                        // Toast.makeText(getApplicationContext(),"정보가 입력되었습니다.",Toast.LENGTH_LONG).show();

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
    private void writeUser (String userId, String userEmail, String userPass,String userName, String userPhone, String userAddr){
        User user = new User(userEmail, userPass, userName, userPhone, userAddr);

        database.child("users").child(userId).setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                //
                Toast.makeText(getApplicationContext(),"정보가 입력되었습니다.",Toast.LENGTH_LONG).show();
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {

                        Toast.makeText(getApplicationContext(),"입력 실패",Toast.LENGTH_LONG).show();
                    }
                });

    }
}

