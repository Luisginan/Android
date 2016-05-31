package bintang.id.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;


import bintang.id.model.Product;
import bintang.id.tabfragment.R;

/**
 * Created by bintang on 2/16/2016.
 * Purpose : Adapter for list view
 */
public class ListViewAdapter extends BaseAdapter {

    Context context;
    List<Product> productList;

    public ListViewAdapter(Context context, List<Product> items){
        this.context = context;
        this.productList = items;
    }

    private class ViewHolder{
        TextView tvName, tvPrice;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if(convertView == null){
            convertView = inflater.inflate(R.layout.layout_listview_listitem, null);
            viewHolder = new ViewHolder();
            viewHolder.tvName = (TextView)convertView.findViewById(R.id.tvItems);
            viewHolder.tvPrice = (TextView)convertView.findViewById(R.id.tvPriceItem);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)convertView.getTag();
        }

        Product product = (Product)getItem(position);
        viewHolder.tvName.setText(product.getProductName());
        viewHolder.tvPrice.setText(String.valueOf(product.getProductPrice()));

        setPosition(position, convertView);

        return convertView;
    }

    private View setPosition(int position, View convertView){
        final int RGB_GRAY = Color.rgb(238, 233, 233);
        final int RGB_WHITE = Color.rgb(255, 255, 255);

        if(position % 2 == 0){
            convertView.setBackgroundColor(RGB_GRAY);
        }else{
            convertView.setBackgroundColor(RGB_WHITE);
        }
        return convertView;
    }

    @Override
    public int getCount() {
        return productList.size();
    }

    @Override
    public Object getItem(int position) {
        return productList.get(position);
    }

    @Override
    public long getItemId(int position) {
            return  ((Product) getItem(position)).getProductImage();
    }

}
