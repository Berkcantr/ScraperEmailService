package com.example.demo.scraper;


import java.io.IOException;
import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.example.demo.enums.Interest;
import com.example.demo.models.ArticleModel;


public class HealthScraper {

	public static void main(String[] args) {
        
		String homepageUrl = "https://www.trthaber.com/haber/saglik/2.sayfa.html";
        
        try {

            Document homepageDocument = Jsoup.connect(homepageUrl).get();
            
            Elements articleLinks = homepageDocument.select("a.headline-slider-item");


            ArrayList<String> articleUrls = new ArrayList<>();
            for (Element link : articleLinks) {
                String url = link.attr("href");
                articleUrls.add(url);
                System.out.println(url);
            }

            for (String articleUrl : articleUrls) {
                scrapeArticle(articleUrl);
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    //print news. If statements go here:
    private static void scrapeArticle(String url) {
        try {
            Document articleDocument = Jsoup.connect(url).get();
            
            Elements article = articleDocument.select(".col-md-8");
            
            String header = article.select("h1.title").text();
            String content = article.select("h2.spot").text();
            
            Elements paragraphs = article.select("p");
            StringBuilder fullContent = new StringBuilder();
            for (Element p : paragraphs) {
                fullContent.append(p.text()).append("\n");
            }
            
            ArticleModel article1 = new ArticleModel(url, header, fullContent.toString().trim(), Interest.HEALTH);
            System.out.println(article1.toString());
            
            System.out.println("////////////////////////////////////////////////////////////////////////////////////////////\n////////////////////////////////////////////////////////////////////////////////////////////\n////////////////////////////////////////////////////////////////////////////////////////////");
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
