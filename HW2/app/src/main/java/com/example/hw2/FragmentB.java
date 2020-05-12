package com.example.hw2;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.hw2.datamodel.City;
import com.example.hw2.datamodel.Weather;
import com.example.hw2.datamodel.WeatherType;
import com.example.hw2.network.CityResultsObserver;
import com.example.hw2.network.ForecastForACityResultsObserver;
import com.example.hw2.network.IpmaWeatherClient;
import com.example.hw2.network.WeatherTypesResultsObserver;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;


public class FragmentB extends Fragment {
    private TextView cidade;
    private String city;

    IpmaWeatherClient client = new IpmaWeatherClient();
    private HashMap<String, City> cities;
    private HashMap<Integer, WeatherType> weatherDescriptions;
    private LinkedList<Weather> mweatherlist = new LinkedList<>(); //lista da meteorologia

    private WeatherListAdapter mAdapter;
    private RecyclerView mRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_b, container, false);
        Bundle bundle = getArguments();
        if (getArguments() != null) {
            city = bundle.getString("message");
            callWeatherForecastForACityStep1(city);

        }else{
            city="";
        }
        System.out.println("cityyyyyyyyyyy "+city);
        return view;
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        cidade = view.findViewById(R.id.cidade);
        cidade.setText(city);
        mRecyclerView = view.findViewById(R.id.recyclerview);
        mAdapter = new WeatherListAdapter(getContext(), mweatherlist);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }


    private void callWeatherForecastForACityStep1(final String city) {

        // call the remote api, passing an (anonymous) listener to get back the results
        client.retrieveWeatherConditionsDescriptions(new WeatherTypesResultsObserver() {
            @Override
            public void receiveWeatherTypesList(HashMap<Integer, WeatherType> descriptorsCollection) {
                FragmentB.this.weatherDescriptions = descriptorsCollection;
                callWeatherForecastForACityStep2( city);
            }
            @Override
            public void onFailure(Throwable cause) {
                cidade.append("Failed to get weather conditions!");
            }
        });

    }

    private void callWeatherForecastForACityStep2(final String city) {
        client.retrieveCitiesList(new CityResultsObserver() {

            @Override
            public void receiveCitiesList(HashMap<String, City> citiesCollection) {
                FragmentB.this.cities = citiesCollection;
                City cityFound = cities.get(city);
                if( null != cityFound) {
                    int locationId = cityFound.getGlobalIdLocal();
                    callWeatherForecastForACityStep3(locationId);
                } else {
                    cidade.append("unknown city: " + city);
                }
            }

            @Override
            public void onFailure(Throwable cause) {
                cidade.append("Failed to get cities list!");
            }
        });
    }

    private void callWeatherForecastForACityStep3(int localId) {
        client.retrieveForecastForCity(localId, new ForecastForACityResultsObserver() {
            @Override
            public void receiveForecastList(List<Weather> forecast) {
                for (Weather day : forecast) {
                    mweatherlist.add(day);
                    System.out.println("AQUIIIIIIIIII"+mweatherlist);
                }
            }
            @Override
            public void onFailure(Throwable cause) {
                cidade.append( "Failed to get forecast for 5 days");
            }
        });

    }
}
