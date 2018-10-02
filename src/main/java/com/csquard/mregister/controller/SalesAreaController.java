<<<<<<< HEAD
package com.csquard.mregister.controller;


import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.csquard.mregister.exception.ResourceNotFoundException;
import com.csquard.mregister.model.SalesArea;
import com.csquard.mregister.model.SalesRegion;
import com.csquard.mregister.payload.ApiResponse;
import com.csquard.mregister.payload.SalesAreaRequest;
import com.csquard.mregister.repository.AsmRepository;
import com.csquard.mregister.repository.SalesAreaRepository;
import com.csquard.mregister.repository.SalesRegionRepository;

@RestController
@RequestMapping("/api")
public class SalesAreaController {
	

    @Autowired
	AsmRepository asmRepository;
    
    @Autowired
   	SalesAreaRepository salesAreaRepository;
    
    @Autowired
   	SalesRegionRepository salesRegionRepository;
    
	// Get All SalesAreas
    @GetMapping("/salesareas")
    public List<SalesArea> getAllSalesAreas() {
        return salesAreaRepository.findAllSalesAreas();
    }
    //find all SalesAreas by SalesRegionId
    @GetMapping("/salesareas/salesregion/{salesRegionId}")
    @PreAuthorize("hasAnyRole('ASM','ADMIN','DISTRIBUTOR')")
    public List<SalesArea> getAllSalesAreasBySalesRegionId(@PathVariable (value = "salesRegionId") Long salesRegionId) {
    	if(!salesRegionRepository.existsById(salesRegionId)) {
            throw new ResourceNotFoundException("SalesRegion", "id", salesRegionId);
        }
        return salesAreaRepository.findBySalesRegionId(salesRegionId);
    }
	
 // Create a new salesarea

    @PostMapping("/salesarea/create")
    @PreAuthorize("hasAnyRole('ADMIN','DISTRIBUTOR','ASM')")
    public ApiResponse createSalesArea(@Valid @RequestBody SalesAreaRequest salesAreaRequest) {    

        // Creating sales area
    	
     salesRegionRepository.findById(salesAreaRequest.getSales_region_id())		
                 .orElseThrow(() -> new ResourceNotFoundException("SalesRegion", "id", salesAreaRequest.getSales_region_id()));
        
        SalesArea salesArea = new SalesArea(salesAreaRequest.getName(), salesAreaRequest.getSales_region_id());
        		       		
        salesAreaRepository.save(salesArea);

  return new ApiResponse(true, "Sales Area Added Successfully");
    }

 // Get a Single salesarea
    @GetMapping("/salesarea/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','DISTRIBUTOR','ASM')")
    public Optional<SalesArea> getSalesAreaById(@PathVariable(value = "id") Long id) {
        return  salesAreaRepository.findById(id);
    }

 // Update a SalesArea
    @PutMapping("/salesarea/{id}")
    
    public SalesArea updateSalesArea(@PathVariable(value = "id") Long salesAreaId,
                                            @Valid @RequestBody SalesArea salesAreaDetails) {

    	SalesArea salesArea = salesAreaRepository.findById(salesAreaId)
                .orElseThrow(() -> new ResourceNotFoundException("SalesArea", "id", salesAreaId));
    	
    	  salesRegionRepository.findById(salesAreaDetails.getSales_region_id())		
          .orElseThrow(() -> new ResourceNotFoundException("SalesRegion", "id", salesAreaDetails.getSales_region_id()));

    	
    	
      salesArea.setName(salesAreaDetails.getName());   
      salesArea.setSales_region_id(salesAreaDetails.getSales_region_id());
      SalesArea updatedSalesArea = salesAreaRepository.save(salesArea);
        return updatedSalesArea;
    }
    
    // Delete a SalesArea
    @DeleteMapping("/salesareas/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','DISTRIBUTOR','ASM')")
    public ResponseEntity<?> deleteSalesArea(@PathVariable(value = "id") Long salesAreaId) {
        SalesArea salesArea = salesAreaRepository.findById(salesAreaId)
                .orElseThrow(() -> new ResourceNotFoundException("SalesArea", "id", salesAreaId));
        salesAreaRepository.delete(salesArea);        
        return ResponseEntity.ok().body(new ApiResponse(true, "Sales Area deleted successfully"));
    }
	
}
=======
package com.csquard.mregister.controller;


