package com.example.nesvera.apprestaurante.Database;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nesvera on 05/12/2016.
 */

public class DadosCategoria {
    private String nome;
    private List<DadosItem> produto;

    public DadosCategoria() {
        this.produto = new ArrayList<DadosItem>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<DadosItem> getCategoria() {
        return produto;
    }

    public void setProduto(List<DadosItem> produtos) {
        this.produto = produtos;
    }
}
