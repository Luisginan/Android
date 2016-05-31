package id.jati.excercisesettingdialog;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.preference.EditTextPreference;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ListView;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by jati on 23/02/2016.
 * Note: Class SettingsFragment extends class PreferenceFragment = for android API 11 and later.
 *       kelas ini mengimplemetasikan fungsi OnSharedPreferenceChangeListener yang akan di trigger
 *       pada saat terjadi perubahan value pada preference objek yang didefiniskan dalam preference.xml,
 *
 * function
 * onCreate =  - akan menampilkan setting dialog (yang didefinisikan dalam file xml),
 *               file xml akan dipilih berdasarkan extra :name dan value dari preference_header.xml.
 *             - mengambil value setiap objek preference, melalui sharedPreference
 *             - dan register  OnSharedPreferenceChangeListener
 *             - merubah atribut summary pada setiap objek preference.
 * onSharedPreferenceChanged (implemets) = akan merubah atribut summary pada saat terjadi perubahan value
 *                                         pada object preference.
 *
 */

public  class SettingsFragment extends PreferenceFragment implements OnSharedPreferenceChangeListener  {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String settings = getArguments().getString("settings");
        if ("notifications".equals(settings)) {
            addPreferencesFromResource(R.xml.preferences);
        } else if ("sync".equals(settings)) {
            addPreferencesFromResource(R.xml.preferences);
        }

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);

        getDefaultValueChangeToSummary(sharedPreferences);
    }

    public void getDefaultValueChangeToSummary(SharedPreferences sharedPreferences)
    {
        Preference preference;
        String[] stringKey ={"pref_edit_text","pref_ringtones","pref_list","pref_list2"};
        String[] checkboxKey ={"pref_checkbox","pref_checkbox2"};

        for (String key:stringKey) {
            preference = findPreference(key);
            preference.setSummary(sharedPreferences.getString(key, ""));
        }

        for (String key:checkboxKey) {
            preference = findPreference(key);
            if (sharedPreferences.getBoolean(key,false))
                preference.setSummary("Enable");
            else
                preference.setSummary("Disable");
        }

        String key="pref_multilist";
        String items = "";
        Set<String> selections = sharedPreferences.getStringSet(key, null);
        for (String i : selections) {
            items = items + " " + i;
        }
        if (items.isEmpty())
            items = "None";
        preference = findPreference(key);
        preference.setSummary(items);

        key="pref_switch";
        preference = findPreference(key);
        if (sharedPreferences.getBoolean(key, true))
            preference.setSummary("On");
        else
            preference.setSummary("Off");

        /* find key and value on sharedPreference
        Map<String, ?> sharedPreferencesAll = sharedPreferences.getAll();
        for (Map.Entry<String, ?> i: sharedPreferencesAll.entrySet()) {
             preference=findPreference(i.getKey());
        }*/

    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        Preference preference = findPreference(key);
        switch (key) {
            case "pref_edit_text":
                String value = sharedPreferences.getString(key, "");
                preference.setSummary(value);
                break;
            case "pref_checkbox":
            case "pref_checkbox2":
                if (sharedPreferences.getBoolean(key, true))
                    preference.setSummary("Enable");
                else
                    preference.setSummary("Disable");
                break;
            case "pref_list":
            case "pref_list2":
                preference.setSummary(sharedPreferences.getString(key, ""));
                break;
            case "pref_ringtones":
                preference.setSummary(sharedPreferences.getString(key, ""));
                //int index=findPreference("pref_ringtones").getOrder();
                break;
            case "pref_multilist":
                String items = "";
                Set<String> selections = sharedPreferences.getStringSet(key, null);
                for (String i : selections) {
                    items = items + " " + i;
                }

                if (items.isEmpty())
                    items = "None";
                preference.setSummary(items);
                break;
            case "pref_switch":
                if (sharedPreferences.getBoolean(key, true))
                    preference.setSummary("On");
                else
                    preference.setSummary("Off");
                break;
        }
    }


}
