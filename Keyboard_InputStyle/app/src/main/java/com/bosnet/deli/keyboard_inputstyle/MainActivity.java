package com.bosnet.deli.keyboard_inputstyle;

import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

public class MainActivity extends AppCompatActivity implements View.OnFocusChangeListener {
    EditText email, nama, hp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nama = (EditText) findViewById(R.id.editNama );
        hp = (EditText) findViewById(R.id.editHP);
        email = (EditText) findViewById(R.id.editEmail);


        setListener(nama);
        setListener(hp) ;
        setListener(email) ;



        email.setOnEditorActionListener(new OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == R.id.LastIME || actionId == EditorInfo.IME_NULL) {

                    proses(v);
                    return true;
                }
                return false;
            }

        });

    }

    public void proses(View view) {
      if (nama.getText().toString().trim().equalsIgnoreCase("") || hp.getText().toString().trim().equalsIgnoreCase("") || email.getText().toString().trim().equalsIgnoreCase("") ) {
          new AlertDialog.Builder(MainActivity.this).setTitle("Info").setMessage("Input Belum Lengkap").setNeutralButton("Close", null).show();
          nama.requestFocus();
      }
        else
      {
          nama.requestFocus();
          nama.setError(null);
          hp.setError(null);
          email.setError(null);
          nama.setText("");
          hp.setText("");
          email.setText("");


          new AlertDialog.Builder(MainActivity.this).setTitle("Info").setMessage("Data anda telah tersimpan").setNeutralButton("Close", null).show();
      }
    }

    public void setListener(final EditText edittext)
    {
        edittext.setOnFocusChangeListener(this);
    }


    @Override
    public void onFocusChange(View sender, boolean hasFocus) {
        EditText editText = (EditText) sender;

        if (hasFocus == false) {
            editText.setError(null);
            if (editText.getText().toString().trim().equalsIgnoreCase("")) {
                editText.setError("Kolom ini tidak boleh kosong");

            }
        }
    }
}