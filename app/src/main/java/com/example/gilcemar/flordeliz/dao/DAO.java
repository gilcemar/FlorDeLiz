package com.example.gilcemar.flordeliz.dao;

import android.content.Context;

import java.io.IOException;

/**
 * Created by gilcemar on 14/11/17.
 */

public abstract class DAO {

    protected static final String EXCLUIRCALCADO = "botaoExcCal";
    protected static final String INSERIRCALCADO = "botaoInsCal";
    protected static final String PESQUISARCALCADO = "botaoPesqCal";
    protected static final String ALTERARCALCADO = "botaoAltCal";

    protected static final String EXCLUIRENDLOJA = "botaoExcEndLoja";
    protected static final String INSERIRENDLOJA = "botaoInsEndLoja";
    protected static final String PESQUISARENDLOJA = "botaoPesqEndLoja";
    protected static final String ALTERARENDLOJA = "botaoAltEndLoja";

    protected static final String EXCLUIRTEL = "botaoExcTel";
    protected static final String INSERIRTEL = "botaoInsTel";
    protected static final String PESQUISARTEL = "botaoPesqTel";
    protected static final String ALTERARTEL = "botaoAltTel";


    protected static final String EXCLUIRCLIENTE = "botaoExcCli";
    protected static final String INSERIRCLIENTE = "botaoInsCli";
    protected static final String PESQUISARCLIENTE = "botaoPesqCli";
    protected static final String ALTERARCLIENTE = "botaoAltCli";

    protected static final String EXCLUIRLOTE = "botaoExcLote";
    protected static final String INSERIRLOTE = "botaoInsLote";
    protected static final String PESQUISARLOTE = "botaoPesqLote";
    protected static final String ALTERARLOTE = "botaoAltLote";

    protected static final String EXCLUIRITEMLOTE = "botaoExcItemLote";
    protected static final String INSERIRITEMLOTE = "botaoInsItemLote";
    protected static final String PESQUISARITEMLOTE = "botaoPesqItemLote";
    protected static final String ALTERARITEMLOTE = "botaoAltItemLote";

    protected static final String EXCLUIRPEDIDO = "botaoExcItemLote";
    protected static final String INSERIRPEDIDO = "botaoInsItemLote";
    protected static final String PESQUISARPEDIDO = "botaoPesqItemLote";
    protected static final String ALTERARPEDIDO = "botaoAltItemLote";

    protected static final String EXCLUIRITEMPEDIDO = "botaoExcItemLote";
    protected static final String INSERIRITEMPEDIDO = "botaoInsItemLote";
    protected static final String PESQUISARITEMPEDIDO = "botaoPesqItemLote";
    protected static final String ALTERARITEMPEDIDO = "botaoAltItemLote";

    public abstract String[] inserir(String[] parametros, Context context) throws IOException;
    public abstract String[] alterar(String[] parametros, Context context) throws IOException;
    public abstract String[] pesquisar(String[] parametros, Context context) throws IOException;
    public abstract String[] excluir(String[] parametros, Context context) throws IOException;

}
