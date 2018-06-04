package com.unioeste.oficina.repositories;

import com.unioeste.oficina.model.ModeloVeiculo;
import com.unioeste.oficina.utils.ConexaoBD;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ModeloVeiculoDAO {
    public void CadastraModeloVeiculo(ModeloVeiculo modeloVeiculo) throws SQLException {
        Connection c = new ConexaoBD().getConexaoMySQL();
        java.sql.Statement st = c.createStatement();

        MarcaVeiculoDAO marcaVeiculoDAO = new MarcaVeiculoDAO();
        if (marcaVeiculoDAO.BuscaMarcaVeiculo(modeloVeiculo.getMarca().getMarca()) == null)
        {
            marcaVeiculoDAO.CadastraMarcaVeiculo(modeloVeiculo.getMarca());
            modeloVeiculo.getMarca().setId(marcaVeiculoDAO.BuscaMarcaVeiculo(modeloVeiculo.getMarca().getMarca()).getId());
            st.executeQuery("INSERT INTO `modeloveiculo` (`idModeloVeiculo`, `MarcaVeiculo_idMarcaVeiculo`, `marcaModelo`) " +
                    "VALUES (NULL, '"+modeloVeiculo.getMarca().getId()+"', '"+modeloVeiculo.getModelo()+"');");


        }else {

            modeloVeiculo.getMarca().setId(marcaVeiculoDAO.BuscaMarcaVeiculo(modeloVeiculo.getMarca().getMarca()).getId());
            st.executeQuery("INSERT INTO `modeloveiculo` (`idModeloVeiculo`, `MarcaVeiculo_idMarcaVeiculo`, `marcaModelo`) " +
                    "VALUES (NULL, '" + modeloVeiculo.getMarca().getId() + "', '" + modeloVeiculo.getModelo() + "');");
        }
        c.close();
    }
    public ModeloVeiculo BuscaModeloVeiculo(String modelo) throws SQLException {
        Connection c = new ConexaoBD().getConexaoMySQL();
        java.sql.Statement st = c.createStatement();
        ResultSet r = st.executeQuery("SELECT * FROM `modeloveiculo` WHERE `marcaModelo` LIKE '"+ modelo +"'");
        ModeloVeiculo modeloVeiculo = null;
        while (r.next())
        {
            modeloVeiculo = new ModeloVeiculo();
            modeloVeiculo.setId(r.getInt("idModeloVeiculo"));
            MarcaVeiculoDAO marcaVeiculoDAO = new MarcaVeiculoDAO();

            modeloVeiculo.setMarca(marcaVeiculoDAO.BuscaMarcaVeiculoID(r.getInt("MarcaVeiculo_idMarcaVeiculo")));
            modeloVeiculo.setModelo(r.getString("marcaModelo"));
        }
        c.close();
        return modeloVeiculo;
    }
    public ModeloVeiculo BuscaModeloVeiculoID(int id) throws SQLException {
        Connection c = new ConexaoBD().getConexaoMySQL();
        java.sql.Statement st = c.createStatement();
        ResultSet r = st.executeQuery("SELECT * FROM `modeloveiculo` WHERE `idModeloVeiculo` ="+ id );
        ModeloVeiculo modeloVeiculo = null;
        while (r.next())
        {
            modeloVeiculo = new ModeloVeiculo();
            modeloVeiculo.setId(r.getInt("idModeloVeiculo"));

            MarcaVeiculoDAO marcaVeiculoDAO = new MarcaVeiculoDAO();


            modeloVeiculo.setMarca(marcaVeiculoDAO.BuscaMarcaVeiculoID(r.getInt("MarcaVeiculo_idMarcaVeiculo")));
            modeloVeiculo.setModelo(r.getString("marcaModelo"));
        }
        c.close();
        return modeloVeiculo;
    }

}
