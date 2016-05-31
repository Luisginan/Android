package bintang.id.listviewwithheader;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by bintang on 3/16/2016.
 * Purpose : An adapter to custom the listview and set the logic for needed purpose.
 */
public class PersonAdapter extends ArrayAdapter<Person> {
    private final Context context;
    private final ArrayList<Person> persons;

    //Custom Constructor
    public PersonAdapter(Context context, ArrayList<Person> modelsArrayList) {
        super(context, R.layout.row_item, modelsArrayList);
        this.context = context;
        this.persons = modelsArrayList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //create the inflater for each view
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView;
        //Cek whether header or item, 1= Header, 0= Item
        if(persons.get(position).getType() == 1){
            rowView = setHeaderView(position, parent, inflater);
        }else{
            rowView = setRowView(position, parent, inflater);
        }

        return rowView;
    }

    private View setRowView(int position, ViewGroup parent, LayoutInflater inflater) {
        View rowView;

        rowView = inflater.inflate(R.layout.row_item, parent, false);
        TextView name = (TextView) rowView.findViewById(R.id.nameLabel);
        TextView jobTitle = (TextView) rowView.findViewById(R.id.jobTitleLabel);

        name.setText(persons.get(position).getName());
        jobTitle.setText(persons.get(position).getJobTitle());

        return rowView;
    }

    private View setHeaderView(int position, ViewGroup parent, LayoutInflater inflater) {
        View rowView;

        rowView = inflater.inflate(R.layout.row_header, parent, false);
        TextView headerTag = (TextView)rowView.findViewById(R.id.headerTitle);

        headerTag.setText(persons.get(position).getName());

        return rowView;
    }
}