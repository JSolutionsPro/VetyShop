package com.apolosolutions.Apolo.Modelos;

import com.fasterxml.jackson.annotation.JsonBackReference;

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

    //Relacion empleado-movimiento
    @ManyToOne
    @JoinColumn(name = "usuario_id", insertable = false, updatable=false)
    @JsonBackReference(value="empleado-movimiento")
    private Usuario usuario;
    private int usuario_id;

    //Relacion empresa-movimiento
    @ManyToOne
    @JoinColumn(name = "empresa_id", insertable = false, updatable = false)
    @JsonBackReference(value="empresa-movimiento")
    private Empresa empresa;
    private int empresa_id;


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

    public int getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(int usuario_id) {
        this.usuario_id = usuario_id;
    }

    public int getEmpresa_id() {
        return empresa_id;
    }

    public void setEmpresa_id(int empresa_id) {
        this.empresa_id = empresa_id;
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
