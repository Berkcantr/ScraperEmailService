package com.example.demo;

import java.io.IOException;
import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WebScraper {

    public static void main(String[] args) {
        //https://www.youtube.com/watch?v=OxyBRjGfJsk    JWT auth  <-------- JWT Dependency
        String homepageUrl = "https://spor.haber7.com/";
        
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
    
    private static void scrapeArticle(String url) {
        try {
            Document articleDocument = Jsoup.connect(url).get();
            //change from this print logic to simply using toString()
            //if url is www.spor.haber7....... do A
            // else if string content == null, look for h3.spot (example)
            //else if url is www.haber7....... do B
            //else return not available and dont print the article
            
            Elements article = articleDocument.select(".col-md-8");
            
            String header = article.select("h1.title").text();
            String content = article.select("h2.spot").text();
            
            Elements paragraphs = article.select("p");
            StringBuilder fullContent = new StringBuilder();
            for (Element p : paragraphs) {
                fullContent.append(p.text()).append("\n");
            }
            
            System.out.println("Article URL: " + url + "\n");
            System.out.println(header + "\n");
            System.out.println(content + "\n");
            System.out.println(fullContent.toString().trim());
            System.out.println("////////////////////////////////////////////////////////////////////////////////////////////\n\n////////////////////////////////////////////////////////////////////////////////////////////");
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
