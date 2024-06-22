package com.example.controller;

import com.example.model.PolicyDocument;
import com.example.model.PolicyType;
import com.example.model.User;
import com.example.model.UserPolicy;
import com.example.service.PolicyDocumentService;
import com.example.service.UserPolicyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = "http://localhost:3003")
@RestController
@RequestMapping("/policy-documents")
public class PolicyDocumentController {
	@Value("${react.public.folder}")
    private String reactPublicFolder;

	 private final PolicyDocumentService policyDocumentService;

	    @Autowired
	    public PolicyDocumentController(PolicyDocumentService policyDocumentService) {
	        this.policyDocumentService = policyDocumentService;
	    }

	    @PostMapping("/create/term")
	    public ResponseEntity<Object> createTermPolicyDocument(
	            @RequestParam("policyType") PolicyType policyType,
	            @RequestParam("annualIncome") Double annualIncome,
	            @RequestParam("anyDisease") Boolean anyDisease,
	            @RequestParam("nomineeName") String nomineeName,
	            @RequestParam("nomineeRelation") String nomineeRelation,
	            @RequestParam("nomineeEmail") String nomineeEmail,
	            @RequestParam("nomineeProof") MultipartFile nomineeProof,
	            @RequestParam("userPolicyId") Long userPolicyId) {
	    	  if (nomineeProof.isEmpty()) {
		            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		        }
	       

	            try {
	            	String fileName = nomineeProof.getOriginalFilename();
	            	 Path uploadPath = Paths.get(reactPublicFolder + "/assets/images");
	                // Ensure the directory exists
	                if (!Files.exists(uploadPath)) {
	                    Files.createDirectories(uploadPath);
	                }

	                // Save the file
	                
	                Path filePath = uploadPath.resolve(fileName);
	                Files.copy(nomineeProof.getInputStream(), filePath);

	                // Create and save the policy document
	                PolicyDocument policyDocument = new PolicyDocument();
	                policyDocument.setPolicyType(policyType);
	                policyDocument.setAnnualIncome(annualIncome);
	                policyDocument.setAnyDisease(anyDisease);
	                policyDocument.setNomineeName(nomineeName);
	                policyDocument.setNomineeRelation(nomineeRelation);
	                policyDocument.setNomineeEmail(nomineeEmail);
	                policyDocument.setNomineeProof("assets/images/" + fileName);
	                Optional<UserPolicy> userPolicyOptional = UserPolicyService.getUserPolicyByUserPolicyId(userPolicyId);
	                if (userPolicyOptional.isPresent()) {
	                    policyDocument.setUserPolicy(userPolicyOptional.get());
	                } else {
	                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid user policy ID");
	                }

	                PolicyDocument createdPolicyDocument = policyDocumentService.createPolicyDocument(policyDocument);
	                return ResponseEntity.status(HttpStatus.CREATED).body(createdPolicyDocument);

	            } catch (IOException e) {
	                e.printStackTrace();
	                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	            }
	    }
	    @PostMapping("/create/auto")
	    public ResponseEntity<Object> createAutoPolicyDocument(
	            @RequestParam("policyType") PolicyType policyType,
	            @RequestParam("vehicleModelNo") String vehicleModelNo,
	            @RequestParam("licensePlateNo") String licensePlateNo,
	            @RequestParam("vehicleValue") Double vehicleValue,
	            @RequestParam("primaryUse") String primaryUse,
	            @RequestParam("vehicleType") String vehicleType,
	            @RequestParam("driverAge") Integer driverAge,
	            @RequestParam("cheatSheet") MultipartFile cheatSheet,
	            @RequestParam("userPolicyId") Long userPolicyId) {
	    	  if (cheatSheet.isEmpty()) {
		            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		        }
	       

	            try {
	            	String fileName = cheatSheet.getOriginalFilename();
	            	 Path uploadPath = Paths.get(reactPublicFolder + "/assets/images");
	                // Ensure the directory exists
	                if (!Files.exists(uploadPath)) {
	                    Files.createDirectories(uploadPath);
	                }

	                // Save the file
	                
	                Path filePath = uploadPath.resolve(fileName);
	                Files.copy(cheatSheet.getInputStream(), filePath);

	                // Create and save the policy document
	                PolicyDocument policyDocument = new PolicyDocument();
	                policyDocument.setPolicyType(policyType);
	               policyDocument.setVehicleModelNo(vehicleModelNo);
	                policyDocument.setLicensePlateNo(licensePlateNo);
	                policyDocument.setVehicleValue(vehicleValue);
	                policyDocument.setPrimaryUse(primaryUse);
	                policyDocument.setVehicleType(vehicleType);
	                policyDocument.setDriverAge(driverAge);
	                policyDocument.setCheatSheet("assets/images/" + fileName);
	                Optional<UserPolicy> userPolicyOptional = UserPolicyService.getUserPolicyByUserPolicyId(userPolicyId);
	                if (userPolicyOptional.isPresent()) {
	                    policyDocument.setUserPolicy(userPolicyOptional.get());
	                } else {
	                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid user policy ID");
	                }

	                PolicyDocument createdPolicyDocument = policyDocumentService.createPolicyDocument(policyDocument);
	                return ResponseEntity.status(HttpStatus.CREATED).body(createdPolicyDocument);

	            } catch (IOException e) {
	                e.printStackTrace();
	                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	            }
	    }
	    @PostMapping("/create/health")
	    public ResponseEntity<Object> createHealthPolicyDocument(
	            @RequestParam("policyType") PolicyType policyType,
	            @RequestParam("age") Integer age,
	            @RequestParam("height") Double height,
	            @RequestParam("weight") Double weight,
	            @RequestParam("smoke") Boolean smoke,
	            @RequestParam("alcohol") Boolean alcohol,
	            @RequestParam("bp") Boolean bp,
	            @RequestParam("diabetics") Boolean diabetics,
	            @RequestParam("criticalDisease") String criticalDisease,
	            @RequestParam("healthReport") MultipartFile healthReport,
	            @RequestParam("userPolicyId") Long userPolicyId) {
	    	  if (healthReport.isEmpty()) {
		            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		        }
	       

	            try {
	            	String fileName = healthReport.getOriginalFilename();
	            	 Path uploadPath = Paths.get(reactPublicFolder + "/assets/images");
	                // Ensure the directory exists
	                if (!Files.exists(uploadPath)) {
	                    Files.createDirectories(uploadPath);
	                }

	                // Save the file
	                
	                Path filePath = uploadPath.resolve(fileName);
	                Files.copy(healthReport.getInputStream(), filePath);

	                // Create and save the policy document
	                PolicyDocument policyDocument = new PolicyDocument();
	                policyDocument.setPolicyType(policyType);
	               policyDocument.setAge(age);
	                policyDocument.setHeight(height);
	                policyDocument.setWeight(weight);
	                policyDocument.setSmoke(smoke);
	                policyDocument.setAlcohol(alcohol);
	                policyDocument.setBp(bp);
	                policyDocument.setDiabetics(diabetics);
	                policyDocument.setCriticalDisease(criticalDisease);
	                policyDocument.setHealthReport("assets/images/" + fileName);
	                Optional<UserPolicy> userPolicyOptional = UserPolicyService.getUserPolicyByUserPolicyId(userPolicyId);
	                if (userPolicyOptional.isPresent()) {
	                    policyDocument.setUserPolicy(userPolicyOptional.get());
	                } else {
	                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid user policy ID");
	                }

	                PolicyDocument createdPolicyDocument = policyDocumentService.createPolicyDocument(policyDocument);
	                return ResponseEntity.status(HttpStatus.CREATED).body(createdPolicyDocument);

	            } catch (IOException e) {
	                e.printStackTrace();
	                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	            }
	    }
    @GetMapping("/get")
    public List<PolicyDocument> getAllPolicyDocuments() {
        return policyDocumentService.getAllPolicyDocuments();
    }

    @GetMapping("getById/{policyDetailsId}")
    public ResponseEntity<PolicyDocument> getPolicyDocumentById(@PathVariable Long policyDetailsId) {
        Optional<PolicyDocument> policyDocument = policyDocumentService.getPolicyDocumentById(policyDetailsId);
        return policyDocument.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/term-policies")
    public ResponseEntity<List<PolicyDocument>> getTermPolicies() {
        List<PolicyDocument> termPolicies = policyDocumentService.getTermPolicies();
        return new ResponseEntity<>(termPolicies, HttpStatus.OK);
    }
    // Additional endpoints as per your business requirements
}
