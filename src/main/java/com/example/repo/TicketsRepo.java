package com.example.repo;
 
import java.util.List;
 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
 
import com.example.model.Tickets;
 
public interface TicketsRepo extends JpaRepository<Tickets,Long>{
	
	@Query("Select u FROM Tickets u where u.responseTxt IS NULL")
    List<Tickets> findByResponseTxtIsNull(); // Pending queries
    
	@Query("Select u FROM Tickets u where u.responseTxt IS NOT NULL")
    List<Tickets> findByResponseTxtIsNotNull(); // Resolved queries
    
    @Query("SELECT t.user.userId, t.id as ticketId, t.title FROM Tickets t")
    List<Object[]> findUserIdTicketIdAndTitle();
    @Query("select t from Tickets t where t.user.userId=:userId")
    List<Tickets> findByUser_Id(Long userId);
    
 
 
}