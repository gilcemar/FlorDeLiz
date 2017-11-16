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

public class ItemLoteDAO extends DAO {
    @Override
    public String[] inserir(String[] parametros, Context context) throws IOException {
        String url = "http://192.168.1.7/teste/view/cadastroItemLote.php?XDEBUG_SESSION_START=netbeans-xdebug";

        OkHttpClient client = new OkHttpClient();
        Request.Builder builder = new Request.Builder();
        builder.url(url);

        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded;charset=utf-8");
        //RequestBody body = RequestBody.create(mediaType,post);
        RequestBody body = null;
        FormBody.Builder formBody = new FormBody.Builder();
        formBody.add(INSERIRITEMLOTE,INSERIRITEMLOTE);//esse name vem lá do script PHP.
        formBody.add("codCalProd",parametros[0]);
        formBody.add("idLote",parametros[1]);
        formBody.add("idLoteAnt",parametros[2]);
        formBody.add("tamCalc",parametros[3]);
        formBody.add("quantProd",parametros[4]);
        formBody.add("quantDispon",parametros[5]);
        formBody.add("precoRev",parametros[6]);
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
        String url = "http://192.168.1.7/teste/view/cadastroItemLote.php?XDEBUG_SESSION_START=netbeans-xdebug";

        OkHttpClient client = new OkHttpClient();
        Request.Builder builder = new Request.Builder();
        builder.url(url);

        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded;charset=utf-8");
        //RequestBody body = RequestBody.create(mediaType,post);
        RequestBody body = null;
        FormBody.Builder formBody = new FormBody.Builder();
        formBody.add(ALTERARITEMLOTE,ALTERARITEMLOTE);//esse name vem lá do script PHP.
        formBody.add("codCalProd",parametros[0]);
        formBody.add("idLote",parametros[1]);
        formBody.add("idLoteAnt",parametros[2]);
        formBody.add("tamCalc",parametros[3]);
        formBody.add("quantProd",parametros[4]);
        formBody.add("quantDispon",parametros[5]);
        formBody.add("precoRev",parametros[6]);
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
        String url = "http://192.168.1.7/teste/view/cadastroItemLote.php?XDEBUG_SESSION_START=netbeans-xdebug";

        OkHttpClient client = new OkHttpClient();
        Request.Builder builder = new Request.Builder();
        builder.url(url);

        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded;charset=utf-8");
        //RequestBody body = RequestBody.create(mediaType,post);
        RequestBody body = null;
        FormBody.Builder formBody = new FormBody.Builder();
        formBody.add(PESQUISARITEMLOTE,PESQUISARITEMLOTE);//esse name vem lá do script PHP.
        formBody.add("codCalProd",parametros[0]);//NESSE MÉTODO É NECESSÁRIO FICAR ESSA ORDEM DIFERENTE
        formBody.add("idLote",parametros[3]);//AQUI
        formBody.add("idLoteAnt",parametros[2]);//E AQUI
        formBody.add("tamCalc",parametros[3]);
        formBody.add("quantProd",parametros[4]);
        formBody.add("quantDispon",parametros[5]);
        formBody.add("precoRev",parametros[6]);
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
        String url = "http://192.168.1.7/teste/view/cadastroItemLote.php?XDEBUG_SESSION_START=netbeans-xdebug";

        OkHttpClient client = new OkHttpClient();
        Request.Builder builder = new Request.Builder();
        builder.url(url);

        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded;charset=utf-8");
        //RequestBody body = RequestBody.create(mediaType,post);
        RequestBody body = null;
        FormBody.Builder formBody = new FormBody.Builder();
        formBody.add(EXCLUIRITEMLOTE,EXCLUIRITEMLOTE);//esse name vem lá do script PHP.
        formBody.add("codCalProd",parametros[0]);
        formBody.add("idLote",parametros[1]);
        formBody.add("idLoteAnt",parametros[2]);
        formBody.add("tamCalc",parametros[3]);
        formBody.add("quantProd",parametros[4]);
        formBody.add("quantDispon",parametros[5]);
        formBody.add("precoRev",parametros[6]);
        body = formBody.build();
        builder.post(body);

        Request request = builder.build();



        Response response = client.newCall(request).execute();
        String[] resposta = new String[3];
        resposta[0] = response.body().string();

        return resposta;
    }
}
