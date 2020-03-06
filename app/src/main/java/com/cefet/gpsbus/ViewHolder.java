package com.cefet.gpsbus;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewHolder extends RecyclerView.ViewHolder{

    final TextView titulo;
    final TextView descricao;
    final ImageView icon;

    public ViewHolder(View view){
        super(view);
        titulo = (TextView) view.findViewById(R.id.item_text_view);
        descricao = (TextView) view.findViewById(R.id.detail_text_view);
        icon = (ImageView) view.findViewById(R.id.item_icon);
    }

}
