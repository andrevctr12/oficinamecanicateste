package com.unioeste.oficina.model;


public class Endereco {
    private int id;
    private Bairro Bairro;
    private Rua Rua;
    private Cidade Cidade;
    private int CEP;


    @Override
    public String toString() {
        return "Endereco{" +
                "id=" + id +
                ", Bairro=" + Bairro +
                ", Rua=" + Rua +
                ", Cidade=" + Cidade +
                ", CEP=" + CEP +

                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public com.unioeste.oficina.model.Bairro getBairro() {
        return Bairro;
    }

    public void setBairro(com.unioeste.oficina.model.Bairro bairro) {
        Bairro = bairro;
    }

    public com.unioeste.oficina.model.Rua getRua() {
        return Rua;
    }

    public void setRua(com.unioeste.oficina.model.Rua rua) {
        Rua = rua;
    }

    public com.unioeste.oficina.model.Cidade getCidade() {
        return Cidade;
    }

    public void setCidade(com.unioeste.oficina.model.Cidade cidade) {
        Cidade = cidade;
    }

    public int getCEP() {
        return CEP;
    }

    public void setCEP(int CEP) {
        this.CEP = CEP;
    }



}