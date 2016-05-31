package bintang.id.activityexercise;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;

/**
 * Created by bintang on 2/24/2016.
 */


public class ResultPassValueActivity  extends Activity{
    EditText txtFirstValue, txtSecondValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_resultpassvalue);

        init();
        fillValue();
    }

    private void init(){
        txtFirstValue = (EditText)findViewById(R.id.txtFirstValue);
        txtSecondValue = (EditText)findViewById(R.id.txtSecondValue);
    }

    private void fillValue(){
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            String first = extras.getString("firstValue");
            String second = extras.getString("secondValue");

            txtFirstValue.setText(first);
            txtSecondValue.setText(second);
            extras.clear();
        }
    }
}
