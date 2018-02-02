package com.winklix.indu.healthcareapp.activities;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.winklix.indu.healthcareapp.R;
import com.winklix.indu.healthcareapp.adapters.Doctor_Adapter;
import com.winklix.indu.healthcareapp.adapters.SharingServiceAdapter;
import com.winklix.indu.healthcareapp.api.RestClient;
import com.winklix.indu.healthcareapp.pojo.LocationServiceDataPojo;
import com.winklix.indu.healthcareapp.pojo.LocationServicePojo;
import com.winklix.indu.healthcareapp.testlist.MyDialog;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class NearByServiceCenter_Activity extends AppCompatActivity implements View.OnClickListener {

    Context mcontext;
    private SharingServiceAdapter adapter;
    RecyclerView sharing_dr_recy_view;
    private RecyclerView.LayoutManager mLayoutManager;
    private String cat_id,subCat_id,latitude,longitude,days,price;
    private ArrayList<LocationServiceDataPojo> list;
    private MyDialog myDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_near_by_service_center_);

        sharing_dr_recy_view = (RecyclerView) findViewById(R.id.sharing_dr_recy_view);
        sharing_dr_recy_view.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(NearByServiceCenter_Activity.this);
        sharing_dr_recy_view.setLayoutManager(mLayoutManager);

        cat_id = getIntent().getStringExtra("cat_id");
        subCat_id = getIntent().getStringExtra("subCat_id");
        latitude = getIntent().getStringExtra("latitude");
        longitude = getIntent().getStringExtra("longitude");
        days = getIntent().getStringExtra("days");
        price = getIntent().getStringExtra("price");

        list = new ArrayList<LocationServiceDataPojo>();

        myDialog = new MyDialog(this);

        mcontext = NearByServiceCenter_Activity.this;

        LocationService();
    }

    public void onClick(View view) {
        //mcontext.startActivity(new Intent(mcontext, ServiceLocation_Activity.class));

        final Dialog dialog = new Dialog(mcontext);
        dialog.setContentView(R.layout.payment_popup);
        dialog.setTitle("Our Services Availabel 24*7");

        Button online_pay_btn = (Button) dialog.findViewById(R.id.online_pay_btn);

        online_pay_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mcontext,PaymentActivity.class);
                intent.putExtra("price", price);
                intent.putExtra("days",days);
                startActivity(intent);

                dialog.dismiss();
            }
        });



        Button cash_pay_btn = (Button) dialog.findViewById(R.id.cash_pay_btn);

        cash_pay_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mcontext, "Thanks", Toast.LENGTH_SHORT).show();
                mcontext.startActivity(new Intent(mcontext, ServiceLocation_Activity.class));
                dialog.dismiss();

            }
        });

        dialog.show();
    }

    private void LocationService(){

        myDialog.ShowProgressDialog();

        RestClient.get().LocationService(cat_id, subCat_id,"28.6276", "77.2784", new Callback<LocationServicePojo>() {
            @Override
            public void success(LocationServicePojo locationServicePojo, Response response) {

                list.addAll(locationServicePojo.getData());

                adapter = new SharingServiceAdapter(NearByServiceCenter_Activity.this,list);
                sharing_dr_recy_view.setAdapter(adapter);

                myDialog.CancelProgressDialog();

                System.out.println("xxx success");
            }

            @Override
            public void failure(RetrofitError error) {

                System.out.println("xxx faill");

                myDialog.CancelProgressDialog();

            }
        });
    }

}
