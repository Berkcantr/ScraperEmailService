package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import com.example.demo.enums.Interest;
import com.example.demo.models.ArticleModel;
import com.example.demo.models.UserModel;
import com.example.demo.scraper.BlogScraper;
import com.example.demo.scraper.SportScraper;
import com.example.demo.services.ArticleService;

import java.util.List;


@RestController
@RequestMapping("/articles")
public class ArticleController {

    @Autowired
    private ArticleService articleService;
    
    @Autowired
    private SportScraper sportScraper;
    
    @Autowired
    private BlogScraper blogScraper;

    @GetMapping("/list")
    public ResponseEntity<List<ArticleModel>> getAllArticles() {
        return ResponseEntity.ok(articleService.getAllArticles());
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<ArticleModel>> getArticlesByCategory(@PathVariable Interest category) {
        return ResponseEntity.ok(articleService.getArticlesByCategory(category));
    }

    @PostMapping("/upload")
    public ResponseEntity<ArticleModel> addArticle(@RequestBody ArticleModel article) {
        return ResponseEntity.ok(articleService.addArticle(article));
    }
    

    @GetMapping("/content")
    public ResponseEntity<ArticleModel> getArticle(@RequestParam String url) {
    	ArticleModel targetArticle = articleService.findArticle(url);
    	return ResponseEntity.ok(targetArticle);
    }
    
    
    //change to just scrape later
    @GetMapping("/scrape")
    public ResponseEntity<String> scrapeSportsArticles() {
        sportScraper.scrapeAndStoreArticles();
        blogScraper.scrapeAndStoreArticles();
        return ResponseEntity.ok("Sports articles scraped and stored.");
    }
}
    
