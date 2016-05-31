package id.jati.camera_imagereaderfix;

/*
 * Pengujian :menggunakan emulator geeny Motion.
 * Capture Image menggunakan Intent Camera.
 * langkah-langkah:
    * AndroidManifest
     - camerafeature = google play dapat mencegah aplikasi di install pada device yang tidak memiliki kamera
     - StoragePermission = agar dapat melakukan penyimpanan ke external storage
    * Compose Camera Intent : MediaStore.ACTION_IMAGE_CAPTURE
    * Start Intent Camera : startActivityforResult, didalamnya termasuk memilih media storage yang digunakan.
    * Menerima hasil dari Intent: OnActivityResult().
    * Menampilkan File yang tersimpan ke ImageView melalui fungsi PreviewImage.
    * options.inSampleSize=8; menampilkan resolusi 1/8 dari lebar dan tinggi gambar.
    *
    * sumber : http://developer.android.com/guide/topics/media/camera.html
    *
 */

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
    private static final int CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE = 200;
    public static final int MEDIA_TYPE_IMAGE = 1;
    public static final int MEDIA_TYPE_VIDEO = 2;
    private Uri fileUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InitButtonCapture();
        InitButtonRecord();
    }

    private  void InitButtonCapture()
    {
        Button btn_runCameraCapture = (Button) findViewById(R.id.btn_runCamera);
        btn_runCameraCapture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
                startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
            }
        });
    }

    private void InitButtonRecord()
    {
        Button btn_runCameraRecorod = (Button) findViewById(R.id.btn_runCameraRecord);
        btn_runCameraRecorod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                fileUri = getOutputMediaFileUri(MEDIA_TYPE_VIDEO);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
                intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
                intent.putExtra(MediaStore.EXTRA_DURATION_LIMIT,10);
                startActivityForResult(intent, CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE);
                startActivityForResult(intent, CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE);
            }
        });
    }

    private  Uri getOutputMediaFileUri(int type){
        return Uri.fromFile(getOutputMediaFile(type));
    }

    private File getOutputMediaFile(int type){

        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "MyCameraApp");

        if (! mediaStorageDir.exists()){
            if (! mediaStorageDir.mkdirs()){
                Log.d("MyCameraApp", "failed to create directory");
                return null;
            }
        }

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE){
            mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                    "IMG_"+ timeStamp + ".jpg");
        } else if(type == MEDIA_TYPE_VIDEO) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                    "VID_"+ timeStamp + ".mp4");
        } else {
            return null;
        }

        return mediaFile;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE)
        {   if(resultCode == RESULT_OK)  {
            Toast.makeText(getApplicationContext(),"Picture Saved",Toast.LENGTH_LONG).show();
            PreviewImage();
        }  else if(resultCode == RESULT_CANCELED) {
            Toast.makeText(getApplicationContext(),"Take Picture Canceled",Toast.LENGTH_LONG).show();
        }  else {
            Toast.makeText(getApplicationContext(),"Error On Take Picture", Toast.LENGTH_LONG).show();
        }
        }
    }

    public void PreviewImage()
    {
        try
        {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize=8;
            Bitmap myBitmap = BitmapFactory.decodeFile(fileUri.getPath(),options);
            ImageView myImage = (ImageView) findViewById(R.id.image01);
            myImage.setImageBitmap(myBitmap);
        }catch (NullPointerException e)
        {
            e.printStackTrace();
        }
    }
}
