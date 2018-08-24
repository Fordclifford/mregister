package com.csquard.mregister.controller;

import com.csquard.mregister.exception.ResourceNotFoundException;
import com.csquard.mregister.model.Agent;
import com.csquard.mregister.model.SalesArea;
import com.csquard.mregister.model.SalesRegion;
import com.csquard.mregister.model.Tdr;
import com.csquard.mregister.payload.AgentRequest;
import com.csquard.mregister.payload.UserSummary;
import com.csquard.mregister.repository.AgentRepository;
import com.csquard.mregister.repository.SalesAreaRepository;
import com.csquard.mregister.repository.SalesRegionRepository;
import com.csquard.mregister.repository.TdrRepository;
import com.csquard.mregister.security.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AgentController {

    @Autowired
    AgentRepository agentRepository;
    
    @Autowired
    SalesRegionRepository salesRegionRepository;
    
    @Autowired
    SalesAreaRepository salesAreaRepository;
    
    @Autowired
    TdrRepository tdrRepository;
    
//    @Autowired
//    private FileStorageService fileStorageService;

 // Get  Agents by TDR Id
    @GetMapping("/agents/tdr/{tdr_id}")
    @PreAuthorize("hasAnyRole('TDR','ASM','ADMIN','DISTRIBUTOR')")
    public List<Agent> getAllAgentsByTdr(@PathVariable(value = "tdr_id") long tdr_id) {
    	 if(!tdrRepository.existsById(tdr_id)) {
             throw new ResourceNotFoundException("Tdr", "id", tdr_id);
         }
        return agentRepository.findByTdrId(tdr_id);
        
    }
    
    // Get  Agents
    @GetMapping("/agents")
   @PreAuthorize("hasAnyRole('TDR','ASM','ADMIN','DISTRIBUTOR')")
   public List<Agent> getAllAgents() {
        return agentRepository.findAllAgents();
    }
    
 // Create a new Agent
    @PostMapping("/agent/create")
    @PreAuthorize("hasAnyRole('ADMIN','DISTRIBUTOR','TDR','ASM')")
    public Agent createAgent( @CurrentUser UserSummary currentUser, @Valid @RequestBody AgentRequest agentRequest) {
    	 //String fileName = fileStorageService.storeFile(file);

//         String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
//                 .path("/downloadFile/")
//                 .path(fileName)
//                 .toUriString();

        // Creating agent 
      salesRegionRepository.findById(agentRequest.getSales_region_id())		
                 .orElseThrow(() -> new ResourceNotFoundException("SalesRegion", "id", agentRequest.getSales_region_id()));
          salesAreaRepository.findById(agentRequest.getSales_area_id())        		 
                 .orElseThrow(() -> new ResourceNotFoundException("SalesArea", "id", agentRequest.getSales_area_id()));
         tdrRepository.findById(agentRequest.getTdr_id())        		 
                 .orElseThrow(() -> new ResourceNotFoundException("Tdr", "id", agentRequest.getTdr_id()));
         
      Agent agent =new Agent(agentRequest.getSales_area_id(), agentRequest.getSales_area_id(), agentRequest.getTdr_id(), agentRequest.getAgent_no(), agentRequest.getAddress(), agentRequest.getTown(), agentRequest.getSigned_name(), agentRequest.getSigned_contact(), agentRequest.getMobile(), agentRequest.getLocation(), agentRequest.getDevice_type(), agentRequest.getImei_no(), agentRequest.getId_no(), agentRequest.getId_attachment(), agentRequest.getSignature());
         
     Agent result = agentRepository.save(agent);
     return result;
//
//       return new AgentResponse(currentUser, agentRequest.getAgent_no(), agentRequest.getAddress(), agentRequest.getTown(), agentRequest.getSigned_name(), agentRequest.getSigned_contact(), agentRequest.getMobile(), agentRequest.getLocation(), agentRequest.getDevice_type(), agentRequest.getImei_no(), agentRequest.getId_no(), fileName, agentRequest.getSignature(), tdr, salesArea, salesRegion, fileDownloadUri, file.getContentType(), file.getSize());
 }

 // Get a Single Agent
    @GetMapping("/agents/{agent_no}")
    public Agent getAgentById(@PathVariable(value = "agent_no") Long agentNo) {
        return agentRepository.findById(agentNo)
                .orElseThrow(() -> new ResourceNotFoundException("Agent", "agent_no", agentNo));
    }

 // Update an Agent
    @PutMapping("/agents/{agent_no}")
    public Agent updateAgent(@PathVariable(value = "agent_no") Long agentNo,
                                            @Valid @RequestBody Agent agentDetails) {

        Agent agent = agentRepository.findById(agentNo)
                .orElseThrow(() -> new ResourceNotFoundException("Agent", "agent_no", agentNo));

        agent.setAddress(agentDetails.getAddress());
       agent.setTdrId(agentDetails.getTdrId());
        agent.setTown(agentDetails.getTown());
        agent.setSigned_contact(agentDetails.getSigned_contact());
        agent.setSignature(agentDetails.getSignature());
        agent.setMobile(agentDetails.getMobile());
        agent.setLocation(agentDetails.getLocation());
        agent.setSalesAreaId(agentDetails.getSalesAreaId());
        agent.setSalesRegionId(agentDetails.getSalesRegionId());
        agent.setDevice_type(agentDetails.getDevice_type());
        agent.setImei_no(agentDetails.getImei_no());
        agent.setId_no(agentDetails.getId_no());
        agent.setId_attachment(agentDetails.getId_attachment());
        agent.setSigned_name(agentDetails.getSigned_name());
        
        Agent updateAgent = agentRepository.save(agent);
        return updateAgent;
    }
    
    // Delete a Agent
    @DeleteMapping("/agents/{agent_no}")
    public ResponseEntity<?> deleteAgent(@PathVariable(value = "agent_no") Long agentNo) {
        Agent agent = agentRepository.findById(agentNo)
                .orElseThrow(() -> new ResourceNotFoundException("Agent", "agent_no", agentNo));

        agentRepository.delete(agent);

        return ResponseEntity.ok().build();
    }
}