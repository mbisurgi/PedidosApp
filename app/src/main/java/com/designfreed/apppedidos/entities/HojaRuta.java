package com.designfreed.apppedidos.entities;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.io.Serializable;
import java.util.Date;

@Table(name = "hojas_ruta")
public class HojaRuta extends Model implements Serializable {
    @Column(name = "id_hoja_ruta")
    private Long hojaRutaId;
    @Column(name = "fecha")
    private Date fecha;
    @Column(name = "id_chofer")
    private Long choferId;
    @Column(name = "estado")
    private Boolean estado;

    public HojaRuta() {
    }

    public HojaRuta(Long hojaRutaId, Date fecha, Long choferId, Boolean estado) {
        this.hojaRutaId = hojaRutaId;
        this.fecha = fecha;
        this.choferId = choferId;
        this.estado = estado;
    }

    public Long gethojaRutaId() {
        return hojaRutaId;
    }

    public void sethojaRutaId(Long hojaRutaId) {
        this.hojaRutaId = hojaRutaId;
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

    @Override
    public String toString() {
        return "HojaRuta{" +
                "hojaRutaId=" + hojaRutaId +
                ", fecha=" + fecha +
                ", choferId=" + choferId +
                ", estado=" + estado +
                '}';
    }
}
