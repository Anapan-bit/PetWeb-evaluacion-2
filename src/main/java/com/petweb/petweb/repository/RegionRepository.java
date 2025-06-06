package com.petweb.petweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.petweb.petweb.model.Region;

@Repository
public interface RegionRepository extends JpaRepository<Region, Integer> {

}
