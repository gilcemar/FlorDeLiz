package com.example.gilcemar.flordeliz.control;

import android.content.Context;

import com.example.gilcemar.flordeliz.dao.DAO;
import com.example.gilcemar.flordeliz.dao.LoteDAO;

import java.io.IOException;

/**
 * Created by gilcemar on 15/11/17.
 */

public class ControladorLote extends Controlador {
    @Override
    public String[] inserir(String[] parametros, Context context) {
        String[] retorno = new String[1];
        if (super.internetEstaConectada(context)){
            Object[] param = new Object[2];
            param[0] = parametros;
            param[1] = "inserirCalc";
            DAO dao = new LoteDAO();

            try {
                retorno = dao.inserir(parametros, context);
                return retorno;

            } catch (IOException e) {
                e.printStackTrace();
                retorno[0] = e.getMessage();
                return retorno;
            }

            //return true;
        }else{
            retorno[0] = "Não deu certo a conexão";
            return retorno;
        }
    }

    @Override
    public String[] alterar(String[] parametros, Context context) {
        String[] retorno = new String[1];
        if (super.internetEstaConectada(context)){
            Object[] param = new Object[2];
            param[0] = parametros;

            DAO dao = new LoteDAO();

            try {
                retorno = dao.alterar(parametros, context);
                return retorno;

            } catch (IOException e) {
                e.printStackTrace();
                retorno[0] = e.getMessage();
                return retorno;
            }

            //return true;
        }else{
            retorno[0]= "Não deu certo a conexão";
            return retorno;
        }
    }

    @Override
    public String[] pesquisar(String[] parametros, Context context) {
        String[] retorno = new String[1];
        if (super.internetEstaConectada(context)){
            Object[] param = new Object[2];
            param[0] = parametros;

            DAO dao = new LoteDAO();

            try {
                retorno = dao.pesquisar(parametros, context);
                return retorno;

            } catch (IOException e) {
                e.printStackTrace();
                retorno[0] = e.getMessage();
                return retorno;
            }

            //return true;
        }else{
            retorno[0] = "Não deu certo a conexão";
            return retorno;
        }
    }

    @Override
    public String[] excluir(String[] parametros, Context context) {
        String[] retorno = new String[1];
        if (internetEstaConectada(context)){
            Object[] param = new Object[2];
            param[0] = parametros;

            DAO dao = new LoteDAO();

            try {
                retorno = dao.excluir(parametros, context);
                return retorno;

            } catch (IOException e) {
                e.printStackTrace();
                retorno[0] = e.getMessage();
                return retorno;
            }

            //return true;
        }else{
            retorno[0] = "Não deu certo a conexão";
            return retorno;
        }
    }
}
