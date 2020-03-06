package com.cefet.gpsbus;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.security.AccessController.getContext;

/**
 * Created by diego on 22/01/2018.
 */

public class DB {

    private static SQLiteDatabase dataBase;
    private static int status = 0;

    public static String getStatus(){
        String ss = Integer.toString(status);
        ss += "%";
        return ss;
    }

    private static void updateStatus(){
        status += 8;
        if(status>100){
            status = 100;
        }
    }

    public static void setDataBase(SQLiteDatabase db){
        dataBase = db;
    }

    private static JSONArray loadBairroTab(Context context) throws JSONException {
        String json = null;

        try{
            InputStream is = context.getAssets().open("bairroTab.json");

            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            json = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        Log.d("Load_json", "bairroTab");
        return new JSONArray(json);
    }

    private static JSONArray loadItinerarioTab(Context context) throws JSONException {
        String json = null;

        try{
            InputStream is = context.getAssets().open("itinerarioTab.json");

            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            json = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        Log.d("Load_json", "itinerarioTab");
        return new JSONArray(json);
    }

    private static JSONArray loadOnibusTab(Context context) throws JSONException {
        String json = null;

        try{
            InputStream is = context.getAssets().open("onibusTab.json");

            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            json = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        Log.d("Load_json", "onibusTab");
        return new JSONArray(json);
    }

    private static JSONArray loadPontoTab(Context context) throws JSONException {
        String json = null;

        try{
            InputStream is = context.getAssets().open("pontoTab.json");

            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            json = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        Log.d("Load_json", "pontoTab");
        return new JSONArray(json);
    }

    private static JSONArray loadPontoTuristicoTab(Context context) throws JSONException {
        String json = null;

        try{
            InputStream is = context.getAssets().open("pontoTuristicoTab.json");

            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            json = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        Log.d("Load_json", "pontoTuristicoTab");
        return new JSONArray(json);
    }

    private static JSONArray loadTerminalTab(Context context) throws JSONException {
        String json = null;

        try{
            InputStream is = context.getAssets().open("terminalTab.json");

            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            json = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        Log.d("Load_json", "terminalTab");
        return new JSONArray(json);
    }

    private static JSONArray loadHorarioDomingoIdaTab(Context context) throws JSONException {
        String json = null;

        try{
            InputStream is = context.getAssets().open("horarioDomingoIda.json");

            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            json = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        Log.d("Load_json", "horarioDomingoIdaTab");
        return new JSONArray(json);
    }

    private static JSONArray loadHorarioDomingoVoltaTab(Context context) throws JSONException {
        String json = null;

        try{
            InputStream is = context.getAssets().open("horarioDomingoVolta.json");

            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            json = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        Log.d("Load_json", "horarioDomingoVoltaTab");
        return new JSONArray(json);
    }

    private static JSONArray loadHorarioSabadoIdaTab(Context context) throws JSONException {
        String json = null;

        try{
            InputStream is = context.getAssets().open("horarioSabadoIda.json");

            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            json = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        Log.d("Load_json", "horarioSabadoIdaTab");
        return new JSONArray(json);
    }

    private static JSONArray loadHorarioSabadoVoltaTab(Context context) throws JSONException {
        String json = null;

        try{
            InputStream is = context.getAssets().open("horarioSabadoVolta.json");

            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            json = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        Log.d("Load_json", "horarioSabadoVoltaTab");
        return new JSONArray(json);
    }

    private static JSONArray loadHorarioSemanaIdaTab(Context context) throws JSONException {
        String json = null;

        try{
            InputStream is = context.getAssets().open("horarioSemanaIda.json");

            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            json = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        Log.d("Load_json", "horarioSemanaIdaTab");
        return new JSONArray(json);
    }

    private static JSONArray loadHorarioSemanaVoltaTab(Context context) throws JSONException {
        String json = null;

        try{
            InputStream is = context.getAssets().open("horarioSemanaVolta.json");

            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            json = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        Log.d("Load_json", "horarioSemanaVoltaTab");
        return new JSONArray(json);
    }

    public static void createDb(final SQLiteDatabase db, final Context context) throws JSONException {
        final JSONArray[] bairroJson = new JSONArray[1];
        final JSONArray[] itinerarioJson = new JSONArray[1];
        final JSONArray[] onibusJson = new JSONArray[1];
        final JSONArray[] pontoJson = new JSONArray[1];
        final JSONArray[] pontoTuristicoJson = new JSONArray[1];
        final JSONArray[] terminalJson = new JSONArray[1];
        final JSONArray[] horarioDomingoIdaJson = new JSONArray[1];
        final JSONArray[] horarioDomingoVoltaJson = new JSONArray[1];
        final JSONArray[] horarioSabadoIdaJson = new JSONArray[1];
        final JSONArray[] horarioSabadoVoltaJson = new JSONArray[1];
        final JSONArray[] horarioSemanaIdaJson = new JSONArray[1];
        final JSONArray[] horarioSemanaVoltaJson = new JSONArray[1];

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    bairroJson[0] = DB.loadBairroTab(context);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    itinerarioJson[0] = DB.loadItinerarioTab(context);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    onibusJson[0] = DB.loadOnibusTab(context);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t4 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    pontoJson[0] = DB.loadPontoTab(context);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t5 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    pontoTuristicoJson[0] = DB.loadPontoTuristicoTab(context);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t6 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    terminalJson[0] = DB.loadTerminalTab(context);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t20 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    horarioDomingoIdaJson[0] = DB.loadHorarioDomingoIdaTab(context);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t21 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    horarioDomingoVoltaJson[0] = DB.loadHorarioDomingoVoltaTab(context);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t22 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    horarioSabadoIdaJson[0] = DB.loadHorarioSabadoIdaTab(context);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t23 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    horarioSabadoVoltaJson[0] = DB.loadHorarioSabadoVoltaTab(context);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t24 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    horarioSemanaIdaJson[0] = DB.loadHorarioSemanaIdaTab(context);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t25 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    horarioSemanaVoltaJson[0] = DB.loadHorarioSemanaVoltaTab(context);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
        t20.start();
        t21.start();
        t22.start();
        t23.start();
        t24.start();
        t25.start();

        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
            t5.join();
            t6.join();
            t20.join();
            t21.join();
            t22.join();
            t23.join();
            t24.join();
            t25.join();
            updateStatus();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return;
        }

        //Log.d("etapa", "0");

        Thread t7 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ContentValues values = new ContentValues();
                    for(int i = 0; i < bairroJson[0].length(); i++) {
                        JSONObject jsonObject = null;
                        jsonObject = bairroJson[0].getJSONObject(i);
                        int id = jsonObject.getInt("id");
                        String nome = jsonObject.getString("nome");

                        values.put("id", id);
                        values.put("nome", nome);
                        db.insert("bairro", null, values);
                        values.clear();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                updateStatus();
            }
        });

        //Log.d("etapa", "1");

        Thread t8 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ContentValues values = new ContentValues();
                    for(int i = 0; i < itinerarioJson[0].length(); i++){
                        JSONObject jsonObject = itinerarioJson[0].getJSONObject(i);
                        int idItinerario = jsonObject.getInt("idItinerario");
                        int idPonto = jsonObject.getInt("idPonto");
                        int sort = jsonObject.getInt("sort");

                        values.put("idItinerario", idItinerario);
                        values.put("idPonto", idPonto);
                        values.put("sort", sort);
                        db.insert("itinerario", null, values);
                        values.clear();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                updateStatus();
            }
        });

        //Log.d("etapa", "2");

        Thread t9 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ContentValues values = new ContentValues();
                    for(int i = 0; i < onibusJson[0].length(); i++){
                        JSONObject jsonObject = onibusJson[0].getJSONObject(i);
                        int numero = jsonObject.getInt("numero");
                        String nome = jsonObject.getString("nome");
                        String sentido = jsonObject.getString("sentido");
                        String diurnoNoturno = jsonObject.getString("diurnoNoturno");
                        String informacoesAdicionais = jsonObject.getString("informacoesAdicionais");
                        int idItinerario = jsonObject.getInt("idItinerario");

                        values.put("numero", numero);
                        values.put("nome", nome);
                        values.put("sentido", sentido);
                        values.put("diurnoNoturno", diurnoNoturno);
                        values.put("informacoesAdicionais", informacoesAdicionais);
                        values.put("idItinerario", idItinerario);
                        db.insert("onibus", null, values);
                        values.clear();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                updateStatus();
            }
        });

        //Log.d("etapa", "3");

        Thread t10 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ContentValues values = new ContentValues();
                    for(int i = 0; i < pontoJson[0].length(); i++){
                        JSONObject jsonObject = pontoJson[0].getJSONObject(i);
                        int id = jsonObject.getInt("id");
                        String lat = jsonObject.getString("lat");
                        String lng = jsonObject.getString("lng");
                        String referencia = jsonObject.getString("referencia");
                        int idBairro = jsonObject.getInt("idBairro");

                        values.put("id", id);
                        values.put("lat", lat);
                        values.put("lng", lng);
                        values.put("referencia", referencia);
                        values.put("idBairro", idBairro);
                        db.insert("ponto", null, values);
                        values.clear();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                updateStatus();
            }
        });

        //Log.d("etapa", "4");

        Thread t11 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ContentValues values = new ContentValues();
                    for(int i = 0; i < pontoTuristicoJson[0].length(); i++){
                        JSONObject jsonObject = pontoTuristicoJson[0].getJSONObject(i);
                        int id = jsonObject.getInt("id");
                        String nome = jsonObject.getString("nome");
                        String descricao = jsonObject.getString("descricao");
                        String lat = jsonObject.getString("lat");
                        String lng = jsonObject.getString("lng");
                        String site = jsonObject.getString("site");

                        values.put("id", id);
                        values.put("nome", nome);
                        values.put("descricao", descricao);
                        values.put("lat", lat);
                        values.put("lng", lng);
                        values.put("site", site);
                        db.insert("pontoTuristico", null, values);
                        values.clear();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                updateStatus();
            }
        });

        //Log.d("etapa", "5");

        Thread t12 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ContentValues values = new ContentValues();
                    for(int i = 0; i < terminalJson[0].length(); i++){
                        JSONObject jsonObject = terminalJson[0].getJSONObject(i);
                        int idPonto = jsonObject.getInt("idPonto");
                        String possuiConexao = jsonObject.getString("possuiConexao");

                        values.put("idPonto", idPonto);
                        values.put("possuiConexao", possuiConexao);
                        db.insert("terminal", null, values);
                        values.clear();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                updateStatus();
            }
        });