import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.csquard.mregister.exception.ResourceNotFoundException;
import com.csquard.mregister.model.SalesArea;
import com.csquard.mregister.model.SalesRegion;
import com.csquard.mregister.payload.ApiResponse;
import com.csquard.mregister.payload.SalesAreaRequest;
import com.csquard.mregister.repository.AsmRepository;
import com.csquard.mregister.repository.SalesAreaRepository;
import com.csquard.mregister.repository.SalesRegionRepository;

@RestController
@RequestMapping("/api")
public class SalesAreaController {
	

    @Autowired
	AsmRepository asmRepository;
    
    @Autowired
   	SalesAreaRepository salesAreaRepository;
    
    @Autowired
   	SalesRegionRepository salesRegionRepository;
    
	// Get All SalesAreas
    @GetMapping("/salesareas")
    public List<SalesArea> getAllSalesAreas() {
        return salesAreaRepository.findAllSalesAreas();
    }
    //find all SalesAreas by SalesRegionId
    @GetMapping("/salesareas/salesregion/{salesRegionId}")
//    @PreAuthorize("hasAnyRole('ASM','ADMIN','DISTRIBUTOR')")
    public List<SalesArea> getAllSalesAreasBySalesRegionId(@PathVariable (value = "salesRegionId") Long salesRegionId) {
    	if(!salesRegionRepository.existsById(salesRegionId)) {
            throw new ResourceNotFoundException("SalesRegion", "id", salesRegionId);
        }
        return salesAreaRepository.findBySalesRegionId(salesRegionId);
    }
	
 // Create a new salesarea

    @PostMapping("/salesarea/create")
    @PreAuthorize("hasAnyRole('ADMIN','DISTRIBUTOR','ASM')")
    public ApiResponse createSalesArea(@Valid @RequestBody SalesAreaRequest salesAreaRequest) {    

        // Creating sales area
    	
     salesRegionRepository.findById(salesAreaRequest.getSales_region_id())		
                 .orElseThrow(() -> new ResourceNotFoundException("SalesRegion", "id", salesAreaRequest.getSales_region_id()));
        
        SalesArea salesArea = new SalesArea(salesAreaRequest.getName(), salesAreaRequest.getSales_region_id());
        		       		
        salesAreaRepository.save(salesArea);

  return new ApiResponse(true, "Sales Area Added Successfully");
    }

 // Get a Single salesarea
    @GetMapping("/salesarea/{id}")
//    @PreAuthorize("hasAnyRole('ADMIN','DISTRIBUTOR','ASM')")
    public Optional<SalesArea> getSalesAreaById(@PathVariable(value = "id") Long id) {
        return  salesAreaRepository.findById(id);
    }

 // Update a SalesArea
    @PutMapping("/salesarea/{id}")
    
    public SalesArea updateSalesArea(@PathVariable(value = "id") Long salesAreaId,
                                            @Valid @RequestBody SalesArea salesAreaDetails) {

    	SalesArea salesArea = salesAreaRepository.findById(salesAreaId)
                .orElseThrow(() -> new ResourceNotFoundException("SalesArea", "id", salesAreaId));
    	
    	  salesRegionRepository.findById(salesAreaDetails.getSales_region_id())		
          .orElseThrow(() -> new ResourceNotFoundException("SalesRegion", "id", salesAreaDetails.getSales_region_id()));

    	
    	
      salesArea.setName(salesAreaDetails.getName());   
      salesArea.setSales_region_id(salesAreaDetails.getSales_region_id());
      SalesArea updatedSalesArea = salesAreaRepository.save(salesArea);
        return updatedSalesArea;
    }
    
    // Delete a SalesArea
    @DeleteMapping("/salesareas/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','DISTRIBUTOR','ASM')")
    public ResponseEntity<?> deleteSalesArea(@PathVariable(value = "id") Long salesAreaId) {
        SalesArea salesArea = salesAreaRepository.findById(salesAreaId)
                .orElseThrow(() -> new ResourceNotFoundException("SalesArea", "id", salesAreaId));
        salesAreaRepository.delete(salesArea);        
        return ResponseEntity.ok().body(new ApiResponse(true, "Sales Area deleted successfully"));
    }
	
}
>>>>>>> 97f7824d90a5ad7679914b4efccb4da1151ec8b0
