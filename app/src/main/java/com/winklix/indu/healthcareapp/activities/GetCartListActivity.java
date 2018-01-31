package com.winklix.indu.healthcareapp.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.winklix.indu.healthcareapp.Health_Api;
import com.winklix.indu.healthcareapp.Health_Shared_Pref;
import com.winklix.indu.healthcareapp.R;
import com.winklix.indu.healthcareapp.adapters.GetCartAdapter;
import com.winklix.indu.healthcareapp.adapters.ProductAdapter;
import com.winklix.indu.healthcareapp.api.RestClient;
import com.winklix.indu.healthcareapp.modals.Product_Modal;
import com.winklix.indu.healthcareapp.pojo.GetCartDataPojo;
import com.winklix.indu.healthcareapp.pojo.GetCartPojo;
import com.winklix.indu.healthcareapp.testlist.MyDialog;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Administrator on 1/18/2018.
 */

public class GetCartListActivity extends AppCompatActivity {

    private GetCartAdapter adapter;
    RecyclerView cart_recycle_view;
    public LinearLayoutManager layoutManager;
    private ArrayList<GetCartDataPojo> product_list;
    private MyDialog dialog;
    Health_Shared_Pref health_shared_pref;
    private String User_id;
    private TextView place_order;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_list);

        product_list = new ArrayList<GetCartDataPojo>();

        cart_recycle_view = (RecyclerView) findViewById(R.id.cartList_recycle_view);

        dialog = new MyDialog(this);

        health_shared_pref = new Health_Shared_Pref(this);

        place_order = (TextView) findViewById(R.id.place_order);

        User_id = health_shared_pref.getPrefranceStringValue(Health_Api.USERID);

        GetCartList();

    }

    private void GetCartList(){

        dialog.ShowProgressDialog();

        RestClient.get().GetCart(User_id, new Callback<GetCartPojo>() {
            @Override
            public void success(GetCartPojo getCartPojo, Response response) {

                if (getCartPojo.getData().size() > 0){
                    place_order.setVisibility(View.VISIBLE);
                    product_list.addAll(getCartPojo.getData());

                    layoutManager = new LinearLayoutManager(GetCartListActivity.this);
                    cart_recycle_view.setLayoutManager(layoutManager);

                    adapter = new GetCartAdapter(GetCartListActivity.this, product_list);
                    cart_recycle_view.setAdapter(adapter);
                    cart_recycle_view.setItemAnimator(new DefaultItemAnimator());
                } else {
                    place_order.setVisibility(View.GONE);
                }



                dialog.CancelProgressDialog();

                System.out.println("xx success");
            }

            @Override
            public void failure(RetrofitError error) {
                System.out.println("xxx faill");

                dialog.CancelProgressDialog();
            }
        });
    }
}
