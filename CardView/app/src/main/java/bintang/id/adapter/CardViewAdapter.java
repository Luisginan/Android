
/**
 * Created by Bintang on 3/8/2016.
 * Purpose :
 * An adapter to handle the view using recycler view
 * and holder class to define the component.
 * In RecyclerView, to set OnItemClickListener and onItemLongClickListener
 * we must to create an interface.
 */
package bintang.id.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import bintang.id.cardview.R;
import bintang.id.model.Product;

public class CardViewAdapter extends RecyclerView.Adapter<CardViewAdapter.ProductViewHolder> {
    private static IClickListener clickListener;
    private static ILongClickListener longClickListener;
    private ArrayList<Product> item;

    //public constructor
    public CardViewAdapter(ArrayList<Product> item) {
        this.item = item;
    }

    /*implements interface ViewHolder's method
    Register the layout as the view of inflater, so the layout can automatically inflate.
    */
    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_customrowemployee, viewGroup, false);
        ProductViewHolder product = new ProductViewHolder(v);
        return product;
    }

    /*implements interface ViewHolder's method
    fill the property from viewHolder's data
    */
    @Override
    public void onBindViewHolder(ProductViewHolder productViewHolder, int i) {
        productViewHolder.name.setText(item.get(i).getProductName());
        productViewHolder.price.setText(item.get(i).getProductPrice());
        productViewHolder.image.setImageResource(item.get(i).getProductImage());

    }

    //set public onItemClickListener method so it able to use from any class
    public void setOnItemClickListener(IClickListener clickListener) {
        CardViewAdapter.clickListener = clickListener;
    }

    //set public OnItemLongClickListener method so it able to use from any class
    public void setOnItemLongClickListener(ILongClickListener longClickListener) {
        CardViewAdapter.longClickListener = longClickListener;
    }

    @Override
    public int getItemCount() {
        return item.size();
    }

    //create ItemClickListener
    public interface IClickListener {
        void onItemClick(int position, View v);
    }

    //create ItemLongClickListener
    public interface ILongClickListener {
        void onLongClick(int position, View v);
    }

    //create holder class
    public class ProductViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener, AdapterView.OnLongClickListener {
        TextView name, price;
        ImageView image;

        /*public constructor in holder class
        *to register all property from view.
        *After register all components, we register the listener to the view, such as:
        * OnClickListener and OnLongClickListener
        * */
        public ProductViewHolder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.lblName);
            price = (TextView) itemView.findViewById(R.id.lblPrice);
            image = (ImageView) itemView.findViewById(R.id.productImage);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        //implements interface onClick's method
        @Override
        public void onClick(View v) {
            clickListener.onItemClick(getAdapterPosition(), v);
            item.remove(getAdapterPosition());
            notifyItemRemoved(getAdapterPosition());
            notifyItemRangeChanged(getAdapterPosition(), item.size());
        }

        //implements interface onLongClick's method
        @Override
        public boolean onLongClick(View v) {
            longClickListener.onLongClick(getAdapterPosition(), v);
            return true;
        }
    }

}
