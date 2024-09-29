package com.example.demo.models;

import org.springframework.data.mongodb.core.mapping.Document;

import com.example.demo.enums.Interest;

// add image field

@Document(collection = "articles")
public class ArticleModel {
	private String url;
	private String header;
	private String content;
	private Interest category;
	private String image;
	
	public ArticleModel(String url, String header, String content, Interest category, String image) {
		this.url = url;
		this.header = header;
		this.content = content;
		this.category = category;
		this.image = image;
	}

	public ArticleModel() {}
	
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getUrl() {
		return url;
	}
	public String getHeader() {
		return header;
	}
	public String getContent() {
		return content;
	}
	public Interest getCategory() {
		return category;
	}
	
	
	
	public void setUrl(String url) {
		this.url = url;
	}
	public void setHeader(String header) {
		this.header = header;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public void setCategory(Interest category) {
		this.category = category;
	}
	
	public String toString() {
		return /*image +*/ "\n" + url + "\nHeader: " + header + "\n\n" + content; 
	}
	
	
}
