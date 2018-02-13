package com.winklix.indu.healthcareapp;

import android.*;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.winklix.indu.healthcareapp.activities.DoctorPanelActivity;
import com.winklix.indu.healthcareapp.activities.Login_Activity;
import com.winklix.indu.healthcareapp.activities.PatientBuyBook_Activity;
import com.winklix.indu.healthcareapp.testlist.PermissionUtils;

import java.util.ArrayList;

public class SplashScreenActivity extends AppCompatActivity implements PermissionUtils.PermissionResultCallback {

    Context context;
    ImageView logo_iv;
    Health_Shared_Pref health_shared_pref;
    ArrayList<String> permissions = new ArrayList<>();
    PermissionUtils permissionUtils;
    boolean isPermissionGranted;
    private static final int AUTO_HIDE_DELAY_MILLIS = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        context = SplashScreenActivity.this;
        health_shared_pref = new Health_Shared_Pref(context);

        logo_iv = (ImageView) findViewById(R.id.logo_iv);

        permissionUtils = new PermissionUtils(SplashScreenActivity.this);
        permissions.add(android.Manifest.permission.ACCESS_FINE_LOCATION);
        permissions.add(android.Manifest.permission.ACCESS_COARSE_LOCATION);
        permissionUtils.check_permission(permissions, "Need GPS permission for getting your location", 1);

}

        private void startActivity() {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (health_shared_pref.getPrefranceBooleanValue(Health_Api.IsLoggedIn)) {
                        if (health_shared_pref.getPrefranceStringValue(Health_Api.LoginFrom).equalsIgnoreCase("Doctor")) {
                            Intent inti = new Intent(context, DoctorPanelActivity.class);
                            startActivity(inti);
                            finish();
                        } else {
                            Intent inti = new Intent(context, PatientBuyBook_Activity.class);
                            startActivity(inti);
                            finish();
                        }
                    } else {
                        Intent inti = new Intent(context, Login_Activity.class);
                        startActivity(inti);
                        finish();
                    }
                }
            }, AUTO_HIDE_DELAY_MILLIS);

        }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {

        // redirects to utils

        permissionUtils.onRequestPermissionsResult(requestCode,permissions,grantResults);

    }

    @Override
    public void PermissionGranted(int request_code) {
        startActivity();
    }

    @Override
    public void PartialPermissionGranted(int request_code, ArrayList<String> granted_permissions) {

    }

    @Override
    public void PermissionDenied(int request_code) {

    }

    @Override
    public void NeverAskAgain(int request_code) {

    }
}


