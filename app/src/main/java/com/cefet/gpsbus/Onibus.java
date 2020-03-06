package com.cefet.gpsbus;

/**
 * Created by diego on 23/02/2018.
 */

public class Onibus {

    private int numero;
    private String nome;
    private String referencia;
    private String diurnoNoturno;
    private String informacoesAdicionais;

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getDiurnoNoturno() {
        return diurnoNoturno;
    }

    public void setDiurnoNoturno(String diurnoNoturno) {
        this.diurnoNoturno = diurnoNoturno;
    }

    public String getInformacoesAdicionais() {
        return informacoesAdicionais;
    }

    public void setInformacoesAdicionais(String informacoesAdicionais) {
        this.informacoesAdicionais = informacoesAdicionais;
    }
}
