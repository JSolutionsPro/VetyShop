package com.apolosolutions.Apolo.Repositorios;

import com.apolosolutions.Apolo.Modelos.MovimientoDinero;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface MovimientoDineroRepositorio extends JpaRepository<MovimientoDinero, Integer> {

    @Query(value ="SELECT * FROM transacciones WHERE usuario_id= ?1", nativeQuery = true)
    public abstract Page<MovimientoDinero> findByUsuario(Integer id, Pageable pageable);

    //Metodo para filtrar movimientos de un usuario por empresa
    @Query(value="SELECT * FROM transacciones WHERE usuario_id IN (SELECT id FROM usuario WHERE empresa_id= ?1)", nativeQuery = true)
    public abstract Page<MovimientoDinero> findMovimientosOfUsuariosByEmpresa(Integer id, Pageable pageable);

    @Query(value ="SELECT * FROM transacciones WHERE empresa_id= ?1", nativeQuery = true)
    public abstract ArrayList<MovimientoDinero> findByEmpresa(Integer id);

    //Metodo para ver la suma de TODOS LOS MOVIMIENTOS
    @Query(value="SELECT SUM(monto) FROM transacciones", nativeQuery = true)
    public abstract Long SumarMovimientos();

    //Metodo para ver la suma de TODOS LOS EGRESOS DE MOVIMIENTOS
    @Query(value="SELECT SUM(monto) FROM transacciones WHERE monto <0", nativeQuery = true)
    public abstract Long SumarEgresosMovimientos();

    //Metodo para ver la suma de TODOS LOS INGRESOS DE MOVIMIENTOS
    @Query(value="SELECT SUM(monto) FROM transacciones WHERE monto >0", nativeQuery = true)
    public abstract Long SumarIngresosMovimientos();

}
