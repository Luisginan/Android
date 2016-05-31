package id.jati.menuandfbapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.share.Sharer;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;

public class ShareTextActivity extends AppCompatActivity {
    TextView info;
    private EditText editUrl,editTitle,editDesc;
    ShareDialog shareDialog;
    private CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        callbackManager = CallbackManager.Factory.create();
        setContentView(R.layout.activity_share_text);
        init();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.share_text_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.menu_item_fb:
                this.ShareTextFb();
                return true;
            case R.id.menu_item_twiter:
                this.ShareTextIntent();
            case R.id.menu_item_share:
                this.ShareTextIntent();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void init()
    {
        editUrl = (EditText) findViewById(R.id.edit_url);
        editTitle = (EditText) findViewById(R.id.edit_title);
        editDesc = (EditText) findViewById(R.id.edit_desc);
        info = (TextView) findViewById(R.id.info);
    }

    public void ShareTextFb()
    {
        shareDialog = new ShareDialog(this);
        ShareLinkContent content = new ShareLinkContent.Builder()
                .setContentTitle(editTitle.getText().toString())
                .setContentDescription(editDesc.getText().toString())
                .setContentUrl(Uri.parse(editUrl.getText().toString()))
                .build();
        shareDialog.registerCallback(callbackManager, new FacebookCallback<Sharer.Result>() {
            @Override
            public void onSuccess(Sharer.Result result) {
                info.setText("sukses");
            }

            @Override
            public void onCancel() {
                info.setText("cancel");
            }

            @Override
            public void onError(FacebookException error) {
                info.setText("error"+error.getMessage().toString());
            }
        });
        shareDialog.show(content);
    }

    public void ShareTextIntent()
    {
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, editUrl.getText().toString());
        startActivityForResult(Intent.createChooser(shareIntent, "Share via:"), 100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);

        if(requestCode==100)
        {
            if(resultCode==RESULT_OK)
            {
                info.setText("Sukses");
            }
            if(resultCode==RESULT_CANCELED)
            {
                info.setText("di Cancel");
            }

        }
    }

}
