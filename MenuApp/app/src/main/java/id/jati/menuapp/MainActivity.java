package id.jati.menuapp;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity  {
    TextView info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        registerForContextMenu(info);
    }

    public void init()
    {
        info = (TextView) findViewById(R.id.info);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.menu_item_two:
                Intent intent = new Intent(this,ACBParticularViewActivity.class);
                startActivity(intent);
                return true;
            case R.id.menu_item_three:
                info.setText("Item 2 di kLik via onOptionsItemSelected");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //onclick on menuitem
    public void itemOneClick(MenuItem menuItem)
    {
        info.setText("Menu Item 1 di Klik via atribut OnClick");
        Intent intent = new Intent(this,ActionbarSingleViewActivity.class);
        startActivity(intent);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu_text, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.icm_font_red :
                info.setTextColor(Color.RED);
                return  true;
            case R.id.icm_font_blue :
                info.setTextColor(Color.BLUE);
                return  true;
            case R.id.icm_set_text :
                info.setText("change via Contex view dengan floating Menu untuk android dibawah 3.0");
                return true;
            default:
                return false;

        }
    }


}
