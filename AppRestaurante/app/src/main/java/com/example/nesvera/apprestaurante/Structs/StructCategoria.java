package com.example.nesvera.apprestaurante.Structs;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nesvera on 08/12/2016.
 */

public class StructCategoria {

    private String nome;
    private List<StructItem> items;

    public StructCategoria() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<StructItem> getItems() {
        return items;
    }

    public int  getListSize(){
        return items.size();
    }

    public void addItem(StructItem item){
        items.add(item);
    }

    public void setItems(List<StructItem> items) {
        this.items = items;
    }
}
