package bintang.id.alarmexercise;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Log;

/**
 * Created by bintang on 2/1/2016.
 */
public class AlarmReceiver extends WakefulBroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        MainActivity.getTvResult().setText("Alarm Active !!!");
        Log.d("Alarm ON", "Alarm is now on");
    }



}
