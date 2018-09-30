package com.csquard.mregister.controller;

import java.util.List;

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
import com.csquard.mregister.model.SalesRegion;
import com.csquard.mregister.payload.ApiResponse;
import com.csquard.mregister.repository.AsmRepository;
import com.csquard.mregister.repository.SalesRegionRepository;

@RestController
@RequestMapping("/api")
public class SalesRegionController {
	

    @Autowired
	AsmRepository asmRepository;
    
    @Autowired
   	SalesRegionRepository salesRegionRepostory;
	// Get All SalesRegions
    @GetMapping("/salesregions")
//    @PreAuthorize("hasAnyRole('ADMIN','DISTRIBUTOR')")
    public List<SalesRegion> getAllSalesRegions() {
        return salesRegionRepostory.findAll();
    }
    
 // Create a new SalesRegion
    @PostMapping("/salesregion/create")
    @PreAuthorize("hasAnyRole('ADMIN','DISTRIBUTOR')")
    public SalesRegion createSalesRegion(@Valid @RequestBody SalesRegion salesRegion) {
        return salesRegionRepostory.save(salesRegion);
    }

 // Get a Single SalesRegion
    @GetMapping("/salesregion/{id}")
//    @PreAuthorize("hasAnyRole('ADMIN','DISTRIBUTOR')")
    public SalesRegion getSalesRegionById(@PathVariable(value = "id") Long salesRegionId) {
        return salesRegionRepostory.findById(salesRegionId)
                .orElseThrow(() -> new ResourceNotFoundException("SalesRegion", "id", salesRegionId));
    }

 // Update a SalesRegion
    @PutMapping("/salesregion/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','DISTRIBUTOR')")
    public SalesRegion updateSalesRegion(@PathVariable(value = "id") Long salesRegionId,
                                            @Valid @RequestBody SalesRegion salesRegionDetails) {

    	SalesRegion salesRegion = salesRegionRepostory.findById(salesRegionId)
                .orElseThrow(() -> new ResourceNotFoundException("SalesRegion", "id", salesRegionId));
    	
      salesRegion.setName(salesRegionDetails.getName());                        
      SalesRegion updatedSalesRegion = salesRegionRepostory.save(salesRegion);
        return updatedSalesRegion;
    }
    
    // Delete a SalesRegion
    @DeleteMapping("/salesregion/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','DISTRIBUTOR')")
    public ResponseEntity<?> deleteSalesRegion(@PathVariable(value = "id") Long salesRegionId) {
        SalesRegion salesRegion = salesRegionRepostory.findById(salesRegionId)
                .orElseThrow(() -> new ResourceNotFoundException("SalesRegion", "id", salesRegionId));
        salesRegionRepostory.delete(salesRegion);        
        return ResponseEntity.ok().body(new ApiResponse(true, "Sales Region deleted successfully"));
    }
	
}
