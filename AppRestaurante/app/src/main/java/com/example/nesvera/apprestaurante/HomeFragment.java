package com.example.nesvera.apprestaurante;

        import android.app.Fragment;
        import android.os.Bundle;
        import android.support.annotation.Nullable;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;

        import com.example.nesvera.apprestaurante.Firebase.DatabaseAccess;
        import com.google.firebase.auth.FirebaseAuth;
        import com.google.firebase.database.FirebaseDatabase;

        import java.util.ArrayList;
        import java.util.List;

/**
 * Created by Nesvera on 03/12/2016.
 */

public class HomeFragment extends android.support.v4.app.Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home,container,false);



    }
}