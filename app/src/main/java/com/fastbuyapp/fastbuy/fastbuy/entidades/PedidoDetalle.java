package com.fastbuyapp.fastbuy.fastbuy.entidades;

/**
 * Created by OMAR on 05/09/2018.
 */

public class PedidoDetalle {
    private int numero;
    private Producto producto;
    private int cantidad;
    private float total;

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
}
