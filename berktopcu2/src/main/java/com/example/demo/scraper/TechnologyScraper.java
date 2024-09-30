package com.example.demo.scraper;


import java.io.IOException;
import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.example.demo.enums.Interest;
import com.example.demo.models.ArticleModel;


public class TechnologyScraper {

	public static void main(String[] args) {
		
	        String homepageUrl = "https://www.ntv.com.tr/teknoloji";  // change this to for each scraper
	        
	        try {

	            Document homepageDocument = Jsoup.connect(homepageUrl).get();
	            
	            Elements listItems = homepageDocument.select("li.category-main-slider-pagination-item a");
	            
	            for (Element item : listItems) {
	                String url = item.attr("href");
	                //System.out.println(url);
	                String fullUrl = "https://www.ntv.com.tr" + url;
	                //System.out.println(fullUrl);
	                scrapeArticle(fullUrl);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        System.out.println("hello");
	    }
    
    private static void scrapeArticle(String url) {
        try {
            Document articleDocument = Jsoup.connect(url).get();
            
            Elements article = articleDocument.select(".category-detail-left");
            
            String header = article.select("h1.category-detail-title").text();
            String content = article.select("h2.category-detail-sub-title").text();
            
            Elements paragraphs = article.select("p");
            StringBuilder fullContent = new StringBuilder();
            for (Element p : paragraphs) {
                fullContent.append(p.text()).append("\n");
            }
            
            ArticleModel article1 = new ArticleModel(url, header, fullContent.toString().trim(), Interest.TECHNOLOGY, null);
            System.out.println(article1.toString());
            
            System.out.println("////////////////////////////////////////////////////////////////////////////////////////////\n////////////////////////////////////////////////////////////////////////////////////////////\n////////////////////////////////////////////////////////////////////////////////////////////");
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

