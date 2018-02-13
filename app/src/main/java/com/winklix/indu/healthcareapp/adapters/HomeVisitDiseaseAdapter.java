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
import com.winklix.indu.healthcareapp.activities.HomeVisitDescActivity;
import com.winklix.indu.healthcareapp.modals.Product_Modal;
import com.winklix.indu.healthcareapp.pojo.HomeVisitDiseasesPojo;
import com.winklix.indu.healthcareapp.pojo.HomeVisitDiseasesdataPojo;

import java.util.ArrayList;

/**
 * Created by dell on 09-02-2018.
 */

public class HomeVisitDiseaseAdapter extends RecyclerView.Adapter<HomeVisitDiseaseAdapter.ViewHolder> {

    private ArrayList<HomeVisitDiseasesdataPojo> diseasepojo;
    private Context mContext;
    private String cat_id;

    public HomeVisitDiseaseAdapter(Context context, ArrayList<HomeVisitDiseasesdataPojo> list,String cat_id){
        this.mContext = context;
        this.diseasepojo = list;
        this.cat_id = cat_id;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View myview = LayoutInflater.from(mContext).inflate(R.layout.product_data, parent, false);

        return new ViewHolder(myview);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final HomeVisitDiseasesdataPojo pro_model = diseasepojo.get(position);

        holder.buy_prod_prize.setVisibility(View.GONE);

        holder.buy_prod_name.setText(pro_model.getService_name());
        Picasso.with(mContext).load("http://2040healthcare.com/images/homevisitservice/" +pro_model.getImage())
                .placeholder(R.mipmap.ic_launcher)
                .into(holder.buy_prod_img);

        holder.product_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, HomeVisitDescActivity.class);
                intent.putExtra("service_name",pro_model.getService_name());
                intent.putExtra("cat_id",cat_id);
                mContext.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return diseasepojo.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView buy_prod_name,buy_prod_prize;
        ImageView buy_prod_img;
        private LinearLayout product_ll;

        public ViewHolder(View itemView) {
            super(itemView);
            buy_prod_name = (TextView) itemView.findViewById(R.id.buy_prod_name);
            buy_prod_prize = (TextView) itemView.findViewById(R.id.buy_prod_prize);
            buy_prod_img = (ImageView) itemView.findViewById(R.id.buy_prod_img);

            product_ll = (LinearLayout) itemView.findViewById(R.id.product_ll);
        }
    }
}
