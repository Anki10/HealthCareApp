package com.winklix.indu.healthcareapp.activities;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.winklix.indu.healthcareapp.R;
import com.winklix.indu.healthcareapp.adapters.HomeVisitAdapter;
import com.winklix.indu.healthcareapp.adapters.HomeVisitDiseaseAdapter;
import com.winklix.indu.healthcareapp.api.RestClient;
import com.winklix.indu.healthcareapp.modals.Service_Modal;
import com.winklix.indu.healthcareapp.pojo.HomeVisitDiseasesPojo;
import com.winklix.indu.healthcareapp.testlist.MyDialog;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by dell on 09-02-2018.
 */

public class HomeVisitDiseasesActivity extends AppCompatActivity implements View.OnClickListener {

    private MyDialog myDialog;
    RecyclerView service_recy_view;
    Context context;
    //Spinner location_spinner;
    private HomeVisitDiseaseAdapter adapter;
    private List<Service_Modal> service_modals;
    LinearLayoutManager layoutManager;
    private String cat_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_visit_diseases);

        myDialog = new MyDialog(HomeVisitDiseasesActivity.this);

        service_recy_view = (RecyclerView) findViewById(R.id.homevisit_disease_recy_view);

        cat_id = getIntent().getStringExtra("cat_id");

        getHomeVisitDiseaselist();

    }

    private void getHomeVisitDiseaselist(){
        myDialog.ShowProgressDialog();
        RestClient.get().homeVisitDiseases(cat_id, new Callback<HomeVisitDiseasesPojo>() {
            @Override
            public void success(HomeVisitDiseasesPojo homeVisitDiseasesPojo, Response response) {

                layoutManager = new GridLayoutManager(context,2);
                service_recy_view.setLayoutManager(layoutManager);

                adapter = new HomeVisitDiseaseAdapter(HomeVisitDiseasesActivity.this, homeVisitDiseasesPojo.getData(),cat_id);
                service_recy_view.setAdapter(adapter);
                service_recy_view.setItemAnimator(new DefaultItemAnimator());

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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.product_ll:

        }
    }
}
