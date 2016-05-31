package bintang.id.swipeviewpager;

/**
 * Created by bintang on 3/15/2016.
 * Purpose : Handler received data and set to view
 */
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import bintang.id.model.Employee;

@SuppressLint("ValidFragment")
public class FragmentViewHandler extends Fragment{

    TextView tvLblName, tvJobTitle, tvPosition;
    ImageView imgView;

    Employee employee;

    @SuppressLint("ValidFragment")
    public FragmentViewHandler(Employee employee) {
        this.employee = employee;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.myfragment_layout, container, false);

        init(v);
        fillComponent();

        return v;
    }

    private void init(View v){
        tvLblName   = (TextView) v.findViewById(R.id.lblName);
        tvJobTitle  = (TextView) v.findViewById(R.id.lblJobTitle);
        tvPosition  = (TextView) v.findViewById(R.id.lblPosition);
        imgView     = (ImageView) v.findViewById(R.id.productImage);
    }


    private void fillComponent(){
        tvLblName.setText(employee.getName());
        tvJobTitle.setText(employee.getJobTitle());
        tvPosition.setText(String.valueOf(employee.getNo()));

        imgView.setImageResource(R.mipmap.ic_launcher);
    }


}



