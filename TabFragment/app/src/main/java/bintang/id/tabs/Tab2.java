package bintang.id.tabs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

import bintang.id.adapter.ImageAdapter;
import bintang.id.model.Animal;
import bintang.id.tabfragment.R;

/**
 * Created by bintang on 3/10/2016.
 */
public class Tab2 extends Fragment {
    GridView gridView;
    ArrayList<Animal> animals = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedinstanceState){
        View view   =  inflater.inflate(R.layout.layout_tab2, container, false);

        fillData();
        gridView    = (GridView)view.findViewById(R.id.gridview);
        gridView.setAdapter(new ImageAdapter(getActivity(), animals));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), animals.get(position).getAnimalName(), Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    private void fillData() {
        animals.add(new Animal(R.drawable.animal_cow, "Sapi"));
        animals.add(new Animal(R.drawable.animal_lion, "Singa"));
        animals.add(new Animal(R.drawable.animal_parakeet, "Parkit"));
        animals.add(new Animal(R.drawable.animal_pinguiin, "Pinguin"));
        animals.add(new Animal(R.drawable.animal_rabbit, "Kelinci"));
        animals.add(new Animal(R.drawable.animal_rhino, "Badak"));
        animals.add(new Animal(R.drawable.animal_squirrel, "Musang"));
        animals.add(new Animal(R.drawable.animal_zebra, "Zebra"));
        animals.add(new Animal(R.drawable.animal_eagle, "Elang"));
        animals.add(new Animal(R.drawable.animal_bear, "Beruang"));
        animals.add(new Animal(R.drawable.animal_panda, "Panda"));
        animals.add(new Animal(R.drawable.animal_frog, "Kodok"));
        animals.add(new Animal(R.drawable.animal_elephant, "Gajah"));
        animals.add(new Animal(R.drawable.animal_fox, "Serigala"));
    }

}
