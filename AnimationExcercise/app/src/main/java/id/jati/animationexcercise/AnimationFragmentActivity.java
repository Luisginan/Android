package id.jati.animationexcercise;
/*
    Animation Fragment Activity
    - menciptakan  fragment.
    - fragment akan di remove saat  animation listener mengetahui animasi berakhir.
      dengan memamnggil addAnimationListener milik Fragment.
      dengan adanya fungsi addAnimationListener kita dapat memanfaatkan AnimationListener. salah satunya
      saat animasi telah selesai dijalankan, dapat meremove fragment.

    - Animasi dijalankan oleh AdvertiseFragment, dengan memanggil method
      - Show = untuk menampilkan animasi saat  fragment muncul (slide-down).
      - Hide = untuk menampilkan animasi yang menutup (slide-up) saat fragment di klik,

      Alasan hide diletakkan disisi fragment, karena pada saat fragment di remove, animasi
             pada fragment tidak berjalan.

     sumber: http://developer.android.com/guide/components/fragments.html
 */
import android.animation.Animator;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class AnimationFragmentActivity extends AppCompatActivity {

    Button btn_adv,btn_adv_up;
    String tag="";

    AdvertiseFragment fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_fragment);
        getSupportActionBar().setTitle("Fragment Animation");

        init();

        btn_adv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragment = new AdvertiseFragment();
                fragmentTransaction.add(R.id.root_activity, fragment,"ADV_FRAG");
                fragmentTransaction.commit();

                fragment.AddAnimatorListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        FragmentManager fragmentManager = getFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.remove(fragment);
                        fragmentTransaction.commit();
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });
            }
        });

    }

    public void init()
    {
        btn_adv = (Button) findViewById(R.id.btn_adv);

    }
}
