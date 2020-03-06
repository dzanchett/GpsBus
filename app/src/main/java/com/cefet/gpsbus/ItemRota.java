package com.cefet.gpsbus;

public class ItemRota {

    private String titulo;
    private String descricao;
    private int icon;

    final static int ICON_WALK = 0;
    final static int ICON_CAR = 1;
    final static int ICON_WARNING = 2;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
