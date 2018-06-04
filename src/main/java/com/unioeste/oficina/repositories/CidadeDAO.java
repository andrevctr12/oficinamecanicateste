package com.unioeste.oficina.repositories;

import com.unioeste.oficina.model.Cidade;
import com.unioeste.oficina.model.UF;
import com.unioeste.oficina.utils.ConexaoBD;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CidadeDAO {
    public void CadastraCidade(Cidade cidade) throws SQLException {
        UFDAO ufdao = new UFDAO();
        UF aux = ufdao.BuscaUFSigla(cidade.getUF().getUFsigla());


        if (aux == null)
        {
            ufdao.CadastraUF(cidade.getUF());
        }
        Connection c = new ConexaoBD().getConexaoMySQL();
        java.sql.Statement st = c.createStatement();
        st.executeQuery("INSERT INTO cidade (nomeCidade, siglaCidade, UF_siglaUF) VALUES ('"
                + cidade.getNome() + "','" + cidade.getSiglaCidade() + "','" + cidade.getUF().getUFsigla() + "');");

        c.close();

    }

    public Cidade BuscaCidade(String nome) throws SQLException {
        Connection c = new ConexaoBD().getConexaoMySQL();
        java.sql.Statement st = c.createStatement();
        ResultSet r = st.executeQuery("SELECT * FROM cidade WHERE nomeCidade LIKE'" + nome + "'");

        Cidade cidade = null;
        while (r.next())
        {
            UFDAO ufdao = new UFDAO();
            cidade = new Cidade();
            cidade.setId(r.getInt("idCidade"));

            UF uf = ufdao.BuscaUFSigla(r.getString("UF_siglaUF"));
            cidade.setUF(uf);
            cidade.setNome(r.getString("nomeCidade"));
            cidade.setSiglaCidade(r.getString("Siglacidade"));
        }

        c.close();
        return cidade;
    }
    public Cidade BuscaCidadeID(int ID) throws SQLException {
        Connection c = new ConexaoBD().getConexaoMySQL();
        java.sql.Statement st = c.createStatement();
        ResultSet r = st.executeQuery("SELECT * FROM cidade WHERE idCidade ='" + ID + "'");

        Cidade cidade = null;
        while (r.next())
        {
            UFDAO ufdao = new UFDAO();
            cidade = new Cidade();
            cidade.setId(r.getInt("idCidade"));
            UF uf = ufdao.BuscaUFSigla(r.getString("UF_siglaUF"));
            cidade.setUF(uf);
            cidade.setNome(r.getString("nomeCidade"));
            cidade.setSiglaCidade(r.getString("Siglacidade"));
        }

        c.close();
        return cidade;
    }

    public void AlteraCidade(Cidade cidade) throws SQLException {
        Connection c = new ConexaoBD().getConexaoMySQL();
        java.sql.Statement st = c.createStatement();

        UFDAO ufdao = new UFDAO();
        UF aux = ufdao.BuscaUFSigla(cidade.getUF().getUFsigla());


        if (aux == null)
        {
            ufdao.CadastraUF(cidade.getUF());
        }

        st.executeQuery("UPDATE cidade  SET  nomeCidade = '" +cidade.getNome() +
        "', siglaCidade = '" + cidade.getSiglaCidade() + "', UF_siglaUF = '"+cidade.getUF().getUFsigla()+"'");
        c.close();
    }
}
