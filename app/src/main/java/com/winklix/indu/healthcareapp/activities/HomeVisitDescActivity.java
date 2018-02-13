package com.winklix.indu.healthcareapp.activities;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.winklix.indu.healthcareapp.R;
import com.winklix.indu.healthcareapp.api.RestClient;
import com.winklix.indu.healthcareapp.pojo.HomeVisitTypeListingDataPojo;
import com.winklix.indu.healthcareapp.pojo.HomevisitTypeListingPojo;
import com.winklix.indu.healthcareapp.testlist.MyDialog;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by dell on 09-02-2018.
 */

public class HomeVisitDescActivity extends AppCompatActivity {

    Button patient_silver,patient_platinum,patient_gold;
    private MyDialog dialog;
    private ArrayList<HomeVisitTypeListingDataPojo> listinglist;
    private String service_name,cat_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_desc);

        patient_silver = (Button) findViewById(R.id.patient_silver);
        patient_platinum = (Button) findViewById(R.id.patient_platinum);
        patient_gold = (Button) findViewById(R.id.patient_gold);

        patient_silver.setVisibility(View.INVISIBLE);
        patient_platinum.setVisibility(View.INVISIBLE);
        patient_gold.setVisibility(View.INVISIBLE);

        service_name = getIntent().getStringExtra("service_name");
        cat_id = getIntent().getStringExtra("cat_id");

        dialog = new MyDialog(HomeVisitDescActivity.this);

        listinglist = new ArrayList<HomeVisitTypeListingDataPojo>();

        getServiceTypeList();

    }

    private void getServiceTypeList(){
         dialog.ShowProgressDialog();

        RestClient.get().homevisitTypeListing(cat_id, service_name, new Callback<HomevisitTypeListingPojo>() {
            @Override
            public void success(HomevisitTypeListingPojo homevisitTypeListingPojo, Response response) {
                listinglist.addAll(homevisitTypeListingPojo.getData());

                dialog.CancelProgressDialog();

                for (int i =0; i< listinglist.size();i++){
                    if (listinglist.get(i).getService_type().equalsIgnoreCase("1")){
                      patient_platinum.setVisibility(View.VISIBLE);
                        final int finalI = i;
                        final int finalI4 = i;
                        patient_platinum.setOnClickListener(new View.OnClickListener() {
                          @Override
                          public void onClick(View v) {
                              dialogList(listinglist.get(finalI).getService_description(),listinglist.get(finalI).getService_price(),listinglist.get(finalI4).getService_type());
                          }
                      });
                    } else if (listinglist.get(i).getService_type().equalsIgnoreCase("2")){
                        patient_gold.setVisibility(View.VISIBLE);
                        final int finalI2 = i;
                        final int finalI3 = i;
                        patient_gold.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialogList(listinglist.get(finalI2).getService_description(),listinglist.get(finalI2).getService_price(),listinglist.get(finalI3).getService_type());
                            }
                        });
                    } else if (listinglist.get(i).getService_type().equalsIgnoreCase("3")) {
                        patient_silver.setVisibility(View.VISIBLE);
                        final int finalI1 = i;
                        final int finalI5 = i;
                        patient_silver.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialogList(listinglist.get(finalI1).getService_description(),listinglist.get(finalI1).getService_price(),listinglist.get(finalI5).getService_type());
                            }
                        });
                    }
                }
            }

            @Override
            public void failure(RetrofitError error) {
                dialog.CancelProgressDialog();

            }
        });
    }

    private void dialogList(String desc, final String price, final String servic_type){
        final Dialog dialog1 = new Dialog(HomeVisitDescActivity.this);
        dialog1.setTitle("Select service Type...");
        dialog1.setContentView(R.layout.service_type_dialog);
        TextView tv_title = (TextView) dialog1.findViewById(R.id.tv_service_type);
        final TextView tv_description = (TextView) dialog1.findViewById(R.id.tv_service_description);
        final TextView tv_price = (TextView) dialog1.findViewById(R.id.tv_servicePrice);
        final Button service_type_Submit = (Button) dialog1.findViewById(R.id.service_type_btnSubmit);


        tv_title.setText("Your Service Type : "+ service_name);

        tv_description.setText(desc);
        tv_price.setText(price);

        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int width = (int) ((int)displaymetrics.widthPixels * 0.9);
        int height = (int) ((int)displaymetrics.heightPixels * 0.4);
        dialog1.getWindow().setLayout(width, height);
        dialog1.show();

        dialog.CancelProgressDialog();

        service_type_Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeVisitDescActivity.this,ServicePackageActivity.class);
                intent.putExtra("cat_id",cat_id);
                intent.putExtra("subCat_id","");
                intent.putExtra("service_type",servic_type);
                intent.putExtra("from","homevisit");
                intent.putExtra("price",price);
                startActivity(intent);
                dialog1.dismiss();
            }
        });
    }
}
