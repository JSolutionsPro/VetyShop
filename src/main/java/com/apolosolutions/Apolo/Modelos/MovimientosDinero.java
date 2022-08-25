package com.apolosolutions.Apolo.Modelos;

import javax.persistence.*;


@Entity
@Table(name="Transacciones")
public class MovimientosDinero {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private long Cantidad
    private String Concepto
    @ManyToOne
    @JoinColumn(name = "Obrero_id")
    private Empleado cliente;

    public MovimientosDinero(int id) {
        this.id = id;
    }

    public MovimientosDinero(long cantidad, String concepto, Empleado cliente) {
        Cantidad = cantidad;
        Concepto = concepto;
        this.cliente = cliente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getCantidad() {
        return Cantidad;
    }

    public void setCantidad(long cantidad) {
        Cantidad = cantidad;
    }

    public String getConcepto() {
        return Concepto;
    }

    public void setConcepto(String concepto) {
        Concepto = concepto;
    }

    public Empleado getCliente() {
        return cliente;
    }

    public void setCliente(Empleado cliente) {
        this.cliente = cliente;
    }
}
