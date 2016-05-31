package bintang.id.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;

import bintang.id.model.Animal;


/**
 * Created by bintang on 2/16/2016.
 * Purpose : Adapter for grid view
 */
public class ImageAdapter extends BaseAdapter {
    ArrayList<Animal> animals;
    private Context context;

    public ImageAdapter(Context context, ArrayList<Animal> animals ){
        this.context = context;
        this.animals = animals;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null){
            imageView = new ImageView(context);
            imageView.setLayoutParams(new GridView.LayoutParams(350, 350));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);

        }else{
            imageView = (ImageView) convertView;
        }
        if(position < animals.size()){
            imageView.setImageResource(animals.get(position).getImageNumber());
        }

        return imageView;
    }

    @Override
    public int getCount() {
        return animals.size();
    }

    @Override
    public Object getItem(int position) {
        return animals.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

}
