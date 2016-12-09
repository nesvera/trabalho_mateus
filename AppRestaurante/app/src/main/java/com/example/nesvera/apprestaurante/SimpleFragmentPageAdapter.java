package com.example.nesvera.apprestaurante;

import android.content.Context;
import android.nfc.Tag;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;
import static android.content.ContentValues.TAG;

import com.example.nesvera.apprestaurante.Firebase.DatabaseAccess;
import com.example.nesvera.apprestaurante.Firebase.OnGetDataListener;
import com.example.nesvera.apprestaurante.Structs.StructCategoria;
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

    //private String[] tabTitle = new String[]{"Home","Xis","Hotdog"};

    List<String> tabCardapio;

    Context context;
    private int pageCount;

    FirebaseAuth firebaseAuth;
    FirebaseDatabase database;
    DatabaseAccess dbRestaurante;


    public SimpleFragmentPageAdapter(FragmentManager fm, Context context, String restaurante) {
        super(fm);
        this.context = context;

        // Inicia a lista que ira conter as Categorias de Produtos
        tabTitle = new ArrayList<String>();

        // Adiciona a aba Home
        tabTitle.add("Home");
        pageCount = 1;

        firebaseAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        dbRestaurante = new DatabaseAccess(database);
        dbRestaurante.setRestaurante(restaurante);

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
    }

    @Override
    public Fragment getItem(int position) {

        if( position == 0 ) {
            HomeFragment homeFragment = new HomeFragment();

            return homeFragment;

        }else{
            ListaFragment listaFragment = new ListaFragment();

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
