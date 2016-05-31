package bintang.id.modaldialogexercise;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener{

    //Declare all object to be use
    Button btnCommonDialog, btnToast, btnDialogText, btnCustom, btnSnackBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init(){
        btnCommonDialog = (Button)findViewById(R.id.btnCommonDialog);
        btnCommonDialog.setOnClickListener(this);
        btnToast        = (Button)findViewById(R.id.btnToast);
        btnToast.setOnClickListener(this);
        btnDialogText   = (Button)findViewById(R.id.btnDialogArray);
        btnDialogText.setOnClickListener(this);
        btnCustom       = (Button)findViewById(R.id.btnCustom);
        btnCustom.setOnClickListener(this);
        btnSnackBar     = (Button)findViewById(R.id.btnSnackBar);
        btnSnackBar.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnCommonDialog:
                showCommonDialog();
                break;
            case R.id.btnDialogArray:
                showArrayDialog();
                break;
            case R.id.btnToast:
                Toast.makeText(getBaseContext(), "Ping... This is Button Toast", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnCustom:
                showCustomDialog();
                break;
            case R.id.btnSnackBar:
                final CoordinatorLayout showCoordinatorLayout = (CoordinatorLayout)findViewById(R.id.coordinatorLayout);
                Snackbar.make(showCoordinatorLayout, "This is sample of SnackBar... ", Snackbar.LENGTH_SHORT).show();
                break;

            default:

        }
    }

    private void showCommonDialog(){
        final AlertDialog.Builder commonDialogBuilder = new AlertDialog.Builder(MainActivity.this);
        commonDialogBuilder.setCancelable(false)
        .setTitle("Common Alert Dialog")
        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Toast.makeText(getBaseContext(), "You have pressed Button OK on Common Alert Dialog Box!", Toast.LENGTH_SHORT).show();
            }
        })
        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Toast.makeText(getBaseContext(), "You have pressed Button Cancel on Common Alert Dialog Box!", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        AlertDialog dialog = commonDialogBuilder.create();
        dialog.show();
    }
    private void showArrayDialog(){
        final String[] planet = getResources().getStringArray(R.array.Planets);

        final AlertDialog.Builder arrayDialogBuilder = new AlertDialog.Builder(MainActivity.this);
        arrayDialogBuilder.setCancelable(true);
        arrayDialogBuilder.setTitle("Common Alert Dialog");
        arrayDialogBuilder.setItems(planet, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getBaseContext(), planet[which], Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog dialog = arrayDialogBuilder.create();
        dialog.show();
    }


    private void showCustomDialog(){
        final LayoutInflater inflater = MainActivity.this.getLayoutInflater();
        View view = inflater.inflate(R.layout.custom_alertdialog1, null);

        final EditText txtUsername = (EditText)view.findViewById(R.id.txtUsername);
        final EditText txtPassword = (EditText)view.findViewById(R.id.txtPassword);
        AlertDialog.Builder customDialogBuilder = new AlertDialog.Builder(MainActivity.this);
        customDialogBuilder.setView(view)
                .setCancelable(false)
                .setPositiveButton("Send", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        String username, password;

                        username = txtUsername.getText().toString();
                        password = txtPassword.getText().toString();

                        sendData(username, password);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        AlertDialog dialog = customDialogBuilder.create();
        dialog.show();
    }

    private void sendData(String username, String password){
        Intent intent = new Intent(this, Passing.class);
        intent.putExtra("username", username);
        intent.putExtra("password", password);
        startActivity(intent);
    }

}
