package com.designfreed.apppedidos.entities;

public class Producto {
    private Long id;
    private String producto;

    public Producto() {
    }

    public Producto(Long id, String producto) {
        this.id = id;
        this.producto = producto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }
}
