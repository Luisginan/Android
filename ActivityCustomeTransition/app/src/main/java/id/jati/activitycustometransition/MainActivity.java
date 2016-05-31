package id.jati.activitycustometransition;
/*
 * Transition pada Activity dapat menggunakan metode overridePendingTransition dan jenis Animasi Tween.
   untuk transition saat back dapat menggunakan implementasi  metode onBackPressed().

   beberapa animasi yang dapat digunakan.
    - Scale
    - Alpha
    - Rotate
    - Translate.
    jenis animasi tersebut dapat diimplementasi menggunakan file resource xml pada folder anim.

    http://stackoverflow.com/questions/10243557/how-to-apply-slide-animation-between-two-activities-in-android
    http://developer.android.com/reference/android/app/Activity.html
 *
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Activity Transition App");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Intent intent;

        switch (id) {
            case R.id.second_activity:
                intent = new Intent(this, SecondActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
                return true;
            case R.id.third_activity:
                intent = new Intent(this, ThirdActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.alpha_10, R.anim.alpha_01);
                return true;
            case R.id.fourth_activity:
                intent = new Intent(this,FourthActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.scale_zoom_in,R.anim.scale_zoom_out);
                return true;
            case R.id.five_activity:
                intent = new Intent(this,FiveActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.rotate_minus360,R.anim.rotate_360);
                return true;
            case R.id.six_activity:
                intent = new Intent(this,SixActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.pull_in_top,R.anim.pull_out_bottom);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }


}
