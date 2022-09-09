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
    @Column(name = "correo", unique = true)
    private String correo;
    @Column(name = "rol")
    @Enumerated(value = EnumType.STRING)
    private RolEmpleado rol;

    //Relacion empresa-usuario
    @ManyToOne
    @JoinColumn(name = "empresa_id", foreignKey = @ForeignKey(name="fk_usuario_empresa"), insertable = false, updatable = false)
    @JsonBackReference(value="empresa-usuario")
    private Empresa empresa;
    private int empresa_id;

    //Relacion usuario-movimiento
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    @JsonManagedReference(value="usuario-movimiento")
    private List<MovimientoDinero> movimientoDineros;


    public Usuario() {
    }

    public Usuario(String nombre, String correo, RolEmpleado rol, Empresa empresa, List<MovimientoDinero> movimientoDineros) {
        this.nombre = nombre;
        this.correo = correo;
        this.rol = rol;
        this.empresa = empresa;
        this.movimientoDineros = movimientoDineros;
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

    public int getEmpresa_id() {
        return empresa_id;
    }

    public void setEmpresa_id(int empresa_id) {
        this.empresa_id = empresa_id;
    }

    public List<MovimientoDinero> getMovimientoDineros() {
        return movimientoDineros;
    }

    public void setMovimientoDineros(List<MovimientoDinero> movimientoDineros) {
        this.movimientoDineros = movimientoDineros;
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
