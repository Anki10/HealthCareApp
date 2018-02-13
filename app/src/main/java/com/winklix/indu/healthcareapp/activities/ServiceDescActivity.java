package com.winklix.indu.healthcareapp.activities;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.Button;
import android.widget.TextView;

import com.winklix.indu.healthcareapp.Health_Shared_Pref;
import com.winklix.indu.healthcareapp.R;
import com.winklix.indu.healthcareapp.adapters.Doctor_Adapter;
import com.winklix.indu.healthcareapp.api.RestClient;
import com.winklix.indu.healthcareapp.modals.Doctor_Modal;
import com.winklix.indu.healthcareapp.pojo.ServiceDescriptionDataPojo;
import com.winklix.indu.healthcareapp.pojo.ServiceDescriptionPojo;
import com.winklix.indu.healthcareapp.pojo.ServiceListingDataPojo;
import com.winklix.indu.healthcareapp.pojo.ServiceListingPojo;
import com.winklix.indu.healthcareapp.testlist.MyDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit.Callback;
import retrofit.RetrofitError;

public class ServiceDescActivity extends AppCompatActivity {


    Button patient_silver, patient_platinum, patient_gold;
    Context context;
    private Doctor_Adapter doctor_adapter;
    private List<Doctor_Modal> doctor_modals;
    LinearLayoutManager layoutManager;
    ProgressDialog pd;
    String cat_id, subCat_id, from,service_name;
    RecyclerView dr_recy_view;
    Health_Shared_Pref health_shared_pref;
    private MyDialog dialog;
    private ArrayList<ServiceListingDataPojo> servicelist;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_desc);

        patient_silver = (Button) findViewById(R.id.patient_silver);
        patient_platinum = (Button) findViewById(R.id.patient_platinum);
        patient_gold = (Button) findViewById(R.id.patient_gold);

        servicelist = new ArrayList<>();

        dialog = new MyDialog(this);

        cat_id = getIntent().getStringExtra("cat_id");
        subCat_id = getIntent().getStringExtra("subCat_id");
        from = getIntent().getStringExtra("from");
        service_name = getIntent().getStringExtra("service_name");

        patient_silver.setVisibility(View.INVISIBLE);
        patient_platinum.setVisibility(View.INVISIBLE);
        patient_gold.setVisibility(View.INVISIBLE);

        getServiceListing();

//        patient_silver.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                serviceDescription("3", "SILVER");
//            }
//        });
//
//        patient_platinum.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                serviceDescription("1", "PLATINUM");
//            }
//        });
//
//        patient_gold.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                serviceDescription("2", "GOLD");
//            }
//        });


        context = ServiceDescActivity.this;
        health_shared_pref = new Health_Shared_Pref(context);

        Bundle bundle = getIntent().getExtras();

        //service_id_str = health_shared_pref.getPrefranceStringValue(Health_Api.SERVICE_ID);

        dr_recy_view = (RecyclerView) findViewById(R.id.dr_recy_view);

        doctor_modals = new ArrayList<>();
        //    getCategoryFromDB(service_id_str);


        layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        dr_recy_view.setLayoutManager(layoutManager);

        /*layoutManager = new GridLayoutManager(context,2);
        service_recycle_view.setLayoutManager(layoutManager);*/


        doctor_adapter = new Doctor_Adapter(context, doctor_modals);
        dr_recy_view.setAdapter(doctor_adapter);
        dr_recy_view.setItemAnimator(new DefaultItemAnimator());

        dr_recy_view.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

                if (layoutManager.findLastCompletelyVisibleItemPosition() == doctor_modals.size() - 1) {
                    //  getCategoryFromDB(ledModals_list.get(ledModals_list.size() - 1).getId());
                }

            }
        });


    }


