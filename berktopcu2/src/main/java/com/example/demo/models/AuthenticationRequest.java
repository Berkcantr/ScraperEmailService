package com.example.demo.models;

import com.example.demo.enums.Interest;

public class AuthenticationRequest {

	private String username;
	private String password;
	private Interest interest;
	private int age;
	
	public AuthenticationRequest() {
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
	public Interest getInterest() {
		return interest;
	}
	public void setInterest(Interest interest) {
		this.interest = interest;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	
	
	
}
