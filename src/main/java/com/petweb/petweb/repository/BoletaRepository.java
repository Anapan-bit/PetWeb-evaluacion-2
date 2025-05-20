package com.petweb.petweb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.petweb.petweb.model.Boleta;
import java.time.LocalDate;

@Repository
public interface BoletaRepository extends JpaRepository<Boleta, Integer> {

    // Query para buscar cliente por ID
    @Query("SELECT b FROM Boleta b WHERE b.cliente.id = :clienteId")
    List<Boleta> findByClienteId(@Param("clienteId") Integer clienteId);

    // Query para buscar por fecha de emison
    @Query("SELECT b FROM Boleta b WHERE b.fechaEmision = :fecha")
    List<Boleta> findByFechaEmision(@Param("fecha") LocalDate fecha);

}
