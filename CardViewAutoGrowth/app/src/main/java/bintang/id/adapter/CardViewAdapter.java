
/**
 * Created by Bintang on 3/8/2016.
 * Purpose :
 * An adapter to handle the view using recycler view
 * and holder class to define the component.
 * In RecyclerView, to set OnItemClickListener and onItemLongClickListener
 * we must to create an interface.
 */
package bintang.id.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import bintang.id.cardviewautogrowth.R;
import bintang.id.model.Product;

public class CardViewAdapter extends RecyclerView.Adapter<CardViewAdapter.ProductViewHolder> {
    private static IClickListener clickListener;
    private static ILongClickListener longClickListener;
    private ArrayList<Product> products;
    private boolean txtClicked = false;
    private Context context;


    //public constructor
    public CardViewAdapter(Context context, ArrayList<Product> products) {
        this.products = products;
        this.context = context;
    }

    /*implements interface ViewHolder's method
    Register the layout as the view of inflater, so the layout can automatically inflate.
    */
    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_customrowemployee, viewGroup, false);

        return new ProductViewHolder(v);
    }

    /*implements interface ViewHolder's method
    fill the property from viewHolder's data
    */
    @Override
    public void onBindViewHolder(ProductViewHolder productViewHolder, int i) {
        productViewHolder.name.setText(products.get(i).getProductName());
        productViewHolder.price.setText(products.get(i).getProductPrice());
        productViewHolder.image.setImageResource(products.get(i).getProductImage());

    }

    //set public onItemClickListener method so it able to use from any class
    public void setOnItemClickListener(IClickListener clickListener) {
        CardViewAdapter.clickListener = clickListener;
    }

    //set public OnItemLongClickListener method so it able to use from any class
    public void setOnItemLongClickListener(ILongClickListener longClickListener) {
        CardViewAdapter.longClickListener = longClickListener;
    }

    public void deleteItem(int index) {
        products.remove(index);
        notifyItemRemoved(index);
    }

    @Override
    public int getItemCount() {
        return products.size();
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
    public class ProductViewHolder extends RecyclerView.ViewHolder implements
            View.OnClickListener,
            AdapterView.OnLongClickListener,
            AdapterView.OnItemClickListener,
            AdapterView.OnTouchListener
    {

        boolean bList = false;

        RelativeLayout relativeLayout;
        TextView name, price;
        ImageView image;
        Button btnClick, btnShowList;
        ListView lv;

        /*public constructor in holder class
        *to register all property from view.
        *After register all components, we register the listener to the view, such as:
        * OnClickListener and OnLongClickListener
        * */
        public ProductViewHolder(View itemView) {
            super(itemView);
            initHolder();
            setListener();
        }

        //initialize component for holder
        private void initHolder(){
            relativeLayout = (RelativeLayout)itemView.findViewById(R.id.relativeLay);
            name        = (TextView) itemView.findViewById(R.id.lblName);
            price       = (TextView) itemView.findViewById(R.id.lblPrice);
            image       = (ImageView) itemView.findViewById(R.id.productImage);
            btnClick    = (Button) itemView.findViewById(R.id.btnClick);
            btnShowList = (Button) itemView.findViewById(R.id.btnShowList);
            btnShowList.setText(R.string.showlist);
            lv          = (ListView) itemView.findViewById(R.id.listView);

            fillListView();
        }

        private void fillListView(){
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(context, R.array.android, R.layout.android);
            lv.setAdapter(adapter);
            lv.setFastScrollEnabled(true);
        }

        //set the listener
        private void setListener(){
            name.setOnClickListener(this);
            btnClick.setOnClickListener(this);
            btnShowList.setOnClickListener(this);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
            lv.setOnTouchListener(this);
            lv.setOnItemClickListener(this);
        }

        //implements interface onClick's method
        @Override
        public void onClick(View v) {
            if(v.getId() == R.id.btnClick){
                deleteItem(getAdapterPosition());
            }else if(v.getId() == R.id.lblName){
                txtViewOnClick();
            }else if(v.getId() == R.id.btnShowList){
                showListView();
            }else{
                clickListener.onItemClick(getAdapterPosition(), v);
            }
        }

        private void showListView(){
            if(!bList){
                lv.setVisibility(View.VISIBLE);
                btnShowList.setText(R.string.hidenlist);
                bList = true;
            }else{
                lv.setVisibility(View.GONE);
                btnShowList.setText(R.string.showlist);
                bList = false;
            }
            lv.getParent().requestDisallowInterceptTouchEvent(true);
        }

        private void txtViewOnClick(){
            if(!txtClicked){
                name.setSingleLine(false);
                txtClicked = true;
            }else{
                name.setSingleLine(true);
                name.setEllipsize(TextUtils.TruncateAt.END);
                txtClicked = false;
            }
        }

        //implements interface onLongClick's method
        @Override
        public boolean onLongClick(View v) {
            longClickListener.onLongClick(getAdapterPosition(), v);

            return true;
        }

        @Override
        public boolean onTouch(View cardView, MotionEvent event) {
            cardView.getParent().requestDisallowInterceptTouchEvent(true);
            cardView.onTouchEvent(event);// Handle ListView touch events.
            return true;
        }

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Toast.makeText(context, ((TextView) view).getText() , Toast.LENGTH_SHORT).show();
        }
    }

}
