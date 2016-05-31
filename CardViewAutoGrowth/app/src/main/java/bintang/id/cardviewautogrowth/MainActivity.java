package bintang.id.cardviewautogrowth;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import bintang.id.adapter.CardViewAdapter;
import bintang.id.model.Product;

public class MainActivity extends AppCompatActivity {
    SwipeRefreshLayout swipeRefreshLayout;

    private RecyclerView recycler;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter recyclerAdapter;

    ArrayList<Product> products = new ArrayList<Product>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);

        fillDataProduct();
        init();
        setItemClickListener();
        setLongItemClickListener();
    }

    //set the view to handle setItemClickListener
    private void setItemClickListener(){
        ((CardViewAdapter) recyclerAdapter).setOnItemClickListener(new CardViewAdapter.IClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Log.i("MainActivity", " Clicked on Item " + position);
                makeToast("Item click " + products.get(position).getProductName());
            }
        });
    }

    //set the view to handle onItemLongClick
    private void setLongItemClickListener(){
        ((CardViewAdapter) recyclerAdapter).setOnItemLongClickListener(new CardViewAdapter.ILongClickListener() {
            @Override
            public void onLongClick(int position, View v) {
                Log.i("MainActivity", " Long clicked on Item " + position);
                makeToast("On Long Item Click: " + products.get(position).getProductName());
            }
        });
    }

    private void makeToast(String text){
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
    }

    //Register/initialize all component property
    private void init(){
        recycler = (RecyclerView)findViewById(R.id.recycler);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(layoutManager);

        recyclerAdapter = new CardViewAdapter(this, products);
        recycler.setAdapter(recyclerAdapter);


        swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipe_container);
        refreshAction();
    }

    private void refreshAction(){
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                products.clear();
                fillDataProduct();
                init();
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        swipeRefreshLayout.setColorSchemeResources(
                android.R.color.holo_orange_light,
                android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_red_light);
    }

    //manually hardcoded insert data to model.
    private void fillDataProduct(){
        products.add(new Product(R.drawable.memory_ram, "Ram Memory DDR3", "$ 32"));
        products.add(new Product(R.drawable.motherboard, "Asus Motherboard", "$ 112"));
        products.add(new Product(R.drawable.harddrive, "Hard disk 500Gb", "$ 52"));
        products.add(new Product(R.drawable.processor_i5, "Processor Intel CoreI5", "$ 187"));
        products.add(new Product(R.drawable.monitor, "Monitor Led 20", "$ 100"));
        products.add(new Product(R.drawable.combo_delux, "Combo case Deluxe", "$ 52"));
        products.add(new Product(R.drawable.external_harddrive, "WD Passport External Hard drive", "$ 102"));
        products.add(new Product(R.drawable.gaming_keyboard, "Levtron Gaming Keyboard", "$ 90"));
        products.add(new Product(R.drawable.gaming_mouse, "Logitech Gaming Mouse", "$ 45"));
        products.add(new Product(R.drawable.speaker, "Simbbada Speaker", "$ 45"));
        products.add(new Product(R.drawable.usb_8gb, "Sandisk Universal SeriaL Bus", "$ 27"));}

}
