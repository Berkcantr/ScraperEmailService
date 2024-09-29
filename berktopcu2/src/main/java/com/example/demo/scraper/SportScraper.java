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

    // Method to initiate the scraping process
    public void scrapeAndStoreArticles() {
        String homepageUrl = "https://spor.haber7.com/";
        
        try {
            // Fetch the homepage and extract article URLs
            Document homepageDocument = Jsoup.connect(homepageUrl).get();
            Elements articleLinks = homepageDocument.select("a.headline-slider-item");

            List<String> articleUrls = new ArrayList<>();
            for (Element link : articleLinks) {
                String url = link.attr("href");

                // Ensure we have full URLs (relative links need to be prefixed with homepage URL)
                if (!url.startsWith("http")) {
                    url = homepageUrl + url;
                }
                articleUrls.add(url);
            }

            // For each article URL, scrape and save the article
            for (String articleUrl : articleUrls) {
                scrapeAndSaveArticle(articleUrl);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to scrape and save an individual article
    private void scrapeAndSaveArticle(String url) {
        try {
            // Fetch the article content using the provided URL
            Document articleDocument = Jsoup.connect(url).get();
            Elements articleContent = articleDocument.select(".col-md-8");

            // Extract the title, content, and other fields
            String header = articleContent.select("h1.title").text();
            String content = articleContent.select("h2.spot").text(); // Adjust if needed

            // Extract full article content (all paragraphs)
            StringBuilder fullContent = new StringBuilder();
            articleContent.select("p").forEach(p -> fullContent.append(p.text()).append("\n"));

            // Optionally extract an image URL (uncomment if image is available)
            String imageUrl = articleContent.select("img").attr("src");  // Adjust the selector

            // Check if the article already exists in the DB to avoid duplicates
            if (articleService.findArticle(url) == null) {
                // Create and save the article in the database
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
