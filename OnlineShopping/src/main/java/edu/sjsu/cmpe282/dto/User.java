package edu.sjsu.cmpe282.dto;

import java.util.Date;

public class User {
	
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private short isAdmin;
	private Date lastLoginTime;
		
	public User() {
		}
	
	public User(String firstName, String lastName, String email, String passwd, short isAdmin, Date lastLoginTime) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = passwd;
		this.isAdmin = isAdmin;
		this.lastLoginTime = lastLoginTime;
	}

	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
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
	public void setPassword(String passwd) {
		this.password = passwd;
	}
	public short getIsAdmin() {
		return isAdmin;
	}
	public void setIsAdmin(short isAdmin) {
		this.isAdmin = isAdmin;
	}
	public Date getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	
}
