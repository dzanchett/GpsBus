package com.cefet.gpsbus;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class TelaExibirResultados extends AppCompatActivity {

    private Class activityVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_exibir_resultados);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String tipoResultado = getIntent().getStringExtra("TIPO_RESULTADO");

        String pesquisarPor = getIntent().getStringExtra("PESQUISAR_POR");
        String value = getIntent().getStringExtra("VALUE");

        TextView textViewTelaResultados = (TextView) findViewById(R.id.textViewTelaResultados);

        final LayoutInflater mInflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        ListView listView = (ListView) findViewById(R.id.listViewTelaResultados);
        final List<String> ListElementsArrayList = new ArrayList<String>();
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ListElementsArrayList){
            @Override
            public View getView(int position, View convertView, ViewGroup parent)
            {
                View row;

                if (null == convertView) {
                    row = mInflater.inflate(android.R.layout.simple_list_item_1, null);
                } else {
                    row = convertView;
                }

                TextView tv = (TextView) row.findViewById(android.R.id.text1);
                tv.setText(Html.fromHtml(getItem(position)));
                //tv.setText(getItem(position));

                return row;
            }
        };
        listView.setAdapter(adapter);

        if(tipoResultado.equals("PESQUISAR_ONIBUS")){
            activityVoltar = TelaPesquisarOnibus.class;

            List<Onibus> results = DB.pesquisaOnibus(pesquisarPor, value);

            if(!results.isEmpty()) {
                char temp = 'o';

                if(pesquisarPor.equals("Terminal")){
                    value = results.get(0).getReferencia();
                }else if(pesquisarPor.equals("Rua")){
                    temp = 'a';
                }else{
                    value = "bairro " + value;
                }

                textViewTelaResultados.setText("A seguir encontram-se os ônibus que passam n"+ temp + " " + value + " :");

                ListElementsArrayList.clear();

                boolean label = false;

                for (Onibus o : results) {
                    if(o.getDiurnoNoturno().equals("D")) {
                        if(!label) {
                            ListElementsArrayList.add("<b>Onibus Diurnos:</b>");
                            label = true;
                        }
                        if(o.getInformacoesAdicionais().equals("null")) {
                            if(!ListElementsArrayList.contains(o.getNumero() + " - " + o.getNome())) {
                                ListElementsArrayList.add(o.getNumero() + " - " + o.getNome());
                            }
                        }else{
                            if(!ListElementsArrayList.contains(o.getNumero() + " - " + o.getNome() + " " + o.getInformacoesAdicionais())) {
                                ListElementsArrayList.add(o.getNumero() + " - " + o.getNome() + " " + o.getInformacoesAdicionais());
                            }
                        }
                    }
                }

                label = false;

                for (Onibus o : results) {
                    if(o.getDiurnoNoturno().equals("N")) {
                        if(!label) {
                            ListElementsArrayList.add("<b>Onibus Noturnos:</b>");
                            label = true;
                        }
                        if(o.getInformacoesAdicionais().equals("null")) {
                            if(!ListElementsArrayList.contains(o.getNumero() + " - " + o.getNome())) {
                                ListElementsArrayList.add(o.getNumero() + " - " + o.getNome());
                            }
                        }else{
                            if(!ListElementsArrayList.contains(o.getNumero() + " - " + o.getNome() + " " + o.getInformacoesAdicionais())) {
                                ListElementsArrayList.add(o.getNumero() + " - " + o.getNome() + " " + o.getInformacoesAdicionais());
                            }
                        }
                    }
                }
            }else{
                textViewTelaResultados.setText("Não foram encontrados resultados para a sua busca.");
            }
        }else if(tipoResultado.equals("PESQUISAR_HORARIO")){
            activityVoltar = TelaPesquisarHorario.class;

            List<Horario> results = DB.pesquisarHorario(pesquisarPor, value);

            if(!results.isEmpty()){
                textViewTelaResultados.setText("A seguir encontram-se os horários do ônibus " + results.get(0).getNumeroOnibus() + " - " + results.get(0).getNomeOnibus() + ":");

                ListElementsArrayList.clear();

                ListElementsArrayList.add("<b>Durante a Semana | Sábado | Domingos e Feriados</b>");
                ListElementsArrayList.add("<b>Ida - Volta</b>");

                for(Horario h : results){
                    String str1 = h.getHorario();

                    ListElementsArrayList.add(str1);
                }
            }else{
                textViewTelaResultados.setText("Não foram encontrados resultados para a sua busca.");
            }
        }else if(tipoResultado.equals("PESQUISAR_ITINERARIO")) {
            activityVoltar = TelaPesquisarItinerario.class;

            List<Itinerario> results = DB.pesquisarItinerario(pesquisarPor, value);

            if (!results.isEmpty()) {
                textViewTelaResultados.setText("A seguir encontram-se os itinerários do ônibus " + results.get(0).getNumeroOnibus() + " - " + results.get(0).getNomeOnibus() + ":");

                ListElementsArrayList.clear();

                String tipoAtual = "N";

                List<String> voltaLista = new ArrayList<String>();

                for (Itinerario i : results) {
                    if (!tipoAtual.equals(i.getTipo())) {
                        tipoAtual = i.getTipo();

                        if (tipoAtual.equals("I")) {
                            ListElementsArrayList.add("<b>Ida:</b>");
                        } else if (tipoAtual.equals("V")) {
                            ListElementsArrayList.add("<b>Volta:</b>");
                        }

                    }

                    if(voltaLista.contains(i.getReferencia()) == false && i.getTipo() != "I") {
                        ListElementsArrayList.add(i.getReferencia());
                        voltaLista.add(i.getReferencia());
                    }else if(!ListElementsArrayList.contains(i.getReferencia()) && i.getTipo() == "I"){
                        ListElementsArrayList.add(i.getReferencia());
                    }
                }
            } else {
                textViewTelaResultados.setText("Não foram encontrados resultados para a sua busca.");
            }
        }
    }

    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), activityVoltar);
        startActivityForResult(myIntent, 0);
        return true;

    }

}
