package com.apolosolutions.Apolo.Repositorios;

import com.apolosolutions.Apolo.Modelos.MovimientoDinero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface MovimientoDineroRepositorio extends JpaRepository<MovimientoDinero, Integer> {

    @Query(value ="SELECT * FROM transacciones WHERE usuario_id= ?1", nativeQuery = true)
    public abstract ArrayList<MovimientoDinero> findByUsuario(Integer id);

    //Metodo para filtrar movimientos de un usuario por empresa
    @Query(value="SELECT * FROM transacciones WHERE usuario_id IN (SELECT id FROM usuario WHERE empresa_id= ?1)", nativeQuery = true)
    public abstract ArrayList<MovimientoDinero> findMovimientosOfUsuariosByEmpresa(Integer id);

    @Query(value ="SELECT * FROM transacciones WHERE empresa_id= ?1", nativeQuery = true)
    public abstract ArrayList<MovimientoDinero> findByEmpresa(Integer id);

    //Metodo para ver la suma de TODOS LOS MOVIMIENTOS
    @Query(value="SELECT SUM(monto) FROM transacciones", nativeQuery = true)
    public abstract Long SumarMovimientos();
}
