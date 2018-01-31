package com.winklix.indu.healthcareapp.activities;

import android.content.Context;
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

public class SetupFormActivity extends AppCompatActivity implements View.OnClickListener {

    private final String Setup_Api_Url = "http://healthcare.com/app/setupdetail.php";
    private String setup_api_params;

    EditText setup_drname_et,setup_email_et,setup_phone_et,setup_clinic_et,setup_address_et,setup_type_et,setup_detail_et;
    Button setup_submit_btn;
    String set_name_str,set_email_str,set_phone_str,set_clinicname_str,set_address_str,set_type_str,set_detail_str;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_form);

        context = SetupFormActivity.this;

        setup_drname_et = (EditText)findViewById(R.id.setup_drname_et);
        setup_email_et = (EditText)findViewById(R.id.setup_email_et);
        setup_phone_et = (EditText)findViewById(R.id.setup_phone_et);
        setup_clinic_et = (EditText)findViewById(R.id.setup_clinic_et);
        setup_address_et = (EditText)findViewById(R.id.setup_address_et);
        setup_type_et = (EditText)findViewById(R.id.setup_type_et);
        setup_detail_et = (EditText)findViewById(R.id.setup_detail_et);
        setup_submit_btn = (Button) findViewById(R.id.setup_submit_btn);

        setup_submit_btn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.setup_submit_btn) {

            set_name_str =setup_drname_et.getText().toString().trim();
            set_email_str =setup_email_et.getText().toString().trim();
            set_phone_str =setup_phone_et.getText().toString().trim();
            set_address_str =setup_address_et.getText().toString().trim();
            set_type_str =setup_type_et.getText().toString().trim();
            set_detail_str =setup_detail_et.getText().toString().trim();
            set_clinicname_str =setup_clinic_et.getText().toString().trim();

            if (set_name_str == null || set_name_str.length()<3) {
                setup_drname_et.setError("Please enter Name");

            }
            else if (set_email_str.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(set_email_str).matches()) {

                setup_email_et.setError("Please Enter Valid Email");
            }
            else if (set_phone_str == null || set_phone_str.length()>10|| set_phone_str.length()<10) {
                setup_phone_et.setError("Please enter valid Mobile Number");

            }

            else if (set_type_str == null || set_type_str.length()<4) {
                setup_type_et.setError("Please enter Setup Type ");

            } else if (set_clinicname_str == null || set_clinicname_str.length()<4) {
                setup_clinic_et.setError("Please enter Clinic Name");

            }
            else if (!set_name_str.isEmpty() && !set_email_str.isEmpty() && !set_phone_str.isEmpty()
                    && !set_address_str.isEmpty()&& !set_type_str.isEmpty()&& !set_detail_str.isEmpty()) {

                setup_api_params = "dr_name=" + set_name_str + "&&clinic_name=" + set_clinicname_str + "&&email=" + set_email_str +
                        "&&contactno=" + set_phone_str + "&&address=" + set_address_str + "&&setup_type=" + set_type_str + "&&setup_detail=" + set_detail_str + "";

                Log.d("reg", setup_api_params);

                new Web_service_new(this, Setup_Api_Url, new CallBacks() {
                    @Override
                    public void result(String response) {
                        if (response != null) {


                            Log.d("dr=", response);

                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                if (jsonObject.getString("status").equals("sucess")) {


                                    //context.startActivity(new Intent(context, DoctorDashActivity.class));
                                    finish();
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        else {
                            Toast.makeText(context, "Something went wrong PLease try again", Toast.LENGTH_SHORT).show();
                        }
                    }}, setup_api_params.getBytes(), null).execute();

            }

    }}
}
