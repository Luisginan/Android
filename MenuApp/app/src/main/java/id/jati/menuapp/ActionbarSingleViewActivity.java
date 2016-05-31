package id.jati.menuapp;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

public class ActionbarSingleViewActivity extends AppCompatActivity {
    CheckBox checkBox;
    TextView tvInfo;
    private ActionMode mActionMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actionbar_single_view);
        init();

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mActionMode = ActionbarSingleViewActivity.this.startActionMode(new ActionBarCallBack());
                } else {
                    mActionMode.finish();
                    mActionMode=null;
                    checkBox.setChecked(false);
                }

            }
        });

        /*tvInfo.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(mActionMode !=null)
                {
                    //mActionMode.finish();
                    return false;
                }

                // Start the CAB using the ActionMode.Callback
                mActionMode = ActionbarSingleViewActivity.this.startActionMode(new ActionBarCallBack());
                tvInfo.setSelected(true);
                return true;
            }
        });*/

        tvInfo.setOnLongClickListener(new View.OnLongClickListener() {
            // Called when the user long-clicks on someView
            public boolean onLongClick(View view) {
                if (mActionMode != null) {
                    return false;
                }

                // Start the CAB using the ActionMode.Callback defined above
                mActionMode = ActionbarSingleViewActivity.this.startActionMode(mActionModeCallback);
                view.setSelected(true);
                return true;
            }
        });
    }

    void init()
    {
        checkBox = (CheckBox) findViewById(R.id.check_box);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(actionBar.getTitle()+" |CAB untuk individual view");
        actionBar.setSubtitle("Ketika checkbox di klik akan muncul menu pada action bar");

        tvInfo = (TextView) findViewById(R.id.tv_info);
    }

    private ActionMode.Callback mActionModeCallback = new ActionMode.Callback() {

        // Called when the action mode is created; startActionMode() was called
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            // Inflate a menu resource providing context menu items
            MenuInflater inflater = mode.getMenuInflater();
            inflater.inflate(R.menu.context_menu_text_view, menu);
            return true;
        }

        // Called each time the action mode is shown. Always called after onCreateActionMode, but
        // may be called multiple times if the mode is invalidated.
        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false; // Return false if nothing is done
        }

        // Called when the user selects a contextual menu item
        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch (item.getItemId()) {
                case R.id.copy:
                    return true;
                case R.id.cut:
                    return true;
                case R.id.paste:
                    return true;
                default:
                    return false;
            }
        }

        // Called when the user exits the action mode
        @Override
        public void onDestroyActionMode(ActionMode mode) {

        }
    };


    class ActionBarCallBack implements ActionMode.Callback {
    //checkbox di implementasikan via class.
        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            // TODO Auto-generated method stub
            switch (item.getItemId()){
                case R.id.icm_font_red :
                    tvInfo.setTextColor(Color.RED);
                    return true;
                case R.id.icm_font_blue :
                    tvInfo.setTextColor(Color.BLUE);
                    return true;
                case R.id.icm_set_text :
                    tvInfo.setText("Di Klik via Contextual Action Bar");
                    return true;
                default:
                    return false;
            }
        }

        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            // TODO Auto-generated method stub
            mode.getMenuInflater().inflate(R.menu.context_menu_text, menu);
            return true;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            // TODO Auto-generated method stub
            checkBox.setChecked(false);
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            // TODO Auto-generated method stub
            return false;
        }

    }

}


