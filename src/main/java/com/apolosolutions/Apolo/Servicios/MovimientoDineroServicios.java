package com.apolosolutions.Apolo.Servicios;

import com.apolosolutions.Apolo.Modelos.MovimientoDinero;
import com.apolosolutions.Apolo.Repositorios.EmpresaRepositorio;
import com.apolosolutions.Apolo.Repositorios.MovimientoDineroRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class MovimientoDineroServicios {
        @Autowired
        MovimientoDineroRepositorio movimientoDineroRepositorio;
        @Autowired
        EmpresaRepositorio empresaRepositorio;

        public Page<MovimientoDinero> ListarMovimientos(Pageable pageable){
                return movimientoDineroRepositorio.findAll(pageable);
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

        public Page<MovimientoDinero> consultarPorUsuario(Integer id, Pageable pageable) {
                return movimientoDineroRepositorio.findByUsuario(id, pageable);
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

        public Page<MovimientoDinero> consultarMovimientosDeUsuariosPorEmpresa(Integer id, Pageable pageable){
                return movimientoDineroRepositorio.findMovimientosOfUsuariosByEmpresa(id, pageable);
        }

        public Long obtenerSumaMovimientos(){
                return movimientoDineroRepositorio.SumarMovimientos();
        }
        public Long obtenerIngresosMovimientos(){
                return movimientoDineroRepositorio.SumarIngresosMovimientos();
        }
        public Long obtenerEgresosMovimientos(){
                return movimientoDineroRepositorio.SumarEgresosMovimientos();
        }
}
