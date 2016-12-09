package com.example.nesvera.apprestaurante;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;
import android.widget.TextView;

import com.example.nesvera.apprestaurante.Firebase.DatabaseAccess;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class RestaurantePage extends AppCompatActivity {

    Toolbar toolbar;
    private static TabLayout tabLayout;


    TextView mTextView;
    ListView mListView;

    DatabaseAccess dbRestaurante;

    private static ViewPager viewPager;
    private static SimpleFragmentPageAdapter fragAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurante_page);

        toolbar = (Toolbar)findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);


        // Chama os fragmentos
        viewPager = (ViewPager)findViewById(R.id.view_pager);

        // Chamar essa funcao só depois de ter pego o nome de todas tabs
        fragAdapter = new SimpleFragmentPageAdapter(getSupportFragmentManager(),this, "trago_trago_bebidas");

        // --> chamar isso depois de tudo estiver setado
        //viewPager.setAdapter(fragAdapter);

        tabLayout = (TabLayout)findViewById(R.id.tab_layout);
        //tabLayout.setupWithViewPager(viewPager);




        //dbRestaurante.

        /*

        final List<String> listString = new ArrayList<String>();

        mainListView = (ListView)findViewById(R.id.listViewInit);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listString);

        dbRestaurante.getCategoriaList(new OnGetDataListener() {
            @Override
            public void onStart() {
                //DO SOME THING WHEN START GET DATA HERE
            }

            @Override
            public void OnSuccess(DataSnapshot dataSnapshot) {
                //DO SOME THING WHEN GET DATA SUCCESS HERE

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                    // Le posicao da lista que veio do firebase
                    StructCategoria temp = postSnapshot.getValue(StructCategoria.class);

                    if (temp != null) {
                        // handlLeituraCategoria(temp);

                        listString.add(temp.getNome());

                        //System.out.println("---> " + temp.getNome());

                    } else {
                        System.out.println("Algo aconteceu");
                    }
                }

                mainListView.setAdapter(adapter);

            }

            @Override
            public void onFailed(DatabaseError databaseError) {
                //DO SOME THING WHEN GET DATA FAILED HERE
                Log.w(TAG, "Failed to read value.", databaseError.toException());
            }
        });


         */

    }

    public static void refreshTabAdapter(){
        viewPager.setAdapter(fragAdapter);
        tabLayout.setupWithViewPager(viewPager);

    }
}
