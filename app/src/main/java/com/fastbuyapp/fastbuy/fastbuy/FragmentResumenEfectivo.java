package com.fastbuyapp.fastbuy.fastbuy;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.fastbuyapp.fastbuy.fastbuy.config.Servidor;
import com.fastbuyapp.fastbuy.fastbuy.entidades.Globales;
import com.fastbuyapp.fastbuy.fastbuy.entidades.PedidoDetalle;
import com.fastbuyapp.omar.fastbuy.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

public class FragmentResumenEfectivo extends Fragment {
    private Typeface fuente1;
    private final int DURACION_SPLASH = 1500;
    public FragmentResumenEfectivo() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_resumen_efectivo, container, false);

        String fuente = "fonts/fuente1.ttf";
        fuente1 = Typeface.createFromAsset(getActivity().getAssets(), fuente);
        Button btnResumen = (Button) view.findViewById(R.id.btnGenerarPedido);
        btnResumen.setTypeface(fuente1);
        TextView lblFormaPago = (TextView) view.findViewById(R.id.lblFormaPago);
        ImageView imgPago = (ImageView) view.findViewById(R.id.imgPago);

        //
        if(Globales.formaPago.equals("Efectivo")){
            lblFormaPago.setText("Forma de pago: EFECTIVO");
            Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.ic_efectivo);
            imgPago.setImageBitmap(bmp);
            Globales.montoCargo = 0;
        }
        if(Globales.formaPago.equals("Tarjeta")){
            lblFormaPago.setText("Forma de pago: TARJETA");
            Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.ic_credito);
            imgPago.setImageBitmap(bmp);
            Globales.montoCargo = 5;
        }
        if(Globales.formaPago.equals("Efectivo y Tarjeta")){
            lblFormaPago.setText("Forma de pago: EFECTIVO Y TARJETA");
            Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.ic_efectivo_tarjeta);
            imgPago.setImageBitmap(bmp);
            Globales.montoCargo = 5;

        }
        TextView lblNombreUsuario = (TextView) view.findViewById(R.id.lblNombreUsuario);
        lblNombreUsuario.setText(Globales.nombreCliente);
        TextView lblDireccionCLiente = (TextView) view.findViewById(R.id.lblDireccionCLiente);
        lblDireccionCLiente.setText(Globales.direccion);
        TextView lblCiudadOrigen = (TextView) view.findViewById(R.id.lblCiudadOrigen);
        lblCiudadOrigen.setText(Globales.ciudadOrigen);
        TextView lblCompraMonto = (TextView) view.findViewById(R.id.lblCompraMonto);
        lblCompraMonto.setText("S/ " + String.format("%.2f",Globales.montoCompra));
        TextView lblCompraDelivery = (TextView) view.findViewById(R.id.lblCompraDelivery);
        lblCompraDelivery.setText("S/ " + String.format("%.2f",Globales.montoDelivery));
        TextView lblCompraCargo = (TextView) view.findViewById(R.id.lblCompraCargo);
        lblCompraCargo.setText("S/ " + String.format("%.2f",Globales.montoCargo));
        float total = Globales.montoCompra + Globales.montoDelivery + Globales.montoCargo;
        TextView lblCompraTotal = (TextView) view.findViewById(R.id.lblCompraTotal);
        lblCompraTotal.setText("S/ " + String.format("%.2f",total));

        btnResumen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage("En breve recibirá una llamada para la confirmación de la compra. ¿Desea continuar?");
                builder.setTitle("Nueva Compra");
                builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        try {
                            registrarPedido(Globales.nombreCliente, Globales.direccion, Globales.numeroTelefono, Globales.getInstance().getListaPedidos());
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                        Toast toast = Toast.makeText(v.getContext(), "Compra registrada.", Toast.LENGTH_SHORT);
                        View vistaToast = toast.getView();
                        vistaToast.setBackgroundResource(R.drawable.toast_exito);
                        toast.show();
                        new Handler().postDelayed(new Runnable() {
                            public void run() {
                                Intent intent = new Intent(v.getContext(), PortadaActivity.class);
                                intent.putExtra("ubicacion", Globales.ciudad);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                            }

                            ;
                        }, DURACION_SPLASH);
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        return view;
    }
    public void RegistrarPedidoBD(String URL, final ArrayList<PedidoDetalle> lista){
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.length()>0){
                    try {
                        //TextView txtCodigoPedido = (TextView) findViewById(R.id.txtCodigoPedido);
                        JSONObject jo = new JSONObject(response);
                        codigoRegistro = jo.getInt("codigo");
                        int i = 0;
                        for (PedidoDetalle pd: lista) {
                            i++;
                            String item = String.valueOf(i);
                            String codigoProducto = String.valueOf(pd.getProducto().getCodigo());
                            String cantidad = String.valueOf(pd.getCantidad());
                            String precio = String.valueOf(pd.getProducto().getPrecio());
                            String total = String.valueOf(pd.getTotal());
                            Servidor s = new Servidor();
                            String consulta = "http://"+s.getServidor()+"/app/controllers/RegistrarPedidoDetalle.php?pedido=" + String.valueOf(codigoRegistro) +"&item="+item+"&producto="+codigoProducto+"&cantidad="+cantidad+"&precio="+precio+"&total=" + total;
                            //txtCodigoPedido.setText(consulta);
                            RegistrarDetallePedido(consulta);

                        }
                        lista.clear();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(stringRequest);
    }

    public void RegistrarDetallePedido(String URL){
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(stringRequest);
    }
    public int codigoRegistro = 0;
    public void registrarPedido(String nombre, String direccion, String telefono, ArrayList<PedidoDetalle> lista) throws UnsupportedEncodingException {
        Servidor s = new Servidor();
        String a = URLEncoder.encode(nombre, "UTF-8");
        String b = URLEncoder.encode(direccion, "UTF-8");
        String c = URLEncoder.encode(telefono, "UTF-8");
        String consulta = "http://"+s.getServidor()+"/app/controllers/RegistrarNuevoPedido.php?nombre="+a+"&direccion="+b+"&telefono=" + c;
        RegistrarPedidoBD(consulta,lista);

    }

}
