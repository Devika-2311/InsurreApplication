package com.example.model;
 
import java.time.LocalDateTime;
 
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
@Entity
public class AutoClaim {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long autoClaimId;
 
    @Column(name = "vehicle_model_no")
    private String vehicleModelNo;
 
    @Column(name = "license_plate_no")
    private String licensePlateNo;
 
    @Column(name = "ex_showroom_price")
    private double exShowroomPrice;
 
    @Column(name = "vehicle_age")
    private int vehicleAge;
 
    @Column(name = "incident_time")
    private LocalDateTime incidentTime;
 
    @Column(name = "driver_age")
    private int driverAge;
 
    @Column(name = "damage_desc")
    private String damageDescription;
 
    @Column(name = "damage_cost")
    private double damageCost;
 
    @Column(name = "photo_of_damage")
    private String photoOfDamage;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userPolicyId")
    private UserPolicy userPolicy;
 
	public Long getAutoClaimId() {
		return autoClaimId;
	}
 
	public void setAutoClaimId(Long autoClaimId) {
		this.autoClaimId = autoClaimId;
	}
 
	public String getVehicleModelNo() {
		return vehicleModelNo;
	}
 
	public void setVehicleModelNo(String vehicleModelNo) {
		this.vehicleModelNo = vehicleModelNo;
	}
 
	public String getLicensePlateNo() {
		return licensePlateNo;
	}
 
	public void setLicensePlateNo(String licensePlateNo) {
		this.licensePlateNo = licensePlateNo;
	}
 
	public double getExShowroomPrice() {
		return exShowroomPrice;
	}
 
	public void setExShowroomPrice(double exShowroomPrice) {
		this.exShowroomPrice = exShowroomPrice;
	}
 
	public int getVehicleAge() {
		return vehicleAge;
	}
 
	public void setVehicleAge(int vehicleAge) {
		this.vehicleAge = vehicleAge;
	}
 
	public LocalDateTime getIncidentTime() {
		return incidentTime;
	}
 
	public void setIncidentTime(LocalDateTime incidentTime) {
		this.incidentTime = incidentTime;
	}
 
	public int getDriverAge() {
		return driverAge;
	}
 
	public void setDriverAge(int driverAge) {
		this.driverAge = driverAge;
	}
 
	public String getDamageDescription() {
		return damageDescription;
	}
 
	public void setDamageDescription(String damageDescription) {
		this.damageDescription = damageDescription;
	}
 
	public double getDamageCost() {
		return damageCost;
	}
 
	public void setDamageCost(double damageCost) {
		this.damageCost = damageCost;
	}
 
	public String getPhotoOfDamage() {
		return photoOfDamage;
	}
 
	public void setPhotoOfDamage(String photoOfDamage) {
		this.photoOfDamage = photoOfDamage;
	}
 
	public UserPolicy getUserPolicy() {
		return userPolicy;
	}
 
	public void setUserPolicy(UserPolicy userPolicy) {
		this.userPolicy = userPolicy;
	}
 
	@Override
	public String toString() {
		return "AutoClaim [autoClaimId=" + autoClaimId + ", vehicleModelNo=" + vehicleModelNo + ", licensePlateNo="
				+ licensePlateNo + ", exShowroomPrice=" + exShowroomPrice + ", vehicleAge=" + vehicleAge
				+ ", incidentTime=" + incidentTime + ", driverAge=" + driverAge + ", damageDescription="
				+ damageDescription + ", damageCost=" + damageCost + ", photoOfDamage=" + photoOfDamage
				+ ", userPolicy=" + userPolicy + "]";
	}
 
	public AutoClaim(Long autoClaimId, String vehicleModelNo, String licensePlateNo, double exShowroomPrice,
			int vehicleAge, LocalDateTime incidentTime, int driverAge, String damageDescription, double damageCost,
			String photoOfDamage, UserPolicy userPolicy) {
		super();
		this.autoClaimId = autoClaimId;
		this.vehicleModelNo = vehicleModelNo;
		this.licensePlateNo = licensePlateNo;
		this.exShowroomPrice = exShowroomPrice;
		this.vehicleAge = vehicleAge;
		this.incidentTime = incidentTime;
		this.driverAge = driverAge;
		this.damageDescription = damageDescription;
		this.damageCost = damageCost;
		this.photoOfDamage = photoOfDamage;
		this.userPolicy = userPolicy;
	}
 
	public AutoClaim() {
		super();
	}
 
}