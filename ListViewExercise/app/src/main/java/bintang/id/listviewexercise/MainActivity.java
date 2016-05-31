package bintang.id.listviewexercise;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import static bintang.id.listviewexercise.R.layout.activity_main;
import static bintang.id.listviewexercise.R.layout.support_simple_spinner_dropdown_item;


public class MainActivity extends android.app.Activity {

    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_main);

        spinner = (Spinner)findViewById(R.id.spinner);

        final List<String> options = new ArrayList<>();
        options.add("----Choose----");
        options.add("ListViewActivity");
        options.add("GridView");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, support_simple_spinner_dropdown_item, options);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position == 1){
                    startActivity(new Intent(MainActivity.this, ListViewActivity.class));
                }else if(position == 2){
                    startActivity(new Intent(MainActivity.this, GridViewActivity.class));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //nothing to do here.. :D
            }
        });
    }



}

