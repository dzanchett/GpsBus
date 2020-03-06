package com.cefet.gpsbus;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class TelaListaPontosTuristicos extends AppCompatActivity {

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
        setContentView(R.layout.activity_tela_lista_pontos_turisticos);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final ListView listView = (ListView) findViewById(R.id.listaPontosTuristicos);
        final List<String> ListElementsArrayList = new ArrayList<String>();
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ListElementsArrayList);
        listView.setAdapter(adapter);

        ListElementsArrayList.clear();

        List<PontoTuristicoSimples> pontosTuristicos = new ArrayList<PontoTuristicoSimples>();
        pontosTuristicos = DB.listarPontosTuristicos();

        for(PontoTuristicoSimples p : pontosTuristicos){
            ListElementsArrayList.add(p.getNome());
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Log.d("posicao Clique:", ListElementsArrayList.get(position));
                goDescricao(ListElementsArrayList.get(position));
            }
        });
    }

    private void goDescricao(String nome){
        Intent intent = new Intent(this, TelaDescricaoPontosTuristicos.class);
        intent.putExtra("NOME_PONTO_TURISTICO", nome);
        startActivity(intent);
    }

}
