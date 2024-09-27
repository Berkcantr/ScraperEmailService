package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.enums.Interest;
import com.example.demo.models.AuthenticationRequest;
import com.example.demo.models.AuthenticationResponse;
import com.example.demo.models.UserModel;
import com.example.demo.models.UserRepository;
import com.example.demo.services.UserService;
import com.example.demo.utils.JwtUtils;
import java.util.Optional;

@RestController
public class AuthController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtUtils jwtUtils;
	
	//@/Autowired
	@PostMapping("/subs")
	private ResponseEntity<?> subscribeClient(@RequestBody AuthenticationRequest authenticationRequest) {
		
		String username = authenticationRequest.getUsername();
		String password = authenticationRequest.getPassword();
		int age = authenticationRequest.getAge();
		List<Interest> interests = authenticationRequest.getInterests();
		
		UserModel userModel = new UserModel();
		userModel.setUsername(username);
		userModel.setPassword(password);
		userModel.setAge(age);
		userModel.setInterests(interests);
		
		
		try {
			userRepository.save(userModel);
		}
		catch(Exception e) {
			return ResponseEntity.ok(new AuthenticationResponse("Error occured for " + username));
		}
		return ResponseEntity.ok(new AuthenticationResponse("Successful Subscription for " + username));
	}
	
	
	@PutMapping("/edit")
	public ResponseEntity<UserModel> editUser(@RequestParam String username, @RequestBody UserModel updatedUser) {
	    UserModel editedUser = userService.editUser(username, updatedUser);
	    System.out.println("Edited user: " + editedUser.getUsername());
	    return ResponseEntity.ok(editedUser);
	}

	
	@PostMapping("/auth")
	private ResponseEntity<?> authenticateClient(@RequestBody AuthenticationRequest authenticationRequest) {
		String username = authenticationRequest.getUsername();
		String password = authenticationRequest.getPassword(); 
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		}
		catch (BadCredentialsException e) {
			return ResponseEntity.ok(new AuthenticationResponse("Error occured authenticating " + username));
		}
		
		UserDetails loadedUser = userService.loadUserByUsername(username);
		
		System.out.println("Authenticating user: " + loadedUser.getUsername());

		
		String generatedToken = jwtUtils.generateToken(loadedUser);
		
		return ResponseEntity.ok(new AuthenticationResponse("Successful authentication using " + generatedToken));
	}
	
	/*Insert vald service ontop of every html, making sure to check token validity each time user takes
	 * an action on the website. Look into extending lifetime of a token for each action
	 * done while token is still valid.
	 * 
	 */
	
	@GetMapping("/vald")
    public ResponseEntity<String> validateToken(@RequestHeader("Authorization") String token) {
        try {
        	
            if (token.startsWith("Bearer ")) {
                token = token.substring(7);
            }

            String username = jwtUtils.extractUsername(token);
            UserDetails userDetails = userService.loadUserByUsername(username);

            if (jwtUtils.validateToken(token, userDetails)) {
                return ResponseEntity.ok("Token is valid");
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid token");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid token");
        }
    }
}