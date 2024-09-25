package com.example.demo.models;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import com.example.demo.models.UserModel;

@Repository
public interface UserRepository extends MongoRepository<UserModel, String> {

	
	UserModel findByUsername(String username);
	//Optional<UserModel> findByUsername(String username);
	

	
	   

	
	
	
}
