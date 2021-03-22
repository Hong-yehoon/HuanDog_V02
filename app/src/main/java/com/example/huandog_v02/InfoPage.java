package com.example.huandog_v02;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class InfoPage extends AppCompatActivity {

    ImageButton back;

    Button logoutBtn, save;

    String name, addr, pass;

    EditText inName, inPass, inAddr;

    TextView inEmail;

    String email;


    private DatabaseReference database;

    SharedPreferences autoLogin;
    SharedPreferences.Editor editor;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_human);

        database = FirebaseDatabase.getInstance().getReference();

        inName = (EditText)findViewById(R.id.inName);
        inPass = (EditText)findViewById(R.id.inPass);
        inAddr = (EditText)findViewById(R.id.inAddr);
        inEmail = (TextView)findViewById(R.id.inEmail);

        save = (Button)findViewById(R.id.saveBtn1);
        logoutBtn = (Button)findViewById(R.id.logoutBtn);

        name = inName.getText().toString();
        addr = inAddr.getText().toString();
        pass = inPass.getText().toString();

        email = getIntent().getStringExtra("userEmail");

            database.child("users").child(email).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    if (snapshot.getValue(User.class) != null) {
                        User userInfo = snapshot.getValue(User.class);
                        Log.w("Usera", userInfo.toString());

                        inEmail.setText(userInfo.getUserEmail());
                        inName.setText(userInfo.getUserName());
                        inPass.setText(userInfo.getUserPass());
                        inAddr.setText(userInfo.getUserAddr());
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

            save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    infoUpdate(email,pass,name,addr);

                }
            });

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                autoLogin = getSharedPreferences("autoLogin", Activity.MODE_PRIVATE);
                editor = autoLogin.edit();
                editor.clear();
                editor.commit();

                Toast.makeText(getApplicationContext(),"로그아웃",Toast.LENGTH_SHORT).show();
                onBackPressed();

            }
        });

    }

    private void infoUpdate (String userEmail, String userPass, String userName, String userAddr ){

        User update = new User(userEmail, userPass, userName, userAddr);
        database.child("user").child(email).setValue(update).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(getApplicationContext(),"정보가 수정되었습니다.",Toast.LENGTH_SHORT).show();
                inEmail.setText(email);
                inName.setText(name);
                inPass.setText(pass);
                inAddr.setText(addr);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(),"다시 시도해주세요",Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    protected void onPause() {
        super.onPause();
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
