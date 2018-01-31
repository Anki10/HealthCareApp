package com.winklix.indu.healthcareapp.adapters;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.winklix.indu.healthcareapp.R;
import com.winklix.indu.healthcareapp.TestDemo.SubcategoryList;
import com.winklix.indu.healthcareapp.activities.NearByServiceCenter_Activity;
import com.winklix.indu.healthcareapp.activities.ServiceDescActivity;
import com.winklix.indu.healthcareapp.modals.Service_Modal;
import com.winklix.indu.healthcareapp.pojo.ServiceCategoryDataPojo;
import com.winklix.indu.healthcareapp.pojo.ServiceCategoryPojo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by harsh on 27/11/2017.
 */

public class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.MyHolder> implements Filterable {

        private ArrayList<ServiceCategoryDataPojo> service_modals;
        private Context mcontext;
        public ArrayList<ServiceCategoryDataPojo> mFilteredList;

    public ServiceAdapter(Context montext, ArrayList<ServiceCategoryDataPojo> product_models) {

            this.mcontext = montext;
            this.service_modals = product_models;
            this.mFilteredList = product_models;

        }

        @Override
        public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View myview = LayoutInflater.from(mcontext).inflate(R.layout.service_data, parent, false);

            return new MyHolder(myview,mcontext, service_modals);
        }

        @Override
        public void onBindViewHolder(final MyHolder holder, int position) {
            final ServiceCategoryDataPojo serv_model = mFilteredList.get(position);

            holder.service_prod_name.setText(serv_model.getService_cat_name());
            Picasso.with(mcontext).load("http://2040healthcare.com/images/servicecategory/" + serv_model.getImage()).into(holder.service_prod_img);

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

                        mFilteredList = service_modals;
                    } else {

                        ArrayList<ServiceCategoryDataPojo> filteredList = new ArrayList<>();

                        for (ServiceCategoryDataPojo service_search : service_modals) {

//                            if (service_search.getService_location().toLowerCase().contains(charString)
//                                    || service_search.getService_name().toLowerCase().contains(charString)) {
//
//                                filteredList.add(service_search);
//                            }
                        }

                        mFilteredList = filteredList;
                    }

                    FilterResults filterResults = new FilterResults();
                    filterResults.values = mFilteredList;
                    return filterResults;
                }

                @Override
                protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
  //                  mFilteredList = (ArrayList<Service_Modal>) filterResults.values;
                    notifyDataSetChanged();
                }
            };}

        public class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            TextView service_prod_name;
            ImageView service_prod_img;
            Context mcontext;
            List<ServiceCategoryDataPojo> userList;

            public MyHolder(View itemView, Context mcontext, List<ServiceCategoryDataPojo> userList) {
                super(itemView);

                this.mcontext = mcontext;
                this.userList = userList;
                service_prod_name = (TextView) itemView.findViewById(R.id.service_prod_name);
                service_prod_img = (ImageView) itemView.findViewById(R.id.service_prod_img);
                service_prod_name.setOnClickListener(this);
                service_prod_img.setOnClickListener(this);
            }

            @Override
            public void onClick(View view) {
                int position = getAdapterPosition();
                final ServiceCategoryDataPojo product_model = this.userList.get(position);

                Intent intent = new Intent(mcontext, SubcategoryList.class);
                intent.putExtra("list", product_model.getSubcategory());
                mcontext.startActivity(intent);

//                final Dialog dialog = new Dialog(mcontext);
//                dialog.setTitle("Select service Type...");
//                dialog.setContentView(R.layout.service_dialog);
//                final RadioGroup service_type_rg = dialog.findViewById(R.id.service_type_rg);
//                final RadioButton home_service_rb = dialog.findViewById(R.id.home_service_rb);
//                final RadioButton sharing_service_rb = dialog.findViewById(R.id.sharing_service_rb);
//
//                Button service_btnSubmit = (Button) dialog.findViewById(R.id.service_btnSubmit);
//                service_btnSubmit.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//
//                        if (home_service_rb.isChecked()) {
//
//                            Intent intent = new Intent(mcontext, SubcategoryList.class);
//                            intent.putExtra("service_id", product_model.getService_id());
//                            mcontext.startActivity(intent);
//                            dialog.dismiss();
//
//                        } else if (sharing_service_rb.isChecked()) {
//                            Intent intents = new Intent(mcontext, NearByServiceCenter_Activity.class);
//                            mcontext.startActivity(intents);
//                            dialog.dismiss();
//
//                        }
//                    }
//                });
//
//                dialog.show();
            }}}
