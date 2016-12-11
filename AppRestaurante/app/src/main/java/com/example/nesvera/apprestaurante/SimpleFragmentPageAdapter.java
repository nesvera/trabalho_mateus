package com.example.nesvera.apprestaurante;

import android.content.Context;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;
import static android.content.ContentValues.TAG;

import com.example.nesvera.apprestaurante.Firebase.DatabaseAccess;
import com.example.nesvera.apprestaurante.Firebase.OnGetDataListener;
import com.example.nesvera.apprestaurante.Structs.StructCategoria;
import com.example.nesvera.apprestaurante.Structs.StructDados;
import com.example.nesvera.apprestaurante.Structs.StructItem;
import com.example.nesvera.apprestaurante.Structs.StructRestaurante;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nesvera on 03/12/2016.
 */

public class SimpleFragmentPageAdapter extends FragmentPagerAdapter {

    private List<String> tabTitle;
    private List<ListaFragment> fragmentList;

    //private String[] tabTitle = new String[]{"Home","Xis","Hotdog"};

    List<String> tabCardapio;

    List<List<StructItem>> cardapioList;
    List<String> cardapioListStr;

    Context context;
    private int pageCount;

    FirebaseAuth firebaseAuth;
    FirebaseDatabase database;
    public static DatabaseAccess dbRestaurante;

    public static int numeroMesa;

    private boolean recebeuCategorias;

    private int contFrag;


    public SimpleFragmentPageAdapter(FragmentManager fm, Context context, String restaurante) {
        super(fm);

        // Inicia a lista que ira conter as Categorias de Produtos
        tabTitle = new ArrayList<String>();
        fragmentList = new ArrayList<ListaFragment>();

        cardapioList = new ArrayList<List<StructItem>>();
        cardapioListStr = new ArrayList<String>();

        // Adiciona a aba Home
        tabTitle.add("Home");
        pageCount = 1;
        contFrag = 0;

        firebaseAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        dbRestaurante = new DatabaseAccess(database);
        dbRestaurante.setRestaurante(restaurante);

        numeroMesa = 15;

        dbRestaurante.getCategoriaList(new OnGetDataListener() {
            @Override
            public void onStart() {
                // Do some thing when start get data here
            }

            @Override
            public void OnSuccess(DataSnapshot dataSnapshot) {

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                    // Le posicao da lista que veio do firebase
                    StructCategoria temp = postSnapshot.getValue(StructCategoria.class);

                    if (temp != null) {

                        tabTitle.add(temp.getNome());
                        pageCount++;

                    } else {
                        System.out.println("Algo aconteceu");
                    }
                }

                RestaurantePage.refreshTabAdapter();
            }

            @Override
            public void onFailed(DatabaseError databaseError) {
                //DO SOME THING WHEN GET DATA FAILED HERE
                Log.e(TAG, "Failed to read value.", databaseError.toException());
            }
        });

        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {

        int teste = 0;

        if( position == 0 ) {
            final HomeFragment homeFragment = new HomeFragment();

            // Pegar dados do restaurante
            dbRestaurante.getDados(new OnGetDataListener() {
                @Override
                public void onStart() {
                    // Do some thing when start get data here
                }

                @Override
                public void OnSuccess(DataSnapshot dataSnapshot1) {

                    StructDados temp = dataSnapshot1.getValue(StructDados.class);

                    homeFragment.alteraDados(temp);

                }

                @Override
                public void onFailed(DatabaseError databaseError) {
                    //DO SOME THING WHEN GET DATA FAILED HERE
                    Log.e(TAG, "Failed to read value.", databaseError.toException());
                }
            });

            return homeFragment;

        }else{
            final ListaFragment listaFragment = new ListaFragment();

            dbRestaurante.getItems( tabTitle.get(position), new OnGetDataListener() {
                @Override
                public void onStart() {
                    // Do some thing when start get data here
                }

                @Override
                public void OnSuccess(DataSnapshot dataSnapshot1) {

                    // Contem os items de um categoria
                    List<StructItem> structTemp = new ArrayList<StructItem>();
                    List<String> tempListItems = new ArrayList<String>();

                    for (DataSnapshot postSnapshot : dataSnapshot1.getChildren()) {

                        // Le posicao da lista que veio do firebase
                        try{
                            StructItem temp1 = postSnapshot.getValue(StructItem.class);

                            if (temp1 != null) {

                                // Adicionar os items em umca categoria temporaria
                                structTemp.add(temp1);
                                tempListItems.add(temp1.getNome());

                            } else {
                                System.out.println("Algo aconteceu");
                            }

                        }catch (Exception e){
                            // DO something
                        }

                    }

                    // Adiciona a categoria temporaria na lista de categorias
                    cardapioList.add(structTemp);

                    listaFragment.atualizaLista(tempListItems, structTemp);

                }

                @Override
                public void onFailed(DatabaseError databaseError) {
                    //DO SOME THING WHEN GET DATA FAILED HERE
                    Log.e(TAG, "Failed to read value.", databaseError.toException());
                }
            });


            return listaFragment;

        }

    }

    @Override
    public int getCount() {

        return pageCount;
    }

    @Override
    public String getPageTitle(int position) {

        return tabTitle.get(position);
    }

}
