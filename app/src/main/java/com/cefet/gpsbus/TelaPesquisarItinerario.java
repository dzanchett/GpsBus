package com.cefet.gpsbus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Spinner;

public class TelaPesquisarItinerario extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private String pesquisarPorValue;
    private AutoCompleteTextView textView;
    private String[] autoCompleteStr = new String[0];

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
        setContentView(R.layout.activity_tela_pesquisar_itinerario);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Spinner spinner = (Spinner) findViewById(R.id.spinnerPesquisarItinerario);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.pesquisarHorarioItinerarioSelect, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        textView = (AutoCompleteTextView) findViewById(R.id.editTextPesquisarItinerario);
    }

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
        EditText editTextPesquisarItinerario = (EditText) findViewById(R.id.editTextPesquisarItinerario);

        pesquisarPorValue = (String) parent.getItemAtPosition(pos);

        if(((CharSequence) parent.getItemAtPosition(pos)).equals("nome do ônibus")){
            editTextPesquisarItinerario.setHint("nome do ônibus");
            editTextPesquisarItinerario.setInputType(InputType.TYPE_CLASS_TEXT);
            autoCompleteStr = DB.autoCompleteNomeOnibus();
            ArrayAdapter<String> adapterAutoComplete = new ArrayAdapter<String>(this,
                    android.R.layout.simple_dropdown_item_1line, autoCompleteStr);
            textView.setAdapter(adapterAutoComplete);
        }else{
            editTextPesquisarItinerario.setHint("número do ônibus");
            editTextPesquisarItinerario.setInputType(InputType.TYPE_CLASS_NUMBER);
            autoCompleteStr = DB.autoCompleteNumeroOnibus();
            ArrayAdapter<String> adapterAutoComplete = new ArrayAdapter<String>(this,
                    android.R.layout.simple_dropdown_item_1line, autoCompleteStr);
            textView.setAdapter(adapterAutoComplete);
        }
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }

    public void pesquisar(View view){
        EditText editTextPesquisarItinerario = (EditText) findViewById(R.id.editTextPesquisarItinerario);
        String editTextValue = editTextPesquisarItinerario.getText().toString();

        if(editTextValue==null || editTextValue.equals("")){
            String hint = editTextPesquisarItinerario.getHint().toString();
            Intent intent = new Intent(this, NotificacaoCampoVazio.class);
            intent.putExtra("TEXT", "O campo \"" + hint + "\" não pode ficar vazio.");
            startActivity(intent);
        }else {
            Intent intent = new Intent(this, TelaExibirResultados.class);
            intent.putExtra("TIPO_RESULTADO", "PESQUISAR_ITINERARIO");
            intent.putExtra("PESQUISAR_POR", pesquisarPorValue);
            intent.putExtra("VALUE", editTextValue);
            startActivity(intent);
        }
    }
}
