package com.apolosolutions.Apolo.Servicios;

import com.apolosolutions.Apolo.Modelos.Usuario;
import com.apolosolutions.Apolo.Repositorios.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioServicios {

    @Autowired
    UsuarioRepositorio usuarioRepositorio;

    //Mostrar todos los usuarios registrados
    public List<Usuario> ListarUsuarios(){
        List<Usuario> usuarioList= new ArrayList<>();
        usuarioRepositorio.findAll().forEach(usuario -> usuarioList.add(usuario));
        return usuarioList;
    }

    public Usuario consultarUsuario(Integer id){
        return usuarioRepositorio.findById(id).get();
    }

    //Metodo para guardar o actualizar un usuario
    public Usuario guardarActualizarUsuario(Usuario usuario){
        return usuarioRepositorio.save(usuario);
    }

    //Metodo para eliminar un usuario
    public boolean eliminarUsuario(Integer id){
        usuarioRepositorio.deleteById(id);
        if (usuarioRepositorio.findById(id).isPresent()){
            return false; //Si esta, no lo elimino (false)
        }
        return true; //Si no esta, si lo elimino (true)
    }

    //Metodo para buscar los usuarios por Empresa
    public ArrayList<Usuario> consultarUsuariosPorEmpresa(Integer id){
        return usuarioRepositorio.findByEmpresa(id);
    }

}
