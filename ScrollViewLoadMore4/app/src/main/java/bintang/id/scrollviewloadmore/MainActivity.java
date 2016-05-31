package bintang.id.scrollviewloadmore;



/**
 * Created by bintang on 3/22/2016.
 * Purpose : Init all property from view and set the list view to handle load the data
 * when user scroll up since the list view has reach the bottom of the list.
 */

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends Activity {

    ListView listView;
    Button btnClear;
    String[] arrayS = new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o"};
    final ArrayList<String> list = new ArrayList<>();
    int counter = 1;
    View footer;
    ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);

        initComponentandLoadtheList();
    }

    private void initComponentandLoadtheList(){
        init();
        fillArrayList();
        fillListView();
        setOnScrollListener();
        setOnClickListener();
    }

    //region setOnScrollListener
    private void setOnScrollListener(){
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {

                int threshold = 1;
                final int count = listView.getCount();
                Log.i("Count", "" + count);
                final int currentPosition = listView.getFirstVisiblePosition();
                Log.i("currentPosition", "" + currentPosition);
                Log.i("getLastVisiblePosition", "" + listView.getLastVisiblePosition());
                //create fake loading
                if (listView.getLastVisiblePosition() >= count - threshold) {
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            for (int i = 0; i < arrayS.length; i++) {
                                list.add(arrayS[i] + counter);
                            }
                            adapter.notifyDataSetChanged();
                            listView.setAdapter(adapter);
                            listView.setSelectionFromTop(currentPosition + 2, 0);

                            counter++;
                        }
                    }, 3000);
                    setToastMessage("List view is refreshing...");
                }
            }

            @Override
            public void onScroll(AbsListView absListView, final int firstVisibleItem, int visibleItemCount, final int totalItemCount) {
//region Commnet line yang belom terpakai

/*
                int AUTOLOAD_THRESHOLD = 4;
                int total = visibleItemCount + firstVisibleItem;
                final int currentPosition = listView.getFirstVisiblePosition();

                if(totalItemCount - AUTOLOAD_THRESHOLD <= firstVisibleItem + visibleItemCount){
                // Execute some code after 15 seconds have passed
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            public void run() {
                                for (int i = 0; i < arrayS.length; i++) {
                                    list.add(arrayS[i]+counter);
                                }
                                adapter.notifyDataSetChanged();
                                listView.setAdapter(adapter);
                                listView.setSelectionFromTop(currentPosition + 1, 0);
                            }
                        }, 3000);
                    counter++;
                    }

*/
//endregion
            }
        });
    }
//endregion

    //region setOnClickListener
    private void setOnClickListener(){
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (list != null) {
                    list.clear();
                    counter = 1;
                    listView.removeFooterView(footer);
                    initComponentandLoadtheList();
                    setToastMessage("List has deleted...");
                }
            }
        });
    }
//endregion

    private void init(){
        listView = (ListView) findViewById(R.id.load);
        btnClear = (Button)findViewById(R.id.btnClear);
    }

    private void fillArrayList(){
        for (int i = 0; i < arrayS.length; i++) {
            list.add(arrayS[i]);
        }
    }

    private void fillListView(){
        adapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, list);
        footer = ((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.progress, null, false);

        listView.addFooterView(footer);
        listView.setAdapter(adapter);
    }

    private void setToastMessage(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }


}
