package com.apolosolutions.Apolo.Modelos;

import javax.persistence.*;


@Entity
@Table(name="Transacciones")
public class MovimientoDinero {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private long monto;
    private String concepto;

    @ManyToOne
    @JoinColumn(name = "empleado_id")
    private Empleado empleado;

    public MovimientoDinero() {
    }

    public MovimientoDinero(long monto, String concepto, Empleado empleado) {
        this.monto = monto;
        this.concepto = concepto;
        this.empleado = empleado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getMonto() {
        return monto;
    }

    public void setMonto(long monto) {
        this.monto = monto;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    @Override
    public String toString() {
        return "- Datos del movimiento - \n" +
                " Monto: " + getMonto() + '\n' +
                " Concepto: " + getConcepto() + '\n' +
                " Empleado: " + getEmpleado().getNombre() + '\n' +
                "---------------------- ";
    }
}
