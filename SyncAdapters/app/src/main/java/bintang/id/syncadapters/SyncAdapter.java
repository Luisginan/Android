package bintang.id.syncadapters;

/**
 * Created by bintang on 3/7/2016.
 * /**
 * Handle the transfer of data between a server and an
 * app, using the Android sync adapter framework.
 * We should put our main code on onPerformSync method, so it can be execute when framework run the scheduled
 */
import android.accounts.Account;
import android.content.AbstractThreadedSyncAdapter;
import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SyncResult;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;



public class SyncAdapter extends AbstractThreadedSyncAdapter {
    public static final String SYNC_FINISHED = "SyncFinished";
    public static final String SYNC_STARTED = "SyncStarted";
    private static final String TAG = "SyncAdapter";

    ContentResolver mContentResolver;
    Context context;
    String resultValue = null;

    public SyncAdapter(Context context, boolean autoInitialize) {
        super(context, autoInitialize);
        /*
         * If your app uses a content resolver, get an instance of it
         * from the incoming Context
         */
        this.context = context;
        mContentResolver = context.getContentResolver();
        Log.i(TAG, "SyncAdapter");
    }
    /**
     * Set up the sync adapter. This form of the
     * constructor maintains compatibility with Android 3.0
     * and later platform versions
     */
    public SyncAdapter(
            Context context,
            boolean autoInitialize,
            boolean allowParallelSyncs) {
        super(context, autoInitialize, allowParallelSyncs);
        /*
         * If your app uses a content resolver, get an instance of it
         * from the incoming Context
         */
        mContentResolver = context.getContentResolver();
    }
    @Override
    public void onPerformSync(Account account, Bundle extras, String authority, ContentProviderClient provider, SyncResult syncResult) {
        Log.i(TAG, "onPerformSync");
        Intent i = new Intent(SYNC_STARTED);
        context.sendBroadcast(i);

        String params[] = {"http://homecamportal.cloudapp.net/Api/User/Signin??","luisginan@gmail.com","123456"};
        new GetDataSync().execute(params);

        i = new Intent(SYNC_FINISHED);
        context.sendBroadcast(i);

    }


    private class GetDataSync extends AsyncTask<String, Void, String>{
        @Override
        protected String doInBackground(String... params) {
            Log.d(TAG, "Sync is running...!");
            String target = params[0]+"email="+params[1]+"&password="+params[2]; Log.d(TAG, "target= "+target);
            HttpClient httpClient = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet(target);

            int responseCode = -1;
            try{
                HttpResponse httpResponse = httpClient.execute(httpGet);
                responseCode = httpResponse.getStatusLine().getStatusCode();
                if(responseCode == 200 || responseCode == 204){
                    InputStream inputStream = httpResponse.getEntity().getContent();
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                    BufferedReader reader = new BufferedReader(inputStreamReader);
                    StringBuilder sb = new StringBuilder();

                    String bufferedStrChunk = null;
                    while((bufferedStrChunk = reader.readLine()) != null){
                        sb.append(bufferedStrChunk);
                    }
                    System.out.println(sb.toString());
                    resultValue = bufferedStrChunk;
                }else{
                    Log.d(TAG, "Sync failed because of= "+ responseCode);
                    throw new Exception(String.valueOf(responseCode));
                }
            }catch (Exception ex){
                ex.printStackTrace();
            }

            return resultValue;
        }

        @Override
        protected void onPostExecute(String result){
            if(result != null){
                resultValue = result;
                Log.d(TAG, "Sync is succeed");
                Log.d(TAG, result);
            }
        }
    }



}
