package com.restaurantfinder.ui.list;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.restaurantfinder.R;
import com.restaurantfinder.client.RestClient;
import com.restaurantfinder.model.NearbyPlaces;
import com.restaurantfinder.model.Result;
import com.restaurantfinder.ui.search.ListNearbyAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListNearbyPlacesActivity extends AppCompatActivity {

    private String TAG = this.getClass().getSimpleName();
    private ListNearbyAdapter adapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;

    private String lnglat = "";
    private String type;
    private int radius;
    private String keyword;
    private String apiKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_nearby_places);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            // values
            lnglat = extras.getString("lnglat");
        }

        type = "restaurant";
        radius = 500;
        keyword = "cruise";

        // Get API KEY
        apiKey = getApplicationContext().getResources().getString(R.string.GEO_API_KEY);

        recyclerView = (RecyclerView) findViewById(R.id.places_recyclerview);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        Call<NearbyPlaces> call =
                RestClient.getInstance().getPlaces(
                        lnglat,
                        radius,
                        keyword,
                        type,
                        apiKey
                );

        call.enqueue(new Callback<NearbyPlaces>() {
            @Override
            public void onResponse(Call<NearbyPlaces> call, Response<NearbyPlaces> response) {
                if(response.isSuccessful()){
                    NearbyPlaces nearbyPlaces = response.body();
                    ArrayList<Result> nearbyPlacesList = new ArrayList<Result>(nearbyPlaces.getResults());

                    adapter = new ListNearbyAdapter(getApplicationContext(), nearbyPlacesList);
                    recyclerView.setAdapter(adapter);

                } else {
                    //
                }
            }

            @Override
            public void onFailure(Call<NearbyPlaces> call, Throwable t) {
                Log.d(TAG, t.getLocalizedMessage());
            }
        });




    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
