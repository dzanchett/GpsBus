package com.cefet.gpsbus;

import android.util.Log;

import com.google.android.gms.location.places.Place;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by diego on 10/03/2018.
 */

public class AlgoritimoBusca {

    private Place origem;
    private Place destino;
    private int horaSaida;
    private int minutoSaida;
    private int diaDaSemana;

    public AlgoritimoBusca(Place origem, Place destino, int horaSaida, int minutoSaida, int diaDaSemana) {
        this.origem = origem;
        this.destino = destino;
        this.horaSaida = horaSaida;
        this.minutoSaida = minutoSaida;
        this.diaDaSemana = diaDaSemana;
    }

    public List<Object> busca(){
        /*//DEBUG
        List<Object> rotaPronta = new ArrayList<Object>();
        Ponto pontoOrigem = getPontoNearLatLng(InputUsuario.getLatLngOrigem(), "");

        Onibus o1 = new Onibus();
        Onibus o2 = new Onibus();
        Onibus o3 = new Onibus();

        o1.setNome("Teste");
        o1.setNumero(1);

        o2.setNome("Teste");
        o2.setNumero(2);

        o3.setNome("Teste");
        o3.setNumero(3);

        rotaPronta.add("Trajeto a pé até o ponto");
        rotaPronta.add(InputUsuario.getLatLngOrigem());
        rotaPronta.add(new LatLng(pontoOrigem.getLatitude(), pontoOrigem.getLongitude()));
        rotaPronta.add("Trajeto de ônibus");
        rotaPronta.add(pontoOrigem);
        rotaPronta.add(o1);
        rotaPronta.add(o2);
        rotaPronta.add(o3);
        rotaPronta.add(pontoOrigem);
        rotaPronta.add("Trajeto a pé do ponto ao destino");
        rotaPronta.add(InputUsuario.getLatLngOrigem());
        rotaPronta.add(new LatLng(pontoOrigem.getLatitude(), pontoOrigem.getLongitude()));*/


        List<Object> rotaPronta = new ArrayList<Object>();
        LatLng latLngOrigem;
        LatLng latLngDestino;
        Ponto pontoProximoOrigem;
        Ponto pontoMaisProxioDestino;
        List<Object> rotaBus;

        if(InputUsuario.getTipoPesquisa().equals("PESQUISA_TURISTICA")){
            latLngOrigem = InputUsuario.getLatLngOrigem();
            latLngDestino = InputUsuario.getLatLngDestino();
        }else if(InputUsuario.getTipoPesquisa().equals("PESQUISA_SIMPLES")){
            latLngOrigem = InputUsuario.getLatLngOrigem();
            latLngDestino = destino.getLatLng();
        }else {
            latLngOrigem = origem.getLatLng();
            latLngDestino = destino.getLatLng();
        }

        pontoProximoOrigem = getPontoNearLatLng(latLngOrigem);
        pontoMaisProxioDestino = getPontoNearLatLng(latLngDestino);

        rotaBus = getRotaSemConexao(pontoProximoOrigem, pontoMaisProxioDestino);

        if(rotaBus == null){
            //nesse caso será nescessário usar a busca com conexões.
            rotaBus = getRotaComConexao(pontoProximoOrigem, pontoMaisProxioDestino);
        }

        if(rotaBus.size() == 0){
            return null;
        }else{
            rotaPronta.add("Trajeto a pé até o ponto");
            rotaPronta.add(latLngOrigem);
            rotaPronta.add(new LatLng(pontoProximoOrigem.getLatitude(), pontoProximoOrigem.getLongitude()));
            for(Object o : rotaBus){
                rotaPronta.add(o);
            }
            rotaPronta.add("Trajeto a pé do ponto ao destino");
            rotaPronta.add(new LatLng(pontoMaisProxioDestino.getLatitude(), pontoMaisProxioDestino.getLongitude()));
            rotaPronta.add(latLngDestino);

            return rotaPronta;
        }
    }

    /*public List<Object> busca(){
        List<Object> rotaPronta = new ArrayList<Object>();

        Ponto pontoMaisProximoOrigemTP;
        Ponto pontoMaisProximoOrigemPT;
        if(InputUsuario.getTipoPesquisa().equals("PESQUISA_SIMPLES")) {
            pontoMaisProximoOrigemTP = getPontoNearLatLng(InputUsuario.getLatLngOrigem(), "TP");
            pontoMaisProximoOrigemPT = getPontoNearLatLng(InputUsuario.getLatLngOrigem(), "PT");
        }else{
            pontoMaisProximoOrigemTP = getPontoNearLatLng(origem.getLatLng(), "TP");
            pontoMaisProximoOrigemPT = getPontoNearLatLng(origem.getLatLng(), "PT");
        }
        Ponto pontoMaisProximoDestinoTP = getPontoNearLatLng(destino.getLatLng(), "TP");
        Ponto pontoMaisProximoDestinoPT = getPontoNearLatLng(destino.getLatLng(), "PT");

        List<Object> rotaSemConexaoTP = getRotaSemConexao(pontoMaisProximoOrigemTP, pontoMaisProximoDestinoTP);
        List<Object> rotaSemConexaoPT = getRotaSemConexao(pontoMaisProximoOrigemPT, pontoMaisProximoDestinoPT);

        if(rotaSemConexaoPT != null){
            rotaPronta = rotaSemConexaoPT;
        }else if(rotaSemConexaoTP != null){
            rotaPronta = rotaSemConexaoTP;
        }else{
            //busca com conexão.
            List<Object> rota = new ArrayList<Object>();
            Ponto p1 = new Ponto();
            p1.setReferencia("Ponto: R. João de Farias, 250");
            p1.setBairro("Itamaraty");
            Onibus o1 = new Onibus();
            o1.setNumero(300);
            o1.setNome("Terminal Corrêas");
            Ponto t = new Ponto();
            t.setReferencia("Terminal:");
            t.setBairro("Corrêas");
            Onibus o2 = new Onibus();
            o2.setNumero(611);
            o2.setNome("Bonfim");
            Ponto p2 = new Ponto();
            p2.setReferencia("Estrada do Bonfim, 1001");
            p2.setBairro("Corrêas");
            rota.add(p1);
            rota.add(o1);
            rota.add(t);
            rota.add(o2);
            rota.add(p2);
            rotaPronta = rota;
        }

        //para debug:
        rotaPronta.add(pontoMaisProximoOrigemPT);
        rotaPronta.add(pontoMaisProximoDestinoPT);

        return rotaPronta;
    }*/

