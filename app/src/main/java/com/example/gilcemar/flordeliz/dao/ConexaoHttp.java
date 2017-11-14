package com.example.gilcemar.flordeliz.dao;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by gilcemar on 11/11/17.
 */

public class ConexaoHttp extends Conexao{
    @Override
    public String inserirCalcado (String[] parametros) throws IOException{
        String post = "nomeCalc="+parametros[0];
        post += "&menorTam="+parametros[1];
        post += "&maiorTam="+parametros[2];
        post += "&cor="+parametros[3];
        post += "&nomeColecao="+parametros[4];
        post += "&precoCusto="+parametros[5];

        URL url = null;
        //http://localhost/teste/view/cadastroCalcado.php?XDEBUG_SESSION_START=netbeans-xdebug
        HttpURLConnection conn =  null;
        try {
            url = new URL("http://192.168.1.7/teste/view/cadastroCalcado.php?XDEBUG_SESSION_START=netbeans-xdebug");
            conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("POST");
            //https://api.cakephp.org/3.3/class-Cake.Network.Response.html aqui tem os tipos de content type
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("Content-Lenght", ""+Integer.toString(post.getBytes().length));
            conn.setRequestProperty("Content-Language", "pt-BR");

            conn.setUseCaches(false);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            /*
            DataOutputStream dataOutputStream = new DataOutputStream(conn.getOutputStream());
            dataOutputStream.writeBytes(post);
            dataOutputStream.flush();
            dataOutputStream.close();
            */

            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
            outputStreamWriter.write(post);
            outputStreamWriter.flush();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));



            String linha;
            StringBuffer resposta = new StringBuffer();
            while ((linha = bufferedReader.readLine()) != null){
                resposta.append(linha);
                resposta.append('\r');

            }
            bufferedReader.close();

            return resposta.toString();


        }catch (EOFException erro){
            String er = erro.getMessage();
            erro.printStackTrace();
            return er;
        }finally {
            if (conn != null){
                conn.disconnect();
            }
        }
    }
}
