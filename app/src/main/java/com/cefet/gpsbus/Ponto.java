package com.cefet.gpsbus;

/**
 * Created by diego on 05/03/2018.
 */

public class Ponto {

    private String id;
    private double latitude;
    private double longitude;
    private String referencia;
    private String bairro;

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        double lat;
        lat = Double.parseDouble(latitude);
        this.latitude = lat;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        double lng;
        lng = Double.parseDouble(longitude);
        this.longitude = lng;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