    public Ponto getPontoNearLatLng(LatLng latLng){

        List<Ponto> listaPontos;

        listaPontos = DB.listarPontosETerminais();

        double menor = Double.POSITIVE_INFINITY;
        Ponto pontoMaisProximo = null;

        for(Ponto p : listaPontos){
            double dist = Haversine.distancia(latLng.latitude, latLng.longitude, p.getLatitude(), p.getLongitude());

            if(dist<menor){
                menor = dist;
                pontoMaisProximo = p;
            }
        }

        return pontoMaisProximo;
    }

    public List<Object> getRotaSemConexao(Ponto a, Ponto b){
        List<Object> rota;
        String diurnoNoturno;

        if(horaSaida>=6&&horaSaida<23){
            diurnoNoturno = "D";
        }else{
            diurnoNoturno = "N";
        }
        Log.d("horaSaida", Integer.toString(horaSaida));

        List<Onibus> onibusDisponiveis = DB.pesquisarRotaEntreDoisPontos(a, b, diurnoNoturno);

        if(onibusDisponiveis.isEmpty()){
            Log.d("buscaa", "erro");
            rota = null;
        }else{
            Log.d("buscaa", "ok");
            rota = new ArrayList<Object>();
            rota.add("Trajeto de ônibus:");
            rota.add(a);
            for(Onibus o : onibusDisponiveis){
                rota.add(o);
            }
            rota.add(b);
        }

        return rota;
    }

    public List<Object> getRotaComConexao(Ponto a, Ponto b){
        List<Object> rota = new ArrayList<Object>();
        List<List<Ponto>> rotasPonto = new ArrayList<List<Ponto>>();

        List<Ponto> conexoesOrigem = DB.pesquisarConexaoGrafo(a);
        List<Ponto> conexoesDestino = DB.pesquisarConexaoGrafo(b);

        Queue<Vertice> fila = new LinkedList<Vertice>();

        for(Ponto p : conexoesOrigem){
            Vertice v = new Vertice();
            v.ponto = p;
            v.caminho = new ArrayList<Ponto>();
            v.caminho.add(p);
            v.componente = new ArrayList<Integer>();
            fila.add(v);
        }

        Boolean podeIr;

        while(!fila.isEmpty()){
            Vertice at = fila.remove();
            at.componente.add(new Integer(at.ponto.getId()));

            podeIr = true;

            for(Ponto p : conexoesDestino){
                if(at.ponto.getId().equals(p.getId())){
                    List<Ponto> rotaPonto = at.caminho;
                    rotasPonto.add(rotaPonto);
                    podeIr = false;
                    continue;
                }
            }

            if(podeIr) {
                List<Ponto> conexoesTemp = DB.pesquisarConexaoGrafo(at.ponto);

                for (Ponto p : conexoesTemp) {
                    int idTemp = new Integer(p.getId());

                    if (!at.componente.contains(idTemp)) {
                        Vertice v = new Vertice();
                        v.ponto = p;
                        v.caminho = at.caminho;
                        v.caminho.add(p);
                        v.componente = at.componente;
                        fila.add(v);
                    }
                }
            }
        }

        int menor = 1000;

        for(List<Ponto> l : rotasPonto){
            if(l.size()<menor){
                menor = l.size();
            }
        }

        Boolean pesquisando = true;

        while (pesquisando) {
            pesquisando = false;
            int i = 1;
            for (List<Ponto> l : rotasPonto) {
                List<Object> listLoop = new ArrayList<Object>();
                if (l.size() == menor) {
                    Ponto ini;
                    Ponto fim;
                    ini = a;
                    for (Ponto p : l) {
                        fim = p;
                        List<Object> tempOb = getRotaSemConexao(ini, fim);
                        if (tempOb == null) {
                            ini = null;
                            break;
                        }
                        listLoop.addAll(tempOb);
                        ini = p;
                    }
                    if(ini==null){
                        continue;
                    }
                    fim = b;
                    List<Object> tempOb = getRotaSemConexao(ini, fim);
                    if (tempOb == null) {
                       continue;
                    }
                    listLoop.addAll(tempOb);
                    rota.addAll(listLoop);
                    i++;
                }
            }
            if (rota.size() == 0 && menor < 10) {
                Log.d("NaoAchou", "naoAchou");
                menor++;
                pesquisando = true;
            }
        }

        return rota;
    }
}