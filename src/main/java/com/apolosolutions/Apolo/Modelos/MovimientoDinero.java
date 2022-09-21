package com.apolosolutions.Apolo.Modelos;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;


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

    //Relacion usuario-movimiento
    @ManyToOne
    @JoinColumn(name = "usuario_id", foreignKey = @ForeignKey(name="fk_movimiento_usuario"), insertable = false, updatable=false)
    @JsonBackReference(value="usuario-movimiento")
    private Usuario usuario;
    private int usuario_id;

    //Relacion empresa-movimiento
    @ManyToOne
    @JoinColumn(name = "empresa_id", foreignKey = @ForeignKey(name="fk_movimiento_empresa"), insertable = false, updatable = false)
    @JsonBackReference(value="empresa-movimiento")
    private Empresa empresa;
    private int empresa_id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaMovimiento;


    public MovimientoDinero() {
    }

    public MovimientoDinero(long monto, String concepto, Usuario usuario, int usuario_id, Empresa empresa, int empresa_id, LocalDate fechaMovimiento) {
        this.monto = monto;
        this.concepto = concepto;
        this.usuario = usuario;
        this.usuario_id = usuario_id;
        this.empresa = empresa;
        this.empresa_id = empresa_id;
        this.fechaMovimiento = fechaMovimiento;
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
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
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
    public LocalDate getFechaMovimiento() {
        return fechaMovimiento;
    }

    public void setFechaMovimiento(LocalDate fechaMovimiento) {
        this.fechaMovimiento = fechaMovimiento;
    }

    @Override
    public String toString() {
        return "- Datos del movimiento - \n" +
                " Monto: " + getMonto() + '\n' +
                " Concepto: " + getConcepto() + '\n' +
                " Empleado: " + getUsuario().getNombre() + '\n' +
                "---------------------- ";
    }


}
