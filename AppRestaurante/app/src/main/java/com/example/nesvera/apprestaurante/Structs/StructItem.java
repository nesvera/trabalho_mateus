package com.example.nesvera.apprestaurante.Structs;

/**
 * Created by Nesvera on 08/12/2016.
 */

public class StructItem {
    private String nome;
    private String descricao;
    private Double valor;

    public StructItem() {
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

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
}
