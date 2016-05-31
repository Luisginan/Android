package bintang.id.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import bintang.id.listviewexercise.R;

/**
 * Created by bintang on 2/16/2016.
 * Purpose : Adapter for grid view
 */
public class ImageAdapter extends BaseAdapter {

    private Context context;
    public Integer[] thumbNails = {
            R.drawable.animal_cow,
            R.drawable.animal_elephant,
            R.drawable.animal_fox,
            R.drawable.animal_giraffe,
            R.drawable.animal_lion,
            R.drawable.animal_parakeet,
            R.drawable.animal_pinguiin,
            R.drawable.animal_rabbit,
            R.drawable.animal_rhino,
            R.drawable.animal_squirrel,
            R.drawable.animal_zebra,
            R.drawable.animal_eagle,
            R.drawable.animal_bear,
            R.drawable.animal_panda,
            R.drawable.animal_frog,
    };

    public ImageAdapter(Context context){
        this.context = context;
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
        imageView.setImageResource(thumbNails[position]);
        return imageView;
    }

    @Override
    public int getCount() {
        return thumbNails.length;
    }

    @Override
    public Object getItem(int position) {
        return thumbNails[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

}
