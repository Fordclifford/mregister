package com.csquard.mregister.repository;

import org.springframework.stereotype.Repository;

import com.csquard.mregister.model.SalesArea;
import com.csquard.mregister.model.Tdr;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
@Repository

public interface SalesAreaRepository extends JpaRepository<SalesArea, Long>{
@Query(value = "SELECT * FROM sales_areas ", 
    nativeQuery=true)
List<SalesArea> findAllSalesAreas();



}
