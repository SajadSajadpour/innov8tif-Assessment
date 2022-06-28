package com.example.innov8tif.Network;

import retrofit.RestAdapter;

public class Api {

    public static ApiInterface getClient() {

        // base URL
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint("https://jsonplaceholder.typicode.com/") // the Root URL
                .build(); // building the adapter

        //Creating object for our interface
        ApiInterface api = adapter.create(ApiInterface.class);
        return api; // return the APIInterface object
    }
}
