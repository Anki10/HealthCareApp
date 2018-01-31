package com.winklix.indu.healthcareapp.api;


import com.squareup.okhttp.OkHttpClient;
import com.winklix.indu.healthcareapp.testlist.Appconstant;

import retrofit.RestAdapter;
import retrofit.client.OkClient;

public class RestClient {

    private static Api REST_CLIENT;
    private static String ROOT = Appconstant.BASE_URL;

    static {
        setupRestClient();
    }

    private RestClient() {
    }

    public static Api get() {
        return REST_CLIENT;
    }


    private static void setupRestClient() {
        RestAdapter.Builder builder = new RestAdapter.Builder()
                .setEndpoint(ROOT)
                .setClient(new OkClient(new OkHttpClient()));
        builder.setLogLevel(RestAdapter.LogLevel.FULL);

        RestAdapter restAdapter = builder.build();
        REST_CLIENT = restAdapter.create(Api.class);
    }
}