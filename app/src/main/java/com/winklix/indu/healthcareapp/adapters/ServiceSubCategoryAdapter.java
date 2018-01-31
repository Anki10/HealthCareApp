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
import com.winklix.indu.healthcareapp.TestDemo.SubcategoryList;
import com.winklix.indu.healthcareapp.activities.NearByServiceCenter_Activity;
import com.winklix.indu.healthcareapp.activities.ProductActivity;
import com.winklix.indu.healthcareapp.activities.ServiceDescActivity;
import com.winklix.indu.healthcareapp.activities.ServiceSubCategoryListActivity;
import com.winklix.indu.healthcareapp.pojo.ServiceSubCategoryPojo;
import com.winklix.indu.healthcareapp.pojo.SubcategoryPojo;

import java.util.ArrayList;

/**
 * Created by Administrator on 1/18/2018.
 */

public class ServiceSubCategoryAdapter extends RecyclerView.Adapter<ServiceSubCategoryAdapter.ViewHolder>  {

    private Context context;
    private ArrayList<ServiceSubCategoryPojo> subcategorylist;

    public ServiceSubCategoryAdapter(Context context, ArrayList<ServiceSubCategoryPojo>subcategorylist ){
        this.context = context;
        this.subcategorylist = subcategorylist;

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_data, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final ServiceSubCategoryPojo pojo = subcategorylist.get(position);

        holder.buy_cat_name.setText(pojo.getSubcategory_name());

        Picasso.with(context).load("http://2040healthcare.com/images/servicecategory/" + pojo.getImage()).into(holder.buy_cat_img);

        holder.category_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ServiceSubCategoryListActivity.class);
                intent.putExtra("cat_id",pojo.getFk_category_id());
                intent.putExtra("subCat_id",pojo.getSubcategory_id());
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return subcategorylist.size();
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

    private void SubDialog(final String cat_id,final String subCat_id){
        final Dialog dialog = new Dialog(context);
        dialog.setTitle("Select service Type...");
        dialog.setContentView(R.layout.service_dialog);
        final RadioGroup service_type_rg = dialog.findViewById(R.id.service_type_rg);
        final RadioButton home_service_rb = dialog.findViewById(R.id.home_service_rb);
        final RadioButton sharing_service_rb = dialog.findViewById(R.id.sharing_service_rb);

        Button service_btnSubmit = (Button) dialog.findViewById(R.id.service_btnSubmit);
        service_btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (home_service_rb.isChecked()) {

                    Intent intent = new Intent(context, ServiceDescActivity.class);
                    intent.putExtra("cat_id", cat_id);
                    intent.putExtra("subCat_id", subCat_id);
                    context.startActivity(intent);
                    dialog.dismiss();

                } else if (sharing_service_rb.isChecked()) {
                    Intent intents = new Intent(context, ServiceDescActivity.class);
                    intents.putExtra("cat_id", cat_id);
                    intents.putExtra("subCat_id", subCat_id);
                    context.startActivity(intents);
                    dialog.dismiss();

                }
            }
        });

        dialog.show();
    }
}
