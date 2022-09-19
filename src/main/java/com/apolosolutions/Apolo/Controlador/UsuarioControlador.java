package com.apolosolutions.Apolo.Controlador;

import com.apolosolutions.Apolo.Modelos.Empresa;
import com.apolosolutions.Apolo.Modelos.Usuario;
import com.apolosolutions.Apolo.Servicios.EmpresaServicios;
import com.apolosolutions.Apolo.Servicios.UsuarioServicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UsuarioControlador {

    @Autowired
    UsuarioServicios usuarioServicios;

    @Autowired
    EmpresaServicios empresaServicios;

    @GetMapping(path = "/VerUsuarios") //ver todos los usuarios
    public String viewEmpleados(Model model, @ModelAttribute("mensaje") String mensaje){
        List<Usuario> listaUsuarios=usuarioServicios.ListarUsuarios();
        model.addAttribute("listausuarios",listaUsuarios);
        model.addAttribute("mensaje", mensaje);
        return "verUsuarios";
    }

    @GetMapping("/AgregarUsuarios") //Agregar nuevo usuario
    public String nuevoUsuario(Model model, @ModelAttribute("mensaje") String mensaje){
        Usuario us= new Usuario();
        model.addAttribute("us", us);
        model.addAttribute("mensaje", mensaje);
        List<Empresa> listaEmpresas = empresaServicios.listaEmpresas();
        model.addAttribute("listaempresas", listaEmpresas);
        return "agregarUsuario";
    }

    @PostMapping("/GuardarUsuario")

    public String guardarusuario(Usuario usuario, RedirectAttributes redirectAttributes){
        if(usuarioServicios.guardarActualizarUsuario(usuario)){
            redirectAttributes.addFlashAttribute("mensaje","saveOK");
            return "redirect:/VerUsuarios";
        }
        redirectAttributes.addFlashAttribute("mensaje","saveError");
        return "redirect:/GuardarUsuario";
    }

    @GetMapping("/EditarUsuario/{id}")

    public String editarUsuario(Model model, @PathVariable Integer id, @ModelAttribute("mensaje") String mensaje){
        Usuario usuario=usuarioServicios.consultarUsuario(id);
        model.addAttribute("usuario", usuario);
        model.addAttribute("mensaje", mensaje);
        List<Empresa> listaEmpresas = empresaServicios.listaEmpresas();
        model.addAttribute("listaempresa", listaEmpresas);
        return "editarUsuario";
    }

    @PostMapping("/ActualizarUsuario")

    public String actualizarUsuario(@ModelAttribute("usuario") Usuario usuario, RedirectAttributes redirectAttributes){
        if(usuarioServicios.guardarActualizarUsuario(usuario)){
            redirectAttributes.addFlashAttribute("mensaje","updateOK");
            return "redirect:/VerUsuarios";
        }
        redirectAttributes.addFlashAttribute("mensaje", "updateError");
        return "redirect:/ActualizarUsuario/";
    }

    @GetMapping("/EliminarUsuario/{id}")
    public String eliminarUsuario(@PathVariable Integer id, RedirectAttributes redirectAttributes){
        if (usuarioServicios.eliminarUsuario(id)){
            redirectAttributes.addFlashAttribute("mensaje","deleteOK");
            return "redirect:/VerUsuarios";
        }
        redirectAttributes.addFlashAttribute("mensaje","deleteError");
        return "redirect:/VerUsuarios";
    }







    /*@GetMapping("/usuarios/{id}") //Consultar por ID
    public Usuario usuarioPorId(@PathVariable("id") Integer id){
        return usuarioServicios.consultarUsuario(id);
    }

    @PatchMapping("usuarios/{id}") //Actualizar usuarios
    public Usuario actualizarUsuario(@PathVariable("id") Integer id, @RequestBody Usuario usuario){
        Usuario usuario1=usuarioServicios.consultarUsuario(id);
        usuario1.setNombre(usuario.getNombre());
        usuario1.setCorreo(usuario.getCorreo());
        usuario1.setEmpresa(usuario.getEmpresa());
        usuario1.setMovimientos(usuario.getMovimientos());
        usuario1.setRol(usuario.getRol());
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
    @GetMapping("/empresas/{id}/usuarios") //Consultar por ID empresa
    public ArrayList<Usuario> UsuarioPorEmpresa(@PathVariable("id") Integer id){
        return usuarioServicios.obtenerPorEmpresa(id);
    }










    /*@GetMapping(path = "/usuarios") //ver todos los usuarios
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

    @PatchMapping("usuarios/{id}") //Actualizar usuarios
    public Usuario actualizarUsuario(@PathVariable("id") Integer id, @RequestBody Usuario usuario){
        Usuario usuario1=usuarioServicios.consultarUsuario(id);
        usuario1.setNombre(usuario.getNombre());
        usuario1.setCorreo(usuario.getCorreo());
        usuario1.setEmpresa(usuario.getEmpresa());
        usuario1.setMovimientos(usuario.getMovimientos());
        usuario1.setRol(usuario.getRol());
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
    @GetMapping("/empresas/{id}/usuarios") //Consultar por ID empresa
    public ArrayList<Usuario> UsuarioPorEmpresa(@PathVariable("id") Integer id){
        return usuarioServicios.obtenerPorEmpresa(id);
    }*/

}
