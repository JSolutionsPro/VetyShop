package com.apolosolutions.Apolo.Modelos;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Empresa")
public class Empresa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "nombre", unique = true)
    private String nombre;
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "telefono")
    private String telefono;
    @Column(name = "nit", unique = true)
    private String NIT;

    //Relacion empresa-usuario
    @OneToMany(mappedBy = "empresa", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference(value="empresa-usuario")
    private List<Usuario> usuarios;

    //Relacion empresa-movimiento
    @OneToMany(mappedBy = "empresa", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference(value="empresa-movimiento")
    private List<MovimientoDinero> movimientoDineros;

    public Empresa() {
    }


    public Empresa(String nombre, String direccion, String telefono, String NIT, List<Usuario> usuarios, List<MovimientoDinero> movimientoDineros) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.NIT = NIT;
        this.usuarios = usuarios;
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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNIT() {
        return NIT;
    }

    public void setNIT(String NIT) {
        this.NIT = NIT;
    }
    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
    public List<MovimientoDinero> getMovimientoDineros() {
        return movimientoDineros;
    }

    public void setMovimientoDineros(List<MovimientoDinero> movimientoDineros) {
        this.movimientoDineros = movimientoDineros;
    }
    @Override
    public String toString() {
        return "- Datos de la empresa - \n" +
                " Nombre: " + getNombre() + '\n' +
                " Direccion: " + getDireccion() + '\n' +
                " Telefono: " + getTelefono() + '\n' +
                " NIT: " + getNIT() +
                "\n---------------------- ";
    }

}
