package com.example.luisginanjar.windowmanager;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WindowManager.LayoutParams wlp = new WindowManager.LayoutParams();
                wlp.gravity = Gravity.CENTER;
                wlp.height = 400;
                wlp.width = 400;
                wlp.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL;
                wlp.format = PixelFormat.RGBA_8888;

                WindowManager.LayoutParams wlpB = new WindowManager.LayoutParams();
                wlpB.gravity = Gravity.CENTER;
                wlpB.height =WindowManager.LayoutParams.MATCH_PARENT;
                wlpB.width = WindowManager.LayoutParams.MATCH_PARENT;
                wlpB.alpha = 0.8f;
                wlpB.flags = WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON | WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN;
                wlpB.format = PixelFormat.RGBA_8888;


                LayoutInflater inflater = (LayoutInflater)getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final View vCustomDialog =  inflater.inflate(R.layout.popup,null);
                final View vBackground = new View(getBaseContext());
                vBackground.setBackgroundColor(Color.BLACK);

                final WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);
                wm.addView(vBackground,wlpB);
                wm.addView(vCustomDialog, wlp);


                Button btnClose = (Button) vCustomDialog.findViewById(R.id.button);
                vBackground.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View sender) {
                        wm.removeView(vBackground);
                        wm.removeView(vCustomDialog);
                    }
                });
                btnClose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View sender) {
                        wm.removeView(vBackground);
                        wm.removeView(vCustomDialog);

                    }
                });
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
