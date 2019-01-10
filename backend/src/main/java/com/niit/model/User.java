package com.niit.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="users_details")
public class User {
	@Id
	private String email;
		@Column(nullable=false)
	private String password;
		@Column(nullable=false)
	private String firstname;
	private String lastname;
	private String phonenumber;
	@Column(nullable=false)
	private String role;
	@Column(name="onlinestatus")
	private boolean online;//Map this property with column named onlinestatus
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
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public boolean isOnline() {
		return online;
	}
	public void setOnline(boolean online) {
		this.online = online;
	}
	@Override
	public String toString() {
		return this.email;
	}
	@Override
	public boolean equals(Object obj) {
		User user=(User)obj;
		return this.email.equals(user.getEmail());
				
	}
	@Override
	public int hashCode() {
		return this.email.hashCode();
	}

}
