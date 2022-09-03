package com.apolosolutions.Apolo.Modelos;

import javax.persistence.*;


@Entity
@Table(name="Transacciones")
public class MovimientoDinero {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="monto")
    private long monto;
    @Column(name = "concepto")
    private String concepto;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public MovimientoDinero() {
    }

    public MovimientoDinero(long monto, String concepto, Usuario usuario) {
        this.monto = monto;
        this.concepto = concepto;
        this.usuario = usuario;
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

    public Usuario getEmpleado() {
        return usuario;
    }

    public void setEmpleado(Usuario usuario) {
        this.usuario = usuario;
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
