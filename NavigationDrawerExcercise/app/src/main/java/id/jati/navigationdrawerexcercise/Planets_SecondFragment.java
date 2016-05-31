package id.jati.navigationdrawerexcercise;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class Planets_SecondFragment extends Fragment {
    private ImageView imageView;

    public Planets_SecondFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_planet, container, false);
        imageView = (ImageView) view.findViewById(R.id.image_planet);

        String title_menu = getArguments().getString("planet");
        int imageId = getResources().getIdentifier(title_menu,
                "drawable", getActivity().getPackageName());
        ((ImageView)view.findViewById(R.id.image_planet)).setImageResource(imageId);

        return view;
    }

}
