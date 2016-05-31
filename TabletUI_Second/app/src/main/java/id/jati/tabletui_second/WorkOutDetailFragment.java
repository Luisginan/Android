package id.jati.tabletui_second;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * WorkoutDetail fragment menampilakan detail data dari data pada FragmentList.
 * pada saat on start View pada layout fragment ini di isi.
 *
 * fragment ini dipanggil dari 2 Activity dan 2 layout yang berbeda.
 * 1. MainActivity jika layar large
 * 2. Detail Activity jika layar normal/small
 */
public class WorkOutDetailFragment extends Fragment {
    private long workoutId;

    public WorkOutDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if(savedInstanceState !=null)
        {
            workoutId = savedInstanceState.getLong("workoutId");
        }
        return inflater.inflate(R.layout.fragment_work_out_detail, container, false);
    }

    @Override
    public void onStart()
    {
        super.onStart();
        View view = getView();
        if(view != null)
        {
            TextView title = (TextView) view.findViewById(R.id.textTitle);
            Workout workout = Workout.workouts[(int) workoutId];
            title.setText(workout.getName());

            TextView description = (TextView) view.findViewById(R.id.textDescription);
            description.setText(workout.getDescription());
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState)
    {
        savedInstanceState.putLong("workoutId",workoutId);
    }

    public void setWorkoutId(long id)
    {
        this.workoutId = id;
    }

}
