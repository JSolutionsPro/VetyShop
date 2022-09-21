package com.apolosolutions.Apolo.Controlador;

import com.apolosolutions.Apolo.Modelos.Empresa;
import com.apolosolutions.Apolo.Repositorios.EmpresaRepositorio;
import com.apolosolutions.Apolo.Servicios.EmpresaServicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

    @GetMapping({"/Inicio"})
    public String principal(){
        return "apolo";
    }

    //metodo para ver empresas

    @GetMapping({"/VerEmpresas"})

    public String viewEmpresas( @RequestParam(value="pagina", required=false, defaultValue = "0") int NumeroPagina,
                                @RequestParam(value="medida", required=false, defaultValue = "4") int medida,
                                Model model,@ModelAttribute("mensaje") String mensaje){
    Page<Empresa> paginaEmpresas= empresaRepositorio.findAll(PageRequest.of(NumeroPagina,medida));
        model.addAttribute("emplist",paginaEmpresas.getContent());
        model.addAttribute("paginas",new int[paginaEmpresas.getTotalPages()]);
        model.addAttribute("paginaActual", NumeroPagina);
        model.addAttribute("mensaje",mensaje);
    return "verEmpresas";

    }
    //metodo para agregar empresas
    @GetMapping("/AgregarEmpresa")
    public String nuevaEmpresa(Model model, @ModelAttribute("mensaje") String mensaje){
        Empresa empresa= new Empresa();
        model.addAttribute("emp",empresa);
        model.addAttribute("mensaje", mensaje);
        return "agregarEmpresa";

    }

    //metodo para guardar empresa
    @PostMapping("/GuardarEmpresa")
    public String guardarEmpresa(Empresa emp, RedirectAttributes redirectAttributes){
        if (empresaServicios.guardarActualizarEmpresa(emp)) {
            redirectAttributes.addFlashAttribute("mensaje","saveOK");
            return "redirect:/VerEmpresas";
        }
        redirectAttributes.addFlashAttribute("mensaje","saveError");
        return "redirect:/AgregarEmpresa";

    }
    //metodo Editar Empresa
    @GetMapping("/EditarEmpresa/{id}")
    public String editarEmpresa(Model model, @PathVariable Integer id, @ModelAttribute("mensaje") String mensaje){
        Empresa empresa = empresaServicios.consultarEmpresa(id);
        model.addAttribute("emp", empresa);
        model.addAttribute("mensaje", mensaje);
        return "editarEmpresa";

    }
    //metodo Actualizar Empresa
    @PostMapping("/ActualizarEmpresa")
    public String actualizarEmpresa (@ModelAttribute("emp") Empresa empresa, RedirectAttributes redirectAttributes){
        if (empresaServicios.guardarActualizarEmpresa(empresa)){
            redirectAttributes.addFlashAttribute("mensaje","updateOK");
            return "redirect:/VerEmpresas";

        }
        redirectAttributes.addFlashAttribute("mensaje","updateError");
        return "redirect:/EditarEmpresa/" + empresa.getId();

    }
    //metodo Eliminar Empresa
    @GetMapping("/EliminarEmpresa/{id}")
    public  String eliminarEmpresa (@PathVariable Integer id, RedirectAttributes redirectAttributes){
        if (empresaServicios.eliminarEmpresa(id)){
            redirectAttributes.addFlashAttribute("mensaje", "deleteOK");
            return "redirect:/VerEmpresas";
        }
        redirectAttributes.addFlashAttribute("memsaje", "deleteError");
        return "redirect:/VerEmpresas";

    }




    /*GetMapping("/empresas") //Listado de empresas
    public List<Empresa> verEmpresas(){
        return empresaServicios.listaEmpresas();
    }

    @PostMapping("/empresas") // Crear una empresa nueva
    public Empresa guardarEmpresa(@RequestBody Empresa empresa){
        return empresaServicios.guardarActualizarEmpresa(empresa);
    }

    @GetMapping("/empresas/{id}")
    public Empresa empresaPorId(@PathVariable("id") Integer id){
        return empresaServicios.consultarEmpresa(id);
    }

    @PatchMapping("/empresas/{id}")
    public Empresa actualizarEmpresa(@PathVariable("id") Integer id, @RequestBody Empresa empresa){
        Empresa empresa1 = empresaServicios.consultarEmpresa(id);
        empresa1.setNombre(empresa.getNombre());
        empresa1.setDireccion(empresa.getDireccion());
        empresa1.setTelefono(empresa.getTelefono());
        empresa1.setNIT(empresa.getNIT());
        return empresaServicios.guardarActualizarEmpresa(empresa1);
    }

    @DeleteMapping("/empresas/{id}")
    public String eliminarEmpresa(@PathVariable("id") Integer id){
        boolean respuesta = empresaServicios.eliminarEmpresa(id);
        if (respuesta){
            return "Se elimino la empresa con id " + id;
        }
        else {
            return "No se pudo eliminar la empresa con id " + id;
        }
    }*/

}
