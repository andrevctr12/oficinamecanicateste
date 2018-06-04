package com.unioeste.oficina.utils;

import com.unioeste.oficina.model.Rua;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBD {
    public static String status = "N�o conectou...";

    private Connection connection;

    public Connection getConexaoMySQL() {


        try {

            // Carregando o JDBC Driver padr�o

            String driverName = "org.mariadb.jdbc.Driver";

            Class.forName(driverName);

            String url = "jdbc:mariadb://c9cujduvu830eexs.cbetxkdyhwsb.us-east-1.rds.amazonaws.com:3306/eksp46ld8nijkrli";
            String user = "phryiu84huu99igi";
            String password = "sff4p0egqwaypaz4";




//            String url = "jdbc:mariadb://localhost:3306/oficina?";
//            String user = "root";
//            String password = "";
            connection = DriverManager.getConnection(url, user, password);

            //connection = DriverManager.getConnection("mariadb://phryiu84huu99igi:sff4p0egqwaypaz4@c9cujduvu830eexs.cbetxkdyhwsb.us-east-1.rds.amazonaws.com:3306/eksp46ld8nijkrli");




            if (connection != null) {

                status = ("STATUS--->Conectado com sucesso!");

            } else {

                status = ("STATUS--->N�o foi possivel realizar conex�o");

            }


            return connection;


        } catch (ClassNotFoundException e) {  //Driver n�o encontrado

            System.out.println("O driver expecificado nao foi encontrado.");

            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Nao foi possivel conectar ao Banco de Dados.");

            return null;

        }
    }

    public String statusConection() {

        return status;

    }

    public boolean FecharConexao() {

        try {

            this.connection.close();

            return true;

        } catch (SQLException e) {

            return false;

        }

    }


}
