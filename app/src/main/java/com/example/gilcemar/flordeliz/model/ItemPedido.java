package com.example.gilcemar.flordeliz.model;

import java.io.Serializable;

/**
 * Created by gilcemar on 16/11/17.
 */

public class ItemPedido implements Serializable {
    private String quantItem;
    private String codCalProd;
    private String idPedido;

    public ItemPedido() {
        this.quantItem = "";
        this.codCalProd = "";
        this.idPedido = "";
    }

    public String getQuantItem() {
        return quantItem;
    }

    public void setQuantItem(String quantItem) {
        this.quantItem = quantItem;
    }

    public String getCodCalProd() {
        return codCalProd;
    }

    public void setCodCalProd(String codCalProd) {
        this.codCalProd = codCalProd;
    }

    public String getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(String idPedido) {
        this.idPedido = idPedido;
    }
}
