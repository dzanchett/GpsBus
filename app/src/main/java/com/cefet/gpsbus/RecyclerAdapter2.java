package com.cefet.gpsbus;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class RecyclerAdapter2 extends RecyclerView.Adapter {

    private List<Object> lista;
    private Context context;
    private int mExpandedPosition = -1;

    public RecyclerAdapter2(List<Object> lista, Context context){
        this.lista = lista;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.recycle_layout, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        Object object = lista.get(position);

        /*if(object instanceof Ponto){
            Ponto p = (Ponto) object;
            if(p.getReferencia().equals("Terminal:")) {
                viewHolder.titulo.setText(Html.fromHtml("<b>" + p.getReferencia() + " " + p.getBairro() + ".</b>"));
            }else {
                viewHolder.titulo.setText(Html.fromHtml("<b>" + p.getReferencia() + " " + p.getBairro() + ".</b>"));
            }
        }else if(object instanceof Onibus){
            Onibus on = (Onibus) object;
            viewHolder.titulo.setText(Html.fromHtml("<b>" + "Ônibus: " + Integer.toString(on.getNumero()) + " - " +  on.getNome() + ".</b>"));
        }*/

        ItemRota item = (ItemRota) object;
        viewHolder.titulo.setText(Html.fromHtml(item.getTitulo()));
        viewHolder.descricao.setText(Html.fromHtml(item.getDescricao(), null, new MyTagHandler()));

        switch (item.getIcon()){
            case ItemRota.ICON_WALK:
                viewHolder.icon.setImageResource(R.drawable.walk);
                break;
            case ItemRota.ICON_CAR:
                viewHolder.icon.setImageResource(R.drawable.bus);
                break;
            case ItemRota.ICON_WARNING:
                viewHolder.icon.setImageResource(R.drawable.warning);
                break;
        }

        /*final boolean isExpanded = position==mExpandedPosition;
        viewHolder.descricao.setVisibility(!isExpanded?View.VISIBLE:View.GONE);
        viewHolder.itemView.setActivated(!isExpanded);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mExpandedPosition = isExpanded ? -1:position;
                notifyItemChanged(position);
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

}
