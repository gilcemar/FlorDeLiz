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

public class PedidoDAO extends DAO {
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
        formBody.add(INSERIRPEDIDO,INSERIRPEDIDO);//esse name vem lá do script PHP.
        formBody.add("descPedido",parametros[0]);
        formBody.add("cnpjLoja",parametros[1]);
        formBody.add("cnpjLojaAnt",parametros[2]);
        formBody.add("idPedido",parametros[3]);
        formBody.add( "idPedidoAnt",parametros[4]);
        body = formBody.build();
        builder.post(body);

        Request request = builder.build();

        Response response = client.newCall(request).execute();
        String[] resposta = new String[3];
        resposta[0]= response.body().string();
        String str = context.getString(R.string.lojaInsLojaSuc);

        DAO dao = new ItemPedidoDAO();
        String[] respEnd = dao.inserir(parametros, context);
        resposta[1] = respEnd[0];

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
        formBody.add(ALTERARPEDIDO,ALTERARPEDIDO);//esse name vem lá do script PHP.
        formBody.add("descPedido",parametros[0]);
        formBody.add("cnpjLoja",parametros[1]);
        formBody.add("cnpjLojaAnt",parametros[2]);
        formBody.add("idPedido",parametros[3]);
        formBody.add( "idPedidoAnt",parametros[4]);
        body = formBody.build();
        builder.post(body);

        Request request = builder.build();

        Response response = client.newCall(request).execute();
        String[] resposta = new String[3];
        resposta[0] = response.body().string();

        DAO dao = new ItemPedidoDAO();
        String[] respEnd = dao.alterar(parametros, context);
        resposta[1] = respEnd[0];

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
        formBody.add(PESQUISARPEDIDO,PESQUISARPEDIDO);//esse name vem lá do script PHP.
        formBody.add("descPedido",parametros[0]);
        formBody.add("cnpjLoja",parametros[1]);
        formBody.add("cnpjLojaAnt",parametros[2]);
        formBody.add("idPedido",parametros[3]);
        formBody.add( "idPedidoAnt",parametros[4]);
        body = formBody.build();
        builder.post(body);

        Request request = builder.build();

        Response response = client.newCall(request).execute();
        String[] resposta = new String[3];
        resposta[0] = response.body().string();

        DAO dao = new ItemPedidoDAO();
        String[] respEnd = dao.pesquisar(parametros, context);
        resposta[1] = respEnd[0];

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
        formBody.add(EXCLUIRPEDIDO,EXCLUIRPEDIDO);//esse name vem lá do script PHP.
        formBody.add("descPedido",parametros[0]);
        formBody.add("cnpjLoja",parametros[1]);
        formBody.add("cnpjLojaAnt",parametros[2]);
        formBody.add("idPedido",parametros[3]);
        formBody.add( "idPedidoAnt",parametros[4]);
        body = formBody.build();
        builder.post(body);

        Request request = builder.build();

        Response response = client.newCall(request).execute();
        String[] resposta = new String[1];
        resposta[0]= response.body().string();

        return resposta;
    }
}
