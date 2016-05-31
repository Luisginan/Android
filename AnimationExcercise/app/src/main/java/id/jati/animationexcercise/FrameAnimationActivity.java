package id.jati.animationexcercise;
/*
    Frame by frame animasi di ambil dari *.xml dalam /res/drawable.
    - *.xml berisi urutan item image yang akan di tampilkan, beserta durasinya..
 */
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class FrameAnimationActivity extends AppCompatActivity {

    ImageView frame_image;
    Button btn_start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame_animation);
        getSupportActionBar().setTitle("Frame by Frame Animation");

        btn_start = (Button) this.findViewById(R.id.btn_start);
        frame_image = (ImageView) this.findViewById(R.id.frame_image);

        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                frame_image.setBackgroundResource(R.drawable.anim_frame);
                AnimationDrawable animationDrawable = (AnimationDrawable) frame_image.getBackground();
                animationDrawable.stop();
                animationDrawable.start();
            }
        });

        frame_image.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                frame_image.setBackgroundResource(R.drawable.anim_frame);
                AnimationDrawable animationDrawable = (AnimationDrawable) frame_image.getBackground();
                animationDrawable.stop();
                animationDrawable.start();
                return true;
            }
        });


    }
}
