package com.example.service;
import java.util.List;
import org.springframework.stereotype.Service;
import com.example.model.HealthClaim;
import com.example.model.UserPolicy;
import com.example.repo.HealthClaimRepo;
 
@Service
public class HealthClaimService {
    private final HealthClaimRepo healthClaimRepository;
 
    public HealthClaimService(HealthClaimRepo healthClaimRepository) {
        this.healthClaimRepository = healthClaimRepository;
    }
	  public HealthClaim createHealthClaim(HealthClaim healthClaim) {
	        UserPolicy userPolicy = healthClaim.getUserPolicy();
 
//	        if (healthClaim.getDateofservice().isAfter(userPolicy.getEndDate())) {
//	            throw new IllegalArgumentException("Health claim date must be on or before the end date of the user policy.");
//	        }
//	        if (healthClaim.getClaimamt() > userPolicy.getLeftcoverage()) {
//	            throw new IllegalArgumentException("Claim amount exceeds the left coverage of the user policy.");
//	        }
	        return healthClaimRepository.save(healthClaim);
	    }
	    public List<HealthClaim> getAllHealthClaims() {
	        return healthClaimRepository.findAll();
	    }
	    public List<HealthClaim> findHealthClaimsByUserPolicyId(Long userPolicyId) {
	        return healthClaimRepository.findHealthClaimsByUserPolicyId(userPolicyId);
	    }
 
}
 