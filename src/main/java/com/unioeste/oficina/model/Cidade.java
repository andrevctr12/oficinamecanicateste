package com.unioeste.oficina.model;

public class Cidade {
    private int id;
    private String nome;
    private UF UF;
    private String SiglaCidade;

    @Override
    public String toString() {
        return "Cidade{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", UF=" + UF +
                ", SiglaCidade='" + SiglaCidade + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public com.unioeste.oficina.model.UF getUF() {
        return UF;
    }

    public void setUF(com.unioeste.oficina.model.UF UF) {
        this.UF = UF;
    }

    public String getSiglaCidade() {
        return SiglaCidade;
    }

    public void setSiglaCidade(String siglaCidade) {
        SiglaCidade = siglaCidade;
    }
}
