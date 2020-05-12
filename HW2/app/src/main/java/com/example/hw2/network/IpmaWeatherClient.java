package com.example.hw2.network;

import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.hw2.datamodel.City;
import com.example.hw2.datamodel.CityGroup;
import com.example.hw2.datamodel.Weather;
import com.example.hw2.datamodel.WeatherGroup;
import com.example.hw2.datamodel.WeatherType;
import com.example.hw2.datamodel.WeatherTypeGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class IpmaWeatherClient {

    private IpmaApiInterface apiService;

    public IpmaWeatherClient() {
        Retrofit retrofitInstance = RetrofitInstance.getRetrofitInstance();
        this.apiService = retrofitInstance.create(IpmaApiInterface.class);
    }

    /*private void getDistritos(){
        final HashMap<String, City> cities = new HashMap<>();
        Call<CityGroup> call = apiService.getCityParent();
        call.enqueue(new Callback<CityGroup>() {
            @Override
            public void onResponse(Call<CityGroup> call, Response<CityGroup> response) {
                CityGroup citiesGroup = response.body();
                for (City city : citiesGroup.getData()) {
                    cities.put(city.getLocal(), city);
                }
            }
            @Override
            public void onFailure(Call<CityGroup> call, Throwable t) {
                Log.e("main", "errog calling remote api: " + t.getLocalizedMessage());
            }
        });
    }*/
    /**
     * get a list of cities (districts)
     * @param listener a listener object to callback with the results
     */
    public void retrieveCitiesList(final CityResultsObserver listener) {
        final HashMap<String, City> cities = new HashMap<>();

        Call<CityGroup> call = apiService.getCityParent();
        call.enqueue(new Callback<CityGroup>() {
            @Override
            public void onResponse(Call<CityGroup> call, Response<CityGroup> response) {
                int statusCode = response.code();
                CityGroup citiesGroup = response.body();
                for (City city : citiesGroup.getData()) {
                    cities.put(city.getLocal(), city);
                }
                listener.receiveCitiesList( cities);
            }

            @Override
            public void onFailure(Call<CityGroup> call, Throwable t) {
                Log.e("main", "errog calling remote api: " + t.getLocalizedMessage());
                listener.onFailure( t);
            }
        });
    }

    /**
     * get the dictionary for the weather states
     * @param listener a listener object to callback with the results
     */
    public void retrieveWeatherConditionsDescriptions(final WeatherTypesResultsObserver listener) {
        final HashMap<Integer, WeatherType> weatherDescriptions = new HashMap<>();

        Call<WeatherTypeGroup> call = apiService.getWeatherTypes();
        call.enqueue(new Callback<WeatherTypeGroup>() {
            @Override
            public void onResponse(Call<WeatherTypeGroup> call, Response<WeatherTypeGroup> response) {
                int statusCode = response.code();
                WeatherTypeGroup weatherTypesGroup = response.body();
                for ( WeatherType weather: weatherTypesGroup.getTypes() ) {
                    weatherDescriptions.put(weather.getIdWeatherType(), weather);
                }
                listener.receiveWeatherTypesList(weatherDescriptions);
            }

            @Override
            public void onFailure(Call<WeatherTypeGroup> call, Throwable t) {
                Log.e( "main", "errog calling remote api: " + t.getLocalizedMessage());
                listener.onFailure( t);
            }
        });
    }

    /**
     * get the 5-day forecast for a city
     * @param  localId the global identifier of the location
     * @param listener a listener object to callback with the results
     */
    public void retrieveForecastForCity(int localId, final ForecastForACityResultsObserver listener) {
        final List<Weather> forecast = new ArrayList<>();

        Call<WeatherGroup> call = apiService.getWeatherParent(localId);
        call.enqueue(new Callback<WeatherGroup>() {
            @Override
            public void onResponse(Call<WeatherGroup> call, Response<WeatherGroup> response) {
                int statusCode = response.code();
                WeatherGroup weatherTypesGroup = response.body();
                forecast.addAll(weatherTypesGroup.getForecasts() );
                listener.receiveForecastList(forecast);
            }

            @Override
            public void onFailure(Call<WeatherGroup> call, Throwable t) {
                Log.e( "main", "errog calling remote api: " + t.getLocalizedMessage());
                listener.onFailure( t);
            }
        });

    }

}
