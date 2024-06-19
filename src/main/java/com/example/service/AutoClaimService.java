package com.example.service;
import java.util.List;
import org.springframework.stereotype.Service;
 
import com.example.model.AutoClaim;
import com.example.repo.AutoClaimRepo;
 
@Service
public class AutoClaimService {
	  private final AutoClaimRepo autoClaimRepository;
 
	    public AutoClaimService(AutoClaimRepo autoClaimRepository) {
	        this.autoClaimRepository = autoClaimRepository;
	    }
 
	    public AutoClaim createAutoClaim(AutoClaim autoClaim) {
	        return autoClaimRepository.save(autoClaim);
	    }
	    public List<AutoClaim> getAllAutoClaims() {
	        return autoClaimRepository.findAll();
	    }
	    public List<AutoClaim> findAutoClaimsByUserPolicyId(Long userPolicyId) {
	        return autoClaimRepository.findAutoClaimsByUserPolicyId(userPolicyId);
	    }
 
}
 