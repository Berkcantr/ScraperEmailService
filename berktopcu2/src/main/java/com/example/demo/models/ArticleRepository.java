package com.example.demo.models;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import com.example.demo.enums.Interest;
import com.example.demo.models.ArticleModel;

@Repository
public interface ArticleRepository extends MongoRepository<ArticleModel, String> {

	
	List<ArticleModel> findByCategory(Interest category);
	
	ArticleModel findByUrl(String url);
	
}