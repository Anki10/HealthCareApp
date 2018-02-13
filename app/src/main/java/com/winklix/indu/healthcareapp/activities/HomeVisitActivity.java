package com.winklix.indu.healthcareapp.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
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
import android.view.View;

import com.winklix.indu.healthcareapp.Health_Shared_Pref;
import com.winklix.indu.healthcareapp.R;
import com.winklix.indu.healthcareapp.adapters.HomeVisitAdapter;
import com.winklix.indu.healthcareapp.adapters.ServiceAdapter;
import com.winklix.indu.healthcareapp.api.RestClient;
import com.winklix.indu.healthcareapp.modals.Service_Modal;
import com.winklix.indu.healthcareapp.pojo.HomevisitcategoryPojo;
import com.winklix.indu.healthcareapp.testlist.MyDialog;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by dell on 08-02-2018.
 */

public class HomeVisitActivity extends AppCompatActivity implements View.OnClickListener,NavigationView.OnNavigationItemSelectedListener  {

    RecyclerView service_recy_view;
    Context context;
    //Spinner location_spinner;
    private HomeVisitAdapter adapter;
    private List<Service_Modal> service_modals;
    LinearLayoutManager layoutManager;
    ProgressDialog pd;
    Health_Shared_Pref health_shared_pref;
    private MyDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_visit);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        dialog = new MyDialog(HomeVisitActivity.this);

        service_recy_view = (RecyclerView) findViewById(R.id.service_recy_view);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        getHomevisit();

    }

    private void getHomevisit(){
        dialog.ShowProgressDialog();

        RestClient.get().homevisit(new Object(), new Callback<HomevisitcategoryPojo>() {
            @Override
            public void success(HomevisitcategoryPojo homevisitcategoryPojo, Response response) {

                layoutManager = new GridLayoutManager(context,2);
                service_recy_view.setLayoutManager(layoutManager);

                adapter = new HomeVisitAdapter(HomeVisitActivity.this, homevisitcategoryPojo.getData());
                service_recy_view.setAdapter(adapter);
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
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.product_ll:


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

//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//
//                if (serviceAdapter != null) serviceAdapter.getFilter().filter(newText);
//                return true;
//            }
//        });
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
