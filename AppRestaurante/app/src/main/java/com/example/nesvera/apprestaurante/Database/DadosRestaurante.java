package com.example.nesvera.apprestaurante.Database;

import com.example.nesvera.apprestaurante.Database.DadosItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nesvera on 05/12/2016.
 */

public class DadosRestaurante {

    private String nome;
    private String descricao;


    public DadosRestaurante() {
        nome = new String();
        descricao = new String();
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

}
