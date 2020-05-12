package com.example.hw2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;


import com.example.hw2.datamodel.City;
import com.example.hw2.network.CityResultsObserver;
import com.example.hw2.network.IpmaWeatherClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class CitiesListAdapter extends RecyclerView.Adapter<CitiesListAdapter.CitiesViewHold> {
    IpmaWeatherClient client = new IpmaWeatherClient();
    private HashMap<String, City> cities;
    private LinkedList<String> mCitiesList;
    private LayoutInflater mInflater;
    Context context;
    OnCityListener onCityListener;

    //CLASS INTERNA
    public class CitiesViewHold extends RecyclerView.ViewHolder implements View.OnClickListener{
        OnCityListener onCityListener;
        public TextView citiesList;
        CitiesListAdapter mAdapter;

        public CitiesViewHold(View itemView, CitiesListAdapter adapter, OnCityListener onCityListener) {
            super(itemView);
            citiesList = itemView.findViewById(R.id.word);
            this.mAdapter = adapter;
            this.onCityListener = onCityListener;
            itemView.setOnClickListener(this);
        }

        @SuppressLint("ResourceType")
        @Override
        public void onClick(View v) {
            int mPosition = getAdapterPosition();

            onCityListener.onCityClick(mPosition);
        }
    }
    ///*****************TERMINA CLASSE INTERNA***************/

    public CitiesListAdapter(Context context, LinkedList<String> mCitiesList, OnCityListener onCityListener){
        mInflater = LayoutInflater.from(context);
        this.mCitiesList = mCitiesList;
        this.context = context;
        this.onCityListener = onCityListener;
    }

    @Override
    public CitiesListAdapter.CitiesViewHold onCreateViewHolder(ViewGroup parent, int viewType) {
        View mItemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.city_list, parent, false);
        return new CitiesViewHold(mItemView, this, onCityListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CitiesViewHold holder, int position) {
        // Retrieve the data for that position.
        String mCurrent = mCitiesList.get(position);
        // Add the data to the view holder.
        holder.citiesList.setText(mCurrent);
    }

    @Override
    public int getItemCount() {
        return mCitiesList.size();
    }

    public interface OnCityListener{
        void onCityClick(int position);
    }


}
