package com.example.service;

import com.example.model.UserPolicy;
import com.example.repo.UserPolicyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserPolicyService {

	 private final UserPolicyRepo userPolicyRepository;

	    @Autowired
	    public UserPolicyService(UserPolicyRepo userPolicyRepository) {
	        this.userPolicyRepository = userPolicyRepository;
	    }
    public UserPolicy createUserPolicy(UserPolicy userPolicy) {
        return userPolicyRepository.save(userPolicy);
    }

    public List<UserPolicy> getAllUserPolicies() {
        return userPolicyRepository.findAll();
    }
    public Optional<UserPolicy> getUserPolicyById(Long id) {
        return userPolicyRepository.findById(id);
    }
    // Method to get user policy details by user ID
    public List<UserPolicy> getUserPoliciesByUserId(Long userId) {
        return userPolicyRepository.findAllByUser_UserId(userId);
    }
    public UserPolicy readOne(Long id) {
    	UserPolicy c= userPolicyRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Invalid account ID"));
		return c;
	}
    public UserPolicy renew(Long id) {
        UserPolicy c = userPolicyRepository.findById(id)
                           .orElseThrow(() -> new IllegalArgumentException("Invalid account ID"));
        
        if (LocalDate.now().isAfter(c.getEndDate())) {
            throw new IllegalStateException("Renewal is not possible after the policy end date");
        }
        LocalDate currentDate = LocalDate.now();
        LocalDate endDate =c.getEndDate();
 
        if (!currentDate.equals(endDate.minusDays(1))) {
            throw new IllegalStateException("Renewal is only possible on Last Policy Serving Day. Come on "+c.getEndDate()+" to renew your policy");
        }
        
        c.setStartDate(c.getEndDate());
        c.setLeftcoverage(c.getCoverage());
        LocalDate newEndDate = c.getEndDate().plusYears(c.getTerm());
        c.setEndDate(newEndDate);
        userPolicyRepository.save(c);
 
        return c;
    }
 
    
    public List<UserPolicy> findPoliciesEndingTomorrow() {
        LocalDateTime tomorrow = LocalDateTime.now().plusDays(1);
        return userPolicyRepository.findByEndDate(tomorrow);
    }
 
}
