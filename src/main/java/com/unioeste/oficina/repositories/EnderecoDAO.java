package com.unioeste.oficina.repositories;

import com.unioeste.oficina.model.Bairro;
import com.unioeste.oficina.model.Cidade;
import com.unioeste.oficina.model.Endereco;
import com.unioeste.oficina.model.Rua;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EnderecoDAO {    public void CadastraEnd(Endereco endereco) throws SQLException {
    Connection c = new ConexaoBD().getConexaoMySQL();
    java.sql.Statement st = c.createStatement();
    RuaDAO ruaDAO = new RuaDAO();
    if(ruaDAO.BuscaRua(endereco.getRua().getNomeRua()) == null)
    {
        ruaDAO.CadastraRua(endereco.getRua());
    }
    endereco.getRua().setID(ruaDAO.BuscaRua(endereco.getRua().getNomeRua()).getID());

    BairroDAO bairroDAO = new BairroDAO();
    if (bairroDAO.BuscaBairro(endereco.getBairro().getNome()) == null)
    {

        bairroDAO.CadastraBairro(endereco.getBairro());
    }
    endereco.getBairro().setID(bairroDAO.BuscaBairro(endereco.getBairro().getNome()).getID());


    CidadeDAO cidadeDAO = new CidadeDAO();
    if (cidadeDAO.BuscaCidade(endereco.getCidade().getNome()) == null)
    {
        cidadeDAO.CadastraCidade(endereco.getCidade());
    }
    endereco.getCidade().setId(cidadeDAO.BuscaCidade(endereco.getCidade().getNome()).getId());


    st.executeQuery("INSERT INTO endereçocliente (idendereçoCliente, Bairro_idBairro, Rua_idRua, Cidade_idCidade" +
            ", CEP) VALUES " +
            "(NULL, '" + endereco.getBairro().getID() + "', '" + endereco.getRua().getID() + "', '" + endereco.getCidade().getId() + "'," +
            " '" + endereco.getCEP() + "')");


    c.close();

}




    public Endereco BuscaEnderecoid(int ID) throws SQLException {
        Connection c = new ConexaoBD().getConexaoMySQL();
        java.sql.Statement st = c.createStatement();
        ResultSet r = st.executeQuery("SELECT * FROM endereçocliente WHERE idendereçoCliente =  "+ ID+" ");
        Endereco endereco = null;
        while (r.next())
        {
            endereco = new Endereco();

            endereco.setCEP(r.getInt("CEP"));
            endereco.setId(r.getInt("idendereçoCliente"));

            BairroDAO bairroDAO = new BairroDAO();
            Bairro bairro = bairroDAO.BuscaBairroID(r.getInt("Bairro_idBairro"));
            endereco.setBairro(bairro);

            RuaDAO ruaDAO = new RuaDAO();
            Rua rua = ruaDAO.BuscaRuaID(r.getInt("Rua_idRua"));
            endereco.setRua(rua);

            CidadeDAO cidadeDAO = new CidadeDAO();
            Cidade cidade = cidadeDAO.BuscaCidadeID(r.getInt("Cidade_idCidade"));

            endereco.setCidade(cidade);

        }

        c.close();

        return endereco;
    }
    public Endereco BuscaEnderecoByIds(int idRua, int idBairro,int idCidade, int CEP) throws SQLException {
        Connection c = new ConexaoBD().getConexaoMySQL();
        java.sql.Statement st = c.createStatement();
        ResultSet r = st.executeQuery("SELECT * FROM endereçocliente WHERE " +
                "Bairro_idBairro = "+idBairro+" AND Rua_idRua = "+idRua+" AND " +
                "Cidade_idCidade = "+idCidade+" AND CEP = " + CEP);
        Endereco endereco = null;
        while (r.next())
        {
            endereco = new Endereco();

            endereco.setCEP(r.getInt("CEP"));
            endereco.setId(r.getInt("idendereçoCliente"));

            BairroDAO bairroDAO = new BairroDAO();
            Bairro bairro = bairroDAO.BuscaBairroID(r.getInt("Bairro_idBairro"));
            endereco.setBairro(bairro);

            RuaDAO ruaDAO = new RuaDAO();
            Rua rua = ruaDAO.BuscaRuaID(r.getInt("Rua_idRua"));
            endereco.setRua(rua);

            CidadeDAO cidadeDAO = new CidadeDAO();
            Cidade cidade = cidadeDAO.BuscaCidadeID(r.getInt("Cidade_idCidade"));

            endereco.setCidade(cidade);

            endereco.setId(r.getInt("idendereçoCliente"));


        }

        c.close();

        return endereco;
    }


    public void AlteraEndereco(Endereco endereco) throws SQLException {
        Connection c = new ConexaoBD().getConexaoMySQL();
        java.sql.Statement st = c.createStatement();

        RuaDAO ruaDAO = new RuaDAO();
        ruaDAO.AlteraRuA(endereco.getRua());

        CidadeDAO cidadeDAO = new CidadeDAO();
        cidadeDAO.AlteraCidade(endereco.getCidade());

        BairroDAO bairroDAO = new BairroDAO();
        bairroDAO.AlteraBairro(endereco.getBairro());






        st.executeQuery("UPDATE endereçocliente SET " +
                "CEP = '"+ endereco.getCEP() +
                "'WHERE idendereçoCliente =  "+ endereco.getId());
        c.close();

    }
}
