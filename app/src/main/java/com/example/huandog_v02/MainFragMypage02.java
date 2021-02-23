package com.example.huandog_v02;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class MainFragMypage02 extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.mainfrag02_mypage, container, false);
        Button myNotice = (Button) viewGroup.findViewById(R.id.myNotice);
        myNotice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent01 = new Intent(getContext(),Notice.class);
                startActivity(intent01);
            }
        });
        Button myOut = (Button)viewGroup.findViewById(R.id.myOut);

        myOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"서비스 준비중 :) ",Toast.LENGTH_LONG).show();
            }
        });

       Button myInfo = (Button)viewGroup.findViewById(R.id.myInfo);
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
                   }
               });

               join.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View view) {
                       Intent intent03 = new Intent(getContext(),Joinpage.class);
                       startActivity(intent03);
                   }
               });

               loginDialog.show();
           }
       });

        return viewGroup;

    }
}