package com.example.controller;
import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.model.TermClaim;
import com.example.service.TermClaimService;
 
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/term-claims")
public class TermClaimController {
    private final TermClaimService termClaimService;
 
    public TermClaimController(TermClaimService termClaimService) {
        this.termClaimService = termClaimService;
    }
	@PostMapping("/create")
	public TermClaim createTermClaim(@RequestBody TermClaim termClaim) {
		  return termClaimService.createTermClaim(termClaim);
	}
	@GetMapping("/all")
	public List<TermClaim> getAllTermClaims() {
		return termClaimService.getAllTermClaims();
	}
}
 