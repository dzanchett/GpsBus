package com.cefet.gpsbus;

import android.app.Instrumentation;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.test.InstrumentationRegistry;
import android.test.InstrumentationTestCase;
import static android.support.test.InstrumentationRegistry.getTargetContext;

import com.google.android.gms.location.places.Place;
import com.google.android.gms.maps.model.LatLng;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Admin on 21/03/2018.
 */

public class AlgoritimoBuscaTest {
    @Before
    public void before(){
        Context context = InstrumentationRegistry.getTargetContext();
        DbHelper mDbHelper = new DbHelper(context);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        DB.createDb(db);
    }

    @Test
    public void getPontoNearLatLngTest1() throws Exception {
        AlgoritimoBusca algoritimo = new AlgoritimoBusca(null, null, 0, 0, 0);
        LatLng latLng = new LatLng(-22.4748067, -43.1530076);
        List<Ponto> listaPontos = DB.listarPontosETerminais();
        Ponto pontoTeste = null;

        for(Ponto p : listaPontos){
            if(p.getReferencia().equals("R. João de Farias, 250")){
                pontoTeste = p;
                break;
            }
        }

        assertEquals(pontoTeste.getLatitude(), algoritimo.getPontoNearLatLng(latLng, "PT").getLatitude(), 0.0);
    }

    @Test
    public void getPontoNearLatLngTest2() throws Exception {
        AlgoritimoBusca algoritimo = new AlgoritimoBusca(null, null, 0, 0, 0);
        LatLng latLng = new LatLng(-22.4748067, -43.1530076);
        List<Ponto> listaPontos = DB.listarPontosETerminais();
        Ponto pontoTeste = null;

        for(Ponto p : listaPontos){
            if(p.getReferencia().equals("R. João de Farias, 250")){
                pontoTeste = p;
                break;
            }
        }

        assertEquals(pontoTeste.getLongitude(), algoritimo.getPontoNearLatLng(latLng, "PT").getLongitude(), 0.0);
    }

    @Test
    public void getPontoNearLatLngTest3() throws Exception {
        AlgoritimoBusca algoritimo = new AlgoritimoBusca(null, null, 0, 0, 0);
        LatLng latLng = new LatLng(-22.4748067, -43.1530076);
        List<Ponto> listaPontos = DB.listarPontosETerminais();
        Ponto pontoTeste = null;

        for(Ponto p : listaPontos){
            if(p.getReferencia().equals("R. João de Farias, 250")){
                pontoTeste = p;
                break;
            }
        }

        assertEquals(pontoTeste.getReferencia(), algoritimo.getPontoNearLatLng(latLng, "PT").getReferencia());
    }

    @Test
    public void getPontoNearLatLngTest4() throws Exception {
        AlgoritimoBusca algoritimo = new AlgoritimoBusca(null, null, 0, 0, 0);
        LatLng latLng = new LatLng(-22.4748067, -43.1530076);
        List<Ponto> listaPontos = DB.listarPontosETerminais();
        Ponto pontoTeste = null;

        for(Ponto p : listaPontos){
            if(p.getReferencia().equals("R. João de Farias, 250")){
                pontoTeste = p;
                break;
            }
        }

        assertEquals(pontoTeste.getBairro(), algoritimo.getPontoNearLatLng(latLng, "PT").getBairro());
    }
}
