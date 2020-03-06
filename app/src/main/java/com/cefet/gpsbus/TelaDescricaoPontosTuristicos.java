package com.cefet.gpsbus;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;

import java.util.Calendar;
import java.util.List;

public class TelaDescricaoPontosTuristicos extends AppCompatActivity {

    private String url;
    private double lat;
    private double lng;
    private LocationManager mLocationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_descricao_pontos_turisticos);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String nomePontoTuristico = getIntent().getStringExtra("NOME_PONTO_TURISTICO");

        PontoTuristico p = DB.pesquisarPontoTuristico(nomePontoTuristico);

        getSupportActionBar().setTitle(p.getNome() + ":");

        int imageResource = ImagePontoTuristico.getImageById(p.getId());

        ImageView image = (ImageView) findViewById(R.id.ImagemPontoTuristico);
        image.setImageResource(imageResource);


        TextView descricao = (TextView) findViewById(R.id.DescricaoPontoTuristico);

        descricao.setText(p.getDescricao());

        url = p.getSite();
        lat = p.getLat();
        lng = p.getLng();
    }

    public void maisInformacoes(View view){
        Uri uriUrl = Uri.parse(url);
        Intent abrirNavegador = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(abrirNavegador);
    }

    public void rotasAPe(View view){
        Intent intent = new Intent(this, TelaExibirRotaAPe.class);
        intent.putExtra("LATITUDE_PONTO", lat);
        intent.putExtra("LONGITUDE_PONTO", lng);
        startActivity(intent);
    }

    public void rotasOnibus(View view){
        Intent intent = new Intent(this, TelaExibirRota.class);

        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
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
        Location location = getLastKnownLocation();
        double latitudeOrigem = location.getLatitude();
        double longitudeOrigem = location.getLongitude();

        LatLng latLngOrigem = new LatLng(latitudeOrigem, longitudeOrigem);
        LatLng latLngDestino = new LatLng(lat, lng);

        Calendar rightNow = Calendar.getInstance();
        int currentHour = rightNow.get(Calendar.HOUR_OF_DAY);
        InputUsuario.setTipoPesquisa("PESQUISA_TURISTICA");
        InputUsuario.setHoraSaida(currentHour);
        InputUsuario.setLatLngOrigem(latLngOrigem);
        InputUsuario.setLatLngDestino(latLngDestino);
        startActivity(intent);
    }

    private Location getLastKnownLocation() {
        mLocationManager = (LocationManager)getApplicationContext().getSystemService(LOCATION_SERVICE);
        List<String> providers = mLocationManager.getProviders(true);
        Location bestLocation = null;
        for (String provider : providers) {
            @SuppressLint("MissingPermission") Location l = mLocationManager.getLastKnownLocation(provider);
            if (l == null) {
                continue;
            }
            if (bestLocation == null || l.getAccuracy() < bestLocation.getAccuracy()) {
                // Found best last known location: %s", l);
                bestLocation = l;
            }
        }
        return bestLocation;
    }

}
