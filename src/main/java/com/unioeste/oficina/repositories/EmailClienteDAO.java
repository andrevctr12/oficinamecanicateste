package com.unioeste.oficina.repositories;



import com.unioeste.oficina.model.Cliente;
import com.unioeste.oficina.model.Email;
import com.unioeste.oficina.utils.ConexaoBD;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmailClienteDAO {
    public void CadastraEmail(String email, int idCliente) throws SQLException {
        Connection c = new ConexaoBD().getConexaoMySQL();
        java.sql.Statement st = c.createStatement();
        ResultSet r = st.executeQuery("INSERT INTO emailcliente " +
                "(idemailCliente, emailCliente, Cliente_idCliente) " +
                "VALUES (NULL, '" + email + "', '" + idCliente + "');");

        c.close();

    }
    public String BuscaEmail(int idCliente) throws SQLException {
        Connection c = new ConexaoBD().getConexaoMySQL();
        java.sql.Statement st = c.createStatement();
        ResultSet r = st.executeQuery("SELECT * FROM emailcliente WHERE Cliente_idCliente = " + idCliente );

        String email= null;
        while (r.next()){

            email = (r.getString("emailCliente"));


        }

        c.close();

        return email;
    }
    public int Buscaid(int idCliente) throws SQLException {
        Connection c = new ConexaoBD().getConexaoMySQL();
        java.sql.Statement st = c.createStatement();
        ResultSet r = st.executeQuery("SELECT * FROM emailcliente WHERE Cliente_idCliente = " + idCliente );

        int ID = 0;
        while (r.next()){

            ID = (r.getInt("idemailCliente"));


        }

        c.close();

        return ID;
    }

    public Email BuscaEmailbyString(String email) throws SQLException {
        Connection c = new ConexaoBD().getConexaoMySQL();
        java.sql.Statement st = c.createStatement();
        ResultSet r = st.executeQuery("SELECT * FROM emailcliente WHERE emailCliente like '" + email + "'");

        while (r.next()){

            Email email1 = new Email();
            return email1;


        }
        return null;
    }



    public void BuscaEmailLista(ArrayList<Cliente> listaCliente) throws SQLException {
        EmailClienteDAO emailClienteDAO = new EmailClienteDAO();
        for (Cliente cliente : listaCliente) {
            cliente.setEmail(emailClienteDAO.BuscaEmail(cliente.getID()));
        }
    }

    public void  AlteraEmail(int id, String email) throws SQLException {
        EmailClienteDAO emailClienteDAO = new EmailClienteDAO();

        System.out.println("aaa");
        if(emailClienteDAO.BuscaEmailbyString(email) == null)
        {
            System.out.println("a");
            emailClienteDAO.CadastraEmail(email,id);

        }


    }
}

