package bintang.id.modaldialogexercise;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;


public class Passing extends Activity {
    EditText txtUsername_Passing, txtPassword_Passing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_passing);
        init();
        getExtras();
    }

    private void init(){
        txtUsername_Passing = (EditText)findViewById(R.id.txtUsername_Passing);
        txtPassword_Passing = (EditText)findViewById(R.id.txtPassword_Passing);
    }

    private void getExtras(){
        Bundle extras = getIntent().getExtras();
        String username, password;
        if(extras != null){
            username = extras.getString("username");
            password = extras.getString("password");
        }else{
            username = "-";
            password = "-";
        }

        setText(username, password);
        Toast.makeText(getBaseContext(), "SENT....", Toast.LENGTH_SHORT).show();
        extras.clear();
    }


    private void setText(String username, String password){
        txtUsername_Passing.setText(username);
        txtUsername_Passing.setEnabled(false);

        txtPassword_Passing.setText(password);
        txtPassword_Passing.setEnabled(false);
    }
}
