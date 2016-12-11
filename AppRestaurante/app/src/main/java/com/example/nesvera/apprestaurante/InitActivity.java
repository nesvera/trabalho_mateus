package com.example.nesvera.apprestaurante;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.nesvera.apprestaurante.Firebase.DatabaseAccess;
import com.example.nesvera.apprestaurante.Firebase.OnGetDataListener;
import com.example.nesvera.apprestaurante.Structs.StructCategoria;
import com.example.nesvera.apprestaurante.Structs.StructItem;
import com.example.nesvera.apprestaurante.Structs.StructRestaurante;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;
import static com.example.nesvera.apprestaurante.Firebase.DatabaseAccess.restauranteListOk;
import static java.security.AccessController.getContext;

public class InitActivity extends AppCompatActivity {
    private Button btn_scan;
    private DatabaseAccess dbRestaurante;

    private static ListView mainListView;
    private static ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_init);


        final Activity activity = this;

        btn_scan = (Button)findViewById(R.id.b_scan);
        btn_scan.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
               /*
                IntentIntegrator integrator = new IntentIntegrator(activity);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                integrator.setOrientationLocked(true);
                integrator.setPrompt("Leia o QR Code");
                integrator.setCameraId(0);
                integrator.setBeepEnabled(false);
                integrator.setBarcodeImageEnabled(false);
                integrator.setCaptureActivity(CaptureActivityPortrait.class);
                integrator.initiateScan();
                */

                Intent intent = new Intent(InitActivity.this, RestaurantePage.class);
                startActivity(intent);
            }
        });

        // Inicia comunicacao com o banco de dados para obter os restaurantes proximos
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        dbRestaurante = new DatabaseAccess(database);

        // Declara listas para citar os restaurantes e seta ela para a listview
        final List<String> listString = new ArrayList<String>();

        //final MyListAdapter listString = new MyListAdapter();

        mainListView = (ListView)findViewById(R.id.listViewInit);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listString);
        mainListView.setAdapter(adapter);
        mainListView.setClickable(true);

        mainListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicao, long l) {

                Toast.makeText(InitActivity.this, "Restaurante: " + adapterView.getItemAtPosition(posicao), Toast.LENGTH_LONG ).show();
            }
        });

        // chama o metodo para ler os restaurantes do servidor e atualizar a listview inicial
        dbRestaurante.getRestauranteList(new OnGetDataListener() {
            @Override
            public void onStart() {
                //DO SOME THING WHEN START GET DATA HERE
            }

            @Override
            public void OnSuccess(DataSnapshot dataSnapshot) {
                //DO SOME THING WHEN GET DATA SUCCESS HERE

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                    // Le posicao da lista que veio do firebase
                    StructRestaurante temp = postSnapshot.getValue(StructRestaurante.class);

                    if (temp != null) {
                        listString.add(temp.getDados().getNome());

                    } else {
                        System.out.println("Algo aconteceu");
                    }
                }

                // Atualiza a listveiw  com os restaurantes recebidos
                mainListView.setAdapter(adapter);
            }

            @Override
            public void onFailed(DatabaseError databaseError) {
                //DO SOME THING WHEN GET DATA FAILED HERE
                Log.w(TAG, "Failed to read value.", databaseError.toException());
            }
        });

        // Pronto até aqui (falta implementar o click na lista)
    }

    protected void onActivityResult( int requestCode, int resultCode, Intent data ){
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data );

        if( result != null ){
            if( result.getContents() == null ){
                Toast.makeText(this, "You cancelled the scanning", Toast.LENGTH_LONG).show();

            }else{
                Toast.makeText(this, result.getContents(), Toast.LENGTH_LONG).show();

                Intent intent = new Intent(InitActivity.this, RestaurantePage.class);
                startActivity(intent);
        }

        }else{
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

}

/** O que falta
 *                  Obter o nome do restaurante e mesa e declarar no SimpleFragmentPageAdapter via QR
 *                  Fazer o dialog para pagina inical que mostra os dados do restaurante
 *
 *
 * **/
