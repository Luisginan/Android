package bintang.id.swipeviewpager;
/**
 * Created by bintang on 3/15/2016.
 * Purpose : First Activity to hold the fragment
 */
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.os.Bundle;

import java.util.ArrayList;

import bintang.id.adapter.FragmentAdapter;
import bintang.id.model.Employee;

public class MainActivity extends FragmentActivity {
    ArrayList<Employee> employees = new ArrayList<Employee>();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);

        fillData();
        ViewPager pager = (ViewPager) findViewById(R.id.pager);
        FragmentManager fm = getSupportFragmentManager();
        FragmentAdapter pagerAdapter = new FragmentAdapter(fm, employees, employees.size() );
        pager.setAdapter(pagerAdapter);
    }

    private void fillData(){
        employees.add(new Employee(1, "Luis Ginanjar", "Lead Developer"));
        employees.add(new Employee(2, "Bintang Parulian", "Android Developer"));
        employees.add(new Employee(3, "Jati", "Android Developer"));
        employees.add(new Employee(4, "Ibnu Deli", "Android Developer"));
    }

}