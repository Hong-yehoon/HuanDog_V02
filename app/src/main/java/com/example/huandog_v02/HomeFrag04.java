package com.example.huandog_v02;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class HomeFrag04 extends Fragment {

    ArrayList<Dogs> dogs;
    ListView list;


    private static CustomAdapter01 customAdapter01;

    ViewGroup viewGroup;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewGroup = (ViewGroup) inflater.inflate(R.layout.home_timeline, container, false);

        dogs = new ArrayList<>();
        dogs.add(new Dogs("재돌",getResources().getDrawable(R.drawable.dog01),"09 분"));
        dogs.add(new Dogs("핑구",getResources().getDrawable(R.drawable.dog02),"13 분"));
        dogs.add(new Dogs("철훈",getResources().getDrawable(R.drawable.dog03),"22 분"));
        dogs.add(new Dogs("우디",getResources().getDrawable(R.drawable.dog04),"37 분"));
        dogs.add(new Dogs("밤이",getResources().getDrawable(R.drawable.dog05),"45 분"));

        list = (ListView)viewGroup.findViewById(R.id.htList);
        customAdapter01 = new CustomAdapter01(getContext(),dogs);
        list.setAdapter(customAdapter01);

        return viewGroup;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

}
class Dogs {
    private String name;
    private Drawable image;
    private String time;

    public Dogs (String name, Drawable image, String time){
        this.name = name;
        this.image = image;
        this.time = time;


    }
    public String getName(){
        return name;
    }

    public Drawable getImage() {
        return image;
    }

    public String getTime() {
        return time;
    }
}

/*

class list extends AppCompatActivity{

    Button hbtn = (Button)findViewById(R.id.htHeart);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_timeline_listview);

    }

    public void hClick(){

        hbtn.setBackgroundResource(R.drawable.heart_red);
    }
}
*/
