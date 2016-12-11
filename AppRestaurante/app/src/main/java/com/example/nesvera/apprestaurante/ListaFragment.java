package com.example.nesvera.apprestaurante;

        import android.app.Dialog;
        import android.content.Context;
        import android.os.Bundle;
        import android.support.annotation.Nullable;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ArrayAdapter;
        import android.widget.Button;
        import android.widget.ListAdapter;
        import android.widget.ListView;
        import android.widget.TextView;

        import com.example.nesvera.apprestaurante.Structs.StructDados;
        import com.example.nesvera.apprestaurante.Structs.StructItem;
        import com.example.nesvera.apprestaurante.Structs.StructPedido;

        import java.util.List;

/**
 * Created by Nesvera on 03/12/2016.
 */

public class ListaFragment extends android.support.v4.app.ListFragment  {

    String[] players = {"joao","jose","carlos","roberto","denilson","diego","lucas","francisco","pele","vsf"};

    private List<StructItem> structItems;

    private Context context;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        context = getActivity();

        View rootView = inflater.inflate(R.layout.fragment_lista,container,false);

        //ListAdapter adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, players );
        //setListAdapter(adapter);

        return rootView;

    }

    @Override
    public void onListItemClick(ListView l, View v, final int position, long id) {

        final Dialog itemDialog = new Dialog(v.getContext());

        itemDialog.setTitle("Pedido");

        itemDialog.setContentView(R.layout.item_dialog);

        TextView tvItemNome = (TextView)itemDialog.findViewById(R.id.textViewProduto);
        tvItemNome.setText(structItems.get(position).getNome());

        TextView tvItemValor = (TextView)itemDialog.findViewById(R.id.textViewValor);
        tvItemValor.setText(String.valueOf(structItems.get(position).getValor()));

        Button btCancelar = (Button)itemDialog.findViewById(R.id.bt_cancelar);
        btCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemDialog.dismiss();
            }
        });

        Button btPedir = (Button)itemDialog.findViewById(R.id.bt_pedir);
        btPedir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Enviar para o servidor

                StructPedido novoPedido = new StructPedido();

                novoPedido.setMesa(SimpleFragmentPageAdapter.numeroMesa);
                novoPedido.setAnotacao("topper");
                novoPedido.setComida(structItems.get(position).getNome());
                novoPedido.setStatus(false);

                SimpleFragmentPageAdapter.dbRestaurante.addPedido(novoPedido);

                itemDialog.dismiss();
            }
        });

        itemDialog.show();


        super.onListItemClick(l, v, position, id);
    }

    public void atualizaLista( List<String> listaItemsNomes, List<StructItem> structItemsList ){

        structItems = structItemsList;

        ListAdapter adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, listaItemsNomes );
        setListAdapter(adapter);

    }

}
