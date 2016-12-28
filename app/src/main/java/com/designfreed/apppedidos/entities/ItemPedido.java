package com.designfreed.apppedidos.entities;

import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "items_pedido")
public class ItemPedido {
    @Column(name = "id_item_pedido")
    private Long itemPedidoId;
    @Column(name = "producto")
    private Producto producto;
    @Column(name = "cantidad")
    private Integer cantidad;
    @Column(name = "precio")
    private Double precio;

    public ItemPedido() {
    }

    public ItemPedido(Long itemPedidoId, Producto producto, Integer cantidad, Double precio) {
        this.itemPedidoId = itemPedidoId;
        this.producto = producto;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public Long getItemPedidoId() {
        return itemPedidoId;
    }

    public void setItemPedidoId(Long itemPedidoId) {
        this.itemPedidoId = itemPedidoId;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }
}
