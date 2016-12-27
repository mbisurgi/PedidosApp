package com.designfreed.apppedidos.entities;

public class Cliente {
    private Long id;
    private String razonSocial;
    private String calle;
    private String altura;
    private CondicionVenta condicionVenta;

    public Cliente() {
    }

    public Cliente(Long id, String razonSocial, String calle, String altura, CondicionVenta condicionVenta) {
        this.id = id;
        this.razonSocial = razonSocial;
        this.calle = calle;
        this.altura = altura;
        this.condicionVenta = condicionVenta;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
