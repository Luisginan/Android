package bintang.id.loader;

import android.content.AsyncTaskLoader;
import android.content.Context;

import bintang.id.model.Employee;

/**
 * Created by bintang on 2/25/2016.
 * Purpose : This class used as a Asyncronous class to the Loader
 */
public class SampleLoader extends AsyncTaskLoader<Employee>{

    Employee employee;
    public SampleLoader(Context context) {
        super(context);
        // onContentChanged();
    }

    /*
    @Override
    public void onStartLoading(){
        super.onStartLoading();

        if (takeContentChanged()) {
            forceLoad();
        }
    }
*/
    @Override
    public Employee loadInBackground() {
        employee = new Employee();
        employee.setNik(12345);
        employee.setName("Bintang Tampan");
        employee.setDivision("Android Developer");

        try{
            Thread.sleep(5000); //biar kayak lagi delay network. :))
        }catch (Exception ex){
            ex.printStackTrace();
        }

        return employee;
    }
}
