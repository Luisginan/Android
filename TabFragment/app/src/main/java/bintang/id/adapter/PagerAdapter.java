package bintang.id.adapter;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;

import bintang.id.tabs.Tab1;
import bintang.id.tabs.Tab2;
import bintang.id.tabs.Tab3;

/**
 * Created by bintang on 3/10/2016.
 */
public class PagerAdapter extends FragmentStatePagerAdapter {
    int num0Tabs;


    public PagerAdapter(FragmentManager fm, int num0Tabs){
        super(fm);
        this.num0Tabs = num0Tabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0 :
                Tab1 tab1 = new Tab1();
                return tab1;
            case 1 :
                Tab2 tab2 = new Tab2();
                return tab2;
            case 2 :
                Tab3 tab3 = new Tab3();
                return  tab3;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return num0Tabs;
    }
}
