package id.jati.excercisesettingdialog;

import android.preference.PreferenceActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jati on 24/02/2016.
 * class PreferenceActivityHeader digunakan menampilkan setting dialog yang didefinisikan dalam
 *                                preference_header.xml
 * dalam class ini harus overide fungsi isValidFragment, yang akan di triger pada saat
 * memilih fragment, apakah header memiliki fragment atau tidak.
 */
public class PreferenceActivityHeader extends PreferenceActivity {

    /**
     * Populate the activity with the top-level headers.
     */

    public static List<String> fragments = new ArrayList<String>();
    @Override
    public void onBuildHeaders(List<Header> target) {

        loadHeadersFromResource(R.xml.preference_header, target);
        fragments.clear();
        for (Header header : target) {
            fragments.add(header.fragment);
        }
    }

    @Override
    protected boolean isValidFragment(String fragmentName)
    {
        return fragments.contains(fragmentName);

    }
}
