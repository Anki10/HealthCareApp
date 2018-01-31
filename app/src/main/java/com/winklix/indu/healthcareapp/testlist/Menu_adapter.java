package com.winklix.indu.healthcareapp.testlist;

import android.content.Context;
import android.content.SharedPreferences;
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

import java.util.ArrayList;


public class Menu_adapter extends RecyclerView.Adapter<Menu_adapter.MyHolder> {

    private ArrayList<MenuHolder> userList;
    private Context mcontext;
    String ratedValue,user_id,name_str,email_str;

    public String api_parameters;


    public class MyHolder extends RecyclerView.ViewHolder {
        TextView text;
        ImageView img;
        LinearLayout ll_navigation_menu;


        public MyHolder(View itemView) {
            super(itemView);

            text = (TextView) itemView.findViewById(R.id.text);
            img = (ImageView) itemView.findViewById(R.id.drawer_item_icon);
            ll_navigation_menu = (LinearLayout) itemView.findViewById(R.id.ll_navigation_menu);

            //led_rate_pref = new Led_Rate_Pref(mcontext);
            //led_rate_pref.setPrefranceValue(Rate_Api.IsRatedIn,true);
        }
    }

    public Menu_adapter(Context montext, ArrayList<MenuHolder> msgArrayList) {

        this.mcontext = montext;
        this.userList = msgArrayList;

    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View myview = LayoutInflater.from(mcontext).inflate(R.layout.navigation_menu, parent, false);

        return new MyHolder(myview);
    }

    private boolean isFirstTime()
    {
        SharedPreferences preferences = mcontext.getSharedPreferences("", Context.MODE_PRIVATE);

        boolean ranBefore = preferences.getBoolean("RanBefore", false);
        if (!ranBefore) {
            // first time
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("RanBefore", true);
            editor.commit();
        }
        return !ranBefore;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, final int position) {
        final MenuHolder mOdelq = userList.get(position);

        holder.text.setText(mOdelq.getTitleName());
        Picasso.with(mcontext).load(userList.get(position).getIcons()).into(holder.img);
        holder.ll_navigation_menu.setOnClickListener((BuyDashboardActivity) mcontext);


    }
    @Override
    public int getItemCount() {
        return userList.size();
    }

    @Override
    public int getItemViewType(int position) {


        return super.getItemViewType(position);
    }


}
