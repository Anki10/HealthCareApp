package com.winklix.indu.healthcareapp.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.winklix.indu.healthcareapp.Health_Api;
import com.winklix.indu.healthcareapp.Health_Shared_Pref;
import com.winklix.indu.healthcareapp.R;
import com.winklix.indu.healthcareapp.adapters.ServiceAdapter;
import com.winklix.indu.healthcareapp.api.RestClient;
import com.winklix.indu.healthcareapp.modals.Service_Modal;
import com.winklix.indu.healthcareapp.pojo.ServiceCategoryPojo;
import com.winklix.indu.healthcareapp.testlist.MyDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit.Callback;
import retrofit.RetrofitError;

public class BookServiceDash_Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    RecyclerView service_recy_view;
    Context context;
    //Spinner location_spinner;
    private ServiceAdapter serviceAdapter;
    private List<Service_Modal> service_modals;
    LinearLayoutManager layoutManager;
    ProgressDialog pd;
    Health_Shared_Pref health_shared_pref;
    private MyDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        context = BookServiceDash_Activity.this;
        health_shared_pref = new Health_Shared_Pref(context);

        dialog = new MyDialog(this);

       // location_spinner = (Spinner)findViewById(R.id.location_spinner);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

       /* String[] country_Codes =
                {"Select Your Location","Delhi", "Punjab", "Haryana", "Chandigarh", "Mumbai", "Bihar"};

        ArrayAdapter<String> location_adapter = new ArrayAdapter<String>(this, android.
                R.layout.simple_spinner_dropdown_item, country_Codes);*/

     //   location_spinner.setAdapter(location_adapter);
       // location_adapter.notifyDataSetChanged();

        service_recy_view = (RecyclerView)findViewById(R.id.service_recy_view) ;

        service_modals = new ArrayList<>();
   //     getServicesFromDB("");
        getBookServiceList();

//        service_recy_view.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//
//                if (layoutManager.findLastCompletelyVisibleItemPosition() == service_modals.size() - 1) {
//                    //  getCategoryFromDB(ledModals_list.get(ledModals_list.size() - 1).getId());
//                }
//
//            }
//        });
    }

    private void getBookServiceList(){
        dialog.ShowProgressDialog();

        RestClient.get().GetServiceCategoryList(new Object(), new Callback<ServiceCategoryPojo>() {
            @Override
            public void success(ServiceCategoryPojo serviceCategoryPojo, retrofit.client.Response response) {

                layoutManager = new GridLayoutManager(context,2);
                service_recy_view.setLayoutManager(layoutManager);

                serviceAdapter = new ServiceAdapter(context, serviceCategoryPojo.getData());
                service_recy_view.setAdapter(serviceAdapter);
                service_recy_view.setItemAnimator(new DefaultItemAnimator());



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

        getMenuInflater().inflate(R.menu.menu_search, menu);

        MenuItem search = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(search);
        search(searchView);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }

    private void search(SearchView searchView) {

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                if (serviceAdapter != null) serviceAdapter.getFilter().filter(newText);
                return true;
            }
        });
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.nav_camera) {

        } else if (id == R.id.nav_gallery) {

        }  else if (id == R.id.nav_share) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
