package id.jati.activitycustometransition;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

/**
 * Created by luis on 3/15/2016.
 * Purpose :
 */
public class SixActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_six);

        getSupportActionBar().setTitle("Pull Up Transition");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    public void  onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.pull_in_top, R.anim.pull_out_bottom);
    }
}
