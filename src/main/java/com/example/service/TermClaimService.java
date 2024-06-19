package com.example.service;
import java.util.List;
import org.springframework.stereotype.Service;
import com.example.model.TermClaim;
import com.example.repo.TermClaimRepo;
@Service
public class TermClaimService {
    private final TermClaimRepo termClaimRepository;
 
    public TermClaimService(TermClaimRepo termClaimRepository) {
        this.termClaimRepository = termClaimRepository;
    }
 
	    public TermClaim createTermClaim(TermClaim termClaim) {
	        return termClaimRepository.save(termClaim);
	    }
	    public List<TermClaim> getAllTermClaims() {
	        return termClaimRepository.findAll();
	    }
}
 