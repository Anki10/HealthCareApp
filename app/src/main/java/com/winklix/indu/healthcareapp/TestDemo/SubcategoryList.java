package com.winklix.indu.healthcareapp.TestDemo;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.winklix.indu.healthcareapp.R;
import com.winklix.indu.healthcareapp.TestDemo.adaptersss.ItemClickListener;
import com.winklix.indu.healthcareapp.TestDemo.adaptersss.Section;
import com.winklix.indu.healthcareapp.TestDemo.adaptersss.SectionedExpandableLayoutHelper;
import com.winklix.indu.healthcareapp.TestDemo.modelsss.Item;
import com.winklix.indu.healthcareapp.activities.NearByServiceCenter_Activity;
import com.winklix.indu.healthcareapp.activities.PaymentActivity;
import com.winklix.indu.healthcareapp.activities.ServiceDescActivity;
import com.winklix.indu.healthcareapp.activities.SubCategoryActivity;
import com.winklix.indu.healthcareapp.adapters.ServiceSubCategoryAdapter;
import com.winklix.indu.healthcareapp.adapters.SubCategoryAdapter;
import com.winklix.indu.healthcareapp.pojo.ServiceSubCategoryPojo;
import com.winklix.indu.healthcareapp.pojo.SubcategoryPojo;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class SubcategoryList extends AppCompatActivity implements ItemClickListener {
   public RecyclerView service_sub_cat_recycler_view;

    String[] orthoItems = {"Low Back Pain","Low Back Pain","Low Back Pain"};
    private String service_id;
    public LinearLayoutManager layoutManager;
    private ServiceSubCategoryAdapter adapter;
    private ArrayList<ServiceSubCategoryPojo>sub_category_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subcategory_list);

        service_id = getIntent().getStringExtra("service_id");

        sub_category_list = (ArrayList<ServiceSubCategoryPojo>) getIntent().getSerializableExtra("list");

        service_sub_cat_recycler_view =
                (RecyclerView) findViewById(R.id.service_sub_cat_recycler_view);

        layoutManager = new GridLayoutManager(SubcategoryList.this,3);
        service_sub_cat_recycler_view.setLayoutManager(layoutManager);

        adapter = new ServiceSubCategoryAdapter(this, sub_category_list);
        service_sub_cat_recycler_view.setAdapter(adapter);
        service_sub_cat_recycler_view.setItemAnimator(new DefaultItemAnimator());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    // Toast.makeText(this, "Item: " + item.getName() + " clicked", Toast.LENGTH_SHORT).show();


    @Override
    public void itemClicked(Item item) {
  //      Toast.makeText(this, "Item: " + item.getName() + " clicked", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void itemClicked(Section section) {
   //     Toast.makeText(this, "Section: " + section.getName() + " clicked", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }


}

