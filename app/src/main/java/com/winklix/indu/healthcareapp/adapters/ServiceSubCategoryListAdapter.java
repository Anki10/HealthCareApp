package com.winklix.indu.healthcareapp.adapters;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.winklix.indu.healthcareapp.R;
import com.winklix.indu.healthcareapp.activities.ServiceDescActivity;
import com.winklix.indu.healthcareapp.activities.ServiceSubCategoryListActivity;
import com.winklix.indu.healthcareapp.pojo.ServiceNameDataPojo;

import java.util.ArrayList;

/**
 * Created by dell on 23-01-2018.
 */

public class ServiceSubCategoryListAdapter extends RecyclerView.Adapter<ServiceSubCategoryListAdapter.ViewHolder>  {

    private Context context;
    private ArrayList<ServiceNameDataPojo> servicenamelist;
    private String cat_id,subCat_id;


    public ServiceSubCategoryListAdapter(Context context, ArrayList<ServiceNameDataPojo> list,String cat_id,String sub_catId){

        this.context = context;
        this.servicenamelist = list;
        this.cat_id = cat_id;
        this.subCat_id = sub_catId;

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_data, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final ServiceNameDataPojo pojo = servicenamelist.get(position);

        holder.buy_cat_name.setText(pojo.getService_name());

        Picasso.with(context).load("http://2040healthcare.com/images/serviceproduct/" + pojo.getImage()).into(holder.buy_cat_img);

        holder.category_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intents = new Intent(context, ServiceDescActivity.class);
                intents.putExtra("cat_id", cat_id);
                intents.putExtra("subCat_id", subCat_id);
                intents.putExtra("service_name",pojo.getService_name());
               context.startActivity(intents);
            }
        });

    }

    @Override
    public int getItemCount() {
        return servicenamelist.size();
    }

   public class ViewHolder extends RecyclerView.ViewHolder {

        private LinearLayout category_ll;
        private TextView buy_cat_name;
        private ImageView buy_cat_img;


        public ViewHolder(View itemView) {
            super(itemView);

            category_ll = (LinearLayout) itemView.findViewById(R.id.category_ll);
            buy_cat_name = (TextView) itemView.findViewById(R.id.buy_cat_name);
            buy_cat_img = (ImageView) itemView.findViewById(R.id.buy_cat_img);
        }
    }


}
