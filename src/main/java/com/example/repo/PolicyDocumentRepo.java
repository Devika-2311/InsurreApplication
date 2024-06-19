package com.example.repo;

import com.example.model.PolicyDocument;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PolicyDocumentRepo extends JpaRepository<PolicyDocument, Long> {
	@Query("SELECT p FROM PolicyDocument p WHERE p.policyType = 'TERM'")
    List<PolicyDocument> findTermPolicies();
    // Define custom queries if needed
}
