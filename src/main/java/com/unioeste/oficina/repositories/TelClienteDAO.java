package com.unioeste.oficina.repositories;


import com.unioeste.oficina.model.DDD;
import com.unioeste.oficina.model.TelCliente;
import com.unioeste.oficina.utils.ConexaoBD;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TelClienteDAO {
    public void CadastraTelCliente(TelCliente telCliente) throws SQLException {
        DDDDAO ddddao = new DDDDAO();
        DDD ddd = ddddao.BuscaDDD(telCliente.getDDD());

        if (ddd == null)
        {
            ddd = new DDD(telCliente.getDDD());
            ddddao.CadastraDDD(ddd);
        }

        Connection c = new ConexaoBD().getConexaoMySQL();
        java.sql.Statement st = c.createStatement();
        st.executeQuery("INSERT INTO telefone (Telefoneid, Telefone, DDD_idDDD)" +
                " VALUES (NULL, '"+telCliente.getTelefone()+"', '"+telCliente.getDDD()+"')");

        c.close();
    }

    public TelCliente BuscaTelClienteID(int id) throws SQLException {
        Connection c = new ConexaoBD().getConexaoMySQL();
        java.sql.Statement st = c.createStatement();
        ResultSet r = st.executeQuery("SELECT * FROM telefone WHERE Telefoneid = " + id);

        TelCliente telCliente = null;
        while (r.next())
        {
            telCliente = new TelCliente();
            telCliente.setID(r.getInt("Telefoneid"));
            telCliente.setTelefone(r.getString("Telefone"));
            telCliente.setDDD(r.getInt("DDD_idDDD"));
        }

        c.close();

        return telCliente;
    }

    public TelCliente BuscaTelCliente(String tel) throws SQLException {
        Connection c = new ConexaoBD().getConexaoMySQL();
        java.sql.Statement st = c.createStatement();
        ResultSet r = st.executeQuery("SELECT * FROM telefone WHERE Telefone like '" + tel+ "'");

        TelCliente telCliente = null;
        while (r.next())
        {
            telCliente = new TelCliente();
            telCliente.setID(r.getInt("Telefoneid"));
            telCliente.setTelefone(r.getString("Telefone"));
            telCliente.setDDD(r.getInt("DDD_idDDD"));

        }

        c.close();

        return telCliente;
    }

    public void AlteraTel(TelCliente telCliente) throws SQLException {
        Connection c = new ConexaoBD().getConexaoMySQL();
        DDDDAO ddddao = new DDDDAO();
        TelClienteDAO telClienteDAO = new TelClienteDAO();

        if (ddddao.BuscaDDD(telCliente.getDDD()) == null)
        {

            DDD ddd = new DDD(telCliente.getDDD());
            ddddao.CadastraDDD(ddd);
        }
        if (telClienteDAO.BuscaTelCliente(telCliente.getTelefone()) == null)
        {
            telClienteDAO.CadastraTelCliente(telCliente);

            telCliente.setID(BuscaTelCliente(telCliente.getTelefone()).getID());
        }

        c.close();
    }
}
