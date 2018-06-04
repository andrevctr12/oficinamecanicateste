package com.unioeste.oficina.repositories;

import com.unioeste.oficina.model.DDD;
import com.unioeste.oficina.utils.ConexaoBD;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public  class DDDDAO {
    public void CadastraDDD(DDD DDD) throws SQLException {
        Connection c = new ConexaoBD().getConexaoMySQL();
        java.sql.Statement st = c.createStatement();
        st.executeQuery("INSERT INTO ddd (idDDD) VALUES ('" + DDD.getDDD() + "')");

        c.close();
    }

    public DDD BuscaDDD(int DDD) throws SQLException {
        Connection c = new ConexaoBD().getConexaoMySQL();
        java.sql.Statement st = c.createStatement();
        ResultSet r = st.executeQuery("SELECT * FROM ddd WHERE idDDD =" + DDD);

        DDD ddd = null;
        while (r.next()) {
            ddd = new DDD();
            ddd.setDDD(r.getInt("idDDD"));
        }

        c.close();

        return ddd;
    }

    public void AlteraDao(int ddd) throws SQLException {
        Connection c = new ConexaoBD().getConexaoMySQL();
        java.sql.Statement st = c.createStatement();
        st.executeQuery(" SET FOREIGN_KEY_CHECKS=0 ");
        st.executeQuery("UPDATE ddd SET idDDD = " + ddd);
        st.executeQuery(" SET FOREIGN_KEY_CHECKS=1 ");

    }
}
