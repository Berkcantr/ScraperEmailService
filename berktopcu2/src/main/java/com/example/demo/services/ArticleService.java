package com.example.demo.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.enums.Interest;
import com.example.demo.models.ArticleModel;
import com.example.demo.models.ArticleRepository;

import java.util.List;
import java.util.Locale;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    public List<ArticleModel> getAllArticles() {
        return articleRepository.findAll();
    }

    public List<ArticleModel> getArticlesByCategory(Interest category) {
    	return articleRepository.findByCategory(category);
    }

    public ArticleModel addArticle(ArticleModel article) {
        return articleRepository.save(article);
    }
    
    public ArticleModel findArticle(String url) {
    	return articleRepository.findByUrl(url);
    }
    
}
