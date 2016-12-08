package com.example.nesvera.apprestaurante.Firebase;

import java.util.List;

/**
 * Created by Nesvera on 07/12/2016.
 */

public class DadosRestaurante {

    private String nome;
    private List<String> cardapio;

    public DadosRestaurante() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
