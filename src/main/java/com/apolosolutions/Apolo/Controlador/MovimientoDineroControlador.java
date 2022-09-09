package com.apolosolutions.Apolo.Controlador;

import com.apolosolutions.Apolo.Modelos.MovimientoDinero;
import com.apolosolutions.Apolo.Repositorios.MovimientoDineroRepositorio;
import com.apolosolutions.Apolo.Servicios.MovimientoDineroServicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MovimientoDineroControlador {

    @Autowired
    MovimientoDineroServicios movimientoDineroServicios;

    @GetMapping(path = "/movimientos")
    public List<MovimientoDinero> listarMovimientos(){
        return movimientoDineroServicios.ListarMovimientos();
    }

    @PostMapping(path = "/movimientos") //Ojo el Body debe traer el ID de usuario y empresa
    public MovimientoDinero guardarMovimiento(@RequestBody MovimientoDinero movimiento) {
        return movimientoDineroServicios.guardarActualizarMovimiento(movimiento);
    }

    @PatchMapping(path = "/movimientos/{id}") //ID del movimiento
    public MovimientoDinero actualizarMovimiento(@PathVariable("id") Integer id, @RequestBody MovimientoDinero movimiento){
        MovimientoDinero mov = movimientoDineroServicios.consultarMovimiento(id);
        mov.setConcepto(movimiento.getConcepto());
        mov.setMonto(movimiento.getMonto());
        mov.setUsuario(movimiento.getUsuario());
        mov.setEmpresa(movimiento.getEmpresa());
        return movimientoDineroServicios.guardarActualizarMovimiento(mov);
    }

    @DeleteMapping(path = "/movimientos/{id}") //ID del movimiento
    public String eliminarMovimiento (@PathVariable ("id") Integer id){
        boolean respuesta= movimientoDineroServicios.eliminarMovimiento(id);
        if (respuesta){
            return "Si se elimino el movimiento con ID "+id;
        }
        else {
            return "No se elimino el movimiento con ID "+id;
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
