package com.winklix.indu.healthcareapp.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.winklix.indu.healthcareapp.Health_Api;
import com.winklix.indu.healthcareapp.Health_Shared_Pref;
import com.winklix.indu.healthcareapp.R;
import com.winklix.indu.healthcareapp.api.RestClient;
import com.winklix.indu.healthcareapp.databases.DBHelper;
import com.winklix.indu.healthcareapp.services.CallBacks;
import com.winklix.indu.healthcareapp.services.HealthCare_Api;
import com.winklix.indu.healthcareapp.services.HealthCare_Shared_Pref;
import com.winklix.indu.healthcareapp.services.Web_service_new;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private final String Dr_Api_Url = "http://rohit.winklix.com/indu/app/dr_register.php";
    private final String Patient_Api_Url = "http://2040healthcare.com/app/register.php";
    private String dr_api_params,patient_api_params;
    Button reg_btn;
    TextView reg_login_tv;
    RadioButton dr_reg_rb,patient_reg_rb;
    EditText reg_name_et,reg_email_et,reg_phone_et,reg_pass_et;
    String name_str,email_str,phone_str,pass_str,dr_reg_id,patient_reg_id;
    HealthCare_Shared_Pref healthCare_shared_pref;
    Context context;
    DBHelper dbHelper;
    Health_Shared_Pref health_shared_pref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registers);

        context = RegisterActivity.this;

        health_shared_pref = new Health_Shared_Pref(context);

        dbHelper = new DBHelper(context);

        healthCare_shared_pref = new HealthCare_Shared_Pref(context);

        reg_login_tv = (TextView)findViewById(R.id.reg_login_tv);

        reg_name_et = (EditText) findViewById(R.id.reg_name_et);
        reg_email_et = (EditText) findViewById(R.id.reg_email_et);
        reg_phone_et = (EditText) findViewById(R.id.reg_phone_et);
        reg_pass_et = (EditText) findViewById(R.id.reg_pass_et);

        reg_btn = (Button)findViewById(R.id.reg_btn);

        reg_btn.setOnClickListener(this);
        reg_login_tv.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        int id = view.getId();
        switch (id) {
            case R.id.reg_btn:

                name_str = reg_name_et.getText().toString().trim();
                email_str = reg_email_et.getText().toString().trim();
                phone_str = reg_phone_et.getText().toString().trim();
                pass_str = reg_pass_et.getText().toString().trim();

                if(dr_reg_rb.isChecked()){

                   if (name_str == null || name_str.length()<3) {
                       reg_name_et.setError("Please enter Name");

                    }
                    else if (email_str.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email_str).matches()) {

                       reg_email_et.setError("Please Enter Valid Email");
                    }
                    else if (phone_str == null || phone_str.length()>10|| phone_str.length()<10) {
                       reg_phone_et.setError("Please enter valid Mobile Number");

                    }

                    else if (pass_str == null || pass_str.length()<4) {
                       reg_pass_et.setError("Please enter Password ");

                    }
                    else if (!name_str.isEmpty() && !email_str.isEmpty() && !pass_str.isEmpty()
                            && !phone_str.isEmpty()) {

                               dr_api_params = "dr_name=" + name_str + "&&dr_email=" + email_str + "&&dr_phone=" + phone_str + "&&dr_passwod=" + pass_str + "";
                               Log.d("reg", dr_api_params);

                               new Web_service_new(this, Dr_Api_Url, new CallBacks() {
                                   @Override
                                   public void result(String response) {
                                       if (response != null) {


                                           Log.d("dr=", response);

                                           try {
                                               JSONObject jsonObject = new JSONObject(response);
                                               if (jsonObject.getString("status").equals("sucess")) {

                                                   dr_reg_id = jsonObject.getString("id");
                                                   healthCare_shared_pref.setPrefranceValue(HealthCare_Api.DR_REG_ID, dr_reg_id);

                                                   dbHelper.addDoctor(name_str,email_str,pass_str,phone_str);

                                                   Toast.makeText(context, "Login Pin will be generated within 24 hours.", Toast.LENGTH_SHORT).show();

                                                   finish();
                                               }

                                           } catch (JSONException e) {
                                               e.printStackTrace();
                                           }
                                       }

                                       else {
                                           Toast.makeText(context, "Something went wrong PLease try again", Toast.LENGTH_SHORT).show();
                                       }
                                   }}, dr_api_params.getBytes(), null).execute();

                           }
                           break;
                       }



                else {
                    if (patient_reg_rb.isChecked()) {

                        if (name_str == null || name_str.length()<3) {
                            reg_name_et.setError("Please enter Name");

                        }
                        else if (email_str.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email_str).matches()) {

                            reg_email_et.setError("Please Enter Valid Email");
                        }
                        else if (phone_str == null || phone_str.length()>10|| phone_str.length()<10) {
                            reg_phone_et.setError("Please enter valid Mobile Number");

                        }

                        else if (pass_str == null || pass_str.length()<4) {
                            reg_pass_et.setError("Please enter Password ");

                        }
                        else if (!name_str.isEmpty() && !email_str.isEmpty() && !pass_str.isEmpty()
                                && !phone_str.isEmpty()) {

                            patient_api_params = "name=" + name_str + "&&email=" + email_str + "&&phone=" + phone_str + "&&password=" + pass_str + "";
                            Log.d("reg", patient_api_params);

                            new Web_service_new(this, Patient_Api_Url, new CallBacks() {
                                @Override
                                public void result(String response) {
                                    if (response != null) {

                                        Log.d("patient=", response);
                                        try {
                                            JSONObject jsonObject = new JSONObject(response);

                                            String Reg = jsonObject.getString("Registration");

                                            JSONObject jsonObject1 = new JSONObject(Reg);

                                                patient_reg_id = jsonObject1.getString("id");
                                                healthCare_shared_pref.setPrefranceValue(HealthCare_Api.PATIENT_REG_ID, patient_reg_id);

                                            health_shared_pref.setPrefranceValue(Health_Api.IsLoggedIn,true);


                                               Intent intent = new Intent(context,PatientBuyBook_Activity.class);
                                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                                startActivity(intent);
                                               finish();


                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }

                                    else {
                                        Toast.makeText(context, "Something went wrong PLease try again", Toast.LENGTH_SHORT).show();
                                    }}
                            }, patient_api_params.getBytes(), null).execute();

                        }
                    }

                else {
                Toast.makeText(context, "Please Select Are You Register As Doctor or Pateint", Toast.LENGTH_SHORT).show();
            }}
                break;

            case R.id.reg_login_tv:

                Intent intents = new Intent(this, Login_Activity.class);
                startActivity(intents);
                finish();
                break;
        }
}

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    private void RegisterForm(){
     //   RestClient.get().Register();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        finish();
    }
}


