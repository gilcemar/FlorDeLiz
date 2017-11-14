package com.example.gilcemar.flordeliz.dao;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by gilcemar on 11/11/17.
 */

public class ConexaoJson extends Conexao {
    @Override
    public String inserirCalcado (String[] parametros) throws IOException {
        String url = "http://192.168.1.7/teste/view/cadastroCalcado.php?XDEBUG_SESSION_START=netbeans-xdebug";
        String post = "nomeCalc="+parametros[0];
        post += "&menorTam="+parametros[1];
        post += "&maiorTam="+parametros[2];
        post += "&cor="+parametros[3];
        post += "&nomeColecao="+parametros[4];
        post += "&precoCusto="+parametros[5];
        JSONObject json = new JSONObject();
        try {
            json.put("nomeCalc", parametros[0]);
            json.put("menorTam", parametros[1]);
            json.put("maiorTam", parametros[2]);
            json.put("cor", parametros[3]);
            json.put("nomeColecao", parametros[4]);
            json.put("precoCusto", parametros[5]);
        }catch (Exception err){

        }
        String jsonStr = json.toString();

        OkHttpClient client = new OkHttpClient();
        Request.Builder builder = new Request.Builder();
        builder.url(url);

        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(mediaType,jsonStr);

        builder.post(body);

        Request request = builder.build();



        Response response = client.newCall(request).execute();
        String resposta = response.body().string();



        return resposta;

    }
}
