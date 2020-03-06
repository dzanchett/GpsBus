package com.cefet.gpsbus;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class TelaExibirRota extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Context context = this;
    private RequestQueue mQueue;
    final List<String> rotasParaPontoJSON = new ArrayList<String>();
    private List<Object> listaTemp;
    private ItemRota itemTemp = null;
    private LatLng origemTemp = null;
    private LatLng destinoTemp = null;
    private String descricaoTemp = null;
    private Ponto pontoOrigemTemp = null;
    private Ponto pontoDestinoTemp = null;
    private List<Onibus> listaOnibus = new ArrayList<Onibus>();
    private List<Object> rota;
    private int idx;
    //List<String> ListElementsArrayList;
    //ArrayAdapter<String> adapter;

    @Override
    public void onBackPressed() {
        if(InputUsuario.getTipoPesquisa().equals("PESQUISA_TURISTICA")) {
            finish();
        }else{
            return;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_exibir_rota);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if(InputUsuario.getTipoPesquisa().equals("PESQUISA_TURISTICA")){
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }

        final LayoutInflater mInflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public void onResume() {
        super.onResume();

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                AlgoritimoBusca alg = new AlgoritimoBusca(InputUsuario.getPlaceOrigem(), InputUsuario.getPlaceDestino(), InputUsuario.getHoraSaida(), InputUsuario.getMinutoSaida(), InputUsuario.getDiaDaSemana());
                listaTemp = alg.busca();

                //Log.d("rrrr", listaTemp.toString());
                //Log.d("buildd_size", Integer.toString(listaTemp.size()));

                rota = new ArrayList<Object>();

                if(listaTemp != null){
                    idx = 0;
                    build(false);
                }else{
                    ItemRota item = new ItemRota();

                    item.setTitulo("Desculpe");
                    item.setDescricao("<br>Infelizmente não foram encontrados resultados para a sua busca.");
                    item.setIcon(ItemRota.ICON_WARNING);

                    rota.add(item);

                    recyclerView = (RecyclerView) findViewById(R.id.rotasLista);
                    recyclerView.post(new Runnable() {
                        @Override
                        public void run() {
                            recyclerView.setAdapter(new RecyclerAdapter(rota, context));
                            Log.d("buildd_end", rota.toString());

                            RecyclerView.LayoutManager layout = new LinearLayoutManager(context,
                                    LinearLayoutManager.VERTICAL, false);

                            recyclerView.setLayoutManager(layout);

                            recyclerView.setVisibility(View.VISIBLE);

                        }
                    }) ;

                    final ProgressBar loading = (ProgressBar) findViewById(R.id.progressBar2);
                    loading.post(new Runnable() {
                        @Override
                        public void run() {
                            loading.setVisibility(View.INVISIBLE);
                        }
                    });
                }
            }
        });

        t.start();

        /*ListElementsArrayList = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ListElementsArrayList){
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
        recyclerView.setAdapter(adapter);
        recyclerView.setVisibility(View.VISIBLE);*/
    }

    private void build(boolean toEnd){
        Log.d("buildd", Integer.toString(idx));
        Object o;

        if(idx<listaTemp.size()){
            o = listaTemp.get(idx);
        }else{
            recyclerView = (RecyclerView) findViewById(R.id.rotasLista);
            recyclerView.setAdapter(new RecyclerAdapter(rota, this));
            Log.d("buildd_end", rota.toString());

            RecyclerView.LayoutManager layout = new LinearLayoutManager(this,
                    LinearLayoutManager.VERTICAL, false);

            recyclerView.setLayoutManager(layout);

            ProgressBar loading = (ProgressBar) findViewById(R.id.progressBar2);
            loading.setVisibility(View.INVISIBLE);

            recyclerView.setVisibility(View.VISIBLE);

            return;
        }


        if(toEnd == false) {
            if (o instanceof String) {
                if (itemTemp != null) {
                    rota.add(itemTemp);
                }
                itemTemp = new ItemRota();
                String s = (String) o;
                itemTemp.setTitulo("<b>" + s + "</b>");
                idx++;
                build(false);
                return;
            } else if (o instanceof LatLng) {
                LatLng latLng = (LatLng) o;
                if (origemTemp == null) {
                    origemTemp = latLng;
                    idx++;
                    build(false);
                    return;
                } else {
                    destinoTemp = latLng;
                    try {
                        getRotaAPe(origemTemp, destinoTemp);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } else if(o instanceof Ponto){
                Ponto p = (Ponto) o;
                if(pontoOrigemTemp == null){
                    pontoOrigemTemp = p;
                    idx++;
                    build(false);
                    return;
                } else {
                    pontoDestinoTemp = p;
                    descricaoTemp = new String("<br>Espere seu ônibus no ponto localizado em <b>" + pontoOrigemTemp.getReferencia() + ", " + pontoOrigemTemp.getBairro() + "</b>.");
                    descricaoTemp = descricaoTemp.concat("<br><br>Pegue um dos ônibus da lista abaixo:");
                    descricaoTemp = descricaoTemp.concat("<ul>");
                    for(Onibus bus : listaOnibus){
                        descricaoTemp = descricaoTemp.concat("<li>" + bus.getNumero() + " - " + bus.getNome() + " " + (bus.getInformacoesAdicionais() != null && bus.getInformacoesAdicionais().equals("null") == false ? bus.getInformacoesAdicionais() : "") + "</li>");
                    }
                    descricaoTemp = descricaoTemp.concat("</ul>");
                    descricaoTemp = descricaoTemp.concat("<br><br>Desça do ônibus em <b>" + pontoDestinoTemp.getReferencia() + ", " + pontoDestinoTemp.getBairro() + "</b>.");
                    itemTemp.setDescricao(descricaoTemp);
                    itemTemp.setIcon(ItemRota.ICON_CAR);
                    rota.add(itemTemp);
                    itemTemp = null;
                    pontoOrigemTemp = null;
                    origemTemp = null;
                    destinoTemp = null;
                    listaOnibus.clear();
                    idx++;
                    build(false);
                }
            } else if(o instanceof Onibus){
                Onibus on = (Onibus) o;
                listaOnibus.add(on);
                idx++;
                build(false);
            }
        }else{
            List<String> l = null;
            l = rotasParaPontoJSON;
            descricaoTemp = new String("<br><ul>");
            for (String s : l) {
                if(s.contains("<div style=\"font-size:0.9em\">Estrada de uso restrito</div>")){
                    s = s.replace("<div style=\"font-size:0.9em\">Estrada de uso restrito</div>", "");
                }
                descricaoTemp = descricaoTemp.concat("<li>" + s + "</li>");
            }
            descricaoTemp = descricaoTemp.concat("</ul>");
            itemTemp.setDescricao(descricaoTemp);
            rota.add(itemTemp);
            itemTemp = null;
            origemTemp = null;
            destinoTemp = null;
            rotasParaPontoJSON.clear();
            idx++;
            build(false);
            return;
        }

        /*for(Object o : rota){
            if(o instanceof Ponto){
                Ponto p = (Ponto) o;
                if(p.getReferencia().equals("Terminal:")) {
                    //addRota(p.getReferencia() + " " + p.getBairro() + ".");
                }else {
                    //addRota(p.getReferencia() + ", " + p.getBairro() + ".");
                }
            }else if(o instanceof Onibus){
                Onibus on = (Onibus) o;
                //addRota("Ônibus: " + Integer.toString(on.getNumero()) + " - " +  on.getNome() + ".");
            }
        }*/
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

                            rotasParaPontoJSON.add(instrucoes);
                        }

                        build(true);
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
        /*StringRequest stringRequest = new StringRequest(Request.Method.GET, urlRota, new Response.Listener<String>() {
            @Override
            public void onResponse(String Response){
                rotasParaPontoJSON.add(Response);
            }
        },  new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                rotasParaPontoJSON.add("");
            }
        });

        queue.add(stringRequest);*/
    //}

    /*private void addRota(String rota){
        ListElementsArrayList.add(rota);
    }*/

    private void printJson(String json) throws JSONException {
        JSONObject jsonObject = new JSONObject(json);
        JSONArray jsonArray = jsonObject.getJSONArray("routes");
        JSONArray legsArray = new JSONArray(jsonArray.getJSONObject(0).getString("legs"));
        JSONArray stepsArray = new JSONArray(legsArray.getJSONObject(0).getString("steps"));

        for(int i = 0; i<stepsArray.length(); i++){
            try{
                JSONObject object = stepsArray.getJSONObject(i);
                String instrucoes = object.getString("html_instructions");
                //addRota(instrucoes);
            }catch (JSONException e){
                //falha.
            }
        }
    }

}
