package com.winklix.indu.healthcareapp.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
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
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.winklix.indu.healthcareapp.BuildConfig;
import com.winklix.indu.healthcareapp.Health_Api;
import com.winklix.indu.healthcareapp.Health_Shared_Pref;
import com.winklix.indu.healthcareapp.R;
import com.winklix.indu.healthcareapp.adapters.CategoryAdapter;
import com.winklix.indu.healthcareapp.adapters.ProductAdapter;
import com.winklix.indu.healthcareapp.api.RestClient;
import com.winklix.indu.healthcareapp.imageslider.FlipperLayout;
import com.winklix.indu.healthcareapp.imageslider.FlipperView;
import com.winklix.indu.healthcareapp.modals.Product_Modal;
import com.winklix.indu.healthcareapp.pojo.CategoryPojo;
import com.winklix.indu.healthcareapp.testlist.ExpandableListAdapter;
import com.winklix.indu.healthcareapp.testlist.JSONParser;
import com.winklix.indu.healthcareapp.testlist.MenuHolder;
import com.winklix.indu.healthcareapp.testlist.Menu_adapter;
import com.winklix.indu.healthcareapp.testlist.MyDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit.Callback;
import retrofit.RetrofitError;

public class BuyDashboardActivity extends AppCompatActivity implements View.OnClickListener
{

    FlipperLayout flipperLayout;
    RecyclerView category_recycle_view;
    Context context;
    private CategoryAdapter categoryAdapter;
    private List<Product_Modal> product_models;
    public  LinearLayoutManager layoutManager;
    ProgressDialog pd;
    TextView p_name_tv,p_email_tv;
    String pName_str,pEmail_str;
    Health_Shared_Pref health_shared_pref;

    RecyclerView buy_nav_recycler;
    ArrayAdapter<String> prod_adapter;
    ArrayList<String> prod_name=new ArrayList<>();

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    private ProgressDialog mprocessingdialog;
    private static String url = "http://2040healthcare.com/app/menu.php";
    private MyDialog dialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_dashboard);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        context = BuyDashboardActivity.this;

        dialog = new MyDialog(BuyDashboardActivity.this);

        mprocessingdialog = new ProgressDialog(this);
        expListView = (ExpandableListView) findViewById(R.id.nav_list);

  //      new BuyDashboardActivity.DownloadJason().execute();

        health_shared_pref = new Health_Shared_Pref(context);

        p_name_tv = (TextView) findViewById(R.id.p_name_tv);
        p_email_tv = (TextView) findViewById(R.id.p_email_tv);

        pName_str = health_shared_pref.getPrefranceStringValue(Health_Api.PatientName);
        pEmail_str = health_shared_pref.getPrefranceStringValue(Health_Api.PatientEmail);

        p_name_tv.setText(pName_str);
        p_email_tv.setText(pEmail_str);


        buy_nav_recycler = (RecyclerView) findViewById(R.id.buy_nav_recycler);
        prod_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, prod_name);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

       /* NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
*/
        prod_adapter.notifyDataSetChanged();
        ArrayList<MenuHolder> usersHolders = new ArrayList<>();

        MenuHolder usersHolder = new MenuHolder();
        Uri alllead = Uri.parse("android.resource://" + BuildConfig.APPLICATION_ID + "/" + R.drawable.ic_home);
        String s = alllead.toString();
        usersHolder.setTitleName("HOME");
        usersHolder.setIcons(s);
        usersHolders.add(usersHolder);



        MenuHolder usersHolder2 = new MenuHolder();
        Uri user2 = Uri.parse("android.resource://" + BuildConfig.APPLICATION_ID + "/" + R.drawable.ic_history);
        String s2 = user2.toString();
        usersHolder2.setTitleName("My Account");
        usersHolder2.setIcons(s2);
        usersHolders.add(usersHolder2);

        MenuHolder usersHolder3 = new MenuHolder();
        Uri user3 = Uri.parse("android.resource://" + BuildConfig.APPLICATION_ID + "/" + R.drawable.ic_history);
        String s3 = user3.toString();
        usersHolder3.setTitleName("Need Help");
        usersHolder3.setIcons(s3);
        usersHolders.add(usersHolder3);

        MenuHolder usersHolder4 = new MenuHolder();
        Uri user4 = Uri.parse("android.resource://" + BuildConfig.APPLICATION_ID + "/" + R.drawable.ic_history);
        String s4 = user4.toString();
        usersHolder4.setTitleName("Settings");
        usersHolder4.setIcons(s4);
        usersHolders.add(usersHolder4);

        MenuHolder usersHolder5 = new MenuHolder();
        Uri user5 = Uri.parse("android.resource://" + BuildConfig.APPLICATION_ID + "/" + R.drawable.ic_history);
        String s5 = user5.toString();
        usersHolder5.setTitleName("CONTACT US");
        usersHolder5.setIcons(s5);
        usersHolders.add(usersHolder5);


        MenuHolder usersHolder7 = new MenuHolder();
        Uri user7 = Uri.parse("android.resource://" + BuildConfig.APPLICATION_ID + "/" + R.drawable.ic_history);
        String s7 = user7.toString();
        usersHolder7.setTitleName("SHARE");
        usersHolder7.setIcons(s7);
        usersHolders.add(usersHolder7);

        MenuHolder usersHolder8 = new MenuHolder();
        Uri user8 = Uri.parse("android.resource://" + BuildConfig.APPLICATION_ID + "/" + R.drawable.ic_history);
        String s8 = user8.toString();
        usersHolder8.setTitleName("Feedback");
        usersHolder8.setIcons(s8);
        usersHolders.add(usersHolder8);

        Menu_adapter menu_adapter = new Menu_adapter(BuyDashboardActivity.this, usersHolders);

        layoutManager = new LinearLayoutManager(this);
        buy_nav_recycler.setLayoutManager(layoutManager);
        buy_nav_recycler.setAdapter(menu_adapter);

        flipperLayout = (FlipperLayout) findViewById(R.id.flipper_layout);

        setLayout();

        category_recycle_view = (RecyclerView) findViewById(R.id.category_recycle_view);

        GetCategoryList();

        product_models = new ArrayList<>();

    }
   //     getCategoryFromDB("");


