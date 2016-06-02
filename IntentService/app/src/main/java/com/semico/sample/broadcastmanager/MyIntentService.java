package com.semico.sample.broadcastmanager;

import android.app.IntentService;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

/**
 * Created by Luis Ginanjar on 01/06/2016.
 * Purpose :
 */
public class MyIntentService extends IntentService {

    public static final String  ACTION = "MSG";
    public MyIntentService() {
        super("Services");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Intent broadcastIntent = new Intent(ACTION);
        broadcastIntent.putExtra("name","Luis Ginanjar");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LocalBroadcastManager.getInstance(this).sendBroadcast(broadcastIntent);
    }
}
