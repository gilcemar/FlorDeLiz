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
import com.example.gilcemar.flordeliz.control.ControladorItemLote;
import com.example.gilcemar.flordeliz.control.ControladorLote;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CadastroLote extends AppCompatActivity implements View.OnClickListener{

    private String codProdutoAnt;
    private String numeroLoteAnt;
    private  String codProdutoProduzido;
    private EditText campoCodProduto;
    private EditText campoDataProducao;
    private EditText campoNumeroLote;
    private EditText campoTamCalc;
    private EditText campoQuantProd;
    private EditText campoQuantDispon;
    private EditText campoPrecoRev;

    private Button botaoPesquisar;
    private Button botaoPesquisaItemLote;
    private Button botaoInserir;
    private Button botaoAlterar;
    private Button botaoExcluir;
    private Button botaoVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_lote);

        codProdutoAnt="";
        numeroLoteAnt = "";
        codProdutoProduzido = "";
        campoCodProduto= findViewById(R.id.edtCodCalcado);
        campoDataProducao= findViewById(R.id.edtDataProdLote);
        campoNumeroLote = findViewById(R.id.edtNumeroLote);
        campoTamCalc = findViewById(R.id.edtTamCalcado);
        campoQuantProd = findViewById(R.id.edtQuatProd);
        campoQuantDispon = findViewById(R.id.edtQuantDispon);
        campoPrecoRev = findViewById(R.id.edtPrecoRevenda);

        this.botaoPesquisar = findViewById(R.id.botaoPesqLote);
        this.botaoPesquisaItemLote = findViewById(R.id.botaoPesqItemLote);
        this.botaoInserir = findViewById(R.id.botaoInsLote);
        this.botaoAlterar = findViewById(R.id.botaoAltLote);
        this.botaoExcluir = findViewById(R.id.botaoExcLote);
        this.botaoVoltar = findViewById(R.id.botaoVoltarLote);
        this.botaoPesquisar.setOnClickListener(this);
        this.botaoPesquisaItemLote.setOnClickListener(this);
        this.botaoInserir.setOnClickListener(this);
        this.botaoAlterar.setOnClickListener(this);
        this.botaoExcluir.setOnClickListener(this);
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
        String[] parametros= new String[14];
        //começar a colocar do índice para que lá na doBackground seja colocada a ação correta

        /*Dados do Lote*/
        parametros[0]= campoCodProduto.getText().toString();
        parametros[1]= codProdutoAnt;
        parametros[2]= campoDataProducao.getText().toString();
        parametros[3]= campoNumeroLote.getText().toString();
        /*Dados do Lote*/

        /*Dados do item do lote*/
        parametros[4] = codProdutoProduzido;
        parametros[5]= numeroLoteAnt;
        parametros[6]= campoTamCalc.getText().toString();
        parametros[7]= campoQuantProd.getText().toString();
        parametros[8]= campoQuantDispon.getText().toString();
        parametros[9]= campoPrecoRev.getText().toString();
        /*Dados do item do lote*/

        Object[] param = new Object[2];
        param[0] = parametros;
        param[1] = v;
        return param;
    }

    public Object[] getCamposItemLote(View v){
        String[] parametros= new String[14];
        //começar a colocar do índice para que lá na doBackground seja colocada a ação correta

        /*Dados do item lote*/
        parametros[0]= campoCodProduto.getText().toString();
        parametros[1]= campoNumeroLote.getText().toString();
        parametros[2]= "";
        parametros[3]= "";
        parametros[4]= "";
        parametros[5]= "";
        parametros[6]= "";
        /*Dados do item lote*/

        Object[] param = new Object[2];
        param[0] = parametros;
        param[1] = v;
        return param;
    }

    public boolean preencherCampoItemLote(String resultado){

        JSONArray jsonArray;
        try {
            jsonArray= new JSONArray(resultado);

            JSONObject row = jsonArray.getJSONObject(0);

            //aqui tem que colocar sempre os nomes das colunas que vem no resultado do JSON, ou seja, os nomes das
            // colunas do banco de dados.

            /*Dados do item do lote*/
            codProdutoProduzido = row.getString("codCalcProd");
            campoTamCalc.setText(row.getString("tamCalc"));
            campoQuantProd.setText(row.getString("quantCalcProd"));
            campoQuantDispon.setText(row.getString("quantCalcDispon"));
            campoPrecoRev.setText(row.getString("precoRevenda"));
            /*Dados do item do lote*/

            return true;
        } catch (JSONException e) {
            e.printStackTrace();

            return false;
        }
    }

    public boolean preencherCampoLote(String resultado){

        JSONArray jsonArray;
        try {
            jsonArray= new JSONArray(resultado);

            JSONObject row = jsonArray.getJSONObject(0);
            //aqui tem que colocar sempre os nomes das colunas que vem no resultado do JSON, ou seja, os nomes das
            // colunas do banco de dados.

            /*Dados do lote*/
            campoCodProduto.setText(row.getString("codProd"));
            codProdutoAnt =row.getString("codProd");
            campoDataProducao.setText(row.getString("dataProd"));
            campoNumeroLote.setText(row.getString("idLote"));
            numeroLoteAnt = row.getString("idLote");
            /*Dados do lote*/

            return true;
        } catch (JSONException e) {
            e.printStackTrace();

            return false;
        }

    }

    @Override
    public void onClick(View v) {
        final int id = v.getId();
        switch (v.getId()){
            case R.id.botaoVoltarLote:{
                //Intent it = new Intent(Principal.this, CadastroCliente.class);
                finish();
                break;
            }
            case R.id.botaoPesqItemLote :{
                new CadastroLote.TarefaAssincrona().execute(getCamposItemLote(v));
                break;
            }
            case R.id.botaoPesqLote:{
                new CadastroLote.TarefaAssincrona().execute(getCampos(v));//nesse trecho é passado
                // como parâmetros os campos.
                break;
            }
            case R.id.botaoInsLote:{
                new CadastroLote.TarefaAssincrona().execute(getCampos(v));//nesse trecho é passado como parâmetros os campos.
                break;
            }
            case R.id.botaoAltLote:{
                new CadastroLote.TarefaAssincrona().execute(getCampos(v));//nesse trecho é passado como parâmetros os campos.
                break;
            }
            case R.id.botaoExcLote:{
                new CadastroLote.TarefaAssincrona().execute(getCampos(v));//nesse trecho é passado como parâmetros os campos.
                break;
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
            switch(acao.getId()){
                case R.id.botaoInsLote:{
                    String[] resultado = new String[5];
                    String[] qualErro = new String[3];
                    try {
                        Controlador controlador = new ControladorLote();
                        qualErro = controlador.inserir(parametros, getApplicationContext());
                        resultado[0]= "botaoIns";
                        resultado[1]= qualErro[0];

                        return resultado;
                        //break;
                    }catch (NullPointerException nulo){
                        //qualErro = nulo.getMessage();
                        resultado[0]= "botaoIns";
                        resultado[1]= qualErro[0];

                        return resultado;
                    }


                }
                case R.id.botaoAltLote:{
                    String[] resultado = new String[5];
                    String selecao[];
                    try {
                        Controlador controlador = new ControladorLote();
                        selecao= controlador.alterar(parametros, getApplicationContext());
                        resultado[0] = "botaoAlt";
                        resultado[1] = selecao[0];

                        return resultado;
                        //break;
                    }catch (NullPointerException nulo){
                        String mensagemExc = nulo.getMessage();
                        resultado[0] = "botaoAlt";
                        resultado[1] = mensagemExc;

                        resultado[4] = "Erro";
                        return resultado;
                    }
                    //break;
                }
                case R.id.botaoExcLote:{
                    String[] resultado = new String[5];
                    String[] selecao = new String[3];
                    try {
                        Controlador controlador = new ControladorLote();
                        selecao= controlador.excluir(parametros, getApplicationContext());
                        resultado[0] = "botaoExc";
                        resultado[1] = selecao[0];

                        return resultado;
                        //break;
                    }catch (NullPointerException nulo){
                        String mensagemExc = nulo.getMessage();
                        resultado[0] = "botaoExc";
                        resultado[1] = mensagemExc;

                        return resultado;
                    }
                    //break;
                }
                case R.id.botaoPesqLote:{
                    String toast = "Tudo certo";
                    String[] resultado = new String[5];
                    String[] selecao;
                    try {
                        Controlador controlador = new ControladorLote();
                        selecao= controlador.pesquisar(parametros, getApplicationContext());
                        resultado[0] = "botaoPesq";
                        resultado[1] = selecao[0];
                        resultado[2] = selecao[1];
                        resultado[4] = "OK";
                        return resultado;
                        //break;
                    }catch (NullPointerException nulo){
                        String mensagemExc = nulo.getMessage();
                        resultado[0] = "botaoPesq";
                        resultado[1] = mensagemExc;
                        //resultado[2] = selecao[1];
                        //resultado[3] = selecao[2];
                        resultado[4] = "Erro";
                        return resultado;
                    }
                }
                case R.id.botaoPesqItemLote:{
                    String toast = "Tudo certo";
                    String[] resultado = new String[5];
                    String[] selecao;
                    try {
                        Controlador controlador = new ControladorItemLote();
                        selecao= controlador.pesquisar(parametros, getApplicationContext());
                        resultado[0] = "botaoPesqItemLote";
                        resultado[1] = selecao[0];
                        resultado[2] = selecao[1];
                        resultado[4] = "OK";
                        return resultado;
                        //break;
                    }catch (NullPointerException nulo){
                        String mensagemExc = nulo.getMessage();
                        resultado[0] = "botaoPesqItemLote";
                        resultado[1] = mensagemExc;
                        //resultado[2] = selecao[1];
                        //resultado[3] = selecao[2];
                        resultado[4] = "Erro";
                        return resultado;
                    }
                }
                default:{
                    return null;
                }
            }
            //return "Erro";

        }

        protected void onPostExecute(String[] resultado){
            //String acao = resultado[];
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
                case "botaoPesqItemLote" :{
                    if (resultado.length>3){
                        Intent it = new Intent(CadastroLote.this, CadastroListaItemLote.class);
                        it.putExtra("numeroLote", resultado[1]);
                        startActivity(it);
                    }else {
                        Toast.makeText(getApplicationContext(),"Nenhum item existe para esse lote.", Toast.LENGTH_LONG).show();
                    }
                    break;
                }
                case "botaoPesq" : {
                    boolean preenchimentoOk = preencherCampoLote(resultado[1]);
                    //boolean preencherEnd = preencherCampoItemLote(resultado[2]);

                    if(resultado[4]=="OK"){
                        if(preenchimentoOk){
                            Toast.makeText(getApplicationContext(),"Seleção realizada.", Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(getApplicationContext(), resultado[1], Toast.LENGTH_LONG).show();
                        }
                    }else {
                        Toast.makeText(getApplicationContext(), resultado[1], Toast.LENGTH_LONG).show();
                    }
                    break;
                }
                default:{
                    break;
                }
            }
        }
    }
}
