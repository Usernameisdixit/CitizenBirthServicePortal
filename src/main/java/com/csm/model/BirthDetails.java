package com.csm.model;



import java.sql.Date;
import java.time.LocalDateTime;


import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class BirthDetails {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
    private String childname;
    private String fathername;
    private String mothername;
    private String placeofbirth;
    
    @Column(name = "date_of_birth")
    @Temporal(TemporalType.DATE) // Stores only the date part, without time
    private Date dateOfBirth;
    
    private String sex;
    private String state;
    private String district;
    private String zipcode;
    private String image;
    private String applicationtype;
    @CreationTimestamp
    private LocalDateTime registrationTime;
    private String status;
    private String appliedby;


    
    public BirthDetails()
    {
    	
    }
    
  //getter and setter and other methods
    
        public BirthDetails(Long id, String childname, String fathername, String mothername, String placeofbirth,
		Date dateOfBirth, String sex, String state, String district, String zipcode, String image,
		String applicationtype, LocalDateTime registrationTime, String status, String appliedby) {
	super();
	this.id = id;
	this.childname = childname;
	this.fathername = fathername;
	this.mothername = mothername;
	this.placeofbirth = placeofbirth;
	this.dateOfBirth = dateOfBirth;
	this.sex = sex;
	this.state = state;
	this.district = district;
	this.zipcode = zipcode;
	this.image = image;
	this.applicationtype = applicationtype;
	this.registrationTime = registrationTime;
	this.status = status;
	this.appliedby = appliedby;
}
        
        public Long getId() {
    		return id;
    	}
    	

	public void setId(Long id) {
		this.id = id;
	}

	public String getChildname() {
		return childname;
	}

	public void setChildname(String childname) {
		this.childname = childname;
	}

	public String getFathername() {
		return fathername;
	}

	public void setFathername(String fathername) {
		this.fathername = fathername;
	}

	public String getMothername() {
		return mothername;
	}

	public void setMothername(String mothername) {
		this.mothername = mothername;
	}

	public String getPlaceofbirth() {
		return placeofbirth;
	}

	public void setPlaceofbirth(String placeofbirth) {
		this.placeofbirth = placeofbirth;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getApplicationtype() {
		return applicationtype;
	}

	public void setApplicationtype(String applicationtype) {
		this.applicationtype = applicationtype;
	}

	public LocalDateTime getRegistrationTime() {
		return registrationTime;
	}

	public void setRegistrationTime(LocalDateTime registrationTime) {
		this.registrationTime = registrationTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	

	public String getAppliedby() {
		return appliedby;
	}

	public void setAppliedby(String appliedby) {
		this.appliedby = appliedby;
	}

	@Override
	public String toString() {
		return "BirthDetails [id=" + id + ", childname=" + childname + ", fathername=" + fathername + ", mothername="
				+ mothername + ", placeofbirth=" + placeofbirth + ", dateOfBirth=" + dateOfBirth + ", sex=" + sex
				+ ", state=" + state + ", district=" + district + ", zipcode=" + zipcode + ", image=" + image
				+ ", applicationtype=" + applicationtype + ", registrationTime=" + registrationTime + ", status="
				+ status + ", appliedby=" + appliedby + "]";
	}

	
	
	
	
    
    
    
    

    



  


}
