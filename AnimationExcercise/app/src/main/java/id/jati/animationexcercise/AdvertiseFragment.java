package id.jati.animationexcercise;
/*
    untuk memperoleh view dari layout fragment untuk keperluan Animasi, diambil saat onCreateView fragment.

    Untuk mejalankan animasi saat slide down (membuka) dan slide up (menutup) pada fragment.
    membuat fungsi Show(). dan Hide().
    pada fungsi Hide(), listener dikirimkan ke fungsi AddAnimatorListener, untuk mengetahui
    AnimatorListener pada saat hide.
    salah satu tujuannya adalah saat animasi telah selesai (sampai kondisi end), maka dapat meremove fragment..

 */

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;


public class AdvertiseFragment extends Fragment {
    View v;
    private static final String TAG = "AdvertiseFragment";
    private Animator.AnimatorListener listener;

    public AdvertiseFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_advertise, container, false);
        v.setVisibility(View.INVISIBLE);
        View tv = v.findViewById(R.id.text_view);
        ((TextView)tv).setText("Fragment #");

        View imageView = v.findViewById(R.id.imageView1);
        View viewRoot = v.findViewById(R.id.root);
        viewRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Hide();
            }
        });

        v.setVisibility(View.VISIBLE);
        Show();
        return v;
    }

    public  void AddAnimatorListener(Animator.AnimatorListener listener)
    {
        this.listener = listener;
    }

    public void Hide()
    {
        View viewRoot = v.findViewById(R.id.root);
        AnimatorSet animatorSet = (AnimatorSet) AnimatorInflater.loadAnimator(getActivity(), R.animator.fragment_slide_up);
        animatorSet.setTarget(viewRoot);
        animatorSet.addListener(listener);
        animatorSet.start();
    }

    public void Show()
    {
        View viewRoot = v.findViewById(R.id.root);
        AnimatorSet animatorSet = (AnimatorSet) AnimatorInflater.loadAnimator(getActivity(), R.animator.fragment_slide_down);
        animatorSet.setTarget(viewRoot);
        animatorSet.start();
    }

}
