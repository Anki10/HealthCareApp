package com.winklix.indu.healthcareapp.activities;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

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

               final Dialog dialog = new Dialog(PatientBuyBook_Activity.this);
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

                            Intent intent = new Intent(PatientBuyBook_Activity.this, HomeVisitActivity.class);
                            startActivity(intent);
                            dialog.dismiss();

                        } else if (sharing_service_rb.isChecked()) {
                            Intent intent = new Intent(PatientBuyBook_Activity.this, BookServiceDash_Activity.class);
                            intent.putExtra("type","patient_book");
                            startActivity(intent);
                            dialog.dismiss();

                        }
                    }
                });

                dialog.show();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        finish();
    }
}
