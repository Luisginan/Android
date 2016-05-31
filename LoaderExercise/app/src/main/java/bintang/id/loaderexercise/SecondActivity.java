package bintang.id.loaderexercise;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.Loader;
import android.os.Bundle;
import android.widget.TextView;

import bintang.id.loader.SampleLoader;
import bintang.id.model.Employee;

/**
 * Created by bintang on 2/25/2016.
 * purpose : This class use to receive the value from Loader from
 * the sampe ID as the MainActivity has.
 */
public class SecondActivity extends Activity implements LoaderManager.LoaderCallbacks<Employee>{

    TextView result;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_second);

        int id = MainActivity.LOADER_ID;
        init();
        getLoaderManager().initLoader(MainActivity.LOADER_ID, null, this).forceLoad();
    }

    private void init(){
        result = (TextView)findViewById(R.id.tvResult2);
    }

    private void setToView(Employee employee){
        int nik         = employee.getNik();
        String name     = employee.getName();
        String division = employee.getDivision();

        result.setText("NIK = "+ nik + "\nName= "+ name + "\nDivision= "+ division);
    }

    @Override
    public Loader<Employee> onCreateLoader(int id, Bundle args) {
        return new SampleLoader(this);
    }

    @Override
    public void onLoadFinished(Loader<Employee> loader, Employee data) {
        if(data != null){
            setToView(data);
        }
    }

    @Override
    public void onLoaderReset(Loader<Employee> loader) {
        //Nothing to do here
    }


}
