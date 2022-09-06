package com.apolosolutions.Apolo.Controlador;

import com.apolosolutions.Apolo.Modelos.Usuario;
import com.apolosolutions.Apolo.Servicios.UsuarioServicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UsuarioControlador {

    @Autowired
    UsuarioServicios usuarioServicios;

    @GetMapping(path = "/usuarios/") //ver todos los usuarios
    public List<Usuario> verUsuario(){
        return usuarioServicios.ListarUsuarios();
    }

    @PostMapping("/usuarios") //guardar nuevo usuario
    public Usuario guardarActualizarUsuario(@RequestBody Usuario usuario) {
        return usuarioServicios.guardarActualizarUsuario(usuario);
    }

    @GetMapping("/usuarios/{id}") //Consultar por ID
    public Usuario usuarioPorId(@PathVariable("id") Integer id){
        return usuarioServicios.consultarUsuario(id);
    }

    @GetMapping("/empresas/{id}/usuarios") //Consultar por empresa
    public ArrayList<Usuario> UsuarioPorEmpresa(@PathVariable("id") Integer id){
        return usuarioServicios.obtenerPorEmpresa(id);
    }

    @PatchMapping("usuarios/{id}") //Actualizar usuarios
    public Usuario actualizarUsuario(@PathVariable("id") Integer id, @RequestBody Usuario usuario){
        Usuario usuario1=usuarioServicios.consultarUsuario(id);
        usuario1.setNombre(usuario1.getNombre());
        usuario1.setCorreo(usuario1.getCorreo());
        usuario1.setEmpresa(usuario1.getEmpresa());
        usuario1.setRol(usuario1.getRol());
        return usuarioServicios.guardarActualizarUsuario(usuario1);
    }

    @DeleteMapping("/usuarios/{id}") //Eliminar usuarios
    public String eliminarUsuario(@PathVariable("id") Integer id){
        boolean respuesta=usuarioServicios.eliminarUsuario(id);
        if(respuesta){
            return "Se ha eliminado correctamente el usuario con id "+id;
        }
        return "No se logro eliminar usuario con id "+id;
    }


}
