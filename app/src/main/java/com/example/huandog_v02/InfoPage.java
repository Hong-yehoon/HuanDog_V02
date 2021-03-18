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
import android.widget.ImageButton;
import android.widget.RelativeLayout;
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

import java.util.HashMap;
import java.util.Map;

public class InfoPage extends AppCompatActivity {

    ImageButton back;
    Button human, dog, save;
    RelativeLayout hulayout, doglayout;

    DatabaseOpenHelper dbhelper01, dbhelper02;

    SQLiteDatabase sqlDB;

    String name, addr;
    Cursor cur;

    EditText inName, inPhone, inAddr,  inDname, inSort, inAge, inNum;

    TextView inEmail;

    boolean loginSession;
    String email;

    private DatabaseReference database;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_human);

        //dbhelper01 = new DatabaseOpenHelper(InfoPage.this, DatabaseOpenHelper.table01,null,1);
        //dbhelper02 = new DatabaseOpenHelper(InfoPage.this, DatabaseOpenHelper.table02,null,1);

        database = FirebaseDatabase.getInstance().getReference();


        inName = (EditText)findViewById(R.id.inName);
        inPhone = (EditText)findViewById(R.id.inPhone);
        inAddr = (EditText)findViewById(R.id.inAddr);
        inDname = (EditText)findViewById(R.id.inDname);
        inSort = (EditText)findViewById(R.id.inSort);
        inAge = (EditText)findViewById(R.id.inAge);
        inEmail = (TextView)findViewById(R.id.inEmail);

        save = (Button)findViewById(R.id.saveBtn1);

        name = inName.getText().toString();
        addr = inAddr.getText().toString();

        loginSession = getIntent().getBooleanExtra("LoginS",true);
        email = getIntent().getStringExtra("userEmail");


        if(loginSession==true)
            database.child("users").child(email).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    if (snapshot.getValue(User.class) != null) {
                        User userInfo = snapshot.getValue(User.class);
                        Log.w("Usera", userInfo.toString());

                        inEmail.setText(userInfo.getUserEmail());
                        inName.setText(userInfo.getUserName());

                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

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

        if(loginSession==true){
            save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    infoUpdate(email,name,addr);
                }
            });
        }

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

    private void infoUpdate (String userEmail, String userName, String userAddr ){
        String key = database.child("users").push().getKey();
        User update = new User(userEmail,userName,userAddr);
        Map<String, Object> userValues = update.getInfo();


    }
}
