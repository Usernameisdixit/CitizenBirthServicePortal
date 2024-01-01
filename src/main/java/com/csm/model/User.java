package com.csm.model;

import java.sql.Date;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class User {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @Column(name = "date_of_birth")
    @Temporal(TemporalType.DATE) // Stores only the date part, without time
    private Date dateOfBirth;

    @Column(name = "sex")
    private String sex;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "address")
    private String address;
    
    @Column(name = "phonenumber",unique = true)
    private String phonenumber;

    // Getters, Setters, Constructors, and other methods
    

    public User() {}

	

	public User(Long id, String username, String password, Role role, Date dateOfBirth, String sex, String email,
			String address, String phonenumber) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.role = role;
		this.dateOfBirth = dateOfBirth;
		this.sex = sex;
		this.email = email;
		this.address = address;
		this.phonenumber = phonenumber;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}



	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", role=" + role
				+ ", dateOfBirth=" + dateOfBirth + ", sex=" + sex + ", email=" + email + ", address=" + address
				+ ", phonenumber=" + phonenumber + "]";
	}
	


    

}
