package com.petweb.petweb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.petweb.petweb.model.Boleta;
import com.petweb.petweb.model.Carrito;

@Repository
public interface CarritoRepository extends JpaRepository<Carrito, Integer> {

    List<Carrito> findByBoleta(Boleta boleta);
}
