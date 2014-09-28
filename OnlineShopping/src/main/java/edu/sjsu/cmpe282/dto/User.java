package edu.sjsu.cmpe282.dto;

public class User {
	
	private String firstName;
	private String lastName;
	private String email;
	private String password;
		
	public User() {
		}
	
	public User(String firstName, String lastName, String email, String passwd) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = passwd;
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

}
