package com.example.controller;
import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.model.HealthClaim;
import com.example.service.HealthClaimService;
 
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/health-claims")
public class HealthClaimController {
 
	private final HealthClaimService healthClaimService;
    public HealthClaimController(HealthClaimService healthClaimService) {
        this.healthClaimService = healthClaimService;
    }
	@PostMapping("/create")
	public HealthClaim createHealthClaim(@RequestBody HealthClaim healthClaim) {
		  return healthClaimService.createHealthClaim(healthClaim);
	}
	@GetMapping("/all")
	public List<HealthClaim> getAllHealthClaims() {
		return healthClaimService.getAllHealthClaims();
	}
	@GetMapping("/user-policies/{userPolicyId}")
    public List<HealthClaim> getHealthClaimsByUserPolicyId(@PathVariable Long userPolicyId) {
        return healthClaimService.findHealthClaimsByUserPolicyId(userPolicyId);
    }
	}