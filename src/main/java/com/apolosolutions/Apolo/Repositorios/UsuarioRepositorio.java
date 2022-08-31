package com.apolosolutions.Apolo.Repositorios;

import com.apolosolutions.Apolo.Modelos.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario,Integer> {
}
