package com.winklix.indu.healthcareapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.winklix.indu.healthcareapp.R;
import com.winklix.indu.healthcareapp.pojo.GetCartDataPojo;

import java.util.ArrayList;

/**
 * Created by Administrator on 1/18/2018.
 */

public class GetCartAdapter extends RecyclerView.Adapter<GetCartAdapter.MyViewHolder>  {

    private Context context;
    private ArrayList<GetCartDataPojo>productlist;

    public GetCartAdapter(Context context,ArrayList<GetCartDataPojo> list){
        this.context = context;
        this.productlist = list;

    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.get_cart_row_data, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        GetCartDataPojo pojo = productlist.get(position);

        holder.product_cart_name.setText(pojo.getProd_name());
        holder.product_cart_price.setText("Rs: "+ pojo.getPrice());

        Picasso.with(context).load("http://2040healthcare.com/images/product/" +pojo.getThumb()).into(holder.product_image_cart);

    }

    @Override
    public int getItemCount() {
        return productlist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView product_cart_name,product_cart_price;
        private ImageView product_image_cart,product_cart_remove;


        public MyViewHolder(View itemView) {
            super(itemView);

            product_cart_name = (TextView) itemView.findViewById(R.id.product_cart_name);
            product_cart_price = (TextView) itemView.findViewById(R.id.product_cart_price);
            product_image_cart = (ImageView) itemView.findViewById(R.id.product_image_cart);
            product_cart_remove = (ImageView) itemView.findViewById(R.id.product_cart_remove);
        }
    }
}
