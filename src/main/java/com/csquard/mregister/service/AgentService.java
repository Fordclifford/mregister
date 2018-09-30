//package com.csquard.mregister.service;
//
//import com.csquard.mregister.exception.BadRequestException;
//import com.csquard.mregister.exception.ResourceNotFoundException;
//import com.csquard.mregister.model.*;
//import com.csquard.mregister.payload.PagedResponse;
//import com.csquard.mregister.payload.AgentRequest;
//import com.csquard.mregister.payload.AgentResponse;
//import com.csquard.mregister.repository.AgentRepository;
//import com.csquard.mregister.repository.SalesAreaRepository;
//import com.csquard.mregister.repository.SalesRegionRepository;
//import com.csquard.mregister.repository.UserRepository;
//import com.csquard.mregister.security.UserPrincipal;
//import com.csquard.mregister.util.AppConstants;
//import com.csquard.mregister.util.ModelMapper;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//import org.springframework.stereotype.Service;
//import java.util.Collections;
//import java.util.List;
//import java.util.Map;
//import java.util.function.Function;
//import java.util.stream.Collectors;
//
//@Service
//public class AgentService {
//
//    @Autowired
//    private AgentRepository agentRepository;
//
//    @Autowired
//    private UserRepository userRepository;
//    
//    @Autowired
//    private SalesAreaRepository salesAreaRepository;
//    
//    @Autowired
//    private SalesRegionRepository salesRegionRepository;
//    
//
//   
//
//    public PagedResponse<AgentResponse> getAllAgents(UserPrincipal currentUser, int page, int size) {
//        validatePageNumberAndSize(page, size);
//
//        // Retrieve Agents
//        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "createdAt");
//        Page<Agent> agents = agentRepository.findAll(pageable);
//
//        if(agents.getNumberOfElements() == 0) {
//            return new PagedResponse<>(Collections.emptyList(), agents.getNumber(),
//                    agents.getSize(), agents.getTotalElements(), agents.getTotalPages(), agents.isLast());
//        }
//
//        // Map Agents to AgentResponses to agent creator details
//         Map<Long, User> creatorMap = getAgentCreatorMap(agents.getContent());
//
//        List<AgentResponse> agentResponses = agents.map(agent -> {
//            return ModelMapper.mapAgentToResponse(agent, creatorMap.get(agent.getCreatedBy()));
//        }).getContent();
//
//        return new PagedResponse<>(agentResponses, agents.getNumber(),
//        		agents.getSize(), agents.getTotalElements(), agents.getTotalPages(), agents.isLast());
//    }
//
//    private Map<Long, User> getAgentCreatorMap(List<Agent> agent) {
//    	 List<Long> creatorIds = agent.stream()
//                 .map(Agent::getCreatedBy)
//                 .distinct()
//                 .collect(Collectors.toList());
//
//         List<User> creators = userRepository.findByIdIn(creatorIds);
//         Map<Long, User> creatorMap = creators.stream()
//                 .collect(Collectors.toMap(User::getId, Function.identity()));
//
//         return creatorMap;
//	}
//    
//
//	public PagedResponse<AgentResponse> getAgentsCreatedBy(String username, UserPrincipal currentUser, int page, int size) {
//        validatePageNumberAndSize(page, size);
//
//        User user = userRepository.findByUsername(username)
//                .orElseThrow(() -> new ResourceNotFoundException("Users", "username", username));
//
//        // Retrieve all agents created by the given username
//        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "createdAt");
//        Page<Agent> agents = agentRepository.findByCreatedBy(user.getId(), pageable);
//
//        if (agents.getNumberOfElements() == 0) {
//            return new PagedResponse<>(Collections.emptyList(), agents.getNumber(),
//            		agents.getSize(), agents.getTotalElements(), agents.getTotalPages(), agents.isLast());
//        }
//
//        // Map agents to AgentResponses containing agent creator details
//
//        List<AgentResponse> agentReponse = agents.map(agent -> {
//            return ModelMapper.mapAgentToResponse(agent, user);
//        }).getContent();
//
//        return new PagedResponse<>(agentReponse, agents.getNumber(),
//        		agents.getSize(), agents.getTotalElements(), agents.getTotalPages(), agents.isLast());
//    }
//	
//
//	public PagedResponse<AgentResponse> getAgentsBySalesAreaId(Long id, UserPrincipal currentUser, int page, int size) {
//        validatePageNumberAndSize(page, size);
//
//        SalesArea salesArea = salesAreaRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("SalesArea", "id", id));
//
//        // Retrieve all agents in a SalesArea
//        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "createdAt");
//        Page<Agent> agents = agentRepository.findBySalesAreaId(salesArea.getId(), pageable);
//
//        if (agents.getNumberOfElements() == 0) {
//            return new PagedResponse<>(Collections.emptyList(), agents.getNumber(),
//            		agents.getSize(), agents.getTotalElements(), agents.getTotalPages(), agents.isLast());
//        }
//        
//        Map<Long, User> creatorMap = getAgentCreatorMap(agents.getContent());
//
//        List<AgentResponse> agentResponses = agents.map(agent -> {
//            return ModelMapper.mapAgentToResponse(agent, creatorMap.get(agent.getCreatedBy()));
//        }).getContent();
//        
//        return new PagedResponse<AgentResponse>(agentResponses, agents.getNumber(),
//        		agents.getSize(), agents.getTotalElements(), agents.getTotalPages(), agents.isLast());
//    }
//
//
//    private void validatePageNumberAndSize(int page, int size) {
//        if(page < 0) {
//            throw new BadRequestException("Page number cannot be less than zero.");
//        }
//
//        if(size > AppConstants.MAX_PAGE_SIZE) {
//            throw new BadRequestException("Page size must not be greater than " + AppConstants.MAX_PAGE_SIZE);
//        }
//    }
//    public AgentResponse getAgentByAgentNo(Long agent_no, UserPrincipal currentUser) {
//        Agent agents= agentRepository.findById(agent_no)
//        		.orElseThrow(() -> new ResourceNotFoundException("Agents", "agent_no", agent_no));
//
//       
//        // Retrieve Agent creator details
//        User creator = userRepository.findById(agents.getCreatedBy())
//                .orElseThrow(() -> new ResourceNotFoundException("Users", "id", agents.getCreatedBy()));
//
//        return ModelMapper.mapAgentToResponse(agents, creator);
//    }
//    
//    public PagedResponse<AgentResponse> getAgentsBySalesRegionId(Long id, UserPrincipal currentUser, int page, int size) {
//        validatePageNumberAndSize(page, size);
//
//        SalesRegion salesRegion = salesRegionRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("SalesRegions", "id", id));
//
//        // Retrieve all agents in a SalesRegion
//        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "createdAt");
//        Page<Agent> agents = agentRepository.findBySalesRegionId(salesRegion.getId(), pageable);
//
//        if (agents.getNumberOfElements() == 0) {
//            return new PagedResponse<>(Collections.emptyList(), agents.getNumber(),
//            		agents.getSize(), agents.getTotalElements(), agents.getTotalPages(), agents.isLast());
//        }
//        
//        Map<Long, User> creatorMap = getAgentCreatorMap(agents.getContent());
//
//        List<AgentResponse> agentResponses = agents.map(agent -> {
//            return ModelMapper.mapAgentToResponse(agent, creatorMap.get(agent.getCreatedBy()));
//        }).getContent();
//        
//        return new PagedResponse<AgentResponse>(agentResponses, agents.getNumber(),
//        		agents.getSize(), agents.getTotalElements(), agents.getTotalPages(), agents.isLast());
//    }
//    
//    
//
//}