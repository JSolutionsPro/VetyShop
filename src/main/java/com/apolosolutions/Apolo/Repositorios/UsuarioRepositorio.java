package com.apolosolutions.Apolo.Repositorios;

import com.apolosolutions.Apolo.Modelos.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario,Integer> {

    @Query(value="SELECT * FROM Usuario WHERE empresa_id= ?1", nativeQuery=true)
    public abstract ArrayList<Usuario> findByEmpresa(Integer id);

}