        Thread t30 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ContentValues values = new ContentValues();
                    for(int i = 0; i < horarioDomingoIdaJson[0].length(); i++){
                        JSONObject jsonObject = horarioDomingoIdaJson[0].getJSONObject(i);
                        int numeroOnibus = jsonObject.getInt("numeroOnibus");
                        String horario = jsonObject.getString("horario");

                        values.put("numeroOnibus", numeroOnibus);
                        values.put("horario", horario);
                        db.insert("horarioDomingoIda", null, values);
                        values.clear();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                updateStatus();
            }
        });

        Thread t31 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ContentValues values = new ContentValues();
                    for(int i = 0; i < horarioDomingoVoltaJson[0].length(); i++){
                        JSONObject jsonObject = horarioDomingoVoltaJson[0].getJSONObject(i);
                        int numeroOnibus = jsonObject.getInt("numeroOnibus");
                        String horario = jsonObject.getString("horario");

                        values.put("numeroOnibus", numeroOnibus);
                        values.put("horario", horario);
                        db.insert("horarioDomingoVolta", null, values);
                        values.clear();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                updateStatus();
            }
        });

        Thread t32 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ContentValues values = new ContentValues();
                    for(int i = 0; i < horarioSabadoIdaJson[0].length(); i++){
                        JSONObject jsonObject = horarioSabadoIdaJson[0].getJSONObject(i);
                        int numeroOnibus = jsonObject.getInt("numeroOnibus");
                        String horario = jsonObject.getString("horario");

                        values.put("numeroOnibus", numeroOnibus);
                        values.put("horario", horario);
                        db.insert("horarioSabadoIda", null, values);
                        values.clear();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                updateStatus();
            }
        });

        Thread t33 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ContentValues values = new ContentValues();
                    for(int i = 0; i < horarioSabadoVoltaJson[0].length(); i++){
                        JSONObject jsonObject = horarioSabadoVoltaJson[0].getJSONObject(i);
                        int numeroOnibus = jsonObject.getInt("numeroOnibus");
                        String horario = jsonObject.getString("horario");

                        values.put("numeroOnibus", numeroOnibus);
                        values.put("horario", horario);
                        db.insert("horarioSabadoVolta", null, values);
                        values.clear();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                updateStatus();
            }
        });

        Thread t34 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ContentValues values = new ContentValues();
                    for(int i = 0; i < horarioSemanaIdaJson[0].length(); i++){
                        JSONObject jsonObject = horarioSemanaIdaJson[0].getJSONObject(i);
                        int numeroOnibus = jsonObject.getInt("numeroOnibus");
                        String horario = jsonObject.getString("horario");

                        values.put("numeroOnibus", numeroOnibus);
                        values.put("horario", horario);
                        db.insert("horarioSemanaIda", null, values);
                        values.clear();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                updateStatus();
            }
        });

        Thread t35 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ContentValues values = new ContentValues();
                    for(int i = 0; i < horarioSemanaVoltaJson[0].length(); i++){
                        JSONObject jsonObject = horarioSemanaVoltaJson[0].getJSONObject(i);
                        int numeroOnibus = jsonObject.getInt("numeroOnibus");
                        String horario = jsonObject.getString("horario");

                        values.put("numeroOnibus", numeroOnibus);
                        values.put("horario", horario);
                        db.insert("horarioSemanaVolta", null, values);
                        values.clear();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                updateStatus();
            }
        });

        t7.start();
        t8.start();
        t9.start();
        t10.start();
        t11.start();
        t12.start();
        t30.start();
        t31.start();
        t32.start();
        t33.start();
        t34.start();
        t35.start();

        try {
            t7.join();
            t8.join();
            t9.join();
            t10.join();
            t11.join();
            t12.join();
            t30.join();
            t31.join();
            t32.join();
            t33.join();
            t34.join();
            t35.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Log.d("etapa", "6");
    }

    /*public static void createDb(SQLiteDatabase db){

        dataBase = db;
        Log.d("dataBase ini", dataBase.toString());

        ContentValues values = new ContentValues();

        values.put(DbContract.FeedEntryPontos.COLUMN_ID, 0);
        values.put(DbContract.FeedEntryPontos.COLUMN_LATITUDE, -22.5100211);
        values.put(DbContract.FeedEntryPontos.COLUMN_LONGITUDE, -43.1759586);
        values.put(DbContract.FeedEntryPontos.COLUMN_SENTIDO, "F");
        values.put(DbContract.FeedEntryPontos.COLUMN_REFERENCIA, "Rua do Imperador, 654 – 686");
        values.put(DbContract.FeedEntryPontos.COLUMN_ID_BAIRRO, 0);
        db.insert(DbContract.FeedEntryPontos.TABLE_NAME, null, values);
        values.clear();

        values.put(DbContract.FeedEntryPontos.COLUMN_ID, 1);
        values.put(DbContract.FeedEntryPontos.COLUMN_LATITUDE, -22.5033942);
        values.put(DbContract.FeedEntryPontos.COLUMN_LONGITUDE, -43.1699861);
        values.put(DbContract.FeedEntryPontos.COLUMN_SENTIDO, "PT");
        values.put(DbContract.FeedEntryPontos.COLUMN_REFERENCIA, "Av. Dom Pedro I, 885 – 431");
        values.put(DbContract.FeedEntryPontos.COLUMN_ID_BAIRRO, 0);
        db.insert(DbContract.FeedEntryPontos.TABLE_NAME, null, values);
        values.clear();

        values.put(DbContract.FeedEntryPontos.COLUMN_ID, 2);
        values.put(DbContract.FeedEntryPontos.COLUMN_LATITUDE, -22.5021558);
        values.put(DbContract.FeedEntryPontos.COLUMN_LONGITUDE, -43.1704875);
        values.put(DbContract.FeedEntryPontos.COLUMN_SENTIDO, "PT");
        values.put(DbContract.FeedEntryPontos.COLUMN_REFERENCIA, "R. Fonseca Ramos, 165 – 35");
        values.put(DbContract.FeedEntryPontos.COLUMN_ID_BAIRRO, 0);
        db.insert(DbContract.FeedEntryPontos.TABLE_NAME, null, values);
        values.clear();

        values.put(DbContract.FeedEntryPontos.COLUMN_ID, 3);
        values.put(DbContract.FeedEntryPontos.COLUMN_LATITUDE, -22.5004473);
        values.put(DbContract.FeedEntryPontos.COLUMN_LONGITUDE, -43.1683872);
        values.put(DbContract.FeedEntryPontos.COLUMN_SENTIDO, "PT");
        values.put(DbContract.FeedEntryPontos.COLUMN_REFERENCIA, "R. Fonseca Ramos, 449 – 351");
        values.put(DbContract.FeedEntryPontos.COLUMN_ID_BAIRRO, 0);
        db.insert(DbContract.FeedEntryPontos.TABLE_NAME, null, values);
        values.clear();

        values.put(DbContract.FeedEntryPontos.COLUMN_ID, 4);
        values.put(DbContract.FeedEntryPontos.COLUMN_LATITUDE, -22.4994911);
        values.put(DbContract.FeedEntryPontos.COLUMN_LONGITUDE, -43.1661544);
        values.put(DbContract.FeedEntryPontos.COLUMN_SENTIDO, "PT");
        values.put(DbContract.FeedEntryPontos.COLUMN_REFERENCIA, "Estr. da Saudade, 2 – 72");
        values.put(DbContract.FeedEntryPontos.COLUMN_ID_BAIRRO, 0);
        db.insert(DbContract.FeedEntryPontos.TABLE_NAME, null, values);
        values.clear();

        values.put(DbContract.FeedEntryPontos.COLUMN_ID, 5);
        values.put(DbContract.FeedEntryPontos.COLUMN_LATITUDE, -22.4998561);
        values.put(DbContract.FeedEntryPontos.COLUMN_LONGITUDE, -43.1647322);
        values.put(DbContract.FeedEntryPontos.COLUMN_SENTIDO, "PT");
        values.put(DbContract.FeedEntryPontos.COLUMN_REFERENCIA, "R. Joaquim Murtinho, 183 – 1");
        values.put(DbContract.FeedEntryPontos.COLUMN_ID_BAIRRO, 0);
        db.insert(DbContract.FeedEntryPontos.TABLE_NAME, null, values);
        values.clear();

        values.put(DbContract.FeedEntryPontos.COLUMN_ID, 6);
        values.put(DbContract.FeedEntryPontos.COLUMN_LATITUDE, -22.4980428);
        values.put(DbContract.FeedEntryPontos.COLUMN_LONGITUDE, -43.1626341);
        values.put(DbContract.FeedEntryPontos.COLUMN_SENTIDO, "PT");
        values.put(DbContract.FeedEntryPontos.COLUMN_REFERENCIA, "R. Quissamã, 809 – 601");
        values.put(DbContract.FeedEntryPontos.COLUMN_ID_BAIRRO, 0);
        db.insert(DbContract.FeedEntryPontos.TABLE_NAME, null, values);
        values.clear();

        values.put(DbContract.FeedEntryPontos.COLUMN_ID, 7);
        values.put(DbContract.FeedEntryPontos.COLUMN_LATITUDE, -22.4918246);
        values.put(DbContract.FeedEntryPontos.COLUMN_LONGITUDE, -43.1570802);
        values.put(DbContract.FeedEntryPontos.COLUMN_SENTIDO, "PT");
        values.put(DbContract.FeedEntryPontos.COLUMN_REFERENCIA, "R. Quissamã, 1899 – 1321");
        values.put(DbContract.FeedEntryPontos.COLUMN_ID_BAIRRO, 0);
        db.insert(DbContract.FeedEntryPontos.TABLE_NAME, null, values);
        values.clear();

        values.put(DbContract.FeedEntryPontos.COLUMN_ID, 8);
        values.put(DbContract.FeedEntryPontos.COLUMN_LATITUDE, -22.4912303);
        values.put(DbContract.FeedEntryPontos.COLUMN_LONGITUDE, -43.1562853);
        values.put(DbContract.FeedEntryPontos.COLUMN_SENTIDO, "PT");
        values.put(DbContract.FeedEntryPontos.COLUMN_REFERENCIA, "R. Quissamã, 1899 – 1321");
        values.put(DbContract.FeedEntryPontos.COLUMN_ID_BAIRRO, 0);
        db.insert(DbContract.FeedEntryPontos.TABLE_NAME, null, values);
        values.clear();

        values.put(DbContract.FeedEntryPontos.COLUMN_ID, 9);
        values.put(DbContract.FeedEntryPontos.COLUMN_LATITUDE, -22.4903004);
        values.put(DbContract.FeedEntryPontos.COLUMN_LONGITUDE, -43.155462);
        values.put(DbContract.FeedEntryPontos.COLUMN_SENTIDO, "PT");
        values.put(DbContract.FeedEntryPontos.COLUMN_REFERENCIA, "R. Quissamã, 1899 – 132");
        values.put(DbContract.FeedEntryPontos.COLUMN_ID_BAIRRO, 0);
        db.insert(DbContract.FeedEntryPontos.TABLE_NAME, null, values);
        values.clear();

        values.put(DbContract.FeedEntryPontos.COLUMN_ID, 10);
        values.put(DbContract.FeedEntryPontos.COLUMN_LATITUDE, -22.4896998);
        values.put(DbContract.FeedEntryPontos.COLUMN_LONGITUDE, -43.154686);
        values.put(DbContract.FeedEntryPontos.COLUMN_SENTIDO, "PT");
        values.put(DbContract.FeedEntryPontos.COLUMN_REFERENCIA, "R. Quissamã, 1999 – 1951");
        values.put(DbContract.FeedEntryPontos.COLUMN_ID_BAIRRO, 0);
        db.insert(DbContract.FeedEntryPontos.TABLE_NAME, null, values);
        values.clear();

        values.put(DbContract.FeedEntryPontos.COLUMN_ID, 11);
        values.put(DbContract.FeedEntryPontos.COLUMN_LATITUDE, -22.4889954);
        values.put(DbContract.FeedEntryPontos.COLUMN_LONGITUDE, -43.1532664);
        values.put(DbContract.FeedEntryPontos.COLUMN_SENTIDO, "PT");
        values.put(DbContract.FeedEntryPontos.COLUMN_REFERENCIA, "R. Quissamã, 2209 – 2099");
        values.put(DbContract.FeedEntryPontos.COLUMN_ID_BAIRRO, 0);
        db.insert(DbContract.FeedEntryPontos.TABLE_NAME, null, values);
        values.clear();

        values.put(DbContract.FeedEntryPontos.COLUMN_ID, 12);
        values.put(DbContract.FeedEntryPontos.COLUMN_LATITUDE, -22.4866222);
        values.put(DbContract.FeedEntryPontos.COLUMN_LONGITUDE, -43.1510238);
        values.put(DbContract.FeedEntryPontos.COLUMN_SENTIDO, "T");
        values.put(DbContract.FeedEntryPontos.COLUMN_REFERENCIA, "R. João de Farias, 250");
        values.put(DbContract.FeedEntryPontos.COLUMN_ID_BAIRRO, 0);
        db.insert(DbContract.FeedEntryPontos.TABLE_NAME, null, values);
        values.clear();

        values.put(DbContract.FeedEntryPontos.COLUMN_ID, 13);
        values.put(DbContract.FeedEntryPontos.COLUMN_LATITUDE, -22.4397758);
        values.put(DbContract.FeedEntryPontos.COLUMN_LONGITUDE, -43.1398329);
        values.put(DbContract.FeedEntryPontos.COLUMN_SENTIDO, "TP");
        values.put(DbContract.FeedEntryPontos.COLUMN_REFERENCIA, "Estr. União e Indústria, 4664");
        values.put(DbContract.FeedEntryPontos.COLUMN_ID_BAIRRO, 0);
        db.insert(DbContract.FeedEntryPontos.TABLE_NAME, null, values);
        values.clear();

        values.put(DbContract.FeedEntryPontos.COLUMN_ID, 14);
        values.put(DbContract.FeedEntryPontos.COLUMN_LATITUDE, -22.4397758);
        values.put(DbContract.FeedEntryPontos.COLUMN_LONGITUDE, -43.1398329);
        values.put(DbContract.FeedEntryPontos.COLUMN_SENTIDO, "T");
        values.put(DbContract.FeedEntryPontos.COLUMN_REFERENCIA, "Estr. União e Indústria, 4664");
        values.put(DbContract.FeedEntryPontos.COLUMN_ID_BAIRRO, 0);
        db.insert(DbContract.FeedEntryPontos.TABLE_NAME, null, values);
        values.clear();


        values.put(DbContract.FeedEntryBairros.COLUMN_ID, 0);
        values.put(DbContract.FeedEntryBairros.COLUMN_NOME, "Itamaraty");
        db.insert(DbContract.FeedEntryBairros.TABLE_NAME, null, values);
        values.clear();

        values.put(DbContract.FeedEntryItinerario.COLUMN_NUMERO_ONIBUS, 340);
        values.put(DbContract.FeedEntryItinerario.COLUMN_ID_PONTO, 0);
        db.insert(DbContract.FeedEntryItinerario.TABLE_NAME, null, values);
        values.clear();

        values.put(DbContract.FeedEntryItinerario.COLUMN_NUMERO_ONIBUS, 340);
        values.put(DbContract.FeedEntryItinerario.COLUMN_ID_PONTO, 1);
        db.insert(DbContract.FeedEntryItinerario.TABLE_NAME, null, values);
        values.clear();

        values.put(DbContract.FeedEntryItinerario.COLUMN_NUMERO_ONIBUS, 340);
        values.put(DbContract.FeedEntryItinerario.COLUMN_ID_PONTO, 2);
        db.insert(DbContract.FeedEntryItinerario.TABLE_NAME, null, values);
        values.clear();

        values.put(DbContract.FeedEntryItinerario.COLUMN_NUMERO_ONIBUS, 340);
        values.put(DbContract.FeedEntryItinerario.COLUMN_ID_PONTO, 3);
        db.insert(DbContract.FeedEntryItinerario.TABLE_NAME, null, values);
        values.clear();

        values.put(DbContract.FeedEntryItinerario.COLUMN_NUMERO_ONIBUS, 340);
        values.put(DbContract.FeedEntryItinerario.COLUMN_ID_PONTO, 4);
        db.insert(DbContract.FeedEntryItinerario.TABLE_NAME, null, values);
        values.clear();

        values.put(DbContract.FeedEntryItinerario.COLUMN_NUMERO_ONIBUS, 340);
        values.put(DbContract.FeedEntryItinerario.COLUMN_ID_PONTO, 5);
        db.insert(DbContract.FeedEntryItinerario.TABLE_NAME, null, values);
        values.clear();

        values.put(DbContract.FeedEntryItinerario.COLUMN_NUMERO_ONIBUS, 340);
        values.put(DbContract.FeedEntryItinerario.COLUMN_ID_PONTO, 6);
        db.insert(DbContract.FeedEntryItinerario.TABLE_NAME, null, values);
        values.clear();

        values.put(DbContract.FeedEntryItinerario.COLUMN_NUMERO_ONIBUS, 340);
        values.put(DbContract.FeedEntryItinerario.COLUMN_ID_PONTO, 7);
        db.insert(DbContract.FeedEntryItinerario.TABLE_NAME, null, values);
        values.clear();

        values.put(DbContract.FeedEntryItinerario.COLUMN_NUMERO_ONIBUS, 340);
        values.put(DbContract.FeedEntryItinerario.COLUMN_ID_PONTO, 8);
        db.insert(DbContract.FeedEntryItinerario.TABLE_NAME, null, values);
        values.clear();

        values.put(DbContract.FeedEntryItinerario.COLUMN_NUMERO_ONIBUS, 340);
        values.put(DbContract.FeedEntryItinerario.COLUMN_ID_PONTO, 9);
        db.insert(DbContract.FeedEntryItinerario.TABLE_NAME, null, values);
        values.clear();

        values.put(DbContract.FeedEntryItinerario.COLUMN_NUMERO_ONIBUS, 340);
        values.put(DbContract.FeedEntryItinerario.COLUMN_ID_PONTO, 10);
        db.insert(DbContract.FeedEntryItinerario.TABLE_NAME, null, values);
        values.clear();

        values.put(DbContract.FeedEntryItinerario.COLUMN_NUMERO_ONIBUS, 340);
        values.put(DbContract.FeedEntryItinerario.COLUMN_ID_PONTO, 11);
        db.insert(DbContract.FeedEntryItinerario.TABLE_NAME, null, values);
        values.clear();

        values.put(DbContract.FeedEntryItinerario.COLUMN_NUMERO_ONIBUS, 340);
        values.put(DbContract.FeedEntryItinerario.COLUMN_ID_PONTO, 12);
        db.insert(DbContract.FeedEntryItinerario.TABLE_NAME, null, values);
        values.clear();

        values.put(DbContract.FeedEntryHorario.COLUMN_NUMERO_ONIBUS, 340);
        values.put(DbContract.FeedEntryHorario.COLUMN_HORARIO_TERMINAL, 22.00);
        values.put(DbContract.FeedEntryHorario.COLUMN_HORARIO_BAIRRO, 23.00);
        values.put(DbContract.FeedEntryHorario.COLUMN_DIA_DA_SEMANA, "W");
        db.insert(DbContract.FeedEntryHorario.TABLE_NAME, null, values);
        values.clear();

        values.put(DbContract.FeedEntryOnibus.COLUMN_NUMERO, 340);
        values.put(DbContract.FeedEntryOnibus.COLUMN_NOME, "Terminal Itamaraty");
        db.insert(DbContract.FeedEntryOnibus.TABLE_NAME, null, values);
        values.clear();

        values.put(DbContract.FeedEntryPontosTuristicos.COLUMN_NAME_ID, 1);
        values.put(DbContract.FeedEntryPontosTuristicos.COLUMN_NAME_NOME, "Museu Imperial");
        values.put(DbContract.FeedEntryPontosTuristicos.COLUMN_NAME_DESCRICAO, "O Museu Imperial, popularmente conhecido como Palácio Imperial, é um museu histórico-temático localizado no centro histórico da cidade de Petrópolis, no estado do Rio de Janeiro, no Brasil.");
        values.put(DbContract.FeedEntryPontosTuristicos.COLUMN_NAME_ID_IMAGEM, "1");
        db.insert(DbContract.FeedEntryPontosTuristicos.TABLE_NAME, null, values);
        values.clear();

        values.put(DbContract.FeedEntryPontosTuristicos.COLUMN_NAME_ID, 2);
        values.put(DbContract.FeedEntryPontosTuristicos.COLUMN_NAME_NOME, "Palácio de Cristal");
        values.put(DbContract.FeedEntryPontosTuristicos.COLUMN_NAME_DESCRICAO, "descrição 2");
        values.put(DbContract.FeedEntryPontosTuristicos.COLUMN_NAME_ID_IMAGEM, "1");
        db.insert(DbContract.FeedEntryPontosTuristicos.TABLE_NAME, null, values);
        values.clear();

        values.put(DbContract.FeedEntryPontosTuristicos.COLUMN_NAME_ID, 3);
        values.put(DbContract.FeedEntryPontosTuristicos.COLUMN_NAME_NOME, "Palácio Rio Negro");
        values.put(DbContract.FeedEntryPontosTuristicos.COLUMN_NAME_DESCRICAO, "descrição 2");
        values.put(DbContract.FeedEntryPontosTuristicos.COLUMN_NAME_ID_IMAGEM, "1");
        db.insert(DbContract.FeedEntryPontosTuristicos.TABLE_NAME, null, values);
        values.clear();
        values.put(DbContract.FeedEntryPontosTuristicos.COLUMN_NAME_ID, 4);
        values.put(DbContract.FeedEntryPontosTuristicos.COLUMN_NAME_NOME, "Catedral São Pedro De Alcântara");
        values.put(DbContract.FeedEntryPontosTuristicos.COLUMN_NAME_DESCRICAO, "descrição 2");
        values.put(DbContract.FeedEntryPontosTuristicos.COLUMN_NAME_ID_IMAGEM, "1");
        db.insert(DbContract.FeedEntryPontosTuristicos.TABLE_NAME, null, values);
        values.clear();
        values.put(DbContract.FeedEntryPontosTuristicos.COLUMN_NAME_ID, 5);
        values.put(DbContract.FeedEntryPontosTuristicos.COLUMN_NAME_NOME, "Casa de Santos Dumont");
        values.put(DbContract.FeedEntryPontosTuristicos.COLUMN_NAME_DESCRICAO, "descrição 2");
        values.put(DbContract.FeedEntryPontosTuristicos.COLUMN_NAME_ID_IMAGEM, "1");
        db.insert(DbContract.FeedEntryPontosTuristicos.TABLE_NAME, null, values);
        values.clear();
        values.put(DbContract.FeedEntryPontosTuristicos.COLUMN_NAME_ID, 6);
        values.put(DbContract.FeedEntryPontosTuristicos.COLUMN_NAME_NOME, "Praça da Liberdade");
        values.put(DbContract.FeedEntryPontosTuristicos.COLUMN_NAME_DESCRICAO, "descrição 2");
        values.put(DbContract.FeedEntryPontosTuristicos.COLUMN_NAME_ID_IMAGEM, "1");
        db.insert(DbContract.FeedEntryPontosTuristicos.TABLE_NAME, null, values);
        values.clear();
        values.put(DbContract.FeedEntryPontosTuristicos.COLUMN_NAME_ID, 7);
        values.put(DbContract.FeedEntryPontosTuristicos.COLUMN_NAME_NOME, "Parque Natural");
        values.put(DbContract.FeedEntryPontosTuristicos.COLUMN_NAME_DESCRICAO, "descrição 2");
        values.put(DbContract.FeedEntryPontosTuristicos.COLUMN_NAME_ID_IMAGEM, "1");
        db.insert(DbContract.FeedEntryPontosTuristicos.TABLE_NAME, null, values);
        values.clear();
        values.put(DbContract.FeedEntryPontosTuristicos.COLUMN_NAME_ID, 8);
        values.put(DbContract.FeedEntryPontosTuristicos.COLUMN_NAME_NOME, "Museu da FEB");
        values.put(DbContract.FeedEntryPontosTuristicos.COLUMN_NAME_DESCRICAO, "descrição 2");
        values.put(DbContract.FeedEntryPontosTuristicos.COLUMN_NAME_ID_IMAGEM, "1");
        db.insert(DbContract.FeedEntryPontosTuristicos.TABLE_NAME, null, values);
        values.clear();
        values.put(DbContract.FeedEntryPontosTuristicos.COLUMN_NAME_ID, 9);
        values.put(DbContract.FeedEntryPontosTuristicos.COLUMN_NAME_NOME, "Praça 14 BIS");
        values.put(DbContract.FeedEntryPontosTuristicos.COLUMN_NAME_DESCRICAO, "descrição 2");
        values.put(DbContract.FeedEntryPontosTuristicos.COLUMN_NAME_ID_IMAGEM, "1");
        db.insert(DbContract.FeedEntryPontosTuristicos.TABLE_NAME, null, values);
        values.clear();
        values.put(DbContract.FeedEntryPontosTuristicos.COLUMN_NAME_ID, 10);
        values.put(DbContract.FeedEntryPontosTuristicos.COLUMN_NAME_NOME, "Obelisco");
        values.put(DbContract.FeedEntryPontosTuristicos.COLUMN_NAME_DESCRICAO, "descrição 2");
        values.put(DbContract.FeedEntryPontosTuristicos.COLUMN_NAME_ID_IMAGEM, "1");
        db.insert(DbContract.FeedEntryPontosTuristicos.TABLE_NAME, null, values);
        values.clear();
        values.put(DbContract.FeedEntryPontosTuristicos.COLUMN_NAME_ID, 11);
        values.put(DbContract.FeedEntryPontosTuristicos.COLUMN_NAME_NOME, "Palácio Quitandinha");
        values.put(DbContract.FeedEntryPontosTuristicos.COLUMN_NAME_DESCRICAO, "descrição 2");
        values.put(DbContract.FeedEntryPontosTuristicos.COLUMN_NAME_ID_IMAGEM, "1");
        db.insert(DbContract.FeedEntryPontosTuristicos.TABLE_NAME, null, values);
        values.clear();
    }*/

    private static String Capitalizar(String str){
        StringBuffer strBuffer = new StringBuffer();
        Matcher strMatcher = Pattern.compile("([a-z])([a-z]*)", Pattern.CASE_INSENSITIVE).matcher(str);
        while(strMatcher.find()){
            strMatcher.appendReplacement(strBuffer, strMatcher.group(1).toUpperCase() + strMatcher.group(2).toLowerCase());
        }

        return strMatcher.appendTail(strBuffer).toString();
    }

    public static String[] autoCompleteTerminais(){
        String[] ret;
        List<String> str = new ArrayList<String>();
        Cursor query = null;

        query = dataBase.rawQuery("SELECT DISTINCT A.referencia FROM ponto A, terminal B WHERE A.id = B.idPonto", null);

        if(query!=null){
            if(query.moveToFirst()){
                do{
                    String s = query.getString(query.getColumnIndex("referencia"));
                    s = Capitalizar(s.replaceAll("[0-9]|,|-|N/D", ""));
                    s = s.trim();
                    if(!str.contains(s)){
                        str.add(s);
                    }
                }while(query.moveToNext());
            }
            query.close();
        }

        ret = new String[str.size()];

        int i = 0;

        for(String s : str){
            ret[i] = s;
            i++;
        }

        return ret;
    }

    public static String[] autoCompleteBairros(){
        String[] ret;
        List<String> str = new ArrayList<String>();
        Cursor query = null;

        query = dataBase.rawQuery("SELECT DISTINCT nome FROM bairro", null);

        if(query!=null){
            if(query.moveToFirst()){
                do{
                    String s = query.getString(query.getColumnIndex("nome"));
                    s = Capitalizar(s);
                    s = s.trim();
                    str.add(s);
                }while(query.moveToNext());
            }
            query.close();
        }

        ret = new String[str.size()];

        int i = 0;

        for(String s : str){
            ret[i] = s;
            i++;
        }

        return ret;
    }

    public static String[] autoCompleteRuas(){
        String[] ret;
        List<String> str = new ArrayList<String>();
        Cursor query = null;

        query = dataBase.rawQuery("SELECT DISTINCT referencia FROM ponto", null);

        if(query!=null){
            if(query.moveToFirst()){
                do{
                    String s = query.getString(query.getColumnIndex("referencia"));
                    s = Capitalizar(s.replaceAll("[0-9]|,|-|N/D", ""));
                    s = s.trim();
                    if(!str.contains(s)){
                        str.add(s);
                    }
                }while(query.moveToNext());
            }
            query.close();
        }

        ret = new String[str.size()];

        int i = 0;

        for(String s : str){
            ret[i] = s;
            i++;
        }

        return ret;
    }

    public static String[] autoCompleteNomeOnibus(){
        String[] ret;
        List<String> str = new ArrayList<String>();
        Cursor query = null;

        query = dataBase.rawQuery("SELECT DISTINCT nome FROM onibus", null);

        if(query!=null){
            if(query.moveToFirst()){
                do{
                    String s = query.getString(query.getColumnIndex("nome"));
                    str.add(s);
                }while(query.moveToNext());
            }
            query.close();
        }

        ret = new String[str.size()];

        int i = 0;

        for(String s : str){
            ret[i] = s;
            i++;
        }

        return ret;
    }

    public static String[] autoCompleteNumeroOnibus(){
        String[] ret;
        List<String> str = new ArrayList<String>();
        Cursor query = null;

        query = dataBase.rawQuery("SELECT DISTINCT numero FROM onibus", null);

        if(query!=null){
            if(query.moveToFirst()){
                do{
                    String s = query.getString(query.getColumnIndex("numero"));
                    str.add(s);
                }while(query.moveToNext());
            }
            query.close();
        }

        ret = new String[str.size()];

        int i = 0;

        for(String s : str){
            ret[i] = s;
            i++;
        }

        return ret;
    }

    public static List<Onibus> pesquisaOnibus(String pesquisarPor, String value){
        List<Onibus> ret = new ArrayList<Onibus>();
        Cursor query = null;
        boolean temReferencia = false;

        //value = Normalizer.normalize(value, Normalizer.Form.NFD);
        //value = value.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");

        if(pesquisarPor.equals("Terminal")) {
            query = dataBase.rawQuery("SELECT DISTINCT A.referencia, C.numero, C.nome, C.diurnoNoturno, C.informacoesAdicionais FROM ponto A, itinerario B, onibus C, terminal D WHERE D.idPonto = A.id AND lower(A.referencia) COLLATE Latin1_general_CI_AI LIKE '%' || ? || '%' COLLATE Latin1_general_CI_AI AND A.id = B.idPonto AND B.idItinerario = C.idItinerario;", new String[]{value});
            temReferencia = true;
        }else if(pesquisarPor.equals("Bairro")){
            query = dataBase.rawQuery("SELECT DISTINCT C.numero, C.nome, C.diurnoNoturno, C.informacoesAdicionais FROM ponto A, itinerario B, onibus C, bairro D WHERE D.id = A.idBairro AND lower(D.nome) COLLATE Latin1_general_CI_AI LIKE '%' || ? || '%' COLLATE Latin1_general_CI_AI AND A.id = B.idPonto AND B.idItinerario = C.idItinerario;", new String[]{value});
        }else if(pesquisarPor.equals("Rua")){
            query = dataBase.rawQuery("SELECT DISTINCT C.numero, C.nome, C.diurnoNoturno, C.informacoesAdicionais FROM ponto A, itinerario B, onibus C WHERE lower(A.referencia) LIKE '%' || ? || '%' AND A.id = B.idPonto AND B.idItinerario = C.idItinerario;", new String[]{value});
        }

        if(query!=null){
            if(query.moveToFirst()){
                do{
                    Onibus o = new Onibus();
                    o.setNumero(query.getInt(query.getColumnIndex("numero")));
                    o.setNome(query.getString(query.getColumnIndex("nome")));
                    if(temReferencia) {
                        String tempS = query.getString(query.getColumnIndex("referencia"));
                        tempS = Capitalizar(tempS.replaceAll("[0-9]|,|-|N/D", ""));
                        tempS = tempS.trim();
                        o.setReferencia(tempS);
                    }
                    o.setDiurnoNoturno(query.getString(query.getColumnIndex("diurnoNoturno")));
                    o.setInformacoesAdicionais(query.getString(query.getColumnIndex("informacoesAdicionais")));
                    ret.add(o);
                }while(query.moveToNext());
            }
            query.close();
        }

        return ret;
    }

    public static List<Horario> pesquisarHorario(String pesquisarPor, String value){
        List<Horario> ret = new ArrayList<Horario>();
        List<String> horarioSemanaIda = new ArrayList<String>();
        List<String> horarioSemanaVolta = new ArrayList<String>();
        List<String> horarioSabadoIda = new ArrayList<String>();
        List<String> horarioSabadoVolta = new ArrayList<String>();
        List<String> horarioDomingoIda = new ArrayList<String>();
        List<String> horarioDomingoVolta = new ArrayList<String>();
        Cursor query = null;
        String nomeOnibus = null;
        String numeroOnibus = null;

        if(pesquisarPor.equals("nome do ônibus")){
            nomeOnibus = value;
            query = dataBase.rawQuery("SELECT numero FROM onibus WHERE nome = ?", new String[]{nomeOnibus});

            if(query != null){
                if(query.moveToFirst()){
                    numeroOnibus = query.getString(query.getColumnIndex("numero"));
                }
            }
        }else{
            numeroOnibus = value;
            query = dataBase.rawQuery("SELECT nome FROM onibus WHERE numero = ?", new String[]{numeroOnibus});

            if(query != null){
                if(query.moveToFirst()){
                    nomeOnibus = query.getString(query.getColumnIndex("nome"));
                }
            }
        }

        if(numeroOnibus == null || nomeOnibus == null){
            return ret;
        }

        query =  dataBase.rawQuery("SELECT horario FROM horarioSemanaIda WHERE numeroOnibus = ?", new String[]{numeroOnibus});

        if(query!=null){
            if(query.moveToFirst()){
                do{
                    String horarioTemp = query.getString(query.getColumnIndex("horario"));
                    horarioSemanaIda.add(horarioTemp.substring(0, 5));
                }while(query.moveToNext());
            }
            query.close();
        }

        query =  dataBase.rawQuery("SELECT horario FROM horarioSemanaVolta WHERE numeroOnibus = ?", new String[]{numeroOnibus});

        if(query!=null){
            if(query.moveToFirst()){
                do{
                    String horarioTemp = query.getString(query.getColumnIndex("horario"));
                    horarioSemanaVolta.add(horarioTemp.substring(0, 5));
                }while(query.moveToNext());
            }
            query.close();
        }

        query =  dataBase.rawQuery("SELECT horario FROM horarioSabadoIda WHERE numeroOnibus = ?", new String[]{numeroOnibus});

        if(query!=null){
            if(query.moveToFirst()){
                do{
                    String horarioTemp = query.getString(query.getColumnIndex("horario"));
                    horarioSabadoIda.add(horarioTemp.substring(0, 5));
                }while(query.moveToNext());
            }
            query.close();
        }

        query =  dataBase.rawQuery("SELECT horario FROM horarioSabadoVolta WHERE numeroOnibus = ?", new String[]{numeroOnibus});

        if(query!=null){
            if(query.moveToFirst()){
                do{
                    String horarioTemp = query.getString(query.getColumnIndex("horario"));
                    horarioSabadoVolta.add(horarioTemp.substring(0, 5));
                }while(query.moveToNext());
            }
            query.close();
        }

        query =  dataBase.rawQuery("SELECT horario FROM horarioDomingoIda WHERE numeroOnibus = ?", new String[]{numeroOnibus});

        if(query!=null){
            if(query.moveToFirst()){
                do{
                    String horarioTemp = query.getString(query.getColumnIndex("horario"));
                    horarioDomingoIda.add(horarioTemp.substring(0, 5));
                }while(query.moveToNext());
            }
            query.close();
        }

        query =  dataBase.rawQuery("SELECT horario FROM horarioDomingoVolta WHERE numeroOnibus = ?", new String[]{numeroOnibus});

        if(query!=null){
            if(query.moveToFirst()){
                do{
                    String horarioTemp = query.getString(query.getColumnIndex("horario"));
                    horarioDomingoVolta.add(horarioTemp.substring(0, 5));
                }while(query.moveToNext());
            }
            query.close();
        }

        horarioSemanaIda = merge(horarioSemanaIda, horarioSemanaVolta, '-', 1);
        horarioSabadoIda = merge(horarioSabadoIda, horarioSabadoVolta, '-', 1);
        horarioDomingoIda = merge(horarioDomingoIda, horarioDomingoVolta, '-', 1);

        horarioSemanaIda = naturalMerge(horarioSemanaIda, horarioSabadoIda, '|', 1);
        horarioSemanaIda = naturalMerge(horarioSemanaIda, horarioDomingoIda, '|', 2);

        for(String str : horarioSemanaIda){
            Horario h = new Horario();
            h.setNomeOnibus(nomeOnibus);
            h.setNumeroOnibus(numeroOnibus);
            h.setHorario(str);
            ret.add(h);
        }

        return ret;
    }

    private static List<String> merge(List<String> list1, List<String> list2, char simbolo, int peso){
        List<String> listRet = new ArrayList<String>();
        int size1, size2, i, j, temp1, temp2, temp3;

        size1 = list1.size();
        size2 = list2.size();

        i = 0;
        j = 0;

        while(i<size1 && j<size2){
            temp1 = 0;
            temp2 = 0;
            temp3 = 0;

            char[] tempChar1 = list1.get(i).toCharArray();
            char[] tempChar2 = list2.get(j).toCharArray();
            char[] tempChar3 = null;

            if(i+1 < size1){
                tempChar3 = list1.get(i+1).toCharArray();
                temp3 += ((tempChar3[0]-'0') * 10) + (tempChar3[1]-'0');
            }

            if(tempChar1[0] != '*' && tempChar2[0] != '*') {
                temp1 += ((tempChar1[0]-'0') * 10) + (tempChar1[1]-'0');
                temp2 += ((tempChar2[0]-'0') * 10) + (tempChar2[1]-'0');
            }

            if((temp1<temp2 && tempChar3!=null && temp3>=temp2)||(temp1<temp2 && tempChar3==null)||tempChar2[0]=='*'||tempChar1[0]=='*') {
                listRet.add(list1.get(i) + " " + simbolo + " " + list2.get(j));
                i++;
                j++;
            }else if(tempChar3!=null && temp1<temp2 && temp3<temp2){
                if(simbolo == '-') {
                    listRet.add(list1.get(i) + " " + simbolo + " " + "*****");
                }else if(simbolo == '|'){
                    if(peso == 1) {
                        listRet.add(list1.get(i) + " " + simbolo + " " + "*****  -  *****");
                    }else if(peso == 2){
                        listRet.add("*****  -  *****" + " " + simbolo + " " + list1.get(i) + " " + simbolo + " " + "*****  -  *****");
                    }
                }
                i++;
            }else if(temp1>temp2){
                if(simbolo == '-') {
                    listRet.add("*****" + " " + simbolo + " " + list2.get(j));
                }else if(simbolo == '|'){
                    if(peso == 1) {
                        listRet.add("*****  -  *****" + " " + simbolo + " " + list2.get(j));
                    }else if(peso == 2){
                        listRet.add("*****  -  *****" + " " + simbolo + " " + "*****  -  *****" + " " + simbolo + " " + list2.get(j));
                    }
                }
                j++;
            }else{
                temp1 += (tempChar1[3] * 10) + tempChar1[4];
                temp2 += (tempChar2[3] * 10) + tempChar2[4];

                if(temp1<=temp2) {
                    listRet.add(list1.get(i) + " " + simbolo + " " + list2.get(j));
                    i++;
                    j++;
                }else{
                    if(simbolo == '-') {
                        listRet.add("*****" + " " + simbolo + " " + list2.get(j));
                    }else if(simbolo == '|'){
                        if(peso == 1) {
                            listRet.add("*****  -  *****" + " " + simbolo + " " + list2.get(j));
                        }else if(peso == 2){
                            listRet.add("*****  -  *****" + " " + simbolo + " " + "*****  -  *****" + " " + simbolo + " " + list2.get(j));
                        }
                    }
                    j++;
                }
            }
        }

        while (i < size1) {
            if(simbolo == '-') {
                listRet.add(list1.get(i) + " " + simbolo + " " + "*****");
            }else if(simbolo == '|'){
                if(peso == 1) {
                    listRet.add(list1.get(i) + " " + simbolo + " " + "*****  -  *****");
                }else if(peso == 2){
                    listRet.add("*****  -  *****" + " " + simbolo + " " + list1.get(i) + " " + simbolo + " " + "*****  -  *****");
                }
            }
            i++;
        }

        while(j < size2){
            if(simbolo == '-') {
                listRet.add("*****" + " " + simbolo + " " + list2.get(j));
            }else if(simbolo == '|'){
                if(peso == 1) {
                    listRet.add("*****  -  *****" + " " + simbolo + " " + list2.get(j));
                }else if(peso == 2){
                    listRet.add("*****  -  *****" + " " + simbolo + " " + "*****  -  *****" + " " + simbolo + " " + list2.get(j));
                }
            }
            j++;
        }

        return listRet;
    }

    private static List<String> naturalMerge(List<String> list1, List<String> list2, char simbolo, int peso){
        List<String> listRet = new ArrayList<String>();
        int size1, size2, i, j;

        size1 = list1.size();
        size2 = list2.size();

        i = 0;
        j = 0;

        while(i<size1 && j<size2){
            listRet.add(list1.get(i) + " " + simbolo + " " + list2.get(j));
            i++;
            j++;
        }

        while (i < size1) {
            if(simbolo == '-') {
                listRet.add(list1.get(i) + " " + simbolo + " " + "*****");
            }else if(simbolo == '|'){
                listRet.add(list1.get(i) + " " + simbolo + " " + "*****  -  *****");
            }
            i++;
        }

        while(j < size2){
            if(simbolo == '-') {
                listRet.add("*****" + " " + simbolo + " " + list2.get(j));
            }else if(simbolo == '|'){
                if(peso == 1) {
                    listRet.add("*****  -  *****" + " " + simbolo + " " + list2.get(j));
                }else if(peso == 2){
                    listRet.add("*****  -  *****" + " " + simbolo + " " + "*****  -  *****" + " " + simbolo + " " + list2.get(j));
                }
            }
            j++;
        }

        return listRet;
    }

    public static List<Itinerario> pesquisarItinerario(String pesquisarPor, String value){
        List<Itinerario> ret = new ArrayList<Itinerario>();
        Cursor query = null;

        if(pesquisarPor.equals("nome do ônibus")){
            query =  dataBase.rawQuery("SELECT DISTINCT A.numero, A.nome, C.referencia FROM onibus A, itinerario B, ponto C WHERE lower(A.nome) LIKE '%' || ? || '%' AND A.idItinerario = B.idItinerario AND B.idPonto = C.id AND A.sentido = 'I' ORDER BY B.sort", new String[]{value});

            if(query!=null){
                if(query.moveToFirst()){
                    do{
                        Itinerario i = new Itinerario();
                        i.setNomeOnibus(query.getString(query.getColumnIndex("nome")));
                        i.setNumeroOnibus(query.getString(query.getColumnIndex("numero")));
                        String tempS = query.getString(query.getColumnIndex("referencia"));
                        tempS = Capitalizar(tempS.replaceAll("[0-9]|,|-|N/D", ""));
                        tempS = tempS.trim();
                        i.setReferencia(tempS);
                        i.setTipo("I");
                        ret.add(i);
                    }while(query.moveToNext());
                }
                query.close();
            }

            query =  dataBase.rawQuery("SELECT DISTINCT A.numero, A.nome, C.referencia, A.sentido FROM onibus A, itinerario B, ponto C WHERE lower(A.nome) LIKE '%' || ? || '%' AND A.idItinerario = B.idItinerario AND B.idPonto = C.id AND A.sentido != 'I' ORDER BY B.sort", new String[]{value});

            if(query!=null){
                if(query.moveToFirst()){
                    do{
                        Itinerario i = new Itinerario();
                        i.setNomeOnibus(query.getString(query.getColumnIndex("nome")));
                        i.setNumeroOnibus(query.getString(query.getColumnIndex("numero")));
                        String tempS = query.getString(query.getColumnIndex("referencia"));
                        tempS = Capitalizar(tempS.replaceAll("[0-9]|,|-|N/D", ""));
                        tempS = tempS.trim();
                        i.setReferencia(tempS);
                        i.setTipo(query.getString(query.getColumnIndex("sentido")));
                        ret.add(i);
                    }while(query.moveToNext());
                }
                query.close();
            }
        }else{
            query =  dataBase.rawQuery("SELECT DISTINCT A.numero, A.nome, C.referencia FROM onibus A, itinerario B, ponto C WHERE A.numero = ? AND A.idItinerario = B.idItinerario AND B.idPonto = C.id AND A.sentido = 'I' ORDER BY B.sort", new String[]{value});

            if(query!=null){
                if(query.moveToFirst()){
                    do{
                        Itinerario i = new Itinerario();
                        i.setNomeOnibus(query.getString(query.getColumnIndex("nome")));
                        i.setNumeroOnibus(query.getString(query.getColumnIndex("numero")));
                        String tempS = query.getString(query.getColumnIndex("referencia"));
                        tempS = Capitalizar(tempS.replaceAll("[0-9]|,|-|N/D", ""));
                        tempS = tempS.trim();
                        i.setReferencia(tempS);
                        i.setTipo("I");
                        ret.add(i);
                    }while(query.moveToNext());
                }
                query.close();
            }

            query =  dataBase.rawQuery("SELECT DISTINCT A.numero, A.nome, C.referencia, A.sentido FROM onibus A, itinerario B, ponto C WHERE A.numero = ? AND A.idItinerario = B.idItinerario AND B.idPonto = C.id AND A.sentido != 'I' ORDER BY B.sort", new String[]{value});

            if(query!=null){
                if(query.moveToFirst()){
                    do{
                        Itinerario i = new Itinerario();
                        i.setNomeOnibus(query.getString(query.getColumnIndex("nome")));
                        i.setNumeroOnibus(query.getString(query.getColumnIndex("numero")));
                        String tempS = query.getString(query.getColumnIndex("referencia"));
                        tempS = Capitalizar(tempS.replaceAll("[0-9]|,|-|N/D", ""));
                        tempS = tempS.trim();
                        i.setReferencia(tempS);
                        i.setTipo(query.getString(query.getColumnIndex("sentido")));
                        ret.add(i);
                    }while(query.moveToNext());
                }
                query.close();
            }
        }

        return ret;
    }

    public static List<Ponto> listarPontosETerminais(){
        List<Ponto> ret = new ArrayList<Ponto>();
        Cursor query = null;

        query = dataBase.rawQuery("SELECT A.lat, A.lng, A.referencia, B.nome, A.id FROM ponto A, bairro B, itinerario C WHERE A.idBairro = B.id AND C.idPonto = A.id", null);

        if(query!=null){
            if(query.moveToFirst()){
                do{
                    Ponto p = new Ponto();
                    p.setLatitude(query.getString(query.getColumnIndex("lat")));
                    p.setLongitude(query.getString(query.getColumnIndex("lng")));
                    String tempS = query.getString(query.getColumnIndex("referencia"));
                    tempS = Capitalizar(tempS.replaceAll("[0-9]|,|-|N/D", ""));
                    tempS = tempS.trim();
                    p.setReferencia(tempS);
                    p.setBairro(query.getString(query.getColumnIndex("nome")));
                    p.setId(query.getString(query.getColumnIndex("id")));
                    ret.add(p);
                }while(query.moveToNext());
            }
            query.close();
        }
        return ret;
    }

    public static List<Ponto> listarPontosETerminaisNotTP(){
        List<Ponto> ret = new ArrayList<Ponto>();
        Cursor query = null;

        query = dataBase.rawQuery("SELECT A.latitude, A.longitude, A.referencia, B.nome, A.id  FROM PONTOS A, BAIRROS B WHERE A.bairro = B.id AND A.sentido != 'TP'", null);

        if(query!=null){
            if(query.moveToFirst()){
                do{
                    Ponto p = new Ponto();
                    p.setLatitude(query.getString(query.getColumnIndex("latitude")));
                    p.setLongitude(query.getString(query.getColumnIndex("longitude")));
                    p.setReferencia(query.getString(query.getColumnIndex("referencia")));
                    p.setBairro(query.getString(query.getColumnIndex("nome")));
                    p.setId(query.getString(query.getColumnIndex("id")));
                    ret.add(p);
                }while(query.moveToNext());
            }
            query.close();
        }
        return ret;
    }

    public static List<Ponto> listarPontosETerminaisNotPT(){
        List<Ponto> ret = new ArrayList<Ponto>();
        Cursor query = null;

        query = dataBase.rawQuery("SELECT A.latitude, A.longitude, A.referencia, B.nome, A.id FROM PONTOS A, BAIRROS B WHERE A.bairro = B.id AND A.sentido != 'PT'", null);

        if(query!=null){
            if(query.moveToFirst()){
                do{
                    Ponto p = new Ponto();
                    p.setLatitude(query.getString(query.getColumnIndex("latitude")));
                    p.setLongitude(query.getString(query.getColumnIndex("longitude")));
                    p.setReferencia(query.getString(query.getColumnIndex("referencia")));
                    p.setBairro(query.getString(query.getColumnIndex("nome")));
                    p.setId(query.getString(query.getColumnIndex("id")));
                    ret.add(p);
                }while(query.moveToNext());
            }
            query.close();
        }
        return ret;
    }

    public static List<PontoTuristicoSimples> listarPontosTuristicos() {
        List<PontoTuristicoSimples> ret = new ArrayList<PontoTuristicoSimples>();
        Cursor query = null;

        query = dataBase.rawQuery("SELECT id, nome FROM pontoTuristico", null);

        if (query != null) {
            if (query.moveToFirst()) {
                do {
                    PontoTuristicoSimples p = new PontoTuristicoSimples();
                    p.setId(query.getString(query.getColumnIndex("id")));
                    p.setNome(query.getString(query.getColumnIndex("nome")));
                    ret.add(p);
                } while (query.moveToNext());
            }
            query.close();
        }
        return ret;
    }

    public static PontoTuristico pesquisarPontoTuristico(String nome){
        PontoTuristico p = new PontoTuristico();
        Cursor query = null;

        query = dataBase.rawQuery("SELECT * FROM pontoTuristico WHERE nome = ?", new String[]{nome});

        if(query!=null){
            if(query.moveToFirst()){
                p.setId(query.getString(query.getColumnIndex("id")));
                p.setNome(query.getString(query.getColumnIndex("nome")));
                p.setDescricao(query.getString(query.getColumnIndex("descricao")));
                p.setSite(query.getString(query.getColumnIndex("site")));
                p.setLat(query.getDouble(query.getColumnIndex("lat")));
                p.setLng(query.getDouble(query.getColumnIndex("lng")));
            }
        }

        return p;
    }

    public static List<Onibus> pesquisarRotaEntreDoisPontos(Ponto a, Ponto b, String diurnoNoturno){
        List<Onibus> ret = new ArrayList<Onibus>();
        Cursor query = null;

        Log.d("TesteBd", a.getId() + " " + b.getId());
        query = dataBase.rawQuery("SELECT DISTINCT A.numero, A.nome, A.informacoesAdicionais FROM onibus A, itinerario B, itinerario C WHERE B.idPonto = " + a.getId() + " AND C.idPonto = " + b.getId() + " AND B.idItinerario = C.idItinerario AND C.idItinerario = A.idItinerario AND A.diurnoNoturno = '" + diurnoNoturno + "'", null);
        //query = dataBase.rawQuery("SELECT DISTINCT numero, nome, informacoesAdicionais FROM onibus WHERE onibus.numero = 300", null);

        if(query!=null){
            if(query.moveToFirst()){
                do {
                    Onibus o = new Onibus();
                    o.setNumero(query.getInt(query.getColumnIndex("numero")));
                    o.setNome(query.getString(query.getColumnIndex("nome")));
                    o.setInformacoesAdicionais(query.getString(query.getColumnIndex("informacoesAdicionais")));
                    ret.add(o);
                }while(query.moveToNext());
            }
        }

        return ret;
    }

    public static List<Ponto> pesquisarConexaoGrafo(Ponto a){
        List<Ponto> ret = new ArrayList<Ponto>();
        Cursor query = null;

        query = dataBase.rawQuery("SELECT DISTINCT A.lat, A.lng, A.referencia, A.id, E.nome FROM ponto A, itinerario B, itinerario C, terminal D, bairro E WHERE B.idPonto = " + a.getId() + " AND C.idPonto = A.id AND B.idItinerario = C.idItinerario AND A.id = D.idPonto AND D.possuiConexao = 'T' AND E.id = A.idBairro", null);

        if(query!=null){
            if(query.moveToFirst()){
                do{
                    Ponto p = new Ponto();
                    p.setLatitude(query.getString(query.getColumnIndex("lat")));
                    p.setLongitude(query.getString(query.getColumnIndex("lng")));
                    String tempS = query.getString(query.getColumnIndex("referencia"));
                    tempS = Capitalizar(tempS.replaceAll("[0-9]|,|-|N/D", ""));
                    tempS = tempS.trim();
                    p.setReferencia(tempS);
                    p.setBairro(query.getString(query.getColumnIndex("nome")));
                    p.setId(query.getString(query.getColumnIndex("id")));
                    ret.add(p);
                }while(query.moveToNext());
            }
        }

        return ret;
    }
}
