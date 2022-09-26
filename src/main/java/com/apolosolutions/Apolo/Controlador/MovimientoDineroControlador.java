package com.apolosolutions.Apolo.Controlador;

import com.apolosolutions.Apolo.Modelos.Empresa;
import com.apolosolutions.Apolo.Modelos.MovimientoDinero;
import com.apolosolutions.Apolo.Modelos.Usuario;
import com.apolosolutions.Apolo.Modelos.enums.RolEmpleado;
import com.apolosolutions.Apolo.Repositorios.MovimientoDineroRepositorio;
import com.apolosolutions.Apolo.Servicios.EmpresaServicios;
import com.apolosolutions.Apolo.Servicios.MovimientoDineroServicios;
import com.apolosolutions.Apolo.Servicios.UsuarioServicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
public class MovimientoDineroControlador {

    @Autowired
    MovimientoDineroServicios movimientoDineroServicios;

    @Autowired
    MovimientoDineroRepositorio movimientoDineroRepositorio;

    @Autowired
    UsuarioServicios usuarioServicios;

    @Autowired
    EmpresaServicios empresaServicios;

    //Metodo para traer el usuario que inicio sesion
    public Usuario infoSesion(){
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        String correo=auth.getName();
        Usuario usuarioSesion=usuarioServicios.movimientoPorCorreo(correo);
        return usuarioSesion;
    }

    //Metodo para calcular el total de los movimientos, ingresos y egresos
    public List<Long> totalMovimientos(Page<MovimientoDinero> movimientos){

        List<MovimientoDinero> movs= movimientos.getContent();

        List<Long> lista= new ArrayList<Long>();
        Long total = 0l;
        Long ingresos = 0l;
        Long egresos = 0l;

        for( int i=0 ; i<movs.size(); i++){
            total += movs.get(i).getMonto();
            if (movs.get(i).getMonto() > 0) {
                ingresos += movs.get(i).getMonto();
            }
            if (movs.get(i).getMonto() < 0) {
                egresos += movs.get(i).getMonto();
            }
        }

        Collections.addAll(lista,total,ingresos,egresos);

        return lista;
    }

    //Metodos para redireccionar a la vista adecuada
    public String redirectTo="";
    public String getRedirectTo() {return redirectTo;}
    public void setRedirectTo(String redirectTo) { this.redirectTo = redirectTo;}


    public void validarRedirect(String redirect){
    switch(infoSesion().getRol()) {
        case ROLE_USER:
            if (redirect=="/VerMovimientos"|| redirect=="") {
                redirectTo="/Inicio";
            }
        case ROLE_ADMIN:
            if (redirect=="") {
                redirectTo="/Inicio";
            } else if (redirect.contains("/VerMovimiento/Usuario/")) {
                redirectTo="/VerMovimientos/MisMovimientos/";
            }
        default:
            break;
    }}



    @GetMapping(path = "/VerMovimientos")
    public String listarMovimientos( @RequestParam(value="pagina", required=false, defaultValue = "0") int NumeroPagina,
                                     @RequestParam(value="medida", required=false, defaultValue = "7") int medida,
                                     Model model,
                                     @ModelAttribute("mensaje") String mensaje){
        Pageable paginado = PageRequest.of(NumeroPagina,medida, Sort.by("id").ascending());
        Page<MovimientoDinero> paginaMovimientos= movimientoDineroServicios.ListarMovimientos(paginado);
        List<Long> totalesMovimientos= totalMovimientos(movimientoDineroServicios.ListarMovimientos(Pageable.unpaged()));
        model.addAttribute("movlist",paginaMovimientos.getContent());
        model.addAttribute("totales",totalesMovimientos);
        model.addAttribute("paginas",new int[paginaMovimientos.getTotalPages()]);
        model.addAttribute("paginaActual", NumeroPagina);
        model.addAttribute("mensaje",mensaje);
        setRedirectTo("/VerMovimientos");
        return "verMovimientos";
    }

    @GetMapping(path = "/VerMovimientos/MiEmpresa/")
    public String consultaMovimientosDeUsuariosPorEmpresa( @RequestParam(value="pagina", required=false, defaultValue = "0") int NumeroPagina,
                                     @RequestParam(value="medida", required=false, defaultValue = "7") int medida,
                                     Model model,
                                            @ModelAttribute("mensaje") String mensaje){
        Pageable paginado = PageRequest.of(NumeroPagina,medida, Sort.by("id").ascending());
        Page<MovimientoDinero> paginaMovimientos= movimientoDineroServicios.consultarMovimientosDeUsuariosPorEmpresa(infoSesion().getEmpresa_id(), paginado);
        List<Long> totalesMovimientos= totalMovimientos(movimientoDineroServicios.consultarMovimientosDeUsuariosPorEmpresa(infoSesion().getEmpresa_id(), Pageable.unpaged()));
        model.addAttribute("movlist",paginaMovimientos.getContent());
        model.addAttribute("totales",totalesMovimientos);
        model.addAttribute("paginas", new int[paginaMovimientos.getTotalPages()]);
        model.addAttribute("paginaActual", NumeroPagina);
        model.addAttribute("mensaje",mensaje);
        setRedirectTo("/VerMovimientos/MiEmpresa/");
        return "verMovimientos";
    }

