package bintang.id.androidpermissions;

/**
 * Created by Bintang on 3/3/2016.
 * Purpose :
 * Because of since android API 23 and above has changed their permission method,
 * that permission for dangerous permission not only granted only when user goint to install
 * the application, but also when run-time. Thus, we must add a few line code to handle it.
 */

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnContact;
    private static final int REQUEST_CONTACT = 1;
    private static String[] PERMISSIONS_CONTACT = {Manifest.permission.READ_CONTACTS, Manifest.permission.WRITE_CONTACTS};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);

        initComponent();
    }

    private void initComponent(){
        btnContact = (Button)findViewById(R.id.btnContact);
        btnContact.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnContact:

                /*checkSelfPermission is used to check previously whether has been granted or not
                if the permission has been granted, it never asked again,
                but if it has not been granted yet, the OS will ask user whether allow or deny.
                */
                if(ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED
                        || ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_CONTACTS) != PackageManager.PERMISSION_GRANTED){
                    requestContactsPermissions();
                }else{
                    messageDialog(getResources().getString(R.string.PERMISSION_ALREADY_GRANTED));
                }
                break;
        }
    }

    private void requestContactsPermissions(){
        if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_CONTACTS)
                || ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_CONTACTS)){
            ActivityCompat.requestPermissions(this, PERMISSIONS_CONTACT, REQUEST_CONTACT);
        }else{
            ActivityCompat.requestPermissions(this, PERMISSIONS_CONTACT, REQUEST_CONTACT);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,String permissions[], int[] grantResults) {
        if (requestCode == REQUEST_CONTACT) {
                for( int i = 0; i < permissions.length; i++ ) {
                    if( grantResults[i] == PackageManager.PERMISSION_GRANTED ) {
                        messageDialog(getResources().getString(R.string.PERMISSION_GRANTED));
                    } else if( grantResults[i] == PackageManager.PERMISSION_DENIED ) {
                        messageDialog(getResources().getString(R.string.PERMISSION_DENIED));
                    }
                }

        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }

    }

    private void messageDialog(String message){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this)
                .setTitle(R.string.PERMISSION_INFORMATION)
                .setMessage(message)
                .setPositiveButton(R.string.PERMISSION_OK, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        AlertDialog alert = alertDialog.create();
        alert.show();
    }


}
