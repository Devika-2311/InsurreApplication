package com.example.controller;

import com.example.model.PolicyDocument;
import com.example.service.PolicyDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/policy-documents")
public class PolicyDocumentController {

	 private final PolicyDocumentService policyDocumentService;

	    @Autowired
	    public PolicyDocumentController(PolicyDocumentService policyDocumentService) {
	        this.policyDocumentService = policyDocumentService;
	    }

    @PostMapping("/create")
    public ResponseEntity<PolicyDocument> createPolicyDocument( @RequestBody PolicyDocument policyDocument) {
        PolicyDocument createdPolicyDocument = policyDocumentService.createPolicyDocument(policyDocument);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPolicyDocument);
    }

    @GetMapping("/get")
    public List<PolicyDocument> getAllPolicyDocuments() {
        return policyDocumentService.getAllPolicyDocuments();
    }

    @GetMapping("getById/{policyDetailsId}")
    public ResponseEntity<PolicyDocument> getPolicyDocumentById(@PathVariable Long policyDetailsId) {
        Optional<PolicyDocument> policyDocument = policyDocumentService.getPolicyDocumentById(policyDetailsId);
        return policyDocument.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/term-policies")
    public ResponseEntity<List<PolicyDocument>> getTermPolicies() {
        List<PolicyDocument> termPolicies = policyDocumentService.getTermPolicies();
        return new ResponseEntity<>(termPolicies, HttpStatus.OK);
    }
    // Additional endpoints as per your business requirements
}
