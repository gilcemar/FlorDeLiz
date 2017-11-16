package com.example.gilcemar.flordeliz.view;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gilcemar.flordeliz.R;
import com.example.gilcemar.flordeliz.control.Controlador;
import com.example.gilcemar.flordeliz.control.ControladorItemPedido;
import com.example.gilcemar.flordeliz.control.ControladorPedido;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CadastroPedido extends AppCompatActivity implements View.OnClickListener{

    private String cnpjAnt;
    private String numeroPedidoAnt;
    private EditText numeroPedido;
    private EditText cnpj;
    private EditText desconto;

    private Button botaoInserir;
    private Button botaoAlterar;
    private Button botaoExcluir;
    private Button botaoPesquisar;
    private Button botaoListarItemPedido;
    private Button botaoVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_pedido);
        cnpjAnt ="";
        numeroPedidoAnt = "";

        numeroPedido = findViewById(R.id.editTextNumPedido);
        cnpj = findViewById(R.id.editTextCnpjPedido);
        desconto= findViewById(R.id.editTextDescPedido);

        this.botaoInserir = findViewById(R.id.botaoInsPedido);
        this.botaoAlterar = findViewById(R.id.botaoAltPedido);
        this.botaoExcluir = findViewById(R.id.botaoExcPedido);
        this.botaoPesquisar = findViewById(R.id.botaoPesquisarPedido);
        this.botaoListarItemPedido = findViewById(R.id.botaoListarItensPedido);
        this.botaoVoltar = findViewById(R.id.botaoVoltar);

        this.botaoInserir.setOnClickListener(this);
        this.botaoAlterar.setOnClickListener(this);
        this.botaoExcluir.setOnClickListener(this);
        this.botaoPesquisar.setOnClickListener(this);
        this.botaoListarItemPedido.setOnClickListener(this);
        this.botaoVoltar.setOnClickListener(this);
    }

    /**
     * Esse método traz todos os campos da tela, sendo que é necessário passar a view já que na classe AsyncTask é
     * solicitado qual o botão que foi acionado.
     *
     * @param v é a view da qual proveio o clique no botão.
     * @return retorna um array de Objetc, no qual estão contidos os campos e a informação de qual botão foi
     * acionado na tela.
     */
    public Object[] getCampos(View v){
        String[] parametros= new String[5];
        //começar a colocar do índice para que lá na doBackground seja colocada a ação correta

        /*Obtendo os dados do pedido*/
        parametros[0]= desconto.getText().toString();
        parametros[1]= cnpj.getText().toString();
        parametros[2]= cnpjAnt;
        parametros[3]= numeroPedido.getText().toString();
        parametros[4]= numeroPedidoAnt;
        /*Fim da obtenção dos dados do pedido*/

        //Setando os parâmetros e a view
        Object[] param = new Object[2];
        param[0] = parametros;
        param[1] = v;
        return param;
    }

    public boolean preencherCamposPedido (String resultado){
        boolean ok = false;
        JSONArray jsonArray;
        try {
            jsonArray= new JSONArray(resultado);

            JSONObject row = jsonArray.getJSONObject(0);
            //aqui tem que colocar sempre os nomes das colunas que vem no resultado do JSON, ou seja, os nomes das
            // colunas do banco de dados.

            /*Setando dados do pedido*/
            cnpj.setText(row.getString("cnpjLojaCliente"));
            desconto.setText(row.getString("desconto"));
            numeroPedido.setText(row.getString("idPedido"));
            cnpjAnt = row.getString("cnpjLojaCliente");
            numeroPedidoAnt = row.getString("idPedido");

            ok = true;
        } catch (JSONException e) {
            e.printStackTrace();

            ok = false;
        }
        return ok;
    }

    @Override
    public void onClick(View v) {
        final int id = v.getId();
        switch (v.getId()){
            case R.id.botaoVoltar:{
                //Intent it = new Intent(Principal.this, CadastroCliente.class);
                finish();
                break;
            }
            default:{
                new TarefaAssincrona().execute(getCampos(v));
            }
        }

    }

    //https://arthurlehdermann.wordpress.com/2013/03/11/asynctask-executando-tarefas-em-segundo-plano/
    //no link acima ensina como é a passagem de parâmetros na classe abaixo.
    private class TarefaAssincrona extends AsyncTask<Object[],Void, String[]> {

        @Override
        protected String[] doInBackground(Object[]... objects) {
            String[] parametros = (String[])objects[0][0];
            View acao = (View) objects[0][1];
            String parametro = "";
            String[] resultado = new String[5];
            String[] qualErro = new String[3];
            String selecao[] = new String[3];
            switch(acao.getId()){
                case R.id.botaoInsPedido:{
                    try {
                        Controlador controlador = new ControladorPedido();
                        qualErro = controlador.inserir(parametros, getApplicationContext());
                        resultado[0]= "botaoIns";
                        resultado[1]= qualErro[0];
                        resultado[2]= qualErro[1];
                        resultado[3]= qualErro[2];
                        resultado[4] = "OK";

                        break;
                    }catch (NullPointerException nulo){
                        //qualErro = nulo.getMessage();
                        resultado[0]= "botaoIns";
                        resultado[1]= qualErro[0];
                        resultado[2]= qualErro[1];
                        resultado[3]= qualErro[2];
                        resultado[4] = "OK";
                        break;
                    }
                }
                case R.id.botaoAltPedido:{
                    try {
                        Controlador controlador = new ControladorPedido();
                        selecao= controlador.alterar(parametros, getApplicationContext());
                        resultado[0] = "botaoAlt";
                        resultado[1] = selecao[0];
                        resultado[2] = selecao[1];
                        resultado[3] = selecao[2];
                        resultado[4] = "OK";

                        break;
                    }catch (NullPointerException nulo){
                        String mensagemExc = nulo.getMessage();
                        resultado[0] = "botaoAlt";
                        resultado[1] = mensagemExc;
                        //resultado[2] = selecao[1];
                        //resultado[3] = selecao[2];
                        resultado[4] = "Erro";
                        break;
                    }
                }
                case R.id.botaoExcPedido:{
                    try {
                        Controlador controlador = new ControladorPedido();
                        selecao= controlador.excluir(parametros, getApplicationContext());
                        resultado[0] = "botaoExc";
                        resultado[1] = selecao[0];
                        //resultado[2] = selecao[1];
                        //resultado[3] = selecao[2];
                        resultado[4] = "OK";
                        break;
                    }catch (NullPointerException nulo){
                        String mensagemExc = nulo.getMessage();
                        resultado[0] = "botaoExc";
                        resultado[1] = mensagemExc;
                        //resultado[2] = selecao[1];
                        //resultado[3] = selecao[2];
                        resultado[4] = "Erro";
                        break;
                    }
                }
                case R.id.botaoPesquisarPedido:{
                    try {
                        Controlador controlador = new ControladorPedido();
                        selecao= controlador.pesquisar(parametros, getApplicationContext());
                        resultado[0] = "botaoPesq";
                        resultado[1] = selecao[0];
                        //resultado[2] = selecao[1];
                        //resultado[3] = selecao[2];
                        resultado[4] = "OK";
                        break;
                    }catch (NullPointerException nulo){
                        String mensagemExc = nulo.getMessage();
                        resultado[0] = "botaoPesq";
                        resultado[1] = mensagemExc;
                        //resultado[2] = selecao[1];
                        //resultado[3] = selecao[2];
                        resultado[4] = "Erro";
                        break;
                    }
                }
                case R.id.botaoListarItensPedido:{
                    try {
                        ControladorItemPedido controlador = new ControladorItemPedido();
                        String[] parametrosBuscaItensPedido = new String[3];
                        parametrosBuscaItensPedido[0]="";
                        parametrosBuscaItensPedido[1]="";
                        parametrosBuscaItensPedido[2]=parametros[3];
                        selecao= controlador.listarItemPedido(parametrosBuscaItensPedido, getApplicationContext());
                        resultado[0] = "botaoList";
                        resultado[1] = selecao[0];
                        //resultado[2] = selecao[1];
                        //resultado[3] = selecao[2];
                        resultado[4] = "OK";
                        break;
                    }catch (NullPointerException nulo){
                        String mensagemExc = nulo.getMessage();
                        resultado[0] = "botaoList";
                        resultado[1] = mensagemExc;
                        //resultado[2] = selecao[1];
                        //resultado[3] = selecao[2];
                        resultado[4] = "Erro";
                        break;
                    }
                }
                default:{
                    break;
                }
            }
            return resultado;

        }

        protected void onPostExecute(String[] resultado){
            switch(resultado[0]){
                case "botaoIns":{
                    Toast.makeText(getApplicationContext(), resultado[1],Toast.LENGTH_LONG).show();
                    break;
                }
                case "botaoAlt" :{
                    Toast.makeText(getApplicationContext(), resultado[1], Toast.LENGTH_LONG).show();
                    break;
                }
                case "botaoExc" :{
                    Toast.makeText(getApplicationContext(), resultado[1], Toast.LENGTH_LONG).show();
                    break;
                }
                case "botaoPesq" : {
                    boolean preenchimentoOk = preencherCamposPedido(resultado[1]);

                    if(resultado[4]=="OK"){
                        if(preenchimentoOk){
                            Toast.makeText(getApplicationContext(),"Seleção realizada.", Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(getApplicationContext(), resultado[1], Toast.LENGTH_LONG).show();
                        }
                    }else {
                        Toast.makeText(getApplicationContext(), resultado[1], Toast.LENGTH_LONG).show();
                    }
                }
                case "botaoList" :{
                    Intent it = new Intent(CadastroPedido.this, CadastroListaPedido.class);
                    it.putExtra("ITENSPEDIDO", resultado[1]);
                    startActivity(it);
                }
                default:{
                    break;
                }
            }
        }
    }
}
