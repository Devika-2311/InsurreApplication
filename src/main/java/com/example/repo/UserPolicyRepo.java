package com.example.repo;
import java.time.LocalDateTime;
import java.util.List;
 
import org.springframework.data.jpa.repository.JpaRepository;
 
import com.example.model.UserPolicy;
public interface UserPolicyRepo extends JpaRepository<UserPolicy,Long> {
	 List<UserPolicy> findByEndDate(LocalDateTime endDate);
List<UserPolicy> findAllByUser_UserId(Long userId);

}