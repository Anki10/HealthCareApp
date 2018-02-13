package com.winklix.indu.healthcareapp.activities;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.winklix.indu.healthcareapp.R;
import com.winklix.indu.healthcareapp.api.RestClient;
import com.winklix.indu.healthcareapp.pojo.LocationServicePojo;
import com.winklix.indu.healthcareapp.pojo.ServiceListingDataPojo;
import com.winklix.indu.healthcareapp.pojo.ServiceListingPojo;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by dell on 29-01-2018.
 */

public class ServicePackageActivity extends AppCompatActivity implements View.OnClickListener, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {

    private Button btn_1Day, btn_2day, btn_5day, btn_10days, btn_15days, btn_20days;
    private String cat_id, subCat_id, service_type, from, price;
    //Define a request code to send to Google Play services
    private final static int CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;
    private GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;
    private double currentLatitude;
    private double currentLongitude;
    private ArrayList<ServiceListingDataPojo> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_packages);

        list = new ArrayList<>();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                // The next two lines tell the new client that “this” current class will handle connection stuff
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                //fourth line adds the LocationServices API endpoint from GooglePlayServices
                .addApi(LocationServices.API)
                .build();

        // Create the LocationRequest object
        mLocationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(10 * 1000)        // 10 seconds, in milliseconds
                .setFastestInterval(1 * 1000);




        btn_1Day = (Button) findViewById(R.id.days_1);
        btn_2day = (Button) findViewById(R.id.days_2);
        btn_5day = (Button) findViewById(R.id.days_5);
        btn_10days = (Button) findViewById(R.id.days_10);
        btn_15days = (Button) findViewById(R.id.days_15);
        btn_20days = (Button) findViewById(R.id.days_20);

        cat_id = getIntent().getStringExtra("cat_id");
        subCat_id = getIntent().getStringExtra("subCat_id");
        service_type = getIntent().getStringExtra("service_type");
        from = getIntent().getStringExtra("from");
        price = getIntent().getStringExtra("price");

        btn_1Day.setOnClickListener(this);
        btn_2day.setOnClickListener(this);
        btn_5day.setOnClickListener(this);
        btn_10days.setOnClickListener(this);
        btn_15days.setOnClickListener(this);
        btn_20days.setOnClickListener(this);

    }

    @Override
    protected void onResume() {
        super.onResume();

        mGoogleApiClient.connect();
    }

    @Override
    protected void onStart() {
        super.onStart();

        mGoogleApiClient.connect();
    }

    @Override
    protected void onPause() {
        super.onPause();

        //Disconnect from API onPause()
        if (mGoogleApiClient.isConnected()) {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
            mGoogleApiClient.disconnect();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.days_1:
                    address("1");
                break;

            case R.id.days_2:
                address("2");
                break;

            case R.id.days_5:
                address("5");
                break;

            case R.id.days_10:
                address("10");
                break;

            case R.id.days_15:
                address("15");
                break;

            case R.id.days_20:
                address("20");
                break;

        }

    }

    private void servicetype(String service_type, final String days) {

        RestClient.get().ServicePrice(cat_id, subCat_id, service_type, new Callback<ServiceListingPojo>() {
            @Override
            public void success(ServiceListingPojo serviceListingPojo, retrofit.client.Response response) {
                try {
                    list.addAll(serviceListingPojo.getData());
                    for (int i=0; i< list.size();i++ ){
                        Intent intent = new Intent(ServicePackageActivity.this, PaymentActivity.class);
                        intent.putExtra("price", list.get(i).getService_price());
                        intent.putExtra("days", days);
                        startActivity(intent);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void failure(RetrofitError error) {
            }
        });
    }

    private void LocationService(String days,String adds) {

        Intent intent = new Intent(ServicePackageActivity.this, NearByServiceCenter_Activity.class);
        intent.putExtra("cat_id", cat_id);
        intent.putExtra("subCat_id", subCat_id);
        intent.putExtra("currentLatitude", String.valueOf(currentLatitude));
        intent.putExtra("currentLongitude",String.valueOf(currentLongitude));
        intent.putExtra("from", from);
        intent.putExtra("days", days);
        intent.putExtra("price", price);
        intent.putExtra("adds", adds);

        startActivity(intent);

    }


    @Override
    public void onLocationChanged(Location location) {
        currentLatitude = location.getLatitude();
        currentLongitude = location.getLongitude();

 //       Toast.makeText(this, currentLatitude + " WORKS " + currentLongitude + "", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Location location = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);

        if (location == null) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, (com.google.android.gms.location.LocationListener) this);

        } else {
            //If everything went fine lets get latitude and longitude
            currentLatitude = location.getLatitude();
            currentLongitude = location.getLongitude();

    //        Toast.makeText(this, currentLatitude + " WORKS " + currentLongitude + "", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        if (connectionResult.hasResolution()) {
            try {
                // Start an Activity that tries to resolve the error
                connectionResult.startResolutionForResult(this, CONNECTION_FAILURE_RESOLUTION_REQUEST);
                    /*
                     * Thrown if Google Play services canceled the original
                     * PendingIntent
                     */
            } catch (IntentSender.SendIntentException e) {
                // Log the error
                e.printStackTrace();
            }
        } else {
                /*
                 * If no resolution is available, display a dialog to the
                 * user with the error.
                 */
            Log.e("Error", "Location services connection failed with code " + connectionResult.getErrorCode());
        }
    }

    private void address(final String days){
        final Dialog dialog = new Dialog(ServicePackageActivity.this);
        dialog.setTitle("Please fill your address...");
        dialog.setContentView(R.layout.address_dialog);

        final EditText ed_add = (EditText) dialog.findViewById(R.id.ed_address);
        Button submit_btn = (Button) dialog.findViewById(R.id.submit_btn);

        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LocationService(days,ed_add.getText().toString());
            }
        });
        dialog.show();
    }
}

