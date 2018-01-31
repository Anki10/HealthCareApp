package com.winklix.indu.healthcareapp.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.winklix.indu.healthcareapp.Health_Api;
import com.winklix.indu.healthcareapp.Health_Shared_Pref;
import com.winklix.indu.healthcareapp.R;
import com.winklix.indu.healthcareapp.services.CallBacks;
import com.winklix.indu.healthcareapp.services.Web_service_new;

import org.json.JSONException;
import org.json.JSONObject;

public class Login_Activity extends AppCompatActivity implements View.OnClickListener, CallBacks {


    private final String Patient_Api_Login_Url = "http://2040healthcare.com/app/login.php";
    private String patient_api_login_params;

      private Button login_btn;
      private TextView login_reg_tv,setup_reg_tv;
      RadioGroup login_rg;
      Context context;
      LinearLayout dr_ll;
      EditText email_et,pass_et;
      RadioButton dr_rbtn,patient_rbtn;
      String email_str,pass_str;

      Health_Shared_Pref health_shared_pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        context = Login_Activity.this;

        health_shared_pref = new Health_Shared_Pref(context);

        login_btn = (Button) findViewById(R.id.login_btn);
        login_reg_tv = (TextView) findViewById(R.id.login_reg_tv);
        setup_reg_tv = (TextView) findViewById(R.id.setup_reg_tv);
        email_et = (EditText) findViewById(R.id.email_et);
        pass_et = (EditText) findViewById(R.id.pass_et);
        login_rg = (RadioGroup) findViewById(R.id.login_rg);
        dr_ll = (LinearLayout) findViewById(R.id.dr_ll);
        dr_rbtn = (RadioButton) findViewById(R.id.dr_rbtn);
        patient_rbtn = (RadioButton) findViewById(R.id.patient_rbtn);

        login_btn.setOnClickListener(this);
        login_reg_tv.setOnClickListener(this);
        setup_reg_tv.setOnClickListener(this);

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);  // to go to previous menu or act as back button
    }

    @Override
    public void onClick(View view) {

        int id = view.getId();
        if (id == R.id.login_btn) {

            email_str = email_et.getText().toString().trim();
            pass_str = pass_et.getText().toString().trim();

            if (dr_rbtn.isChecked()) {
                Intent intents = new Intent(this, LoginPinActivity.class);
                startActivity(intents);
                finish();

            } else if (patient_rbtn.isChecked()) {

                    if (email_str.isEmpty()) {

                        email_et.setError("Please Enter Valid Email");
                    } else if (pass_str.isEmpty()) {

                        pass_et.setError("Please Enter Valid Password");
                    } else {
                        if (!email_str.isEmpty() && !pass_str.isEmpty()) {


                            patient_api_login_params = "email=" + email_str + "&&password=" + pass_str + "";

                            new Web_service_new(context, Patient_Api_Login_Url, this, patient_api_login_params.getBytes(), "").execute();
                        }
                    }
                }
                else {
                    Toast.makeText(context, "Please Select Are You Login As Doctor or Pateint", Toast.LENGTH_SHORT).show();
            }

        } else if (id == R.id.login_reg_tv) {

            Intent intents = new Intent(this, RegisterActivity.class);
            startActivity(intents);


        }
        else if (id == R.id.setup_reg_tv) {

            Intent intents = new Intent(this, SetupFormActivity.class);
            startActivity(intents);

        }
    }

    @Override
    public void result(String response) {

            if (response != null) {
                Log.d("patient_login=", response);

                try {
                    JSONObject jsonObject = new JSONObject(response);

                    if (jsonObject.has("login")) {

                        JSONObject login_jo = jsonObject.getJSONObject("login");
                        String p_name = login_jo.getString("name");
                        String p_email = login_jo.getString("email");
                        String p_phone = login_jo.getString("phone");
                        String User_id = login_jo.getString("id");


                        health_shared_pref.setPrefranceValue(Health_Api.PatientName,p_name);
                        health_shared_pref.setPrefranceValue(Health_Api.PatientEmail,p_email);
                        health_shared_pref.setPrefranceValue(Health_Api.USERID,User_id);

                        context.startActivity(new Intent(context, PatientBuyBook_Activity.class));
                        health_shared_pref.setPrefranceValue(Health_Api.IsLoggedIn,true);
                        finish();

                    } else if (jsonObject.getString("status").equals("invalid")) {
                        Toast.makeText(context, "Please Enter Valid Email or Password", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
    }}


