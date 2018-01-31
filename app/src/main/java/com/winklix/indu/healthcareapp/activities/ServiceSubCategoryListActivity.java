package com.winklix.indu.healthcareapp.activities;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.winklix.indu.healthcareapp.R;
import com.winklix.indu.healthcareapp.adapters.ServiceSubCategoryListAdapter;
import com.winklix.indu.healthcareapp.adapters.SubCategoryAdapter;
import com.winklix.indu.healthcareapp.api.RestClient;
import com.winklix.indu.healthcareapp.pojo.ServiceNameDataPojo;
import com.winklix.indu.healthcareapp.pojo.ServiceNamePojo;
import com.winklix.indu.healthcareapp.pojo.SubcategoryPojo;
import com.winklix.indu.healthcareapp.testlist.MyDialog;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by dell on 23-01-2018.
 */

public class ServiceSubCategoryListActivity extends AppCompatActivity implements View.OnClickListener {

    RecyclerView Subcategory_recycle_view;
    ServiceSubCategoryListAdapter adapter;
    public LinearLayoutManager layoutManager;
    private ArrayList<ServiceNameDataPojo> sub_category_list;
    private String cat_id,subCat_id;
    private MyDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subcategory_list);

        cat_id = getIntent().getStringExtra("cat_id");
        subCat_id = getIntent().getStringExtra("subCat_id");

        dialog = new MyDialog(ServiceSubCategoryListActivity.this);

        sub_category_list = new ArrayList<>();

        Subcategory_recycle_view = (RecyclerView) findViewById(R.id.service_sub_cat_recycler_view);

        ServiceSubCategoryList();

    }


    private void ServiceSubCategoryList(){

        dialog.ShowProgressDialog();

        RestClient.get().ServiceName(cat_id, subCat_id, new Callback<ServiceNamePojo>() {
            @Override
            public void success(ServiceNamePojo serviceNamePojo, Response response) {

                sub_category_list.addAll(serviceNamePojo.getData());

                layoutManager = new GridLayoutManager(ServiceSubCategoryListActivity.this,3);
                Subcategory_recycle_view.setLayoutManager(layoutManager);

                adapter = new ServiceSubCategoryListAdapter(ServiceSubCategoryListActivity.this, sub_category_list);
                Subcategory_recycle_view.setAdapter(adapter);
                Subcategory_recycle_view.setItemAnimator(new DefaultItemAnimator());

                dialog.CancelProgressDialog();

                System.out.println("xxxx success");
            }

            @Override
            public void failure(RetrofitError error) {
                System.out.println("xxxx faill");

                dialog.CancelProgressDialog();
            }
        });
    }

    private void SubDialog(){
        final Dialog dialog = new Dialog(ServiceSubCategoryListActivity.this);
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

                    Intent intent = new Intent(ServiceSubCategoryListActivity.this, ServiceDescActivity.class);
                    intent.putExtra("cat_id", cat_id);
                    intent.putExtra("subCat_id", subCat_id);
                    intent.putExtra("from","HomeVisit");
                    startActivity(intent);
                    dialog.dismiss();

                } else if (sharing_service_rb.isChecked()) {
                    Intent intents = new Intent(ServiceSubCategoryListActivity.this, ServiceDescActivity.class);
                    intents.putExtra("cat_id", cat_id);
                    intents.putExtra("subCat_id", subCat_id);
                    intents.putExtra("from","Sharing");
                    startActivity(intents);
                    dialog.dismiss();

                }
            }
        });

        dialog.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.category_ll:
                SubDialog();
        }
    }
}
