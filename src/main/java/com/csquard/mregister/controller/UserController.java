package com.csquard.mregister.controller;

import com.csquard.mregister.exception.ResourceNotFoundException;
import com.csquard.mregister.model.User;
import com.csquard.mregister.payload.*;
import com.csquard.mregister.repository.AgentRepository;
import com.csquard.mregister.repository.UserRepository;

import com.csquard.mregister.security.UserPrincipal;
import com.csquard.mregister.service.AgentService;
import com.csquard.mregister.security.CurrentUser;
import com.csquard.mregister.util.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AgentRepository agentRepository;

    @Autowired
    private AgentService agentService;

    @GetMapping("/user/me")
   // @PreAuthorize("hasRole('DDR')")
    public UserSummary getCurrentUser(@CurrentUser UserPrincipal currentUser) {
        UserSummary userSummary = new UserSummary(currentUser.getId(), currentUser.getUsername(), currentUser.getName());
        return userSummary;
    }

    @GetMapping("/user/checkUsernameAvailability")
    public UserIdentityAvailability checkUsernameAvailability(@RequestParam(value = "username") String username) {
        Boolean isAvailable = !userRepository.existsByUsername(username);
        return new UserIdentityAvailability(isAvailable);
    }

    @GetMapping("/user/checkEmailAvailability")
    public UserIdentityAvailability checkEmailAvailability(@RequestParam(value = "email") String email) {
        Boolean isAvailable = !userRepository.existsByEmail(email);
        return new UserIdentityAvailability(isAvailable);
    }

    @GetMapping("/users/{username}")
    public UserProfile getUserProfile(@PathVariable(value = "username") String username) {
        User user = userRepository.findByUsername(username)
         .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));
        
        

        long agentCount = agentRepository.countByCreatedBy(user.getId());
//        long salesAreaCount = agentRepository.countBySalesAreaId(user.getId());
//        long SalesRegionCount = agentRepository.countBySalesRegionId(user.)
    UserProfile userProfile = new UserProfile(user.getId(), user.getUsername(), user.getName(), user.getCreatedAt(), agentCount);

        return userProfile;
    }

    @GetMapping("/users/{username}/agents")
    public PagedResponse<AgentResponse> getAgentsCreatedBy(@PathVariable(value = "username") String username,
                                                         @CurrentUser UserPrincipal currentUser,
                                                         @RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
                                                         @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) {
        return agentService.getAgentsCreatedBy(username, currentUser, page, size);
    }


   
}