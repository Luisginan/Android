package bintang.id.alarmexercise;

import android.app.Dialog;
import android.app.TimePickerDialog;

import android.os.Bundle;
import android.os.Message;
import android.os.Handler;

import android.widget.TimePicker;



/**
 * Created by bintang on 2/1/2016.
 */
public class DialogFragment extends android.support.v4.app.DialogFragment {
    private int timeHour, timeMinute;
    private Handler alarmHandler;
    public DialogFragment(Handler handler){
        this.alarmHandler = handler;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        Bundle alarmBundle = getArguments();
        timeHour = alarmBundle.getInt(Constant.HOUR);
        timeMinute = alarmBundle.getInt(Constant.MINUTE);

        TimePickerDialog.OnTimeSetListener listener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                timeHour    = hourOfDay;
                timeMinute  = minute;

                Bundle timePickerBundle = new Bundle();
                timePickerBundle.putInt(Constant.HOUR, timeHour);
                timePickerBundle.putInt(Constant.MINUTE, minute);

                Message msg = new Message();
                msg.setData(timePickerBundle);
                alarmHandler.sendMessage(msg);
            }
        };

        return new TimePickerDialog(getActivity(), listener, timeHour, timeMinute, false);
    }
}