/*        layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        prod_recycle_view.setLayoutManager(layoutManager);*/



//        prod_recycle_view.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//
//                if (layoutManager.findLastCompletelyVisibleItemPosition() == product_models.size() - 1) {
//
//                }}});}

    private void GetCategoryList(){
        dialog.ShowProgressDialog();
        RestClient.get().category(new Callback<CategoryPojo>() {
            @Override
            public void success(CategoryPojo categoryPojo, retrofit.client.Response response) {
                layoutManager = new GridLayoutManager(context,2);
                category_recycle_view.setLayoutManager(layoutManager);

                categoryAdapter = new CategoryAdapter(context, categoryPojo.getData());
                category_recycle_view.setAdapter(categoryAdapter);
                category_recycle_view.setItemAnimator(new DefaultItemAnimator());

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

//    private void getCategoryFromDB(String cat) {
//        final AsyncTask<String, Void, Void> asyncTaskprog = new AsyncTask<String, Void, Void>() {
//
//            @Override
//
//            protected Void doInBackground(String... Ids) {
//
//                OkHttpClient clientp = new OkHttpClient();
//
//                okhttp3.RequestBody body = new FormBody.Builder().
//                        add("", "")
//                        .build();
//
//                Request requestp = new Request.Builder().post(body)
//                        .url("http://test.2040healthcare.com/app/product.php")
//                        .build();
//
//                try {
//                    Response responsep = clientp.newCall(requestp).execute();
//
//
//                    if (responsep.body().toString() == null) {
//                        // no_his_tv.setVisibility(View.VISIBLE);
//                        return null;
//                    } else {
//                        JSONObject jsonObject = new JSONObject(responsep.body().string());
//                        JSONArray jsonarray1 = jsonObject.getJSONArray("data"); //dta
//
//                        for (int i = 0; i < jsonarray1.length(); i++) {
//
//                            JSONObject objectp = jsonarray1.getJSONObject(i);
//
//                            Product_Modal product_model = new Product_Modal(objectp.getString("prod_id"),
//                                    objectp.getString("prod_name"), objectp.getString("product_images"),
//                                    objectp.getString("prod_price"), objectp.getString("prod_description"));
//
//                            BuyDashboardActivity.this.product_models.add(product_model);
//
//                        }
//                    }
//                } catch (JSONException | IOException e) {
//                    e.printStackTrace();
//                }
//                return null;
//            }
//
//            @Override
//            protected void onPreExecute() {
//                super.onPreExecute();
//                pd = new ProgressDialog(context);
//                pd.setMessage("Please wait...");
//                pd.setCancelable(false);
//                pd.setCanceledOnTouchOutside(false);
//                pd.show();
//            }
//
//
//            @Override
//            protected void onPostExecute(Void aVoid) {
//                productAdapter.notifyDataSetChanged();
//                pd.dismiss();
//            }
//        };
//
//        asyncTaskprog.execute("");
//    }

    private void setLayout() {
        String url[] = new String[]{
                "http://gomerblog.com/wp-content/uploads/2017/09/new-consult.jpg",
                "https://png.pngtree.com/thumb_back/fh260/back_pic/03/71/99/0757b86faa36ca9.jpg",
                "https://images.theconversation.com/files/61137/original/rw63cq29-1412745846.jpg?ixlib=rb-1.1.0&q=45&auto=format&w=926&fit=clip",
                "http://images.indianexpress.com/2015/03/medicines-l.jpg",
                "http://newsfirst.lk/english/wp-content/uploads/2016/03/medicine.jpg",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRxrVx5myHxYg7aZNagcyek8JAgxGNVg39NGxdqbidNc9MZf3clNA",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTB8lGjDYvCVBs3mNr8sQLi3YkALIyqU2RHuAWR6j4wY3yda2RA",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ9yzrmsn22NFdMTI8Fj0K30vmvePIMZeWKB3Ijvr4tTu_XQAOpCg",
                "http://www.bloglet.com/gallery/the-advantages-of-studying-medicine/the-advantages-of-studying-medicine_2.jpg"};

        for (int i = 0; i < 9; i++)
        {
            FlipperView view = new FlipperView(this);
            view.setImageUrl(url[i]).setDescription("");
            flipperLayout.addFlipperView(view);
            view.setOnFlipperClickListener(new FlipperView.OnFlipperClickListener() {
                @Override
                public void onFlipperClick(FlipperView flipperView) {

                }
            });}}


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
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_navigation_menu:
      //          finalize();
        }
    }

    private class DownloadJason extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... arg0) {

            JSONParser jp = new JSONParser();
            String jsonstr = jp.makeServiceCall(url);
            Log.d("Response = ", jsonstr);

            if (jsonstr != null) {
                listDataHeader = new ArrayList<String>();
                listDataChild = new HashMap<String, List<String>>();

                try {

                    JSONObject jobj = new JSONObject(jsonstr);
                    JSONArray jaMainmenu = jobj.getJSONArray("mainmenu");
                    JSONObject objMainMenu = jaMainmenu.getJSONObject(0);
                    JSONArray arrayCategory = objMainMenu.getJSONArray("category");

                    for (int hk = 0; hk < arrayCategory.length(); hk++) {
                        JSONObject d = arrayCategory.getJSONObject(hk);

                        listDataHeader.add(d.getString("categoryname"));

                        List<String> lease_offer = new ArrayList<String>();
                        JSONArray subArray = d.getJSONArray("subcategory");
                        for (int y = 0; y < subArray.length(); y++) {

                            JSONObject sub_eachObj = subArray.getJSONObject(y);
                            lease_offer.add(sub_eachObj.getString("subcategoryname"));

                            listDataChild.put(listDataHeader.get(hk), lease_offer);

                        }}
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            } else {
                Toast.makeText(getApplicationContext(),
                        "Please Check internet Connection", Toast.LENGTH_SHORT)
                        .show();

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            mprocessingdialog.dismiss();
            listAdapter = new ExpandableListAdapter(getApplicationContext(), listDataHeader, listDataChild);
            expListView.setAdapter(listAdapter);

        }}

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//
//        getMenuInflater().inflate(R.menu.buy_dashboard, menu);
//
//        MenuItem search = menu.findItem(R.id.buy_search);
//        SearchView searchView = (SearchView) MenuItemCompat.getActionView(search);
//        search(searchView);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//
//        return super.onOptionsItemSelected(item);
//    }
//
//    private void search(SearchView searchView) {
//
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
//                if (categoryAdapter != null) categoryAdapter.getFilter().filter(newText);
//                return true;
//            }
//        });
//    }

}