package com.winklix.indu.healthcareapp.activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.winklix.indu.healthcareapp.Health_Shared_Pref;
import com.winklix.indu.healthcareapp.R;
import com.winklix.indu.healthcareapp.services.CallBacks;
import com.winklix.indu.healthcareapp.services.Web_service_new;

import org.json.JSONException;
import org.json.JSONObject;

public class DoctorPanelActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private final String PatientReff_Api_Url = "http://rohit.winklix.com/indu/app/dr_reff_patient.php";
    private String patientreff_api_params;
  //  EditText p_name_et,p_email_et,p_phone_et,p_address_et,p_reason_et,p_reffby_et;
    String pname_str,pemail_str,pmobile_str,paddress_str,preason_str,preff_str;
    Context context;
    Button psubmit_btn;
    Health_Shared_Pref health_shared_pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_panel);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        context = DoctorPanelActivity.this;

        health_shared_pref = new Health_Shared_Pref(context);

//        p_name_et = (EditText) findViewById(R.id.p_name_et);
//        p_email_et = (EditText) findViewById(R.id.p_email_et);
//        p_phone_et = (EditText) findViewById(R.id.p_phone_et);
//        p_address_et = (EditText) findViewById(R.id.p_address_et);
//        p_reason_et = (EditText) findViewById(R.id.p_reason_et);
//        p_reffby_et = (EditText) findViewById(R.id.p_reffby_et);
//
//        psubmit_btn = (Button) findViewById(R.id.psubmit_btn);
//        psubmit_btn.setOnClickListener(this);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.doctor_panel, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

       if (id == R.id.dr_home_iv) {


        }  else if (id == R.id.dr_history_iv) {
            startActivity(new Intent(DoctorPanelActivity.this,DrHistoryActivity.class));
        } else if (id == R.id.dr_contactUs){
           startActivity(new Intent(DoctorPanelActivity.this,ContactUsActivity.class));
       } else if (id == R.id.dr_Logout){

       }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void logout(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(DoctorPanelActivity.this);
        // set title
        alertDialogBuilder.setTitle("Logout");
        // set dialog message
        alertDialogBuilder
                .setMessage("Logout")
                .setCancelable(false)
                .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        DeleteSharedPrfernce();

                        Intent intent = new Intent(DoctorPanelActivity.this, Login_Activity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        finish();
                        Toast.makeText(DoctorPanelActivity.this, getResources().getString(R.string.logout_sucessfuly), Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("No",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        // if this button is clicked, just close
                        // the dialog box and do nothing
                        dialog.cancel();
                    }
                });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();
        // show it
        alertDialog.show();
    }

    public void DeleteSharedPrfernce() {
//        health_shared_pref.
//        edit.clear();
//        edit.commit();
    }

//    @Override
//    public void onClick(View view) {
//        int id = view.getId();
//        if (id==R.id.psubmit_btn) {
//
//            pname_str = p_name_et.getText().toString().trim();
//            pemail_str = p_email_et.getText().toString().trim();
//            pmobile_str = p_phone_et.getText().toString().trim();
//            paddress_str = p_address_et.getText().toString().trim();
//            preason_str = p_reason_et.getText().toString().trim();
//            preff_str = p_reffby_et.getText().toString().trim();
//
//            if (pname_str == null || pname_str.length()<3) {
//                p_name_et.setError("Please enter Name");
//            }
//            else if (pemail_str.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(pemail_str).matches()) {
//                p_email_et.setError("Please Enter Valid Email");
//            }
//            else if (pmobile_str == null || pmobile_str.length()>10|| pmobile_str.length()<10) {
//                p_phone_et.setError("Please enter valid Mobile Number");
//            }
//            else if (preason_str == null) {
//                p_reason_et.setError("Please enter Password ");
//
//            }  else if (preff_str == null) {
//                p_reffby_et.setError("Please enter Password ");
//            }
//            else if (!pname_str.isEmpty() && !pemail_str.isEmpty() && !pmobile_str.isEmpty()
//                    && !preff_str.isEmpty()&& !preason_str.isEmpty()) {
//
//                patientreff_api_params = "pname=" + pname_str + "&&pemail=" + pemail_str + "&&pmobile=" + pmobile_str +
//                        "&&paddress=" + paddress_str +"&&preason=" + preason_str +"&&refferenceby=" + preff_str + "";
//                Log.d("reffpara", patientreff_api_params);
//
//                new Web_service_new(this, PatientReff_Api_Url, new CallBacks() {
//                    @Override
//                    public void result(String response) {
//                        if (response != null) {
//
//
//                            Log.d("drreff=", response);
//
//                            try {
//                                JSONObject jsonObject = new JSONObject(response);
//                                if (jsonObject.getString("status").equals("sucess")) {
//
//                                    Toast.makeText(DoctorPanelActivity.this,"Your form submit succesfully",Toast.LENGTH_LONG).show();
//
//                                    p_name_et.setText("");
//                                    p_email_et.setText("");
//                                    p_phone_et.setText("");
//                                    p_address_et.setText("");
//                                    p_reason_et.setText("");
//                                    p_reffby_et.setText("");
//                                }
//
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//                        }
//
//                        else {
//                            Toast.makeText(context, "Something went wrong PLease try again", Toast.LENGTH_SHORT).show();
//                        }
//                    }}, patientreff_api_params.getBytes(), null).execute();
//
//            }
 //       }
 //       }

}
