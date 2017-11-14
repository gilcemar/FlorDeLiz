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

public class TelefoneDAO extends DAO {
    @Override
    public String[] inserir(String[] parametros, Context context) throws IOException {
        String url = "http://192.168.1.7/teste/view/cadastroTelefone.php?XDEBUG_SESSION_START=netbeans-xdebug";

        OkHttpClient client = new OkHttpClient();
        Request.Builder builder = new Request.Builder();
        builder.url(url);

        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded;charset=utf-8");
        //RequestBody body = RequestBody.create(mediaType,post);
        RequestBody body = null;

        FormBody.Builder formBody = new FormBody.Builder();
        formBody.add(INSERIRTEL,INSERIRTEL);//esse name vem lá do script PHP.
        formBody.add("numero",parametros[12]);
        formBody.add("cnpj",parametros[1]);
        formBody.add("telAnt",parametros[13]);

        body = formBody.build();
        builder.post(body);

        Request request = builder.build();

        Response response = client.newCall(request).execute();
        String[] resposta = new String[1];
        resposta[0]= response.body().string();

        return resposta;
    }

    @Override
    public String[] alterar(String[] parametros, Context context) throws IOException {
        String url = "http://192.168.1.7/teste/view/cadastroTelefone.php?XDEBUG_SESSION_START=netbeans-xdebug";

        OkHttpClient client = new OkHttpClient();
        Request.Builder builder = new Request.Builder();
        builder.url(url);

        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded;charset=utf-8");
        //RequestBody body = RequestBody.create(mediaType,post);
        RequestBody body = null;

        FormBody.Builder formBody = new FormBody.Builder();
        formBody.add(ALTERARTEL,ALTERARTEL);//esse name vem lá do script PHP.
        formBody.add("numero",parametros[12]);
        formBody.add("cnpj",parametros[1]);
        formBody.add("telAnt",parametros[13]);

        body = formBody.build();
        builder.post(body);

        Request request = builder.build();

        Response response = client.newCall(request).execute();
        String[] resposta = new String[1];
        resposta[0]= response.body().string();

        return resposta;
    }

    @Override
    public String[] pesquisar(String[] parametros, Context context) throws IOException {
        String url = "http://192.168.1.7/teste/view/cadastroTelefone.php?XDEBUG_SESSION_START=netbeans-xdebug";

        OkHttpClient client = new OkHttpClient();
        Request.Builder builder = new Request.Builder();
        builder.url(url);

        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded;charset=utf-8");
        //RequestBody body = RequestBody.create(mediaType,post);
        RequestBody body = null;

        FormBody.Builder formBody = new FormBody.Builder();
        formBody.add(PESQUISARTEL,PESQUISARTEL);//esse name vem lá do script PHP.
        formBody.add("numero",parametros[12]);
        formBody.add("cnpj",parametros[1]);
        formBody.add("telAnt",parametros[13]);

        body = formBody.build();
        builder.post(body);

        Request request = builder.build();

        Response response = client.newCall(request).execute();
        String[] resposta = new String[1];
        resposta[0]= response.body().string();

        return resposta;
    }

    @Override
    public String[] excluir(String[] parametros, Context context) throws IOException {
        String url = "http://192.168.1.7/teste/view/cadastroTelefone.php?XDEBUG_SESSION_START=netbeans-xdebug";

        OkHttpClient client = new OkHttpClient();
        Request.Builder builder = new Request.Builder();
        builder.url(url);

        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded;charset=utf-8");
        //RequestBody body = RequestBody.create(mediaType,post);
        RequestBody body = null;

        FormBody.Builder formBody = new FormBody.Builder();
        formBody.add(EXCLUIRTEL,EXCLUIRTEL);//esse name vem lá do script PHP.
        formBody.add("numero",parametros[12]);
        formBody.add("cnpj",parametros[1]);//não invente de mudar a sequencia é essa e pronto.
        formBody.add("telAnt",parametros[13]);

        body = formBody.build();
        builder.post(body);

        Request request = builder.build();

        Response response = client.newCall(request).execute();
        String[] resposta = new String[1];
        resposta[0]= response.body().string();

        return resposta;
    }
}
