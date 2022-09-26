package com.apolosolutions.Apolo.Controlador;

import com.apolosolutions.Apolo.Modelos.Empresa;
import com.apolosolutions.Apolo.Modelos.Usuario;
import com.apolosolutions.Apolo.Repositorios.UsuarioRepositorio;
import com.apolosolutions.Apolo.Servicios.EmpresaServicios;
import com.apolosolutions.Apolo.Servicios.UsuarioServicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    @Autowired
    UsuarioRepositorio usuarioRepositorio;

    public String redirectTo="";
    public String getRedirectTo() {return redirectTo;}
    public void setRedirectTo(String redirectTo) { this.redirectTo = redirectTo;}


    public void validarRedirect(String redirect){
        switch(getRedirectTo()) {
            case "":
                    redirectTo="/Inicio";
            default:
                break;
        }}

    @GetMapping(path = "/VerUsuarios") //ver todos los usuarios
    public String viewEmpleados(@RequestParam(value="pagina", required=false, defaultValue = "0") int NumeroPagina,
                                @RequestParam(value="medida", required=false, defaultValue = "8") int medida,Model model, @ModelAttribute("mensaje") String mensaje){
            Page<Usuario> paginaUsuario= usuarioRepositorio.findAll(PageRequest.of(NumeroPagina,medida, Sort.by("id").ascending()));
        model.addAttribute("userlist",paginaUsuario.getContent());
        model.addAttribute("paginas",new int[paginaUsuario.getTotalPages()]);
        model.addAttribute("paginaActual", NumeroPagina);
        model.addAttribute("mensaje", mensaje);
        setRedirectTo("/VerUsuarios");
        return "verUsuarios";
    }

    @GetMapping("/VerUsuarios/Empresa/{id}") //Consultar por ID empresa
    public String UsuarioPorEmpresa(@RequestParam(value="pagina", required=false, defaultValue = "0") int NumeroPagina,
                                                @RequestParam(value="medida", required=false, defaultValue = "8") int medida,
                                                Model model,
                                                @ModelAttribute("mensaje") String mensaje,
                                                @PathVariable("id") Integer id){
        Pageable paginado = PageRequest.of(NumeroPagina,medida, Sort.by("id").ascending());
        Page<Usuario> paginaUsuario= usuarioServicios.obtenerPorEmpresa(id, paginado);
        model.addAttribute("userlist",paginaUsuario.getContent());
        model.addAttribute("paginas",new int[paginaUsuario.getTotalPages()]);
        model.addAttribute("paginaActual", NumeroPagina);
        model.addAttribute("mensaje", mensaje);
        setRedirectTo("/VerUsuarios/Empresa/"+id);
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
        String contrasenaEncriptada=codificador().encode(usuario.getContrasena());
        usuario.setContrasena(contrasenaEncriptada);
        if(usuarioServicios.guardarActualizarUsuario(usuario)){
            redirectAttributes.addFlashAttribute("mensaje","saveOK");
            validarRedirect(redirectTo);
            return "redirect:" + getRedirectTo();
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
        Integer id= usuario.getId();
        String RegistroAntiguo=usuarioServicios.consultarUsuario(id).getContrasena();
        if(!usuario.getContrasena().equals(RegistroAntiguo)){
            String contrasenaEncriptada=codificador().encode(usuario.getContrasena());
            usuario.setContrasena(contrasenaEncriptada);
        }
        if(usuarioServicios.guardarActualizarUsuario(usuario)){
            redirectAttributes.addFlashAttribute("mensaje","updateOK");
            validarRedirect(redirectTo);
            return "redirect:" + getRedirectTo();
        }
        redirectAttributes.addFlashAttribute("mensaje", "updateError");
        return "redirect:/ActualizarUsuario/";
    }

    @GetMapping("/EliminarUsuario/{id}")
    public String eliminarUsuario(@PathVariable Integer id, RedirectAttributes redirectAttributes){
        if (usuarioServicios.eliminarUsuario(id)){
            redirectAttributes.addFlashAttribute("mensaje","deleteOK");
            validarRedirect(redirectTo);
            return "redirect:" + getRedirectTo();
        }
        redirectAttributes.addFlashAttribute("mensaje","deleteError");
        return "redirect:" + getRedirectTo();
    }

    @RequestMapping(value="/Denegado")
    public String accesoDenegado(){
        return "accessDenied";
    }

    @Bean //Encriptación
    public PasswordEncoder codificador(){
        return new BCryptPasswordEncoder();
    }

}
