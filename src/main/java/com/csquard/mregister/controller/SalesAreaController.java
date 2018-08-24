package com.csquard.mregister.controller;


import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
    
	// Get All SalesRegions
    @GetMapping("/salesareas")
    public List<SalesArea> getAllSalesAreas() {
        return salesAreaRepository.findAllSalesAreas();
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
    public Optional<SalesArea> getSalesAreaById(@PathVariable(value = "id") Long id) {
        return  salesAreaRepository.findById(id);
    }

// // Update a SalesRegion
//    @PutMapping("/salesregions/{id}")
//    public SalesRegion updateSalesRegion(@PathVariable(value = "id") Long salesRegionId,
//                                            @Valid @RequestBody SalesRegion salesRegionDetails) {
//
//    	SalesRegion salesRegion = salesRegionRepostory.findById(salesRegionId)
//                .orElseThrow(() -> new ResourceNotFoundException("SalesRegion", "id", salesRegionId));
//    	
//      salesRegion.setName(salesRegionDetails.getName());                        
//      SalesRegion updatedSalesRegion = salesRegionRepostory.save(salesRegion);
//        return updatedSalesRegion;
//    }
    
//    // Delete a Asm
//    @DeleteMapping("/salesregions/{id}")
//    public ResponseEntity<?> deleteAsm(@PathVariable(value = "id") Long salesRegionId) {
//        SalesRegion salesRegion = salesRegionRepostory.findById(salesRegionId)
//                .orElseThrow(() -> new ResourceNotFoundException("SalesRegion", "id", salesRegionId));
//        salesRegionRepostory.delete(salesRegion);        
//        return ResponseEntity.ok().body(new ApiResponse(true, "Sales Region deleted successfully"));
//    }
//	
}
