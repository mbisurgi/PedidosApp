package com.designfreed.apppedidos.entities;

import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "clientes")
public class Cliente {
    @Column(name = "id_cliente")
    private Long clienteId;
    @Column(name = "razon_social")
    private String razonSocial;
    @Column(name = "calle")
    private String calle;
    @Column(name = "altura")
    private String altura;
    @Column(name = "condicion_venta")
    private CondicionVenta condicionVenta;

    public Cliente() {
    }

    public Cliente(Long clienteId, String razonSocial, String calle, String altura, CondicionVenta condicionVenta) {
        this.clienteId = clienteId;
        this.razonSocial = razonSocial;
        this.calle = calle;
        this.altura = altura;
        this.condicionVenta = condicionVenta;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getAltura() {
        return altura;
    }

    public void setAltura(String altura) {
        this.altura = altura;
    }

    public CondicionVenta getCondicionVenta() {
        return condicionVenta;
    }

    public void setCondicionVenta(CondicionVenta condicionVenta) {
        this.condicionVenta = condicionVenta;
    }
}
