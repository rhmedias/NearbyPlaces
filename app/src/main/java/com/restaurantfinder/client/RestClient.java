package com.restaurantfinder.client;

import com.restaurantfinder.PlacesInterface;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.restaurantfinder.Api.BASE_URL;

/**
 * @author Tosin Onikute.
 */

public class RestClient {

    private static PlacesInterface REST_CLIENT;
    private static String url = BASE_URL;

    public RestClient() {}

    public static PlacesInterface getInstance() {
        if (REST_CLIENT == null) {
            setupRestClient();
        }
        return REST_CLIENT;
    }


    private static void setupRestClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        REST_CLIENT = retrofit.create(PlacesInterface.class);
    }

}
