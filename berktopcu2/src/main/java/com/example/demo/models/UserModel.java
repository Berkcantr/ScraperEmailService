package com.example.demo.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;

@Document(collection = "users")
public class UserModel implements UserDetails {

    @Id
    private String Id;
    private String username;
    private String password;

    public UserModel() {
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
        // You can return authorities (roles/permissions) here if needed, otherwise return an empty list
        return null; // Or return an empty list if not using roles
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

