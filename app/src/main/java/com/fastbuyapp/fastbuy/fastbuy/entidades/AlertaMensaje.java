package com.fastbuyapp.fastbuy.fastbuy.entidades;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by OMAR on 06/09/2018.
 */

public class AlertaMensaje extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(getActivity());
        builder.setMessage("¿Seguro que desea cancelar la compra.");
        builder.setTitle("Confirmación");
        builder.setPositiveButton("Si", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ArrayList<PedidoDetalle> lista = new ArrayList<PedidoDetalle>();
                lista.clear();
                Globales g = Globales.getInstance();
                g.setListaPedidos(lista);
                Toast.makeText(getActivity(),"Carrito vacío.", Toast.LENGTH_LONG);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        return builder.create();
    }

}
