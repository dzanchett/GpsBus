package com.cefet.gpsbus;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Spinner;

public class TelaPesquisarOnibus extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

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
        setContentView(R.layout.activity_tela_pesquisar_onibus);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Spinner spinner = (Spinner) findViewById(R.id.spinnerPesquisarOnibus);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.pesquisarOnibusSelect, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        textView = (AutoCompleteTextView) findViewById(R.id.editTextPesquisarOnibus);
    }

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
        EditText editTextPesquisarOnibus = (EditText) findViewById(R.id.editTextPesquisarOnibus);

        pesquisarPorValue = (String) parent.getItemAtPosition(pos);

        if(((CharSequence) parent.getItemAtPosition(pos)).equals("Terminal")){
            editTextPesquisarOnibus.setHint("nome do terminal");
            editTextPesquisarOnibus.setInputType(InputType.TYPE_CLASS_TEXT);
            autoCompleteStr = DB.autoCompleteTerminais();
            ArrayAdapter<String> adapterAutoComplete = new ArrayAdapter<String>(this,
                    android.R.layout.simple_dropdown_item_1line, autoCompleteStr);
            textView.setAdapter(adapterAutoComplete);
        }else if(((CharSequence) parent.getItemAtPosition(pos)).equals("Bairro")){
            editTextPesquisarOnibus.setHint("nome do bairro");
            editTextPesquisarOnibus.setInputType(InputType.TYPE_CLASS_TEXT);
            autoCompleteStr = DB.autoCompleteBairros();
            ArrayAdapter<String> adapterAutoComplete = new ArrayAdapter<String>(this,
                    android.R.layout.simple_dropdown_item_1line, autoCompleteStr);
            textView.setAdapter(adapterAutoComplete);
        }else{
            editTextPesquisarOnibus.setHint("nome da rua");
            editTextPesquisarOnibus.setInputType(InputType.TYPE_CLASS_TEXT);
            autoCompleteStr = DB.autoCompleteRuas();
            ArrayAdapter<String> adapterAutoComplete = new ArrayAdapter<String>(this,
                    android.R.layout.simple_dropdown_item_1line, autoCompleteStr);
            textView.setAdapter(adapterAutoComplete);
        }
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }

    public void pesquisar(View view){
        EditText editTextPesquisarOnibus = (EditText) findViewById(R.id.editTextPesquisarOnibus);
        String editTextValue = editTextPesquisarOnibus.getText().toString();

        if(editTextValue==null || editTextValue.equals("")){
            String hint = editTextPesquisarOnibus.getHint().toString();
            Intent intent = new Intent(this, NotificacaoCampoVazio.class);
            intent.putExtra("TEXT", "O campo \"" + hint + "\" não pode ficar vazio.");
            startActivity(intent);
        }else {
            Intent intent = new Intent(this, TelaExibirResultados.class);
            intent.putExtra("TIPO_RESULTADO", "PESQUISAR_ONIBUS");
            intent.putExtra("PESQUISAR_POR", pesquisarPorValue);
            intent.putExtra("VALUE", editTextValue);
            startActivity(intent);
        }
    }

}
