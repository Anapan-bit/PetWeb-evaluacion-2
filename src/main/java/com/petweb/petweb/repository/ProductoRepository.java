package com.petweb.petweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.petweb.petweb.model.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {

}
