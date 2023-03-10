package com.Aidietician.Entities;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;



@Entity
@Table(name = "USER")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@NotBlank(message = "Name field is required !!!")
	@Size(min = 3, max = 20, message = "Name must be between 3 to 20 characters !!!")
	private String name;
	
	@Column(unique = true)
	@Email(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$", message = "Invalid Email !!!")
	private String email;
	
	@NotBlank(message = "Password field is required !!!")
	@Size(min = 3, message = "Password must be greater than 3 characters !!!")
	private String password;
	
	private String role;
	private boolean enabled;

	
	
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "user")
	private List<userRequest> userdetailsList = new ArrayList<>();
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(int id, String name, String email, String password, String role, boolean enabled) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.role = role;
		this.enabled = enabled;
	
	}

	

	public List<userRequest> getUserdetailsList() {
		return userdetailsList;
	}public void setUserdetailsList(List<userRequest> userdetailsList) {
		this.userdetailsList = userdetailsList;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

}

