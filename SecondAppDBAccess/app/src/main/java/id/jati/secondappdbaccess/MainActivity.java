package id.jati.secondappdbaccess;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tvFirstName,tvLastName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        /*Log.d("Insert: ", "Inserting ..");
        ContactDbHelper contactDbHelper = new ContactDbHelper(this);
        contactDbHelper.addContact(new Contact(1, "Andi", "Kurnia", "085711002200", "andi@info.com"));
        contactDbHelper.addContact(new Contact(2, "Budi", "Yanto", "081315678910", "budi@info.com"));
        Log.d("Reading: ", "Reading all contacts..");

        Contact contact = new Contact();
        contact = contactDbHelper.getContact();
        tvFirstName = (TextView) findViewById(R.id.tvfirstname);
        tvLastName = (TextView) findViewById(R.id.tvlastname);*/
    }
}
