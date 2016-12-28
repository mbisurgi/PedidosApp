package com.designfreed.apppedidos.entities;

public class CondicionVenta {
    private Long condicionVentaId;
    private String condicionVenta;

    public CondicionVenta() {
    }

    public CondicionVenta(Long condicionVentaId, String condicionVenta) {
        this.condicionVentaId = condicionVentaId;
        this.condicionVenta = condicionVenta;
    }

    public Long getCondicionVentaId() {
        return condicionVentaId;
    }

    public void setCondicionVentaId(Long condicionVentaId) {
        this.condicionVentaId = condicionVentaId;
    }

    public String getCondicionVenta() {
        return condicionVenta;
    }

    public void setCondicionVenta(String condicionVenta) {
        this.condicionVenta = condicionVenta;
    }
}
