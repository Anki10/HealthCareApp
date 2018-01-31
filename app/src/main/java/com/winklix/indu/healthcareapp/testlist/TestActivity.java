package com.winklix.indu.healthcareapp.testlist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ExpandableListView;

import com.winklix.indu.healthcareapp.R;

public class TestActivity extends AppCompatActivity  {

    private ExpandableListView menu_exp_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        menu_exp_list = findViewById(R.id.menu_exp_list);


    }}