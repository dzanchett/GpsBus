package com.cefet.gpsbus;

import java.util.ArrayList;
import java.util.List;

public class NoArvoreRota {

    private int tipoDado;
    private String titulo;
    private List<Object> listaDados;
    private List<NoArvoreRota> filhos = new ArrayList<NoArvoreRota>();

    public NoArvoreRota(int tipoDado, String titulo, List<Object> listaDados) {
        this.tipoDado = tipoDado;
        this.titulo = titulo;
        this.listaDados = listaDados;
    }

    public void adicionaFilho(NoArvoreRota no){
        filhos.add(no);
    }

    public int getTipoDado() {
        return tipoDado;
    }

    public String getTitulo() {
        return titulo;
    }

    public List<Object> getListaDados() {
        return listaDados;
    }

    public List<NoArvoreRota> getFilhos() {
        return filhos;
    }
}
