package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.enums.Interest;
import com.example.demo.models.UserModel;
import com.example.demo.models.UserRepository;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserModel foundUser = userRepository.findByUsername(username);
		if(foundUser == null) {
			return null;
		}
		String name = foundUser.getUsername();
		String password = foundUser.getPassword();
		int age = foundUser.getAge();
		List<Interest> interest = foundUser.getInterests();
		
		
		return new User(name, password, new ArrayList<>());
	}
	
	public List<String> getAllUserEmails() {
        return userRepository.findAll()
                             .stream()
                             .map(UserModel::getUsername)
                             .collect(Collectors.toList());
    }
	
	
}

