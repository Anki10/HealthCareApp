package com.winklix.indu.healthcareapp.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;
import com.winklix.indu.healthcareapp.R;
import com.winklix.indu.healthcareapp.activities.BuyDashboardActivity;
import com.winklix.indu.healthcareapp.activities.Buy_Prod_DescActivity;
import com.winklix.indu.healthcareapp.activities.ProductActivity;
import com.winklix.indu.healthcareapp.modals.Product_Modal;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by harsh on 27/11/2017.
 */

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyHolder> implements Filterable {

    private ArrayList<Product_Modal> product_models;
    private Activity mcontext;
    Intent intent;
    public ArrayList<Product_Modal> mFilteredList;


    public ProductAdapter(Activity montext, ArrayList<Product_Modal> product_models) {

        this.mcontext = montext;
        this.product_models = product_models;
        this.mFilteredList = product_models;


    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View myview = LayoutInflater.from(mcontext).inflate(R.layout.product_data, parent, false);

        return new MyHolder(myview, mcontext, product_models);
    }

    @Override
    public void onBindViewHolder(final MyHolder holder, int position) {
        final Product_Modal pro_model = mFilteredList.get(position);

        holder.buy_prod_name.setText(pro_model.getProd_name());
        holder.buy_prod_prize.setText("Rs. " + pro_model.getProd_prize());
       Picasso.with(mcontext).load("http://2040healthcare.com/images/product/" +pro_model.getProd_img())
               .placeholder(R.mipmap.ic_launcher)
               .into(holder.buy_prod_img);
    //     Glide.with((ProductActivity)mcontext).load("http://test.2040healthcare.com/images/product/" + pro_model.getProd_img()).into(holder.buy_prod_img);


    }

    @Override
    public int getItemCount() {
        return mFilteredList.size();
    }

    @Override
    public int getItemViewType(int position) {

        return super.getItemViewType(position);
    }

    @Override
    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String charString = charSequence.toString();

                if (charString.isEmpty()) {

                    mFilteredList = product_models;
                } else {

                    ArrayList<Product_Modal> filteredList = new ArrayList<>();

                    for (Product_Modal service_search : product_models) {

                        if (service_search.getProd_name().toLowerCase().contains(charString)
                                || service_search.getProd_prize().toLowerCase().contains(charString)) {

                            filteredList.add(service_search);
                        }
                    }

                    mFilteredList = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = mFilteredList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mFilteredList = (ArrayList<Product_Modal>) filterResults.values;
                notifyDataSetChanged();
            }
        };}

    public class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView buy_prod_name, buy_prod_prize;
        ImageView buy_prod_img;
        Context mcontext;
        List<Product_Modal> userList;

        public MyHolder(View itemView, Context mcontext, List<Product_Modal> userList) {
            super(itemView);

            this.mcontext = mcontext;
            this.userList = userList;
            buy_prod_name = (TextView) itemView.findViewById(R.id.buy_prod_name);
            buy_prod_prize = (TextView) itemView.findViewById(R.id.buy_prod_prize);
            buy_prod_img = (ImageView) itemView.findViewById(R.id.buy_prod_img);
            buy_prod_name.setOnClickListener(this);
            buy_prod_img.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            Product_Modal product_model = this.userList.get(position);
            Intent intent = new Intent(mcontext, Buy_Prod_DescActivity.class);
            intent.putExtra("Prod_id", product_model.getProd_id());
            intent.putExtra("name",product_model.getProd_name());
            intent.putExtra("img", "http://test.2040healthcare.com/images/product/"+product_model.getProd_img());
            intent.putExtra("prod_prize", product_model.getProd_prize());
            intent.putExtra("desc", product_model.getProd_description());
            intent.putExtra("specification", product_model.getProd_specification());
            mcontext.startActivity(intent);

        }
    }
}

