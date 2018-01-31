package com.winklix.indu.healthcareapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.winklix.indu.healthcareapp.R;

public class PatientBuyBook_Activity extends AppCompatActivity implements View.OnClickListener {

    private Button buy, book;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);

        buy = (Button) findViewById(R.id.patient_buy);
        book = (Button) findViewById(R.id.patient_book);

        buy.setOnClickListener(this);
        book.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {

            case R.id.patient_buy:

                Intent intent1 = new Intent(this, BuyDashboardActivity.class);
                intent1.putExtra("type","patient_buy");
                startActivity(intent1);
                break;

            case R.id.patient_book:

                Intent intent = new Intent(this, BookServiceDash_Activity.class);
                intent.putExtra("type","patient_book");
                startActivity(intent);


        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        finish();
    }
}
