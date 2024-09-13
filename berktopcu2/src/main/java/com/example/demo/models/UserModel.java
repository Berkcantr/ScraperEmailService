package com.example.demo.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.enums.Interest;

import java.util.Collection;


@Document(collection = "users")
public class UserModel implements UserDetails {

    @Id
    private String Id;
    private String username;
    private String password;
    private int age;
    //MAKE INTO ARRAYLIST
    private Interest interest;
    
    public UserModel() {
    }

    
    public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Interest getInterest() {
		return interest;
	}
	public void setInterest(Interest interest) {
		this.interest = interest;
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

	@Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;  // Adjust logic if necessary
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;  // Adjust logic if necessary
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;  // Adjust logic if necessary
    }

    @Override
    public boolean isEnabled() {
        return true;  // Adjust logic if necessary
    }
}

