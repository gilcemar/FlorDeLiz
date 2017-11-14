package com.example.gilcemar.flordeliz.view;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gilcemar.flordeliz.R;
import com.example.gilcemar.flordeliz.control.Controlador;
import com.example.gilcemar.flordeliz.control.ControladorCliente;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CadastroCliente extends AppCompatActivity implements View.OnClickListener{
    private static final String INSERIRCALCADO = "inserirCalc";
    private static final String ALTERARCALCADO = "alterarCalc";
    private static final String EXCLUIRCALCADO = "excluirCalc";
    private static final String PESQUISARCALCADO = "pesquisarCalc";

    public String getCnpjAnt() {
        return cnpjAnt;
    }

    public void setCnpjAnt(String cnpjAnt) {
        this.cnpjAnt = cnpjAnt;
    }

    private String cnpjAnt;
    private EditText campoCnpj;
    private EditText campoNomeLoja;
    private EditText campoCpf;
    private EditText campoNomeDono;


    private Button botaoPesquisar;
    private Button botaoInserir;
    private Button botaoAlterar;
    private Button botaoExcluir;
    private Button botaoVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_cliente);
        cnpjAnt ="";
        campoCnpj = findViewById(R.id.edtCnpjCliente);
        campoNomeLoja = findViewById(R.id.edtNomeLoja);
        campoCpf = findViewById(R.id.edtCpfDono);
        campoNomeDono = findViewById(R.id.edtNomeDono);
        this.botaoPesquisar = findViewById(R.id.botaoPesqCliente);
        this.botaoInserir = findViewById(R.id.botaoInsCliente);
        this.botaoAlterar = findViewById(R.id.botaoAltCliente);
        this.botaoExcluir = findViewById(R.id.botaoExcCliente);
        this.botaoVoltar = findViewById(R.id.botaoVoltarCliente);
        this.botaoPesquisar.setOnClickListener(this);
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
        String[] parametros= new String[5];
        //começar a colocar do índice para que lá na doBackground seja colocada a ação correta
        parametros[0]= cnpjAnt;
        parametros[1]= campoCnpj.getText().toString();
        parametros[2]= (campoCpf.getText().toString());
        parametros[3]= (campoNomeDono.getText().toString());
        parametros[4]= campoNomeLoja.getText().toString();

        Context contexto = getApplicationContext();
        //ControladorCalcado controlCalcado = new ControladorCalcado();
        //controlCalcado.inserir(parametros,contexto);
        Object[] param = new Object[2];
        param[0] = parametros;
        param[1] = v;
        return param;
    }

    public boolean preencherCampo (String resultado){
        String teste = "numa";
        JSONArray jsonArray;
        try {
            jsonArray= new JSONArray(resultado);
            //String outra = jsonArray.getString(0);

            JSONObject row = jsonArray.getJSONObject(0);
            //aqui tem que colocar sempre os nomes das colunas que vem no resultado do JSON, ou seja, os nomes das
            // colunas do banco de dados.
            cnpjAnt =row.getString("cnpjLojaCliente");
            campoCnpj.setText(row.getString("cnpjLojaCliente"));
            campoCpf.setText(row.getString("cpfCliente"));
            campoNomeDono.setText(row.getString("nomeCliente"));
            campoNomeLoja.setText(row.getString("nomeLojaCliente"));


            return true;
        } catch (JSONException e) {
            e.printStackTrace();
            teste = e.getMessage();
            //teste
            return false;
        }

    }

    @Override
    public void onClick(View v) {
        final int id = v.getId();
        switch (v.getId()){
            case R.id.botaoVoltarCliente:{
                //Intent it = new Intent(Principal.this, CadastroCliente.class);
                finish();
                break;
            }
            case R.id.botaoPesqCliente:{
                new TarefaAssincrona().execute(getCampos(v));//nesse trecho é passado como parâmetros os campos.
                break;
            }
            case R.id.botaoInsCliente:{
                new TarefaAssincrona().execute(getCampos(v));//nesse trecho é passado como parâmetros os campos.
                break;
            }
            case R.id.botaoAltCliente:{
                new TarefaAssincrona().execute(getCampos(v));//nesse trecho é passado como parâmetros os campos.
                break;
            }
            case R.id.botaoExcCliente:{
                new TarefaAssincrona().execute(getCampos(v));//nesse trecho é passado como parâmetros os campos.
                break;
            }

        }

    }

    //https://arthurlehdermann.wordpress.com/2013/03/11/asynctask-executando-tarefas-em-segundo-plano/
    //no link acima ensina como é a passagem de parâmetros na classe abaixo.
    private class TarefaAssincrona extends AsyncTask<Object[],Void, String[]>{

        @Override
        protected String[] doInBackground(Object[]... objects) {
            String[] parametros = (String[])objects[0][0];
            View acao = (View) objects[0][1];
            String parametro = "";
            switch(acao.getId()){
                case R.id.botaoInsCliente:{
                    String[] resultado = new String[3];
                    String qualErro="Tudo ok";
                    try {
                        Controlador controladorCliente = new ControladorCliente();
                        qualErro = controladorCliente.inserir(parametros, getApplicationContext());
                        resultado[0]= "botaoIns";
                        resultado[1]= qualErro;
                        resultado[2] = "OK";
                        return resultado;
                        //break;
                    }catch (NullPointerException nulo){
                        qualErro = nulo.getMessage();
                        resultado[0]= "botaoIns";
                        resultado[1]= qualErro;
                        resultado[2] = "Erro";
                        return resultado;
                    }


                }
                case R.id.botaoAltCliente:{
                    String[] resultado = new String[3];
                    String selecao;
                    try {
                        Controlador controladorCliente = new ControladorCliente();
                        selecao= controladorCliente.alterar(parametros, getApplicationContext());
                        resultado[0] = "botaoAlt";
                        resultado[1] = selecao;
                        resultado[2] = "OK";
                        return resultado;
                        //break;
                    }catch (NullPointerException nulo){
                        String mensagemExc = nulo.getMessage();
                        resultado[0] = "botaoAlt";
                        resultado[1] = mensagemExc;
                        resultado[2] = "Erro";
                        return resultado;
                    }
                    //break;
                }
                case R.id.botaoExcCliente:{
                    String[] resultado = new String[3];
                    String selecao;
                    try {
                        Controlador controladorCliente = new ControladorCliente();
                        selecao= controladorCliente.excluir(parametros, getApplicationContext());
                        resultado[0] = "botaoExc";
                        resultado[1] = selecao;
                        resultado[2] = "OK";
                        return resultado;
                        //break;
                    }catch (NullPointerException nulo){
                        String mensagemExc = nulo.getMessage();
                        resultado[0] = "botaoExc";
                        resultado[1] = mensagemExc;
                        resultado[2] = "Erro";
                        return resultado;
                    }
                    //break;
                }
                case R.id.botaoPesqCliente:{
                    String toast = "Tudo certo";
                    String[] resultado = new String[3];
                    String selecao;
                    try {
                        Controlador controladorCliente = new ControladorCliente();
                        selecao= controladorCliente.pesquisar(parametros, getApplicationContext());
                        resultado[0] = "botaoPesq";
                        resultado[1] = selecao;
                        resultado[2] = "OK";
                        return resultado;
                        //break;
                    }catch (NullPointerException nulo){
                        String mensagemExc = nulo.getMessage();
                        resultado[0] = "botaoPesq";
                        resultado[1] = mensagemExc;
                        resultado[2] = "Erro";
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
                    Toast.makeText(getApplicationContext(), resultado[1], Toast.LENGTH_LONG).show();
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
                    boolean preenchimentoOk = preencherCampo(resultado[1]);
                    if(resultado[2]=="OK"){
                        if(preenchimentoOk){
                            Toast.makeText(getApplicationContext(),"Calçado selecionado com sucesso.", Toast.LENGTH_LONG)
                                    .show();
                        }else{
                            Toast.makeText(getApplicationContext(),resultado[1], Toast.LENGTH_LONG).show();
                        }
                    }else {
                        Toast.makeText(getApplicationContext(),resultado[1], Toast.LENGTH_LONG).show();
                    }
                }
                default:{
                    break;
                }
            }

            //String enf = resultado;//depois retirar os espaços com trim.

        }
    }
}
