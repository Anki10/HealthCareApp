package com.winklix.indu.healthcareapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.winklix.indu.healthcareapp.R;
import com.winklix.indu.healthcareapp.activities.BuyDashboardActivity;
import com.winklix.indu.healthcareapp.activities.SubCategoryActivity;
import com.winklix.indu.healthcareapp.modals.Product_Modal;
import com.winklix.indu.healthcareapp.pojo.CategoryDataPojo;
import com.winklix.indu.healthcareapp.pojo.CategoryPojo;

import java.util.ArrayList;

/**
 * Created by Administrator on 1/17/2018.
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder> implements Filterable {

    private Context context;
    private ArrayList<CategoryDataPojo> categorylist;
    public ArrayList<CategoryDataPojo> mFilteredList;

    public CategoryAdapter(Context context, ArrayList<CategoryDataPojo> list){
        this.context = context;
        this.categorylist = list;
        this.mFilteredList = list;

    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_data, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final CategoryDataPojo pojo = categorylist.get(position);

        holder.buy_cat_name.setText(pojo.getCategory_name());

        Picasso.with((BuyDashboardActivity) context).load("http://2040healthcare.com/images/category/" + pojo.getImage())
                .placeholder(R.mipmap.ic_launcher)
                .into(holder.buy_cat_img);

        holder.category_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, SubCategoryActivity.class);
                intent.putExtra("sub_category_list",pojo.getSubcategory());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categorylist.size();
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

                    mFilteredList = categorylist;
                } else {

                    ArrayList<CategoryDataPojo> filteredList = new ArrayList<>();

                    for (CategoryDataPojo service_search : categorylist) {

                        if (service_search.getCategory_name().toLowerCase().contains(charString)) {

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
                mFilteredList = (ArrayList<CategoryDataPojo>) filterResults.values;
                notifyDataSetChanged();
            }
        };
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


