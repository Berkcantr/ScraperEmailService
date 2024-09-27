package com.example.demo.scraper;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.models.ArticleModel;
import com.example.demo.Berktopcu2Application;
import com.example.demo.enums.Interest;
import com.example.demo.services.ArticleService;

@Service
public class SportScraper {

    @Autowired
    private ArticleService articleService;

    public void main(String[] args) {
		scrapeAndStoreArticles();
	}
    
    public void scrapeAndStoreArticles() {
        String homepageUrl = "https://spor.haber7.com/";
        
        try {
            Document homepageDocument = Jsoup.connect(homepageUrl).get();
            Elements articleLinks = homepageDocument.select("a.headline-slider-item");

            List<String> articleUrls = new ArrayList<>();
            for (Element link : articleLinks) {
                String url = link.attr("href");
                articleUrls.add(url);
            }

            for (String articleUrl : articleUrls) {
                scrapeAndSaveArticle(articleUrl);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void scrapeAndSaveArticle(String url) {
        try {
            Document articleDocument = Jsoup.connect(url).get();
            Elements article = articleDocument.select(".col-md-8");

            String header = article.select("h1.title").text();
            String content = article.select("h2.spot").text();
            //String image = article.select(").text();

            StringBuilder fullContent = new StringBuilder();
            article.select("p").forEach(p -> fullContent.append(p.text()).append("\n"));

            // Create and save the article
            ArticleModel articleModel = new ArticleModel(url, header, fullContent.toString().trim(), Interest.SPORTS, null);
            articleService.addArticle(articleModel);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
