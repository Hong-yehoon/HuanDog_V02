package com.example.huandog_v02;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class FragmentAdapter extends FragmentStatePagerAdapter {

    private int tabCount;

    public FragmentAdapter(FragmentManager fm, int tabCount){
        super(fm);
        this.tabCount = tabCount;
    }


    @Override
    public Fragment getItem(int position) {

        switch(position){
            case 0:
                HomeFrag03 fragment03 = new HomeFrag03();
                return fragment03;
            case 1:
                HomeFrag04 fragment04 = new HomeFrag04();
                return fragment04;
        }
        return getItem(position);
    }

    @Override
    public int getItemPosition( Object object) {
        return POSITION_NONE;
    }




    @Override
    public int getCount() {
        return tabCount;
    }



}
