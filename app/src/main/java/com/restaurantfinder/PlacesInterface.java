package com.restaurantfinder;

import com.restaurantfinder.model.Places;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @author Tosin Onikute.
 */

public interface PlacesInterface {

    @GET("/place/{latlng}")
    Call<List<Places>> getPlaces(@Path("latlng") String latlng);


}
