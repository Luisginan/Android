package id.jati.navigationdrawerexcercise;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private String[] mListPlanetTitle;
    private DrawerLayout mDrawerLayout;
    private ListView mLvPlanetMenu;

    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListPlanetTitle = getResources().getStringArray(R.array.planets);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        mLvPlanetMenu = (ListView) findViewById(R.id.lv_planet_menu);

        mLvPlanetMenu.setAdapter(new ArrayAdapter<>(this,
                R.layout.planet_menu_item, mListPlanetTitle));

        mLvPlanetMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectItem(position);
            }
        });

        mDrawerToggle = new ActionBarDrawerToggle(
                this,
                mDrawerLayout,
                //R.drawable.ic_drawer, //todo :tidak di dukung pada ActionbarDrawerToogle v7
                R.string.drawer_open,
                R.string.drawer_close)
        {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                invalidateOptionsMenu();
                Log.d("drawer", "onDrawerClosed");
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
                Log.d("drawer", "onDrawerOpened");
            }
        };

        mDrawerLayout.addDrawerListener(mDrawerToggle);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }


    /**
     * Swaps fragments in the main content view
     */
    private void selectItem(int position) {

        Fragment fragment = new PlanetFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(PlanetFragment.ARG_PLANET_NUMBER, position);
        fragment.setArguments(bundle);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.content_frame, fragment);
        transaction.commit();

        mLvPlanetMenu.setItemChecked(position, true);
        getSupportActionBar().setTitle(mListPlanetTitle[position]);
        mDrawerLayout.closeDrawer(mLvPlanetMenu);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mDrawerToggle.onOptionsItemSelected(item))
        {
            return true;
        }

        if(item.getItemId()==R.id.action_other)
        {
            Toast.makeText(this,"Hello, there",Toast.LENGTH_SHORT).show();
        }

        if(item.getItemId()==R.id.second_activity)
        {
            Intent intent = new Intent(this,SecondActivity.class);
            startActivity(intent);
        }
        //todo diluar ini untuk handle actionbar item yang lain
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mLvPlanetMenu);
        menu.findItem(R.id.action_other).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    public static class PlanetFragment extends Fragment {
        public static final String ARG_PLANET_NUMBER = "planet_number";
        public PlanetFragment() {

        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState)
        {
            View view = inflater.inflate(R.layout.fragment_planet,container,false);
            int i= getArguments().getInt(ARG_PLANET_NUMBER);
            String planet = getResources().getStringArray(R.array.planets)[i];
            getActivity().setTitle(planet);

            int imageId = getResources().getIdentifier(planet.toLowerCase(),
                    "drawable",getActivity().getPackageName());

            ((ImageView)view.findViewById(R.id.image_planet)).setImageResource(imageId);

            return view;
        }
    }


}
