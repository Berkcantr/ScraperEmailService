package com.example.demo.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.enums.Interest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Document(collection = "users")
public class UserModel implements UserDetails {

	
    @Id
    private String id;
    private String username;
    private String password;
    private int age;
    private List<Interest> interests = new ArrayList<>();
    
    /*
     * 
     * 
     *
     * 
     * store news articles in mongodb
     * 
     *																														
     * Create the remaining scrapers and create homepage
     * 
     * 
     * create tests
     */
    
    
    
    
    public UserModel() {
    }

    
    public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public List<Interest> getInterests() {
		return interests;
	}
	public void setInterests(List<Interest> interests) {
		this.interests = interests;
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
	
	
	public void addInterest(Interest interest) {
        if (!this.interests.contains(interest)) {
            this.interests.add(interest);
        }
    }

    public void removeInterest(Interest interest) {
        this.interests.remove(interest);
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

