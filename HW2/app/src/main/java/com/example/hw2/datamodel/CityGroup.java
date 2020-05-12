package com.example.hw2.datamodel;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CityGroup {

    @SerializedName("owner")
    @Expose
    private String owner;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("data")
    @Expose
    private List<City> data = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public CityGroup() {
    }

    /**
     *
     * @param owner
     * @param country
     * @param data
     */
    public CityGroup(String owner, String country, List<City> data) {
        super();
        this.owner = owner;
        this.country = country;
        this.data = data;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<City> getData() {
        return data;
    }

    public void setData(List<City> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "CityGroup{" +
                "owner='" + owner + '\'' +
                ", country='" + country + '\'' +
                ", data=" + data +
                '}';
    }
}