//    private void serviceDescription(final String service_type, final String service_name) {
//        dialog.ShowProgressDialog();
//        RestClient.get().ServiceDescription(cat_id, subCat_id, service_type, new Callback<ServiceDescriptionPojo>() {
//            @Override
//            public void success(final ServiceDescriptionPojo serviceDescriptionPojo, retrofit.client.Response response) {
//
//                final Dialog dialog1 = new Dialog(ServiceDescActivity.this);
//                dialog1.setTitle("Select service Type...");
//                dialog1.setContentView(R.layout.service_type_dialog);
//                TextView tv_title = (TextView) dialog1.findViewById(R.id.tv_service_type);
//                final TextView tv_description = (TextView) dialog1.findViewById(R.id.tv_service_description);
//                final TextView tv_price = (TextView) dialog1.findViewById(R.id.tv_servicePrice);
//                final Button service_type_Submit = (Button) dialog1.findViewById(R.id.service_type_btnSubmit);
//
//
//                tv_title.setText("Your Service Type : " + service_name);
//
//                tv_description.setText(serviceDescriptionPojo.getData().getService_description());
//                tv_price.setText(serviceDescriptionPojo.getData().getService_price());
//
//                DisplayMetrics displaymetrics = new DisplayMetrics();
//                getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
//                int width = (int) ((int) displaymetrics.widthPixels * 0.9);
//                int height = (int) ((int) displaymetrics.heightPixels * 0.4);
//                dialog1.getWindow().setLayout(width, height);
//                dialog1.show();
//
//                dialog.CancelProgressDialog();
//
//                service_type_Submit.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Intent intent = new Intent(ServiceDescActivity.this, ServicePackageActivity.class);
//                        intent.putExtra("cat_id", cat_id);
//                        intent.putExtra("subCat_id", subCat_id);
//                        intent.putExtra("service_type", service_type);
//                        intent.putExtra("from", from);
//                        intent.putExtra("price", serviceDescriptionPojo.getData().getService_price());
//                        startActivity(intent);
//                        dialog1.dismiss();
//                    }
//                });
//
//            }
//
//            @Override
//            public void failure(RetrofitError error) {
//                dialog.CancelProgressDialog();
//            }
//        });
//
//    }

    private void getCategoryFromDB(final String serviceid) {
        final AsyncTask<String, Void, Void> asyncTaskprog = new AsyncTask<String, Void, Void>() {

            @Override

            protected Void doInBackground(String... Ids) {

                OkHttpClient clientp = new OkHttpClient();

                okhttp3.RequestBody body = new FormBody.Builder().
                        add("serviceid", serviceid)
                        .build();

                Request requestp = new Request.Builder().post(body)
                        .url("http://rohit.winklix.com/indu/app/drlist.php")
                        .build();

                try {
                    Response responsep = clientp.newCall(requestp).execute();


                    if (responsep.body().toString() == null) {
                        // no_his_tv.setVisibility(View.VISIBLE);
                        return null;
                    } else {
                        JSONObject jsonObject = new JSONObject(responsep.body().string());
                        JSONArray jsonarray1 = jsonObject.getJSONArray("data"); //dta

                        for (int i = 0; i < jsonarray1.length(); i++) {

                            JSONObject objectp = jsonarray1.getJSONObject(i);

                            Doctor_Modal doctor_modals = new Doctor_Modal(objectp.getString("dr_id"),
                                    objectp.getString("serviceid"), objectp.getString("dr_name"),
                                    objectp.getString("dr_email"), objectp.getString("dr_phone"),
                                    objectp.getString("dr_qualification"), objectp.getString("dr_specialization"),
                                    objectp.getString("dr_experience"), objectp.getString("dr_available"));

                            ServiceDescActivity.this.doctor_modals.add(doctor_modals);

                        }
                    }
                } catch (JSONException | IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                pd = new ProgressDialog(context);
                pd.setMessage("Please wait...");
                pd.setCancelable(false);
                pd.setCanceledOnTouchOutside(false);
                pd.show();
            }


            @Override
            protected void onPostExecute(Void aVoid) {
                doctor_adapter.notifyDataSetChanged();
                pd.dismiss();
            }
        };

        asyncTaskprog.execute("serviceid");
    }

    private void getServiceListing(){
        dialog.ShowProgressDialog();
        RestClient.get().ServiceListing(cat_id, subCat_id, service_name, new Callback<ServiceListingPojo>() {
            @Override
            public void success(ServiceListingPojo serviceListingPojo, retrofit.client.Response response) {
                servicelist.addAll(serviceListingPojo.getData());

                dialog.CancelProgressDialog();

                for (int i =0; i< servicelist.size();i++){
                    if (servicelist.get(i).getService_type().equalsIgnoreCase("1")){
                        patient_platinum.setVisibility(View.VISIBLE);
                        final int finalI = i;
                        final int finalI4 = i;
                        patient_platinum.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialogList(servicelist.get(finalI).getService_description(),servicelist.get(finalI).getService_price(),servicelist.get(finalI4).getService_type());
                            }
                        });
                    } else if (servicelist.get(i).getService_type().equalsIgnoreCase("2")){
                        patient_gold.setVisibility(View.VISIBLE);
                        final int finalI2 = i;
                        final int finalI3 = i;
                        patient_gold.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialogList(servicelist.get(finalI2).getService_description(),servicelist.get(finalI2).getService_price(),servicelist.get(finalI3).getService_type());
                            }
                        });
                    } else if (servicelist.get(i).getService_type().equalsIgnoreCase("3")) {
                        patient_silver.setVisibility(View.VISIBLE);
                        final int finalI1 = i;
                        final int finalI5 = i;
                        patient_silver.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialogList(servicelist.get(finalI1).getService_description(),servicelist.get(finalI1).getService_price(),servicelist.get(finalI5).getService_type());
                            }
                        });
                    }
                }
            }

            @Override
            public void failure(RetrofitError error) {

                System.out.println("xxx faill");

            }
        });
    }

    private void dialogList(String desc, final String price, final String servic_type){
        final Dialog dialog1 = new Dialog(ServiceDescActivity.this);
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
                Intent intent = new Intent(ServiceDescActivity.this,ServicePackageActivity.class);
                intent.putExtra("cat_id",cat_id);
                intent.putExtra("subCat_id",subCat_id);
                intent.putExtra("service_type",servic_type);
                intent.putExtra("from","sharing");
                intent.putExtra("price",price);
                startActivity(intent);
                dialog1.dismiss();
            }
        });
    }

}