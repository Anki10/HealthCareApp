package com.winklix.indu.healthcareapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.winklix.indu.healthcareapp.Health_Api;
import com.winklix.indu.healthcareapp.Health_Shared_Pref;
import com.winklix.indu.healthcareapp.R;
import com.winklix.indu.healthcareapp.api.RestClient;
import com.winklix.indu.healthcareapp.pojo.AddtoCartPojo;
import com.winklix.indu.healthcareapp.testlist.MyDialog;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class Buy_Prod_DescActivity extends AppCompatActivity {


    ImageView prod_desc_img;
    TextView prod_desc_prize,prod_desc_name,prod_desc_detail,prod_specialization;
    private LinearLayout ll_addtocart;
    private MyDialog dialog;
    private String Prod_id,prod_prize;
    Health_Shared_Pref health_shared_pref;
    private String User_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy__prod__desc);

  /*      Bundle bundle=this.getIntent().getExtras();
        int pic=bundle.getInt("img");*/

        ll_addtocart = (LinearLayout) findViewById(R.id.ll_addtocart);
        dialog = new MyDialog(this);

        health_shared_pref = new Health_Shared_Pref(this);


        prod_desc_prize = (TextView) findViewById(R.id.prod_desc_prize);
        prod_desc_name = (TextView) findViewById(R.id.prod_desc_name);
        prod_desc_detail = (TextView) findViewById(R.id.prod_desc_detail);
        prod_desc_img = (ImageView) findViewById(R.id.prod_desc_img);
        prod_specialization = (TextView) findViewById(R.id.prod_specialization);

        Intent intent = getIntent();
       // Glide.with(this).load(intent.getExtras().getString("img")).diskCacheStrategy(DiskCacheStrategy.ALL).into(prod_desc_img);

        Picasso.with(this).load(intent.getStringExtra("img"))
                .placeholder(R.mipmap.ic_launcher)
                .into(prod_desc_img);

        prod_desc_prize.setText(Html.fromHtml("Product Prize: " + getIntent().getStringExtra("prod_prize")), TextView.BufferType.SPANNABLE);
        prod_desc_name.setText(Html.fromHtml("Product Name : " + getIntent().getStringExtra("name")), TextView.BufferType.SPANNABLE);
        prod_desc_detail.setText(Html.fromHtml("" + getIntent().getStringExtra("desc")), TextView.BufferType.SPANNABLE);
        prod_specialization.setText(Html.fromHtml("" + getIntent().getStringExtra("specification")), TextView.BufferType.SPANNABLE);


        Prod_id = intent.getStringExtra("Prod_id");
        prod_prize = intent.getStringExtra("prod_prize");

        //prod_desc_img.setImageResource(pic);

        User_id = health_shared_pref.getPrefranceStringValue(Health_Api.USERID);

        ll_addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddtoCart();
            }
        });
    }

    private void AddtoCart(){
        dialog.ShowProgressDialog();
        RestClient.get().AddtoCart(Prod_id, "1", prod_prize, "1", User_id, new Callback<AddtoCartPojo>() {
            @Override
            public void success(AddtoCartPojo addtoCartPojo, Response response) {

                String msg = addtoCartPojo.getMessage();

                dialog.CancelProgressDialog();

                Toast.makeText(Buy_Prod_DescActivity.this,"Your product added successfully",Toast.LENGTH_LONG).show();

                System.out.println("xxx sucess"+msg);
            }

            @Override
            public void failure(RetrofitError error) {
                dialog.CancelProgressDialog();

                System.out.println("xxx faill");
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.product_cart, menu);

//        Intent intent = new Intent(Buy_Prod_DescActivity.this,GetCartListActivity.class);
//        startActivity(intent);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_cart:
                Intent intent = new Intent(Buy_Prod_DescActivity.this,GetCartListActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
