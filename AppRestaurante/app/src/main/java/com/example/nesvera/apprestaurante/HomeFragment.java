package com.example.nesvera.apprestaurante;

        import android.app.Fragment;
        import android.os.Bundle;
        import android.support.annotation.Nullable;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.TextView;

        import com.example.nesvera.apprestaurante.Firebase.DatabaseAccess;
        import com.example.nesvera.apprestaurante.Structs.StructDados;
        import com.google.firebase.auth.FirebaseAuth;

        import org.w3c.dom.Text;

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

    public void alteraDados(StructDados structDados){

        TextView tvNome = (TextView)getActivity().findViewById(R.id.textViewNome);
        tvNome.setText(structDados.getNome());

        TextView tvDescricao = (TextView)getActivity().findViewById(R.id.textViewDescricao);
        tvDescricao.setText(structDados.getDescricao());

        TextView tvEndereco = (TextView)getActivity().findViewById(R.id.textViewEndereco);
        tvEndereco.setText(structDados.getEndereco());

    }
}