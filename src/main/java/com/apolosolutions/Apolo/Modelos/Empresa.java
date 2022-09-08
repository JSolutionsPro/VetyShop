package com.apolosolutions.Apolo.Modelos;

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
    @Column(name = "nit")
    private String NIT;

    @OneToMany(mappedBy = "empresa", fetch = FetchType.LAZY)
    private List<Usuario> usuarios;
    //relacion con tabla movimientos
    @OneToMany(mappedBy = "empresa", fetch = FetchType.LAZY)
    private List<MovimientoDinero> movimientoDinero;

    public Empresa() {
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public Empresa(String nombre, String direccion, String telefono, String NIT, List<Usuario> usuarios, List<MovimientoDinero> movimientoDinero) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.NIT = NIT;
        this.usuarios = usuarios;
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
