package com.winklix.indu.healthcareapp.services;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Web_service_new extends AsyncTask<String, Void, String> {

    CallBacks callback;
    byte[] bytes_data;
    String coming_url;
    String abc;
    ProgressDialog pd;
    Context context;
    public static String cookSTr;

    public Web_service_new(Context con, String url1, CallBacks callback1, byte[] bytes, String cookies) {
        this.context = con;
        this.coming_url = url1;
        this.callback = callback1;
        this.bytes_data = bytes;
        this.cookSTr = cookies;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pd = new ProgressDialog(context);
        pd.setMessage("Please wait...");
        pd.setCancelable(false);
        pd.setCanceledOnTouchOutside(false);
        pd.show();
    }

    @Override
    protected String doInBackground(String... params) {

        try {
            URL url = new URL(coming_url);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");

            if (cookSTr != null) {
                connection.setRequestProperty("Cookie", cookSTr);
            }
            connection.connect();

            DataOutputStream streamWriter = new DataOutputStream(connection.getOutputStream());
            streamWriter.write(bytes_data);/// passed json parameter in byte
            streamWriter.flush();
            streamWriter.close();

            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            if (cookSTr == null) {

                String ary = connection.getHeaderField("Set-Cookie");
                if (ary != null && ary.contains(";")) {
                    cookSTr = ary.split(";")[0];
                }
            }

            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            br.close();
            abc = sb.toString();

            Log.e("return data web service", "" + abc);

            return sb.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }


        return null;
    }

    @Override
    protected void onPostExecute(String ss) {
        super.onPostExecute(ss);

        callback.result(ss);

        pd.dismiss();
    }
}
