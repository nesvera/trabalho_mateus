package com.example.nesvera.apprestaurante.Firebase;

import android.util.Log;

import com.example.nesvera.apprestaurante.Structs.StructRestaurante;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by Nesvera on 07/12/2016.
 */

public class DatabaseAccess {

    private static FirebaseDatabase firebaseDatabase;

    public static DatabaseReference restauranteRef;
    private List<DadosCategoria> cardapioRef;

    private List<DadosCategoria> categoriaList;
    private List<String> categoriaListStr;

    private List<String> restaranteList;

    public DatabaseAccess(FirebaseDatabase db) {
        firebaseDatabase = db;

        categoriaList = new ArrayList<DadosCategoria>();
        categoriaListStr = new ArrayList<String>();
        restaranteList = new ArrayList<String>();

    }

    public void addRestaurante( String restaurante ){
        DatabaseReference teste = firebaseDatabase.getReference(restaurante);
        teste.setValue(restaurante);
    }

    public void setRestaurante( String restaurante ){
        restauranteRef = firebaseDatabase.getReference(restaurante);
    }

    public void addCategoria( DadosCategoria categoria ){
        // Adiciona uma categoria de produto dentro do "Cardapio"
        restauranteRef.child("Cardapio").child(categoria.getNome()).setValue(categoria);
    }

    public List<DadosCategoria> getCategoriaList(){

        // Limpa as listas auxiliares globais
        categoriaList = null;
        categoriaList = new ArrayList<DadosCategoria>();

        categoriaListStr = null;
        categoriaListStr = new ArrayList<String>();

        // Seta a referencia dentro da "pasta" Cardapio
        final DatabaseReference cardapio = restauranteRef.child("Cardapio/");

        // Le todas categorias de produtos dentro dessa pasta
        cardapio.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                    // Le posicao da lista que veio do firebase
                    DadosCategoria temp = postSnapshot.getValue(DadosCategoria.class);

                    if (temp != null) {
                        handlLeituraCategoria(temp);

                        //System.out.println("############# " + temp.getNome());

                    } else {
                        System.out.println("Algo aconteceu");
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", databaseError.toException());
            }
        });

        return categoriaList;
    }

    public List<String> getCategoriaListStr(){

        // Limpa as listas auxiliares globais
        categoriaList = null;
        categoriaList = new ArrayList<DadosCategoria>();

        categoriaListStr = null;
        categoriaListStr = new ArrayList<String>();

        // Seta a referencia dentro da "pasta" Cardapio
        final DatabaseReference cardapio = restauranteRef.child("Cardapio/");

        // Le todas categorias de produtos dentro dessa pasta
        cardapio.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                    // Le posicao da lista que veio do firebase
                    DadosCategoria temp = postSnapshot.getValue(DadosCategoria.class);

                    if (temp != null) {
                        handlLeituraCategoria(temp);

                        //System.out.println("############# " + temp.getNome());

                    } else {
                        System.out.println("Algo aconteceu");
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", databaseError.toException());
            }
        });

        return categoriaListStr;
    }

    public List<String> getRestauranteList(){

        // Limpa as listas auxiliares globais
        categoriaList = null;
        categoriaList = new ArrayList<DadosCategoria>();

        categoriaListStr = null;
        categoriaListStr = new ArrayList<String>();

        restaranteList.clear();

        // Seta a referencia dentro da "pasta" Cardapio
        final DatabaseReference cardapio = firebaseDatabase.getReference();

        // Le todas categorias de produtos dentro dessa pasta
        cardapio.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                    // Le posicao da lista que veio do firebase
                    StructRestaurante temp = postSnapshot.getValue(StructRestaurante.class);

                    if (temp != null) {
                        handlLeituraRestaurante(temp);

                        System.out.println("############# " + temp.getDados().getNome());

                    } else {
                        System.out.println("Algo aconteceu");
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", databaseError.toException());
            }
        });

        return restaranteList;
    }

    public void handlLeituraCategoria( DadosCategoria tmp ){
        // Adiciona elementos a um vetor
        categoriaList.add(tmp);
        categoriaListStr.add(tmp.getNome());
    }

    public void handlLeituraRestaurante( StructRestaurante tmp ){
        // Adiciona elementos a um vetor
        restaranteList.add(tmp.getDados().getNome());
    }


    public DatabaseReference getRestauranteRef() {
        return restauranteRef;
    }

    public void setRestauranteRef(DatabaseReference restauranteRef) {
        this.restauranteRef = restauranteRef;
    }

    public void addPedido(DadosPedido pedido ){
        restauranteRef.child("Pedidos").push().setValue(pedido);

    }

    public void getPedidoList(){

    }

    public void addItem( String categoria, DadosItem item ){
        // Adiciona um elementos na lista "categoria", criando um elemento com o nome do item e colocando os dados dentro
        restauranteRef.child(categoria).child(item.getNome()).setValue(item);
    }

    public List<DadosPedido> getPedidos(){



        return null;
    }



    public void setupCardapioListener(){
        DatabaseReference cardapio = firebaseDatabase.getReference("Cardapio/");
        cardapio.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.e("Count " , ""+dataSnapshot.getChildrenCount());

                for(DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    DadosCategoria temp = postSnapshot.getValue(DadosCategoria.class);

                    if (temp != null) {
                        System.out.println("############# " + temp.getNome());
                        //LatLng latLng = new LatLng(enemy.getLat(), enemy.getLng());

                        //handleNewEnemy(latLng, enemy.getName());

                    } else {
                        System.out.println("Algo aconteceu");
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    /*
    public void setupDBListener(){
        // Read from the database
        //myRef = database.getReference("enemies/");
        //myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //Log.e("Count " , ""+dataSnapshot.getChildrenCount());
                for(DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    Enemy enemy = postSnapshot.getValue(Enemy.class);
                    if (enemy != null){
                        System.out.println("############# " + enemy.getName());
                        LatLng latLng = new LatLng(enemy.getLat(), enemy.getLng());

                        handleNewEnemy(latLng, enemy.getName());
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });
    }
    */

}
