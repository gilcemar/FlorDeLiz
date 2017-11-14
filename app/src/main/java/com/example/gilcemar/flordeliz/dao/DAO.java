package com.example.gilcemar.flordeliz.dao;

import java.io.IOException;

/**
 * Created by gilcemar on 14/11/17.
 */

public abstract class DAO {
    protected static final String INSERIRCALCADO = "botaoInsCal";
    protected static final String ALTERARCALCADO = "botaoAltCal";
    protected static final String EXCLUIRCALCADO = "botaoExcCal";
    protected static final String PESQUISARCALCADO = "botaoPesqCal";

    protected static final String INSERIRCLIENTE = "botaoInsCli";
    protected static final String ALTERARCLIENTE = "botaoAltCli";
    protected static final String PESQUISARCLIENTE = "botaoPesqCli";
    protected static final String EXCLUIRCLIENTE = "botaoExcCli";

    public abstract String inserir(String[] parametros) throws IOException;
    public abstract String alterar(String[] parametros) throws IOException;
    public abstract String pesquisar(String[] parametros) throws IOException;
    public abstract String excluir(String[] parametros) throws IOException;

}
