package bintang.id.listviewwithheader;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Person> persons = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);
        ListView listView = (ListView)findViewById(R.id.listView);

        fillPerson();
        listView.setAdapter(new PersonAdapter(this, persons));
    }

    private void fillPerson(){
        //separator
        persons.add(new Person(1,"Team Ngemart",""));
        persons.add(new Person(0,"Luis Ginanjar","Android Developer"));
        persons.add(new Person(0,"Bintang Parulian","Android Developer"));
        persons.add(new Person(0,"Jati","Android Developer"));
        persons.add(new Person(0,"Ibnu Deli","Android Developer"));
        //separator
        persons.add(new Person(1,"Team Bosnet",""));
        persons.add(new Person(0,"Rukky","Bosnet Core Developer"));
        persons.add(new Person(0,"Jenny","Bosnet Core Developer"));
        persons.add(new Person(0,"Yosef","Bosnet Core Developer"));
        persons.add(new Person(0,"Gunalan","Bosnet Core Developer"));
        persons.add(new Person(0,"Agung","Bosnet Core Developer"));
        persons.add(new Person(0,"Jitu","Bosnet Core Developer"));
        persons.add(new Person(0,"Hudha","Bosnet Core Developer"));
        persons.add(new Person(0,"Marcela","Bosnet Core Developer"));
    }
}