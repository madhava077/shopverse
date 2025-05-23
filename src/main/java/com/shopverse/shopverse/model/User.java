package com.shopverse.shopverse.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String username;

    @Column(unique = true)
	private String email;
	private String password;
	private String role;
	private String address;
	private String phoneNumber;
    
    public User() {
    }
    
 public User(Long id, String username, String email, String password, String role, String address,
            String phoneNumber) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
        this.address = address;
        this.phoneNumber = phoneNumber;
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

 public String getEmail() {
    return email;
 }

 public void setEmail(String email) {
    this.email = email;
 }

 public String getPassword() {
    return password;
 }

 public void setPassword(String password) {
    this.password = password;
 }

 public String getRole() {
    return role;
 }

 public void setRole(String role) {
    this.role = role;
 }

 public String getAddress() {
    return address;
 }

 public void setAddress(String address) {
    this.address = address;
 }

 public String getPhoneNumber() {
    return phoneNumber;
 }

 public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
 }
    
}
    
       
 

          
    
 

       


    