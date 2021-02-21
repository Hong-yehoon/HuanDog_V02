package com.example.huandog_v02;

import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class MainFragHome01 extends Fragment {

     TabLayout tablayout;
    ViewPager viewPager;
    FragmentAdapter fragmentadapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.home_activity, container, false);

        tablayout = (TabLayout)viewGroup.findViewById(R.id.homeTab);
        tablayout.addTab(tablayout.newTab().setText("State"));
        tablayout.addTab(tablayout.newTab().setText("timeline"));
        tablayout.setTabGravity(TabLayout.GRAVITY_FILL);

        //initializeing ViewPager
        viewPager = (ViewPager)viewGroup.findViewById(R.id.homeviewPager);

        //이거 몬지 하나드 모르겟다
       //fragmentadapter = new FragmentAdapter(getActivity().getSupportFragmentManager(),tablayout.getTabCount());
        fragmentadapter = new FragmentAdapter(getFragmentManager(),tablayout.getTabCount());
        viewPager.setAdapter(fragmentadapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tablayout));



        //리스너ㅓㅓㅓㅓㅓ

        tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        return viewGroup;
    }
}