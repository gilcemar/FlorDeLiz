package com.example.gilcemar.flordeliz.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.example.gilcemar.flordeliz.R;
import com.example.gilcemar.flordeliz.model.ItemLote;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CadastroListaItemLote extends AppCompatActivity implements AdapterView.OnItemClickListener{

    List<ItemLote> listaItens;
    ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_item_lote);

        lista = (ListView)findViewById(R.id.lista);
        lista.setOnItemClickListener(this);
        listaItens = new ArrayList<ItemLote>();
        Intent in = getIntent();
        Bundle bundle = in.getExtras();
        String teste = "";
        if (bundle!=null){
            teste = (String) bundle.get("numeroLote");
            preencherItemLote(teste);
        }
        //ArrayAdapter<ItemLote> adapter = new ArrayAdapter<ItemLote>(this, android.R.layout.simple_list_item_1, listaItens);
        //ArrayAdapter<ItemLote> adapter = new ArrayAdapter<ItemLote>(this,android.R.layout.simple_list_item_1, cursos);
        BaseAdapter adapter = new AdaptadorItemLote(listaItens,this);
        lista.setAdapter(adapter);
        lista.setOnItemClickListener(this);
    }



    public boolean preencherItemLote(String resultado){

        JSONArray jsonArray;
        try {
            jsonArray= new JSONArray(resultado);

            if (jsonArray.length()>0){
                for(int i=0;i<jsonArray.length();i++){
                    JSONObject row = jsonArray.getJSONObject(i);
                    ItemLote item = new ItemLote();
                    item.setCodCalPro(row.getString("codCalcProd"));
                    item.setIdLote(row.getString("idLote"));
                    item.setTamCal(row.getString("tamCalc"));
                    item.setQuanCalProd(row.getString("quantCalcProd"));
                    item.setQuantCalDispon(row.getString("quantCalcDispon"));
                    item.setPrecoRevenda(row.getString("precoRevenda"));
                    listaItens.add(item);
                }
            }
            return true;
        } catch (JSONException e) {
            e.printStackTrace();

            return false;
        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ItemLote itemLote = (ItemLote)parent.getAdapter().getItem(position);

        Intent intent = new Intent(CadastroListaItemLote.this, CadastroItemLote.class);
        intent.putExtra("ITEMLOTE", itemLote);
        startActivity(intent);
    }
}
