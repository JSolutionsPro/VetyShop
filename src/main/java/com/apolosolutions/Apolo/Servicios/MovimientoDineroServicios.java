package com.apolosolutions.Apolo.Servicios;

import com.apolosolutions.Apolo.Modelos.MovimientoDinero;
import com.apolosolutions.Apolo.Repositorios.MovimientoDineroRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class MovimientoDineroServicios {
        @Autowired
        MovimientoDineroRepositorio movimientoDineroRepositorio;

        public List<MovimientoDinero> ListarMovimientos(){
                List<MovimientoDinero> movimientolist= new ArrayList<>();
                movimientoDineroRepositorio.findAll().forEach(movimiento -> movimientolist.add(movimiento));
                return movimientolist;
        }
        public MovimientoDinero consultarMovimiento(Integer id){
                return movimientoDineroRepositorio.findById(id).get();
        }

        public MovimientoDinero guardarActualizarMovimiento(MovimientoDinero movimientoDinero){
                MovimientoDinero mov = movimientoDineroRepositorio.save(movimientoDinero);
                return mov;
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

        public void eliminarMovimientos(List<MovimientoDinero> movimientoList){
                movimientoDineroRepositorio.deleteAll(movimientoList);
        }

}
