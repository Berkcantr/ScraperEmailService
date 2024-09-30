package com.example.demo;

import com.example.demo.enums.Interest;
import com.example.demo.models.ArticleModel;
import com.example.demo.models.ArticleRepository;
import com.example.demo.services.ArticleService;

import net.datafaker.Faker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.List;

public class ArticleServiceTest {

    @Mock
    private ArticleRepository articleRepository;

    @InjectMocks
    private ArticleService articleService;
    
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    
    private static Faker faker = new Faker();
    String fakeurl = faker.internet().url();
    String fakeurl2 = faker.internet().url();
    String faketitle = faker.book().title();
    String faketitle2 = faker.book().title();
    String fakecontent = faker.lorem().paragraph();
    String fakecontent2 = faker.lorem().paragraph();
    
    @Test
    void testAddArticle() {
        ArticleModel article = new ArticleModel(fakeurl, faketitle, fakecontent, Interest.SPORTS, "image url");
        when(articleRepository.save(article)).thenReturn(article);
        ArticleModel savedArticle = articleService.addArticle(article);

        assertEquals(fakeurl, savedArticle.getUrl());
        assertEquals(faketitle, savedArticle.getHeader());
        verify(articleRepository, times(1)).save(article);
    }
    
    @Test
    void testGetAllArticles() {
        List<ArticleModel> articles = List.of(
                new ArticleModel(fakeurl, faketitle, fakecontent, Interest.SPORTS, "image url"),
                new ArticleModel(fakeurl2, faketitle2, fakecontent2, Interest.FINANCE, null)
        );
        when(articleRepository.findAll()).thenReturn(articles);

        List<ArticleModel> fetchedArticles = articleService.getAllArticles();
        assertEquals(2, fetchedArticles.size());
        assertEquals(fakeurl, fetchedArticles.get(0).getUrl());
        assertEquals(fakeurl2, fetchedArticles.get(1).getUrl());
        verify(articleRepository, times(1)).findAll();
    }
    
}
