package id.jati.animationexcercise;
/*
    Tween Animation ==> Masuk dalam kategori View Animation.
    ada 2 cara mendefinisikannya:
    - lewat *.xml di /res/anim
    - lewat code.

    terdapat 4 jenis kategori animasi:
    1. alpha = menampilkan animasi transparency, dengan nilai float.
       elemen :(formAlpha - toAlpha = misal 0.0 - 1.0)
    2. rotate = animasi memutar
       elemen : (fromDegrees-toDegrees)=misal 0-360 (pivotX-pivotY) misal=50%,50% (titik putar di tengan image)
    3. Scale = menampilkan animasi skala dari gambar, misalnya dari besar ke kecil
       elemen :(fromXScale-toXScale)(fromYScale-toYScale) (pivotX-pivotY)
    4. Translate= menampilkan animasi perpindahan image dari sumbu X ke X atau Y ke Y. atau kombinasi keduanya.
       elemen: (fromXDelta-toXDelta) (fromYDelta-toYDelta)

    sumber : http://developer.android.com/guide/topics/resources/animation-resource.html

 */

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;

public class TweenAnimationActivity extends AppCompatActivity {

    ImageView image_tween,image_ball;
    Button btn_scale,btn_rotate,btn_alpha,btn_translate;
    Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tween_animation);
        init();


        btn_scale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_scale);
                image_tween.setVisibility(View.VISIBLE);
                image_tween.startAnimation(animation);
            }
        });

        btn_rotate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.anim_rotate);
                image_tween.setVisibility(View.VISIBLE);
                image_tween.startAnimation(animation);
            }
        });

        btn_alpha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.anim_alpha);
                image_tween.setVisibility(View.VISIBLE);
                image_tween.startAnimation(animation);
            }
        });

        btn_translate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.anim_translate);
                image_tween.setVisibility(View.VISIBLE);
                image_tween.startAnimation(animation);
            }
        });

        //mendefinisikan Tween Animation dengan pemrograman.
        RotateAnimation rotateAnimation = new RotateAnimation(0,-360
                ,RotateAnimation.RELATIVE_TO_SELF,0.5f
                ,RotateAnimation.RELATIVE_TO_SELF,0.5f);
        rotateAnimation.setDuration(5000);
        rotateAnimation.setRepeatCount(10);
        image_ball.setAnimation(rotateAnimation);

    }

    void init()
    {
        getSupportActionBar().setTitle("View - Tween Animation");
        image_tween = (ImageView) findViewById(R.id.tween_image);
        image_tween.setVisibility(View.INVISIBLE);
        btn_scale = (Button) findViewById(R.id.btn_scale);
        btn_rotate = (Button) findViewById(R.id.btn_rotate);
        btn_alpha = (Button) findViewById(R.id.btn_alpha);
        btn_translate = (Button) findViewById(R.id.btn_translate);

        image_ball = (ImageView) findViewById(R.id.image_ball);
    }
}
