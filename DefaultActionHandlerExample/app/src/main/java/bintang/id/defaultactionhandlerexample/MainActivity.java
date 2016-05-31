package bintang.id.defaultactionhandlerexample;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String LAUNCH_FROM_URL = "bintang.id.defaultactionhandler";
    Button btnTest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView launchInfo = (TextView)findViewById(R.id.tvTitle);
        TextView tvContent = (TextView)findViewById(R.id.tvContent);


        if ((getIntent().getAction() == Intent.ACTION_VIEW&&getIntent().getScheme().equals("http")) ||
        (getIntent().getAction() == Intent.ACTION_VIEW&&getIntent().getScheme().equals("https"))){

            tvContent.setText("Schema= "+getIntent().getScheme()+"\n Action= "+getIntent().getAction()+"\n Data= "+ getIntent().getData());
            Uri uri = getIntent().getData();
            launchInfo.setText(uri.toString());
        }
        else launchInfo.setText("NULL");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


}