    @GetMapping(path = "/VerMovimientos/MisMovimientos/")
    public String consultarMovimientosPorUsuarioSesion( @RequestParam(value="pagina", required=false, defaultValue = "0") int NumeroPagina,
                                                  @RequestParam(value="medida", required=false, defaultValue = "7") int medida,
                                                  Model model,
                                                  @ModelAttribute("mensaje") String mensaje){
        Pageable paginado = PageRequest.of(NumeroPagina,medida, Sort.by("id").ascending());
        Page<MovimientoDinero> paginaMovimientos= movimientoDineroServicios.consultarPorUsuario(infoSesion().getId(), paginado);
        List<Long> totalesMovimientos= totalMovimientos(movimientoDineroServicios.consultarPorUsuario(infoSesion().getId(), Pageable.unpaged()));
        model.addAttribute("movlist",paginaMovimientos.getContent());
        model.addAttribute("totales",totalesMovimientos);
        model.addAttribute("paginas", new int[paginaMovimientos.getTotalPages()]);
        model.addAttribute("paginaActual", NumeroPagina);
        model.addAttribute("mensaje",mensaje);
        setRedirectTo("/VerMovimientos/MisMovimientos/");
        return "verMovimientos";
    }

    @GetMapping(path = "/VerMovimiento/Usuario/{id}")
    public String consultarMovimientosPorUsuario( @RequestParam(value="pagina", required=false, defaultValue = "0") int NumeroPagina,
                                            @RequestParam(value="medida", required=false, defaultValue = "7") int medida,
                                            Model model,
                                            @ModelAttribute("mensaje") String mensaje,
                                                  @PathVariable("id") Integer id){
        Pageable paginado = PageRequest.of(NumeroPagina,medida, Sort.by("id").ascending());
        Page<MovimientoDinero> paginaMovimientos= movimientoDineroServicios.consultarPorUsuario(id, paginado);
        List<Long> totalesMovimientos= totalMovimientos(movimientoDineroServicios.consultarPorUsuario(id, Pageable.unpaged()));
        model.addAttribute("movlist",paginaMovimientos.getContent());
        model.addAttribute("totales",totalesMovimientos);
        model.addAttribute("paginas", new int[paginaMovimientos.getTotalPages()]);
        model.addAttribute("paginaActual", NumeroPagina);
        model.addAttribute("mensaje",mensaje);
        setRedirectTo("/VerMovimiento/Usuario/"+id);
        return "verMovimientos";
    }

    @GetMapping(path = "/AgregarMovimiento")
    public String agregarMovimiento(Model model, @ModelAttribute("mensaje") String mensaje){
        MovimientoDinero mov= new MovimientoDinero();
        model.addAttribute("mov", mov);
        model.addAttribute("usuarioSesion",infoSesion());
        List<Empresa> listaEmpresas = empresaServicios.listaEmpresas();
        model.addAttribute("emprelist", listaEmpresas);
        model.addAttribute("mensaje", mensaje);
        return "agregarMovimiento";
    }

    @PostMapping(path = "/GuardarMovimiento") //Ojo el Body debe traer el ID de usuario y empresa
    public String guardarMovimiento(MovimientoDinero movimiento, RedirectAttributes redirectAttributes) {
         if(movimientoDineroServicios.guardarActualizarMovimiento(movimiento)){
             redirectAttributes.addFlashAttribute("mensaje","saveOK");
            //return validarRedirect("redirect:/VerMovimientos","redirect:/VerMovimientos/MiEmpresa/");
             validarRedirect(getRedirectTo());
             return "redirect:" + getRedirectTo();
         }
        redirectAttributes.addFlashAttribute("mensaje","saveError");
        return "redirect:/AgregarMovimiento";
    }

    @GetMapping(path ="/EditarMovimiento/{id}") //ID del movimiento
    public String editarMovimiento(Model model, @PathVariable("id") Integer id, @ModelAttribute("mensaje") String mensaje){
        MovimientoDinero movimiento= movimientoDineroServicios.consultarMovimiento(id);
        model.addAttribute("mov", movimiento);
        List<Usuario> listaUsuarios = usuarioServicios.ListarUsuarios();
        model.addAttribute("usualist", listaUsuarios);
        List<Empresa> listaEmpresas = empresaServicios.listaEmpresas();
        model.addAttribute("emprelist", listaEmpresas);
        model.addAttribute("mensaje", mensaje);
        return "editarMovimiento";
    }

    @PostMapping(path = "/ActualizarMovimiento")
    public String actualizarMovimiento(@ModelAttribute ("movimiento") MovimientoDinero movimiento, RedirectAttributes redirectAttributes){
        if(movimientoDineroServicios.guardarActualizarMovimiento(movimiento)){
            redirectAttributes.addFlashAttribute("mensaje","updateOK");
            if (!getRedirectTo().contains("/VerMovimiento/Usuario/")){
                validarRedirect(getRedirectTo());
                return "redirect:" + getRedirectTo();
            }
            return "redirect:" + getRedirectTo();
        }
        redirectAttributes.addFlashAttribute("mensaje","updateError");
        return "redirect:/EditarMovimiento/"+movimiento.getId();
    }

    @GetMapping(path = "/EliminarMovimiento/{id}") //ID del movimiento
    public String eliminarMovimiento (@PathVariable ("id") Integer id, RedirectAttributes redirectAttributes){
        if (movimientoDineroServicios.eliminarMovimiento(id)){
            redirectAttributes.addFlashAttribute("mensaje", "deleteOK");
            validarRedirect(redirectTo);
            return "redirect:" + getRedirectTo();
        }
            redirectAttributes.addFlashAttribute("memsaje", "deleteError");
            return "redirect:" + getRedirectTo();
    }

    /*@GetMapping(path = "/empresas/{id}/movimientos") //ID de la empresa
    public List<MovimientoDinero> consultarMovimientosPorEmpresa(@PathVariable("id") Integer id) {
        return movimientoDineroServicios.consultarPorEmpresa(id);
    }*/

}
