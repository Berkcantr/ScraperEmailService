package com.example.demo.scraper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.enums.Interest;
import com.example.demo.models.ArticleModel;
import com.example.demo.services.ArticleService;

@Service
public class SportScraper {

    @Autowired
    private ArticleService articleService;

   
    public void scrapeAndStoreArticles() {
        String homepageUrl = "https://spor.haber7.com/";
        
        try {

            Document homepageDocument = Jsoup.connect(homepageUrl).get();
            Elements articleLinks = homepageDocument.select("a.headline-slider-item");

            List<String> articleUrls = new ArrayList<>();
            for (Element link : articleLinks) {
                String url = link.attr("href");

         
                if (!url.startsWith("http")) {
                    url = homepageUrl + url;
                }
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
            Elements articleContent = articleDocument.select(".col-md-8");


            String header = articleContent.select("h1.title").text();
            String content = articleContent.select("h2.spot").text(); 

            
            StringBuilder fullContent = new StringBuilder();
            articleContent.select("p").forEach(p -> fullContent.append(p.text()).append("\n"));

            String imageUrl = articleContent.select("img").attr("src"); 

            if (articleService.findArticle(url) == null) {
                ArticleModel articleModel = new ArticleModel(url, header, fullContent.toString().trim(), Interest.SPORTS, imageUrl);
                articleService.addArticle(articleModel);
                System.out.println("Saved article: " + header);
            } else {
                System.out.println("Article already exists: " + url);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
