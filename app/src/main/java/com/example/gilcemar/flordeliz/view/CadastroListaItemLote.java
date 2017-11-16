package com.example.gilcemar.flordeliz.view;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.gilcemar.flordeliz.R;
import com.example.gilcemar.flordeliz.control.ControladorItemLote;
import com.example.gilcemar.flordeliz.model.ItemLote;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CadastroListaItemLote extends AppCompatActivity implements AdapterView.OnItemClickListener{
    ItemLote item = new ItemLote();
    List<ItemLote> listaItens;
    ListView lista;
    BaseAdapter adapter;


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
        adapter = new AdaptadorItemLote(listaItens,this);

        lista.setAdapter(adapter);
        lista.setOnItemClickListener(this);
        if(adapter.getCount()==0){
            Intent intent = new Intent(CadastroListaItemLote.this, CadastroItemLote.class);
            startActivity(intent);
        }
    }

    public Object[] atualizarLista(){
        String[] parametros = new String[7];
        Object[] param = new Object[2];
        if(item !=null){

            parametros[0] = item.getCodCalPro();
            parametros[1] = item.getIdLote();
            parametros[2] = "";
            parametros[3] = "";
            parametros[4] = "";
            parametros[5] = "";
            parametros[6] = "";
        }
        param[0]=parametros;

        return parametros;
    }



    public boolean preencherItemLote(String resultado){

        JSONArray jsonArray;
        try {
            jsonArray= new JSONArray(resultado);
            listaItens.clear();
            if (jsonArray.length()>0){
                for(int i=0;i<jsonArray.length();i++){
                    JSONObject row = jsonArray.getJSONObject(i);
                    item = new ItemLote();
                    item.setCodCalPro(row.getString("codCalcProd"));
                    item.setIdLote(row.getString("idLote"));
                    item.setTamCal(row.getString("tamCalc"));
                    item.setQuanCalProd(row.getString("quantCalcProd"));
                    item.setQuantCalDispon(row.getString("quantCalcDispon"));
                    item.setPrecoRevenda(row.getString("precoRevenda"));
                    listaItens.add(item);
                }
                lista.setAdapter(adapter);
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
        startActivityForResult(intent,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        new TarefaAssincrona().execute(atualizarLista());
        super.onActivityResult(requestCode, resultCode, data);
    }

    private class TarefaAssincrona extends AsyncTask<Object[],Void, Object[]> {

        @Override
        protected Object[] doInBackground(Object[]... objects) {
            //String[] parametros = (String[])objects[0][0];
            //View acao = (View) objects[0][1];
            Object[] resultado = new Object[5];
            String[] qualErro = new String[3];
            String[] parametros = (String[])objects[0];
            //Activity activity = (Activity) objects[0][1];
            try {

                ControladorItemLote controlador = new ControladorItemLote();
                qualErro = controlador.pesquisar(parametros, getApplicationContext());
                resultado[0]= "botaoListar";
                resultado[1]= qualErro[0];
                //resultado[2]=activity;

            }catch (NullPointerException nulo){
                //qualErro = nulo.getMessage();
                resultado[0]= "botaoPesq";
                resultado[1]= qualErro[0];
                //resultado[2]=activity;
            }

            return resultado;

        }

        protected void onPostExecute(Object[] resultado){

            boolean ok = preencherItemLote((String) resultado[1]);
            if(ok){
                //Activity activity= (Activity) resultado[2];
                //BaseAdapter adapter = new AdaptadorItemPedido(listaDePedidos, activity);
                //listViewItensPedido.setAdapter(adapter);
                Toast.makeText(getApplicationContext(),"Lista atualizada", Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(getApplicationContext(),"Nenhuma atualização", Toast.LENGTH_LONG).show();
            }

        }
    }
}
