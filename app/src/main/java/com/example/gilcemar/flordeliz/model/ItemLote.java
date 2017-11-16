package com.example.gilcemar.flordeliz.model;

import java.io.Serializable;

/**
 * Created by gilcemar on 15/11/17.
 */

public class ItemLote implements Serializable{

    String codCalPro;
    String idLote;
    String tamCal;
    String quanCalProd;
    String quantCalDispon;
    String precoRevenda;

    public ItemLote() {
        this.codCalPro = "";
        this.idLote = "";
        this.tamCal = "";
        this.quanCalProd = "";
        this.quantCalDispon = "";
        this.precoRevenda = "";
    }

    public String getCodCalPro() {
        return codCalPro;
    }

    public void setCodCalPro(String codCalPro) {
        this.codCalPro = codCalPro;
    }

    public String getIdLote() {
        return idLote;
    }

    public void setIdLote(String idLote) {
        this.idLote = idLote;
    }

    public String getTamCal() {
        return tamCal;
    }

    public void setTamCal(String tamCal) {
        this.tamCal = tamCal;
    }

    public String getQuanCalProd() {
        return quanCalProd;
    }

    public void setQuanCalProd(String quanCalProd) {
        this.quanCalProd = quanCalProd;
    }

    public String getQuantCalDispon() {
        return quantCalDispon;
    }

    public void setQuantCalDispon(String quantCalDispon) {
        this.quantCalDispon = quantCalDispon;
    }

    public String getPrecoRevenda() {
        return precoRevenda;
    }

    public void setPrecoRevenda(String precoRevenda) {
        this.precoRevenda = precoRevenda;
    }
}
