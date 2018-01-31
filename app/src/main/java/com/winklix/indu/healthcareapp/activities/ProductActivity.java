package com.winklix.indu.healthcareapp.activities;

import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.winklix.indu.healthcareapp.R;
import com.winklix.indu.healthcareapp.adapters.ProductAdapter;
import com.winklix.indu.healthcareapp.adapters.SubCategoryAdapter;
import com.winklix.indu.healthcareapp.api.RestClient;
import com.winklix.indu.healthcareapp.modals.Product_Modal;
import com.winklix.indu.healthcareapp.pojo.ProductDataPojo;
import com.winklix.indu.healthcareapp.pojo.SubcategoryPojo;
import com.winklix.indu.healthcareapp.testlist.MyDialog;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Administrator on 1/18/2018.
 */

public class ProductActivity extends AppCompatActivity {


    private ProductAdapter adapter;
    RecyclerView product_recycle_view;
    public LinearLayoutManager layoutManager;
    private ArrayList<Product_Modal> product_list;
    private MyDialog dialog;
    private String cat,sub_cat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        product_list = new ArrayList<Product_Modal>();

        product_recycle_view = (RecyclerView) findViewById(R.id.product_recycle_view);

        dialog = new MyDialog(this);

        cat = getIntent().getStringExtra("cat");
        sub_cat = getIntent().getStringExtra("sub_cat");

        getProductList();
    }

    private void getProductList(){
            dialog.ShowProgressDialog();

        RestClient.get().GetProductList(cat, sub_cat, new Callback<ProductDataPojo>() {
            @Override
            public void success(ProductDataPojo productDataPojo, Response response) {

                product_list.addAll(productDataPojo.getData());

                layoutManager = new GridLayoutManager(ProductActivity.this,3);
                product_recycle_view.setLayoutManager(layoutManager);

                adapter = new ProductAdapter(ProductActivity.this, product_list);
                product_recycle_view.setAdapter(adapter);
                product_recycle_view.setItemAnimator(new DefaultItemAnimator());

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
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.buy_dashboard, menu);

        MenuItem search = menu.findItem(R.id.buy_search);
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

                if (adapter != null) adapter.getFilter().filter(newText);
                return true;
            }
        });
    }

}
