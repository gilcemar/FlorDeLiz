package com.example.gilcemar.flordeliz.control;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.gilcemar.flordeliz.dao.Conexao;

import java.io.IOException;

/**
 * Created by gilcemar on 10/11/17.
 */

public class ControladorCalcado {
    /**
     * Esse método verica o estado do serviço da rede. No caso de estar OK retorna verdadeiro, caso
     * contrário retorna falso.
     * @return boolean
     */

    public boolean internetEstaConectada(Context context){
        ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo infNet = connManager.getActiveNetworkInfo();

        if (infNet != null && infNet.isConnected()){
            return true;
        }else{
            return false;
        }
    }

    public String inserir(String [] parametros, Context context){

        String retorno = "deu certo";
        if (internetEstaConectada(context)){
            Object[] param = new Object[2];
            param[0] = parametros;
            param[1] = "inserirCalc";
            Conexao conexao = new Conexao();

            try {
                retorno = conexao.inserirCalcado(parametros);
                return retorno;

            } catch (IOException e) {
                e.printStackTrace();
                retorno = e.getMessage();
                return retorno;
            }

            //return true;
        }else{
            retorno = "Não deu certo a conexão";
            return retorno;
        }


    }

    public String pesquisar (String [] parametros, Context context){


        String retorno;
        if (internetEstaConectada(context)){
            Object[] param = new Object[2];
            param[0] = parametros;

            Conexao conexao = new Conexao();

            try {
                retorno = conexao.pesquisarCalcado(parametros);
                return retorno;

            } catch (IOException e) {
                e.printStackTrace();
                retorno = e.getMessage();
                return retorno;
            }

            //return true;
        }else{
            retorno= "Não deu certo a conexão";
            return retorno;
        }


    }

    public String alterar (String [] parametros, Context context){


        String retorno;
        if (internetEstaConectada(context)){
            Object[] param = new Object[2];
            param[0] = parametros;

            Conexao conexao = new Conexao();

            try {
                retorno = conexao.alterarCalcado(parametros);
                return retorno;

            } catch (IOException e) {
                e.printStackTrace();
                retorno = e.getMessage();
                return retorno;
            }

            //return true;
        }else{
            retorno= "Não deu certo a conexão";
            return retorno;
        }


    }


    public String excluir(String [] parametros, Context context){


        String retorno;
        if (internetEstaConectada(context)){
            Object[] param = new Object[2];
            param[0] = parametros;

            Conexao conexao = new Conexao();

            try {
                retorno = conexao.excluirCalcado(parametros);
                return retorno;

            } catch (IOException e) {
                e.printStackTrace();
                retorno = e.getMessage();
                return retorno;
            }

            //return true;
        }else{
            retorno= "Não deu certo a conexão";
            return retorno;
        }


    }
}
