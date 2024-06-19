package com.example.service;

import com.example.model.PolicyDocument;
import com.example.repo.PolicyDocumentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PolicyDocumentService {

	   private final PolicyDocumentRepo policyDocumentRepository;

	    @Autowired
	    public PolicyDocumentService(PolicyDocumentRepo policyDocumentRepo) {
	        this.policyDocumentRepository = policyDocumentRepo;
	    }


    public PolicyDocument createPolicyDocument(PolicyDocument policyDocument) {
        return policyDocumentRepository.save(policyDocument);
    }
    public List<PolicyDocument> getTermPolicies() {
        return policyDocumentRepository.findTermPolicies();
    }

    public List<PolicyDocument> getAllPolicyDocuments() {
        return policyDocumentRepository.findAll();
    }

    public Optional<PolicyDocument> getPolicyDocumentById(Long policyDetailsId) {
        return policyDocumentRepository.findById(policyDetailsId);
    }

    // Additional methods as per your business requirements
}
