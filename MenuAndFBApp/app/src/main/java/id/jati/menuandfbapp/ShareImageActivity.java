/*
    Activity ShareImage.
    - share image menggunakan
      * shareDialog = APP yg menggunakan share dialog tidak membutuhkan mekanisme login.
      * ShareButton = intinya memanggil shareDialog. -> proses saat image di load, maka tombol share button aktif.
      * ShareApi = membutuhkan mekanisme login, sehingga di APP  harus dibuat login dan
      *            penambahan permission publish_action
                     List<String> permissionNeeds = Arrays.asList("publish_actions");
                     manager = LoginManager.getInstance();
                     manager.logInWithPublishPermissions(this,permissionNeeds);
       * untuk mekanisme callback jangan lupa di tambahkan = callbackManager.onActivityResult(requestCode, resultCode, data)
         pada onActivityResult.

    yang belum berhasil Share via:
    - sendButton = mekansme penggunaan blm ditemukan.

 */
package id.jati.menuandfbapp;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.share.ShareApi;
import com.facebook.share.Sharer;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.LikeView;
import com.facebook.share.widget.SendButton;
import com.facebook.share.widget.ShareButton;
import com.facebook.share.widget.ShareDialog;

public class ShareImageActivity extends AppCompatActivity {

    private static final int ACTION_PICK_IMAGE = 1;
    Button btn_loadImage,btn_share_api;
    ImageView imageShare;
    String selectedImagePath;
    TextView info;
    ShareDialog shareDialog;
    ShareButton shareButtonfb;
    SendButton sendButton;
    LikeView likeView;
    private CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        callbackManager= CallbackManager.Factory.create();
        setContentView(R.layout.activity_share_image);
        init();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.share_image_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.menu_item_fb:
                ShareDialogToFb();
                return true;
            case R.id.menu_item_twiter:

                return true;
            case R.id.menu_item_share:

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void init()
    {
        btn_loadImage = (Button) findViewById(R.id.btnLoadImage);
        imageShare = (ImageView) findViewById(R.id.image_share);
        info = (TextView) findViewById(R.id.info);
        shareButtonfb = (ShareButton) findViewById(R.id.share_btn_fb);
        btn_share_api = (Button) findViewById(R.id.btn_share_api);
        sendButton = (SendButton) findViewById(R.id.send_button);

        likeView = (LikeView) findViewById(R.id.likeview);
        likeView.setObjectIdAndType(
                "https://www.facebook.com/FacebookDevelopers",
                LikeView.ObjectType.OPEN_GRAPH);
    }

    public void LoadImageClick(View view)
    {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select Picture"), ACTION_PICK_IMAGE);
    }

    @Override
    public void onActivityResult(int requestCode,int resultCode, Intent data)
    {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == ACTION_PICK_IMAGE) {
                try {
                    Uri selectedImageUri = data.getData();
                    selectedImagePath = getPath(selectedImageUri);
                    info.setText(selectedImagePath);
                    imageShare.setImageURI(selectedImageUri);

                    this.ShareButtonToFb();
                    this.ShareSendButtonToFb();

                } catch (Exception exp)
                {
                    info.setText(exp.getMessage().toString());
                }
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

    public void SharedImageFBWithApi()
    {
        Bitmap image = ((BitmapDrawable)imageShare.getDrawable()).getBitmap();
        SharePhoto photo = new SharePhoto.Builder()
                .setBitmap(image)
                .build();
        SharePhotoContent content = new SharePhotoContent.Builder()
                .addPhoto(photo)
                .build();

        ShareApi.share(content, new FacebookCallback<Sharer.Result>() {
            @Override
            public void onSuccess(Sharer.Result result) {
                info.setText("sukses");
                Toast.makeText(getApplicationContext(), "Picture posted with success on Facebook!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancel() {
                info.setText("cancel");
            }

            @Override
            public void onError(FacebookException error) {
                info.setText(error.getMessage().toString());
            }
        });
    }

    public void btn_share_apiClick(View view)
    {
        SharedImageFBWithApi();
    }

    public void ShareDialogToFb(){
        shareDialog = new ShareDialog(this);
        Bitmap image = ((BitmapDrawable)imageShare.getDrawable()).getBitmap();
        SharePhoto photo = new SharePhoto.Builder()
                .setBitmap(image)
                .setCaption("Testing")
                .build();
        SharePhotoContent content = new SharePhotoContent.Builder()
                .addPhoto(photo)
                .build();

        shareDialog.show(content);
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
                info.setText("error" + error.getMessage().toString());
            }
        });
    }

    public void ShareButtonToFb()
    {
        Bitmap image = ((BitmapDrawable)imageShare.getDrawable()).getBitmap();
        SharePhoto photo = new SharePhoto.Builder()
                .setBitmap(image)
                .build();
        SharePhotoContent content = new SharePhotoContent.Builder()
                .addPhoto(photo)
                .build();
        shareButtonfb.registerCallback(callbackManager, new FacebookCallback<Sharer.Result>() {
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
        shareButtonfb.setShareContent(content);


    }



    public void ShareSendButtonToFb()
    {
        Bitmap image = ((BitmapDrawable)imageShare.getDrawable()).getBitmap();
        SharePhoto photo = new SharePhoto.Builder()
                .setBitmap(image)
                .build();
        SharePhotoContent content = new SharePhotoContent.Builder()
                .addPhoto(photo)
                .build();
        sendButton.setShareContent(content);
        sendButton.registerCallback(callbackManager, new FacebookCallback<Sharer.Result>() {
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
    }
}
