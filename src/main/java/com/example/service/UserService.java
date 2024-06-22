package com.example.service;



import com.example.model.User;
import com.example.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService {

	private final UserRepo userRepository;

    @Autowired
    public UserService(UserRepo userRepo) {
        this.userRepository = userRepo;
    }



    public Optional<User> getUserById(Long userId) {
        return userRepository.findById(userId);
    }

    public  User saveUser(User user) {
        // Additional business logic if needed before saving
        return userRepository.save(user);
    }
    public User login(String emailId, String password) {
        return  userRepository.findByEmailIdAndPassword(emailId, password);
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
  

   public User createUser(User user) {
   	BCryptPasswordEncoder bpe=new BCryptPasswordEncoder();
   	String newps=bpe.encode(user.getPassword());
   	user.setPassword(newps);
       return userRepository.save(user);
   }

   public List<User> getAllUsers() {
       return userRepository.findAll();
   }
   public User login(String emailId, String password, String role) {
       BCryptPasswordEncoder bpe = new BCryptPasswordEncoder();
       List<User> users = userRepository.findByEmailIdAndRole(emailId, role);

       for (User user : users) {
           String hashedPassword = user.getPassword(); // Ensure password retrieval is correct
           if (bpe.matches(password, hashedPassword)) {
               return user; // Return the user whose password and role match
           }
       }

       // No matching user found or password doesn't match
       return null;
   }
   public boolean checkUser(String emailId) {
		
   	List<User> users = userRepository.findByEmailId(emailId);
   	return !users.isEmpty();
		
	}

   
   public boolean changePassword(String emailId, String oldPassword, String newPassword) {
       BCryptPasswordEncoder bpe = new BCryptPasswordEncoder();
       List<User> users = userRepository.findByEmailId(emailId);
       for (User user : users) {
           if (bpe.matches(oldPassword, user.getPassword())) {
               String encodedNewPassword = bpe.encode(newPassword);
               user.setPassword(encodedNewPassword);
               userRepository.save(user); // Update user with new password
               return true; // Password changed successfully
           }
       }
       return false; // No matching user found or old password incorrect
   }

   
   
   public boolean resetPassword(String emailId, String newPassword) {
       BCryptPasswordEncoder bpe = new BCryptPasswordEncoder();
       List<User> users = userRepository.findByEmailId(emailId);
       boolean passwordChanged = false; // Flag to track if password change was successful
       
       for (User user : users) {
           String encodedNewPassword = bpe.encode(newPassword);
           user.setPassword(encodedNewPassword);
           userRepository.save(user); // Update user with new password
           passwordChanged = true; // Set flag indicating password change success
       }
       
       return passwordChanged; // Return true if at least one user's password was changed
   }
   
   
   
   
   
   public User editProfile(String emailId, String address, String phoneNumber) {
       List<User> users = userRepository.findByEmailId(emailId);
       if (!users.isEmpty()) {
           User user = users.get(0);
           if (address != null) {
               user.setAddress(address);
           }
           if (phoneNumber != null) {
               user.setPhoneNo(phoneNumber);
           }
           userRepository.save(user); // Update user with new address and phone number if provided
           return user;
       }
       return null; // or throw an exception if user not found
   }
   
   
 
   private final Map<String, String> otpStorage = new HashMap<>();

   public void saveOTP(String email, String otp) {
       otpStorage.put(email, otp);
   }

   public String getSavedOTP(String email) {
       return otpStorage.get(email);
   }

    // Additional methods as per your business requirements
}
