package id.jati.sharetosocialmediaapp;


import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ShareCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
//import android.widget.ShareActionProvider;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private ShareActionProvider shareActionProvider;

    EditText editTo,editSubject,editMessage;
    Button buttonSend,buttonImage,button_share,button_share2;
    TextView image_uri,image_uri2;
    ImageView image,image2;

    private static final int SELECT_PICTURE = 1;
    String selectedImagePath;

    ArrayList<Uri> imageUris = new ArrayList<Uri>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

    }

    private void init()
    {
        buttonImage = (Button) findViewById(R.id.button_image);
        button_share=(Button)  findViewById(R.id.button_image_share);
        button_share2=(Button)  findViewById(R.id.button_image_share2);
        buttonImage.setOnClickListener(this);
        button_share.setOnClickListener(this);
        button_share2.setOnClickListener(this);

        image = (ImageView) findViewById(R.id.image);
        image_uri = (TextView) findViewById(R.id.image_uri);
        image2 = (ImageView) findViewById(R.id.image2);
        image_uri2 = (TextView) findViewById(R.id.image_uri2);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.share_menu, menu);

        MenuItem item = menu.findItem(R.id.item_menu_gplus);
        /*shareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(item);

        Intent shareIntent = ShareCompat.IntentBuilder.from(this)
                .setType("text/plain").setText("TEST TEXT INTENT VIA ACTION BAR").getIntent();
        shareActionProvider.setShareIntent(shareIntent);*/
        // Create the share Intent
        return super.onCreateOptionsMenu(menu);
    }

    // Call to update the share intent
    private void setShareIntent(Intent shareIntent) {
        if (shareActionProvider != null) {
            shareActionProvider.setShareIntent(shareIntent);
        }
    }

    public void send_onclick(View view)
    {
        editTo = (EditText)findViewById(R.id.edit_to);
        editSubject = (EditText)findViewById(R.id.edit_subject);
        editMessage = (EditText)findViewById(R.id.edit_message);

        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{editTo.getText().toString()});
        sendIntent.putExtra(Intent.EXTRA_SUBJECT, editSubject.getText().toString());
        sendIntent.putExtra(Intent.EXTRA_TEXT, editMessage.getText().toString());
        sendIntent.setType("text/plain");
        startActivity(Intent.createChooser(sendIntent, "Share via:"));
    }


    @Override
    public void onClick(View v) {

        Intent intent = new Intent();
        switch (v.getId()){
            case R.id.button_image:
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Select Picture"), SELECT_PICTURE);
                break;
            case R.id.button_image_share:
                intent.setAction(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_STREAM, Uri.parse(image_uri.getText().toString()));
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "Share Image To:"),SELECT_PICTURE);
                Toast.makeText(MainActivity.this, "Image Sending..", Toast.LENGTH_LONG).show();
                break;
            case R.id.button_image_share2:
                imageUris.add(Uri.parse(image_uri.getText().toString()));
                imageUris.add(Uri.parse(image_uri2.getText().toString()));
                intent.setAction(Intent.ACTION_SEND_MULTIPLE);
                intent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, imageUris);
                intent.setType("image/*");
                startActivity(Intent.createChooser(intent, "Share images to.."));
                break;
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                Uri selectedImageUri = data.getData();
                selectedImagePath = getPath(selectedImageUri);

                image.setImageURI(selectedImageUri);
                image_uri.setText(selectedImageUri.toString());

                image2.setImageURI(selectedImageUri);
                image_uri2.setText(selectedImageUri.toString());
            }
        }
    }

    public String getPath(Uri uri) {
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }

}
