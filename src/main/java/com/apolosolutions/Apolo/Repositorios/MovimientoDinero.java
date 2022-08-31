package com.apolosolutions.Apolo.Repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovimientoDinero extends JpaRepository<MovimientoDinero, Integer> {
}
