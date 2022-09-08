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

    @GetMapping(path = "/empresas/{id}/movimientos") //ID de la empresa
    public List<MovimientoDinero> consultarMovimientosPorEmpresa(@PathVariable("id") Integer id) {
        return movimientoDineroServicios.consultarPorEmpresa(id);
    }

    @GetMapping(path = "/usuarios/{id}/movimientos") //ID del usuario
    public List<MovimientoDinero> consultarMovimientosPorUsuarios(@PathVariable("id") Integer id) {
        return movimientoDineroServicios.consultarPorUsuario(id);
    }
/*
    @PostMapping(path = "/empresas/{id}/movimientos") //ID del ? REVISAR
    public MovimientoDinero guardarMovimiento(@PathVariable("id") Integer id, @RequestBody MovimientoDinero movimiento) {
        return movimientoDineroServicios.guardarActualizarMovimiento(movimiento);
    }

    @PatchMapping(path = "/empresas/{id}/movimientos") //ID del movimiento
    public MovimientoDinero actualizarMovimiento(@PathVariable("id") Integer id, @RequestBody MovimientoDinero movimiento){
        MovimientoDinero mov = movimientoDineroServicios.consultarMovimiento(id);
        mov.setConcepto(movimiento.getConcepto());
        mov.setMonto(movimiento.getMonto());
        mov.setEmpleado(movimiento.getEmpleado());
        return movimientoDineroServicios.guardarActualizarMovimiento(mov);
    }

    @DeleteMapping(path = "/empresas/{id}/movimientos") //ID del movimiento
    public String eliminarMovimiento (@PathVariable ("id") Integer id){
        boolean respuesta= movimientoDineroServicios.eliminarMovimiento(id);
        if (respuesta){
            return "Si se elimino el movimiento con ID "+id;
        }
        else {
            return "No se elimino el movimiento con ID "+id;
        }
    } */

    @PostMapping(path = "/empresas/{id}/movimientos") //ID del
    public List<MovimientoDinero> guardarMovimientos(@PathVariable("id") Integer id, @RequestBody List<MovimientoDinero> movimiento) {
        return movimientoDineroServicios.guardarActualizarMovimientos(movimiento);
    }

    @PatchMapping(path = "/empresas/{id}/movimientos") //ID del movimiento
    public List<MovimientoDinero> actualizarMovimientos(@PathVariable("id") Integer id, @RequestBody List<MovimientoDinero> movimientos){
        List<MovimientoDinero> mov = movimientoDineroServicios.consultarPorEmpresa(id);
        for (int i = 0; i < mov.size(); i++) {
            mov.get(i).setConcepto(movimientos.get(i).getConcepto());
            mov.get(i).setMonto(movimientos.get(i).getMonto());
            mov.get(i).setEmpleado(movimientos.get(i).getEmpleado());
            movimientoDineroServicios.guardarActualizarMovimiento(mov.get(i));
        }

        return mov;
    }

    @DeleteMapping(path = "/empresas/{id}/movimientos") //ID del movimiento
    public void eliminarMovimientos(@PathVariable ("id") Integer id){
        List<MovimientoDinero> movimientoList = movimientoDineroServicios.consultarPorEmpresa(id);
        movimientoDineroServicios.eliminarMovimientos(movimientoList);
    }
}
