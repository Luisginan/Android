package id.jati.excercisesettingdialog;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

/**
 * Created by jati on 25/02/2016.
 */
public class TestActivity extends Activity {
    protected void onCreate(Bundle bundle){
        super.onCreate(bundle);

        Bundle extra = getIntent().getExtras();
        String value = extra.getString("test");

    }


}
