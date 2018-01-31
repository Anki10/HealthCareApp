package com.winklix.indu.healthcareapp.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.winklix.indu.healthcareapp.R;
import com.winklix.indu.healthcareapp.services.CallBacks;
import com.winklix.indu.healthcareapp.services.Web_service_new;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginPinActivity extends AppCompatActivity implements View.OnClickListener, CallBacks {

    private final String Dr_Api_Login_Url = "http://rohit.winklix.com/indu/app/dr_login.php";
    private String dr_api_login_params;
    Button submit_pin_btn;
    EditText pin_et;
    String pin_str;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_pin);

        context = LoginPinActivity.this;
        pin_et = (EditText) findViewById(R.id.pin_et);
        submit_pin_btn = (Button) findViewById(R.id.submit_pin_btn);
        submit_pin_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.submit_pin_btn) {

             pin_str =pin_et.getText().toString().trim();

                if (pin_str.isEmpty()) {

                    pin_et.setError("Please Enter Valid Login Pin");
                }
                else {
                    if (!pin_str.isEmpty()) {

                        dr_api_login_params = "loginpin=" + pin_str + "";

                        new Web_service_new(context, Dr_Api_Login_Url, this, dr_api_login_params.getBytes(), "").execute();
                    }
                }
            }

        }

    @Override
    public void result(String response) {
        if (response != null) {
            Log.d("dr_login=", response);

            try {
                JSONObject jsonObject = new JSONObject(response);

                if (jsonObject.has("login")) {

                    JSONObject login_jo = jsonObject.getJSONObject("login");
                    String dr_name = login_jo.getString("dr_name");


                    context.startActivity(new Intent(context, DoctorPanelActivity.class));
                    finish();

                }
                else if (jsonObject.getString("status").equals("invalid")) {
                    Toast.makeText(context, "Please Enter Valid Login Pin", Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
    }
}
}
