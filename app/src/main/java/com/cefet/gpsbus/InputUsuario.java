package com.cefet.gpsbus;

import com.google.android.gms.location.places.Place;
import com.google.android.gms.maps.model.LatLng;

/**
 * Created by diego on 10/03/2018.
 */

public class InputUsuario {

    private static String tipoPesquisa;
    private static Place placeDestino;
    private static Place placeOrigem;
    private static LatLng latLngOrigem;
    private static LatLng latLngDestino;
    private static int HoraSaida;
    private static int minutoSaida;
    private static int diaDoMes;
    private static int DiaDaSemana;

    public static void setNULL(){
        InputUsuario.tipoPesquisa = null;
        InputUsuario.placeDestino = null;
    }

    public static String getTipoPesquisa() {
        return tipoPesquisa;
    }

    public static void setTipoPesquisa(String tipoPesquisa) {
        InputUsuario.tipoPesquisa = tipoPesquisa;
    }

    public static Place getPlaceDestino() {
        return placeDestino;
    }

    public static void setPlaceDestino(Place placeDestino) {
        InputUsuario.placeDestino = placeDestino;
    }

    public static Place getPlaceOrigem() {
        return placeOrigem;
    }

    public static void setPlaceOrigem(Place placeOrigem) {
        InputUsuario.placeOrigem = placeOrigem;
    }

    public static int getHoraSaida() {
        return HoraSaida;
    }

    public static void setHoraSaida(int horaSaida) {
        HoraSaida = horaSaida;
    }

    public static int getMinutoSaida() {
        return minutoSaida;
    }

    public static void setMinutoSaida(int minutoSaida) {
        InputUsuario.minutoSaida = minutoSaida;
    }

    public static int getDiaDoMes() {
        return diaDoMes;
    }

    public static void setDiaDoMes(int diaDoMes) {
        InputUsuario.diaDoMes = diaDoMes;
    }

    public static int getDiaDaSemana() {
        return DiaDaSemana;
    }

    public static void setDiaDaSemana(int diaDaSemana) {
        InputUsuario.DiaDaSemana = diaDaSemana;
    }

    public static LatLng getLatLngOrigem() {
        return latLngOrigem;
    }

    public static void setLatLngOrigem(LatLng latLngOrigem) {
        InputUsuario.latLngOrigem = latLngOrigem;
    }

    public static LatLng getLatLngDestino() {
        return latLngDestino;
    }

    public static void setLatLngDestino(LatLng latLngDestino) {
        InputUsuario.latLngDestino = latLngDestino;
    }
}
