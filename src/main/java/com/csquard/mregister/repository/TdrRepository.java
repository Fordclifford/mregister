<<<<<<< HEAD
package com.csquard.mregister.repository;

import org.springframework.stereotype.Repository;

import com.csquard.mregister.model.Tdr;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface TdrRepository extends JpaRepository<Tdr, Long> {	  
	List<Tdr> findByAsmId( long asmId);	
	  Boolean existsBySalesAreaId(Long salesAreaId);
	  List<Tdr> findByUserId( long userId);
}
=======
package com.csquard.mregister.repository;

import org.springframework.stereotype.Repository;

import com.csquard.mregister.model.Tdr;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface TdrRepository extends JpaRepository<Tdr, Long> {	  
	List<Tdr> findByAsmId( long asmId);	
	  Boolean existsBySalesAreaId(Long salesAreaId);
	  List<Tdr> findByUserId( long userId);
}
>>>>>>> 97f7824d90a5ad7679914b4efccb4da1151ec8b0
