package com.designfreed.apppedidos.entities;

import java.io.Serializable;
import java.util.Date;

public class HojaRuta implements Serializable {
    private Long id;
    private Date fecha;
    private Long choferId;
    private Boolean estado;

    public HojaRuta() {
    }

    public HojaRuta(Long id, Date fecha, Long choferId, Boolean estado) {
        this.id = id;
        this.fecha = fecha;
        this.choferId = choferId;
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Long getChoferId() {
        return choferId;
    }

    public void setChoferId(Long choferId) {
        this.choferId = choferId;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
}
