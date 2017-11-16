package com.example.gilcemar.flordeliz.view;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.gilcemar.flordeliz.R;
import com.example.gilcemar.flordeliz.model.ItemLote;

import java.util.List;

/**
 * Created by gilcemar on 15/11/17.
 */

public class Adaptador extends BaseAdapter{

    private List<ItemLote> lista;
    private Activity activity;

    public Adaptador(List<ItemLote> lista, Activity activity) {
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

    //A fonte utilizada para a implementação foi http://blog.alura.com.br/personalizando-uma-listview-no-android/
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = activity.getLayoutInflater();
        View viewItem =layoutInflater.inflate(R.layout.item_lote, parent, false);
        ItemLote item = lista.get(position);
        TextView cabecalho = (TextView) viewItem.findViewById(R.id.cabecalhoListaItemLote);
        TextView numeroLote = (TextView) viewItem.findViewById(R.id.textViewNumLote);
        TextView codCalProd = (TextView) viewItem.findViewById(R.id.textViewCodCalcadoProd);
        TextView quantProd = (TextView) viewItem.findViewById(R.id.textViewQuantProd);
        TextView quantDispon = (TextView) viewItem.findViewById(R.id.textViewQuantDispon);
        TextView tamCalcado = (TextView) viewItem.findViewById(R.id.textViewTamCalcado);
        TextView precoRevenda = (TextView) viewItem.findViewById(R.id.textViewPrecoRevenda);

        cabecalho.setText("Item " + (position +1));
        numeroLote.setText(item.getIdLote());
        codCalProd.setText(item.getCodCalPro());
        quantProd.setText(item.getQuanCalProd());
        quantDispon.setText(item.getQuantCalDispon());
        tamCalcado.setText(item.getTamCal());
        precoRevenda.setText(item.getPrecoRevenda());

        return viewItem;

    }
}
