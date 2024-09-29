package com.example.demo.scraper;

import java.io.IOException;
import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.example.demo.enums.Interest;
import com.example.demo.models.ArticleModel;


public class TestScraper {

	public static void main(String[] args) {
        
		String homepageUrl = "https://www.trthaber.com/haber/saglik/3.sayfa.html";
        
        try {

            Document homepageDocument = Jsoup.connect(homepageUrl).get();
            System.out.println(homepageDocument);
            Elements articleLinks = homepageDocument.select("div.bullets .bullets a");


            ArrayList<String> articleUrls = new ArrayList<>();
            for (Element link : articleLinks) {
                String url = link.attr("href");
                articleUrls.add(url);
                System.out.println(url);
            }

            /*for (String articleUrl : articleUrls) {
                scrapeArticle(articleUrl);
            }*/
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}/*
public static void main(String[] args) {
    String html = "<div class=\"bottom-container\">"
            + "<!-- The provided HTML goes here -->"
            + "</div>";

    // Parse the HTML
    Document doc = Jsoup.parse(html);

    // Select all <a> tags with href attribute
    Elements links = doc.select("a[href]");

    // Print out the href attributes
    for (Element link : links) {
        System.out.println(link.attr("href"));
    }
}*/