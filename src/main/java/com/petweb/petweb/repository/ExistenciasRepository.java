package com.petweb.petweb.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.petweb.petweb.model.Bodega;
import com.petweb.petweb.model.Existencias;
import com.petweb.petweb.model.Producto;

@Repository
public interface ExistenciasRepository extends JpaRepository<Existencias, Integer> {

    Optional<Existencias> findByProductoAndBodega(Producto producto, Bodega bodega);

    List<Existencias> findByProducto_Id(Integer productoId);

}
