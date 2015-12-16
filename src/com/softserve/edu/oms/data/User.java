package com.softserve.edu.oms.data;

public class User {
	private String firstname;
	private String lastname;
	private String login;
	private String password;
	private String email;
	
	public User(String firstname, String lastname, String login,
			String password, String email) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.login = login;
		this.password = password;
		this.email = email;
	}

	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}

	public String getEmail() {
		return email;
	}

}
