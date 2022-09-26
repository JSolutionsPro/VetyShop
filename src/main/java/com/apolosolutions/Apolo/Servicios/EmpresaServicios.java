package com.apolosolutions.Apolo.Servicios;

import com.apolosolutions.Apolo.Modelos.Empresa;
import com.apolosolutions.Apolo.Repositorios.EmpresaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmpresaServicios {
    @Autowired
    EmpresaRepositorio empresaRepositorio;

    //Metodo que retorna una lista de las empresas
    public List<Empresa> listaEmpresas (){
        List<Empresa> empresaList = new ArrayList<>();
        empresaRepositorio.findAll().forEach(empresa -> empresaList.add(empresa));
        return empresaList;
    }
    //Metodo que permite consultar empresa por id
    public  Empresa consultarEmpresa(Integer id){
        return empresaRepositorio.findById(id).get();
    }

    //Metodo para guardar o actualizar empresa|
    public Boolean  guardarActualizarEmpresa( Empresa empresa){
        Empresa empresa1 =empresaRepositorio.save(empresa);
        if (empresaRepositorio.findById(empresa1.getId()) != null) {
            return true;
        }
        return false;
    }
    //Metodo para eliminar empresa
    public boolean eliminarEmpresa (Integer id){
        empresaRepositorio.deleteById(id);
        if (empresaRepositorio.findById(id).isPresent()){
            return false;
        }
        return true;
    }


}
