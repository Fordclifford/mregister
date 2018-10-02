<<<<<<< HEAD
package com.csquard.mregister.repository;

import org.springframework.stereotype.Repository;

import com.csquard.mregister.model.SalesArea;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
@Repository

public interface SalesAreaRepository extends JpaRepository<SalesArea, Long>{
@Query(value = "SELECT * FROM sales_areas ", 
    nativeQuery=true)
List<SalesArea> findAllSalesAreas();

List <SalesArea> findBySalesRegionId( Long salesRegionId);


}
=======
package com.csquard.mregister.repository;

import org.springframework.stereotype.Repository;

import com.csquard.mregister.model.SalesArea;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
@Repository

public interface SalesAreaRepository extends JpaRepository<SalesArea, Long>{
@Query(value = "SELECT * FROM sales_areas ", 
    nativeQuery=true)
List<SalesArea> findAllSalesAreas();

List <SalesArea> findBySalesRegionId( Long salesRegionId);


}
>>>>>>> 97f7824d90a5ad7679914b4efccb4da1151ec8b0
