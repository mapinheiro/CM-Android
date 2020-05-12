package com.example.hw2.datamodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class City {

    @SerializedName("idRegiao")
    @Expose
    private int idRegiao;
    @SerializedName("idAreaAviso")
    @Expose
    private String idAreaAviso;
    @SerializedName("idConcelho")
    @Expose
    private int idConcelho;
    @SerializedName("globalIdLocal")
    @Expose
    private int globalIdLocal;
    @SerializedName("latitude")
    @Expose
    private String latitude;
    @SerializedName("idDistrito")
    @Expose
    private int idDistrito;
    @SerializedName("local")
    @Expose
    private String local;
    @SerializedName("longitude")
    @Expose
    private String longitude;

    /**
     * No args constructor for use in serialization
     *
     */
    public City() {
    }

    /**
     *
     * @param idAreaAviso
     * @param globalIdLocal
     * @param latitude
     * @param idRegiao
     * @param idDistrito
     * @param idConcelho
     * @param local
     * @param longitude
     */
    public City(int idRegiao, String idAreaAviso, int idConcelho, int globalIdLocal, String latitude, int idDistrito, String local, String longitude) {
        super();
        this.idRegiao = idRegiao;
        this.idAreaAviso = idAreaAviso;
        this.idConcelho = idConcelho;
        this.globalIdLocal = globalIdLocal;
        this.latitude = latitude;
        this.idDistrito = idDistrito;
        this.local = local;
        this.longitude = longitude;
    }

    public int getIdRegiao() {
        return idRegiao;
    }

    public void setIdRegiao(int idRegiao) {
        this.idRegiao = idRegiao;
    }

    public String getIdAreaAviso() {
        return idAreaAviso;
    }

    public void setIdAreaAviso(String idAreaAviso) {
        this.idAreaAviso = idAreaAviso;
    }

    public int getIdConcelho() {
        return idConcelho;
    }

    public void setIdConcelho(int idConcelho) {
        this.idConcelho = idConcelho;
    }

    public int getGlobalIdLocal() {
        return globalIdLocal;
    }

    public void setGlobalIdLocal(int globalIdLocal) {
        this.globalIdLocal = globalIdLocal;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public int getIdDistrito() {
        return idDistrito;
    }

    public void setIdDistrito(int idDistrito) {
        this.idDistrito = idDistrito;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "City{" +
                "idRegiao=" + idRegiao +
                ", idAreaAviso='" + idAreaAviso + '\'' +
                ", idConcelho=" + idConcelho +
                ", globalIdLocal=" + globalIdLocal +
                ", latitude='" + latitude + '\'' +
                ", idDistrito=" + idDistrito +
                ", local='" + local + '\'' +
                ", longitude='" + longitude + '\'' +
                '}';
    }
}
