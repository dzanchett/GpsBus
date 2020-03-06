package com.cefet.gpsbus;

/**
 * Created by diego on 26/02/2018.
 */

public class Itinerario {

    private String numeroOnibus;
    private String nomeOnibus;
    private String referencia;
    private String tipo;

    public String getNumeroOnibus() {
        return numeroOnibus;
    }

    public void setNumeroOnibus(String numeroOnibus) {
        this.numeroOnibus = numeroOnibus;
    }

    public String getNomeOnibus() {
        return nomeOnibus;
    }

    public void setNomeOnibus(String nomeOnibus) {
        this.nomeOnibus = nomeOnibus;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
