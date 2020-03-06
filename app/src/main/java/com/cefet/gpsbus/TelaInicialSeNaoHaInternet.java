package com.cefet.gpsbus;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class TelaInicialSeNaoHaInternet extends AppCompatActivity implements NetworkStateReceiver.NetworkStateReceiverListener {

    private NetworkStateReceiver networkStateReceiver;
    private View ativarModoOnline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial_se_nao_ha_internet);

        //Inicializar o monitoramento da conexão de internet:
        networkStateReceiver = new NetworkStateReceiver();
        networkStateReceiver.addListener(this);
        this.registerReceiver(networkStateReceiver, new IntentFilter(android.net.ConnectivityManager.CONNECTIVITY_ACTION));

        ativarModoOnline = (View) findViewById(R.id.AtivarModoOnline);
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
    public void networkAvailable() {
        //internet disponpível;
        /* TODO: Your connection-oriented stuff here */
        ativarModoOnline.setVisibility(View.VISIBLE);
    }

    @Override
    public void networkUnavailable() {
        //internet indisponível;
        /* TODO: Your disconnection-oriented stuff here */
        ativarModoOnline.setVisibility(View.INVISIBLE);
    }

    public void sair(View view){
        finish();
    }

    public void goPesquisarOnibus(View view){
        Intent intent = new Intent(this, TelaPesquisarOnibus.class);
        startActivity(intent);
        finish();
    }

    public void goPesquisarHorario(View view){
        Intent intent = new Intent(this, TelaPesquisarHorario.class);
        startActivity(intent);
        finish();
    }

    public void goPesquisarItinerario(View view){
        Intent intent = new Intent(this, TelaPesquisarItinerario.class);
        startActivity(intent);
        finish();
    }

    public void goMapaPontosOnibus(View view){
        Intent intent = new Intent(this, TelaMapaPontosOnibus.class);
        startActivity(intent);
        finish();
    }

    public void goSobre(View view){
        Intent intent = new Intent(this, TelaSobre.class);
        startActivity(intent);
        finish();
    }

    public void goListaPontosTuristicos(View view){
        Intent intent = new Intent(this, TelaListaPontosTuristicos.class);
        startActivity(intent);
        finish();
    }
}
