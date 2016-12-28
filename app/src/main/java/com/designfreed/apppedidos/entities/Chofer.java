package com.designfreed.apppedidos.entities;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.io.Serializable;

@Table(name = "choferes")
public class Chofer extends Model implements Serializable {
    @Column(name = "id_chofer")
    private Long choferId;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "apellido")
    private String apellido;

    public Chofer() {
    }

    public Chofer(Long choferId, String nombre, String apellido) {
        this.choferId = choferId;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public Long getChoferId() {
        return choferId;
    }

    public void setChoferId(Long choferId) {
        this.choferId = choferId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    @Override
    public String toString() {
        return "Chofer{" +
                "choferId=" + choferId +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                '}';
    }
}
