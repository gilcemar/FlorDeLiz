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
import com.example.gilcemar.flordeliz.model.ItemPedido;

public class CadastroItemPedido extends AppCompatActivity implements View.OnClickListener{
    private EditText quantItem;
    private EditText numeroPedido;
    private EditText codCalProd;

    private Button botaoInserir;
    private Button botaoAlterar;
    private Button botaoExcluir;
    private Button botaoVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_item_pedido);
        numeroPedido = findViewById(R.id.editTextNumPedido);
        codCalProd = findViewById(R.id.editTextCodCalcadoProd);
        quantItem = findViewById(R.id.editTextQuantItem);

        botaoInserir = findViewById(R.id.botaoInsItemPedido);
        botaoAlterar = findViewById(R.id.botaoAltItemPedido);
        botaoExcluir = findViewById(R.id.botaoExcItemPedido);
        botaoVoltar = findViewById(R.id.botaoVoltar);

        botaoInserir.setOnClickListener(this);
        botaoAlterar.setOnClickListener(this);
        botaoExcluir.setOnClickListener(this);
        botaoVoltar.setOnClickListener(this);

        Intent in = getIntent();
        Bundle bundle = in.getExtras();
        ItemPedido teste = null;
        if (bundle!=null){
            teste = (ItemPedido) bundle.get("ITEMPEDIDO");
            preencherItemPedido(teste);
        }
    }

    public boolean preencherItemPedido(ItemPedido item){
        boolean preenchimentoCorreto = false;
        if(item != null){
            codCalProd.setText(item.getCodCalProd());
            numeroPedido.setText(item.getIdPedido());
            quantItem.setText(item.getQuantItem());
            preenchimentoCorreto = true;
        }
        return preenchimentoCorreto;
    }

    public Object[] getCampos(View v){
        String[] parametros= new String[14];
        //começar a colocar do índice para que lá na doBackground seja colocada a ação correta

        /*Dados do item do pedido*/
        parametros[0] = quantItem.getText().toString();
        parametros[1]= codCalProd.getText().toString();
        parametros[2]= numeroPedido.getText().toString();
        /*Dados do item do pedido*/

        Object[] param = new Object[2];
        param[0] = parametros;
        param[1] = v;
        return param;
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
                case R.id.botaoInsItemPedido:{

                    String[] qualErro = new String[3];
                    try {
                        Controlador controlador = new ControladorItemPedido();
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
                case R.id.botaoAltItemPedido:{

                    String selecao[];
                    try {
                        Controlador controlador = new ControladorItemPedido();
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
                case R.id.botaoExcItemPedido:{

                    String[] selecao = new String[3];
                    try {

                        Controlador controlador = new ControladorItemPedido();
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
