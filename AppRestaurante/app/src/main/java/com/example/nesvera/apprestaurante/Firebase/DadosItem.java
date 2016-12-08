package com.example.nesvera.apprestaurante.Firebase;

/**
 * Created by Nesvera on 07/12/2016.
 */

public class DadosItem {

    private String nome;
    private String descricao;
    private double valor;

    public DadosItem() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
