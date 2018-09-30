package com.csquard.mregister.repository;

import java.util.*;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.csquard.mregister.model.Agent;
import com.csquard.mregister.model.Tdr;
@Repository
public interface AgentRepository extends JpaRepository<Agent,Long > {
	
	
	@Query(nativeQuery = true, value = "SELECT `agent_no`, `created_at`, `updated_at`, `created_by`, `updated_by`, `address`, `device_type`, `id_attachment`, `id_no`, `imei_no`, `location`, `mobile`,"
			+ " `signature`, `signed_contact`, `signed_name`, `town`, `sales_area_id`, `sales_region_id`, `tdr_id` FROM `mregister_test`.`agents`")
	List<Agent> findAllAgents();
	
	Long countByTdrId(Tdr tdr_id);
		  
	List <Agent> findBySalesRegionId( Long salesRegionId);
	
	long countBySalesRegionId(Long salesRegionId);
	

	List <Agent> findBySalesAreaId( Long salesAreaId);
	
	long countBySalesAreaId( Long sales_area_id);

	Page<Agent> findByCreatedBy(Long agent_no, Pageable pageable);	
	List<Agent> findByTdrId(long tdrId);
	long countByCreatedBy(Long id);
	 
}

