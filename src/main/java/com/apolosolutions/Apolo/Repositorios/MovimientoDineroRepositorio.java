package com.apolosolutions.Apolo.Repositorios;

import com.apolosolutions.Apolo.Modelos.MovimientoDinero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovimientoDineroRepositorio extends JpaRepository<MovimientoDinero, Integer> {
}
