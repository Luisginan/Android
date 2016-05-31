package bintang.id.adapter;

/**
 * Created by bintang on 3/15/2016.
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

import bintang.id.model.Employee;
import bintang.id.swipeviewpager.FragmentViewHandler;

public class FragmentAdapter extends FragmentPagerAdapter {

    ArrayList<Employee> employees;
    int size;

    public FragmentAdapter(FragmentManager fm, ArrayList<Employee> employees, int size) {
        super(fm);
        this.employees = employees;
        this.size = size;
    }

    @Override
    public Fragment getItem(int arg0) {
        FragmentViewHandler fragmentViewHandler = new FragmentViewHandler(employees.get(arg0));

        return fragmentViewHandler;
    }

    @Override
    public int getCount() {
        return size;
    }
}
