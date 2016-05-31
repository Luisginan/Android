package bintang.id.tabs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import java.util.ArrayList;

import bintang.id.adapter.CardViewAdapter;
import bintang.id.model.Product;
import bintang.id.tabfragment.R;

/**
 * Created by bintang on 3/10/2016.
 */
public class Tab1 extends Fragment implements AdapterView.OnItemClickListener{
    ArrayList<Product> products = new ArrayList<>();
    private RecyclerView recycler;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter recyclerAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view =  inflater.inflate(R.layout.layout_tab1, container, false);

        init(view);
        onItemClick();
        onLongItemClickListener();

        return view;
    }

    private void init(View view){
        recycler = (RecyclerView) view.findViewById(R.id.recycler);layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(layoutManager);

        recyclerAdapter = new CardViewAdapter(products);
        recycler.setAdapter(recyclerAdapter);
    }

    //set the view to handle onItemClick
    private void onItemClick() {
        ((CardViewAdapter)recyclerAdapter).setOnItemClickListener(new CardViewAdapter.IClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Log.i("MainActivity", " Clicked on Item " + position);
                makeToast("Item click " + products.get(position).getProductName());
            }
        });
    }

    //set the view to handle onItemLongClick
    private void onLongItemClickListener() {
        ((CardViewAdapter) recyclerAdapter).setOnItemLongClickListener(new CardViewAdapter.ILongClickListener() {
            @Override
            public void onLongClick(int position, View v) {
                Log.i("MainActivity", " Long clicked on Item " + position);
                makeToast("On Long Item Click: " + products.get(position).getProductName());
            }
        });
    }

    private void makeToast(String text) {
        Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceStated){
        super.onActivityCreated(savedInstanceStated);
        fillData();

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }



    private void fillData() {
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
        products.add(new Product(R.drawable.usb_8gb, "Sandisk Universal SeriaL Bus", "$ 27"));
    }
}
