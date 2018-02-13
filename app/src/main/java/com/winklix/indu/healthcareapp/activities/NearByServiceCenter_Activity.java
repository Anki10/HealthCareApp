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

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.winklix.indu.healthcareapp.R;
import com.winklix.indu.healthcareapp.adapters.Doctor_Adapter;
import com.winklix.indu.healthcareapp.adapters.SharingServiceAdapter;
import com.winklix.indu.healthcareapp.api.RestClient;
import com.winklix.indu.healthcareapp.pojo.HomevisitorlocationDataPojo;
import com.winklix.indu.healthcareapp.pojo.HomevisitorlocationPojo;
import com.winklix.indu.healthcareapp.pojo.LocationServiceDataPojo;
import com.winklix.indu.healthcareapp.pojo.LocationServicePojo;
import com.winklix.indu.healthcareapp.testlist.MyDialog;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class NearByServiceCenter_Activity extends AppCompatActivity implements OnMapReadyCallback {

    Context mcontext;
    private SharingServiceAdapter adapter;
    RecyclerView sharing_dr_recy_view;
    private RecyclerView.LayoutManager mLayoutManager;
    private String cat_id,subCat_id,latitude,longitude,days,price;
    private ArrayList<LocationServiceDataPojo> list;
    private ArrayList<HomevisitorlocationDataPojo> homevisitlist;
    private MyDialog myDialog;
    private GoogleMap mMap;
    private String latitude_s,longitude_s,from;
    private String currentLatitude,currentLongitude;


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
        currentLatitude = getIntent().getStringExtra("currentLatitude");
        currentLongitude = getIntent().getStringExtra("currentLongitude");
        days = getIntent().getStringExtra("days");
        price = getIntent().getStringExtra("price");
        from = getIntent().getStringExtra("from");

        list = new ArrayList<LocationServiceDataPojo>();
        homevisitlist = new ArrayList<HomevisitorlocationDataPojo>();


        myDialog = new MyDialog(this);

        mcontext = NearByServiceCenter_Activity.this;

        if (from.equalsIgnoreCase("homevisit")){
            HomeVisitLocation();
        } else {
            LocationService();
        }




        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    public void onClickEvent(View view) {
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

        RestClient.get().LocationService(cat_id, subCat_id,currentLatitude, currentLongitude, new Callback<LocationServicePojo>() {
            @Override
            public void success(LocationServicePojo locationServicePojo, Response response) {

                try {
                    list.addAll(locationServicePojo.getData());

                    for (int i= 0; i< list.size();i++){
                        latitude_s = list.get(i).getLatitude();
                        longitude_s = list.get(i).getLongitude();

                        addMarkerToMap(latitude_s,longitude_s,"hello");
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }

//                adapter = new SharingServiceAdapter(NearByServiceCenter_Activity.this,list);
//                sharing_dr_recy_view.setAdapter(adapter);


                System.out.println("xxx success");
            }

            @Override
            public void failure(RetrofitError error) {

                System.out.println("xxx faill");

            }
        });
    }

    private void HomeVisitLocation(){
        RestClient.get().HomeVisitLocation(cat_id, latitude, longitude, new Callback<HomevisitorlocationPojo>() {
            @Override
            public void success(HomevisitorlocationPojo homevisitorlocationPojo, Response response) {
                try {
                    homevisitlist.addAll(homevisitorlocationPojo.getData());

                    for (int i= 0; i< list.size();i++){
                        latitude_s = homevisitlist.get(i).getLatitude();
                        longitude_s = homevisitlist.get(i).getLongitude();

                        addMarkerToMap(latitude_s,longitude_s,"hello");
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }

//                adapter = new SharingServiceAdapter(NearByServiceCenter_Activity.this,list);
//                sharing_dr_recy_view.setAdapter(adapter);


                System.out.println("xxx success");
            }

            @Override
            public void failure(RetrofitError error) {
                System.out.println("xx faill");
            }
        });
    }

    public void addMarkerToMap(String latitude, String longitude, String title)
    {
        double lat = Double.parseDouble(latitude);
        double lng = Double.parseDouble(longitude);

        MarkerOptions marker = new MarkerOptions().position(new LatLng(lat, lng))
                .title("Employee Time : "+title);
        marker.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));

        CameraPosition cameraPosition = new CameraPosition.Builder().target(
                new LatLng(lat, lng)).zoom(16).build();

        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        mMap.addMarker(marker);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
//        // create marker
//        MarkerOptions marker = new MarkerOptions().position(new LatLng(28.6276, 77.2784)).title("Employee Time : "+"");
//
//        googleMap.getUiSettings().setZoomControlsEnabled(true);
//        googleMap.getUiSettings().setZoomGesturesEnabled(true);
//
//        CameraPosition cameraPosition = new CameraPosition.Builder().target(
//                new LatLng(28.6276, 77.2784)).zoom(16).build();
//
//        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
//
//// adding marker
//        googleMap.addMarker(marker);
    }
}
