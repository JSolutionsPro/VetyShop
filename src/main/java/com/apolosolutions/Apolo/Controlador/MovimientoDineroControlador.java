package com.apolosolutions.Apolo.Controlador;

import com.apolosolutions.Apolo.Modelos.Empresa;
import com.apolosolutions.Apolo.Modelos.MovimientoDinero;
import com.apolosolutions.Apolo.Modelos.Usuario;
import com.apolosolutions.Apolo.Servicios.EmpresaServicios;
import com.apolosolutions.Apolo.Servicios.MovimientoDineroServicios;
import com.apolosolutions.Apolo.Servicios.UsuarioServicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class MovimientoDineroControlador {

    @Autowired
    MovimientoDineroServicios movimientoDineroServicios;

    @Autowired
    UsuarioServicios usuarioServicios;

    @Autowired
    EmpresaServicios empresaServicios;


    @GetMapping(path = "/VerMovimientos")
    public String listarMovimientos(Model model){
        List<MovimientoDinero> movlist = movimientoDineroServicios.ListarMovimientos();
        model.addAttribute("movlist", movlist);
        Long sumaMovimientos= movimientoDineroServicios.obtenerSumaMovimientos();
        model.addAttribute("sumaMontos",sumaMovimientos);
        return "verMovimientos";
    }

    @GetMapping(path = "/AgregarMovimiento")
    public String agregarMovimiento(Model model){
        MovimientoDinero mov= new MovimientoDinero();
        model.addAttribute("mov", mov);
        List<Usuario> listaUsuarios = usuarioServicios.ListarUsuarios();
        model.addAttribute("usualist", listaUsuarios);
        List<Empresa> listaEmpresas = empresaServicios.listaEmpresas();
        model.addAttribute("emprelist", listaEmpresas);
        return "agregarMovimiento";
    }

    @PostMapping(path = "/GuardarMovimiento") //Ojo el Body debe traer el ID de usuario y empresa
    public String guardarMovimiento(MovimientoDinero movimiento, RedirectAttributes redirectAttributes) {
         if(movimientoDineroServicios.guardarActualizarMovimiento(movimiento)== true){
             return "redirect:/VerMovimientos";
         }
        return "redirect:/AgregarMovimiento";
    }

    @GetMapping(path ="/EditarMovimiento/{id}") //ID del movimiento
    public String editarMovimiento(Model model, @PathVariable("id") Integer id){
        MovimientoDinero movimiento= movimientoDineroServicios.consultarMovimiento(id);
        model.addAttribute("mov", movimiento);
        List<Usuario> listaUsuarios = usuarioServicios.ListarUsuarios();
        model.addAttribute("usualist", listaUsuarios);
        List<Empresa> listaEmpresas = empresaServicios.listaEmpresas();
        model.addAttribute("emprelist", listaEmpresas);

        return "editarMovimiento";
    }

    @PostMapping(path = "/ActualizarMovimiento")
    public String actualizarMovimiento(@ModelAttribute ("movimiento") MovimientoDinero movimiento, RedirectAttributes redirectAttributes){
        if(movimientoDineroServicios.guardarActualizarMovimiento(movimiento)){
            return "redirect:/VerMovimientos";
        }
        return "redirect:/EditarMovimiento/"+movimiento.getId();
    }

    @GetMapping(path = "/EliminarMovimiento/{id}") //ID del movimiento
    public String eliminarMovimiento (@PathVariable ("id") Integer id, RedirectAttributes redirectAttributes){
        if (movimientoDineroServicios.eliminarMovimiento(id)){
            return "redirect:/VerMovimientos";
        }
        else {
            return "redirect:/VerMovimientos";
        }
    }

    @GetMapping(path = "/usuarios/{id}/movimientos") //ID del usuario
    public List<MovimientoDinero> consultarMovimientosPorUsuario(@PathVariable("id") Integer id) {
        return movimientoDineroServicios.consultarPorUsuario(id);
    }

    @GetMapping(path = "/empresas/usuarios/{id}/movimientos") //ID de la empresa
    public List<MovimientoDinero> consultaMovimientosDeUsuariosPorEmpresa(@PathVariable ("id") Integer id){
        return movimientoDineroServicios.consultarMovimientosDeUsuariosPorEmpresa(id);
    }

    @GetMapping(path = "/empresas/{id}/movimientos") //ID de la empresa
    public List<MovimientoDinero> consultarMovimientosPorEmpresa(@PathVariable("id") Integer id) {
        return movimientoDineroServicios.consultarPorEmpresa(id);
    }

    @PostMapping(path = "/empresas/{id}/movimientos") //ID de la empresa
    public List<MovimientoDinero> guardarMovimientos(@PathVariable("id") Integer id, @RequestBody List<MovimientoDinero> movimiento) {
        List<MovimientoDinero> mov = movimiento;
        for (int i = 0; i < mov.size(); i++) {
            mov.get(i).setEmpresa_id(id);
        }
        return movimientoDineroServicios.guardarActualizarMovimientos(mov);
    }

    @PatchMapping(path = "/empresas/{id}/movimientos") //ID de la empresa
    public List<MovimientoDinero> actualizarMovimientos(@PathVariable("id") Integer id, @RequestBody List<MovimientoDinero> movimientos){
        List<MovimientoDinero> mov = movimientoDineroServicios.consultarPorEmpresa(id);
        for (int i = 0; i < mov.size(); i++) {
            mov.get(i).setConcepto(movimientos.get(i).getConcepto());
            mov.get(i).setMonto(movimientos.get(i).getMonto());
            mov.get(i).setUsuario(movimientos.get(i).getUsuario());
            movimientoDineroServicios.guardarActualizarMovimiento(mov.get(i));
        }
        return mov;
    }

    @DeleteMapping(path = "/empresas/{id}/movimientos") //ID de la empresa
    public String eliminarMovimientos(@PathVariable ("id") Integer id){
        List<MovimientoDinero> movimientoList = movimientoDineroServicios.consultarPorEmpresa(id);
        boolean respuesta = movimientoDineroServicios.eliminarMovimientos(id,movimientoList);
        if (respuesta){
            return "Si se eliminaron los movimientos de la empresa ID "+id;
        }
        else {
            return "No se eliminaron los movimientos de la empresa ID "+id;
        }
    }
}
