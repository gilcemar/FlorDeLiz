package com.example.gilcemar.flordeliz.dao;

import android.os.AsyncTask;
import android.widget.Toast;

import com.example.gilcemar.flordeliz.model.Calcado;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static okhttp3.Request.*;

/**
 * Created by gilcemar on 10/11/17.
 */

public class Conexao {
    private static final String INSERIRCALCADO = "inserirCalc";
    private static final String ALTERARCALCADO = "alterarCalc";
    private static final String EXCLUIRCALCADO = "excluirCalc";
    private static final String PESQUISARCALCADO = "pesquisarCalc";



    public String inserirCalcado (String[] parametros) throws IOException{
        String url = "http://192.168.1.7/teste/view/cadastroCalcado.php?XDEBUG_SESSION_START=netbeans-xdebug";
        String post = "nomeCalc="+parametros[0];
        post += "&menorTam="+parametros[1];
        post += "&maiorTam="+parametros[2];
        post += "&cor="+parametros[3];
        post += "&nomeColecao="+parametros[4];
        post += "&precoCusto="+parametros[5];


        OkHttpClient client = new OkHttpClient();
        Builder builder = new Builder();
        builder.url(url);

        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded;charset=utf-8");
        //RequestBody body = RequestBody.create(mediaType,post);
        RequestBody body = null;
        FormBody.Builder formBody = new FormBody.Builder();
	    formBody.add("botaoInsCal","inserir calçado");//esse name vem lá do script PHP.
        formBody.add("nomeCalc",parametros[0]);
        formBody.add("menorTam",parametros[1]);
        formBody.add("maiorTam",parametros[2]);
        formBody.add("cor",parametros[3]);
        formBody.add( "nomeColecao",parametros[4]);
        formBody.add("precoCusto",parametros[5]);
        formBody.add("idCalc", parametros[6]);
        body = formBody.build();
        builder.post(body);

        Request request = builder.build();



        Response response = client.newCall(request).execute();
        String resposta = response.body().string();



        return resposta;

    }

    public String pesquisarCalcado (String[] parametros) throws IOException{
        String url = "http://192.168.1.7/teste/view/cadastroCalcado.php?XDEBUG_SESSION_START=netbeans-xdebug";

        OkHttpClient client = new OkHttpClient();
        Builder builder = new Builder();
        builder.url(url);

        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded;charset=utf-8");
        //RequestBody body = RequestBody.create(mediaType,post);
        RequestBody body = null;
        FormBody.Builder formBody = new FormBody.Builder();
        formBody.add("botaoPesqCal","pesquisar calçado");//esse name vem lá do script PHP.
        formBody.add("nomeCalc",parametros[0]);
        formBody.add("menorTam",parametros[1]);
        formBody.add("maiorTam",parametros[2]);
        formBody.add("cor",parametros[3]);
        formBody.add( "nomeColecao",parametros[4]);
        formBody.add("precoCusto",parametros[5]);
        formBody.add("idCalc", parametros[6]);
        body = formBody.build();
        builder.post(body);

        Request request = builder.build();



        Response response = client.newCall(request).execute();
        String resposta = response.body().string();



        return resposta;

    }

    public String alterarCalcado (String[] parametros) throws IOException{
        String url = "http://192.168.1.7/teste/view/cadastroCalcado.php?XDEBUG_SESSION_START=netbeans-xdebug";

        OkHttpClient client = new OkHttpClient();
        Builder builder = new Builder();
        builder.url(url);

        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded;charset=utf-8");
        //RequestBody body = RequestBody.create(mediaType,post);
        RequestBody body = null;
        FormBody.Builder formBody = new FormBody.Builder();
        formBody.add("botaoAltCal","pesquisar calçado");//esse name vem lá do script PHP.
        formBody.add("nomeCalc",parametros[0]);
        formBody.add("menorTam",parametros[1]);
        formBody.add("maiorTam",parametros[2]);
        formBody.add("cor",parametros[3]);
        formBody.add( "nomeColecao",parametros[4]);
        formBody.add("precoCusto",parametros[5]);
        formBody.add("idCalc", parametros[6]);
        body = formBody.build();
        builder.post(body);

        Request request = builder.build();



        Response response = client.newCall(request).execute();
        String resposta = response.body().string();



        return resposta;

    }


    public String excluirCalcado (String[] parametros) throws IOException{
        String url = "http://192.168.1.7/teste/view/cadastroCalcado.php?XDEBUG_SESSION_START=netbeans-xdebug";

        OkHttpClient client = new OkHttpClient();
        Builder builder = new Builder();
        builder.url(url);

        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded;charset=utf-8");
        //RequestBody body = RequestBody.create(mediaType,post);
        RequestBody body = null;
        FormBody.Builder formBody = new FormBody.Builder();
        formBody.add("botaoExcCal","pesquisar calçado");//esse name vem lá do script PHP.
        formBody.add("nomeCalc",parametros[0]);
        formBody.add("menorTam",parametros[1]);
        formBody.add("maiorTam",parametros[2]);
        formBody.add("cor",parametros[3]);
        formBody.add( "nomeColecao",parametros[4]);
        formBody.add("precoCusto",parametros[5]);
        formBody.add("idCalc", parametros[6]);
        body = formBody.build();
        builder.post(body);

        Request request = builder.build();



        Response response = client.newCall(request).execute();
        String resposta = response.body().string();



        return resposta;

    }

}
