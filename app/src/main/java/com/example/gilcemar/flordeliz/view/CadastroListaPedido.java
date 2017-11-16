package com.example.gilcemar.flordeliz.view;

import android.app.Activity;
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
import com.example.gilcemar.flordeliz.control.ControladorItemPedido;
import com.example.gilcemar.flordeliz.model.ItemPedido;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CadastroListaPedido extends AppCompatActivity implements AdapterView.OnItemClickListener{
    private List<ItemPedido> listaDePedidos;
    private ListView listViewItensPedido;
    private ItemPedido item = new ItemPedido();
    private BaseAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_lista_pedido);

        listaDePedidos = new ArrayList<ItemPedido>();
        listViewItensPedido = findViewById(R.id.listaItemPedido);
        Intent in = getIntent();
        Bundle bundle = in.getExtras();
        String teste = "";
        if (bundle!=null){
            teste = (String) bundle.get("ITENSPEDIDO");
            preencherItemPedido(teste);
        }
        //ArrayAdapter<ItemLote> adapter = new ArrayAdapter<ItemLote>(this, android.R.layout.simple_list_item_1, listaItens);
        //ArrayAdapter<ItemLote> adapter = new ArrayAdapter<ItemLote>(this,android.R.layout.simple_list_item_1, cursos);
        adapter = new AdaptadorItemPedido(listaDePedidos,this);
        listViewItensPedido.setAdapter(adapter);
        listViewItensPedido.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ItemPedido itemPedido = (ItemPedido) parent.getAdapter().getItem(position);
        item = itemPedido;
        Intent intent = new Intent(CadastroListaPedido.this, CadastroItemPedido.class);
        intent.putExtra("ITEMPEDIDO", itemPedido);
        startActivityForResult(intent,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        new TarefaAssincrona().execute(atualizarLista(this));
        super.onActivityResult(requestCode, resultCode, data);


    }

    public Object[] atualizarLista(Activity activity){
        String[] parametros = new String[3];
        Object[] param = new Object[2];
        if(item !=null){

            parametros[0] = item.getQuantItem();
            parametros[1] = item.getCodCalProd();
            parametros[2] = item.getIdPedido();

        }
        param[0]=parametros;
        param[1]=activity;
        return parametros;
    }

    public boolean preencherItemPedido(String resultado){

        JSONArray jsonArray;
        try {
            jsonArray= new JSONArray(resultado);
            listaDePedidos.clear();
            if (jsonArray.length()>0){
                for(int i=0;i<jsonArray.length();i++){
                    JSONObject row = jsonArray.getJSONObject(i);
                    ItemPedido item = new ItemPedido();
                    item.setQuantItem(row.getString("quantItem"));
                    item.setCodCalProd(row.getString("codCalcProd"));
                    item.setIdPedido(row.getString("idPedido"));
                    listaDePedidos.add(item);
                }
            }
            listViewItensPedido.setAdapter(adapter);
            return true;
        } catch (JSONException e) {
            e.printStackTrace();

            return false;
        }

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

                ControladorItemPedido controlador = new ControladorItemPedido();
                qualErro = controlador.pesquisar(parametros, getApplicationContext());
                resultado[0]= "botaoPesq";
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

            boolean ok = preencherItemPedido((String) resultado[1]);
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
