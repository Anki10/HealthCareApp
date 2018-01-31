package com.winklix.indu.healthcareapp.testlist;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.winklix.indu.healthcareapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends Activity {

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    private ProgressDialog mprocessingdialog;
    private static String url = "http://test.2040healthcare.com/app/menu.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mprocessingdialog = new ProgressDialog(this);
        expListView = (ExpandableListView) findViewById(R.id.lvExp);

        new DownloadJason().execute();
    }

    private class DownloadJason extends AsyncTask<Void, Void, Void> {


        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();

            mprocessingdialog.setTitle("Please Wait..");
            mprocessingdialog.setMessage("Loading");
            mprocessingdialog.setCancelable(false);
            mprocessingdialog.setIndeterminate(false);
            mprocessingdialog.show();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            // TODO Auto-generated method stub

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

        }
    }

}