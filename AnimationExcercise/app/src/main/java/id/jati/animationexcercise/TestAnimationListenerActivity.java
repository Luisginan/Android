package id.jati.animationexcercise;
/*
    sumber : http://cogitolearning.co.uk/?p=1498 and http://cogitolearning.co.uk/?p=1482
    membahas tentang penggunaan interface AnimatorListener dan AnimatorPauseListener (API 19)

 */

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class TestAnimationListenerActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView image_jelly;
    Button btn_start,btn_cancel,btn_end,btn_pause,btn_resume;
    TextView text_start, text_running, text_pause, text_message;
    ObjectAnimator anim_jelly;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_animation_listener);
        getSupportActionBar().setTitle("Animation Listener");
        getSupportActionBar().setSubtitle("Animation listener and Animation pause Listener");
        init();

        anim_jelly  = ObjectAnimator.ofFloat(image_jelly, View.ROTATION,0,360);
        anim_jelly.setDuration(1300);
        anim_jelly.setRepeatCount(5);
        anim_jelly.setRepeatMode(anim_jelly.INFINITE);


        Animator.AnimatorListener animatorListener = new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                setStatusTextView();
                text_message.setText("Animation is Running");
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                setStatusTextView();
                text_message.setText(text_message.getText() + " ==> and Ending");
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                setStatusTextView();
                text_message.setText("Animation Canceled");
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                setStatusTextView();
                text_message.setText("Animation is Repeating");
            }
        };

        Animator.AnimatorPauseListener animatorPauseListener = new Animator.AnimatorPauseListener() {
            @Override
            public void onAnimationPause(Animator animation) {
                setStatusTextView();
                text_message.setText("Animation Paused");
            }

            @Override
            public void onAnimationResume(Animator animation) {
                setStatusTextView();
                text_message.setText("Animation on Resume");
            }
        };

        //add animator listener on animation.
        anim_jelly.addListener(animatorListener);
        anim_jelly.addPauseListener(animatorPauseListener);



    }

    public void init()
    {
        image_jelly = (ImageView) findViewById(R.id.image_jelly);
        btn_start = (Button) findViewById(R.id.btn_start);
        text_start = (TextView) findViewById(R.id.text_start);
        text_running = (TextView) findViewById(R.id.text_running);
        text_pause = (TextView) findViewById(R.id.text_pause);

        btn_cancel = (Button) findViewById(R.id.btn_cancel);
        btn_end = (Button) findViewById(R.id.btn_end);
        btn_pause = (Button) findViewById(R.id.btn_pause);
        btn_resume = (Button) findViewById(R.id.btn_resume);

        btn_start.setOnClickListener(this);
        btn_cancel.setOnClickListener(this);
        btn_end.setOnClickListener(this);
        btn_pause.setOnClickListener(this);
        btn_resume.setOnClickListener(this);

        text_message = (TextView) findViewById(R.id.text_message);

    }

    public void setStatusTextView()
    {
        text_start.setText("Start Status:"+anim_jelly.isStarted());
        text_running.setText("Running Status:"+anim_jelly.isRunning());
        text_pause.setText("Pause Status:"+anim_jelly.isPaused());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btn_start:
                 anim_jelly.start();
                 break;
            case R.id.btn_cancel:
                anim_jelly.cancel();
                break;
            case R.id.btn_end:
                anim_jelly.end();
                break;
            case R.id.btn_pause:
                anim_jelly.pause();
                break;
            case R.id.btn_resume:
                anim_jelly.resume();
                break;
        }
    }
}
