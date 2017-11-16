package com.example.gilcemar.flordeliz.dao;

import android.content.Context;

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

public class ItemPedidoDAO extends DAO {
    @Override
    public String[] inserir(String[] parametros, Context context) throws IOException {
        String url = "http://192.168.1.7/teste/view/cadastroItemPedido.php?XDEBUG_SESSION_START=netbeans-xdebug";

        OkHttpClient client = new OkHttpClient();
        Request.Builder builder = new Request.Builder();
        builder.url(url);

        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded;charset=utf-8");
        //RequestBody body = RequestBody.create(mediaType,post);
        RequestBody body = null;
        FormBody.Builder formBody = new FormBody.Builder();
        formBody.add(INSERIRITEMPEDIDO,INSERIRITEMPEDIDO);//esse name vem lá do script PHP.
        formBody.add("quantidadeItem",parametros[0]);
        formBody.add("codigoProd",parametros[1]);
        formBody.add("numeroPedido",parametros[2]);
        body = formBody.build();
        builder.post(body);

        Request request = builder.build();



        Response response = client.newCall(request).execute();
        String[] resposta = new String[3];
        resposta[0] = response.body().string();

        return resposta;
    }

    @Override
    public String[] alterar(String[] parametros, Context context) throws IOException {
        String url = "http://192.168.1.7/teste/view/cadastroItemPedido.php?XDEBUG_SESSION_START=netbeans-xdebug";

        OkHttpClient client = new OkHttpClient();
        Request.Builder builder = new Request.Builder();
        builder.url(url);

        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded;charset=utf-8");
        //RequestBody body = RequestBody.create(mediaType,post);
        RequestBody body = null;
        FormBody.Builder formBody = new FormBody.Builder();
        formBody.add(ALTERARITEMPEDIDO,ALTERARITEMPEDIDO);//esse name vem lá do script PHP.
        formBody.add("quantidadeItem",parametros[0]);
        formBody.add("codigoProd",parametros[1]);
        formBody.add("numeroPedido",parametros[2]);
        body = formBody.build();
        builder.post(body);

        Request request = builder.build();



        Response response = client.newCall(request).execute();
        String[] resposta = new String[3];
        resposta[0] = response.body().string();

        return resposta;
    }

    @Override
    public String[] pesquisar(String[] parametros, Context context) throws IOException {
        String url = "http://192.168.1.7/teste/view/cadastroItemPedido.php?XDEBUG_SESSION_START=netbeans-xdebug";

        OkHttpClient client = new OkHttpClient();
        Request.Builder builder = new Request.Builder();
        builder.url(url);

        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded;charset=utf-8");
        //RequestBody body = RequestBody.create(mediaType,post);
        RequestBody body = null;
        FormBody.Builder formBody = new FormBody.Builder();
        formBody.add(PESQUISARITEMPEDIDO,PESQUISARITEMPEDIDO);//esse name vem lá do script PHP.
        formBody.add("quantidadeItem",parametros[0]);
        formBody.add("codigoProd",parametros[1]);
        formBody.add("numeroPedido",parametros[2]);

        body = formBody.build();
        builder.post(body);

        Request request = builder.build();

        Response response = client.newCall(request).execute();
        String[] resposta = new String[3];
        resposta[0] = response.body().string();

        return resposta;
    }

    @Override
    public String[] excluir(String[] parametros, Context context) throws IOException {
        String url = "http://192.168.1.7/teste/view/cadastroItemPedido.php?XDEBUG_SESSION_START=netbeans-xdebug";

        OkHttpClient client = new OkHttpClient();
        Request.Builder builder = new Request.Builder();
        builder.url(url);

        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded;charset=utf-8");
        //RequestBody body = RequestBody.create(mediaType,post);
        RequestBody body = null;
        FormBody.Builder formBody = new FormBody.Builder();
        formBody.add(EXCLUIRITEMPEDIDO,EXCLUIRITEMPEDIDO);//esse name vem lá do script PHP.
        formBody.add("quantidadeItem",parametros[0]);
        formBody.add("codigoProd",parametros[1]);
        formBody.add("numeroPedido",parametros[2]);
        body = formBody.build();
        builder.post(body);

        Request request = builder.build();



        Response response = client.newCall(request).execute();
        String[] resposta = new String[3];
        resposta[0] = response.body().string();

        return resposta;
    }
}
