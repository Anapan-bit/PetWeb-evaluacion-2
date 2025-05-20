package com.petweb.petweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.petweb.petweb.model.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer> {

}
