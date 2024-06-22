package com.example.model;
 
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
 
@Entity
public class Tickets {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 
    private String title;
    private String description;
    private String responseTxt;
    private LocalDateTime responseAt;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;
 
    // Getters and setters
 
    public Long getId() {
        return id;
    }
 
    public void setId(Long id) {
        this.id = id;
    }
 
    public String getTitle() {
        return title;
    }
 
    public void setTitle(String title) {
        this.title = title;
    }
 
    public String getDescription() {
        return description;
    }
 
    public void setDescription(String description) {
        this.description = description;
    }
 
    public String getResponseTxt() {
        return responseTxt;
    }
 
    public void setResponseTxt(String responseTxt) {
        this.responseTxt = responseTxt;
    }
 
    public LocalDateTime getResponseAt() {
        return responseAt;
    }
 
    public void setResponseAt(LocalDateTime responseAt) {
        this.responseAt = responseAt;
    }
 
    public User getUser() {
        return user;
    }
 
    public void setUser(User user) {
        this.user = user;
    }
    
}