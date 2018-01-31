package com.winklix.indu.healthcareapp.TestDemo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

import com.winklix.indu.healthcareapp.R;

public class HeaderList extends AppCompatActivity implements View.OnClickListener {
    private Context context;
    private CardView card_physio, occupational, orthosis, others;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_header_list);
        context = HeaderList.this;

        card_physio = (CardView) findViewById(R.id.card_physio);
        occupational = (CardView) findViewById(R.id.occupational);
        orthosis = (CardView) findViewById(R.id.orthosis);
        others = (CardView) findViewById(R.id.others);

        card_physio.setOnClickListener(this);
        occupational.setOnClickListener(this);
        orthosis.setOnClickListener(this);
        others.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.card_physio:
                startActivity(new Intent(context, SubcategoryList.class));
                break;
            case R.id.occupational:
                startActivity(new Intent(context, SubcategoryList.class));
                break;
            case R.id.orthosis:
                startActivity(new Intent(context, SubcategoryList.class));
                break;
            case R.id.others:
                startActivity(new Intent(context, SubcategoryList.class));
                break;

        }

    }
}
