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

    @PostMapping(path = "/empresas/{id}/movimientos") //ID del usuario REVISAR
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
    }
}
