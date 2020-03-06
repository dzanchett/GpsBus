package com.cefet.gpsbus;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.Calendar;

public class TelaInicialSeHaInternet extends AppCompatActivity implements OnMapReadyCallback, NetworkStateReceiver.NetworkStateReceiverListener {

    private GoogleMap mapaTelaInicial;
    private FusedLocationProviderClient mFusedLocationClient;
    private Location localizacaoAtual;
    private boolean localizacaoObtida;
    private boolean centralizarMapa;
    private LocationRequest mLocationRequest;
    private LocationCallback mLocationCallback;
    private Marker markerVoceEstaAqui;
    private PlaceAutocompleteFragment autocompleteFragment;
    private boolean menuFuncoesOpened;
    private NetworkStateReceiver networkStateReceiver;
    private LatLng latLngAtual = null;

    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial_se_ha_internet);

        centralizarMapa = true;

        mLocationCallback = new LocationCallback() {
            @Override
            public void onLocationAvailability (LocationAvailability locationAvailability){
                if(locationAvailability.isLocationAvailable()==true){
                    Log.d("localização", "obtida");
                }else{
                    Log.d("localização", "não obtida");
                }
            }
            @Override
            public void onLocationResult(LocationResult locationResult) {
                Log.d("localização", "ok");
                if(localizacaoObtida==false && locationResult != null){
                    localizacaoObtida = true;
                }
                for (Location location : locationResult.getLocations()) {
                    localizacaoAtual = location;
                    onMapReady(mapaTelaInicial);
                }
            }
        };

        //obeter coordenadas do usuário:
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        mFusedLocationClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location == null) {
                    //falha;
                    localizacaoObtida = false;
                    Log.d("Localização Obtida", "Falha!");
                } else {
                    localizacaoAtual = location;
                    localizacaoObtida = true;
                    onMapReady(mapaTelaInicial);
                    //Log.d("variavel ini", String.valueOf(localizacaoObtida));
                    Log.d("Localização Obtida", "Sucesso!");
                }
            }
        }).addOnFailureListener(this, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("Localização Obtida", "erro");
            }
        });

        startLocationUpdates();

        //Inicializar o mapa da tela inicial com internet:
        //Log.d("Chamou", "onMapReady");
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapaTelaInicial);
        mapFragment.getMapAsync(this);

        //Inicializar o atuocomplete da caixa de pesquisa principal.
        autocompleteFragment = (PlaceAutocompleteFragment) getFragmentManager().findFragmentById(R.id.buscaSimplesDestino);

        autocompleteFragment.setHint("Onde você deseja ir?");

        AutocompleteFilter autocompleteFilter = new AutocompleteFilter.Builder().setTypeFilter(Place.TYPE_COUNTRY).setCountry("BR").build();

        autocompleteFragment.setFilter(autocompleteFilter);

        InputUsuario.setNULL();

        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // TODO: obter informações sobre o local selecionado.
                Log.i("Places Autocomplete", "Place: " + place.getName());
                InputUsuario.setPlaceDestino(place);
            }

            @Override
            public void onError(Status status) {
                // TODO: Solucionar o erro.
                Log.i("Places Autocomplete", "Ocorreu um erro: " + status);
                InputUsuario.setPlaceDestino(null);
            }
        });

        //Inicializar o Menu de funções:
        menuFuncoesOpened = false;
    }

    @Override
    protected void onResume() {
        super.onResume();

        //Inicializar o monitoramento da conexão de internet:
        networkStateReceiver = new NetworkStateReceiver();
        networkStateReceiver.addListener(this);
        this.registerReceiver(networkStateReceiver, new IntentFilter(android.net.ConnectivityManager.CONNECTIVITY_ACTION));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        networkStateReceiver.removeListener(this);
        this.unregisterReceiver(networkStateReceiver);
    }

    @Override
    public void onBackPressed() {
        ActivityCompat.finishAffinity(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        if (localizacaoObtida) {
            //Log.d("LatLng atual", localizacaoAtual.toString());
            googleMap.setMinZoomPreference(15);
            latLngAtual = new LatLng(localizacaoAtual.getLatitude(), localizacaoAtual.getLongitude());
            if(centralizarMapa) {
                centralizarMapa = false;
                markerVoceEstaAqui = googleMap.addMarker(new MarkerOptions().position(latLngAtual)
                        .title("Você está aqui!"));
                markerVoceEstaAqui.showInfoWindow();
                googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLngAtual));
                InputUsuario.setLatLngOrigem(latLngAtual);

                googleMap.getUiSettings().setMapToolbarEnabled(false);
            }else{
                markerVoceEstaAqui.setPosition(latLngAtual);
            }
        } else {
            mapaTelaInicial = googleMap;
        }
    }

    protected void createLocationRequest() {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(10000);
        mLocationRequest.setFastestInterval(5000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }

    @SuppressLint("MissingPermission")
    private void startLocationUpdates() {
        createLocationRequest();
        mFusedLocationClient.requestLocationUpdates(mLocationRequest, mLocationCallback, null);
    }

    public void menuFuncoes(View view){
        View botoes[] = new View[6];
        botoes[0] = findViewById(R.id.botaoSobre);
        botoes[1] = findViewById(R.id.botaoTurismo);
        botoes[2] = findViewById(R.id.botaoMapaPontos);
        botoes[3] = findViewById(R.id.botaoItinerarios);
        botoes[4] = findViewById(R.id.botãoHorarios);
        botoes[5] = findViewById(R.id.botaoPesquisar);

        if(menuFuncoesOpened){
            fadeOut(botoes);
            menuFuncoesOpened = false;
        }else{
            fadeIn(botoes);
            menuFuncoesOpened = true;
        }
    }

    private void fadeIn(View[] views){
        for(View view : views){
            view.setVisibility(View.VISIBLE);
            view.setAlpha(0);
        }
        for(final View view : views){
            view.animate().setDuration(500).alpha(1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    view.setVisibility(View.VISIBLE);
                }
            });
        }
    }

    private void fadeOut(View[] views){
        for(View view : views){
            view.setVisibility(View.VISIBLE);
            view.setAlpha(1);
        }
        for(final View view : views){
            view.animate().setDuration(500).alpha(0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    view.setVisibility(View.INVISIBLE);
                }
            });
        }
    }

    public void abreMenuBuscaAvancada(View view){
        Intent intent = new Intent(this, menuBuscaAvancada.class);
        startActivity(intent);
    }

    @Override
    public void networkAvailable() {
        //internet disponpível;
        /* TODO: Your connection-oriented stuff here */
        Log.d("Conexao", "true");
    }

    @Override
    public void networkUnavailable() {
        //internet indisponível;
        /* TODO: Your disconnection-oriented stuff here */
        Log.d("Conexao", "false");
        Intent intent = new Intent(this, NotificacaoPerdaConexaoDeInternet.class);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode==RESULT_OK){
            if(requestCode==1){
                finish();
            }
        }
    }

    public void goPesquisarOnibus(View view){
        Intent intent = new Intent(this, TelaPesquisarOnibus.class);
        startActivity(intent);
        menuFuncoes(view);
        finish();
    }

    public void goPesquisarHorario(View view){
        Intent intent = new Intent(this, TelaPesquisarHorario.class);
        startActivity(intent);
        menuFuncoes(view);
        finish();
    }

    public void goPesquisarItinerario(View view){
        Intent intent = new Intent(this, TelaPesquisarItinerario.class);
        startActivity(intent);
        menuFuncoes(view);
        finish();
    }

    public void goMapaPontosOnibus(View view){
        Intent intent = new Intent(this, TelaMapaPontosOnibus.class);
        startActivity(intent);
        menuFuncoes(view);
        finish();
    }

    public void goSobre(View view){
        Intent intent = new Intent(this, TelaSobre.class);
        startActivity(intent);
        menuFuncoes(view);
        finish();
    }

    public void goListaPontosTuristicos(View view){
        Intent intent = new Intent(this, TelaListaPontosTuristicos.class);
        startActivity(intent);
        menuFuncoes(view);
        finish();
    }

    public void goPesquisar(View view){
        if(InputUsuario.getPlaceDestino()!=null){
            Intent intent = new Intent(this, TelaExibirRota.class);
            Calendar rightNow = Calendar.getInstance();
            int currentHour = rightNow.get(Calendar.HOUR_OF_DAY);
            InputUsuario.setTipoPesquisa("PESQUISA_SIMPLES");
            InputUsuario.setHoraSaida(currentHour);
            startActivity(intent);
            finish();
        }else{
            Intent intent = new Intent(this, NotificacaoCampoVazio.class);
            intent.putExtra("TEXT", "O campo \"Onde você deseja ir\" não pode ficar vazio.");
            startActivity(intent);
        }
    }
}