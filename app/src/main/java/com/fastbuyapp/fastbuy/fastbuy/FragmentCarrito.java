package com.fastbuyapp.fastbuy.fastbuy;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import com.fastbuyapp.omar.fastbuy.R;
import com.fastbuyapp.fastbuy.fastbuy.entidades.Globales;
import com.fastbuyapp.fastbuy.fastbuy.entidades.PedidoDetalle;

import java.util.ArrayList;


public class FragmentCarrito extends Fragment {
    private Typeface fuente1;
    public ArrayList<PedidoDetalle> listaPedidos;
    GridView gridView;
    CarritoListAdapter adapter = null;

    public FragmentCarrito() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_carrito, container, false);
        String fuente = "fonts/fuente1.ttf";
        fuente1 = Typeface.createFromAsset(getActivity().getAssets(), fuente);
        listarTablaPedidos(view);
        Button btnContinuarCarrito = (Button) view.findViewById(R.id.btnContinuarCarrito);
        btnContinuarCarrito.setTypeface(fuente1);
        TextView lblSolicitud = (TextView) view.findViewById(R.id.lblSolicitudCompras);
        lblSolicitud.setTypeface(fuente1);

        TextView lblText2 = (TextView) view.findViewById(R.id.lblText2);
        //lblText2.setTypeface(fuente1);
        TextView lblTextCostoEnvio = (TextView) view.findViewById(R.id.lblTextCostoEnvio);
        //lblTextCostoEnvio.setTypeface(fuente1);
        TextView lblTextCostoTotal = (TextView) view.findViewById(R.id.lblTextCostoTotal);
        //lblTextCostoTotal.setTypeface(fuente1);
        return view;
    }
    public float calcularCostoEnvio(){
        return 3;
    }

    public void listarTablaPedidos(final View view){
        Globales g = Globales.getInstance();
        listaPedidos = g.getListaPedidos();
        gridView = (GridView) view.findViewById(R.id.gvTablaProductosPedidos);
        adapter = new CarritoListAdapter(getActivity(), R.layout.list_carrito_item, listaPedidos);
        gridView.setAdapter(adapter);
        float sumaTotal = 0;
        for (int i = 0; i < listaPedidos.size(); i++) {
            sumaTotal += listaPedidos.get(i).getTotal();
        }
        TextView lblTextCostoTotal = (TextView) view.findViewById(R.id.lblTextCostoTotal);
        TextView lblTextCostoPedido = (TextView) view.findViewById(R.id.lblTextCostoPedido);
        TextView lblTextCostoEnvio = (TextView) view.findViewById(R.id.lblTextCostoEnvio);
        float costoEnvio = calcularCostoEnvio();
        float total = costoEnvio + sumaTotal;
        Globales.montoCompra = sumaTotal;
        Globales.montoDelivery = costoEnvio;
        Globales.montoTotal = total;
        lblTextCostoEnvio.setText("S/ " + String.format("%.2f",costoEnvio));
        lblTextCostoPedido.setText("S/ " + String.format("%.2f",sumaTotal));
        lblTextCostoTotal.setText("S/ " + String.format("%.2f",total));

        Button btnContinuar = (Button) view.findViewById(R.id.btnContinuarCarrito);
        btnContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView txtBarra = (TextView) getActivity().findViewById(R.id.txtBarra);
                txtBarra.setText("DATOS");
                final FragmentDatosPedido panelDatosPedido = new FragmentDatosPedido();
                final FragmentManager manager = getActivity().getSupportFragmentManager();
                manager.beginTransaction()
                        .replace(R.id.contenedorPrincipal, panelDatosPedido, panelDatosPedido.getTag())
                        .commit();
            }
        });
    }

}
