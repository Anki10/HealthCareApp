package com.winklix.indu.healthcareapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.winklix.indu.healthcareapp.R;
import com.winklix.indu.healthcareapp.activities.BuyDashboardActivity;
import com.winklix.indu.healthcareapp.activities.ProductActivity;
import com.winklix.indu.healthcareapp.pojo.CategoryDataPojo;
import com.winklix.indu.healthcareapp.pojo.SubcategoryPojo;

import java.util.ArrayList;

/**
 * Created by Administrator on 1/18/2018.
 */

public class SubCategoryAdapter extends RecyclerView.Adapter<SubCategoryAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<SubcategoryPojo> subcategorylist;

    public SubCategoryAdapter(Context context,ArrayList<SubcategoryPojo> list ){

        this.context = context;
        this.subcategorylist = list;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_data, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final SubcategoryPojo pojo = subcategorylist.get(position);

        holder.buy_cat_name.setText(pojo.getSubcategory_name());

        Picasso.with(context).load("http://2040healthcare.com/images/category/" + pojo.getImage())
                .placeholder(R.mipmap.ic_launcher)
                .into(holder.buy_cat_img);

        holder.category_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ProductActivity.class);
                intent.putExtra("cat",pojo.getFk_category_id());
                intent.putExtra("sub_cat",pojo.getSubcategory_id());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return subcategorylist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private LinearLayout category_ll;
        private TextView buy_cat_name;
        private ImageView buy_cat_img;

        public MyViewHolder(View itemView) {
            super(itemView);

            category_ll = (LinearLayout) itemView.findViewById(R.id.category_ll);
            buy_cat_name = (TextView) itemView.findViewById(R.id.buy_cat_name);
            buy_cat_img = (ImageView) itemView.findViewById(R.id.buy_cat_img);
        }
    }
}
