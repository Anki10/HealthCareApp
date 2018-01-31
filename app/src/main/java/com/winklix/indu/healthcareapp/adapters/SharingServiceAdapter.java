package com.winklix.indu.healthcareapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.winklix.indu.healthcareapp.R;
import com.winklix.indu.healthcareapp.pojo.LocationServiceDataPojo;

import java.util.ArrayList;

/**
 * Created by Administrator on 1/16/2018.
 */

public class SharingServiceAdapter extends RecyclerView.Adapter<SharingServiceAdapter.MYHolder> {

    private Context mcontext;
    private ArrayList<LocationServiceDataPojo> locationlist;

    public SharingServiceAdapter(Context montext,ArrayList<LocationServiceDataPojo> list){
        this.mcontext = montext;
        this.locationlist = list;
    }


    @Override
    public MYHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.sharing_service_data, parent, false);
        // set the view's size, margins, paddings and layout parameters
        MYHolder vh = new MYHolder(v); // pass the view to View Holder
        return vh;
    }

    @Override
    public void onBindViewHolder(MYHolder holder, int position) {
        LocationServiceDataPojo pojo = locationlist.get(position);

        holder.clinic_name.setText(pojo.getClinic_name());
        holder.clinic_address.setText(pojo.getAddress());

    }

    @Override
    public int getItemCount() {
        return locationlist.size();
    }

    public class MYHolder extends RecyclerView.ViewHolder {

        private TextView clinic_name,clinic_address;

        public MYHolder(View itemView) {
            super(itemView);

            clinic_name = (TextView) itemView.findViewById(R.id.tv_clinic_name);
            clinic_address = (TextView) itemView.findViewById(R.id.tv_clinic_address);
        }
    }
}
