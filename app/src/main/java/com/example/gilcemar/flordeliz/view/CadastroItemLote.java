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
import com.example.gilcemar.flordeliz.model.ItemLote;

public class CadastroItemLote extends AppCompatActivity implements View.OnClickListener{

    EditText numeroLote;
    EditText codCalProd;
    EditText quantProd;
    EditText quantDispon;
    EditText tamCalcado;
    EditText precoRevenda;


    private Button botaoPesquisar;
    private Button botaoInserir;
    private Button botaoAlterar;
    private Button botaoExcluir;
    private Button botaoVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_item_lote);

        numeroLote = findViewById(R.id.edtNumeroLote3);
        codCalProd = findViewById(R.id.edtCodCalcadoProd3);
        quantProd = findViewById(R.id.edtQuantProd3);
        quantDispon = findViewById(R.id.edtQuantDispon3);
        tamCalcado = findViewById(R.id.edtTamCalcado3);
        precoRevenda = findViewById(R.id.edtPrecoRevenda3);

        //this.botaoPesquisar = findViewById(R.id.botaoPesqLote);
        this.botaoInserir = findViewById(R.id.botaoInsItemLote);
        this.botaoAlterar = findViewById(R.id.botaoAltItemLote3);
        this.botaoExcluir = findViewById(R.id.botaoExcItemLote);
        this.botaoVoltar = findViewById(R.id.botaoVoltarItemLote);
        //this.botaoPesquisar.setOnClickListener(this);
        this.botaoInserir.setOnClickListener(this);
        this.botaoAlterar.setOnClickListener(this);
        this.botaoExcluir.setOnClickListener(this);
        this.botaoVoltar.setOnClickListener(this);


        Intent in = getIntent();
        Bundle bundle = in.getExtras();
        ItemLote teste = null;
        if (bundle!=null){
            teste = (ItemLote) bundle.get("ITEMLOTE");
            preencherItemLote(teste);
        }
    }

    public boolean preencherItemLote(ItemLote item){
        boolean preenchimentoCorreto = false;
        if(item != null){
            codCalProd.setText(item.getCodCalPro());
            numeroLote.setText(item.getIdLote());
            tamCalcado.setText(item.getTamCal());
            quantProd.setText(item.getQuanCalProd());
            quantDispon.setText(item.getQuantCalDispon());
            precoRevenda.setText(item.getPrecoRevenda());
            preenchimentoCorreto = true;
        }
        return preenchimentoCorreto;
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

        /*Dados do item do lote*/
        parametros[0] = codCalProd.getText().toString();
        parametros[1]= numeroLote.getText().toString();
        parametros[2]= numeroLote.getText().toString();
        parametros[3]= tamCalcado.getText().toString();
        parametros[4]= quantProd.getText().toString();
        parametros[5]= quantDispon.getText().toString();
        parametros[6]= precoRevenda.getText().toString();
        /*Dados do item do lote*/

        Object[] param = new Object[2];
        param[0] = parametros;
        param[1] = v;
        return param;
    }

    @Override
    public void onClick(View v) {
        final int id = v.getId();
        switch (v.getId()){
            case R.id.botaoVoltarItemLote:{
                //Intent it = new Intent(Principal.this, CadastroCliente.class);
                finish();
                break;
            }
            default:{
                new TarefaAssincrona().execute(getCampos(v));//nesse trecho é passado
                // como parâmetros os campos.
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
            String[] resultado = new String[5];
            switch(acao.getId()){
                case R.id.botaoInsItemLote:{

                    String[] qualErro = new String[3];
                    try {
                        Controlador controlador = new ControladorItemLote();
                        qualErro = controlador.inserir(parametros, getApplicationContext());
                        resultado[0]= "botaoIns";
                        resultado[1]= qualErro[0];

                    }catch (NullPointerException nulo){
                        //qualErro = nulo.getMessage();
                        resultado[0]= "botaoIns";
                        resultado[1]= qualErro[0];
                    }
                    break;
                }
                case R.id.botaoAltItemLote3:{

                    String selecao[];
                    try {
                        Controlador controlador = new ControladorItemLote();
                        selecao= controlador.alterar(parametros, getApplicationContext());
                        resultado[0] = "botaoAlt";
                        resultado[1] = selecao[0];

                    }catch (NullPointerException nulo){
                        String mensagemExc = nulo.getMessage();
                        resultado[0] = "botaoAlt";
                        resultado[1] = mensagemExc;

                        resultado[4] = "Erro";
                    }
                    break;
                }
                case R.id.botaoExcItemLote:{

                    String[] selecao = new String[3];
                    try {

                        Controlador controlador = new ControladorItemLote();
                        selecao= controlador.excluir(parametros, getApplicationContext());
                        resultado[0] = "botaoExc";
                        resultado[1] = selecao[0];

                    }catch (NullPointerException nulo){
                        String mensagemExc = nulo.getMessage();
                        resultado[0] = "botaoExc";
                        resultado[1] = mensagemExc;
                    }
                    break;
                }

            }
            return resultado;

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
                default:{
                    Toast.makeText(getApplicationContext(),"Nenhuma opção válida escolhida", Toast.LENGTH_LONG).show();
                    break;
                }
            }
        }
    }
}
