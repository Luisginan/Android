package id.jati.navigationdrawerexcercise;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {
    private NavigationView navigationView;
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Navigation On Top");
        actionBar.setSubtitle("With Custom Action Bar");

        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.ic_user);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                //item.setChecked(true); -- cek item multi cek
                navigationView.setCheckedItem(item.getItemId());

                if(item.getItemId()== R.id.drawer_home)
                {
                    Toast.makeText(getBaseContext(),"Hello, i'am Home !!",Toast.LENGTH_SHORT).show();
                }

                if(item.getItemId()== R.id.drawer_settings)
                {
                    Toast.makeText(getBaseContext(),"Hello, i'am Setting !!",Toast.LENGTH_SHORT).show();
                }


                Planets_SecondFragment fragment = new Planets_SecondFragment();
                Bundle bundle = new Bundle();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                bundle.putString("planet", item.getTitle().toString().toLowerCase());
                fragment.setArguments(bundle);
                transaction.replace(R.id.content_frame, fragment);
                transaction.commit();

                mDrawerLayout.closeDrawers();
                return false;
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
            default:
                return false;
        }
    }
}
