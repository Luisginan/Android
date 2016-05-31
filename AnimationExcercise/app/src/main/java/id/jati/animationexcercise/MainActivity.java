package id.jati.animationexcercise;

import android.app.ActivityOptions;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId())
        {
            case R.id.menu_frame:
                intent = new Intent(this,FrameAnimationActivity.class);
                startActivity(intent);
                return true;
            case R.id.menu_tween:
                intent = new Intent(this,TweenAnimationActivity.class);
                startActivity(intent);
                return true;
            case R.id.menu_property :
                intent = new Intent(this, PropertyAnimationActivity.class);
                startActivity(intent);
                return true;
            case R.id.menu_fragment_anim:
                intent = new Intent(this,AnimationFragmentActivity.class);
                startActivity(intent);
                return true;
            case R.id.menu_animation_listener:
                intent = new Intent(this,TestAnimationListenerActivity.class);
                startActivity(intent);
                return true;
            default:
                return false;
        }
    }
}
