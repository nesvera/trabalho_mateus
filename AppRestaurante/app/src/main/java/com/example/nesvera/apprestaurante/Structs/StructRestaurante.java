package com.example.nesvera.apprestaurante.Structs;

import java.util.List;

/**
 * Created by Nesvera on 08/12/2016.
 */

public class StructRestaurante {

    private String cardapio;
    private String pedidos;
    private StructDados dados;

    public StructRestaurante() {
    }

    public String getCardapio() {
        return cardapio;
    }

    public void setCardapio(String cardapio) {
        this.cardapio = cardapio;
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
