package com.example.hw2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hw2.datamodel.Weather;

import java.util.LinkedList;

public class WeatherListAdapter extends RecyclerView.Adapter<WeatherListAdapter.WeatherViewHold>{
    private LinkedList<Weather> mWeatherList;
    private LayoutInflater mInflater;
    Context context;

    //CLASS INTERNA
    public class WeatherViewHold extends RecyclerView.ViewHolder{
        private TextView tmax, tmin, chuva, vento, direcao, data;
        WeatherListAdapter mAdapter;

        public WeatherViewHold(@NonNull LinearLayout view, WeatherListAdapter adapter) {
            super(view);
            data = view.findViewById(R.id.data);
            tmax = view.findViewById(R.id.tmax);
            tmin = view.findViewById(R.id.tmin);
            chuva = view.findViewById(R.id.chuva);
            vento = view.findViewById(R.id.vento);
            direcao = view.findViewById(R.id.direcao);
            this.mAdapter = adapter;
        }
    }
    //###FIM DA CLASS INTERNA

    public WeatherListAdapter(Context context, LinkedList<Weather> mWeatherList){
        mInflater = LayoutInflater.from(context);
        this.mWeatherList = mWeatherList;
        this.context = context;
    }

    @NonNull
    @Override
    public WeatherListAdapter.WeatherViewHold onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.weather_list, parent, false);
        WeatherViewHold vh = new WeatherViewHold((LinearLayout)mItemView, this);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherViewHold holder, int position) {
        Weather weather = mWeatherList.get(position);
        holder.data.setText(weather.getForecastDate());
        holder.tmax.setText(String.valueOf(weather.getTMax())+" ºC");
        holder.tmin.setText(String.valueOf(weather.getTMin())+" ºC");
        holder.chuva.setText(String.valueOf(weather.getPrecipitaProb())+"%");
        holder.vento.setText(String.valueOf(weather.getClassWindSpeed())+" km/h");
        holder.direcao.setText(weather.getPredWindDir());
    }

    @Override
    public int getItemCount() {
        return mWeatherList.size();
    }


}
