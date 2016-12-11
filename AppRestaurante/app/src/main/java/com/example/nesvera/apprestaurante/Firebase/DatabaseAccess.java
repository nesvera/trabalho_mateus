package com.example.nesvera.apprestaurante.Firebase;

import android.util.Log;

import com.example.nesvera.apprestaurante.InitActivity;
import com.example.nesvera.apprestaurante.Structs.StructCategoria;
import com.example.nesvera.apprestaurante.Structs.StructDados;
import com.example.nesvera.apprestaurante.Structs.StructItem;
import com.example.nesvera.apprestaurante.Structs.StructPedido;
import com.example.nesvera.apprestaurante.Structs.StructRestaurante;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.Struct;
import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by Nesvera on 07/12/2016.
 */

public class DatabaseAccess {

    private static FirebaseDatabase firebaseDatabase;
    private DatabaseReference teste;

    public static DatabaseReference restauranteRef;
    private List<StructCategoria> cardapioRef;

    private List<StructCategoria> categoriaList;
    public static List<String> categoriaListStr;

    public static List<String> restaranteList;
    public static boolean restauranteListOk;

    // Construtor recebe referencias da pasta "home" do app
    public DatabaseAccess(FirebaseDatabase db) {
        firebaseDatabase = db;

        categoriaList = new ArrayList<StructCategoria>();
        categoriaListStr = new ArrayList<String>();
        restaranteList = new ArrayList<String>();

    }

    /** Adiciona um "diretorio" na pasta principla do app o nome do novo restaurante **/
    public void addRestaurante( String restaurante ){
        DatabaseReference teste = firebaseDatabase.getReference(restaurante);

        teste.setValue(restaurante);
    }

    public void addRestaurante( String nome, String descricao, String endereco ){
        StructRestaurante newRestaurante = new StructRestaurante();
        StructDados newDadosRestaurante = new StructDados();

        newDadosRestaurante.setNome(nome);
        newDadosRestaurante.setDescricao(descricao);
        newDadosRestaurante.setEndereco(endereco);

        newRestaurante.setDados(newDadosRestaurante);

        DatabaseReference dir = firebaseDatabase.getReference(newRestaurante.getDados().getNome());
        dir.setValue(newRestaurante);

    }

    /** Declara o nome do restaurante que as informacoes vao ser retiradas.... seta toda classe para esse restaurante **/
    public void setRestaurante( String restaurante ){
        restauranteRef = firebaseDatabase.getReference(restaurante);
    }

    /**  Obtem a lista de restaurantes do banco de dados, funcao para ver proximidade sera feita no retorno dessa funcao **/
    public void getRestauranteList( final OnGetDataListener listener ){
        listener.onStart();

        // Seta a referencia dentro da "pasta" home onde esta contido todos restaurantes
        final DatabaseReference restauranteDir = firebaseDatabase.getReference();

        // Le todos restaurantes
        restauranteDir.addListenerForSingleValueEvent( new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listener.OnSuccess(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Failed to read value
                listener.onFailed(databaseError);
            }
        });

    }

    /** Adiciona uma categoria de produto dentro do "Cardapio" do restaurante declarado por setRestaurante **/
    public void addCategoria( StructCategoria categoria ){

        restauranteRef.child("Cardapio").child(categoria.getNome()).setValue(categoria);
    }

    /** Retorna os dados contidos dentro da pasta Categoria.... produtos vendidos **/
    public void getCategoriaList( final OnGetDataListener listener ){

        // Metodo utiliza a interface OnGetDataListener para retornar os valores e resolver a race condition
        listener.onStart();

        // Seta a referencia dentro da "pasta" Cardapio
        final DatabaseReference cardapio = restauranteRef.child("Cardapio");

        // Le todas categorias de produtos dentro dessa pasta
        cardapio.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                listener.OnSuccess(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Failed to read value

                listener.onFailed(databaseError);
            }
        });
    }

    /** Retorn a lista de itens dentro de uma categoria **/
    public void getItems( String categoria, final OnGetDataListener listener ){
        // Metodo utiliza a interface OnGetDataListener para retornar os valores e resolver a race condition
        listener.onStart();

        // Seta a referencia dentro da "pasta" do item, dentro da pasta cardapio
        final DatabaseReference cardapio = restauranteRef.child("Cardapio").child(categoria);

        // Le todas categorias de produtos dentro dessa pasta
        cardapio.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                listener.OnSuccess(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Failed to read value

                listener.onFailed(databaseError);
            }
        });
    }


    /** Retorna os dados do restaurante **/
    public void getDados(final OnGetDataListener listener ){
        // Metodo utiliza a interface OnGetDataListener para retornar os valores e resolver a race condition
        listener.onStart();

        // Seta a referencia dentro da "pasta" do item, dentro da pasta cardapio
        final DatabaseReference cardapio = restauranteRef.child("dados");

        // Le todas categorias de produtos dentro dessa pasta
        cardapio.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                listener.OnSuccess(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Failed to read value

                listener.onFailed(databaseError);
            }
        });
    }

    /** Adiciona pedido do usuario ao banco de dados */
    public void addPedido(StructPedido pedido ){

        restauranteRef.child("Pedidos").push().setValue(pedido);

    }

    /** Obtem a lista de pedidos do banco de dados
     *
     * Quando chamar esse metodo utilizar o exemplo no final desse arquivo
     * **/
    public void getPedidos(final OnGetDataListener listener ){
        // Metodo utiliza a interface OnGetDataListener para retornar os valores e resolver a race condition
        listener.onStart();

        // Seta a referencia dentro da "pasta" do pedidos
        final DatabaseReference cardapio = restauranteRef.child("Pedidos");

        // Le todas categorias de produtos dentro dessa pasta
        cardapio.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                listener.OnSuccess(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Failed to read value

                listener.onFailed(databaseError);
            }
        });
    }



    public DatabaseReference getRestauranteRef() {
        return restauranteRef;
    }

    public void addItem(String categoria, StructItem newItem ){
        // Adiciona um elementos na lista "categoria", criando um elemento com o nome do item e colocando os dados dentro
        restauranteRef.child("Cardapio").child(categoria).child(newItem.getNome()).setValue(newItem);

    }

    public void setRestauranteRef(DatabaseReference restauranteRef) {
        this.restauranteRef = restauranteRef;
    }





    public List<StructPedido> getPedidos(){



        return null;
    }



    public void setupCardapioListener(){
        DatabaseReference cardapio = firebaseDatabase.getReference("Cardapio/");
        cardapio.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.e("Count " , ""+dataSnapshot.getChildrenCount());

                for(DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    StructCategoria temp = postSnapshot.getValue(StructCategoria.class);

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


}




/* Exemplo de como chamar os metodos que retornam dados do banco de dados

 dbRestaurante.getPedidos( new OnGetDataListener() {
                @Override
                public void onStart() {
                    // Do some thing when start get data here
                }

                @Override
                public void OnSuccess(DataSnapshot dataSnapshot1) {

                    for (DataSnapshot postSnapshot : dataSnapshot1.getChildren()) {

                        // Le posicao da lista que veio do firebase
                        try{
                            StructPedido temp1 = postSnapshot.getValue(StructPedido.class);

                            if (temp1 != null) {

                                // utiliza a variavel temp1 aqui dentro

                            } else {
                                System.out.println("Algo aconteceu");
                            }

                        }catch (Exception e){
                            // DO something
                        }

                    }
                }

                @Override
                public void onFailed(DatabaseError databaseError) {
                    //DO SOME THING WHEN GET DATA FAILED HERE
                    Log.e(TAG, "Failed to read value.", databaseError.toException());
                }
            });
 */