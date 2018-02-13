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
import com.winklix.indu.healthcareapp.activities.HomeVisitActivity;
import com.winklix.indu.healthcareapp.activities.HomeVisitDiseasesActivity;
import com.winklix.indu.healthcareapp.pojo.HomevisitcategoryDataPojo;
import com.winklix.indu.healthcareapp.pojo.ServiceCategoryDataPojo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dell on 08-02-2018.
 */

public class HomeVisitAdapter extends RecyclerView.Adapter<HomeVisitAdapter.ViewHolder>  {

    private Context mcontext;
    private ArrayList<HomevisitcategoryDataPojo> service_modals;

    public HomeVisitAdapter(Context context,ArrayList<HomevisitcategoryDataPojo> list){
        this.mcontext = context;
        this.service_modals = list;

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View myview = LayoutInflater.from(mcontext).inflate(R.layout.service_data, parent, false);

        return new ViewHolder(myview,mcontext, service_modals);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final HomevisitcategoryDataPojo serv_model = service_modals.get(position);

        holder.service_prod_name.setText(serv_model.getService_cat_name());
        Picasso.with(mcontext).load("http://2040healthcare.com/images/homevisitservicecategory/" + serv_model.getImage()).into(holder.service_prod_img);

        holder.product_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mcontext,HomeVisitDiseasesActivity.class);
                intent.putExtra("cat_id",serv_model.getService_cat_id());
                mcontext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return service_modals.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView service_prod_name;
        ImageView service_prod_img;
        LinearLayout product_ll;
        Context mcontext;
        List<ServiceCategoryDataPojo> userList;

         public ViewHolder(View itemView,Context mcontext, List<HomevisitcategoryDataPojo> userList) {
            super(itemView);

             service_prod_name = (TextView) itemView.findViewById(R.id.service_prod_name);
             service_prod_img = (ImageView) itemView.findViewById(R.id.service_prod_img);
             product_ll = (LinearLayout) itemView.findViewById(R.id.product_ll);
        }
    }
}
