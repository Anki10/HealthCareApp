package com.winklix.indu.healthcareapp.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.winklix.indu.healthcareapp.R;
import com.winklix.indu.healthcareapp.adapters.CategoryAdapter;
import com.winklix.indu.healthcareapp.adapters.SubCategoryAdapter;
import com.winklix.indu.healthcareapp.pojo.SubcategoryPojo;

import java.util.ArrayList;

/**
 * Created by Administrator on 1/18/2018.
 */

public class SubCategoryActivity extends AppCompatActivity {

    RecyclerView Subcategory_recycle_view;
    private SubCategoryAdapter adapter;
    public LinearLayoutManager layoutManager;
    private ArrayList<SubcategoryPojo>sub_category_list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_category);


        sub_category_list = (ArrayList<SubcategoryPojo>) getIntent().getSerializableExtra("sub_category_list");

        Subcategory_recycle_view = (RecyclerView) findViewById(R.id.sub_category_recycle_view);


        layoutManager = new GridLayoutManager(SubCategoryActivity.this,3);
        Subcategory_recycle_view.setLayoutManager(layoutManager);


        adapter = new SubCategoryAdapter(this, sub_category_list);
        Subcategory_recycle_view.setAdapter(adapter);
        Subcategory_recycle_view.setItemAnimator(new DefaultItemAnimator());

    }

}
