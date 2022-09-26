package com.apolosolutions.Apolo.Controlador;

import com.apolosolutions.Apolo.Modelos.Empresa;
import com.apolosolutions.Apolo.Repositorios.EmpresaRepositorio;
import com.apolosolutions.Apolo.Servicios.EmpresaServicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class EmpresaControlador {

    @Autowired
    EmpresaServicios empresaServicios;

    @Autowired
    EmpresaRepositorio empresaRepositorio;

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

    @GetMapping({"/"})
    public String index(){
        return "index";
    }

    @GetMapping({"/login"})
    public String login(){
        return "login";
    }

    @GetMapping({"/Inicio"})
    public String principal(){
        return "apolo";
    }

    //Metodo para ver todas las empresas
    @GetMapping({"/VerEmpresas"})

    public String viewEmpresas( @RequestParam(value="pagina", required=false, defaultValue = "0") int NumeroPagina,
                                @RequestParam(value="medida", required=false, defaultValue = "8") int medida,
                                Model model,@ModelAttribute("mensaje") String mensaje){
    Page<Empresa> paginaEmpresas= empresaRepositorio.findAll(PageRequest.of(NumeroPagina,medida, Sort.by("id").ascending()));
        model.addAttribute("emplist",paginaEmpresas.getContent());
        model.addAttribute("paginas",new int[paginaEmpresas.getTotalPages()]);
        model.addAttribute("paginaActual", NumeroPagina);
        model.addAttribute("mensaje",mensaje);
        setRedirectTo("/VerEmpresas");
    return "verEmpresas";

    }

    //Metodo para agregar empresas
    @GetMapping("/AgregarEmpresa")
    public String nuevaEmpresa(Model model, @ModelAttribute("mensaje") String mensaje){
        Empresa empresa= new Empresa();
        model.addAttribute("emp",empresa);
        model.addAttribute("mensaje", mensaje);
        return "agregarEmpresa";

    }

    //Metodo para guardar empresa
    @PostMapping("/GuardarEmpresa")
    public String guardarEmpresa(Empresa emp, RedirectAttributes redirectAttributes){
        if (empresaServicios.guardarActualizarEmpresa(emp)) {
            redirectAttributes.addFlashAttribute("mensaje","saveOK");
            validarRedirect(redirectTo);
            return "redirect:" + getRedirectTo();
        }
        redirectAttributes.addFlashAttribute("mensaje","saveError");
        return "redirect:/AgregarEmpresa";

    }

    //Metodo Editar Empresa
    @GetMapping("/EditarEmpresa/{id}")
    public String editarEmpresa(Model model, @PathVariable Integer id, @ModelAttribute("mensaje") String mensaje){
        Empresa empresa = empresaServicios.consultarEmpresa(id);
        model.addAttribute("emp", empresa);
        model.addAttribute("mensaje", mensaje);
        return "editarEmpresa";

    }

    //Metodo Actualizar Empresa
    @PostMapping("/ActualizarEmpresa")
    public String actualizarEmpresa (@ModelAttribute("emp") Empresa empresa, RedirectAttributes redirectAttributes){
        if (empresaServicios.guardarActualizarEmpresa(empresa)){
            redirectAttributes.addFlashAttribute("mensaje","updateOK");
            validarRedirect(redirectTo);
            return "redirect:" + getRedirectTo();

        }
        redirectAttributes.addFlashAttribute("mensaje","updateError");
        return "redirect:/EditarEmpresa/" + empresa.getId();

    }

    //Metodo Eliminar Empresa
    @GetMapping("/EliminarEmpresa/{id}")
    public  String eliminarEmpresa (@PathVariable Integer id, RedirectAttributes redirectAttributes){
        if (empresaServicios.eliminarEmpresa(id)){
            redirectAttributes.addFlashAttribute("mensaje", "deleteOK");
            validarRedirect(redirectTo);
            return "redirect:" + getRedirectTo();
        }
        redirectAttributes.addFlashAttribute("mensaje", "deleteError");
            return "redirect:" + getRedirectTo();

    }

}
