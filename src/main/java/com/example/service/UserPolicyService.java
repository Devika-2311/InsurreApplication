package com.example.service;

import com.example.model.User;
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

	 private static UserPolicyRepo userPolicyRepository;
	 private static UserService us;
	 

	    @Autowired
	    public UserPolicyService(UserPolicyRepo userPolicyRepository, UserService us) {
	        this.userPolicyRepository = userPolicyRepository;
	        this.us=us;
	    }
    public UserPolicy createUserPolicy(UserPolicy userPolicy) {
    	User user = userPolicy.getUser();
        if (user.getUserId() == null) {
            us.saveUser(user);
        }
        return userPolicyRepository.save(userPolicy);
    }
    public Optional<UserPolicy> getUserPolicyById(Long id) {
        return userPolicyRepository.findById(id);
    }
    public Optional<UserPolicy> getPolicyById(Long id) {
        return userPolicyRepository.findById(id);
    }
    public UserPolicy incrementPremiumCount(Long id) {
        Optional<UserPolicy> optionalUserPolicy = getPolicyById(id);
        if (optionalUserPolicy.isPresent()) {
            UserPolicy userPolicy = optionalUserPolicy.get();
            userPolicy.setPremiumCount(userPolicy.getPremiumCount() + 1);
            return createOrUpdatePolicy(userPolicy);
        } else {
            throw new RuntimeException("UserPolicy not found with ID: " + id);
        }
    }
    public UserPolicy createOrUpdatePolicy(UserPolicy userPolicy) {
        return userPolicyRepository.save(userPolicy);
    }
    public List<UserPolicy> getAllUserPolicies() {
        return userPolicyRepository.findAll();
    }
       // Method to get user policy details by user ID
    public List<UserPolicy> getUserPoliciesByUserId(Long userId) {
        return userPolicyRepository.findAllByUser_UserId(userId);
    }
    public static Optional<UserPolicy> getUserPolicyByUserPolicyId(Long userPolicyId) {
        return userPolicyRepository.findById(userPolicyId);
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
 
        if (!currentDate.equals(endDate)) {
            throw new IllegalStateException("Renewal is only possible on Last Policy Serving Day. Come on "+c.getEndDate()+" to renew your policy");
        }
        
        c.setStartDate(c.getEndDate());
        c.setLeftcoverage(c.getCoverage());
        LocalDate newEndDate = c.getEndDate().plusYears(c.getTerm());
        c.setEndDate(newEndDate);
        c.setPremiumCount(0);
        userPolicyRepository.save(c);
 
        return c;
    }
 
    
    public List<UserPolicy> findPoliciesEndingTomorrow() {
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        return userPolicyRepository.findByEndDate(tomorrow);
    }
 
}
