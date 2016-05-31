package bintang.id.activityexercise;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import java.util.EnumMap;

import bintang.id.model.Employee;

/**
 * Created by bintang on 2/24/2016.
 */
public class ResultPassObjectActivity extends Activity {

    EditText txtNIK, txtName, txtDivision;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_resultobject);

        init();
        getExtras();
    }

    private void init(){
        txtNIK      = (EditText)findViewById(R.id.txtNIK);
        txtName     = (EditText)findViewById(R.id.txtName);
        txtDivision = (EditText)findViewById(R.id.txtDivision);
    }

    private void getExtras(){
        Intent intent = getIntent();
        Employee employee = (Employee)intent.getSerializableExtra("employee");

        if(employee != null){
            fillData(employee);
        }
    }

    private void fillData(Employee employee){
        int NIK         = employee.getNik();
        String name     = employee.getName();
        String division = employee.getDivision();

        txtNIK.setText(Integer.toString(NIK));
        txtName.setText(name);
        txtDivision.setText(division);
    }


}
