package com.cefet.gpsbus;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class menuBuscaAvancada extends Activity {

    private PlaceAutocompleteFragment origem;
    private PlaceAutocompleteFragment destino;
    private TimePicker timePicker;
    private DatePicker datePicker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_busca_avancada);

        //reajustar automaticamente o tamanho da janela.
        this.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        setTitle("Busca Avançada:");

        Long tempoCorrente = Calendar.getInstance().getTimeInMillis();
        tempoCorrente -= 1000;

        datePicker = (DatePicker) findViewById(R.id.menuAvancadoData);
        datePicker.setMinDate(tempoCorrente);

        timePicker = (TimePicker) findViewById(R.id.menuAvancadoHora);
        timePicker.setIs24HourView(true);

        AutocompleteFilter autocompleteFilter = new AutocompleteFilter.Builder().setTypeFilter(Place.TYPE_COUNTRY).setCountry("BR").build();

        //Inicializar o atuocomplete origem.
        origem = (PlaceAutocompleteFragment) getFragmentManager().findFragmentById(R.id.menuBuscaAvancadaOrigem);

        origem.setHint("Origem");

        origem.setFilter(autocompleteFilter);

        origem.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // TODO: obter informações sobre o local selecionado.
                Log.i("Places Autocomplete", "Place: " + place.getName());
                InputUsuario.setPlaceOrigem(place);
            }

            @Override
            public void onError(Status status) {
                // TODO: Solucionar o erro.
                Log.i("Places Autocomplete", "Ocorreu um erro: " + status);
                InputUsuario.setPlaceOrigem(null);
            }
        });

        //Inicializar o atuocomplete destino.
        destino = (PlaceAutocompleteFragment) getFragmentManager().findFragmentById(R.id.menuBuscaAvancadaDestino);

        destino.setHint("Destino");

        destino.setFilter(autocompleteFilter);

        destino.setOnPlaceSelectedListener(new PlaceSelectionListener() {
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

    }

    public void cancelar(View view){
        finish();
    }

    public void goPesquisar(View view){
        if(InputUsuario.getPlaceDestino()!=null&&InputUsuario.getPlaceOrigem()!=null){
            Intent intent = new Intent(this, TelaExibirRota.class);
            InputUsuario.setTipoPesquisa("PESQUISA_AVANCADA");
            InputUsuario.setHoraSaida(timePicker.getCurrentHour());
            InputUsuario.setMinutoSaida(timePicker.getCurrentMinute());
            InputUsuario.setDiaDoMes(datePicker.getDayOfMonth());
            GregorianCalendar gregorianCalendar = new GregorianCalendar(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth()-1);
            int dayOfWeek= gregorianCalendar.get(gregorianCalendar.DAY_OF_WEEK);
            InputUsuario.setDiaDaSemana(dayOfWeek);
            startActivity(intent);
            finish();
        }else{
            Intent intent = new Intent(this, NotificacaoCampoVazio.class);
            if(InputUsuario.getPlaceOrigem()==null){
                intent.putExtra("TEXT", "O campo \"Origem\" não pode ficar vazio.");
            }else if(InputUsuario.getPlaceDestino()==null){
                intent.putExtra("TEXT", "O campo \"Destino\" não pode ficar vazio.");
            }
            startActivity(intent);
        }
    }
}
