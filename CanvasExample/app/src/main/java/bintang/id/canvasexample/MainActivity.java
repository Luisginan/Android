package bintang.id.canvasexample;

/**
 * Created by Bintang on 3/17/2016.
 * Purpose :  Activity that handle all action from canvas
 */

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private CanvasView customCanvas;
    Button btnClear, btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);

        init();
        setListener();
    }

    private void init(){
        customCanvas    = (CanvasView) findViewById(R.id.signature_canvas);
        btnClear        = (Button)findViewById(R.id.btnClear);
        btnSave         = (Button)findViewById(R.id.btnSave);
    }

    private void setListener(){
        btnClear.setOnClickListener(this);
        btnSave.setOnClickListener(this);
    }

    //saving file using bitmap
    public void saveFile(String fileName){
        Toast.makeText(this, "File Name= "+fileName, Toast.LENGTH_SHORT).show();

        /*retrieve path from external storage
        if there is not available path on external file, system will write the file in internal memory
        */
        File folder     = new File(Environment.getExternalStorageDirectory().toString());
        boolean success = false;
        if (!folder.exists()) {
            success = folder.mkdirs();
        }
        Log.i("Main Activity", "Folder = "+success);


        File file = new File(Environment.getExternalStorageDirectory().toString() + "/" + fileName+".png");
        if ( !file.exists() ){
            try{
                success = file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Log.i("Main Activity", success + "file");
        //Prepare outputStream
        FileOutputStream oStream;
        try{
            oStream = new FileOutputStream(file);
            Log.i("Main Activity, oStream", ""+oStream);

            //Convert canvas into bitmap
            Bitmap getIntoBitmap = customCanvas.getBitmap();
            Bitmap saveToBitmap = Bitmap.createBitmap(320, 480, Bitmap.Config.ARGB_8888);
            Paint paint = new Paint();
            paint.setColor(Color.WHITE);

            //Create new canvas and setting the size
            Canvas canvas = new Canvas(saveToBitmap);
            canvas.drawRect(new Rect(0, 0, 320, 480), paint);
            canvas.drawBitmap(getIntoBitmap, new Rect(0, 0, getIntoBitmap.getWidth(), getIntoBitmap.getHeight()), new Rect(0, 0, 320, 480), null);

            //saving into .PNG format with 100% quality of compression
            saveToBitmap.compress(Bitmap.CompressFormat.PNG, 100, oStream);
        }catch (NullPointerException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Null error", Toast.LENGTH_SHORT).show();
        }

        catch (FileNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "File error", Toast.LENGTH_SHORT).show();
        }

    }

    //Custom dialog to fill the name of the file
    private void savingConfirmation(){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.layout_savingconfirmation, null);
        dialogBuilder.setView(dialogView);

        final EditText txtFileName = (EditText) dialogView.findViewById(R.id.txtFileName);

        dialogBuilder.setTitle("Insert file name:");
        dialogBuilder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                saveFile(txtFileName.getText().toString());
            }
        });
        dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                dialog.dismiss();
            }
        });
        AlertDialog b = dialogBuilder.create();
        b.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnClear:
                customCanvas.clearCanvas();
                break;
            case R.id.btnSave:
                savingConfirmation();
                break;
        }
    }
}