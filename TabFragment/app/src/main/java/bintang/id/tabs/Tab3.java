package bintang.id.tabs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import bintang.id.adapter.ListViewAdapter;
import bintang.id.model.Product;
import bintang.id.tabfragment.R;

/**
 * Created by bintang on 3/10/2016.
 */
public class Tab3 extends Fragment {

    ArrayList<Product> products = new ArrayList<>();
    ListViewAdapter adapter;
    ListView listView1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedinstanceState){
        View view = inflater.inflate(R.layout.layout_tab3, container, false);

        init(view);
        fillData();

        return view;
    }

    private void init(View view){
        adapter = new ListViewAdapter(getActivity(), products);
        listView1 = (ListView)view.findViewById(R.id.listView1);
        listView1.setAdapter(adapter);
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