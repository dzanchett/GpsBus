package com.cefet.gpsbus;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;
import java.util.List;

public class TelaMapaPontosOnibus extends AppCompatActivity implements OnMapReadyCallback, /*GoogleMap.OnCameraMoveListener,*/ GoogleMap.OnMarkerClickListener {

    private GoogleMap mMap;
    private FusedLocationProviderClient mFusedLocationClient;
    private boolean localizacaoObtida;
    private Location localizacaoAtual;
    private List<Ponto> listaPontos;
    private List<Marker> marcadoresAtivos;
    private boolean cacelated;
    private Thread t;

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |      Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_mapa_pontos_onibus);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        t = new Thread(new Runnable() {
            @Override
            public void run() {
                listaPontos = DB.listarPontosETerminais();
            }
        });

        t.start();

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mFusedLocationClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location == null) {
                    //falha;
                    localizacaoObtida = false;
                    centralizarMapa(mMap);
                    //Log.d("Localização Obtida", "Falha!");
                } else {
                    localizacaoAtual = location;
                    localizacaoObtida = true;
                    centralizarMapa(mMap);
                    //Log.d("variavel ini", String.valueOf(localizacaoObtida));
                    //Log.d("Localização Obtida", "Sucesso!");
                }
            }
        });

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapaPontos);
        mapFragment.getMapAsync(this);

        marcadoresAtivos = new ArrayList<Marker>();
    }

    @Override
    public void onMapReady(GoogleMap googleMap){
        mMap = googleMap;

        //mMap.setOnCameraMoveListener(this);
        mMap.setOnMarkerClickListener(this);
    }

    private void centralizarMapa(GoogleMap googleMap){
        final LatLng centroMapa;

        if(localizacaoObtida){
            centroMapa = new LatLng(localizacaoAtual.getLatitude(), localizacaoAtual.getLongitude());
        }else{
            centroMapa = new LatLng(-22.5086527, -43.1787965);
        }

        cacelated = false;

        CameraUpdate location = CameraUpdateFactory.newLatLngZoom(centroMapa, 17);
        googleMap.animateCamera(location, 1500, new GoogleMap.CancelableCallback() {
            @Override
            public void onFinish() {
                try {
                    t.join();

                    for(Ponto p : listaPontos){
                        LatLng l = new LatLng(p.getLatitude(), p.getLongitude());
                        Marker m = mMap.addMarker(new MarkerOptions().position(l).title(p.getReferencia() + ", " + p.getBairro() + "."));
                        m.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.bus_stop_icon));
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                mMap.setMinZoomPreference(16);
            }

            @Override
            public void onCancel() {
                try {
                    t.join();

                    for(Ponto p : listaPontos){
                        LatLng l = new LatLng(p.getLatitude(), p.getLongitude());
                        Marker m = mMap.addMarker(new MarkerOptions().position(l).title(p.getReferencia() + ", " + p.getBairro() + "."));
                        m.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.bus_stop_icon));
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /*@Override
    public void onCameraMove() {
        LatLng centroMapaAtual = mMap.getCameraPosition().target;
        List<Ponto> pontosProximos = new ArrayList<Ponto>();

        for (Marker m : marcadoresAtivos) {
            m.remove();
        }

        for(Ponto p : listaPontos){
            if(Haversine.distancia(centroMapaAtual.latitude, centroMapaAtual.longitude, p.getLatitude(), p.getLongitude())<=1){
                pontosProximos.add(p);
            }
        }

        for(Ponto p : pontosProximos){
            LatLng l = new LatLng(p.getLatitude(), p.getLongitude());
            Marker m = mMap.addMarker(new MarkerOptions().position(l).title(p.getReferencia() + ", " + p.getBairro() + "."));
            m.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.bus_stop_icon));
            marcadoresAtivos.add(m);
        }
    }*/

    @Override
    public boolean onMarkerClick(Marker marker) {
        marker.showInfoWindow();
        return true;
    }
}
