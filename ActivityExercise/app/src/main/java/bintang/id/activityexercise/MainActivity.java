package bintang.id.activityexercise;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import bintang.id.model.Employee;


public class MainActivity extends Activity{
    String msg = "MainActivity : ";

    Spinner spinner;
    Button btnObjectModel, btnSendValue;
    TextView tvNumberOne, tvNumberTwo, tvNote;
    EditText txtFirstValue, txtSecondValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);
        Log.d(msg, "The onCreate() event");

        init();
        fillSpinnerValue();
        clearControl();
        spinnerAction();
        sendValueButtonAction();
        sendObjectModelAction();
    }

    private void sendValueButtonAction(){
        btnSendValue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((txtFirstValue.getText().length() == 0) && (txtSecondValue.getText().length() == 0)) {
                    Toast.makeText(MainActivity.this, "You must fill all data !", Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        retrieveValue();
                    } catch (Exception Ex) {
                        Ex.printStackTrace();
                    }
                }
            }
        });
    }

    private void sendObjectModelAction(){
        btnObjectModel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Employee employee = new Employee();
                employee.setNik(12345);
                employee.setName("Binz Cool");
                employee.setDivision("Developer");

                sendObjectModel(employee);
            }
        });
    }

    private void sendObjectModel(Employee employee){
        if(employee != null){
            Intent intent = new Intent(this, ResultPassObjectActivity.class);
            intent.putExtra("employee", employee);
            startActivity(intent);
        }
    }

    private void retrieveValue(){
        String firstValue = "";
        String secondValue = "";
        firstValue = txtFirstValue.getText().toString();
        secondValue = txtSecondValue.getText().toString();

        Intent intent = new Intent(this, ResultPassValueActivity.class);
        intent.putExtra("firstValue",firstValue );
        intent.putExtra("secondValue", secondValue);

        startActivity(intent);
    }


    private void spinnerAction(){
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    return;
                } else if (position == 1) {
                    setVisibilityForOption1();
                } else if (position == 2) {
                    setVisibilityForOption2();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        }) ;
    }

    private void setVisibilityForOption1(){
        tvNumberOne.setVisibility(View.VISIBLE);
        tvNumberTwo.setVisibility(View.VISIBLE);
        txtFirstValue.setVisibility(View.VISIBLE);
        txtSecondValue.setVisibility(View.VISIBLE);
        btnSendValue.setVisibility(View.VISIBLE);

        btnObjectModel.setVisibility(View.GONE);
        tvNote.setVisibility(View.GONE);
    }


    private  void setVisibilityForOption2(){
        btnObjectModel.setVisibility(View.VISIBLE);
        tvNote.setVisibility(View.VISIBLE);
        tvNote.setText(R.string.note);

        btnSendValue.setVisibility(View.GONE);
        tvNumberOne.setVisibility(View.GONE);
        tvNumberTwo.setVisibility(View.GONE);
        txtFirstValue.setVisibility(View.GONE);
        txtSecondValue.setVisibility(View.GONE);
    }

    private void init(){
        spinner             = (Spinner)findViewById(R.id.spinner);

        btnObjectModel      = (Button)findViewById(R.id.btnObjectModel);
        btnSendValue        = (Button)findViewById(R.id.btnSendValue);

        tvNumberOne         = (TextView)findViewById(R.id.tvNumberOne);
        tvNumberTwo         = (TextView)findViewById(R.id.tvNumberTwo);
        tvNote              = (TextView)findViewById(R.id.tvNote);

        txtFirstValue       = (EditText)findViewById(R.id.txtFirstValue);
        txtSecondValue      = (EditText)findViewById(R.id.txtSecondValue);
    }

    private void fillSpinnerValue(){
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.options, android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    @Override
    protected void onStart(){
        super.onStart();
        Log.d(msg, "The onStart() event");
    }

    @Override
    protected void onStop(){
        super.onStop();
        Log.d(msg, "The onStop() event");
    }

    @Override
    protected void onPause(){
        super.onPause();
        Log.d(msg, "The onPause() event");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.d(msg, "The onDestroy() event");
    }

    private void clearControl(){
        txtFirstValue.setText("");
        txtSecondValue.setText("");
    }

}
