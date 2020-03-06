package com.cefet.gpsbus;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TelaExibirRotaAPe extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Object> rota;
    final List<String> rotasJSON = new ArrayList<String>();
    private ItemRota itemTemp = null;
    private RequestQueue mQueue;
    private Context context = this;
    private LocationManager mLocationManager;

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_exibir_rota_ape);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();

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

        double latitudeDestino = getIntent().getDoubleExtra("LATITUDE_PONTO", 0);
        double longitudeDestino = getIntent().getDoubleExtra("LONGITUDE_PONTO", 0);

        LatLng latLngOrigem = new LatLng(latitudeOrigem, longitudeOrigem);
        LatLng latLngDestino = new LatLng(latitudeDestino, longitudeDestino);

        try {
            getRotaAPe(latLngOrigem, latLngDestino);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void build(){
        recyclerView = (RecyclerView) findViewById(R.id.rotasAPeLista);
        rota = new ArrayList<Object>();
        itemTemp = new ItemRota();

        List<String> l = null;
        l = rotasJSON;
        String stringTemp = new String("<ul>");
        for(String s : l){
            if(s.contains("<div style=\"font-size:0.9em\">Estrada de uso restrito</div>")){
                s.replace("<div style=\"font-size:0.9em\">Estrada de uso restrito</div>", "");
            }
            stringTemp = stringTemp.concat("<li>" + s + "</li>");
        }
        stringTemp = stringTemp.concat("</ul>");
        itemTemp.setTitulo("");
        itemTemp.setDescricao(stringTemp);
        itemTemp.setIcon(ItemRota.ICON_WALK);
        rota.add(itemTemp);


        recyclerView.setAdapter(new RecyclerAdapter2(rota, this));
        RecyclerView.LayoutManager layout = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layout);
        recyclerView.setVisibility(View.VISIBLE);
    }

    private void getRotaAPe(LatLng origem, LatLng destino) throws InterruptedException {
        final JSONObject[] jsonObject = new JSONObject[1];
        final Boolean[] bool = {false};

        jsonObject[0] = null;

        final String url = "https://maps.googleapis.com/maps/api/directions/json?origin=" + origem.latitude + "," + origem.longitude + "&destination=" + destino.latitude + "," + destino.longitude + "&mode=walking&language=pt-BR&key=AIzaSyC6a4JBWv-nHdGJovCp2x2TuaJuau4XFUs";
        //Log.d("url", urlRota);

        mQueue = Volley.newRequestQueue(context);

        //String url = "https://maps.googleapis.com/maps/api/directions/json?origin=-22.5097383,-43.1779983&destination=-22.511447754430808,-43.178141005993034&mode=walking&language=pt-BR&key=AIzaSyC6a4JBWv-nHdGJovCp2x2TuaJuau4XFUs";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String Response){
                Log.d("response", "ok");
                try {
                    jsonObject[0] = new JSONObject(Response);

                    JSONObject jsonObject1 = jsonObject[0];
                    JSONArray jsonArray = null;
                    try {
                        jsonArray = jsonObject1.getJSONArray("routes");
                        JSONArray legsArray = new JSONArray(jsonArray.getJSONObject(0).getString("legs"));
                        JSONArray stepsArray = new JSONArray(legsArray.getJSONObject(0).getString("steps"));

                        for (int i = 0; i < stepsArray.length(); i++) {
                            JSONObject object = stepsArray.getJSONObject(i);
                            String instrucoes = object.getString("html_instructions");

                            rotasJSON.add(instrucoes);
                        }

                        build();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },  new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("response", "false");
                jsonObject[0] = null;
            }
        });

        mQueue.add(stringRequest);
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
