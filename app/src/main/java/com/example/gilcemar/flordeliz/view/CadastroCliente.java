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
    private String idEnd;
    private String telAnt;
    private EditText campoCnpj;
    private EditText campoNomeLoja;
    private EditText campoTelefone;
    private EditText campoCpf;
    private EditText campoNomeDono;
    private EditText campoRua;
    private EditText campoBairro;
    private EditText campoCidade;
    private EditText campoUf;
    private EditText campoPais;
    private EditText campoNumero;



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
        idEnd = "";
        telAnt = "";
        campoCnpj = findViewById(R.id.edtCnpjCliente);
        campoNomeLoja = findViewById(R.id.edtNomeLoja);
        campoTelefone = findViewById(R.id.edtTelefone);
        campoCpf = findViewById(R.id.edtCpfDono);
        campoNomeDono = findViewById(R.id.edtNomeDono);

        campoRua = findViewById(R.id.edtRua);
        campoBairro = findViewById(R.id.edtBairro);
        campoCidade = findViewById(R.id.edtCidade);
        campoUf = findViewById(R.id.edtUf);
        campoPais = findViewById(R.id.edtPais);
        campoNumero = findViewById(R.id.edtNumero);

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
        String[] parametros= new String[14];
        //começar a colocar do índice para que lá na doBackground seja colocada a ação correta
        parametros[0]= cnpjAnt;

        /*Dados do cliente*/
        parametros[1]= campoCnpj.getText().toString();
        parametros[2]= campoCpf.getText().toString();
        parametros[3]= campoNomeDono.getText().toString();
        parametros[4]= campoNomeLoja.getText().toString();
        /*Dados do cliente*/

        /*Dados do endereço*/
        parametros[5]= campoRua.getText().toString();
        parametros[6]= campoBairro.getText().toString();
        parametros[7]= campoCidade.getText().toString();
        parametros[8]= campoUf.getText().toString();
        parametros[9]= campoPais.getText().toString();
        parametros[10]= campoNumero.getText().toString();
        parametros[11]= idEnd;
        /*Dados do endereço*/

        /*Dados do Telefone*/
        parametros[12] = campoTelefone.getText().toString();
        parametros[13]= telAnt;
        /*Dados do Telefone*/


        Context contexto = getApplicationContext();
        //ControladorCalcado controlCalcado = new ControladorCalcado();
        //controlCalcado.inserir(parametros,contexto);
        Object[] param = new Object[2];
        param[0] = parametros;
        param[1] = v;
        return param;
    }

    public boolean preencherCampoEnd (String resultado){
        String teste = "numa";
        JSONArray jsonArray;
        try {
            jsonArray= new JSONArray(resultado);
            //String outra = jsonArray.getString(0);

            JSONObject row = jsonArray.getJSONObject(0);
            //aqui tem que colocar sempre os nomes das colunas que vem no resultado do JSON, ou seja, os nomes das
            // colunas do banco de dados.

            /*Dados do endereço*/
            campoRua.setText(row.getString("rua"));
            campoBairro.setText(row.getString("bairro"));
            campoCidade.setText(row.getString("cidade"));
            campoUf.setText(row.getString("uf"));
            campoPais.setText(row.getString("pais"));
            campoNumero.setText(row.getString("numero"));
            idEnd = row.getString("idEnd");
            /*Dados do endereço*/

            /*Dados do telefone*/
            /*campoNumero;*/


            return true;
        } catch (JSONException e) {
            e.printStackTrace();
            teste = e.getMessage();
            //teste
            return false;
        }
    }

    public boolean preencherCampoTel (String resultado){
        String teste = "numa";
        JSONArray jsonArray;
        try {
            jsonArray= new JSONArray(resultado);
            //String outra = jsonArray.getString(0);

            JSONObject row = jsonArray.getJSONObject(0);
            //aqui tem que colocar sempre os nomes das colunas que vem no resultado do JSON, ou seja, os nomes das
            // colunas do banco de dados.

            /*Dados do telefone*/
            telAnt = row.getString("numero");
            campoTelefone.setText(row.getString("numero"));

            return true;
        } catch (JSONException e) {
            e.printStackTrace();
            teste = e.getMessage();
            //teste
            return false;
        }
    }

    public boolean preencherCampoCliente (String resultado){
        String teste = "numa";
        JSONArray jsonArray;
        try {
            jsonArray= new JSONArray(resultado);
            //String outra = jsonArray.getString(0);

            JSONObject row = jsonArray.getJSONObject(0);
            //aqui tem que colocar sempre os nomes das colunas que vem no resultado do JSON, ou seja, os nomes das
            // colunas do banco de dados.

            /*Dados do cliente*/
            cnpjAnt =row.getString("cnpjLojaCliente");
            campoCnpj.setText(row.getString("cnpjLojaCliente"));
            campoCpf.setText(row.getString("cpfCliente"));
            campoNomeDono.setText(row.getString("nomeCliente"));
            campoNomeLoja.setText(row.getString("nomeLojaCliente"));
            /*Dados do cliente*/

            /*Dados do endereço*/
            /*campoRua;
            campoBairro;
            campoCidade;
            campoUf;
            campoPais;
            campoNumero;
            idEnd;
            /*Dados do endereço*/

            /*Dados do telefone*/
            /*campoNumero;*/


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
                    String[] resultado = new String[5];
                    String[] qualErro = new String[1];
                    try {
                        Controlador controlador = new ControladorCliente();
                        qualErro = controlador.inserir(parametros, getApplicationContext());
                        resultado[0]= "botaoIns";
                        resultado[1]= qualErro[0];
                        resultado[2]= qualErro[1];
                        resultado[3]= qualErro[2];
                        resultado[4] = "OK";
                        return resultado;
                        //break;
                    }catch (NullPointerException nulo){
                        //qualErro = nulo.getMessage();
                        resultado[0]= "botaoIns";
                        resultado[1]= qualErro[0];
                        resultado[2]= qualErro[1];
                        resultado[3]= qualErro[2];
                        resultado[4] = "OK";
                        return resultado;
                    }


                }
                case R.id.botaoAltCliente:{
                    String[] resultado = new String[5];
                    String selecao[];
                    try {
                        Controlador controlador = new ControladorCliente();
                        selecao= controlador.alterar(parametros, getApplicationContext());
                        resultado[0] = "botaoAlt";
                        resultado[1] = selecao[0];
                        resultado[2] = selecao[1];
                        resultado[3] = selecao[2];
                        resultado[4] = "OK";
                        return resultado;
                        //break;
                    }catch (NullPointerException nulo){
                        String mensagemExc = nulo.getMessage();
                        resultado[0] = "botaoAlt";
                        resultado[1] = mensagemExc;
                        //resultado[2] = selecao[1];
                        //resultado[3] = selecao[2];
                        resultado[4] = "Erro";
                        return resultado;
                    }
                    //break;
                }
                case R.id.botaoExcCliente:{
                    String[] resultado = new String[5];
                    String[] selecao;
                    try {
                        Controlador controlador = new ControladorCliente();
                        selecao= controlador.excluir(parametros, getApplicationContext());
                        resultado[0] = "botaoExc";
                        resultado[1] = selecao[0];
                        resultado[2] = selecao[1];
                        resultado[3] = selecao[2];
                        resultado[4] = "OK";
                        return resultado;
                        //break;
                    }catch (NullPointerException nulo){
                        String mensagemExc = nulo.getMessage();
                        resultado[0] = "botaoExc";
                        resultado[1] = mensagemExc;
                        //resultado[2] = selecao[1];
                        //resultado[3] = selecao[2];
                        resultado[4] = "Erro";
                        return resultado;
                    }
                    //break;
                }
                case R.id.botaoPesqCliente:{
                    String toast = "Tudo certo";
                    String[] resultado = new String[5];
                    String[] selecao;
                    try {
                        Controlador controlador = new ControladorCliente();
                        selecao= controlador.pesquisar(parametros, getApplicationContext());
                        resultado[0] = "botaoPesq";
                        resultado[1] = selecao[0];
                        resultado[2] = selecao[1];
                        resultado[3] = selecao[2];
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
                    Toast.makeText(getApplicationContext(), resultado[1] + '\n' + resultado[2] + '\n' + resultado[3],Toast.LENGTH_LONG).show();
                    break;
                }
                case "botaoAlt" :{
                    Toast.makeText(getApplicationContext(), resultado[1] + '\n' + resultado[2] + '\n' + resultado[3], Toast.LENGTH_LONG).show();
                    break;
                }
                case "botaoExc" :{
                    Toast.makeText(getApplicationContext(), resultado[1] + '\n' + resultado[2] + '\n' + resultado[3], Toast.LENGTH_LONG).show();
                    break;
                }
                case "botaoPesq" : {
                    boolean preenchimentoOk = preencherCampoCliente(resultado[1]);
                    boolean preencherEnd = preencherCampoEnd(resultado[2]);
                    boolean preencherTel = preencherCampoTel(resultado[3]);
                    if(resultado[4]=="OK"){
                        if(preenchimentoOk){
                            Toast.makeText(getApplicationContext(),"Seleção realizada.", Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(getApplicationContext(), resultado[1] + '\n' + resultado[2] + '\n' + resultado[3], Toast.LENGTH_LONG).show();
                        }
                    }else {
                        Toast.makeText(getApplicationContext(), resultado[1] + '\n' + resultado[2] + '\n' + resultado[3], Toast.LENGTH_LONG).show();
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
