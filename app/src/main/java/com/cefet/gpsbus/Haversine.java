package com.cefet.gpsbus;

/**
 * Created by diego on 05/03/2018.
 */

public class Haversine {
    private static final int RaioTerra = 6371;

    public static double distancia(double latInicio, double longInicio,
                                   double latFinal, double longFinal) {

        double dLat  = Math.toRadians((latFinal - latInicio));
        double dLong = Math.toRadians((longFinal - longInicio));

        latInicio = Math.toRadians(latInicio);
        latFinal   = Math.toRadians(latFinal);

        double a = haversin(dLat) + Math.cos(latInicio) * Math.cos(latFinal) * haversin(dLong);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return RaioTerra * c;
    }

    public static double haversin(double val) {
        return Math.pow(Math.sin(val / 2), 2);
    }
}
