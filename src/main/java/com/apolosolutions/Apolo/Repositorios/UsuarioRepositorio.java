package com.apolosolutions.Apolo.Repositorios;

import com.apolosolutions.Apolo.Modelos.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario,Integer> {

    public abstract Page<Usuario> findByEmpresa_id(Integer id, Pageable pageable);

    public abstract Usuario findByCorreo(String correo);
}
