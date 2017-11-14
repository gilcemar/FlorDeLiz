package com.example.gilcemar.flordeliz.dao;


import android.content.Context;

import com.example.gilcemar.flordeliz.R;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by gilcemar on 14/11/17.
 */

public class ClienteDAO extends DAO {
    @Override
    public String[] inserir(String[] parametros, Context context) throws IOException{
        String url = "http://192.168.1.7/teste/view/cadastroCliente.php?XDEBUG_SESSION_START=netbeans-xdebug";

        OkHttpClient client = new OkHttpClient();
        Request.Builder builder = new Request.Builder();
        builder.url(url);

        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded;charset=utf-8");
        //RequestBody body = RequestBody.create(mediaType,post);
        RequestBody body = null;
        FormBody.Builder formBody = new FormBody.Builder();
        formBody.add(INSERIRCLIENTE,INSERIRCLIENTE);//esse name vem lá do script PHP.
        formBody.add("cnpjLojaAnt",parametros[0]);
        formBody.add("cnpjLoja",parametros[1]);
        formBody.add("cpf",parametros[2]);
        formBody.add("clienteNome",parametros[3]);
        formBody.add( "nomeLoja",parametros[4]);
        body = formBody.build();
        builder.post(body);

        Request request = builder.build();



        Response response = client.newCall(request).execute();
        String[] resposta = new String[3];
        resposta[0]= response.body().string();
        String str = context.getString(R.string.lojaInsLojaSuc);

        DAO dao = new EndClienteDAO();
        DAO daoTelefone = new TelefoneDAO();
        String[] respEnd = dao.inserir(parametros, context);
        String[] respTel = daoTelefone.inserir(parametros, context);
        resposta[1] = respEnd[0];
        resposta[2] = respTel[0];


        return resposta;
    }

    @Override
    public String[] alterar(String[] parametros, Context context) throws IOException{
        String url = "http://192.168.1.7/teste/view/cadastroCliente.php?XDEBUG_SESSION_START=netbeans-xdebug";

        OkHttpClient client = new OkHttpClient();
        Request.Builder builder = new Request.Builder();
        builder.url(url);

        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded;charset=utf-8");
        //RequestBody body = RequestBody.create(mediaType,post);
        RequestBody body = null;
        FormBody.Builder formBody = new FormBody.Builder();
        formBody.add(ALTERARCLIENTE,ALTERARCLIENTE);//esse name vem lá do script PHP.
        formBody.add("cnpjLojaAnt",parametros[0]);
        formBody.add("cnpjLoja",parametros[1]);
        formBody.add("cpf",parametros[2]);
        formBody.add("clienteNome",parametros[3]);
        formBody.add( "nomeLoja",parametros[4]);
        body = formBody.build();
        builder.post(body);

        Request request = builder.build();



        Response response = client.newCall(request).execute();
        String[] resposta = new String[3];
        resposta[0] = response.body().string();

        DAO dao = new EndClienteDAO();
        DAO daoTelefone = new TelefoneDAO();
        String[] respEnd = dao.alterar(parametros, context);
        String[] respTel = daoTelefone.alterar(parametros, context);
        resposta[1] = respEnd[0];
        resposta[2] = respTel[0];

        return resposta;
    }

    @Override
    public String[] pesquisar(String[] parametros, Context context) throws IOException{
        String url = "http://192.168.1.7/teste/view/cadastroCliente.php?XDEBUG_SESSION_START=netbeans-xdebug";

        OkHttpClient client = new OkHttpClient();
        Request.Builder builder = new Request.Builder();
        builder.url(url);

        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded;charset=utf-8");
        //RequestBody body = RequestBody.create(mediaType,post);
        RequestBody body = null;
        FormBody.Builder formBody = new FormBody.Builder();
        formBody.add(PESQUISARCLIENTE,PESQUISARCLIENTE);//esse name vem lá do script PHP.
        formBody.add("cnpjLojaAnt",parametros[0]);
        formBody.add("cnpjLoja",parametros[1]);
        formBody.add("cpf",parametros[2]);
        formBody.add("clienteNome",parametros[3]);
        formBody.add( "nomeLoja",parametros[4]);
        body = formBody.build();
        builder.post(body);

        Request request = builder.build();



        Response response = client.newCall(request).execute();
        String[] resposta = new String[3];
        resposta[0] = response.body().string();

        DAO dao = new EndClienteDAO();
        DAO daoTelefone = new TelefoneDAO();
        String[] respEnd = dao.pesquisar(parametros, context);
        String[] respTel = daoTelefone.pesquisar(parametros, context);
        resposta[1] = respEnd[0];
        resposta[2] = respTel[0];



        return resposta;
    }

    @Override
    public String[] excluir(String[] parametros, Context context) throws IOException {
        String url = "http://192.168.1.7/teste/view/cadastroCliente.php?XDEBUG_SESSION_START=netbeans-xdebug";

        OkHttpClient client = new OkHttpClient();
        Request.Builder builder = new Request.Builder();
        builder.url(url);

        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded;charset=utf-8");
        //RequestBody body = RequestBody.create(mediaType,post);
        RequestBody body = null;
        FormBody.Builder formBody = new FormBody.Builder();
        formBody.add(EXCLUIRCLIENTE,EXCLUIRCLIENTE);//esse name vem lá do script PHP.
        formBody.add("cnpjLojaAnt",parametros[0]);
        formBody.add("cnpjLoja",parametros[1]);
        formBody.add("cpf",parametros[2]);
        formBody.add("clienteNome",parametros[3]);
        formBody.add( "nomeLoja",parametros[4]);
        body = formBody.build();
        builder.post(body);

        Request request = builder.build();



        Response response = client.newCall(request).execute();
        String[] resposta = new String[1];
        resposta[0]= response.body().string();



        return resposta;
    }
}
