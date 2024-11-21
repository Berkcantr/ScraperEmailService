package com.example.demo;

import java.util.Date;

public class Article {
	private String url;
	private String header;
	private String content;
	private String category;
	private Date publishedDate;
	
	public Article(String url, String header, String content) {
		this.url = url;
		this.header = header;
		this.content = content;
		this.category = category;
		this.publishedDate = publishedDate;
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

	
	public void setUrl(String url) {
		this.url = url;
	}
	public void setHeader(String header) {
		this.header = header;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public String toString() {
		return url + "\nHeader: " + header + "\n\n" + content; 
	}
	
	
}
