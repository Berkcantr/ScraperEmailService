package com.example.demo.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.enums.Interest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Document(collection = "users")
public class UserModel implements UserDetails {

	
    @Id
    private String Id;
    private String username;
    private String password;
    private int age;
    private List<Interest> interests = new ArrayList<>();
    
    /*
     * 
     * +++When the user fills out the form and clicks "Sign Up," it sends a POST request to /submit-signup. You can handle this in your Spring Boot controller by mapping this path.
     * 
     * 
     * keep users hashed passwords instead of the real thing. ----later
     * 
     * store news articles in elastic search ---- NOW
     * 
     * Index.html is working for the most part, get signup and login working, then have
     * login page auth users instead thorough postman. Once done, create 
     * a home page that displays the news as text  ---- HTML is done, POST action needs logic
     *																														
     * Create the remaining scrapers and create homepage
     * 
     * create email service
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

