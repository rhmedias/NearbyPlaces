package com.restaurantfinder.ui.search;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.restaurantfinder.model.Places;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Tosin Onikute.
 */

public class ListRestaurantAdapter
        extends RecyclerView.Adapter<ListRestaurantAdapter.ViewHolder> {

    private final TypedValue mTypedValue = new TypedValue();
    private int mBackground;
    private ArrayList<Places> mPlaces;
    Context mContext;
    RecyclerView recyclerView;
    private String aTitle;


    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;

        // fonts
        public Typeface typeFace;

        public ViewHolder(View view) {
            super(view);
            mView = view;



        }

        @Override
        public String toString() {
            return super.toString();
        }
    }

    public String getValueAt(int position) {
        return String.valueOf(mPlaces.get(position).getResults().get(0).getId());
    }

    public ListRestaurantAdapter(Context context, ArrayList<Places> places) {
        context.getTheme().resolveAttribute(R.attr.selectableItemBackground, mTypedValue, true);
        mContext = context;
        mBackground = mTypedValue.resourceId;
        mPlaces = places;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.display_list, parent, false);
        view.setBackgroundResource(mBackground);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        /* Set your values */

        final Places model = (Places) mPlaces.get(position);



    }

    @Override
    public int getItemCount() {
        return (null != mPlaces ? mPlaces.size() : 0);
    }

    public void addAll(List<Places> data){
        //mPlaces.addAll(data);
        notifyDataSetChanged();
    }

    public void clear(){
        mPlaces.clear();
    }

}

