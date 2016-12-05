package com.example.nesvera.apprestaurante.Database;

import com.example.nesvera.apprestaurante.Database.DadosRestaurante;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nesvera on 05/12/2016.
 */

public class DatabaseRestaurante {
    private DadosRestaurante Dados;
    private List<DadosCategoria> Cardapio;   // Xis, Hamburguer, Cerveja
    // pedido


    public DatabaseRestaurante() {
        Dados = new DadosRestaurante();
        Cardapio = new ArrayList<DadosCategoria>();
    }

    public DadosRestaurante getDadosRestaurante() {
        return Dados;
    }

    public void setDados(DadosRestaurante dadosRestaurante) {
        this.Dados = dadosRestaurante;
    }

    public List<DadosCategoria> getCardapioRestaurante() {
        return Cardapio;
    }

    public void addCardapio(DadosCategoria cardapioRestaurante) {
        this.Cardapio.add(cardapioRestaurante);
    }
}
