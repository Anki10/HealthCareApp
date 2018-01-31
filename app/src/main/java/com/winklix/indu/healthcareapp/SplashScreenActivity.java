package com.winklix.indu.healthcareapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.winklix.indu.healthcareapp.activities.Login_Activity;
import com.winklix.indu.healthcareapp.activities.PatientBuyBook_Activity;

public class SplashScreenActivity extends AppCompatActivity {

    Context context;
    ImageView logo_iv;
    Health_Shared_Pref health_shared_pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        context = SplashScreenActivity.this;
        health_shared_pref = new Health_Shared_Pref(context);

        startAnimation();

}
    private void startAnimation() {

        logo_iv = (ImageView) findViewById(R.id.logo_iv);
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom_in_anim);
        animation.setAnimationListener(new Animation.AnimationListener() {

            @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            if (health_shared_pref.getPrefranceBooleanValue(Health_Api.IsLoggedIn)){

                Intent inti = new Intent(context, PatientBuyBook_Activity.class);
                startActivity(inti);
                finish();
            }
            else {

                Intent inti = new Intent(context, Login_Activity.class);
                startActivity(inti);
                finish();
            }
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
        });
        logo_iv.startAnimation(animation);
        }
        }


