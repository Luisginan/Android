package bintang.id.contentproviderexample_2;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.widget.EditText;
import android.net.Uri;
import android.widget.Toast;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void onClickAddName(View view){//Add a new student record
        ContentValues cv =  new ContentValues();
        cv.put(EmployeeProvider.NAME, ((EditText)findViewById(R.id.editText2)).getText().toString());
        cv.put(EmployeeProvider.GRADE, ((EditText)findViewById(R.id.editText3)).getText().toString());

        Uri uri = getContentResolver().insert(EmployeeProvider.CONTENT_URI, cv);
        Toast.makeText(getBaseContext(), uri.toString(), Toast.LENGTH_LONG).show();
    }

    public void onClickRetrieveStudents(View view){
        String URL = "content://bintang.id.contentproviderexample_2.provider/EmployeeProvider";
        Uri studentsUri = Uri.parse(URL);
        Cursor c = getContentResolver().query(studentsUri,null, null, null, "name");

        if(c.equals(null)){
            Toast.makeText(getBaseContext(), "Ga ada data !", Toast.LENGTH_LONG).show();
        }else{
            if(c.moveToFirst()){
                do{
                    Toast.makeText(this,
                            c.getString(c.getColumnIndex(EmployeeProvider._ID)) +
                                    ", " +  c.getString(c.getColumnIndex( EmployeeProvider.NAME)) +
                                    ", " + c.getString(c.getColumnIndex( EmployeeProvider.GRADE)),
                            Toast.LENGTH_SHORT).show();
                }while(c.moveToNext());
            }
        }

    }

}
