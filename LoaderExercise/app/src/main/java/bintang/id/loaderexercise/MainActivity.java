package bintang.id.loaderexercise;


import android.app.Activity;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Intent;
import android.content.Loader;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import bintang.id.loader.SampleLoader;
import bintang.id.model.Employee;


/**
 * Created by bintang on 2/24/2016.
 *
 * Purpose : An Exercise how to Create LoaderManager
 */

public class MainActivity extends Activity implements LoaderCallbacks<Employee>{

    public static final int LOADER_ID = 1;
    private static final String TAG = "MainActivity";

    TextView result;
    Button btnPindah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);

        init();
        btnPindahAction();
        /*Init the loader so Loader class is called.
        * in this example I use LOADER_ID to call that specific ID
        *
        * Note : If you using the data type of model, it needs forceLoad() to start the loader.
        * You can call if(Loader) from here with forceLoad or inside the custom AsyncTaskLoader
        * you have created and override the onStartLoading method and of course with forceLoad too.
        */
        //getLoaderManager().initLoader(LOADER_ID, null, this).forceLoad();
    }

    private void init(){
        result      = (TextView)findViewById(R.id.tvResult);
        btnPindah   = (Button)findViewById(R.id.btnPindah);
    }

    private void btnPindahAction(){
        btnPindah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(i);
            }
        });
    }


    @Override
    public Loader<Employee> onCreateLoader(int id, Bundle args) {
        return new SampleLoader(this);
    }

    @Override
    public void onLoadFinished(Loader<Employee> loader, Employee data) {
        Log.i(TAG, "onLoadFinished");
        if(data != null){
            setToView(data);
        }
    }

    @Override
    public void onLoaderReset(Loader<Employee> loader) {
        Log.i(TAG, "onLoaderReset");
        Toast.makeText(this, "Nothing to do....", Toast.LENGTH_LONG).show();
    }

    private void setToView(Employee employee){
        int nik         = employee.getNik();
        String name     = employee.getName();
        String division = employee.getDivision();

        result.setText("NIK = "+ nik + "\nName= "+ name + "\nDivision= "+ division);
    }


}
