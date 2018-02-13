package com.winklix.indu.healthcareapp.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
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

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;
import com.winklix.indu.healthcareapp.Health_Api;
import com.winklix.indu.healthcareapp.Health_Shared_Pref;
import com.winklix.indu.healthcareapp.R;
import com.winklix.indu.healthcareapp.api.RestClient;
import com.winklix.indu.healthcareapp.pojo.LoginResponsePojo;
import com.winklix.indu.healthcareapp.services.CallBacks;
import com.winklix.indu.healthcareapp.services.Web_service_new;
import com.winklix.indu.healthcareapp.testlist.Appconstant;
import com.winklix.indu.healthcareapp.testlist.MyDialog;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class Login_Activity extends AppCompatActivity implements View.OnClickListener, CallBacks {


    private final String Patient_Api_Login_Url = "https://2040healthcare.com/app/login.php";
    private String patient_api_login_params;

      private Button login_btn;
      private TextView login_reg_tv;
      RadioGroup login_rg,dr_role;
      Context context;
      LinearLayout dr_ll;
      EditText email_et,pass_et;
      RadioButton dr_rbtn,patient_rbtn,dr_homeVisit,dr_sharing;
      String email_str,pass_str,identifier,role;
      private MyDialog dialog;
      Health_Shared_Pref health_shared_pref;
      private LinearLayout doctor_role_ll;
      String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        context = Login_Activity.this;

        health_shared_pref = new Health_Shared_Pref(context);

        dialog = new MyDialog(this);

        getImei();

        login_btn = (Button) findViewById(R.id.login_btn);
        login_reg_tv = (TextView) findViewById(R.id.login_reg_tv);
        email_et = (EditText) findViewById(R.id.email_et);
        pass_et = (EditText) findViewById(R.id.pass_et);
        login_rg = (RadioGroup) findViewById(R.id.login_rg);
        dr_ll = (LinearLayout) findViewById(R.id.dr_ll);
        dr_rbtn = (RadioButton) findViewById(R.id.dr_rbtn);
        patient_rbtn = (RadioButton) findViewById(R.id.patient_rbtn);
        doctor_role_ll = (LinearLayout) findViewById(R.id.doctor_role_ll);
        dr_homeVisit = (RadioButton) findViewById(R.id.dr_homevisit);
        dr_sharing = (RadioButton) findViewById(R.id.dr_sharing);
        dr_role = (RadioGroup) findViewById(R.id.dr_role);

        login_btn.setOnClickListener(this);
        login_reg_tv.setOnClickListener(this);

        login_rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton) group.findViewById(checkedId);

                if (rb.getText().equals("Service")){
                    doctor_role_ll.setVisibility(View.VISIBLE);
                } else {
                    doctor_role_ll.setVisibility(View.INVISIBLE);
                }
            }
        });

        dr_role.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton) group.findViewById(checkedId);

                if (rb.getText().equals("Home Visit")){
                    role = String.valueOf(rb.getText());
                } else {
                    role = String.valueOf(rb.getText());
                }

                System.out.println("rolee"+rb.getText());
            }
        });

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

                Login();

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

            if (dr_rbtn.isChecked()) {
                Toast.makeText(Login_Activity.this,"You can't signup as doctor",Toast.LENGTH_LONG).show();

            } else if (patient_rbtn.isChecked()){
                Intent intents = new Intent(this, RegisterActivity.class);
                startActivity(intents);
            } else {
                Intent intents = new Intent(this, RegisterActivity.class);
                startActivity(intents);
            }
        }
//        else if (id == R.id.setup_reg_tv) {
//
//            Intent intents = new Intent(this, SetupFormActivity.class);
//            startActivity(intents);
//
//        }
    }

    private void getImei() {
        if (identifier == null || identifier.length() == 0)
            identifier = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);

 //       saveIntoPrefs(IMEI, identifier);
    }


    private void Login(){
        dialog.ShowProgressDialog();

        if (role.equalsIgnoreCase("Home Visit")){
            role = "homevisitor";
        } else {
           role = "";
        }

        String token_id = getFromPrefs("deviceId");


        RestClient.get().Login(email_et.getText().toString(), pass_et.getText().toString(), role, token_id, new Callback<LoginResponsePojo>() {
            @Override
            public void success(LoginResponsePojo loginResponsePojo, Response response) {

                if (loginResponsePojo.getStatus().equalsIgnoreCase("login successful")){
                    Intent intents = new Intent(Login_Activity.this, LoginPinActivity.class);
                    startActivity(intents);
                    finish();

                    health_shared_pref.setPrefranceValue(Health_Api.IsLoggedIn,true);
                    health_shared_pref.setPrefranceValue(Health_Api.LoginFrom,"Doctor");



                } else {
                    Toast.makeText(Login_Activity.this,"Something wrong",Toast.LENGTH_LONG).show();
                }

                dialog.CancelProgressDialog();

                System.out.println("xxx success");
            }

            @Override
            public void failure(RetrofitError error) {
                System.out.println("xxx faill");

                dialog.CancelProgressDialog();
            }
        });

    }

    public String getFromPrefs(String key) {
        SharedPreferences prefs = getSharedPreferences(Appconstant.PREF_NAME, MODE_PRIVATE);
        return prefs.getString(key, "");
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
                        health_shared_pref.setPrefranceValue(Health_Api.LoginFrom,"patient");
                        finish();

                    } else if (jsonObject.getString("status").equals("invalid")) {
                        Toast.makeText(context, "Please Enter Valid Email or Password", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
    }}


