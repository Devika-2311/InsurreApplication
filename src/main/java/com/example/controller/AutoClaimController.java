package com.example.controller;
 
import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.model.AutoClaim;
import com.example.service.AutoClaimService;
 
 
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/auto-claims")
public class AutoClaimController {
	  private final AutoClaimService autoClaimService;
 
	    public AutoClaimController(AutoClaimService autoClaimService) {
	        this.autoClaimService = autoClaimService;
	    }
	@PostMapping("/create")
	public AutoClaim createAutoClaim(@RequestBody AutoClaim autoClaim) {
		  return autoClaimService.createAutoClaim(autoClaim);
	}
	@GetMapping("/all")
	public List<AutoClaim> getAllAutoClaims() {
		return autoClaimService.getAllAutoClaims();
	}
	@GetMapping("/user-policies/{userPolicyId}")
	public List<AutoClaim> getAutoClaimsByUserPolicyId(@PathVariable Long userPolicyId) {
	    return autoClaimService.findAutoClaimsByUserPolicyId(userPolicyId);
	}
}