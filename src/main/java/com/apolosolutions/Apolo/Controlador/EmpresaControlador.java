package com.apolosolutions.Apolo.Controlador;

import com.apolosolutions.Apolo.Modelos.Empresa;
import com.apolosolutions.Apolo.Servicios.EmpresaServicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmpresaControlador {

    @Autowired
    EmpresaServicios empresaServicios;

    @GetMapping("/empresas") //Listado de empresas
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
    }

}
