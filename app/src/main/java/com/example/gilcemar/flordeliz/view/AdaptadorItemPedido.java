package com.example.gilcemar.flordeliz.view;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.gilcemar.flordeliz.R;
import com.example.gilcemar.flordeliz.model.ItemPedido;

import java.util.List;

/**
 * Created by gilcemar on 16/11/17.
 */

public class AdaptadorItemPedido extends BaseAdapter{
    List<ItemPedido> lista;

    Activity activity;

    public AdaptadorItemPedido(List<ItemPedido> lista, Activity activity) {
        this.lista = lista;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int position) {
        return lista.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = activity.getLayoutInflater();
        View viewItem =layoutInflater.inflate(R.layout.item_pedido, parent, false);
        ItemPedido item = lista.get(position);
        TextView cabecalho = (TextView) viewItem.findViewById(R.id.cabecalhoListaItemPedido);
        TextView numeroPedido= (TextView) viewItem.findViewById(R.id.textViewNumPedido);
        TextView codCalProd = (TextView) viewItem.findViewById(R.id.textViewCodCalcItemPedido);
        TextView quantComprada = (TextView) viewItem.findViewById(R.id.textViewQuantComprada);

        cabecalho.setText("Item " + (position +1));
        numeroPedido.setText(item.getIdPedido());
        codCalProd.setText(item.getCodCalProd());
        quantComprada.setText(item.getQuantItem());

        return viewItem;
    }
}
