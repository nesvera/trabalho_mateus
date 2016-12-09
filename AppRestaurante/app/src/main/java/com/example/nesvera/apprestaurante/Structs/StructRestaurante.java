package com.example.nesvera.apprestaurante.Structs;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nesvera on 08/12/2016.
 */

public class StructRestaurante {

    private List<StructCategoria> cardapio;
    private String pedidos;
    private StructDados dados;

    public StructRestaurante() {
        cardapio = new ArrayList<StructCategoria>();
    }

    public List<StructCategoria> getCardapio() {
        return cardapio;
    }

    public void setCardapio(List<StructCategoria> cardapio) {
        this.cardapio = cardapio;
    }

    public void addCategoria(StructCategoria newCat){
        cardapio.add(newCat);
    }

    public String getPedidos() {
        return pedidos;
    }

    public void setPedidos(String pedidos) {
        this.pedidos = pedidos;
    }

    public StructDados getDados() {
        return dados;
    }

    public void setDados(StructDados dados) {
        this.dados = dados;
    }


}
