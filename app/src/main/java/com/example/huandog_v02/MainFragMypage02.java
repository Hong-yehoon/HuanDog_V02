package com.example.huandog_v02;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class  MainFragMypage02 extends Fragment {

    private DatabaseReference database;
    FirebaseDatabase firebaseDatabase;


    String logoutEmail;


    SharedPreferences autoLogin;
    SharedPreferences.Editor editor;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.mainfrag02_mypage, container, false);
        Button myNotice = (Button) viewGroup.findViewById(R.id.myNotice);
        ImageView myProfile = (ImageView)viewGroup.findViewById(R.id.myProfile);



        myProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(getContext(),view);
                popup.getMenuInflater().inflate(R.menu.mypage_menu,popup.getMenu());

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        if(menuItem.getTitle().equals("사진찍기")){
                          int permission = ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA);

                          if(permission == PackageManager.PERMISSION_DENIED){
                              ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.CAMERA},0);
                          }else {
                              Intent intent01 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                              startActivityForResult(intent01,1);
                          }

                        }else if(menuItem.getTitle().equals("사진가져오기")){
                            Intent intent02 = new Intent(Intent.ACTION_PICK);
                            intent02.setType(android.provider.MediaStore.Images.Media.CONTENT_TYPE);
                            startActivityForResult(intent02,2);

                        }

                        return true;
                    }
                });
                popup.show();
            }

        });

       myNotice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent01 = new Intent(getContext(),Notice.class);
                intent01.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent01);
            }
        });

        Button myOut = (Button)viewGroup.findViewById(R.id.myOut);

        myOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //쉐어드프리퍼런스에서 아이디 가져오고
                autoLogin = getActivity().getSharedPreferences("autoLogin",Context.MODE_PRIVATE);
                logoutEmail = autoLogin.getString("inputEmail","");
                Toast.makeText(getContext(),logoutEmail,Toast.LENGTH_SHORT).show();

                //다이얼로그에 아이디 띄워주구
                Dialog outDialog = new Dialog(getContext());
                outDialog.setContentView(R.layout.delete_dialog);

                TextView deEmail = (TextView)outDialog.findViewById(R.id.DeEmail);
                EditText dePass = (EditText)outDialog.findViewById(R.id.DePass);
                Button deBtn = (Button)outDialog.findViewById(R.id.DeBtn);

                deEmail.setText(logoutEmail);
                firebaseDatabase = FirebaseDatabase.getInstance();


                //버튼 눌렀을 때 비번 맞으면 탈퇴쓰
                deBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if(dePass.length() != 0){
                            database = firebaseDatabase.getReference();
                            database.child("users").child(logoutEmail).addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    User delete;
                                    delete = snapshot.getValue(User.class);
                                    if(snapshot.getValue(User.class) != null){
                                        if(delete.getUserPass().equals(dePass.getText().toString())){

                                            database.child("users").child(logoutEmail).removeValue();

                                            //로그인유지 해제
                                            editor = autoLogin.edit();
                                            editor.clear();
                                            editor.commit();


                                            outDialog.dismiss();
                                        }else{
                                            Toast.makeText(getContext(),"비밀번호가 맞지않습니다.",Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });

                        }else{
                            Toast.makeText(getContext(),"비밀번호를 입력해주세요", Toast.LENGTH_SHORT).show();
                        }

                    }
                });

                outDialog.show();

            }
        });

       Button myInfo;
        myInfo = (Button)viewGroup.findViewById(R.id.myInfo);
        myInfo.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Dialog loginDialog = new Dialog(getContext());
               loginDialog.setContentView(R.layout.info_dialog);
               loginDialog.setTitle("환영합니당");

               Button login = (Button)loginDialog.findViewById(R.id.dialLogin);
               Button join = (Button)loginDialog.findViewById(R.id.dialJoin);

               login.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View view) {
                       Intent intent02 = new Intent(getContext(),Loginpage.class);
                       startActivity(intent02);
                       loginDialog.dismiss();
                   }
               });

               join.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View view) {
                       Intent intent03 = new Intent(getContext(),Joinpage.class);
                       startActivity(intent03);
                       loginDialog.dismiss();
                   }
               });

               loginDialog.show();
           }
       });

        return viewGroup;

    }


}