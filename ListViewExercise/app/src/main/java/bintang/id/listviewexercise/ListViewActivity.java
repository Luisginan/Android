package bintang.id.listviewexercise;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import java.util.Arrays;

import bintang.id.adapter.ListViewAdapter;
import bintang.id.model.Product;

/**
 * Created by bintang on 2/16/2016.
 * Purpose : Sample using List View
 */
public class ListViewActivity extends android.app.Activity {
    android.widget.ListView lvExample;


   @Override
   protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.layout_listview);

       lvExample = (android.widget.ListView)findViewById(R.id.listView1);

       final Product[] productList = new Product[]{
               new Product(1, "Milk", 21.50),
               new Product(2, "Butter", 77.50),
               new Product(3, "Yogurt", 33.30),
               new Product(4, "Meat Ball", 27.80),
               new Product(5, "Ice Cream", 11.35),
               new Product(6, "Choco Chip", 77.56),
               new Product(7, "Aqua", 90.89),
               new Product(8, "Sosro", 66.50),
               new Product(9, "Salt", 3.70),
               new Product(10, "Yakult", 99.10)};

       ListViewAdapter adapter = new ListViewAdapter(this, Arrays.asList(productList));
       lvExample.setAdapter(adapter);


       lvExample.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               Toast.makeText(getBaseContext(), productList[position].getName(), Toast.LENGTH_SHORT).show();
           }
       });

       lvExample.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
           @Override
           public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
               alert(productList[position].getName());
               return true;
           }
       });

   }


    private void alert(String message){
        final AlertDialog.Builder alert = new AlertDialog.Builder(ListViewActivity.this);
        alert.setTitle("On Item Long Click Listener")
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        AlertDialog dialog = alert.create();
        dialog.show();
   }
}
