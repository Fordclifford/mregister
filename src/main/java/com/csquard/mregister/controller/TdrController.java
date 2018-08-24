package com.csquard.mregister.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.csquard.mregister.exception.ResourceNotFoundException;
import com.csquard.mregister.model.Tdr;
import com.csquard.mregister.repository.TdrRepository;

@RestController
@RequestMapping("/api")
public class TdrController {
	

	
    @Autowired
	TdrRepository tdrRepository;
    
	// Get All tdrs
    @GetMapping("/tdrs")
    public List<Tdr> getAlltdrs() {
        return tdrRepository.findAll();
    }
    //find all tdrs by asmId
    @GetMapping("/tdrs/asm/{asmId}")
    public List<Tdr> getAllTdrsByAsmId(@PathVariable (value = "asmId") Long asmId) {
        return tdrRepository.findByAsmId(asmId);
    }

 // Get a Single tdr
    @GetMapping("/tdrs/{id}")
    public Tdr getTdrById(@PathVariable(value = "id") Long tdrId) {
        return tdrRepository.findById(tdrId)
                .orElseThrow(() -> new ResourceNotFoundException("tdr", "id", tdrId));
    }

 // Update an tdr
    @PutMapping("/tdrs/{id}")
    public Tdr updatetTdr(@PathVariable(value = "id") Long tdrId,
                                            @Valid @RequestBody Tdr tdrDetails) {

        Tdr tdr = tdrRepository.findById(tdrId)
                .orElseThrow(() -> new ResourceNotFoundException("tdr", "id", tdrId));

//        tdrs.setFirst_name(tdrDetails.getFirst_name());
//        tdrs.setLast_name(tdrDetails.getLast_name());
//        tdrs.setUser_id(tdrDetails.getUser_id());
//        tdrs.setSales_area_id(tdrDetails.getSales_area_id());
               
        Tdr updatedtdr = tdrRepository.save(tdr);
        return updatedtdr;
    }
    
    // Delete a Tdr
    @DeleteMapping("/tdrs/{id}")
    public ResponseEntity<?> deleteTdr(@PathVariable(value = "id") Long tdrId) {
        Tdr tdr = tdrRepository.findById(tdrId)
                .orElseThrow(() -> new ResourceNotFoundException("tdr", "id", tdrId));

        tdrRepository.delete(tdr);

        return ResponseEntity.ok().build();
    }
	
}
