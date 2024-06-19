package com.example.service;

import com.example.model.Policy;
import com.example.repo.PolicyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PolicyService {

	 private final PolicyRepo policyRepository;

	    @Autowired
	    public PolicyService(PolicyRepo policyRepository, PolicyDocumentService policyDocumentService) {
	        this.policyRepository = policyRepository;
	    }

    public List<Policy> getAllPolicies() {
        return policyRepository.findAll();
    }

    public Optional<Policy> getPolicyById(Long policyId) {
        return policyRepository.findById(policyId);
    }
}

