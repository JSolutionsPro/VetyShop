package com.apolosolutions.Apolo.Modelos;

import com.apolosolutions.Apolo.Modelos.enums.RolEmpleado;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name= "Usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "correo")
    private String correo;
    @ManyToOne
    @JoinColumn(name = "empresa_id")
    @JsonBackReference(value="empresa-movimiento")
    private Empresa empresa;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "rol")
    private RolEmpleado rol;

    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference("usuario-movimiento")
    private List<MovimientoDinero> movimientoDinero;

    public Usuario() {
    }

    public Usuario(String nombre, String correo, Empresa empresa, RolEmpleado rol, List<MovimientoDinero> movimientoDinero) {
        this.nombre = nombre;
        this.correo = correo;
        this.empresa = empresa;
        this.rol = rol;
        this.movimientoDinero = movimientoDinero;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public RolEmpleado getRol() {
        return rol;
    }

    public void setRol(RolEmpleado rol) {
        this.rol = rol;
    }

    public List<MovimientoDinero> getMovimientoDinero() {
        return movimientoDinero;
    }

    public void setMovimientoDinero(List<MovimientoDinero> movimientoDinero) {
        this.movimientoDinero = movimientoDinero;
    }

    @Override
    public String toString() {
        return "- Datos del Usuario - \n" +
                " Nombre: " + getNombre() + '\n' +
                " Correo: " + getCorreo() + '\n' +
                " Empresa: " + getEmpresa().getNombre() + '\n' +
                " Rol: " + getRol() +
                "\n---------------------- ";
    }
}
