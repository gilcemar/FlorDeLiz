package com.example.gilcemar.flordeliz.control;

import android.content.Context;

import com.example.gilcemar.flordeliz.dao.ClienteDAO;
import com.example.gilcemar.flordeliz.dao.DAO;

import java.io.IOException;

/**
 * Created by gilcemar on 14/11/17.
 */

public class ControladorCliente extends Controlador {
    @Override
    public String inserir(String[] parametros, Context context) {
        String retorno = "deu certo";
        if (super.internetEstaConectada(context)){
            Object[] param = new Object[2];
            param[0] = parametros;
            param[1] = "inserirCalc";
            DAO clienteDAO = new ClienteDAO();

            try {
                retorno = clienteDAO.inserir(parametros);
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

    @Override
    public String alterar(String[] parametros, Context context) {
        String retorno;
        if (super.internetEstaConectada(context)){
            Object[] param = new Object[2];
            param[0] = parametros;

            DAO clienteDAO = new ClienteDAO();

            try {
                retorno = clienteDAO.alterar(parametros);
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

    @Override
    public String pesquisar(String[] parametros, Context context) {
        String retorno;
        if (super.internetEstaConectada(context)){
            Object[] param = new Object[2];
            param[0] = parametros;

            DAO clienteDAO = new ClienteDAO();

            try {
                retorno = clienteDAO.pesquisar(parametros);
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

    @Override
    public String excluir(String[] parametros, Context context) {
        String retorno;
        if (internetEstaConectada(context)){
            Object[] param = new Object[2];
            param[0] = parametros;

            DAO clienteDAO = new ClienteDAO();

            try {
                retorno = clienteDAO.excluir(parametros);
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
