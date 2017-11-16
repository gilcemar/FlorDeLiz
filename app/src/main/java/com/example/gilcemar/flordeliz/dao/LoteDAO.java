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

public class LoteDAO extends DAO {
    @Override
    public String[] inserir(String[] parametros, Context context) throws IOException{
        String url = "http://192.168.1.7/teste/view/cadastroLote.php?XDEBUG_SESSION_START=netbeans-xdebug";

        OkHttpClient client = new OkHttpClient();
        Request.Builder builder = new Request.Builder();
        builder.url(url);

        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded;charset=utf-8");
        //RequestBody body = RequestBody.create(mediaType,post);
        RequestBody body = null;
        FormBody.Builder formBody = new FormBody.Builder();
        formBody.add(INSERIRLOTE,INSERIRLOTE);//esse name vem lá do script PHP.
        formBody.add("codProduto",parametros[0]);
        formBody.add("codProdutoAnt",parametros[1]);
        formBody.add("dataProducao",parametros[2]);
        formBody.add("numeroLote",parametros[3]);
        body = formBody.build();
        builder.post(body);

        Request request = builder.build();



        Response response = client.newCall(request).execute();
        String[] resposta = new String[3];
        resposta[0]= response.body().string();
        String str = context.getString(R.string.lojaInsLojaSuc);

        DAO dao = new ItemLoteDAO();

        //String[] respEnd = dao.inserir(parametros, context);
        //resposta[1] = respEnd[0];



        return resposta;
    }

    @Override
    public String[] alterar(String[] parametros, Context context) throws IOException{
        String url = "http://192.168.1.7/teste/view/cadastroLote.php?XDEBUG_SESSION_START=netbeans-xdebug";

        OkHttpClient client = new OkHttpClient();
        Request.Builder builder = new Request.Builder();
        builder.url(url);

        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded;charset=utf-8");
        //RequestBody body = RequestBody.create(mediaType,post);
        RequestBody body = null;
        FormBody.Builder formBody = new FormBody.Builder();
        formBody.add(ALTERARLOTE,ALTERARLOTE);//esse name vem lá do script PHP.
        formBody.add("codProduto",parametros[0]);
        formBody.add("codProdutoAnt",parametros[1]);
        formBody.add("dataProducao",parametros[2]);
        formBody.add("numeroLote",parametros[3]);
        body = formBody.build();
        builder.post(body);

        Request request = builder.build();



        Response response = client.newCall(request).execute();
        String[] resposta = new String[3];
        resposta[0] = response.body().string();

        DAO dao = new ItemLoteDAO();
        String[] respEnd = dao.alterar(parametros, context);


        return resposta;
    }

    @Override
    public String[] pesquisar(String[] parametros, Context context) throws IOException{
        String url = "http://192.168.1.7/teste/view/cadastroLote.php?XDEBUG_SESSION_START=netbeans-xdebug";

        OkHttpClient client = new OkHttpClient();
        Request.Builder builder = new Request.Builder();
        builder.url(url);

        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded;charset=utf-8");
        //RequestBody body = RequestBody.create(mediaType,post);
        RequestBody body = null;
        FormBody.Builder formBody = new FormBody.Builder();
        formBody.add(PESQUISARLOTE,PESQUISARLOTE);//esse name vem lá do script PHP.
        formBody.add("codProduto",parametros[0]);
        formBody.add("codProdutoAnt",parametros[1]);
        formBody.add("dataProducao",parametros[2]);
        formBody.add("numeroLote",parametros[3]);
        body = formBody.build();
        builder.post(body);

        Request request = builder.build();



        Response response = client.newCall(request).execute();
        String[] resposta = new String[3];
        resposta[0] = response.body().string();

        ItemLoteDAO daoItem = new ItemLoteDAO();

        String[] respostaItemLote = daoItem.pesquisar(parametros, context);
        if (respostaItemLote[0]!=null){
            resposta[1] = respostaItemLote[0];
        }

        return resposta;
    }

    @Override
    public String[] excluir(String[] parametros, Context context) throws IOException {
        String url = "http://192.168.1.7/teste/view/cadastroLote.php?XDEBUG_SESSION_START=netbeans-xdebug";

        OkHttpClient client = new OkHttpClient();
        Request.Builder builder = new Request.Builder();
        builder.url(url);

        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded;charset=utf-8");
        //RequestBody body = RequestBody.create(mediaType,post);
        RequestBody body = null;
        FormBody.Builder formBody = new FormBody.Builder();
        formBody.add(EXCLUIRLOTE,EXCLUIRLOTE);//esse name vem lá do script PHP.
        formBody.add("codProduto",parametros[0]);
        formBody.add("codProdutoAnt",parametros[1]);
        formBody.add("dataProducao",parametros[2]);
        formBody.add("numeroLote",parametros[3]);

        body = formBody.build();
        builder.post(body);

        Request request = builder.build();

        Response response = client.newCall(request).execute();
        String[] resposta = new String[1];
        resposta[0]= response.body().string();

        return resposta;
    }
}
