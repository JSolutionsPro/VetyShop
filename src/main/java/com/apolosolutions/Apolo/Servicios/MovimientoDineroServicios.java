package com.apolosolutions.Apolo.Servicios;

import com.apolosolutions.Apolo.Modelos.MovimientoDinero;
import com.apolosolutions.Apolo.Repositorios.EmpresaRepositorio;
import com.apolosolutions.Apolo.Repositorios.MovimientoDineroRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class MovimientoDineroServicios {
        @Autowired
        MovimientoDineroRepositorio movimientoDineroRepositorio;
        @Autowired
        EmpresaRepositorio empresaRepositorio;

        public List<MovimientoDinero> ListarMovimientos(){
                return movimientoDineroRepositorio.findAll();
        }
        public MovimientoDinero consultarMovimiento(Integer id){
                return movimientoDineroRepositorio.findById(id).get();
        }

        public boolean guardarActualizarMovimiento(MovimientoDinero movimiento){
                MovimientoDinero mov = movimientoDineroRepositorio.save(movimiento);
                if (movimientoDineroRepositorio.findById(movimiento.getId())!=null){
                        return true;
                }
                return false;
        }

        public boolean eliminarMovimiento(Integer id){
                movimientoDineroRepositorio.deleteById(id);
                if(this.movimientoDineroRepositorio.findById(id).isPresent()){
                        return false;
                }
                return true;
        }

        public ArrayList<MovimientoDinero> consultarPorUsuario(Integer id) {
                return movimientoDineroRepositorio.findByUsuario(id);
        }
        public ArrayList<MovimientoDinero> consultarPorEmpresa(Integer id){
                return movimientoDineroRepositorio.findByEmpresa(id);
        }

        public List<MovimientoDinero> guardarActualizarMovimientos(List<MovimientoDinero> movimientoDinero){
                List<MovimientoDinero> mov = movimientoDineroRepositorio.saveAll(movimientoDinero);
                return mov;
        }

        public boolean eliminarMovimientos(Integer id, List<MovimientoDinero> movimientoList){
                movimientoDineroRepositorio.deleteAll(movimientoList);
                if(empresaRepositorio.findById(id).isPresent()) {
                        if (this.movimientoDineroRepositorio.findByEmpresa(id).isEmpty()) {
                                return true;
                        }
                }
                return false;
        }

        public List<MovimientoDinero> consultarMovimientosDeUsuariosPorEmpresa(Integer id){
                return movimientoDineroRepositorio.findMovimientosOfUsuariosByEmpresa(id);
        }

        public Long obtenerSumaMovimientos(){
                return movimientoDineroRepositorio.SumarMovimientos();
        }

}
