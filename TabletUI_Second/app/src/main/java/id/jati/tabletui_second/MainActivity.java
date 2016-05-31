package id.jati.tabletui_second;
/* membuat tampilan Fragment Master-Detail.
 * jika Tablet Fragment Master-detail akan ditampilkan dalam 1 Screen
 * jika Screen Normal atau kecil : Fragment master detail akan ditampilkan secara  terPisah (menggunakan 2 Activity)
 * Penjelasan dari MainActivity
 * - Mengimplementasikan WorkoutListener ketikan di Klik oleh user.
 * - Jika screen lebar akan menampilkan kedua fragment. dengan menggunakan layout MainActivity pada folder layout-large
 * - Jika screen small atau normal akan menampilkan Fragment List dan fragment detail akan ditampilakan pada detail activity
 * sumber : Head First: Android Development (Bagian Fragment)
 */

import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
                          implements WorkoutListFragment.WorkoutListListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //cara menampilkan Fragment_detail secara hardcode ke MainActivity, melalui tag <fragment>
         /*WorkOutDetailFragment fragment = (WorkOutDetailFragment)
                 getFragmentManager().findFragmentById(R.id.detail_frag);
          fragment.setWorkoutId(1);
        */

        int screenSize = getResources().getConfiguration().screenLayout &
                Configuration.SCREENLAYOUT_SIZE_MASK;
        String toastMsg;
        switch(screenSize) {
            case Configuration.SCREENLAYOUT_SIZE_LARGE:
                toastMsg = "Large screen";
                break;
            case Configuration.SCREENLAYOUT_SIZE_NORMAL:
                toastMsg = "Normal screen";
                break;
            case Configuration.SCREENLAYOUT_SIZE_SMALL:
                toastMsg = "Small screen";
                break;
            default:
                toastMsg = "Screen size is neither large, normal or small";
        }
        Toast.makeText(this, toastMsg, Toast.LENGTH_LONG).show();

    }

    @Override
    public void itemClicked(long id) {

        int screenSize = getResources().getConfiguration().screenLayout &
                Configuration.SCREENLAYOUT_SIZE_MASK;


       // View fragmentContainer = findViewById(R.id.fragment_container);
        if(screenSize == Configuration.SCREENLAYOUT_SIZE_LARGE)
        {
            WorkOutDetailFragment details = new WorkOutDetailFragment();
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            details.setWorkoutId(id);
            transaction.replace(R.id.fragment_container, details);
            transaction.addToBackStack(null);
            transaction.setTransition(transaction.TRANSIT_FRAGMENT_FADE);
            transaction.commit();
        }
        else
        {
            Intent intent = new Intent(this,DetailActivity.class);
            intent.putExtra(DetailActivity.EXTRA_WORKOUT_ID,(int)id);
            startActivity(intent);
        }


    }
}
