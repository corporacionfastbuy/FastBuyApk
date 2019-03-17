package com.fastbuyapp.fastbuy.fastbuy.entidades;

/**
 * Created by OMAR on 22/08/2018.
 */

public class Empresa {
    private int codigo;
    private String nombreComercial;
    private String razonSocial;
    private String direccion;
    private String telefonos;
    private String imagen;
    private int estado;
    private String estadoAbierto;
    private double precio;

    public String getNombreComercial() {
        return nombreComercial;
    }

    public void setNombreComercial(String nombreComercial) {
        this.nombreComercial = nombreComercial;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(String telefonos) {
        this.telefonos = telefonos;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getEstadoAbierto() {
        return estadoAbierto;
    }

    public void setEstadoAbierto(String estadoAbierto) {
        this.estadoAbierto = estadoAbierto;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
