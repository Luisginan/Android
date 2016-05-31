package bintang.id.alarmexercise;

import java.util.Calendar;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends FragmentActivity {

    private static int timeHourAlarm = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
    private static int timeMinuteAlarm = Calendar.getInstance().get(Calendar.MINUTE);
    TextView tvClock;
    private static TextView tvResult;

    public static TextView getTvResult() {
        return tvResult;
    }

    AlarmManager alarmManager;
    private PendingIntent alarmPendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvClock = (TextView) findViewById(R.id.msg1);
        tvClock.setText(timeHourAlarm + ":" + timeMinuteAlarm);
        tvResult = (TextView) findViewById(R.id.msg2);

        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        Intent intentForAlarm = new Intent(MainActivity.this, AlarmReceiver.class);
        alarmPendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, intentForAlarm, PendingIntent.FLAG_ONE_SHOT);


        View.OnClickListener setAlarmListener = new View.OnClickListener() {
            public void onClick(View view) {
                tvResult.setText("");
                Bundle alarmBundle = new Bundle();
                alarmBundle.putInt(Constant.HOUR, timeHourAlarm);
                alarmBundle.putInt(Constant.MINUTE, timeMinuteAlarm);
                DialogFragment alarmFragment = new DialogFragment(new AlarmHandler());
                alarmFragment.setArguments(alarmBundle);
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.add(alarmFragment, Constant.TIME_PICKER);
                fragmentTransaction.commit();
            }
        };

        Button btnStart = (Button) findViewById(R.id.btnStart);
        btnStart.setOnClickListener(setAlarmListener);


        View.OnClickListener setCancelListener = new View.OnClickListener() {
            public void onClick(View view) {
                tvResult.setText("");
                cancelAlarm();
            }
        };
        Button btnStop = (Button) findViewById(R.id.btnStop);
        btnStop.setOnClickListener(setCancelListener);
    }

    class AlarmHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            Bundle bundle = msg.getData();
            timeHourAlarm = bundle.getInt(Constant.HOUR);
            timeMinuteAlarm = bundle.getInt(Constant.MINUTE);
            tvClock.setText(timeHourAlarm + ":" + timeMinuteAlarm);
            setAlarm();
        }
    }

    private void setAlarm() {
        Calendar alarmCalendar = Calendar.getInstance();
        alarmCalendar.set(Calendar.HOUR_OF_DAY, timeHourAlarm);
        alarmCalendar.set(Calendar.MINUTE, timeMinuteAlarm);

        alarmManager.setExact(AlarmManager.RTC_WAKEUP, alarmCalendar.getTimeInMillis(), alarmPendingIntent);//Hanya sekali dalam sehari dan tidak repetisi
        Toast.makeText(this, "Alarm Started", Toast.LENGTH_LONG).show();
        Log.d("Alarm ON", "Alarm is now on");
    }

    private void cancelAlarm() {
        if (alarmManager != null) {
            alarmManager.cancel(alarmPendingIntent);
            MainActivity.getTvResult().setText("");
            Toast.makeText(this, "Alarm Stoped", Toast.LENGTH_LONG).show();
            Log.d("Alarm OFF", "Alarm is now off");//
        }
    }
}