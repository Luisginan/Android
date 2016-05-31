package com.example.luis.sampleasynctask;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ProgressDialog prgDialog;
    public static final int progress_bar_type = 0;
    private static final int progress_Spinner = 1;

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
                new DownloadFilesTask().execute("halo");

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

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case progress_bar_type:
                prgDialog = new ProgressDialog(this);
                prgDialog.setMessage("Please wait...");
                prgDialog.setIndeterminate(false);
                prgDialog.setMax(100);
                prgDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                prgDialog.setCanceledOnTouchOutside(true);
                prgDialog.setCancelable(true);
                prgDialog.show();
                return prgDialog;
            case progress_Spinner:
                prgDialog = new ProgressDialog(this);
                prgDialog.setMessage("Please wait...");
                prgDialog.setIndeterminate(false);
                prgDialog.setMax(100);

                prgDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                prgDialog.setCancelable(false);
                prgDialog.show();
                return prgDialog;
            default:
                return null;
        }
    }

    private class DownloadFilesTask extends AsyncTask<String, Integer, Long> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Shows Progress Bar Dialog and then call doInBackground method
           showDialog(progress_bar_type);
        }

        @Override
        protected Long doInBackground(String... params) {
            try {
                for (Integer i = 0; i <11;i++)
                {
                    publishProgress(i * 10);
                    Thread.sleep(1000);
                    if(isCancelled())
                    {
                        break;
                    }
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 10000l;
        }

        @Override
        protected void onProgressUpdate(Integer... progress) {
            prgDialog.setProgress(progress[0]);
        }

        @Override
        protected void onPostExecute(Long result) {
            prgDialog.dismiss();
            Toast.makeText(getApplicationContext(),"Process completed",Toast.LENGTH_LONG).show();
        }


    }
}
