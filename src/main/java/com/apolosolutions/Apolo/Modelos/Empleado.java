package com.apolosolutions.Apolo.Modelos;

import com.apolosolutions.Apolo.Modelos.enums.RolEmpleado;

import javax.persistence.*;

@Entity
@Table(name= "Empleado")
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String nombre;
    private String correo;
    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;
    private RolEmpleado rol;

    public Empleado() {
    }

    public Empleado(String nombre, String correo, Empresa empresa, RolEmpleado rol) {
        this.nombre = nombre;
        this.correo = correo;
        this.empresa = empresa;
        this.rol = rol;
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

    @Override
    public String toString() {
        return "- Datos del empleado - \n" +
                " Nombre: " + getNombre() + '\n' +
                " Correo: " + getCorreo() + '\n' +
                " Empresa: " + getEmpresa().getNombre() + '\n' +
                " Rol: " + getRol() +
                "\n---------------------- ";
    }
}
