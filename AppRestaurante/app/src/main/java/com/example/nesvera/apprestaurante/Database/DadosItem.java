package com.example.nesvera.apprestaurante.Database;

/**
 * Created by Nesvera on 05/12/2016.
 */

public class DadosItem {
    private String nome;
    private String descricao;
    private Float valor;

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

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }
}
