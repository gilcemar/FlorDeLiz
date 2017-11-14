package com.example.gilcemar.flordeliz.view;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gilcemar.flordeliz.R;
import com.example.gilcemar.flordeliz.control.ControladorCalcado;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class CadastroCalcado extends AppCompatActivity implements View.OnClickListener{
    private static final String INSERIRCALCADO = "inserirCalc";
    private static final String ALTERARCALCADO = "alterarCalc";
    private static final String EXCLUIRCALCADO = "excluirCalc";
    private static final String PESQUISARCALCADO = "pesquisarCalc";

    public String getIdCalcado() {
        return idCalcado;
    }

    public void setIdCalcado(String idCalcado) {
        this.idCalcado = idCalcado;
    }

    private String idCalcado;
    private EditText campoNomeCal;
    private EditText campoMenorTam;
    private EditText campoMaiorTam;
    private EditText campoCor;
    private EditText campoColecao;
    private EditText campoPrecoCusto;


    private Button botaoPesquisar;
    private Button botaoInserir;
    private Button botaoAlterar;
    private Button botaoExcluir;
    private Button botaoVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_calcado);
        idCalcado ="";
        campoNomeCal = findViewById(R.id.edtNomeCal);
        campoMenorTam = findViewById(R.id.edtMenorTamCal);
        campoMaiorTam = findViewById(R.id.edtMaiorTamCal);
        campoCor = findViewById(R.id.edtCorCal);
        campoColecao = findViewById(R.id.edtColecaoCal);
        campoPrecoCusto = findViewById(R.id.edtPrecoCustCal);
        this.botaoPesquisar = findViewById(R.id.botaoPesqCalc);
        this.botaoInserir = findViewById(R.id.botaoInsCalc);
        this.botaoAlterar = findViewById(R.id.botaoAlterarCal);
        this.botaoExcluir = findViewById(R.id.botaoExcluirCal);
        this.botaoVoltar = findViewById(R.id.botaoVoltar);
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
        String[] parametros= new String[7];
        parametros[0]= campoNomeCal.getText().toString();
        parametros[1]= campoMenorTam.getText().toString();
        parametros[2]= (campoMaiorTam.getText().toString());
        parametros[3]= (campoCor.getText().toString());
        parametros[4]= (campoColecao.getText().toString());
        parametros[5]= (campoPrecoCusto.getText().toString());
        parametros[6]=getIdCalcado();
        Context contexto = getApplicationContext();
        //ControladorCalcado controlCalcado = new ControladorCalcado();
        //controlCalcado.inserirCalcado(parametros,contexto);
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
            campoNomeCal.setText(row.getString("nomeCalc"));
            campoMenorTam.setText(row.getString("menorTam"));
            campoMaiorTam.setText(row.getString("maiorTam"));
            campoCor.setText(row.getString("cor"));
            campoColecao.setText(row.getString("nomeColecao"));
            campoPrecoCusto.setText(row.getString("precoCusto"));
            idCalcado =row.getString("idCalcado");

            return true;
        } catch (JSONException e) {
            e.printStackTrace();
            teste = e.getMessage();

            return false;
        }

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
            case R.id.botaoPesqCalc:{
                new TarefaAssincrona().execute(getCampos(v));//nesse trecho é passado como parâmetros os campos.
                break;
            }
            case R.id.botaoInsCalc:{
                new TarefaAssincrona().execute(getCampos(v));//nesse trecho é passado como parâmetros os campos.
                break;
            }
            case R.id.botaoAlterarCal:{
                new TarefaAssincrona().execute(getCampos(v));//nesse trecho é passado como parâmetros os campos.
                break;
            }
            case R.id.botaoExcluirCal:{
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
                case R.id.botaoInsCalc:{
                    String[] resultado = new String[3];
                    String qualErro="Tudo ok";
                    try {
                        ControladorCalcado controladorCalcado = new ControladorCalcado();
                        qualErro = controladorCalcado.inserirCalcado(parametros, getApplicationContext());
                        resultado[0]= "botaoInsCalc";
                        resultado[1]= qualErro;
                        resultado[2] = "OK";
                        return resultado;
                        //break;
                    }catch (NullPointerException nulo){
                        qualErro = nulo.getMessage();
                        resultado[0]= "botaoInsCalc";
                        resultado[1]= qualErro;
                        resultado[2] = "Erro";
                        return resultado;
                    }


                }
                case R.id.botaoAlterarCal:{
                    String[] resultado = new String[3];
                    String selecao;
                    try {
                        ControladorCalcado controladorCalcado = new ControladorCalcado();
                        selecao= controladorCalcado.alterarCalcado(parametros, getApplicationContext());
                        resultado[0] = "botaoAlterarCal";
                        resultado[1] = selecao;
                        resultado[2] = "OK";
                        return resultado;
                        //break;
                    }catch (NullPointerException nulo){
                        String mensagemExc = nulo.getMessage();
                        resultado[0] = "botaoPesqCalc";
                        resultado[1] = mensagemExc;
                        resultado[2] = "Erro";
                        return resultado;
                    }
                    //break;
                }
                case R.id.botaoExcluirCal:{
                    String[] resultado = new String[3];
                    String selecao;
                    try {
                        ControladorCalcado controladorCalcado = new ControladorCalcado();
                        selecao= controladorCalcado.excluirCalcado(parametros, getApplicationContext());
                        resultado[0] = "botaoExcluirCal";
                        resultado[1] = selecao;
                        resultado[2] = "OK";
                        return resultado;
                        //break;
                    }catch (NullPointerException nulo){
                        String mensagemExc = nulo.getMessage();
                        resultado[0] = "botaoPesqCalc";
                        resultado[1] = mensagemExc;
                        resultado[2] = "Erro";
                        return resultado;
                    }
                    //break;
                }
                case R.id.botaoPesqCalc:{
                    String toast = "Tudo certo";
                    String[] resultado = new String[3];
                    String selecao;
                    try {
                        ControladorCalcado controladorCalcado = new ControladorCalcado();
                        selecao= controladorCalcado.pesquisarCalcado(parametros, getApplicationContext());
                        resultado[0] = "botaoPesqCalc";
                        resultado[1] = selecao;
                        resultado[2] = "OK";
                        return resultado;
                        //break;
                    }catch (NullPointerException nulo){
                        String mensagemExc = nulo.getMessage();
                        resultado[0] = "botaoPesqCalc";
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
                case "botaoInsCalc":{
                    Toast.makeText(getApplicationContext(), resultado[1], Toast.LENGTH_LONG).show();
                    break;
                }
                case "botaoAlterarCal" :{

                    break;
                }
                case "botaoExcluirCal" :{

                    break;
                }
                case "botaoPesqCalc" : {
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
