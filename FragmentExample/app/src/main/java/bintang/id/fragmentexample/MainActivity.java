package bintang.id.fragmentexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements MemeComicListFragment.OnRageComicSelected {

    public String myVariable = " hi iam from activity ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.root_layout, MemeComicListFragment.newInstance(), "rageComicList")
                    .commit();
        }
    }

    @Override
    public void onMemeComicSelected(int imageResId, String name, String description, String url) {
        Toast.makeText(MainActivity.this, "You've select " + name + "!", Toast.LENGTH_SHORT).show();
        final MemeComicDetailsFragment detailsFragment = MemeComicDetailsFragment.newInstance(imageResId, name, description, url);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.root_layout, detailsFragment, "rageComicDetails")
                .addToBackStack(null)
                .commit();
    }
}
