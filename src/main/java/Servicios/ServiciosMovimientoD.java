package Servicios;

import com.apolosolutions.Apolo.Modelos.Usuario;
import com.apolosolutions.Apolo.Modelos.Empresa;
import com.apolosolutions.Apolo.Modelos.MovimientoDinero;
import com.apolosolutions.Apolo.Repositorios.MovimientoDineroRepositorio;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class ServiciosMovimientoDinero {
        @Autowired
        MovimientoDineroRepositorio movimientoDineroRepositorio;

        public List<MovimientoDinero> getAllMovimientos(){
                return new ArrayList<>(movimientoDineroRepositorio.findAll());
        }
        public MovimientoDinero getMovimientoById(Integer id){
                return movimientoDineroRepositorio.findById(id).get();}

        public MovimientoDinero SaveOrUpdateMovimiento(MovimientoDinero movimientoDinero){
                MovimientoDinero mov = movimientoDineroRepositorio.save(movimientoDinero);
                return mov;
        }

        public boolean deleteMovimiento(Integer id){
                movimientoDineroRepositorio.deleteById(id);
                if(this.movimientoDineroRepositorio.findById(id).isPresent()){
                        return false;
                }
                return true;
        }

        public ArrayList<MovimientoDinero> obtenerPorUsuario(Integer id) {
                return movimientoDineroRepositorio.findByUsuario(id);
        }
        public ArrayList<MovimientoDinero>obtenerPorEmpresa(Integer id){
                return movimientoDineroRepositorio.findByEmpresa(id);
        }
}
