package com.example.controller;


import com.example.model.UserPolicy;

import com.example.service.UserPolicyService;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/user-policies")
public class UserPolicyController {


  
    private final UserPolicyService userPolicyService;

    @Autowired
    public UserPolicyController( UserPolicyService userPolicyService) {
       
        this.userPolicyService = userPolicyService;
    }


    @PostMapping("/create")
    public UserPolicy createUserPolicy(@RequestBody UserPolicy userPolicy) {

        return userPolicyService.createUserPolicy(userPolicy);
        
    }

    
    @GetMapping("/{id}")
    public ResponseEntity<UserPolicy> getUserPolicyById(@PathVariable("id") Long id) {
        Optional<UserPolicy> userPolicy = userPolicyService.getUserPolicyById(id);
        return userPolicy.map(policy -> new ResponseEntity<>(policy, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
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

    @GetMapping("/get")
    public List<UserPolicy> getAllUserPolicies() {
        return userPolicyService.getAllUserPolicies();
    }

    @GetMapping("/userpolicy/{userId}")
    public List<UserPolicy> getUserPoliciesByUserId(@PathVariable Long userId) {
        return userPolicyService.getUserPoliciesByUserId(userId);
    }
}
