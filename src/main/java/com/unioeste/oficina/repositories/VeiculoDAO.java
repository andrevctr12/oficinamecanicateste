package com.unioeste.oficina.repositories;

import com.unioeste.oficina.model.Veiculo;
import com.unioeste.oficina.utils.ConexaoBD;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class VeiculoDAO {
    public void CadastraVeiculo(Veiculo veiculo) throws SQLException {
        Connection c = new ConexaoBD().getConexaoMySQL();
        java.sql.Statement st = c.createStatement();

        ModeloVeiculoDAO modeloVeiculoDAO = new ModeloVeiculoDAO();
        if (modeloVeiculoDAO.BuscaModeloVeiculo(veiculo.getModeloVeiculo().getModelo()) == null) {
            modeloVeiculoDAO.CadastraModeloVeiculo(veiculo.getModeloVeiculo());
            veiculo.getModeloVeiculo().setId(modeloVeiculoDAO.BuscaModeloVeiculo(veiculo.getModeloVeiculo().getModelo()).getId());
            st.executeQuery("INSERT INTO `veiculo` (`idVeiculo`, `nomeVeiculo`," +
                    " `ModeloVeiculo_idModeloVeiculo`, `placaVeiculo`, `kmVeiculo`,`idCliente` )" +
                    " VALUES (NULL, '" + veiculo.getNomeVeiculo() + "', '" + veiculo.getModeloVeiculo().getId() + "', '" + veiculo.getPlaca() +
                    "', '" + veiculo.getKm() + "', '" + veiculo.getIdCliente() + "');");
        } else {
            veiculo.getModeloVeiculo().setId(modeloVeiculoDAO.BuscaModeloVeiculo(veiculo.getModeloVeiculo().getModelo()).getId());
            st.executeQuery("INSERT INTO `veiculo` (`idVeiculo`, `nomeVeiculo`," +
                    " `ModeloVeiculo_idModeloVeiculo`, `placaVeiculo`, `kmVeiculo`,`idCliente` )" +
                    " VALUES (NULL, '" + veiculo.getNomeVeiculo() + "', '" + veiculo.getModeloVeiculo().getId() + "', '" + veiculo.getPlaca() +
                    "', '" + veiculo.getKm() + "', '" + veiculo.getIdCliente() + "');");
        }
        c.close();
    }

    public Veiculo BuscaVeiculo(String nome) throws SQLException {
        Connection c = new ConexaoBD().getConexaoMySQL();
        java.sql.Statement st = c.createStatement();
        ResultSet r = st.executeQuery("SELECT * FROM `veiculo` WHERE `nomeVeiculo` LIKE '" + nome +"'");
        Veiculo veiculo = null;
        while (r.next())
        {
            veiculo = new Veiculo();
            veiculo.setId(r.getInt("idVeiculo"));
            veiculo.setNomeVeiculo(r.getString("nomeVeiculo"));
            ModeloVeiculoDAO modeloVeiculoDAO = new ModeloVeiculoDAO();
            veiculo.setModeloVeiculo(modeloVeiculoDAO.BuscaModeloVeiculoID(r.getInt("ModeloVeiculo_idModeloVeiculo")));
            veiculo.setPlaca(r.getString("placaVeiculo"));
            veiculo.setKm(r.getInt("kmVeiculo"));
            veiculo.setIdCliente(r.getInt("idCliente"));
        }
        c.close();
        return veiculo;
    }

    public Veiculo BuscaVeiculoID(int ID) throws SQLException {
        Connection c = new ConexaoBD().getConexaoMySQL();
        java.sql.Statement st = c.createStatement();
        ResultSet r = st.executeQuery("SELECT * FROM `veiculo` WHERE `idVeiculo` = " + ID );
        Veiculo veiculo = null;
        while (r.next())
        {
            veiculo = new Veiculo();
            veiculo.setId(r.getInt("idVeiculo"));
            veiculo.setNomeVeiculo(r.getString("nomeVeiculo"));
            ModeloVeiculoDAO modeloVeiculoDAO = new ModeloVeiculoDAO();
            veiculo.setModeloVeiculo(modeloVeiculoDAO.BuscaModeloVeiculoID(r.getInt("ModeloVeiculo_idModeloVeiculo")));
            veiculo.setPlaca(r.getString("placaVeiculo"));
            veiculo.setKm(r.getInt("kmVeiculo"));
            veiculo.setIdCliente(r.getInt("idCliente"));
        }
        c.close();
        return veiculo;
    }

    public Veiculo BuscaVeiculoByIdCliente(int id) throws SQLException {
        Connection c = new ConexaoBD().getConexaoMySQL();
        java.sql.Statement st = c.createStatement();
        ResultSet r = st.executeQuery("SELECT * FROM `veiculo` WHERE `idCliente` = " + id );
        Veiculo veiculo = null;
        while (r.next())
        {
            veiculo = new Veiculo();
            veiculo.setId(r.getInt("idVeiculo"));
            veiculo.setNomeVeiculo(r.getString("nomeVeiculo"));
            ModeloVeiculoDAO modeloVeiculoDAO = new ModeloVeiculoDAO();
            veiculo.setModeloVeiculo(modeloVeiculoDAO.BuscaModeloVeiculoID(r.getInt("ModeloVeiculo_idModeloVeiculo")));
            veiculo.setPlaca(r.getString("placaVeiculo"));
            veiculo.setKm(r.getInt("kmVeiculo"));
            veiculo.setIdCliente(r.getInt("idCliente"));
        }
        c.close();
        return veiculo;
    }


    public ArrayList<Veiculo> BuscaVeiculoList() throws SQLException {
        ArrayList<Veiculo> list = new ArrayList<>();
        Connection c = new ConexaoBD().getConexaoMySQL();
        java.sql.Statement st = c.createStatement();
        ResultSet r = st.executeQuery("SELECT * FROM `veiculo`");
        Veiculo veiculo = null;
        while (r.next()){
            veiculo = new Veiculo();
            veiculo.setId(r.getInt("idVeiculo"));
            veiculo.setNomeVeiculo(r.getString("nomeVeiculo"));
            ModeloVeiculoDAO modeloVeiculoDAO = new ModeloVeiculoDAO();
            veiculo.setModeloVeiculo(modeloVeiculoDAO.BuscaModeloVeiculoID(r.getInt("ModeloVeiculo_idModeloVeiculo")));
            veiculo.setPlaca(r.getString("placaVeiculo"));
            veiculo.setKm(r.getInt("kmVeiculo"));
            veiculo.setIdCliente(r.getInt("idCliente"));

            list.add(veiculo);
        }

        return list;
    }


    public void AlteraVeiculo(Veiculo veiculo) throws SQLException {
        Connection c = new ConexaoBD().getConexaoMySQL();
        java.sql.Statement st = c.createStatement();
        ModeloVeiculoDAO modeloVeiculoDAO = new ModeloVeiculoDAO();
        MarcaVeiculoDAO marcaVeiculoDAO = new MarcaVeiculoDAO();

        if (((modeloVeiculoDAO.BuscaModeloVeiculo(veiculo.getModeloVeiculo().getModelo())) == null)
            ||(marcaVeiculoDAO.BuscaMarcaVeiculo(veiculo.getModeloVeiculo().getMarca().getMarca()) == null)) {
                modeloVeiculoDAO.CadastraModeloVeiculo(veiculo.getModeloVeiculo());
                veiculo.getModeloVeiculo().setId(modeloVeiculoDAO.BuscaModeloVeiculo(veiculo.getModeloVeiculo().getModelo()).getId());
            }



        st.executeQuery("UPDATE veiculo SET nomeVeiculo = '"+ veiculo.getNomeVeiculo()+"',"
        +" placaVeiculo = '" + veiculo.getPlaca() + "'," + " kmVeiculo = " + veiculo.getKm() +", ModeloVeiculo_idModeloVeiculo ="
        + veiculo.getModeloVeiculo().getId()
        +" WHERE idCliente = " + veiculo.getIdCliente());

        c.close();
    }
}
