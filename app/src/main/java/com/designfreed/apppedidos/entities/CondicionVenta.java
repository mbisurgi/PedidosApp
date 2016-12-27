package com.designfreed.apppedidos.entities;

public class CondicionVenta {
    private Long id;
    private String condicionVenta;

    public CondicionVenta() {
    }

    public CondicionVenta(Long id, String condicionVenta) {
        this.id = id;
        this.condicionVenta = condicionVenta;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCondicionVenta() {
        return condicionVenta;
    }

    public void setCondicionVenta(String condicionVenta) {
        this.condicionVenta = condicionVenta;
    }
}
