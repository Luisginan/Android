package id.jati.tabletui_second;


import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;


/**
 * A simple {@link Fragment} subclass.
 * kelas ini digunakan untuk manmapilkan List. layout yang digunakan
 * adalah simple_list_item_1
 *
 */
public class WorkoutListFragment extends ListFragment {

    //menambahkan listener pada fragmen List, tujuannya agar MainActivity
    //dapat menentukan detail Fragment yang akan ditampilakn.
    //listener ini dipanggil saat on Atach. (fragment di klik)
    static  interface WorkoutListListener
    {
        void itemClicked(long id);
    }

    private WorkoutListListener listener;

    public WorkoutListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_workout_list, container, false);
        String[] names = new String[Workout.workouts.length];
        for(int i=0;i<names.length;i++)
        {
            names[i] = Workout.workouts[i].getName();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                inflater.getContext(),android.R.layout.simple_list_item_1,names);
        setListAdapter(adapter);

        return  super.onCreateView(inflater,container,savedInstanceState);
    }

    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);
        this.listener = (WorkoutListListener) activity;
    }

    @Override
    public void onListItemClick(ListView listView, View view, int position, long id)
    {
        if(listener != null)
        {
            listener.itemClicked(id);
        }
    }

}
