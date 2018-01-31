package com.winklix.indu.healthcareapp.activities;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.winklix.indu.healthcareapp.R;
import com.winklix.indu.healthcareapp.api.RestClient;
import com.winklix.indu.healthcareapp.pojo.LocationServicePojo;
import com.winklix.indu.healthcareapp.pojo.ServiceListingPojo;

import java.text.DateFormat;
import java.util.Date;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by dell on 29-01-2018.
 */

public class ServicePackageActivity extends AppCompatActivity implements View.OnClickListener,GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {

    private Button btn_1Day,btn_2day,btn_5day,btn_10days,btn_15days,btn_20days;
    private String cat_id,subCat_id,service_type,from;
    private GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;
    private LocationListener listener;
    public static final long UPDATE_INERVAL_IN_MILLISECONDS = 600000;
    private Location mCurrentLocation;
    private Boolean mRequestingLocationUpdates;
    private String mLastUpdateTime;
    private String mLatitudeLabel;
    private String mLongitudeLabel;
    private String mLastUpdateTimeLabel;
    private Context context;
    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    private static final int REQUEST_CHECK_SETTINGS = 10;

    public static final long FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS =
            UPDATE_INERVAL_IN_MILLISECONDS / 2;
    private double latitude;
    private double longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_packages);

        buildGoogleApiClient();

        context = this;

        mRequestingLocationUpdates = false;

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

        startUpdatesButtonHandler();

        btn_1Day.setOnClickListener(this);
        btn_2day.setOnClickListener(this);
        btn_5day.setOnClickListener(this);
        btn_10days.setOnClickListener(this);
        btn_15days.setOnClickListener(this);
        btn_20days.setOnClickListener(this);

    }



    @Override
    protected void onStart() {
        super.onStart();

        mGoogleApiClient.connect();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.days_1:
                if (from.equalsIgnoreCase("HomeVisit")){
                    servicetype(service_type,"1");
                } else {
                    LocationService();
                }


                break;

            case R.id.days_2:
                if (from.equalsIgnoreCase("HomeVisit")){
                    servicetype(service_type,"2");
                } else {
                    LocationService();
                }
                break;

            case R.id.days_5:
                if (from.equalsIgnoreCase("HomeVisit")){
                    servicetype(service_type,"5");
                } else {
                    LocationService();
                }
                break;

            case R.id.days_10:
                if (from.equalsIgnoreCase("HomeVisit")){
                    servicetype(service_type,"10");
                } else {
                    LocationService();
                }
                break;

            case R.id.days_15:
                if (from.equalsIgnoreCase("HomeVisit")){
                    servicetype(service_type,"15");
                } else {
                    LocationService();
                }
                break;

            case R.id.days_20:
                if (from.equalsIgnoreCase("HomeVisit")){
                    servicetype(service_type,"20");
                } else {
                    LocationService();
                }
                break;

        }

    }

    private void servicetype(String service_type, final String days){

        RestClient.get().ServicePrice(cat_id, subCat_id, service_type, new Callback<ServiceListingPojo>() {
            @Override
            public void success(ServiceListingPojo serviceListingPojo, retrofit.client.Response response) {
                try {
                    Intent intent = new Intent(ServicePackageActivity.this, PaymentActivity.class);
                    intent.putExtra("price", serviceListingPojo.getData().get(0).getService_price());
                    intent.putExtra("days",days);
                    startActivity(intent);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void failure(RetrofitError error) {
            }
        });
    }

   private void LocationService(){

        Intent intent = new Intent(ServicePackageActivity.this,NearByServiceCenter_Activity.class);
        intent.putExtra("cat_id",cat_id);
        intent.putExtra("subCat_id",subCat_id);
        intent.putExtra("latitude",latitude);
        intent.putExtra("longitude",longitude);
        intent.putExtra("from",from);

        startActivity(intent);

   }

    protected synchronized void buildGoogleApiClient() {
        //     Log.i(TAG, "Building GoogleApiClient");
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        createLocationRequest();
    }

    protected void createLocationRequest() {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(UPDATE_INERVAL_IN_MILLISECONDS);
        mLocationRequest.setFastestInterval(FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }


    public void startUpdatesButtonHandler() {

        if (Build.VERSION.SDK_INT < 23) {
            startLocationUpdates();
            return;
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            startLocationUpdates();
        } else {
        }
    }


    public void stopUpdatesButtonHandler(View view) {
        if (mRequestingLocationUpdates) {
            mRequestingLocationUpdates = false;
        }
    }

    private void startLocationUpdates() {
        //     Log.i(TAG, "startLocationUpdates");

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(mLocationRequest);

        PendingResult<LocationSettingsResult> result =
                LocationServices.SettingsApi.checkLocationSettings(mGoogleApiClient, builder.build());
        result.setResultCallback(new ResultCallback<LocationSettingsResult>() {
            @Override
            public void onResult(@NonNull LocationSettingsResult locationSettingsResult) {
                final Status status = locationSettingsResult.getStatus();

                switch (status.getStatusCode()) {
                    case LocationSettingsStatusCodes.SUCCESS:

                        if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, (com.google.android.gms.location.LocationListener)context);
                        }
                        break;
                    case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:

                        try {
                            status.startResolutionForResult(ServicePackageActivity.this, REQUEST_CHECK_SETTINGS);
                        } catch (IntentSender.SendIntentException e) {
                            // Ignore the error.
                        }
                        break;
                    case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                        // Location settings are not satisfied. However, we have no way
                        // to fix the settings so we won't show the dialog.
                        break;
                }
            }
        });
    }

    @Override
    public void onLocationChanged(Location location) {
        latitude = location.getLatitude();
        longitude = location.getLongitude();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        if (mCurrentLocation == null) {
            mCurrentLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
            mLastUpdateTime = DateFormat.getTimeInstance().format(new Date());
            //           updateUI();
        }

        if (mRequestingLocationUpdates) {
            startLocationUpdates();
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}

