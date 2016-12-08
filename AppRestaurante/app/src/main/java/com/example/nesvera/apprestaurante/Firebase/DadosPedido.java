package com.example.nesvera.apprestaurante.Firebase;

/**
 * Created by Nesvera on 06/12/2016.
 */

public class DadosPedido {

    private String mesa;
    private String item;
    private String pessoa;
    private String descricao;

    public DadosPedido() {
    }

    public String getMesa() {
        return mesa;
    }

    public void setMesa(String mesa) {
        this.mesa = mesa;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getPessoa() {
        return pessoa;
    }

    public void setPessoa(String pessoa) {
        this.pessoa = pessoa;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
