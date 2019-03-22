package com.fastbuyapp.fastbuy.fastbuy.entidades;

import java.util.ArrayList;

/**
 * Created by OMAR on 05/09/2018.
 */

public class Globales {
    private static Globales instance;
    private ArrayList<PedidoDetalle> listaPedidos = new ArrayList<PedidoDetalle>();
    private Globales(){

    }

    public ArrayList<PedidoDetalle> getListaPedidos() {
        return listaPedidos;
    }

    public void agregarPedido(PedidoDetalle detalle) {
        this.listaPedidos.add(detalle);
    }

    public void setListaPedidos(ArrayList<PedidoDetalle> listaPedidos){
        this.listaPedidos = listaPedidos;
    }

    public static double precio;
    public static synchronized Globales getInstance(){
        if (instance == null)
            instance = new Globales();
        return instance;
    }

    public static String ciudad = "";
    public static int Subcategoria;
    public static int categoria;
    public static int ubicacion;
    public static int empresaSeleccionada;
    public static int catProductoSeleccionado;

    public static String nombreCliente;
    public static String direccion;
    public static String referencia;
    public static String numeroTelefono;
    public static String ciudadOrigen;
    public static Double longitudOrigen;
    public static Double latitudOrigen;
    public static float montoTotal;
    public static float montoDelivery;
    public static float montoCompra;
    public static String formaPago = "Efectivo";
    public static float montoCargo = 0;
}
