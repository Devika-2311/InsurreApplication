package com.example.controller;

import com.example.model.Policy;
import com.example.service.PolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/policies")
public class PolicyController {

	private final PolicyService policyService;

    @Autowired
    public PolicyController(PolicyService policyService) {
        this.policyService = policyService;
    }
    @GetMapping("/all")
    public List<Policy> getAllPolicies() {
        return policyService.getAllPolicies();
    }
    @GetMapping("/{policyId}")
    public ResponseEntity<Policy> getPolicyById(@PathVariable Long policyId) {
        return policyService.getPolicyById(policyId)
                .map(policy -> new ResponseEntity<>(policy, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
