package com.example.nesvera.apprestaurante.Database;

import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nesvera on 05/12/2016.
 */

public class CardapioRestaurante {
    private List<DadosCategoria> categorias;   // Xis, Hamburguer, Cerveja


    public CardapioRestaurante() {
        categorias = new ArrayList<DadosCategoria>();
    }

    public void addCategoria(DadosCategoria produto){
        categorias.add(produto);
    }

    // Obtem toda lista de categorias, e dentro de cada categoria os produtos
    public List<DadosCategoria> getCategorias() {
        return categorias;
    }

}
