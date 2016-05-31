package bintang.id.listviewexercise;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import bintang.id.adapter.ImageAdapter;

import static bintang.id.listviewexercise.R.layout.layout_gridview_fullimage;

/**
 * Created by bintang on 2/16/2016.
 * Purpose : Show single image from grid view
 */
public class FullImageActivity extends Activity {
    ImageView imageView;
    TextView tvTitle;
    int position;
    String[] animals = {"Sapi", "Gajah", "Rubah", "Jerapah", "Singa", "Parkit", "Pinguin","Kelinci",
            "Badak", "Tupai", "Zebra", "Elang", "Beruang", "Panda", "Katak"};
    ImageAdapter imageAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout_gridview_fullimage);

        Intent intent = getIntent();
        position = intent.getExtras().getInt("id");
        imageAdapter = new ImageAdapter(this);

        init();
        doAction();
    }

    private void init(){
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        imageView = (ImageView) findViewById(R.id.imageView);
    }

    private void doAction(){
        setText();
        setImage();
    }

    private void setText(){
        String title = animals[position];
        if (title.length() <= 0) {
            title = "Not have title";
        }
        tvTitle.setText(title);
    }

    private void setImage(){
        imageView.setImageResource(imageAdapter.thumbNails[position]);
    }

}