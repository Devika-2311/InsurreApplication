package com.example.repo;
 
import org.springframework.data.jpa.repository.JpaRepository;
 
import com.example.model.TermClaim;
 
public interface TermClaimRepo extends JpaRepository<TermClaim, Long> {
 
}