package com.example.controller;


import com.example.model.UserPolicy;

import com.example.service.UserPolicyService;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins ="http://localhost:3003")
@RestController
@RequestMapping("/user-policies")
public class UserPolicyController {


  
    private final UserPolicyService userPolicyService;

    @Autowired
    public UserPolicyController( UserPolicyService userPolicyService) {
       
        this.userPolicyService = userPolicyService;
    }


    @PostMapping("/create")
    public Long createUserPolicy(@RequestBody UserPolicy userPolicy) {

UserPolicy createdUserPolicy = userPolicyService.createUserPolicy(userPolicy);
System.out.println("Created User Policy: " + createdUserPolicy);

// Return the ID of the created user policy
return createdUserPolicy.getUserPolicyId();
        
        
    }


    
   
    @GetMapping("/user/{userId}")
    public List<UserPolicy> getPoliciesById(@PathVariable Long userId) {
      
       
        return userPolicyService.getUserPoliciesByUserId(userId);
    }
    @RequestMapping(value="/readOne/{id}",method=RequestMethod.GET) //OR @GetMapping("/read")
    public UserPolicy readOne(@PathVariable Long id){
    	return userPolicyService.readOne(id);
    }
     
    @PutMapping("/renew/{id}")
    public UserPolicy renewUserPolicy(@PathVariable Long id) {
        return userPolicyService.renew(id);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<UserPolicy> updatePolicy(@PathVariable Long id, @RequestBody UserPolicy policyDetails) {
        Optional<UserPolicy> optionalPolicy = userPolicyService.getPolicyById(id);
        if (optionalPolicy.isPresent()) {
            UserPolicy policy = optionalPolicy.get();
            policy.setPremium(policyDetails.getPremium());
            policy.setPremiumCount(policyDetails.getPremiumCount());
            UserPolicy updatedPolicy = userPolicyService.createOrUpdatePolicy(policy);
            return ResponseEntity.ok(updatedPolicy);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserPolicy> getUserPolicyById(@PathVariable Long id) {
        Optional<UserPolicy> userPolicy = userPolicyService.getPolicyById(id);
        return userPolicy.map(ResponseEntity::ok)
                         .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PutMapping("/increment/{id}")
    public ResponseEntity<UserPolicy> incrementPremiumCount(@PathVariable Long id) {
        try {
            UserPolicy updatedUserPolicy = userPolicyService.incrementPremiumCount(id);
            return ResponseEntity.ok(updatedUserPolicy);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/get")
    public List<UserPolicy> getAllUserPolicies() {
        return userPolicyService.getAllUserPolicies();
    }

    @GetMapping("/userpolicy/{userId}")
    public List<UserPolicy> getUserPoliciesByUserId(@PathVariable Long userId) {
        return userPolicyService.getUserPoliciesByUserId(userId);
    }
}
