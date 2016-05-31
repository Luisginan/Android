package id.jati.animationexcercise;
/*
  sumber tutorial: http://code.tutsplus.com/tutorials/android-sdk-creating-a-simple-property-animation--mobile-15022
  ObjectAnimator dari Property animation dari contoh ini dideklarasikan pada file *.xml. yang diletakkan di
  /res/animator.
 */

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class PropertyAnimationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_animation);
        getSupportActionBar().setTitle("Property Animation");

        ImageView wheel = (ImageView) findViewById(R.id.wheel);
        AnimatorSet animatorWheelSet = (AnimatorSet) AnimatorInflater.loadAnimator(this,R.animator.wheel_spin);
        animatorWheelSet.setTarget(wheel);
        animatorWheelSet.start();

        //get the sun view
        ImageView sun = (ImageView)findViewById(R.id.sun);
        //load the sun movement animation
        AnimatorSet sunSet = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.sun_swing);
        //set the view as target
        sunSet.setTarget(sun);
        //start the animation
        sunSet.start();

        //
        ValueAnimator skyAnim = ObjectAnimator.ofInt
                (findViewById(R.id.car_layout),"backgroundColor",
                        Color.rgb(0x66, 0xcc, 0xff), Color.rgb(0x00, 0x66, 0x99));

        skyAnim.setDuration(3000);
        skyAnim.setRepeatCount(ValueAnimator.INFINITE);
        skyAnim.setRepeatMode(ValueAnimator.REVERSE);

        skyAnim.setEvaluator(new ArgbEvaluator());
        skyAnim.start();

        //set cloud inisialisastion.
        ObjectAnimator cloudAnim = ObjectAnimator.ofFloat(findViewById(R.id.cloud1), View.X, -350);
        cloudAnim.setDuration(3000);
        cloudAnim.setRepeatCount(ValueAnimator.INFINITE);
        cloudAnim.setRepeatMode(ValueAnimator.REVERSE);
        cloudAnim.start();


        ObjectAnimator cloudAnim2 = ObjectAnimator.ofFloat(findViewById(R.id.cloud2), "x", -300);
        cloudAnim2.setDuration(3000);
        cloudAnim2.setRepeatCount(ValueAnimator.INFINITE);
        cloudAnim2.setRepeatMode(ValueAnimator.REVERSE);
        cloudAnim2.start();
    }
}
