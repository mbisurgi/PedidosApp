package com.designfreed.apppedidos.entities;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "productos")
public class Producto extends Model{
    @Column(name = "id_producto")
    private Long productoId;
    @Column(name = "codigo_producto")
    private Integer productoCodigo;
    @Column(name = "producto")
    private String producto;

    public Producto() {
    }

    public Producto(Long productoId, Integer productoCodigo, String producto) {
        this.productoId = productoId;
        this.productoCodigo = productoCodigo;
        this.producto = producto;
    }

    public Long getProductoId() {
        return productoId;
    }

    public void setProductoId(Long productoId) {
        this.productoId = productoId;
    }

    public Integer getProductoCodigo() {
        return productoCodigo;
    }

    public void setProductoCodigo(Integer productoCodigo) {
        this.productoCodigo = productoCodigo;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }
}
