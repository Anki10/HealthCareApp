package com.winklix.indu.healthcareapp.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import com.winklix.indu.healthcareapp.R;

public class PaymentActivity extends AppCompatActivity {

    private String service_price,days;
    private TextView tv_service_price,service_after_price;
    int price,per_1,day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        service_price = getIntent().getStringExtra("price");
        days = getIntent().getStringExtra("days");

        tv_service_price = (TextView) findViewById(R.id.service_price);
        service_after_price = (TextView) findViewById(R.id.service_after_price);

        tv_service_price.setText("Total Payable Amount Rs: "+ service_price);

        price = Integer.parseInt(service_price);
        day = Integer.parseInt(days);

        service_after_price.setText("Payable amount after discount Rs:  "+ price);

    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.pay30:
                if (checked)
                    price = Integer.parseInt(service_price);

                  service_after_price.setText("Payable amount after discount Rs:  "+ price);
                    break;
            case R.id.pay50:
                if (checked)
                     price = Integer.parseInt(service_price);

                int per = price*10*day/100;

                int main_per = price-per;

                service_after_price.setText("Payable amount after discount Rs:  "+ main_per);
                break;

            case R.id.pay100:
                if (checked)
                    price = Integer.parseInt(service_price);

                per_1 = price*15*day/100;

                int main_per_1 = price-per_1;

                service_after_price.setText("Payable amount after discount Rs:  "+ main_per_1);
                break;
        }
    }
}
