package com.csquard.mregister.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.csquard.mregister.exception.ResourceNotFoundException;
import com.csquard.mregister.model.Tdr;
import com.csquard.mregister.repository.AsmRepository;
import com.csquard.mregister.repository.SalesAreaRepository;
import com.csquard.mregister.repository.TdrRepository;
import com.csquard.mregister.repository.UserRepository;

@RestController
@RequestMapping("/api")
public class TdrController {
	

	
    @Autowired
	TdrRepository tdrRepository;
    
    @Autowired
   	AsmRepository asmRepository;
    
    @Autowired
   	UserRepository userRepository;
    

    @Autowired
   	SalesAreaRepository salesAreaRepository;
    
	// Get All tdrs
    @GetMapping("/tdrs")
    @PreAuthorize("hasAnyRole('ASM','ADMIN','DISTRIBUTOR')")
    public List<Tdr> getAlltdrs() {
        return tdrRepository.findAll();
    }
    //find all tdrs by asmId
    @GetMapping("/tdrs/asm/{asmId}")
    @PreAuthorize("hasAnyRole('ASM','ADMIN','DISTRIBUTOR')")
    public List<Tdr> getAllTdrsByAsmId(@PathVariable (value = "asmId") Long asmId) {
    	if(!asmRepository.existsById(asmId)) {
            throw new ResourceNotFoundException("Asm", "id", asmId);
        }
        return tdrRepository.findByAsmId(asmId);
    }

 // Get a Single tdr
    @GetMapping("/tdrs/{id}")
    @PreAuthorize("hasAnyRole('ASM','ADMIN','DISTRIBUTOR')")
    public Tdr getTdrById(@PathVariable(value = "id") Long tdrId) {
        return tdrRepository.findById(tdrId)
                .orElseThrow(() -> new ResourceNotFoundException("tdr", "id", tdrId));
    }
    

 // Update an tdr
    @PutMapping("/tdrs/{id}")
    @PreAuthorize("hasAnyRole('ASM','ADMIN','DISTRIBUTOR')")
    public Tdr updatedTdr(@PathVariable(value = "id") Long tdrId,
                                            @Valid @RequestBody Tdr tdrDetails) {

        Tdr tdr = tdrRepository.findById(tdrId)
                .orElseThrow(() -> new ResourceNotFoundException("tdr", "id", tdrId));
        
        if(!userRepository.existsById(tdrDetails.getUser_id())) {
            throw new ResourceNotFoundException("User", "id", tdrDetails.getUser_id());
        }
        if(!asmRepository.existsById(tdrDetails.getAsm_id())) {
            throw new ResourceNotFoundException("Asm", "id", tdrDetails.getAsm_id());
        }
        if(!salesAreaRepository.existsById(tdrDetails.getSales_area_id())) {
            throw new ResourceNotFoundException("Asm", "id", tdrDetails.getSales_area_id());
        }

        tdr.setFirst_name(tdrDetails.getFirst_name());
        tdr.setLast_name(tdrDetails.getLast_name());
        tdr.setUser_id(tdrDetails.getUser_id());
        tdr.setSales_area_id(tdrDetails.getSales_area_id());
        tdr.setAsm_id(tdrDetails.getAsm_id());
        tdr.setId_no(tdrDetails.getId_no());
               
        Tdr updatedTdr = tdrRepository.save(tdr);
        return updatedTdr;
    }
    
    // Delete a Tdr
    @DeleteMapping("/tdrs/{id}")
    @PreAuthorize("hasAnyRole('ASM','ADMIN','DISTRIBUTOR')")
    public ResponseEntity<?> deleteTdr(@PathVariable(value = "id") Long tdrId) {
        Tdr tdr = tdrRepository.findById(tdrId)
                .orElseThrow(() -> new ResourceNotFoundException("tdr", "id", tdrId));

        tdrRepository.delete(tdr);

        return ResponseEntity.ok().build();
    }
	
}
