package bintang.id.uiexercise;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.TextView;


public class MainActivity extends Activity implements OnItemSelectedListener{
    Spinner spinner;
    EditText txtSpinnerValue, txtNamaPlanet, txtDiameter, txtDistance;
    TextView tvNamaPlanet, tvDiameter, tvDistance;
    ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init(){
        txtSpinnerValue = (EditText)findViewById(R.id.txtSpinnerValue);
        txtNamaPlanet = (EditText)findViewById(R.id.txtNama);
        txtDiameter = (EditText)findViewById(R.id.txtDiameter);
        txtDistance = (EditText)findViewById(R.id.txtDistance);
        imageView = (ImageView)findViewById(R.id.imageView);

        spinner = (Spinner)findViewById(R.id.spinnerPlanets);
        spinner.setOnItemSelectedListener(this);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.planets, R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(position == 0){
            txtSpinnerValue.setText("Harus dipilih");
        }else{
            String item = parent.getItemAtPosition(position).toString();
            txtSpinnerValue.setText(item);
            setText(position);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    private void setText(int position){
        switch (position) {
            case 1:
                txtNamaPlanet.setText("Merkurius");
                txtDiameter.setText("13,040,131 m");
                txtDistance.setText("34,040,131 Km");
                imageView.setImageResource(R.drawable.home);
                break;
            case 2:
                txtNamaPlanet.setText("Venus");
                txtDiameter.setText("23,066,999 m");
                txtDistance.setText("54,040,131 Km");
                imageView.setImageResource(R.drawable.home);
                break;
            case 3:
                txtNamaPlanet.setText("Earth");
                txtDiameter.setText("73,676,444 m");
                txtDistance.setText("154,040,131 Km");
                imageView.setImageResource(R.drawable.home);
                break;
            default:
        }
    }


}
