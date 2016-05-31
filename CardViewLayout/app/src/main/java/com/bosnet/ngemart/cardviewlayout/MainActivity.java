package com.bosnet.ngemart.cardviewlayout;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Card view layout");

        ImageView imageView = (ImageView) findViewById(R.id.productImage);
        imageView.setImageResource(R.mipmap.ic_launcher);

        TextView txtCardTitle = (TextView) findViewById(R.id.lblName);
        txtCardTitle.setText("ANDROID");

        TextView txtCardText = (TextView) findViewById(R.id.lblPrice);
        txtCardText.setText("Hello World !!");
    }
}
