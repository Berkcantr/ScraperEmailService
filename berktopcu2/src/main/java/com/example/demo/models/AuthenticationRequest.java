package com.example.demo.models;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.enums.Interest;

public class AuthenticationRequest {

	private String username;
	private String password;
	private List<Interest> interests = new ArrayList<>();
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
	public List<Interest> getInterests() {
		return interests;
	}
	public void setInterests(List<Interest> interests) {
		this.interests = interests;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	public void addInterest(Interest interest) {
        if (!this.interests.contains(interest)) {
            this.interests.add(interest);
        }
    }

    public void removeInterest(Interest interest) {
        this.interests.remove(interest);
    }
	
	
}
