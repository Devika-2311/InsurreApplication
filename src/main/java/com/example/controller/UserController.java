package com.example.controller;

import com.example.model.EmailRequest;
import com.example.model.User;
import com.example.service.UserService;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3003")
@RestController
@RequestMapping("/users")
public class UserController {

	 private final UserService userService;
	 
		private final JavaMailSender javaMailSender;

	    @Autowired
	    public UserController(UserService userService) {
	        this.userService = userService;
			this.javaMailSender = null;
	    }
 

    @GetMapping("/get")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/getById/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Long userId) {
        Optional<User> user = userService.getUserById(userId);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

   

   

    @PutMapping("/update/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable Long userId, @RequestBody User user) {
        Optional<User> existingUser = userService.getUserById(userId);
        if (existingUser.isPresent()) {
            user.setUserId(userId); // Ensure the correct ID is set
            User updatedUser = userService.saveUser(user);
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

	
 
   
    
    
    @PostMapping("/send-email")
    public String sendEmail(@RequestBody EmailRequest emailRequest) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(emailRequest.getTo());
        message.setSubject(emailRequest.getSubject());
        message.setText(emailRequest.getBody());
        javaMailSender.send(message);
        return "Email sent successfully";
    }
    
    
 
    @PostMapping("/create")
    public User createUser(@RequestBody User user) {
    	user.setCreatedAt(LocalDate.now());
        return userService.createUser(user);
    }
 
    
    
    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestParam String emailId, @RequestParam String password, @RequestParam String role) {
        User loggedInUser = userService.login(emailId, password, role);
        if (loggedInUser != null) {
            return ResponseEntity.ok(loggedInUser);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email, password, or role");
        }
    }
 
 
    @PostMapping("/changepassword")
    public boolean changePassword(@RequestParam String emailId,
                                  @RequestParam String oldPassword,
                                  @RequestParam String newPassword) {
        return userService.changePassword(emailId, oldPassword, newPassword);
    }
    
    @PostMapping("/reset-password")
    public ResponseEntity<Object> resetPassword(@RequestParam String emailId, @RequestParam String newPassword) {
        
        boolean result = userService.resetPassword(emailId, newPassword);
        if (result) {
            return ResponseEntity.ok("Password reset successfully");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to reset password");
        }
    }
 
    
    @PutMapping("/editprofile")
    public User editProfile(@RequestParam String emailId,
                            @RequestParam(required = false) String address,
                            @RequestParam(required = false) String phoneNumber) {
        return userService.editProfile(emailId, address, phoneNumber);
    }
    
    
    
    
    
    private final Map<String, String> otpStorage = new HashMap<>();
    @PostMapping("/send-otp")
    public ResponseEntity<String> sendOTP(@RequestParam String email, @RequestParam String operation) {
        boolean existingUser = userService.checkUser(email);
 
        if (operation.equals("signup") && existingUser) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already exists in the database");
        } else if (operation.equals("forget_password") && !existingUser) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Email does not exist in the database");
        }
 
        try {
            // Generate a 6-digit OTP
            String otp = generateOTP();
 
            // Send OTP via email
            sendOTPEmail(email, otp);
 
            // Store OTP in memory (replace if exists)
            otpStorage.put(email, otp);
 
            return ResponseEntity.ok("OTP sent successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to send OTP");
        }
    }
 
 
 
    @PostMapping("/validate-otp")
    public ResponseEntity<Boolean> validateOTP(@RequestParam String email, @RequestParam String otp) {
    	
        try {
            // Retrieve OTP from memory
            String storedOTP = otpStorage.get(email);
 
            // Validate OTP
            boolean isValid = storedOTP != null && storedOTP.equals(otp);
 
            // Optionally, remove OTP from storage after validation
            if (isValid) {
                otpStorage.remove(email);
            }
 
            return ResponseEntity.ok(isValid);
        } catch (Exception e) {
            
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
        }
    }
 
   
 
    private String generateOTP() {
        SecureRandom secureRandom = new SecureRandom();
        int otp = 100000 + secureRandom.nextInt(900000); // Generate a 6-digit OTP
        return String.valueOf(otp);
    }
 
 
    void sendOTPEmail(String email, String otp) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("OTP for Password Reset");
        message.setText("Your OTP for password reset is: " + otp);
        javaMailSender.send(message);
    }
    
}
