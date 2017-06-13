package com.restaurantfinder.ui.search;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.restaurantfinder.R;
import com.restaurantfinder.model.NearbyPlaces;
import com.restaurantfinder.model.Result;
import com.restaurantfinder.ui.detail.PlacesDetailActivity;
import com.restaurantfinder.ui.list.ListNearbyPlacesActivity;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Tosin Onikute.
 */

public class ListNearbyAdapter
        extends RecyclerView.Adapter<ListNearbyAdapter.ViewHolder> {

    private final TypedValue mTypedValue = new TypedValue();
    private int mBackground;
    private ArrayList<Result> mPlaces;
    Context mContext;
    RecyclerView recyclerView;
    private String aTitle;
    private String mName;
    private String mVicinity;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView name;
        public final TextView vicinity;

        // fonts
        public Typeface typeFace;

        public ViewHolder(View view) {
            super(view);
            mView = view;

            name = (TextView) view.findViewById(R.id.name);
            vicinity = (TextView) view.findViewById(R.id.vicinity);

        }

        @Override
        public String toString() {
            return super.toString();
        }
    }

    public String getValueAt(int position) {
        return String.valueOf(mPlaces.get(0).getId());
    }

    public ListNearbyAdapter(Context context, ArrayList<Result> places) {
        context.getTheme().resolveAttribute(R.attr.selectableItemBackground, mTypedValue, true);
        mContext = context;
        mBackground = mTypedValue.resourceId;
        mPlaces = places;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.restaurant_list, parent, false);
        view.setBackgroundResource(mBackground);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        /* Set your values */

        final Result model = (Result) mPlaces.get(position);

        mName = "";
        mVicinity = "";

        if (model.getName() != null) {
            mName = model.getName() ;
        }

        if (model.getName() != null) {
            mVicinity = model.getName() ;
        }

        holder.name.setText(mName);
        holder.vicinity.setText(mVicinity);


        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, ListNearbyPlacesActivity.class);
                intent.putExtra("latlng", "1");
                context.startActivity(intent);
            }
        });



    }

    @Override
    public int getItemCount() {
        return (null != mPlaces ? mPlaces.size() : 0);
    }

    public void addAll(List<NearbyPlaces> data){
        //mPlaces.addAll(data);
        notifyDataSetChanged();
    }

    public void clear(){
        mPlaces.clear();
    }

}

