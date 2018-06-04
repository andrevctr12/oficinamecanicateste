package com.unioeste.oficina.repositories;

import com.unioeste.oficina.model.Rua;
import com.unioeste.oficina.utils.ConexaoBD;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RuaDAO {
    public RuaDAO(){
    }

    public void CadastraRua(Rua rua) throws SQLException {
        Connection c = new ConexaoBD().getConexaoMySQL();
        java.sql.Statement st = c.createStatement();
        st.executeQuery("INSERT INTO rua (nomeRua) VALUES ('"+ rua.getNomeRua() + "');");

        c.close();

    }

    public Rua BuscaRua(String nomeRua) throws SQLException {
        Connection c = new ConexaoBD().getConexaoMySQL();
        java.sql.Statement st = c.createStatement();
        ResultSet r = st.executeQuery("SELECT * FROM rua where nomeRua like '"+ nomeRua +"'");

        Rua rua = null;
        while (r.next())
        {
            rua = new Rua();
            rua.setNomeRua(r.getString("nomeRua"));
            rua.setID(r.getInt("idRua"));
        }

        c.close();

        return rua;
    }
    public Rua BuscaRuaID(int id) throws SQLException {
        Connection c = new ConexaoBD().getConexaoMySQL();
        java.sql.Statement st = c.createStatement();
        ResultSet r = st.executeQuery("SELECT * FROM rua where idRua = '" + id + "'");

        Rua rua = null;
        while (r.next()) {
            rua = new Rua();
            rua.setNomeRua(r.getString("nomeRua"));
            rua.setID(r.getInt("idRua"));
        }

        c.close();

        return rua;
    }


    public void AlteraRuA(Rua rua) throws SQLException {
        Connection c = new ConexaoBD().getConexaoMySQL();
        java.sql.Statement st = c.createStatement();
        st.executeQuery("UPDATE rua SET nomeRua = '"+rua.getNomeRua()+"'" +
                " where idRua = '" + rua.getID() + "'");
        c.close();
    }

}
