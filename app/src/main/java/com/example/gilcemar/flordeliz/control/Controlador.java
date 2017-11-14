package com.example.gilcemar.flordeliz.control;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by gilcemar on 14/11/17.
 */

public abstract class Controlador {
    public abstract String inserir(String [] parametros, Context context);
    public abstract String alterar(String [] parametros, Context context);
    public abstract String pesquisar(String [] parametros, Context context);
    public abstract String excluir(String [] parametros, Context context);
    public boolean internetEstaConectada(Context context){
        ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo infNet = connManager.getActiveNetworkInfo();

        if (infNet != null && infNet.isConnected()){
            return true;
        }else{
            return false;
        }
    }

}
