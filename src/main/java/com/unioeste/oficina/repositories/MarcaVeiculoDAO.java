package com.unioeste.oficina.repositories;

import com.unioeste.oficina.model.MarcaVeiculo;
import com.unioeste.oficina.utils.ConexaoBD;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MarcaVeiculoDAO {

    public void CadastraMarcaVeiculo(MarcaVeiculo marcaVeiculo) throws SQLException {
        Connection c = new ConexaoBD().getConexaoMySQL();
        java.sql.Statement st = c.createStatement();

        MarcaVeiculoDAO marcaVeiculoDAO = new MarcaVeiculoDAO();
        if (marcaVeiculoDAO.BuscaMarcaVeiculo(marcaVeiculo.getMarca()) == null) {
            st.executeQuery("INSERT INTO `marcaveiculo` (`idMarcaVeiculo`, `nomeMarca`)" +
                    " VALUES (NULL, '"+ marcaVeiculo.getMarca() +"');");


        }
        c.close();
    }


    public MarcaVeiculo BuscaMarcaVeiculo(String marca) throws SQLException {
        Connection c = new ConexaoBD().getConexaoMySQL();
        java.sql.Statement st = c.createStatement();
        ResultSet r = st.executeQuery("SELECT * FROM `marcaveiculo` WHERE `nomeMarca` LIKE '" + marca +"'");
        MarcaVeiculo marcaVeiculo = null;
        while (r.next())
        {
            marcaVeiculo = new MarcaVeiculo();
            marcaVeiculo.setId(r.getInt("idMarcaVeiculo"));
            marcaVeiculo.setMarca(r.getString("nomeMarca"));

        }
        c.close();
        return marcaVeiculo;
    }
    public MarcaVeiculo BuscaMarcaVeiculoID(int id) throws SQLException {
        Connection c = new ConexaoBD().getConexaoMySQL();
        java.sql.Statement st = c.createStatement();
        ResultSet r = st.executeQuery("SELECT * FROM `marcaveiculo` WHERE `idMarcaVeiculo` LIKE '" + id +"'");
        MarcaVeiculo marcaVeiculo = null;
        while (r.next())
        {
            marcaVeiculo = new MarcaVeiculo();
            marcaVeiculo.setId(r.getInt("idMarcaVeiculo"));
            marcaVeiculo.setMarca(r.getString("nomeMarca"));

        }
        c.close();
        return marcaVeiculo;
    }
}
