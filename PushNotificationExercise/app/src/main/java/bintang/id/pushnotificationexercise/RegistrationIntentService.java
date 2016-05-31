/**
 * Created by Bintang on 2/26/2016.
 * Purpose : This class purposing to get token using Google Cloud Computing (GCM)
 * and registered Id using previous generated token.
 */

package bintang.id.pushnotificationexercise;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.google.android.gms.gcm.GoogleCloudMessaging;

import java.io.IOException;

import bintang.id.Global.GlobalConstant;

public class RegistrationIntentService extends IntentService {

    private static final String TAG = "RegIntentService";
    //private static final String[] TOPICS = {"global"};
    public static final String PROPERTY_REG_ID = "registration_id";
    private static final String PROPERTY_APP_VERSION = "appVersion";


    public RegistrationIntentService() {
        super(TAG);
    }

    String regId;
    GoogleCloudMessaging gcm;


    @Override
    protected void onHandleIntent(Intent intent) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        try {

            //InstanceID instanceID = InstanceID.getInstance(this);
            gcm = GoogleCloudMessaging.getInstance(getApplicationContext());
            //String token = instanceID.getToken(getString(R.string.gcm_defaultSenderId), GoogleCloudMessaging.INSTANCE_ID_SCOPE, null);
            //Log.i(TAG, "GCM Registration Token: " + token);

            gcm = GoogleCloudMessaging.getInstance(this);
            regId = getRegistrationId(getApplicationContext());
            if(regId.isEmpty()){
                doRegister();
            }

            //subscribeTopics(token);
            sharedPreferences.edit().putBoolean(GlobalConstant.SENT_TOKEN_TO_SERVER, true).apply();

        } catch (Exception e) {
            Log.d(TAG, "Failed to complete token refresh", e);
            sharedPreferences.edit().putBoolean(GlobalConstant.SENT_TOKEN_TO_SERVER, false).apply();
        }
        Intent registrationComplete = new Intent(GlobalConstant.REGISTRATION_COMPLETE);

        registrationComplete.putExtra("regId", regId);
        LocalBroadcastManager.getInstance(this).sendBroadcast(registrationComplete);
    }

    private void doRegister(){
        new RegisterInBackGround().execute();
    }

    private String getRegistrationId(Context context){
        final SharedPreferences prefs = getGCMPreferences();
        String registrationId = prefs.getString(PROPERTY_REG_ID, "");

        if (registrationId.isEmpty()) {
            Log.i(TAG, "Registration not found.");
            return "";
        }
        int registeredVersion = prefs.getInt(PROPERTY_APP_VERSION, Integer.MIN_VALUE);
        int currentVersion = getAppVersion(context);
        if (registeredVersion != currentVersion) {
            Log.i(TAG, "App version changed.");
            return "";
        }
        return registrationId;
    }
    private static int getAppVersion(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            throw new RuntimeException("Could not get package name: " + e);
        }
    }

    private SharedPreferences getGCMPreferences() {

        return getSharedPreferences(RegistrationIntentService.class.getSimpleName(), Context.MODE_PRIVATE);
    }

   // private void subscribeTopics(String token) throws IOException {
   //    GcmPubSub pubSub = GcmPubSub.getInstance(this);
   //     for (String topic : TOPICS) {
   //         pubSub.subscribe(token, "/topics/" + topic, null);
   //     }
   // }

    private class RegisterInBackGround extends AsyncTask<Void, Void, String>{

        @Override
        protected String doInBackground(Void... params) {
            gcm = GoogleCloudMessaging.getInstance(getApplicationContext());
            String msg;
            try {
                if (gcm == null) {
                    gcm = GoogleCloudMessaging.getInstance(getApplicationContext());
                }
                regId = gcm.register(getResources().getString(R.string.gcm_defaultSenderId));
                SharedPreferences prefs = getGCMPreferences();
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString(PROPERTY_REG_ID, regId);
                editor.commit();

                msg =  regId;

            } catch (IOException ex) {
                msg = "Error :" + ex.getMessage();
            }
            return msg;
        }

        @Override
        protected void onPostExecute(String msg) {
            Intent registrationComplete = new Intent(GlobalConstant.REGISTRATION_COMPLETE);

            registrationComplete.putExtra("regId", regId);
            LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(registrationComplete);
            Log.i("msg Registration= ", msg);
        }
    }
}
