package com.example.hw2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;

import com.example.hw2.datamodel.City;
import com.example.hw2.datamodel.WeatherType;
import com.example.hw2.network.CityResultsObserver;
import com.example.hw2.network.IpmaWeatherClient;
import com.example.hw2.network.RetrofitInstance;
import com.example.hw2.network.WeatherTypesResultsObserver;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.HashMap;
import java.util.LinkedList;



public class FragmentA extends Fragment implements CitiesListAdapter.OnCityListener {
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    IpmaWeatherClient client = new IpmaWeatherClient();
    private HashMap<String, City> cities;
    private LinkedList<String> mCitiesList = new LinkedList<>(); //lista das cidades

    private CitiesListAdapter mAdapter;
    private RecyclerView mRecyclerView;

    public FragmentA(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_a, container, false);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mCitiesList = callCitties();
        //RecycleView
        mRecyclerView = view.findViewById(R.id.recyclerview);
        mAdapter = new CitiesListAdapter(getContext(), mCitiesList,this);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

    }

    @Override
    public void onCityClick(int position) {
        String elem = mCitiesList.get(position);
        System.out.println("ELEMENTOPPP----"+elem);

        Intent intent = new Intent(getContext(), ActivityB.class);
        intent.putExtra(EXTRA_MESSAGE, elem);
        startActivity(intent);

    }

    private LinkedList<String> callCitties() {
        client.retrieveCitiesList(new CityResultsObserver() {
            @Override
            public void receiveCitiesList(HashMap<String, City> citiesCollection) {
                FragmentA.this.cities = citiesCollection;

                for(City cidade: cities.values()){
                    mCitiesList.add(cidade.getLocal());
                }
            }
            @Override
            public void onFailure(Throwable cause) {
                mCitiesList.add("Failed to get cities list!");
            }
        });
        System.out.println("AQuiiiiiiii Cidades "+ mCitiesList);
        return mCitiesList;
    }
}
