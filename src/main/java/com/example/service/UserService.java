package com.example.service;



import com.example.model.User;
import com.example.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

	private final UserRepo userRepository;

    @Autowired
    public UserService(UserRepo userRepo) {
        this.userRepository = userRepo;
    }


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long userId) {
        return userRepository.findById(userId);
    }

    public User saveUser(User user) {
        // Additional business logic if needed before saving
        return userRepository.save(user);
    }
    public User login(String emailId, String password) {
        return  userRepository.findByEmailIdAndPassword(emailId, password);
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    // Additional methods as per your business requirements
}
