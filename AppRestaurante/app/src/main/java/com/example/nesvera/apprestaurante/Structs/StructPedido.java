package com.example.nesvera.apprestaurante.Structs;

/**
 * Created by Nesvera on 08/12/2016.
 */

public class StructPedido {

    private int mesa;
    private String comida;
    private String anotacao;
    private boolean status;

    public StructPedido() {
    }

    public int getMesa() {
        return mesa;
    }

    public void setMesa(int mesa) {
        this.mesa = mesa;
    }

    public String getComida() {
        return comida;
    }

    public void setComida(String comida) {
        this.comida = comida;
    }

    public String getAnotacao() {
        return anotacao;
    }

    public void setAnotacao(String anotacao) {
        this.anotacao = anotacao;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
